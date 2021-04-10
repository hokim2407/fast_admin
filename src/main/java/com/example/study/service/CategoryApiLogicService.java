package com.example.study.service;

import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;
import com.example.study.model.entity.User;
import com.example.study.network.Header;
import com.example.study.network.request.CategoryApiRequest;
import com.example.study.network.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {
    @Autowired
    PartnerApiLogicService partnerApiLogicService;
    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest apiData = request.getData();
        Category category = Category.builder()
                .title(apiData.getTitle())
                .type(apiData.getTitle())
                .build();
        Category newCategory = baseRepository.save(category);
        return Header.OK(response(newCategory));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest apiData = request.getData();
        Optional<Category> optional = baseRepository.findById(apiData.getId());
        optional.map(data -> data
                    .setTitle(apiData.getTitle())
                    .setType(apiData.getType())
                )
                .map(data -> baseRepository.save(data))
                .map(data -> Header.OK(response(data)))
                .orElseGet(() -> Header.ERROR("NO DATA"));
        return null;
    }

    public Header<PartnerInfoApiResponse> orderInfo(Long id) {
        //category
        Category category = baseRepository.getOne(id);
        CategoryApiResponse categoryApiResponse = response(category);

        List<Partner> partnerList = category.getPartnerList();
        List<PartnerApiResponse> partnerApiResponses = partnerList.stream()
                .map(partner -> {return partnerApiLogicService.response(partner);})
                .collect(Collectors.toList());
        categoryApiResponse.setPartnerList(partnerApiResponses);
        PartnerInfoApiResponse partnerInfoApiResponse = PartnerInfoApiResponse
                .builder()
                .categoryApiResponse(categoryApiResponse)
                .build();
        return Header.OK(partnerInfoApiResponse);
    }

    @Override
    public CategoryApiResponse response(Category data) {
        CategoryApiResponse apiData = CategoryApiResponse.builder()
                .id(data.getId())
                .title(data.getTitle())
                .type(data.getType())
                .build();
        return apiData;
    }
}
