package com.jo0oy.springsecuritydemo.user;

import com.jo0oy.springsecuritydemo.global.exception.DuplicateUsernameException;
import com.jo0oy.springsecuritydemo.global.exception.UserNotFoundException;
import com.jo0oy.springsecuritydemo.user.dto.UserRegisterDto;
import com.jo0oy.springsecuritydemo.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private static final String USER = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";

    @Transactional
    public UserDto signUp(UserRegisterDto request) {
        log.info("{} ::: {}", getClass().getSimpleName(), "signUp");

        validateDuplicateUsername(request.username());

        return new UserDto(
          userRepository.save(request.toEntity(USER))
        );
    }

    @Transactional
    public UserDto signUpAdmin(UserRegisterDto request) {
        log.info("{} ::: {}", getClass().getSimpleName(), "signUpAdmin");

        validateDuplicateUsername(request.username());

        return new UserDto(
            userRepository.save(request.toEntity(ADMIN))
        );
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(UserNotFoundException::new);
    }


    private void validateDuplicateUsername(String username) {
        if(userRepository.existsByUsername(username))
            throw new DuplicateUsernameException();
    }
}
