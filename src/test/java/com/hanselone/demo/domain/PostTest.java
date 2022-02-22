package com.hanselone.demo.domain;

import com.hanselone.demo.repository.PostRepository;
import com.hanselone.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void postTest(){
        Post post = new Post();
        post.setTitle("글 제목입니다");
        post.setBios("글 내용입니다.ㅇㅈ비;ㅇㅂ징베지엡지애ㅔ자애만이;ㅁ나이ㅏㅂ저기ㅏㅓㅂ다ㅣㅈㄷ버ㅏ");
        
        User user = new User();
        user.setName("작성자");

        post.setWriter(user);
        user.getPosts().add(post);

        userRepository.save(user);

        System.out.println( "########################### " + " \n" +post.getWriter().getName());

    }

}