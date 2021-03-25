package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import com.example.study.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {
    //dependency injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
        user.setAccount("user3");
        user.setEmail("user3@gmail.com");
        user.setPhoneNumber("010-0300-0000");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        User saved = userRepository.save(user);
        System.out.println(saved);
        //showAll();
    }


}
