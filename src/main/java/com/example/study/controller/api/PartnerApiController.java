package com.example.study.controller.api;


import com.example.study.controller.CrudController;
import com.example.study.model.entity.Partner;
import com.example.study.network.request.PartnerApiRequest;
import com.example.study.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {
}
