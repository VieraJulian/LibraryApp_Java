package com.library.management.service;

import com.library.management.model.UserEntity;
import com.library.management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("the user with the name " + username + " does not exist."));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRole().getName().name()));

        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, Collections.singleton(authority));
    }
}
