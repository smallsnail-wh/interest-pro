# interest-web

bbs网站+blog网站前端

项目介绍
--
vue.js+node.js+webpack构建的前端项目，后端用的是项目[interest-cloud](https://github.com/smallsnail-wh/interest-pro/tree/master/interest-cloud)（前后端分离）。

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
    ├── styles  样式文件
    ├── app.vue  入口页面
    ├── axios.js  axios配置
    ├── main.js  main.js
    ├── router.js  路由配置
    ├── base.js  公共js
    ├── commponents  共用组件文件
    │	└── interest-quill-editor.vue  富文本组件
    └── views
   		├── mobile  mobile版(开发阶段)
		└── pc  pc版
	        ├── error404.vue  404页面 
	        ├── login.vue  PC登录页面 
	        ├── index.vue  head+footer
	        ├── bbs  bbs目录
	        │   ├── card.vue  帖子页
	        │   ├── detail.vue  详情页
	        │   └── home.vue  bbs首页
	        ├── blog  blog目录
	        │   ├── article-detail.vue  文章详情页
	        │   ├── create-article-success.vue  文章创建成功页面
	        │   ├── create-article.vue  写文章页
	        │   ├── home.vue  blog首页
	        │   ├── update-article.vue  修改文章页
	        │   └── user-article.vue  用户文章页(可修改删除操作)
	        └── user  user目录
	        	├── messages.vue  用户消息页
	        	├── user-info.vue 用户详情页
	        	└── user-page.vue 用户信息+文章页
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
