package com.example.study.controller.api;


import com.example.study.ifs.CrudInterface;
import com.example.study.network.Header;
import com.example.study.network.request.OrderGroupApiRequest;
import com.example.study.network.response.OrderGroupApiResponse;
import com.example.study.service.OrderGroupApiLogicService;
import com.example.study.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
    @Autowired
    OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @GetMapping("")
    public Header<OrderGroupApiResponse> delete(@PathVariable Long id) {
        return orderGroupApiLogicService.delete(id);
    }
}
