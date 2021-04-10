package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.repository.OrderGroupRepository;
import com.example.study.model.repository.UserRepository;
import com.example.study.network.Header;
import com.example.study.network.request.OrderGroupApiRequest;
import com.example.study.network.response.OrderGroupApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse,OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest apiData = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .arrivalDate(apiData.getArrivalDate())
                .orderAt(apiData.getOrderAt())
                .orderType(apiData.getOrderType())
                .paymentType(apiData.getPaymentType())
                .revAddress(apiData.getRevAddress())
                .revName(apiData.getRevName())
                .status(apiData.getStatus())
                .totalPrice(apiData.getTotalPrice())
                .totalQuantity(apiData.getTotalQuantity())
                .user(userRepository.getOne(apiData.getUserId()))
                .build();
        OrderGroup newOrderGroup = baseRepository.save(orderGroup);
        return Header.OK(response(newOrderGroup));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest apiData = request.getData();
        Optional<OrderGroup> optional = baseRepository.findById(apiData.getId());
        return optional.map(data -> data
                    .setArrivalDate(apiData.getArrivalDate())
                    .setOrderAt(apiData.getOrderAt())
                    .setOrderType(apiData.getOrderType())
                    .setPaymentType(apiData.getPaymentType())
                    .setRevAddress(apiData.getRevAddress())
                    .setRevName(apiData.getRevName())
                    .setStatus(apiData.getStatus())
                    .setTotalPrice(apiData.getTotalPrice())
                    .setTotalQuantity(apiData.getTotalQuantity())
                    .setUser(userRepository.getOne(apiData.getUserId()))
                )
                .map(data -> baseRepository.save(data))
                .map(data -> Header.OK(response(data)))
                .orElseGet(() -> Header.ERROR("NO DATA"))
                ;
    }


    public OrderGroupApiResponse response(OrderGroup data) {
        OrderGroupApiResponse apiData = OrderGroupApiResponse.builder()
                .id(data.getId())
                .arrivalDate(data.getArrivalDate())
                .orderAt(data.getOrderAt())
                .orderType(data.getOrderType())
                .paymentType(data.getPaymentType())
                .revAddress(data.getRevAddress())
                .revName(data.getRevName())
                .status(data.getStatus())
                .totalPrice(data.getTotalPrice())
                .totalQuantity(data.getTotalQuantity())
                .userId(data.getUser().getId())
                .build();
        return apiData;
    }


}
