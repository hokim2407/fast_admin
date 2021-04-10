package com.example.study.service;


import com.example.study.model.entity.OrderDetail;
import com.example.study.model.repository.ItemRepository;
import com.example.study.model.repository.OrderGroupRepository;
import com.example.study.network.Header;
import com.example.study.network.request.OrderDetailApiRequest;
import com.example.study.network.response.OrderDetailApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest apiData = request.getData();
        OrderDetail item = OrderDetail.builder()
                .arrivalDate(apiData.getArrivalDate())
                .quantity(apiData.getQuantity())
                .status(apiData.getStatus())
                .totalPrice(apiData.getTotalPrice())
                .item(itemRepository.getOne(apiData.getItemId()))
                .orderGroup(orderGroupRepository.getOne(apiData.getOrderGroupId()))
                .build();
        OrderDetail newItem = baseRepository.save(item);
        return Header.OK(response(newItem));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest apiData = request.getData();
        Optional<OrderDetail> optional = baseRepository.findById(apiData.getId());
        return optional.map(order -> order
                .setQuantity(apiData.getQuantity())
                .setArrivalDate(apiData.getArrivalDate())
                .setStatus(apiData.getStatus())
                .setTotalPrice(apiData.getTotalPrice())
                .setOrderGroup(orderGroupRepository.getOne(apiData.getOrderGroupId()))
                .setItem(itemRepository.getOne(apiData.getItemId()))

        )
                .map(data -> baseRepository.save(data))
                .map(data -> Header.OK(response(data)))
                .orElseGet(() -> Header.ERROR("NO DATA"));

    }

    @Override
    public OrderDetailApiResponse response(OrderDetail apiData) {

        OrderDetailApiResponse data = new OrderDetailApiResponse().builder()
                .id(apiData.getId())
                .arrivalDate(apiData.getArrivalDate())
                .quantity(apiData.getQuantity())
                .status(apiData.getStatus())
                .totalPrice(apiData.getTotalPrice())
                .itemId(apiData.getItem().getId())
                .orderGroupId(apiData.getOrderGroup().getId())
                .build();

        return data;
    }

}
