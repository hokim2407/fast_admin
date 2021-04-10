package com.example.study.service;


import com.example.study.model.entity.Partner;
import com.example.study.model.repository.CategoryRepository;
import com.example.study.network.Header;
import com.example.study.network.request.PartnerApiRequest;
import com.example.study.network.response.PartnerApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest apiData = request.getData();
        Partner partner = Partner.builder()
                .address(apiData.getAddress())
                .businessNumber(apiData.getBusinessNumber())
                .partnerNumber(apiData.getPartnerNumber())
                .callCenter(apiData.getCallCenter())
                .ceoName(apiData.getCeoName())
                .status(apiData.getStatus())
                .category(categoryRepository.getOne(apiData.getCategoryId()))
                .name(apiData.getName())
                .registeredAt(apiData.getRegisteredAt())
                .unregisteredAt(apiData.getUnregisteredAt())
                .build();
        Partner newpartner = baseRepository.save(partner);
        return Header.OK(response(partner));
    }


    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest apiData = request.getData();
        Optional<Partner> optional = baseRepository.findById(apiData.getId());
        return optional
                .map(data -> data
                        .setAddress(apiData.getAddress())
                        .setBusinessNumber(apiData.getBusinessNumber())
                        .setPartnerNumber(apiData.getPartnerNumber())
                        .setCallCenter(apiData.getCallCenter())
                        .setCeoName(apiData.getCeoName())
                        .setStatus(apiData.getStatus())
                        .setCategory(categoryRepository.getOne(apiData.getCategoryId()))
                        .setName(apiData.getName())
                        .setRegisteredAt(apiData.getRegisteredAt())
                        .setUnregisteredAt(apiData.getUnregisteredAt())
                )
                .map(data -> baseRepository.save(data))
                .map(data -> Header.OK(response(data)))
                .orElseGet(() -> Header.ERROR("NO DATA"));

    }

    @Override
    public PartnerApiResponse response(Partner partner) {
        PartnerApiResponse apiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .categoryId(partner.getCategory().getId())
                .address(partner.getAddress())
                .businessNumber(partner.getBusinessNumber())
                .partnerNumber(partner.getPartnerNumber())
                .callCenter(partner.getCallCenter())
                .ceoName(partner.getCeoName())
                .status(partner.getStatus())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .build();
        return apiResponse;
    }
}

