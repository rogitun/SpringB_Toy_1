package com.hanselone.demo.controller;

import com.hanselone.demo.domain.Post;
import com.hanselone.demo.dto.CategoryDto;
import com.hanselone.demo.dto.PostDto;
import com.hanselone.demo.repository.CategoryRepository;
import com.hanselone.demo.repository.PostRepository;
import com.hanselone.demo.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/posts")
    public String posts(Model model){
        List<Post> all = postRepository.findAll();
        model.addAttribute("posts",all);
        return "/post/posts";
    }

    @GetMapping("/posts/{postId}")
    public String detail(@PathVariable("postId") String postId,Model model){
        Post byId = postRepository.findById(postId);
        model.addAttribute("post",byId);

        return "post/detail";
    }

    @PostMapping("/posts/{postId}/up")
    public String postUp(@PathVariable("postId") String postId,Model model){
        postService.changeSuggestion(postId,1);
        return "redirect:/posts/{postId}";
    }
    @PostMapping("/posts/{postId}/down")
    public String postDown(@PathVariable("postId") String postId,Model model){
        postService.changeSuggestion(postId,-1);
        return "redirect:/posts/{postId}";
    }

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
