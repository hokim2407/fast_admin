package com.example.study.controller.api;


import com.example.study.controller.CrudController;
import com.example.study.model.entity.Category;
import com.example.study.network.Header;
import com.example.study.network.request.CategoryApiRequest;
import com.example.study.network.response.CategoryApiResponse;
import com.example.study.network.response.PartnerInfoApiResponse;
import com.example.study.network.response.UserOrderInfoApiResponse;
import com.example.study.service.CategoryApiLogicService;
import com.example.study.service.UserApiLogicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {
    @GetMapping("/{id}/partnerInfo")
    public Header<PartnerInfoApiResponse> shoppingInfo(@PathVariable Long id){
        return ((CategoryApiLogicService)baseService).orderInfo(id);
    }

}
