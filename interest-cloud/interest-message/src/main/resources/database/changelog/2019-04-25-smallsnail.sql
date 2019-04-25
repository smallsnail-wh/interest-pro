--liquibase formatted sql

--changeset smallsnail-wh:20190425-1
CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `content` text NOT NULL COMMENT '内容',
  `create_time` varchar(255) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_name` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='email'

--changeset smallsnail-wh:20190425-2
CREATE TABLE `msg_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `owner_id` int(11) NOT NULL COMMENT '帖子所有者id',
  `form` int(1) DEFAULT NULL COMMENT '消息类型（0：兴趣回帖，1：文章评论/回复）',
  `reply_card_id` int(10) DEFAULT NULL COMMENT '帖子回复id（form=0有效）',
  `comment_id` int(11) DEFAULT NULL COMMENT '文章评论id（form=1有效）',
  `reply_time` varchar(255) DEFAULT NULL COMMENT '回复时间',
  `is_read` int(1) DEFAULT '0' COMMENT '是否已读(0:未读，1:已读)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息记录表'