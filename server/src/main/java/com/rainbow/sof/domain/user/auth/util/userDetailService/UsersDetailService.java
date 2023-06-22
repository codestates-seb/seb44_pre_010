package com.rainbow.sof.domain.user.auth.util.userDetailService;

import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.auth.util.userDetail.UsersDetail;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.repository.UserRepository;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UsersDetailService implements UserDetailsService {
    private final UserRepository repository;

    public UsersDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(JwtTokenizer.getUSER_DISABLE_MAIL())){
            throw  new BusinessLogicException(ExceptionCode.ACCESS_DENIED);
        }
        Optional<User> optionalUser = repository.findByEmail(username);
        User user = optionalUser.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
        );
        return new UsersDetail(user);
    }


}