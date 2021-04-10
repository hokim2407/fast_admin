package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enums.UserStatus;
import com.example.study.model.repository.UserRepository;
import com.example.study.network.Header;
import com.example.study.network.request.UserApiRequest;
import com.example.study.network.response.ItemApiResponse;
import com.example.study.network.response.OrderGroupApiResponse;
import com.example.study.network.response.UserApiResponse;
import com.example.study.network.response.UserOrderInfoApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse,User> {


    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;
    @Autowired
    private ItemApiLogicService itemApiLogicService;

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
        User newUser = baseRepository.save(user);
        return Header.OK(response(newUser));
    }


    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiData = request.getData();
        Optional<User> user = baseRepository.findById(userApiData.getId());
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
                .map(data -> baseRepository.save(data))
                .map(data -> Header.OK(response(data)))
                .orElseGet(() -> Header.ERROR("NO DATA"));

    }



    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        //user
        User user = baseRepository.getOne(id);
        UserApiResponse userApi = response(user);

        //orderGroup
        List<OrderGroupApiResponse> orderGroupApiDataList = user.getOrderGroupList().stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiData = orderGroupApiLogicService.response(orderGroup);

                    //item
                    List<ItemApiResponse> itemApiDataList = orderGroup.getOrderDetailList().stream()
                            .map(orderDetail -> orderDetail.getItem())
                            .map(item -> itemApiLogicService.response(item))
                            .collect(Collectors.toList());
                    orderGroupApiData.setItemList(itemApiDataList);
                    System.out.println(orderGroupApiData.getItemList());
                    return orderGroupApiData;
                })
                .collect(Collectors.toList());
        userApi.setOrderGroupList(orderGroupApiDataList);
        UserOrderInfoApiResponse shoppingInfoApiData = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApi)
                .build();

        return Header.OK(shoppingInfoApiData);
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
