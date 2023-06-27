package com.rainbow.sof.domain.user.auth.util.userDetail;

import com.rainbow.sof.domain.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UsersDetail extends User implements UserDetails {
    public UsersDetail(User user) {
        this.updateUserId(user.getUserId());
        this.updateEmail(user.getEmail());
        this.updateName(user.getName());
        this.updatePassword(user.getPassword());
        this.updateQuestionList(user.getQuestionList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
