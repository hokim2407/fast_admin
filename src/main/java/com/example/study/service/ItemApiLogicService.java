package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.repository.ItemRepository;
import com.example.study.network.Header;
import com.example.study.network.request.ItemApiRequest;
import com.example.study.network.response.request.ItemApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request)  {

        ItemApiRequest apiData = request.getData();
        Item item = Item.builder()
                .name(apiData.getName())
                .title(apiData.getTitle())
                .price(apiData.getPrice())
                .brandName(apiData.getBrandName())
                .status(apiData.getStatus())
                .content(apiData.getContent())
                .registeredAt(apiData.getRegisteredAt())
                .build();
        Item newItem = itemRepository.save(item);
        return Header.OK(response(newItem));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        System.out.println(id);
        return itemRepository.findById(id)
                .map(item -> Header.OK(response(item)))
                .orElseGet(() -> Header.ERROR("NO DATA"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest apiData = request.getData();
        Optional<Item> optional = itemRepository.findById(apiData.getId());
        return optional.map(item ->
                item.setName(apiData.getName())
                        .setTitle(apiData.getTitle())
                        .setPrice(apiData.getPrice())
                        .setBrandName(apiData.getBrandName())
                        .setStatus(apiData.getStatus())
                        .setContent(apiData.getContent())
                        .setRegisteredAt(apiData.getRegisteredAt())
                        .setUnregisteredAt(apiData.getUnregisteredAt())
                )
                .map(data -> itemRepository.save(data))
                .map(data -> Header.OK(response(data)))
                .orElseGet(() -> Header.ERROR("NO DATA"));

    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        return optional.map(data -> {
            itemRepository.delete(data);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("NO DATA"));

    }

    public ItemApiResponse response(Item apiData) {

        ItemApiResponse data = new ItemApiResponse().builder()
                .id(apiData.getId())
                .name(apiData.getName())
                .title(apiData.getTitle())
                .price(apiData.getPrice())
                .brandName(apiData.getBrandName())
                .status(apiData.getStatus())
                .content(apiData.getContent())
                .registeredAt(apiData.getRegisteredAt())
                .unregisteredAt(apiData.getUnregisteredAt())
             //   .partnerId(apiData.getPartner().getId())
                .build();
        return data;
    }







}
