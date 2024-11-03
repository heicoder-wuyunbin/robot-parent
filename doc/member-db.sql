drop database if exists robot_member;
create database robot_member charset utf8mb4;
use robot_member;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`
(
    `id`               char(20) NOT NULL COMMENT '会员id',
    `nickname`         varchar(50)       DEFAULT NULL COMMENT '昵称-默认取微信昵称',
    `phone`            varchar(15)       DEFAULT NULL COMMENT '电话',
    `email`            varchar(15)       DEFAULT NULL COMMENT '邮箱',
    `avatar`           varchar(255)      DEFAULT 'avatar.png' COMMENT '头像-默认微信头像',
    `is_plus`          char(1)           DEFAULT '0' COMMENT '是否plus会员 0-游客/注册用户,1-付了一块钱->会员,2-付了198->plus会员',
    `plus_start`       datetime          default null comment 'plus会员开始时间',
    `plus_end`         datetime          default null comment 'plus会员结束时间',
    `gender`           char(1)           DEFAULT '2' COMMENT '性别 0-女 1-男 2-未知',
    `id_card`          char(20)          DEFAULT NULL COMMENT '身份证号码',
    `real_name`        varchar(10)       DEFAULT NULL COMMENT '真实姓名',
    `real_name_status` char(1)           DEFAULT '0' COMMENT '实名制状态 0-未实名 1-已实名 2-实名失败',
    `birthday`         date              DEFAULT NULL COMMENT '生日',
    `last_login_ip`    char(20)          DEFAULT NULL COMMENT '最后登录ip',
    `last_login_time`  datetime          DEFAULT NULL COMMENT '最后登录时间',
    `beans`            int(11)           DEFAULT 0 COMMENT '码豆',
    `total_beans`      int(11)           DEFAULT 0 comment '累计持有码豆',
    `total_orders`     int(11)           DEFAULT 0 comment '累计下单并支付成功的订单数',
    `total_save`       decimal(10, 2)    DEFAULT NULL COMMENT '总共节省',
    `recommend_id`     char(20)          DEFAULT NULL COMMENT '推荐人id',
    `recommend_name`   char(20)          DEFAULT NULL COMMENT '推荐人名称',
    `year`             char(4)           DEFAULT NULL COMMENT '注册日的年',
    `month`            char(2)           DEFAULT NULL COMMENT '注册日的月',
    `date`             char(2)           DEFAULT NULL COMMENT '注册日的日期',
    `day`              char(2)           DEFAULT NULL COMMENT '注册日的星期',
    `hours`            char(2)           default null comment '注册时间的小时数',
    `come_form`        varchar(255)      DEFAULT NULL COMMENT '来源',
    `status`           char(1)  NOT NULL DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
    `created_at`       datetime          DEFAULT NULL COMMENT '创建时间',
    `updated_at`       datetime          DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`       datetime          DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员表';

DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`
(
    `id`          char(20) NOT NULL COMMENT '投诉id',
    `member_id`   char(20)     DEFAULT NULL COMMENT '会员id',
    `merchant_id` char(20)     DEFAULT NULL COMMENT '商家id',
    `store_id`    char(20)     DEFAULT NULL COMMENT '店铺id',
    `store_name`  char(20)     DEFAULT NULL COMMENT '店铺名字',
    `content`     varchar(255) DEFAULT NULL COMMENT '投诉内容',
    `status`      char(1)      DEFAULT '0' COMMENT '状态 0-未处理 1-处理中,2-已处理',
    `created_at`  datetime     DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime     DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`  datetime     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='投诉';

DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`
(
    `id`          char(20) NOT NULL COMMENT '评价表id',
    `member_id`   char(20)          DEFAULT NULL COMMENT '会员id',
    `member_name` varchar(50)       DEFAULT NULL COMMENT '会员名称',
    `merchant_id` char(20)          DEFAULT NULL COMMENT '商家id',
    `store_id`    char(20)          DEFAULT NULL COMMENT '店铺id',
    `store_name`  char(20)          DEFAULT NULL COMMENT '店铺名称',
    `orders_id`   char(20)          DEFAULT NULL COMMENT '订单id',
    `score`       int(1)   NOT NULL DEFAULT '5' COMMENT '得分 1-5分',
    `content`     varchar(200)      default '' comment '内容',
    `created_at`  datetime          DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime          DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`  datetime          DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='评价表';

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`
(
    `id`         char(20)     NOT NULL COMMENT 'id',
    `member_id`  varchar(20)  NOT NULL COMMENT '会员id',
    `content`    varchar(255) NOT NULL COMMENT '反馈的内容',
    `phone`      varchar(18) COMMENT '联系电话',
    `status`     char(1)      DEFAULT '0' COMMENT '处理状态 0-待处理 1-处理中 2-处理成功',
    `remark`     varchar(255) DEFAULT NULL COMMENT '处理结果如电话沟通后已完美处理',
    `created_at` datetime     DEFAULT NULL COMMENT '创建时间',
    `updated_at` datetime     DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at` datetime     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员反馈表';


