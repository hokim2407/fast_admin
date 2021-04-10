package com.example.study.controller.api;


import com.example.study.controller.CrudController;
import com.example.study.model.entity.OrderGroup;
import com.example.study.network.request.OrderGroupApiRequest;
import com.example.study.network.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}
