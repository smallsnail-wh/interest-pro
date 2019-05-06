# interest-server

## 项目升级

1. 添加kafka


## 项目介绍

spring clound项目,前端项目[interest-web](https://github.com/smallsnail-wh/interest-pro/tree/master/interest-web)和搭配[interest-manage](https://github.com/smallsnail-wh/interest-pro/tree/master/interest-manage)（前后端分离）。

## 项目展示

地址：http://www.lovemtt.com/
可使用github/qq登录
（第一次打开可能会有点慢）

项目目录结构
--
```shell
.
└── spring-cloud
    ├── interest-auth  认证授权服务（负责登陆）
    ├── interest-bbs  bbs服务
    ├── interest-blog blog服务
    ├── interest-common  项目公共包（非服务）
    ├── interest-eureka  eureka服务
    ├── interest-message  用户消息服务
    ├── interest-user  用户服务
    └── interest-zuul  zuul服务
```

## 技术栈

 - Spring cloud
 - Mybatis
 - Spring Security
 - Spring Security OAuth2
 - Redis
 - Kafka
 - Liquibase

## 接口设计：

	RESTful

## 认证与授权：

	使用了Spring Security OAuth2

## 数据库：

 - interest_user 用户表
 - interest_bbs bbs表
 - interest_blog blog表
 - interest_message 消息表
 
 使用mysql（用户密码为BCrypt加密，用户admin的密码为admin）

## 项目搭建

**运行环境：**

  jdk1.8+maven。
  
 **数据库配置：**
 
 数据库mysql(yml配置好数据库连接后Liquibase会自动初始化数据库)
 
 **缓存配置：** 
 
  配置redis，且redis服务必须开启。
 
 ## 如果你项目启动有错误：
 
 1. 检查数据库是否配置好。（用户密码为BCrypt加密，用户admin的密码为admin）
 2. 检查redis是否配置好，redis服务必须开启（检查防火墙是否打开）
 		
