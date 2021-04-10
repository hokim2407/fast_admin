package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enums.UserStatus;
import com.example.study.model.repository.UserRepository;
import com.example.study.network.Header;
import com.example.study.network.request.UserApiRequest;
import com.example.study.network.response.UserApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiData = request.getData();
        User user = User.builder()
                .account(userApiData.getAccount())
                .password(userApiData.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiData.getPhoneNumber())
                .email(userApiData.getEmail())
                .registeredAt(userApiData.getRegisteredAt())
                .build();
        User newUser = userRepository.save(user);
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id)
                .map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("NO DATA"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiData = request.getData();
        Optional<User> user = userRepository.findById(userApiData.getId());
        return user
                .map(data -> data
                        .setAccount(userApiData.getAccount())
                        .setPassword(userApiData.getPassword())
                        .setStatus(userApiData.getStatus())
                        .setEmail(userApiData.getEmail())
                        .setPhoneNumber(userApiData.getPhoneNumber())
                        .setRegisteredAt(userApiData.getRegisteredAt())
                        .setUnregisteredAt(userApiData.getUnregisteredAt())

                )
                .map(data -> userRepository.save(data))
                .map(data -> Header.OK(response(data)))
                .orElseGet(() -> Header.ERROR("NO DATA"));

    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.map(data -> {
            userRepository.delete(data);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("NO DATA"));

    }


    public UserApiResponse response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //TODO :secret
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
        return userApiResponse;
    }


}
