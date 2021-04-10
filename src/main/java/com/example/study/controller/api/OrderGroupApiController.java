package com.example.study.controller.api;


import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterface;
import com.example.study.network.Header;
import com.example.study.network.request.OrderGroupApiRequest;
import com.example.study.network.response.OrderGroupApiResponse;
import com.example.study.service.ItemApiLogicService;
import com.example.study.service.OrderGroupApiLogicService;
import com.example.study.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse> {
    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;
    @PostConstruct
    public void init(){
        this.baseService = orderGroupApiLogicService;
    }

}
