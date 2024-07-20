package com.book.sphere.security;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.sphere.user.User;
import com.book.sphere.user.UserRepository;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
	
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = repository.findByEmail(userEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getName())
            .password(user.getPassword())
            .authorities("USER") // Customize as needed
            .build();
    }
}