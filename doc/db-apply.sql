drop database if exists robot_apply;
create database robot_apply charset utf8mb4;
use robot_apply;

DROP TABLE IF EXISTS `merchant_apply`;
CREATE TABLE `merchant_apply`
(
    `id`             char(20)     NOT NULL DEFAULT '' COMMENT '商家入驻申请表id',
    `member_id`      char(20)     NOT NULL DEFAULT '' COMMENT '会员id',
    `contract`       varchar(20)  NOT NULL DEFAULT '' COMMENT '联系人手机',
    `id_card`        char(20)     NOT NULL DEFAULT '' COMMENT '身份证号码',
    `phone`          varchar(18)  NOT NULL DEFAULT '' COMMENT '联系电话',
    `email`          varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `province`       varchar(10)  NOT NULL DEFAULT '' COMMENT '省份',
    `province_id`    char(6)      NOT NULL DEFAULT '' COMMENT '省份id',
    `city`           varchar(10)  NOT NULL DEFAULT '' COMMENT '城市',
    `city_id`        char(6)      NOT NULL DEFAULT '' COMMENT '城市id',
    `area`           varchar(10)  NOT NULL DEFAULT '' COMMENT '区县',
    `area_id`        char(6)      NOT NULL DEFAULT '' COMMENT '区县id',
    `address`        varchar(50)  NOT NULL DEFAULT '' COMMENT '详细地址',
    `recommend_id`   char(20)     NOT NULL DEFAULT '' COMMENT '推荐人id',
    `status`         char(1)      NOT NULL DEFAULT '0' COMMENT '状态 0-审待审核 1-通过 2-不通过',
    `remark`         varchar(255) NOT NULL DEFAULT '' COMMENT '备注-不通过原因写在这里',
    `recommendation` varchar(255) NOT NULL DEFAULT '' COMMENT '简介',
    `created_at`     datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `updated_at`     datetime              DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`     int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商家入驻申请表';


DROP TABLE IF EXISTS `store_apply`;
CREATE TABLE `store_apply`
(
    `id`               char(20)     NOT NULL DEFAULT '' COMMENT '商家入驻申请表id',
    `merchant_id`      char(20)     NOT NULL DEFAULT '' COMMENT '商家id',
    `contract`         varchar(20)  NOT NULL DEFAULT '' COMMENT '联系人手机',
    `id_card`          char(20)     NOT NULL DEFAULT '' COMMENT '身份证号码',
    `phone`            varchar(18)  NOT NULL DEFAULT '' COMMENT '联系电话',
    `email`            varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `province`         varchar(10)  NOT NULL DEFAULT '' COMMENT '省份',
    `province_id`      char(6)      NOT NULL DEFAULT '' COMMENT '省份id',
    `city`             varchar(10)  NOT NULL DEFAULT '' COMMENT '城市',
    `city_id`          char(6)      NOT NULL DEFAULT '' COMMENT '城市id',
    `area`             varchar(10)  NOT NULL DEFAULT '' COMMENT '区县',
    `area_id`          char(6)      NOT NULL DEFAULT '' COMMENT '区县id',
    `address`          varchar(50)  NOT NULL DEFAULT '' COMMENT '详细地址',
    `longitude`        char(20)     NOT NULL DEFAULT '' COMMENT '经度',
    `latitude`         char(20)     NOT NULL DEFAULT '' COMMENT '纬度',
    `business_license` varchar(25)  NOT NULL DEFAULT '' COMMENT '营业执照号码',
    `cover_image`      varchar(255) NOT NULL DEFAULT '' COMMENT '封面图,门头照',
    `images`           varchar(255) NOT NULL DEFAULT '' COMMENT '店内照 json',
    `bank_name`        varchar(20)  NOT NULL DEFAULT '' COMMENT '开户行名称',
    `bank_account`     varchar(25)  NOT NULL DEFAULT '' COMMENT '银行账号',
    `account_name`     varchar(20)  NOT NULL DEFAULT '' COMMENT '户名',
    `account_type`     varchar(1)   NOT NULL DEFAULT '' COMMENT '账户类型 0-对私 1-对公',
    `status`           char(1)      NOT NULL DEFAULT '0' COMMENT '状态 0-待审核 1-通过 2-不通过',
    `remark`           varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
    `recommendation`   varchar(255) NOT NULL DEFAULT '' COMMENT '简介',
    `created_at`     datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `updated_at`     datetime              DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`     int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='店铺入驻申请表';