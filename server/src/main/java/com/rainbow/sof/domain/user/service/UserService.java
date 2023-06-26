package com.rainbow.sof.domain.user.service;


import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import com.rainbow.sof.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user){
        verifyExistsEmail(user.getEmail());
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.updatePassword(passwordEncode);
        return saveUser(user);
    }

    public User OauhtCreateUser(User user){
        verifyExistsEmail(user.getEmail());
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.updatePassword(passwordEncode);
        return saveUser(user);
    }

//    public User createUser(Map<String, String> userdata){
//        verifyExistsEmail(email);
//        User user = findByUserFromEmail(email);
//        String passwordEncode = passwordEncoder.encode(user.getPassword());
//        user.updatePassword(passwordEncode);
//        return saveUser(user);
//    }

    public User updateUser(String email, long id, UserDto.Patch patchUser){
        verifyExistsEmail(patchUser.getName());
        User updateUser =  checkToFindByUserFromEmail(email,id);
        Optional.ofNullable(patchUser.getName())
                .ifPresent(updateUser::updateName);

        return findVerifiedUser(id);

    }

    public void deleteUser(String email,long userId){
        User disableUser = checkToFindByUserFromEmail(email,userId);
        disableUser.updateStatus(User.Status.USER_QUIT);
        disableUser.updateEmail("disable@disable.com");
        disableUser.updateModifiedAt(LocalDateTime.now());

        saveUser(disableUser);
    }

    public User findVerifiedUser(long userId) {
        Optional<User> findUser = repository.findById(userId);
        return findUser.orElseThrow(
                ()-> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
        );
    }

    public User checkToFindByUserFromEmail(String email,long id){
        User findUser = findByUserFromEmail(email);
        compareToEntityCheck(findUser,id);

        return findUser;

    }

    public User findByUserFromEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        User findUser = user.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findUser;
    }

    private User saveUser(User user) {
        return repository.save(user);
    }

    private void compareToEntityCheck(User user,long id) {
        IsUserActive(user);
        if (user.getUserId() != id){
            throw new BusinessLogicException(ExceptionCode.INVALID_TOKEN);
        }

    }

    private void IsUserActive(User user) {
        if (user.getStatus().equals(User.Status.USER_QUIT)){
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        };
    }

    private void verifyExistsEmail(String email) {
        Optional<User> findUsers = repository.findByEmail(email);
        if (findUsers.isPresent()){
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }



}