package com.wuyunbin.merchant.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Data
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺id
     */
    private String id;

    /**
     * 申请单id
     */
    private String storeApplyId;

    /**
     * 店铺名字
     */
    private String storeName;

    /**
     * 商户id
     */
    private String merchantId;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 营业执照号码
     */
    private String businessLicense;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 行业id
     */
    private String industryId;

    /**
     * 法人身份证号码
     */
    private String idCard;

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
     * 封面图,门头照
     */
    private String coverImage;

    /**
     * 店内照
     */
    private String images;

    /**
     * 营业开始时间 示例：08:30:00
     */
    private Time businessHoursStart;

    /**
     * 营业结束时间 示例：23:30:00
     */
    private Time businessHoursEnd;

    /**
     * 评分 店铺所有订单的评分的均值
     */
    private Double score;

    /**
     * 店铺推荐语
     */
    private String recommendation;

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
     * 0-禁用 1-启用
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

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
