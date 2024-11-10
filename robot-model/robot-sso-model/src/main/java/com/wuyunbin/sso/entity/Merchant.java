package com.wuyunbin.sso.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>
 * 商户表
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Data
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    private String id;

    /**
     * 电话
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 微信open_id
     */
    private String openId;

    /**
     * 微信union_id
     */
    private String unionId;

    /**
     * 状态 0-禁用 1-启用
     */
    private String status;

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
