package com.wuyunbin.apply.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * <p>
 * 店铺入驻申请表
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Data
@TableName("store_apply")
public class StoreApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家入驻申请表id
     */
    private String id;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 联系人手机
     */
    private String contract;

    /**
     * 店铺名
     */

    private String storeName;

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
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 营业执照号码
     */
    private String businessLicense;

    /**
     * 封面图,门头照
     */
    private String coverImage;

    /**
     * 店内照 json
     */
    private String images;

    /**
     * 开户行名称
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 户名
     */
    private String accountName;

    /**
     * 账户类型 0-对私 1-对公
     */
    private String accountType;

    /**
     * 状态 0-待审核 1-通过 2-不通过
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
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;

    /**
     * 删除时间
     */
    private Integer deletedAt;
}
