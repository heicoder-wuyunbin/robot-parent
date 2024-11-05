drop database if exists robot_ad;
create database robot_ad charset utf8mb4;
use robot_ad;

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