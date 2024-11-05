drop database if exists merchant;
create database merchant charset utf8mb4;
use merchant;

DROP TABLE IF EXISTS `merchant_info`;
CREATE TABLE merchant_info
(
    `id`               char(20)       NOT NULL DEFAULT '' COMMENT '商家入驻申请表id',
    `member_id`        char(20)       NOT NULL DEFAULT '' COMMENT '会员id',
    `merchant_id`      char(20)       NOT NULL DEFAULT '' COMMENT '公司id',
    `contract`         varchar(20)    NOT NULL DEFAULT '' COMMENT '联系人',
    `id_card`          char(20)       NOT NULL DEFAULT '' COMMENT '身份证号码',
    `phone`            varchar(18)    NOT NULL DEFAULT '' COMMENT '联系电话',
    `email`            varchar(255)   NOT NULL DEFAULT '' COMMENT '邮箱',
    `province`         varchar(10)    NOT NULL DEFAULT '' COMMENT '省份',
    `province_id`      char(6)        NOT NULL DEFAULT '' COMMENT '省份id',
    `city`             varchar(10)    NOT NULL DEFAULT '' COMMENT '城市',
    `city_id`          char(6)        NOT NULL DEFAULT '' COMMENT '城市id',
    `area`             varchar(10)    NOT NULL DEFAULT '' COMMENT '区县',
    `area_id`          char(6)        NOT NULL DEFAULT '' COMMENT '区县id',
    `address`          varchar(50)    NOT NULL DEFAULT '' COMMENT '详细地址',
    `longitude`        char(20)       NOT NULL DEFAULT '' COMMENT '经度',
    `latitude`         char(20)       NOT NULL DEFAULT '' COMMENT '纬度',
    `business_license` varchar(25)    NOT NULL DEFAULT '' COMMENT '营业执照号码',
    `cover_image`      varchar(255)   NOT NULL DEFAULT '' COMMENT '封面图,门头照',
    `images`           varchar(255)   NOT NULL DEFAULT '' COMMENT '店内照 json',
    `recommend_id`     char(20)       NOT NULL DEFAULT '' COMMENT '推荐人id',
    `rate`             varchar(35)    NOT NULL DEFAULT '' COMMENT 'json vip1=会员折扣率 vip2=plus会员折扣率',
    `credit`           decimal(10, 2) NOT NULL DEFAULT 0 comment '授信额度',
    `bank_name`        varchar(20)    NOT NULL DEFAULT '' COMMENT '开户行名称',
    `bank_account`     varchar(25)    NOT NULL DEFAULT '' COMMENT '银行账号',
    `account_name`     varchar(20)    NOT NULL DEFAULT '' COMMENT '户名',
    `account_type`     varchar(1)     NOT NULL DEFAULT '' COMMENT '账户类型 0-对私 1-对公',
    `status`           char(1)        NOT NULL DEFAULT '0' COMMENT '状态 0-初审待审核 1-初审通过 2-初审不通过 3-平台待审核 4-平台审核通过 5-平台审核失败',
    `remark`           varchar(255)   NOT NULL DEFAULT '' COMMENT '备注',
    `recommendation`   varchar(255)   NOT NULL DEFAULT '' COMMENT '简介',
    `created_at`       datetime                DEFAULT '' COMMENT '创建时间',
    `updated_at`       datetime                DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`       int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    primary key (`id`)
) COMMENT '商户信息表';

DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`
(
    `id`                   char(20)       NOT NULL DEFAULT '' COMMENT '店铺id',
    `store_name`           varchar(50)    NOT NULL DEFAULT '' COMMENT '店铺名字',
    `merchant_id`          char(20)       NOT NULL DEFAULT '' COMMENT '商户id',
    `member_id`            char(20)       NOT NULL DEFAULT '' COMMENT '会员id',
    `contact`              varchar(20)    NOT NULL DEFAULT '' COMMENT '联系人',
    `phone`                varchar(15)    NOT NULL DEFAULT '' COMMENT '电话',
    `email`                varchar(255)   NOT NULL DEFAULT '' COMMENT '邮箱',
    `business_license`     varchar(25)    NOT NULL DEFAULT '' COMMENT '营业执照号码',
    `longitude`            char(20)       NOT NULL DEFAULT '' COMMENT '经度',
    `latitude`             char(20)       NOT NULL DEFAULT '' COMMENT '纬度',
    `industry_id`          char(20)       NOT NULL DEFAULT '' COMMENT '行业id',
    `id_card`              varchar(20)    NOT NULL DEFAULT '' COMMENT '法人身份证号码',
    `province`             varchar(10)    NOT NULL DEFAULT '' COMMENT '省份',
    `province_id`          char(6)        NOT NULL DEFAULT '' COMMENT '省份id',
    `city`                 varchar(10)    NOT NULL DEFAULT '' COMMENT '城市',
    `city_id`              char(6)        NOT NULL DEFAULT '' COMMENT '城市id',
    `area`                 varchar(10)    NOT NULL DEFAULT '' COMMENT '区县',
    `area_id`              char(6)        NOT NULL DEFAULT '' COMMENT '区县id',
    `address`              varchar(50)    NOT NULL DEFAULT '' COMMENT '详细地址',
    `cover_image`          varchar(255)   NOT NULL DEFAULT '' COMMENT '封面图,门头照',
    `images`               varchar(255)   NOT NULL DEFAULT '' COMMENT '店内照',
    `rate`                 varchar(35)    NOT NULL DEFAULT '' COMMENT 'json vip1=会员折扣率 vip2=会长折扣率',
    `business_hours_start` time           NOT NULL DEFAULT '' COMMENT '营业开始时间 示例：08:30:00',
    `business_hours_end`   time           NOT NULL DEFAULT '' COMMENT '营业结束时间 示例：23:30:00',
    `score`                double(4, 2)   NOT NULL DEFAULT '' COMMENT '评分 店铺所有订单的评分的均值',
    `recommendation`       char(100)      NOT NULL DEFAULT '' COMMENT '店铺推荐语',
    `stock`                decimal(10, 2) NOT NULL DEFAULT 0 comment '库存/代理商在这家店采购的额度的余额',
    `early_warning`        decimal(10, 2) NOT NULL DEFAULT 0 comment '预警值',
    `credit`               decimal(10, 2) NOT NULL DEFAULT 0 comment '授信额度',
    `bank_name`            varchar(20)    NOT NULL DEFAULT '' COMMENT '开户行名称',
    `bank_account`         varchar(25)    NOT NULL DEFAULT '' COMMENT '银行账号',
    `account_name`         varchar(20)    NOT NULL DEFAULT '' COMMENT '户名',
    `account_type`         varchar(1)     NOT NULL DEFAULT '' COMMENT '账户类型 0-对私 1-对公',
    `status`               char(1)        NOT NULL DEFAULT '0' COMMENT '0-禁用 1-启用',
    `remark`               varchar(255)   NOT NULL DEFAULT '' COMMENT '备注',
    `created_at`           datetime                DEFAULT '' COMMENT '创建时间',
    `updated_at`           datetime                DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`           int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='店铺表';

DROP TABLE IF EXISTS `store_tags`;
CREATE TABLE `store_tags`
(
    `store_id`   char(20) NOT NULL DEFAULT '' COMMENT '店铺id',
    `tag_id`     char(20) NOT NULL DEFAULT '' COMMENT '标签id',
    `created_at` datetime          DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime          DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)   NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`store_id`, `tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='店铺标签';

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`
(
    `id`         char(20) NOT NULL DEFAULT '' COMMENT '标签id',
    `content`    char(10) NOT NULL DEFAULT '' COMMENT '标签内容',
    `created_at` datetime          DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime          DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)   NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='标签表';