package com.hanselone.demo.repository;

import com.hanselone.demo.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    @Transactional
    public void save(Post post){
        em.persist(post);
    }

    public Post findById(String id){
        Post post = em.find(Post.class, id);
        return post;
    }

    //글 제목으로 검색
    public List<Post> findPosts(String title){
        List posts = em.createQuery("select p from Post p where p.title like concat('%',:title,'%')",Post.class)
                .setParameter("title", title)
                .getResultList();
        return posts;
    }

    //작성자로 검색
    public List<Post> findPostsByWriter(String name){
        List findPosts = em.createQuery("select p from Post p right outer join p.writer u on u.name = :name",Post.class)
                .setParameter("name", name)
                .getResultList();
        return findPosts;
    }

    //특정 시점 사이 글 검색
    public List<Post> findByDate(LocalDateTime start, LocalDateTime end){
        List findPosts = em.createQuery("select p from Post p where p.created between :start and :end",Post.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();

        return findPosts;
    }
    
    //카테고리별 검색 => 카테고리 이름으로
    public List<Post> findByCategory(String name){
        List findPosts = em.createQuery("select p from Post p inner join p.category c on c.name =: name",Post.class)
                .setParameter("name", name)
                .getResultList();

        return findPosts;
    }

    //카테고리 무관 전체
    public List<Post> findAll(){
        List<Post> postAll = em.createQuery("select p from Post p", Post.class).getResultList();
        return postAll;
    }
    
    
    public List<Post> findBySuggestion(){
        return em.createQuery("select p from Post p order by p.suggestion desc",Post.class)
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
    }



}
