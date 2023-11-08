package com.jo0oy.springsecuritydemo.security;

import com.jo0oy.springsecuritydemo.global.exception.UserNotFoundException;
import com.jo0oy.springsecuritydemo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("{} ::: {}", getClass().getSimpleName(), "loadUserByUsername");

        return new AuthUser(
            userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new)
        );
    }
}
