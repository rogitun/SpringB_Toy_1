package com.hanselone.demo.domain;

import com.hanselone.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserTest {


    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void userTest() {
        User user1 = new User();
        user1.setName("hansel");
        user1.setBios("앶대ㅏㅂ재데ㅏㅂ재ㅔ답제ㅐㅏㄷㅂ제ㅐ닺배ㅔ답제ㅐ다ㅐㅔㅂ잗ㅂ재ㅔㅏㄷ재ㅔㅂ다ㅐㅔㅂㅈ다ㅐ");

        userRepository.save(user1);
    }

}