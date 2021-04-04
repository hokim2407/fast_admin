package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    REGISTERED(0, "등록", "사용자 등록 상태"),
    UNREGISTERED(1, "등록", "사용자 등록 상태");

    private Integer id;
    private String title;
    private String description;

}
