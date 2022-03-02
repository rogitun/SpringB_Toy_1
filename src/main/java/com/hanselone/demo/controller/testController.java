package com.hanselone.demo.controller;

import com.hanselone.demo.domain.Post;
import com.hanselone.demo.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class testController {

    private final PostRepository postRepository;

    @GetMapping("/")
    public String test(Model model) {

        List<Post> all = postRepository.findBySuggestion();
        model.addAttribute("posts", all);
        return "hello/hello";

    }


}
