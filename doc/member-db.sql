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
    `phone`      varchar(18) COMMENT '联系电话',
    `status`     char(1)      NOT NULL DEFAULT '0' COMMENT '处理状态 0-待处理 1-处理中 2-处理成功',
    `remark`     varchar(255) NOT NULL DEFAULT '' COMMENT '处理结果如电话沟通后已完美处理',
    `created_at` datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会员反馈表';


DROP TABLE IF EXISTS `consumption`;
CREATE TABLE `consumption`
(
    `id`            char(20)     NOT NULL DEFAULT '' COMMENT 'id',
    `member_id`     varchar(20)  NOT NULL DEFAULT '' COMMENT '会员id',
    `member_name`   varchar(20)  NOT NULL DEFAULT '' COMMENT '会员名字',
    `merchant_id`   varchar(20)  NOT NULL DEFAULT '' COMMENT '店铺/商家id',
    `store_name`    varchar(20)  NOT NULL DEFAULT '' COMMENT '店铺/商家名称',
    `area_id`       varchar(20)  NOT NULL DEFAULT '' COMMENT '区域id,用于查询代理商',
    `beans`         int(11)      NOT NULL DEFAULT '0' COMMENT '码豆数量',
    `member_points` int(11)      NOT NULL DEFAULT '0' COMMENT '会员积分数量',
    `type`          char(1)      NOT NULL DEFAULT '0' COMMENT '0-获得 1-消费',
    `remark`        varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
    `created_at`    datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at`    datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`    int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='码豆记录表';

DROP TABLE IF EXISTS `merchant_apply`;
CREATE TABLE `merchant_apply`
(
    `id`               char(20)       NOT NULL DEFAULT '' COMMENT '商家入驻申请表id',
    `member_id`        char(20)       NOT NULL DEFAULT '' COMMENT '会员id',
    `merchant_id`      char(20)       NOT NULL DEFAULT '' COMMENT '公司id',
    `contract`         varchar(20)    NOT NULL DEFAULT '' COMMENT '联系人',
    `id_card`          char(20)       NOT NULL DEFAULT '' COMMENT '身份证号码',
    `phone`            varchar(18)    NOT NULL DEFAULT '' COMMENT '联系电话',
    `email`            varchar(255)   NOT NULL DEFAULT '' COMMENT '邮箱',
    `industry_id`      varchar(20)    NOT NULL DEFAULT '' COMMENT '行业id',
    `industry_name`    varchar(20)    NOT NULL DEFAULT '' COMMENT '行业名称',
    `industry_license` varchar(255)   NOT NULL DEFAULT '' COMMENT '行业许可证编号',
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
    `rate`             varchar(35)    NOT NULL DEFAULT '' COMMENT 'json vip1=会员折扣率 vip2=会长折扣率',
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
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商家入驻申请表';

drop database if exists ad;
create database ad charset utf8mb4;
use ad;

DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad`
(
    `id`         char(20)     NOT NULL COMMENT '广告id',
    `title`      char(10)     NOT NULL DEFAULT '' COMMENT '广告标题',
    `type`       char(1)      NOT NULL DEFAULT '1' COMMENT '广告类型 0-图片 1-视频',
    `position`   char(1)      NOT NULL DEFAULT '' COMMENT '广告位置 0-首页广告 1-回调广告',
    `image_url`  varchar(255) NOT NULL DEFAULT '' COMMENT '广告图片本身url',
    `url`        varchar(255) NOT NULL DEFAULT '' COMMENT '广告指向url',
    `area_list`  varchar(255) NOT NULL DEFAULT '' COMMENT '地区,为空时表示全国通用',
    `sort`       int(2)       NOT NULL DEFAULT 0 COMMENT '权重,值越大越重要越靠前',
    `status`     char(1)      NOT NULL DEFAULT '' COMMENT '0-禁用 1-启用',
    `created_at` datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='广告表';

DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`
(
    `id`         char(20)     NOT NULL DEFAULT '' COMMENT '轮播图id',
    `title`      char(10)     NOT NULL DEFAULT '' COMMENT '轮播图标题',
    `type`       char(1)      NOT NULL DEFAULT '0' COMMENT '轮播图类型 0-图片 1-视频',
    `image_url`  varchar(255) NOT NULL DEFAULT '' COMMENT '轮播图片本身url',
    `url`        varchar(255) NOT NULL DEFAULT '' COMMENT '轮播图指向url',
    `area_list`  varchar(255) NOT NULL DEFAULT '' COMMENT '地区,为空时表示全国通用',
    `sort`       int(2)       NOT NULL DEFAULT 0 COMMENT '权重,值越大越重要越靠前',
    `status`     char(1)      NOT NULL DEFAULT '' COMMENT '0-禁用 1-启用',
    `created_at` datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='轮播图表';

insert into ad.banner(id, title, type, image_url, url, area_list, status, created_at)
values ('1', '', '0', 'banner01.png', '#', '', '1', '2020-04-23 18:39:41'),
       ('2', '', '0', 'banner02.png', '#', '', '1', '2020-04-23 18:39:41'),
       ('3', '', '0', 'banner03.png', '#', '', '1', '2020-04-23 18:39:41'),
       ('4', '', '0', 'banner04.png', '#', '', '1', '2020-04-23 18:39:41');
drop database if exists coupon;
create database coupon charset utf8mb4;
use coupon;

DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`
(
    `id`              char(20)       NOT NULL COMMENT '优惠券id',
    `member_id`       char(20)       NOT NULL DEFAULT '' COMMENT '会员id',
    `merchant_id`     char(20)       NOT NULL DEFAULT '' COMMENT '商家id',
    `store_id`        char(20)       NOT NULL DEFAULT '' COMMENT '店铺id',
    `store_name`      char(20)       NOT NULL DEFAULT '' COMMENT '店铺名字',
    `price`           decimal(10, 2) NOT NULL DEFAULT '' COMMENT '面值',
    `usage_condition` varchar(255)   NOT NULL DEFAULT '' COMMENT '使用条件 json值(price和stores)',
    `status`          char(1)        NOT NULL DEFAULT '' COMMENT '状态 0-未使用 1-已使用 2-已过期',
    `validity_start`  datetime                DEFAULT '' COMMENT '生效时间',
    `validity_end`    datetime                DEFAULT '' COMMENT '结束时间',
    `created_at`      datetime                DEFAULT '' COMMENT '创建时间',
    `updated_at`      datetime                DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`      int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券表';

drop database if exists merchant;
create database merchant charset utf8mb4;
use merchant;

DROP TABLE IF EXISTS `industry`;
CREATE TABLE `industry`
(
    `id`         char(20) NOT NULL COMMENT '行业id',
    `name`       char(10) NOT NULL COMMENT '行业名称',
    `image_url`  varchar(255)      DEFAULT '' COMMENT '行业小图标',
    `sort`       int(2)            DEFAULT 0 COMMENT '权重,值越大越重要越靠前',
    `created_at` datetime          DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime          DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)   NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商家行业分类表';

-- insert into industry (id, name) VALUES ('1','美食'),('2','洗车');


DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`
(
    `id`                   char(20)    NOT NULL COMMENT '店铺/商家id',
    `store_name`           varchar(50)          DEFAULT '' COMMENT '店铺名字',
    `merchant_id`          char(20) COMMENT '商户id',
    `member_id`            char(20)             DEFAULT '' COMMENT '会员id',
    `contact`              varchar(20)          DEFAULT '' COMMENT '联系人',
    `phone`                varchar(15)          DEFAULT '' COMMENT '电话',
    `email`                varchar(255)         DEFAULT '' COMMENT '邮箱',
    `password`             varchar(255)         DEFAULT '' COMMENT '密码',
    `business_license`     varchar(25)          DEFAULT '' COMMENT '营业执照号码',
    `longitude`            char(20)             DEFAULT '' COMMENT '经度',
    `latitude`             char(20)             DEFAULT '' COMMENT '纬度',
    `industry_id`          char(20)             DEFAULT '' COMMENT '行业id',
    `id_card`              varchar(20)          DEFAULT '' COMMENT '法人身份证号码',
    `province`             varchar(10)          DEFAULT '' COMMENT '省份',
    `province_id`          char(6)              DEFAULT '' COMMENT '省份id',
    `city`                 varchar(10)          DEFAULT '' COMMENT '城市',
    `city_id`              char(6)              DEFAULT '' COMMENT '城市id',
    `area`                 varchar(10)          DEFAULT '' COMMENT '区县',
    `area_id`              char(6)              DEFAULT '' COMMENT '区县id',
    `address`              varchar(50)          DEFAULT '' COMMENT '详细地址',
    `cover_image`          varchar(255)         DEFAULT '' COMMENT '封面图,门头照',
    `images`               varchar(255)         DEFAULT '' COMMENT '店内照',
    `rate`                 varchar(35)          DEFAULT '' COMMENT 'json vip1=会员折扣率 vip2=会长折扣率',
    `business_hours_start` time                 DEFAULT '' COMMENT '营业开始时间 示例：08:30:00',
    `business_hours_end`   time                 DEFAULT '' COMMENT '营业结束时间 示例：23:30:00',
    `score`                double(4, 2)         DEFAULT '' COMMENT '评分 店铺所有订单的评分的均值',
    `recommendation`       char(100)            DEFAULT '' COMMENT '店铺推荐语',
    `stock`                decimal(10, 2)       DEFAULT 0 comment '库存/代理商在这家店采购的额度的余额',
    `early_warning`        decimal(10, 2)       DEFAULT 0 comment '预警值',
    `fold_up`              double(3, 2)         DEFAULT 1 comment '折上折 没有折上折的 设为1，八五折为0.85',
    `type`                 char(1)              DEFAULT '0' COMMENT '商户类型 0-充送 1-折扣',
    `credit`               decimal(10, 2)       DEFAULT 0 comment '授信额度',
    `bank_name`            varchar(20) NULL COMMENT '开户行名称',
    `bank_account`         varchar(25) NULL COMMENT '银行账号',
    `account_name`         varchar(20) NULL COMMENT '户名',
    `account_type`         varchar(1)  NULL COMMENT '账户类型 0-对私 1-对公',
    `packing`              decimal(5, 2)        DEFAULT '' comment '包装费',
    `freight`              decimal(5, 2)        DEFAULT '' comment '配送费',
    `status`               char(1)              DEFAULT '0' COMMENT '0-禁用 1-启用',
    `remark`               varchar(255)         DEFAULT '' COMMENT '备注',
    `created_at`           datetime             DEFAULT '' COMMENT '创建时间',
    `updated_at`           datetime             DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`           int(1)      NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='店铺表';


DROP TABLE IF EXISTS `store_tags`;
CREATE TABLE `store_tags`
(
    `store_id`   char(20) NOT NULL COMMENT '店铺id',
    `tag_id`     char(20) NOT NULL COMMENT '标签id',
    `created_at` datetime          DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime          DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)   NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`store_id`, `tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='店铺标签';

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`
(
    `id`         char(20) NOT NULL COMMENT '标签id',
    `content`    char(10)          DEFAULT '' COMMENT '标签内容',
    `created_at` datetime          DEFAULT '' COMMENT '创建时间',
    `updated_at` datetime          DEFAULT '' COMMENT '最后更新时间',
    `deleted_at` int(1)   NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='标签表';
drop database if exists orders;
create database orders charset utf8mb4;
use orders;

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders`
(
    `id`              varchar(20) NOT NULL COMMENT '订单id',
    `open_id`         varchar(50)          DEFAULT '' comment '微信openId 主要用于微信的JsApi',
    `transaction_no`  varchar(35)          DEFAULT '' COMMENT '外部交易号',
    `payment`         char(2)              DEFAULT '' COMMENT '支付方式 10=微信jsApi支付 11=微信APP支付 12=微信Native支付 13=微信刷脸支付 14=微信H5支付,20-支付宝扫码,30-银联,40-其他',
    `member_id`       varchar(20)          DEFAULT '' COMMENT '会员id',
    `nickname`        varchar(50)          DEFAULT '' COMMENT '会员昵称',
    `member_grade`    varchar(1)           DEFAULT '' COMMENT '会员等级,是否plus会员 0-游客/注册用户,1-付了一块钱->会员,2-付了198->plus会员',
    `phone`           char(25)             DEFAULT '' COMMENT '会员手机号',
    `price`           decimal(10, 2)       DEFAULT '' COMMENT '小票金额',
    `rate`            double(3, 2)         DEFAULT '' COMMENT '折扣率',
    `discount_amount` decimal(10, 2)       DEFAULT '' COMMENT '优惠总金额',
    `pay_amount`      decimal(10, 2)       DEFAULT '' COMMENT '实付金额',
    `total_revenue`   decimal(10, 2)       DEFAULT '' COMMENT '总收益',
    `agent_revenue`   decimal(10, 2)       DEFAULT '' COMMENT '合伙人收益',
    `revenue`         decimal(10, 2)       DEFAULT '' COMMENT '平台收益',
    `principal`       decimal(10, 2)       DEFAULT '' COMMENT '本金',
    `merchant_id`     varchar(20)          DEFAULT '' COMMENT '公司id',
    `store_id`        varchar(20)          DEFAULT '' COMMENT '商家/店铺id',
    `store_name`      char(50)             DEFAULT '' COMMENT '商家/店铺名字',
    `area`            varchar(10)          DEFAULT '' COMMENT '商家/店铺所在区县',
    `status`          char(1)              DEFAULT '0' COMMENT '状态 0-取消支付/未支付 1-已支付 2-关闭订单',
    `has_coupon`      char(1)              DEFAULT '0' COMMENT '是否使用了优惠券 0-未使用 1-已使用',
    `coupon_id`       varchar(20)          DEFAULT '' COMMENT '优惠券id',
    `beans`           int(10)              DEFAULT '' COMMENT '使用码豆数量',
    `remark`          varchar(255)         DEFAULT '' COMMENT '备注',
    `created_at`      datetime             DEFAULT '' COMMENT '创建时间',
    `updated_at`      datetime             DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`      int(1)      NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';

DROP TABLE IF EXISTS `settlement`;

CREATE TABLE `settlement`
(
    `id`              varchar(20) NOT NULL COMMENT '结算总表id',
    `agent_id`        varchar(20)          DEFAULT '' COMMENT '代理商id',
    `amount`          decimal(10, 2)       DEFAULT '' COMMENT '结算金额',
    `settlement_date` date                 DEFAULT '' COMMENT '结算日期',
    `status`          char(1)              DEFAULT '' COMMENT '0-未提现 1-已提现',
    `created_at`      datetime             DEFAULT '' COMMENT '创建时间',
    `updated_at`      datetime             DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`      int(1)      NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='结算总表';

DROP TABLE IF EXISTS `settlement_details`;
CREATE TABLE `settlement_details`
(
    `id`              char(20) NOT NULL COMMENT '结算明细id',
    `agent_id`        char(20)          DEFAULT '' COMMENT '代理商id',
    `member_id`       char(20)          DEFAULT '' COMMENT '会员id',
    `company_id`      char(20)          DEFAULT '' COMMENT '商家id',
    `store_id`        char(20)          DEFAULT '' COMMENT '店铺id',
    `member_grade`    char(1)           DEFAULT '' COMMENT '会员等级',
    `price`           decimal(10, 2)    DEFAULT '' COMMENT '小票金额',
    `rate`            double(3, 2)      DEFAULT '' comment '折扣',
    `discount_amount` decimal(10, 2)    DEFAULT '' COMMENT '折扣金额',
    `pay_amount`      decimal(10, 2)    DEFAULT '' COMMENT '实付金额',
    `total_revenue`   decimal(10, 2)    DEFAULT '' COMMENT '总收益',
    `agent_revenue`   decimal(10, 2)    DEFAULT '' COMMENT '代理商收益',
    `revenue`         decimal(10, 2)    DEFAULT '' COMMENT '平台收益',
    `principal`       decimal(10, 2)    DEFAULT '' COMMENT '本金',
    `created_at`      datetime          DEFAULT '' COMMENT '创建时间',
    `updated_at`      datetime          DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`      int(1)   NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='结算明细';

CREATE TABLE `purchase`
(
    `id`            char(20)       not null comment '采购单id',
    `company_id`    char(20)       not null comment '公司id',
    `store_id`      char(20)       not null comment '店铺id',
    `store_name`    char(50)                DEFAULT '' COMMENT '店铺名字',
    `agent_id`      char(20)       not null comment '代理商id',
    `agent_name`    varchar(10)             DEFAULT '' COMMENT '代理商姓名',
    `agent_phone`   char(15)       NOT NULL COMMENT '代理商手机号',
    `area`          varchar(10)             DEFAULT '' COMMENT '区县',
    `face_price`    decimal(10, 2) not null comment '面值',
    `selling_price` decimal(10, 2) not null comment '出售价',
    `rate`          double(5, 3)   not null comment '折扣',
    `balance`       decimal(10, 2) not null comment '余额',
    `status`        char(1)        not null comment '0-未使用 1-使用中 2-已用完',
    `created_at`    datetime                DEFAULT '' COMMENT '创建时间',
    `updated_at`    datetime                DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`    int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='采购单';
