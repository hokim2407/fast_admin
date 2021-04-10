package com.example.study.network.request;

import com.example.study.model.enums.OrderType;
import com.example.study.network.response.ItemApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGroupApiRequest {
    private Long id;
    private String status;
    private OrderType orderType;
    private String revAddress;
    private String revName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    private Long userId;

    private List<ItemApiResponse> itemList;

}
