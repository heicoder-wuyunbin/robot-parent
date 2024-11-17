package com.wuyunbin.apply.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商家入驻申请表
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Data
@TableName("merchant_apply")
public class MerchantApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家入驻申请表id
     */
    private String id;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 联系人手机
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
     * 省份id
     */
    private String provinceId;

    /**
     * 城市
     */
    private String city;

    /**
     * 城市id
     */
    private String cityId;

    /**
     * 区县
     */
    private String area;

    /**
     * 区县id
     */
    private String areaId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 推荐人id
     */
    private String recommendId;

    /**
     * 状态 0-审待审核 1-通过 2-不通过
     */
    private String status;

    /**
     * 备注-不通过原因写在这里
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
