package com.hanselone.demo.repository;

import com.hanselone.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public String save(User user){
        em.persist(user);
        return user.getUser_id();
    }

}
