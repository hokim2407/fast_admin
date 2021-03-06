package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import com.example.study.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

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
    }

    @Test
    public void read( ) {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser -> {

        });
    }


    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser->
        {
            selectedUser.setAccount("pppp");
            userRepository.save(selectedUser);
        }
        );
    }

    @Test
    public void delete() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser->
                    userRepository.delete(selectedUser)
        );

        Optional<User> deletedUser = userRepository.findById(1L);
        if(deletedUser.isPresent())
            System.out.println(deletedUser);
        else
            System.out.println("NO DATA");
    }
}
