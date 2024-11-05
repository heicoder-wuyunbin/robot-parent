drop database if exists robot_member;
create database robot_member charset utf8mb4;
use robot_member;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`
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

DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`
(
    `id`          char(20)     NOT NULL DEFAULT '' COMMENT '投诉id',
    `member_id`   char(20)     NOT NULL DEFAULT '' COMMENT '会员id',
    `merchant_id` char(20)     NOT NULL DEFAULT '' COMMENT '商家id',
    `store_id`    char(20)     NOT NULL DEFAULT '' COMMENT '店铺id',
    `store_name`  char(20)     NOT NULL DEFAULT '' COMMENT '店铺名字',
    `content`     varchar(255) NOT NULL DEFAULT '' COMMENT '投诉内容',
    `status`      char(1)      NOT NULL DEFAULT '0' COMMENT '状态 0-未处理 1-处理中,2-已处理',
    `created_at`  datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at`  datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`  int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='投诉';

DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`
(
    `id`          char(20)     NOT NULL DEFAULT '' COMMENT '评价表id',
    `member_id`   char(20)     NOT NULL DEFAULT '' COMMENT '会员id',
    `member_name` varchar(50)  NOT NULL DEFAULT '' COMMENT '会员名称',
    `merchant_id` char(20)     NOT NULL DEFAULT '' COMMENT '商家id',
    `store_id`    char(20)     NOT NULL DEFAULT '' COMMENT '店铺id',
    `store_name`  char(20)     NOT NULL DEFAULT '' COMMENT '店铺名称',
    `orders_id`   char(20)     NOT NULL DEFAULT '' COMMENT '订单id',
    `score`       int(1)       NOT NULL DEFAULT '5' COMMENT '得分 1-5分',
    `content`     varchar(200) NOT NULL DEFAULT '' comment '内容',
    `created_at`  datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at`  datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`  int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='评价表';

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`
(
    `id`         char(20)     NOT NULL DEFAULT '' COMMENT 'id',
    `member_id`  varchar(20)  NOT NULL DEFAULT '' COMMENT '会员id',
    `content`    varchar(255) NOT NULL DEFAULT '' COMMENT '反馈的内容',
    `phone`      varchar(18)  NOT NULL DEFAULT '' COMMENT '联系电话',
    `status`     char(1)      NOT NULL DEFAULT '0' COMMENT '处理状态 0-待处理 1-处理中 2-处理成功',
    `remark`     varchar(255) NOT NULL DEFAULT '' COMMENT '处理结果如电话沟通后已完美处理',
    `created_at` datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员反馈表';


DROP TABLE IF EXISTS `bean_logs`;
CREATE TABLE `bean_logs`
(
    `id`            char(20)     NOT NULL DEFAULT '' COMMENT 'id',
    `member_id`     varchar(20)  NOT NULL DEFAULT '' COMMENT '会员id',
    `member_name`   varchar(20)  NOT NULL DEFAULT '' COMMENT '会员名字',
    `store_id`      varchar(20)  NOT NULL DEFAULT '' COMMENT '店铺id',
    `merchant_id`   varchar(20)  NOT NULL DEFAULT '' COMMENT '商家id',
    `store_name`    varchar(20)  NOT NULL DEFAULT '' COMMENT '店铺名称',
    `area_id`       varchar(20)  NOT NULL DEFAULT '' COMMENT '区域id,用于查询代理商',
    `beans`         int(11)      NOT NULL DEFAULT '0' COMMENT '码豆数量',
    `type`          char(1)      NOT NULL DEFAULT '0' COMMENT '0-获得 1-消费',
    `remark`        varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
    `created_at`    datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at`    datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`    int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='码豆记录表';


