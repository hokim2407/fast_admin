package com.example.study.controller.api;


import com.example.study.model.entity.User;
import com.example.study.model.repository.UserRepository;
import com.example.study.network.Header;
import com.example.study.network.request.UserApiRequest;
import com.example.study.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/user2")
public class UserCheckOverlapApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserApiLogicService userApiLogicService;

    @PostMapping("")
    public ResponseEntity<Header<UserApiResponse>> create(@RequestBody Header<UserApiRequest> request) {
        Optional<User> user = userRepository.findByEmail(request.getData().getEmail());
        if (!user.isPresent()) {
            System.out.println(request.getData());
            return ResponseEntity.ok().body(userApiLogicService.create(request));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Header.ERROR("중복된 이메일 입니다."));
    }

}

