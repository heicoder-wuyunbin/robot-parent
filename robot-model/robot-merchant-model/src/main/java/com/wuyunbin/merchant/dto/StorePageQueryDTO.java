package com.wuyunbin.merchant.dto;

import lombok.Data;

@Data
public class StorePageQueryDTO {
    private int current;
    private int pageSize;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;

    private Double distance;
}
