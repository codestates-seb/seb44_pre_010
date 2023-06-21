package com.rainbow.sof.domain.user.service;


import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import com.rainbow.sof.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1:server/src/main/java/com/rainbow/sof/domain/user/service/UserService.java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
<<<<<<< HEAD:server/src/main/java/com/rainbow/sof/user/service/UserService.java
=======
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user){
        verifyExistsEmail(user.getEmail());
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.updatePassword(passwordEncode);
        return repository.save(user);
    }

    public void deleteUser(long userId){
        User user = findVerifiedUser(userId);

        repository.delete(user);
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

    private void compareToEntityCheck(User user,long id) {
        if (user.getUserId() != id){
            throw new BusinessLogicException(ExceptionCode.INVALID_TOKEN);
        }
    }

    private void verifyExistsEmail(String email) {
        Optional<User> findUsers = repository.findByEmail(email);
        if (findUsers.isPresent()){
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }



}
