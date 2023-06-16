package com.rainbow.sof.user.service;


import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import com.rainbow.sof.user.entity.User;
import com.rainbow.sof.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;

    public User createUser(User user){
        verifyExistsEmail(user.getEmail());
//        String passwordEncode = passwordEncoder.encode(user.getPassword());
        return repository.save(user);
    }

    private void verifyExistsEmail(String email) {
        Optional<User> findUsers = repository.findByEmail(email);
        if (findUsers.isPresent()){
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
    }


}
