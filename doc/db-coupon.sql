drop database if exists robot_coupon;
create database robot_coupon charset utf8mb4;
use robot_coupon;

DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`
(
    `id`              char(20)       NOT NULL DEFAULT '' COMMENT '优惠券id',
    `member_id`       char(20)       NOT NULL DEFAULT '' COMMENT '会员id',
    `merchant_id`     char(20)       NOT NULL DEFAULT '' COMMENT '商家id',
    `store_id`        char(20)       NOT NULL DEFAULT '' COMMENT '店铺id',
    `store_name`      char(20)       NOT NULL DEFAULT '' COMMENT '店铺名字',
    `price`           decimal(10, 2) NOT NULL DEFAULT 0 COMMENT '面值',
    `usage_condition` varchar(255)   NOT NULL DEFAULT '' COMMENT '使用条件 json值(price和stores)',
    `status`          char(1)        NOT NULL DEFAULT '' COMMENT '状态 0-未使用 1-已使用 2-已过期',
    `validity_start`  datetime       NOT NULL DEFAULT CURRENT_DATE COMMENT '生效时间',
    `validity_end`    datetime       NOT NULL DEFAULT CURRENT_DATE COMMENT '结束时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at`      int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券表';