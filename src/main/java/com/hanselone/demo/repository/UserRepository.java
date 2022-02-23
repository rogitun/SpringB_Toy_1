package com.hanselone.demo.repository;

import com.hanselone.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public String save(User user){
        em.persist(user);
        return user.getUser_id();
    }

    public User findOne(String user_id){
        User findUser = em.find(User.class,user_id);
        return findUser;
    }
}
