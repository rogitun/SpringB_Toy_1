package com.hanselone.demo.controller;

import com.hanselone.demo.dto.CategoryDto;
import com.hanselone.demo.dto.PostDto;
import com.hanselone.demo.repository.CategoryRepository;
import com.hanselone.demo.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/submit")
    public String submitForm(Model model){
        model.addAttribute("post",new PostDto());
        return "/post/postForm";
    }

    @PostMapping("/post/submit")
    public String submit(PostDto postDto, @AuthenticationPrincipal User user){
        postDto.setUsername(user.getUsername());
        postService.submit(postDto);
        return "redirect:/";
    }
}
