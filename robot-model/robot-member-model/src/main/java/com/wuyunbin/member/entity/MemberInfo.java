package com.wuyunbin.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-05
 */
@Data
@TableName("member_info")
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    private String id;

    /**
     * 昵称-默认取微信昵称
     */
    private String nickname;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像-默认微信头像
     */
    private String avatar;

    /**
     * 是否plus会员 0-游客/注册用户,1-付了一块钱->会员,2-付了198->plus会员
     */
    private String isPlus;

    /**
     * plus会员开始时间
     */
    private Date plusStart;

    /**
     * plus会员结束时间
     */
    private Date plusEnd;

    /**
     * 性别 0-女 1-男 2-未知
     */
    private String gender;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 实名制状态 0-未实名 1-已实名 2-实名失败
     */
    private String realNameStatus;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 码豆
     */
    private Integer beans;

    /**
     * 累计持有码豆
     */
    private Integer totalBeans;

    /**
     * 累计下单并支付成功的订单数
     */
    private Integer totalOrders;

    /**
     * 总共节省
     */
    private BigDecimal totalSave;

    /**
     * 推荐人id
     */
    private String recommendId;

    /**
     * 推荐人名称
     */
    private String recommendName;

    /**
     * 注册日的年
     */
    private String year;

    /**
     * 注册日的月
     */
    private String month;

    /**
     * 注册日的日期
     */
    private String date;

    /**
     * 注册日的星期
     */
    private String day;

    /**
     * 注册时间的小时数
     */
    private String hours;

    /**
     * 来源
     */
    private String comeForm;

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
