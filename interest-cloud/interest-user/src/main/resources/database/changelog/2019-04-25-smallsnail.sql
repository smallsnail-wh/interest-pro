--liquibase formatted sql

--changeset smallsnail-wh:20190425-1
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `headimg` varchar(255) DEFAULT NULL COMMENT '头像url',
  `url` varchar(255) DEFAULT NULL COMMENT 'GitHub主页',
  `create_time` varchar(255) DEFAULT NULL COMMENT '注册时间',
  `githubid` varchar(255) DEFAULT NULL COMMENT 'github的login',
  `qqid` varchar(255) DEFAULT NULL COMMENT 'qq的openid',
  `type` int(1) DEFAULT '0' COMMENT '用户类型（0：普通用户，1：系统用户）',
  `status` int(1) DEFAULT '0' COMMENT '用户状态（0：可用，1：不可用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `githubid_UNIQUE` (`githubid`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`),
  UNIQUE KEY `qqid_UNIQUE` (`qqid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户主表';
--changeset smallsnail-wh:20190425-1-1
INSERT INTO `user` VALUES (1,'admin','admin','{bcrypt}$2a$10$D8E4cuanLviCCe/ASqBC7OZ84JYOH8IT4/y4JLAV/Pm/AdhzPcy2.',NULL,'http://127.0.0.1:8081/interest/blog/interest/head/20190415/72bb167a-5c5a-4941-ad88-c19f7fc882c0.jpeg',NULL,'1531704654140',NULL,NULL,1,0);

--changeset smallsnail-wh:20190425-2
CREATE TABLE `user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `info` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `location` varchar(255) DEFAULT NULL COMMENT '所在位置',
  `skill` varchar(255) DEFAULT NULL COMMENT '技能',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详情表';
--changeset smallsnail-wh:20190425-2-1
INSERT INTO `user_detail` VALUES (1,1,'ni hao !','shanghai','java');

--changeset smallsnail-wh:20190425-3
CREATE TABLE `user_github` (
  `login` varchar(255) NOT NULL COMMENT 'github用户名',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像url',
  `html_url` varchar(255) DEFAULT NULL COMMENT 'github主页',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='github用户';

--changeset smallsnail-wh:20190425-4
CREATE TABLE `user_qq` (
  `openid` varchar(255) NOT NULL COMMENT 'openid',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户名',
  `figureurl_qq_1` varchar(255) DEFAULT NULL COMMENT '头像url',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qq用户';

--changeset smallsnail-wh:20190425-5
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2客户端配置表';
--changeset smallsnail-wh:20190425-5-1
INSERT INTO `oauth_client_details` VALUES ('client',NULL,'{noop}secret','all','password,authorization_code,refresh_token',NULL,NULL,NULL,NULL,NULL,NULL);

--changeset smallsnail-wh:20190425-6
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类id',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  `remark` text COMMENT '描述',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';
--changeset smallsnail-wh:20190425-6-1
INSERT INTO `menu` VALUES (1,'系统管理',' ',0,1,'系统管理','md-settings'),(2,'轮播管理',' ',0,3,NULL,'logo-snapchat'),(3,'兴趣管理',' ',0,4,NULL,'logo-reddit'),(4,'发帖管理',' ',0,5,NULL,'md-chatboxes'),(5,'邮件管理',' ',0,6,NULL,'ios-mail'),(6,'用户管理','/base/system-user',1,1,NULL,'ios-person'),(7,'菜单管理','/base/menu',1,2,NULL,'md-list-box'),(8,'角色管理','/base/role',1,3,NULL,'ios-people'),(9,'轮播','/base/banner',2,1,NULL,'logo-snapchat'),(10,'新建','/base/i-create',3,1,NULL,'md-checkmark-circle'),(11,'修改','/base/i-edit',3,2,NULL,'md-create'),(12,'删除','/base/i-delete',3,3,NULL,'md-remove-circle'),(13,'发帖管理','/base/card',4,1,NULL,'ios-create-outline'),(14,'邮件','/base/email',5,1,NULL,'ios-mail'),(15,'文章管理',NULL,0,7,NULL,'md-paper'),(16,'文章','/base/article',15,1,NULL,'md-paper'),(17,'用户管理',NULL,0,2,NULL,'md-contacts'),(18,'用户','/base/ordinary-user',17,1,NULL,'md-people');

--changeset smallsnail-wh:20190425-7
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role` varchar(50) NOT NULL COMMENT '角色',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `modules` text COMMENT '权限',
  `describe` text COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';
--changeset smallsnail-wh:20190425-7-1
INSERT INTO `role` VALUES (1,'ROLE_SUPER_ADMIN','超级管理员','4;5;7;8;9;10;11;14;1;12;13;16;18;6;','超级管理员，拥有全部权限。'),(2,'ROLE_BASE_ADMIN','基础管理员','9;10;11;12;13;14;16;18;','基础管理员');


--changeset smallsnail-wh:20190425-8
CREATE TABLE `r_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关系表';
--changeset smallsnail-wh:20190425-8-1
INSERT INTO `r_user_role` VALUES (1,1);