package com.example.study.service;

import com.example.study.model.entity.Category;
import com.example.study.network.Header;
import com.example.study.network.request.CategoryApiRequest;
import com.example.study.network.response.CategoryApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {
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
