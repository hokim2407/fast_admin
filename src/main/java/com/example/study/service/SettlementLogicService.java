package com.example.study.service;


import com.example.study.model.entity.Settlement;
import com.example.study.model.repository.SettlementRepository;
import com.example.study.network.Header;
import com.example.study.network.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SettlementLogicService {

    @Autowired
    private SettlementRepository settlementRepository;


    public Header<SettlementResponse> create() {
    //UserApiLogicService.create 내부에서만 동작 
        Settlement settlement = Settlement.builder()
                .price(new BigDecimal(0))
                .build();
        settlementRepository.save(settlement);
        return Header.OK(response(settlement)) ;
    }

    public Header<SettlementResponse> update(Long id, BigDecimal price) {
        //OrderGroupApiLogicService.create 내부에서만 동작
        Optional<Settlement> settlement = settlementRepository.findByUserId(id);
        return settlement.map(
                data-> data.setPrice(data.getPrice().add(price))
        ).map(data->settlementRepository.save(data))
                .map(data->Header.OK(response(data)))
                .orElseGet(()->Header.ERROR("NO DATA"));
    }

    public Header<SettlementResponse> read(Long id) {

        return settlementRepository.findByUserId(id)
                .map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("NO DATA"));
    }

    public Header delete(Long id) {
        //UserApiLogicService.delete 내부에서만 동작 
        Optional<Settlement> optional = settlementRepository.findByUserId(id);
        return optional.map(data -> {
            settlementRepository.delete(data);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("NO DATA"));

    }

    public SettlementResponse response(Settlement settlement)
    {
       SettlementResponse settlementResponse = SettlementResponse.builder()
               .userId(settlement.getUserId())
               .price(settlement.getPrice())
               .build();
        return settlementResponse;
    }
}