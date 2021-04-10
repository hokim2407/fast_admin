package com.example.study.network.response;


import com.example.study.network.request.UserApiRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderInfoApiResponse {
    private UserApiResponse userApiResponse;

}
