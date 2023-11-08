package com.jo0oy.springsecuritydemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

        http

            .httpBasic(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(authorize -> {
                authorize
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers(PathRequest.toH2Console()).permitAll()

                    .requestMatchers(
                        mvc.pattern("/"),
                        mvc.pattern("/home"),
                        mvc.pattern("/signup")
                    ).permitAll()

                    .requestMatchers(
                        mvc.pattern("/note"),
                        mvc.pattern("/notes")
                    ).hasRole("USER")

                    .requestMatchers(
                        mvc.pattern("/note/admin")
                    ).hasRole("ADMIN")

                    .requestMatchers(
                        mvc.pattern(HttpMethod.POST, "/notices")
                    ).hasRole("ADMIN")

                    .requestMatchers(
                        mvc.pattern(HttpMethod.DELETE, "/notices")
                    ).hasRole("ADMIN")

                    .anyRequest().authenticated();
            })

            .formLogin(formLoginConfigurer -> {
                formLoginConfigurer
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll();
            })

            .logout(logoutConfigurer -> {
                logoutConfigurer
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");
            });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
