package com.rainbow.sof.domain.user.auth.util.userDetailService;

import com.rainbow.sof.domain.user.auth.util.userDetail.UsersDetail;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class UsersDetailService implements UserDetailsService {
    private final UserRepository repository;

    public UsersDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByEmail(username);
        User user = optionalUser.orElseThrow(
                () -> new UsernameNotFoundException("is not")
        );
        return new UsersDetail(user);
    }


}
