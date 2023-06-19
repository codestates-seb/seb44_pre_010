package com.rainbow.sof.domain.question.user.service;


import com.rainbow.sof.domain.question.user.entity.User;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import com.rainbow.sof.domain.question.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private void verifyExistsEmail(String email) {
        Optional<User> findUsers = repository.findByEmail(email);
        if (findUsers.isPresent()){
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }


}