DROP TABLE IF EXISTS `consumption`;
CREATE TABLE `consumption`
(
    `id`            char(20)    NOT NULL COMMENT 'id',
    `member_id`     varchar(20) NOT NULL COMMENT '会员id',
    `member_name`   varchar(20) NOT NULL COMMENT '会员名字',
    `merchant_id`   varchar(20) COMMENT '店铺/商家id',
    `store_name`    varchar(20) COMMENT '店铺/商家名称',
    `area_id`       varchar(20) COMMENT '区域id,用于查询代理商',
    `beans`         int(11)      DEFAULT '0' COMMENT '码豆数量',
    `member_points` int(11)      DEFAULT '0' COMMENT '会员积分数量',
    `type`          char(1)      DEFAULT '0' COMMENT '0-获得 1-消费',
    `remark`        varchar(255) DEFAULT NULL COMMENT '备注',
    `created_at`    datetime     DEFAULT NULL COMMENT '创建时间',
    `updated_at`    datetime     DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`    datetime     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='码豆记录表';

DROP TABLE IF EXISTS `merchant_apply`;
CREATE TABLE `merchant_apply`
(
    `id`               char(20)    NOT NULL COMMENT '商家入驻申请表id',
    `member_id`        char(20)    NOT NULL COMMENT '会员id',
    `is_chain`         char(1)     NOT NULL DEFAULT '0' COMMENT '是否连锁店 0-否,1-是',
    `merchant_id`      char(20)    NOT NULL COMMENT '公司id',
    `store_id`         char(20)    NOT NULL COMMENT '店铺/商家id',
    `store_name`       varchar(20) NOT NULL COMMENT '店铺/商家名字',
    `contract`         varchar(20) NOT NULL COMMENT '联系人',
    `id_card`          char(20)             DEFAULT NULL COMMENT '身份证号码',
    `phone`            varchar(18)          DEFAULT '' COMMENT '联系电话',
    `email`            varchar(255)         DEFAULT NULL COMMENT '邮箱',
    `industry_id`      varchar(20)          DEFAULT NULL COMMENT '行业id',
    `industry_name`    varchar(20)          DEFAULT NULL COMMENT '行业名称',
    `industry_license` varchar(255)         DEFAULT NULL COMMENT '行业许可证编号',
    `province`         varchar(10)          DEFAULT NULL COMMENT '省份',
    `province_id`      char(6)              DEFAULT NULL COMMENT '省份id',
    `city`             varchar(10)          DEFAULT NULL COMMENT '城市',
    `city_id`          char(6)              DEFAULT NULL COMMENT '城市id',
    `area`             varchar(10)          DEFAULT NULL COMMENT '区县',
    `area_id`          char(6)              DEFAULT NULL COMMENT '区县id',
    `address`          varchar(50)          DEFAULT NULL COMMENT '详细地址',
    `longitude`        char(20)             DEFAULT NULL COMMENT '经度',
    `latitude`         char(20)             DEFAULT NULL COMMENT '纬度',
    `business_license` varchar(25)          DEFAULT NULL COMMENT '营业执照号码',
    `cover_image`      varchar(255)         DEFAULT NULL COMMENT '封面图,门头照',
    `images`           varchar(255)         DEFAULT NULL COMMENT '店内照 json',
    `recommend_id`     char(20)             DEFAULT NULL COMMENT '推荐人id',
    `rate`             varchar(35)          DEFAULT NULL COMMENT 'json vip1=会员折扣率 vip2=会长折扣率',
    `credit`           decimal(10, 2)       default 0 null comment '授信额度',
    `bank_name`        varchar(20) NULL COMMENT '开户行名称',
    `bank_account`     varchar(25) NULL COMMENT '银行账号',
    `account_name`     varchar(20) NULL COMMENT '户名',
    `account_type`     varchar(1)  NULL COMMENT '账户类型 0-对私 1-对公',
    `status`           char(1)     NOT NULL DEFAULT '0' COMMENT '状态 0-初审待审核 1-初审通过 2-初审不通过 3-平台待审核 4-平台审核通过 5-平台审核失败',
    `remark`           varchar(255)         DEFAULT NULL COMMENT '备注',
    `recommendation`   varchar(255)         DEFAULT NULL COMMENT '简介',
    `created_at`       datetime             DEFAULT NULL COMMENT '创建时间',
    `updated_at`       datetime             DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`       datetime             DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商家入驻申请表';


DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`
(
    `id`         char(20)      NOT NULL COMMENT 'id',
    `store_id`   char(20)      not null comment '店铺id',
    `store_name` char(20) DEFAULT NULL COMMENT '店铺名字',
    `member_id`  char(20)      not null comment '会员id',
    `rate`       char(50)      not null comment '折扣',
    `packing`    decimal(5, 2) not null comment '包装费',
    `freight`    decimal(5, 2) not null comment '配送费',
    `created_at` datetime DEFAULT NULL COMMENT '创建时间',
    `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='购物车';

DROP TABLE IF EXISTS `cart_details`;
CREATE TABLE `cart_details`
(
    `id`          char(20)     NOT NULL COMMENT 'id',
    `cart_id`     char(20)     not null comment '购物车id',
    `goods_id`    char(20)     not null comment '商品id',
    `goods_image` varchar(255) not null comment '商品图片',
    `goods_name`  char(20)     not null comment '商品名称',
    `goods_sum`   int(11)      not null comment '商品数量',
    `goods_price` decimal      not null comment '商品价格',
    `created_at`  datetime DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime DEFAULT NULL COMMENT '最后更新时间',
    `deleted_at`  datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='购物车详情';