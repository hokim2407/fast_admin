package com.example.study.controller.api;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.network.Header;
import com.example.study.network.request.UserApiRequest;
import com.example.study.network.response.request.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request.getData());
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request.getData());
        return userApiLogicService.update(request);
    }

    @Override
    @GetMapping("")
    public Header<UserApiResponse> delete(@PathVariable Long id) {
        return userApiLogicService.delete(id);
    }
}

