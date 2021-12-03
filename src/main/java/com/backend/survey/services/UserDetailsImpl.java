package com.backend.survey.services;

import com.backend.survey.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails {
    private long userId;
    private String username;
    @JsonIgnore
    private String password;
    private String role;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserEntity userEntity) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(userEntity.getRole()));

        UserDetailsImpl userDetails = new UserDetailsImpl(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole(),
                authorityList
        );
        return userDetails;
    }

    public UserDetailsImpl(long userId, String username, String password, String role, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
