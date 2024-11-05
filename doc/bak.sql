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
    `area_id`       varchar(20)  NOT NULL DEFAULT '' COMMENT '区域id',
    `beans`         int(11)      NOT NULL DEFAULT '0' COMMENT '码豆数量',
    `type`          char(1)      NOT NULL DEFAULT '0' COMMENT '0-获得 1-消费',
    `remark`        varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
    `created_at`    datetime              DEFAULT '' COMMENT '创建时间',
    `updated_at`    datetime              DEFAULT '' COMMENT '最后更新时间',
    `deleted_at`    int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='码豆记录表';