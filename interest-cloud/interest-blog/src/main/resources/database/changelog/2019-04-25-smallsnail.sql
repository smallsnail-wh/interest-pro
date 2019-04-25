--liquibase formatted sql

--changeset smallsnail-wh:20190425-1
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `info` text NOT NULL COMMENT '简介',
  `content` longtext NOT NULL COMMENT '内容',
  `click_rate` int(11) DEFAULT '0' COMMENT '点击量',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论量',
  `top` int(1) DEFAULT '0' COMMENT '置顶（0:不置顶，1:置顶）',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `reply_time` varchar(255) DEFAULT NULL COMMENT '最新回复时间',
  `user_id` int(11) NOT NULL COMMENT '创建人id',
  `del` tinyint(1) DEFAULT '0' COMMENT '逻辑删除（0:启用，1:删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

--changeset smallsnail-wh:20190425-2
CREATE TABLE `article_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `comment` text COMMENT '评论',
  `replier_id` int(11) DEFAULT NULL COMMENT '回复评论人id',
  `replier_name` varchar(255) DEFAULT NULL COMMENT '回复评论人的姓名',
  `create_time` varchar(255) DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章评论';