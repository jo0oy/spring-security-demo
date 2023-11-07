package com.jo0oy.springsecuritydemo.user.web;

import com.jo0oy.springsecuritydemo.user.UserService;
import com.jo0oy.springsecuritydemo.user.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/signup")
@Controller
public class SignUpController {

    private final UserService userService;

    @GetMapping("")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("")
    public String signUp(@ModelAttribute UserRegisterDto request) {
        userService.signUp(request);

        return "redirect:login";
    }
}
