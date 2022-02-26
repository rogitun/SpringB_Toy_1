package com.hanselone.demo.controller;

import com.hanselone.demo.dto.UserDto;
import com.hanselone.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user/signup")
    public String signupForm(Model model){
        model.addAttribute("user",new UserDto());

        return "/user/signUpForm";
    }

    @PostMapping("/user/signup")
    public String signUp(UserDto userDto){
        userService.singUp(userDto);
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login(){
        return "user/loginForm";
    }
}
