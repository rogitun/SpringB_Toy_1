package com.hanselone.demo.service;

import com.hanselone.demo.domain.User;
import com.hanselone.demo.dto.PostDto;
import com.hanselone.demo.repository.PostRepository;
import com.hanselone.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void submit(PostDto postDto){
        List<User> byAccount = userRepository.findByAccount(postDto.getUsername());
        postDto.setWriter(byAccount.get(0));
        postRepository.save(postDto.toEntity());
    }
}
