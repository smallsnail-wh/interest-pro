# interest-manage

后台管理系统前端

项目介绍
--
vue.js+node.js+webpack构建的前端项目，后端用的是项目[interest-cloud](https://github.com/smallsnail-wh/interest-pro/tree/master/interest-manage)（前后端分离）。

项目展示
--
地址：http://www.lovemtt.com/ 
可使用github/qq登录
（第一次打开可能会有点慢）

项目主要目录结构
--
```shell
.
├── package.json  项目构建配置
├── vue.config.js  项目构建配置
├── public  公共资源目录
└── src
    ├── images  图片文件
    ├── store  状态管理
    ├── app.vue  入口页面
    ├── axios.js  axios配置
    ├── main.js  main.js
    ├── router.js  路由配置
    ├── base.js  公共js
    └── views
   		├── base.vue  左边菜单和头部
   		├── error404.vue  404报错页面
   		├── login.vue  登陆页面
   		├── welcome.vue  欢迎页面
   		├── article  
   		│   └── article.vue  文章管理页面
   		├── banner  
   		│   └── banner.vue  轮播管理页面
   		├── email 
   		│   └── email.vue  邮件管理页面
   		├── interest  兴趣目录
   		│   ├── interest-create.vue  兴趣创建
   		│   ├── interest-delete.vue  兴趣删除
   		│   ├── interest-edit.vue  兴趣修改
   		│   └── interest-quill-editor.vue  富文本组件
   		├── postcard  
   		│   └── card.vue  发帖管理页面
   		├── sys  系统操作目录
   		│   ├── menu.vue  菜单管理页面
   		│   ├── role.vue  角色管理页面
   		│   └── system-user.vue  系统用户管理页面
		└── user  
		    └── ordinary-user.vue  普通用户管理页面
```

技术栈
--
 - vue2
 - vuex
 - vue-router
 - axios
 - [iview](https://www.iviewui.com/)
 - [vue-quill-editor](https://github.com/surmon-china/vue-quill-editor)
 - webpack

前后端通信：
------
	使用的是axios库。

UI 组件库：
-------
	使用了iview组件库。

安全协议：
-----
	oauth2

前端界面开发
--
注：需要安装nodejs
	

 - 启动：(注意：最好换cnpm，npm我这边启动报404)
	 1. 命令行进入项目文件夹
	 2. 运行npm install（初次启动）
	 3. 运行npm run serve启动前端工程
- 打包命令：
	运行npm run build

打包发布
--
1. 运行npm run build后，得到 dist文件。
2. 使用nginx发布。（[nginx配置参考文件](https://github.com/smallsnail-wh/interest/blob/master/nginx.conf)）
	
Http状态码
--
	在axios.js中拦截异常，并进行处理。
	目前以写的异常处理有：
		1. 401 清除token信息并跳转到登录页面
		2. 403 无权限，跳转到首页
如果你项目启动有错误：
--
1. 项目启动报错，请试一下用管理员权限输入命令。
2. 启动报错：Invalid options in vue.config.js: "publicPath" is not allowed，请把vue.config.js中的publicPath改成baseUrl。
3. install报错，请换成cnpm
