package com.hanselone.demo.domain;

import com.hanselone.demo.repository.CategoryRepository;
import com.hanselone.demo.repository.PostRepository;
import com.hanselone.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EnableJpaAuditing
@SpringBootTest
@Transactional
class PostTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Test //작성자로 글 찾기
    @Rollback(value = false)
    public void postTest(){
        Post post = new Post();
        post.setTitle("글 제목입니다");
        post.setBios("글 내용입니다.ㅇㅈ비;ㅇㅂ징베지엡지애ㅔ자애만이;ㅁ나이ㅏㅂ저기ㅏㅓㅂ다ㅣㅈㄷ버ㅏ");

        Post post2 = new Post();
        post2.setTitle("글 2");
        post2.setBios("dasdasdasds");

        User user = new User();
        user.setName("작성자");

        post.setWriter(user);
        post2.setWriter(user);
        user.getPosts().add(post);
        user.getPosts().add(post2);

        userRepository.save(user);

        List<Post> postsByWriter = postRepository.findPostsByWriter(user.getName());

        System.out.println("###############################");
        for(Post x : postsByWriter){
            System.out.println(x.getTitle());
        }
    }


    @Test //글 제목으로 찾기
    @Rollback(value = false)
    public void findByTitle(){
        Post post = new Post();
        post.setTitle("제목1");
        post.setBios("글 내용입니다");
       // post.setCreated(LocalDateTime.of(22,1,1,0,0));

        Post post2 = new Post();
        post2.setTitle("제목2");
        post2.setBios("dasdasdasds");
       // post2.setCreated(LocalDateTime.now());

        User user = new User();
        user.setName("작성자");

        post.setWriter(user);
        post2.setWriter(user);
        user.getPosts().add(post);
        user.getPosts().add(post2);
        userRepository.save(user);
        List<Post> finds = postRepository.findPosts("제목");

        System.out.println("###############################");
        for(Post x : finds){
            System.out.println(x.getTitle());
        }
        System.out.println("###############################");
    }

    @Test
    @Rollback(value = false)
    public void findByDate(){
        Post post = new Post();
        post.setTitle("제목1");
        post.setBios("제목1의 내용");

        Post post2 = new Post();
        post2.setTitle("제목2");
        post2.setBios("제목1의 내용");

        User user = new User();
        user.setName("작성자");

        post.setWriter(user);
        post2.setWriter(user);
        user.getPosts().add(post);
        user.getPosts().add(post2);
        userRepository.save(user);

       LocalDateTime one = LocalDateTime.of(2022,01,1,0,0);
       LocalDateTime two = LocalDateTime.of(2022,03,01,0,0);

        List<Post> byDate = postRepository.findByDate(one, two);
        System.out.println("$###########################");
        for(Post x : byDate){
            System.out.println(x.getTitle() + " | " +
                    x.getCreated());
        }
        System.out.println("$###########################");

    }

    @Test
    @Rollback(value = false)//카테고리로 찾기
    public void findByCategory(){
        Category first = new Category();
        first.setName("전체");
        Category category = new Category();
        category.setName("디지털");
        Category category2 = new Category();
        category2.setName("IT소식");

        first.getSubCategories().add(category);
        first.getSubCategories().add(category2);
        category.setParent(first);
        category2.setParent(first);


        Post post = new Post();
        post.setTitle("갤럭시s22 출시");
        post.setBios("갤럭시");
        post.setCategory(category2);

        Post post2 = new Post();
        post2.setTitle("hp노트북 삿어요");
        post2.setBios("노트북");
        post2.setCategory(category);

        User user = new User();
        user.setName("작성자");

        post.setWriter(user);
        post2.setWriter(user);
        user.getPosts().add(post);
        user.getPosts().add(post2);

        categoryRepository.save(first);
        userRepository.save(user);

        List<Post> all = postRepository.findByCategory("전체");
        List<Post> digital = postRepository.findByCategory("디지털");

        for(Post x : all){
            System.out.println(x.getTitle());
        }

    }
}