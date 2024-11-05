drop database if exists robot_member;
create database robot_member charset utf8mb4;
use robot_member;

DROP TABLE IF EXISTS `member_info`;
CREATE TABLE `member_info`
(
    `id`               char(20)       NOT NULL DEFAULT '' COMMENT '会员id',
    `nickname`         varchar(50)    NOT NULL DEFAULT '' COMMENT '昵称-默认取微信昵称',
    `phone`            varchar(15)    NOT NULL DEFAULT '' COMMENT '电话',
    `email`            varchar(15)    NOT NULL DEFAULT '' COMMENT '邮箱',
    `avatar`           varchar(255)   NOT NULL DEFAULT 'avatar.png' COMMENT '头像-默认微信头像',
    `is_plus`          char(1)        NOT NULL DEFAULT '0' COMMENT '是否plus会员 0-游客/注册用户,1-付了一块钱->会员,2-付了198->plus会员',
    `plus_start`       datetime       NOT NULL DEFAULT '' comment 'plus会员开始时间',
    `plus_end`         datetime       NOT NULL DEFAULT '' comment 'plus会员结束时间',
    `gender`           char(1)        NOT NULL DEFAULT '2' COMMENT '性别 0-女 1-男 2-未知',
    `id_card`          char(20)       NOT NULL DEFAULT '' COMMENT '身份证号码',
    `real_name`        varchar(10)    NOT NULL DEFAULT '' COMMENT '真实姓名',
    `real_name_status` char(1)        NOT NULL DEFAULT '0' COMMENT '实名制状态 0-未实名 1-已实名 2-实名失败',
    `birthday`         date           NOT NULL DEFAULT '' COMMENT '生日',
    `last_login_ip`    char(20)       NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `last_login_time`  datetime       NULL     DEFAULT NULL COMMENT '最后登录时间',
    `beans`            int(11)        NOT NULL DEFAULT 0 COMMENT '码豆',
    `total_beans`      int(11)        NOT NULL DEFAULT 0 comment '累计持有码豆',
    `total_orders`     int(11)        NOT NULL DEFAULT 0 comment '累计下单并支付成功的订单数',
    `total_save`       decimal(10, 2) NOT NULL DEFAULT '' COMMENT '总共节省',
    `recommend_id`     char(20)       NOT NULL DEFAULT '' COMMENT '推荐人id',
    `recommend_name`   char(20)       NOT NULL DEFAULT '' COMMENT '推荐人名称',
    `year`             char(4)        NOT NULL DEFAULT '' COMMENT '注册日的年',
    `month`            char(2)        NOT NULL DEFAULT '' COMMENT '注册日的月',
    `date`             char(2)        NOT NULL DEFAULT '' COMMENT '注册日的日期',
    `day`              char(2)        NOT NULL DEFAULT '' COMMENT '注册日的星期',
    `hours`            char(2)        NOT NULL DEFAULT '' comment '注册时间的小时数',
    `come_form`        varchar(255)   NOT NULL DEFAULT '' COMMENT '来源',
    `status`           char(1)        NOT NULL DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
    `created_at`       datetime                DEFAULT '' COMMENT '创建时间',
    `updated_at`       datetime                DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`       int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员表';




