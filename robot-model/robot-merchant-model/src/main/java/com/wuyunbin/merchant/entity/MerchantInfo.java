package com.wuyunbin.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * <p>
 * 商户信息表
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Data
@TableName("merchant_info")
public class MerchantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家id
     */
    private String id;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 申请单id
     */
    private String merchantApplyId;

    /**
     * 联系人
     */
    private String contract;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String area;


    /**
     * 详细地址
     */
    private String address;

    /**
     * 推荐人id
     */
    private String recommendId;

    /**
     * 状态 0-正常 1-禁用
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 简介
     */
    private String recommendation;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 最后更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    /**
     * 删除时间
     */
    private Integer deletedAt;
}
