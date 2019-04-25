--liquibase formatted sql

--changeset smallsnail-wh:20190425-1
CREATE TABLE `interest` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `info` text NOT NULL COMMENT '简介',
  `content` text NOT NULL COMMENT '内容',
  `image` varchar(255) DEFAULT NULL COMMENT '图片url',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `banner` int(1) DEFAULT '0' COMMENT '是否加入轮播（0:不加入，1:加入）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兴趣表';
--changeset smallsnail-wh:20190425-1-1
INSERT INTO `interest` VALUES (1,'动漫','一起畅游二次元世界。','<p>&nbsp;&nbsp;&nbsp;<img src=\"http://photo.16pic.com/00/52/03/16pic_5203018_b.jpg\" style=\"display: block; margin: auto;\"></p><p><br></p><h2 class=\"ql-indent-1\"><span class=\"ql-size-large\"> </span>			<span class=\"ql-size-large ql-font-serif\">日本动漫,是日本动画和日本漫画的合称。日本动漫基本上不如美国动画特效那么炫目, 但是凭着剧情的创新和趣味性在全球都有很重要的地位,拥有了广大的收看人群。日本是世界第一大动漫强国。其动画发展的模式具有鲜明的民族特色。比如漫画里近乎逼真的亚洲人的美和各种鲜明特色的文化影子。这些都是其他国家动漫忘尘莫及的</span><span class=\"ql-size-large\">。</span></h2>','http://photo.16pic.com/00/52/03/16pic_5203018_b.jpg',1,1),(2,'汉服','正礼仪于中华 存衣冠于华夏','<p><em>&nbsp;&nbsp;&nbsp;汉服，全称是“汉民族传统服饰”，又称汉衣冠、汉装、华服，是从黄帝即位到公元17世纪中叶（明末清初），在汉族的主要居住区，以“华夏－汉”文化为背景和主导思想，以华夏礼仪文化为中心，通过自然演化而形成的具有独特汉民族风貌性格，明显区别于其他民族的传统服装和配饰体系，是中国“衣冠上国”、“礼仪之邦”、“锦绣中华”、赛里斯国的体现，承载了汉族的染织绣等杰出工艺和美学，传承了30多项中国非物质文化遗产以及受保护的中国工艺美术。</em></p>','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531238674678&di=ac3fc5c5d880c23ef3f8a1219d08fb67&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201707%2F23%2F190138pwzurpp0uzieuu0r.jpg',2,1),(3,'滑板','有胆你就来 这是和生命的较量','<p><strong>&nbsp;&nbsp;&nbsp;滑板Skateboard项目可谓是极限运动历史的鼻祖，许多的极限运动项目均由滑板项目延伸而来。20世纪50年代末60年代初由冲浪运动演变而成的滑板运动，在而今已成为地球上最“酷”的运动。滑板运动以滑行为特色，崇尚自由的运动方式，体验与创造超重力的感受，给滑者带来成功和创造的喜悦。滑板运动不同于传统运动项目，不拘泥于固定的模式，需要滑手自由发挥想象力，在运动过程中创造，以创造力来运动，强调身心的自由，推崇与自然互相融合的运动理念。</strong></p>','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531238779845&di=2c92621c209c107d5d999dddf38a62be&imgtype=0&src=http%3A%2F%2Fimg05.tooopen.com%2Fimages%2F20150903%2Ftooopen_sy_141167247975.jpg',3,1),(4,'吉他','不如学一学吉他 它懂你的孤独','<p>&nbsp;&nbsp;&nbsp;新的一天。阳光温暖。我的心也应该是温暖的。如若可以。在午后，约上几个志同道合的人，一起带着吉他，到一个安静的田野中弹起吉他唱起歌。或者，一个人背着吉他去不远处的山坡上弹个小曲唱个小调，也是一件幸福的事情。大自然会给你愈伤的力量。有时候真有抛却现实的繁杂，背上一把吉他，浪迹天涯的冲动。也许每一个弹吉他爱摇滚的孩子都有这样一种理想主义的悲壮情怀。</p>','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531238896141&di=880a3bc620e4ce4eb362903473b67a87&imgtype=0&src=http%3A%2F%2Fimg.gsxservice.com%2F1645166_x83xnzjf.jpeg',4,1),(5,'舞蹈','每个不曾起舞的日子都是对生命的辜负','<p>&nbsp;&nbsp;&nbsp;<span style=\"background-color: white;\">舞人从容而舞，形舒意广。她的心遨游在无垠的太空，自由地远思长想。开始的动作，像是俯身，又像是仰望；像是来、又像是往。是那样的雍容不迫，又是那么不已的惆怅，实难用语言来形象。接着舞下去，像是飞翔，又像步行；像是辣立，又像斜倾。不经意的动作也决不失法度，手眼身法都应着鼓声。纤细的罗衣从风飘舞，缭绕的长袖左右交横。络绎不绝的姿态飞舞散开，曲折的身段手脚合并。</span></p>','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531834053&di=0f3964dd5bf8e3a701e72a85bb799441&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F81%2F88%2F565541a818c65_1024.jpg',7,1);

--changeset smallsnail-wh:20190425-2
CREATE TABLE `post_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `interest_id` int(11) DEFAULT NULL COMMENT '兴趣id',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `reply_time` varchar(255) DEFAULT NULL COMMENT '最新回复时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子表';

--changeset smallsnail-wh:20190425-3
CREATE TABLE `reply_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` text NOT NULL COMMENT '内容',
  `post_card_id` int(11) DEFAULT NULL COMMENT '帖子id',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) NOT NULL COMMENT '回复用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回帖表';