package com.wuyunbin.merchant.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

@Data
public class StorePageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 店铺id
     */
    private String id;

    /**
     * 店铺名字
     */
    private String storeName;

    /**
     * 商户id
     */
    private String merchantId;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;


    /**
     * 封面图,门头照
     */
    private String coverImage;

    private Double distance;


    /**
     * 评分 店铺所有订单的评分的均值
     */
    private Double score;

}
