package com.example.study.controller.api;


import com.example.study.controller.CrudController;
import com.example.study.model.entity.Category;
import com.example.study.model.entity.User;
import com.example.study.model.repository.UserRepository;
import com.example.study.network.Header;
import com.example.study.network.request.CategoryApiRequest;
import com.example.study.network.request.UserApiRequest;
import com.example.study.network.response.CategoryApiResponse;
import com.example.study.network.response.PartnerInfoApiResponse;
import com.example.study.network.response.SettlementResponse;
import com.example.study.network.response.UserApiResponse;
import com.example.study.service.CategoryApiLogicService;
import com.example.study.service.SettlementLogicService;
import com.example.study.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
DROP TABLE IF EXISTS `settlement`;

CREATE TABLE `settlement` (
  `user_id` bigint(20) NOT NULL  AUTO_INCREMENT,
  `price` decimal(12,4) NOT NULL,
 PRIMARY KEY (`user_id`)
)
* */

@RestController
@RequestMapping("/api/settlement")
public class SettlementApiController{

    @Autowired
    private SettlementLogicService settlementLogicService;

    @GetMapping("/{id}")
    public Header<SettlementResponse> SettlementInfo(@PathVariable Long id){
        System.out.println(id);
        return settlementLogicService.read(id);
    }

}
