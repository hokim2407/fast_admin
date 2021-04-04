package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.repository.OrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();
        OrderDetail saved = orderDetailRepository.save(orderDetail);
        System.out.println(saved);
    }
    @Test
    public void read(@RequestParam Long id) {
        Optional<OrderDetail> orderDetail =  orderDetailRepository.findById(id);
        orderDetail.ifPresent(SelectedOrder -> { System.out.println(SelectedOrder);});

    }
    @Test
    public void update() {

        Optional <OrderDetail> orderDetail =  orderDetailRepository.findById(1L);
        orderDetail.ifPresent(SelectedOrder -> {
            orderDetailRepository.save(SelectedOrder);
        });
    }
    @Test
    @Transactional//롤백시켜줌
    public void delete() {
        Optional <OrderDetail> orderDetail =  orderDetailRepository.findById(1L);

        orderDetail.ifPresent(SelectedOrder -> {
            orderDetailRepository.delete(SelectedOrder);

        });
    }
}
