import Vue from "vue";
import VueRouter from "vue-router";
import iView from "iview";

Vue.use(VueRouter);

const routers = [
  {
    path: "*",
    component: resolve => require(["./views/pc/error404.vue"], resolve)
  },
  {
    path: "/",
    meta: {
      title: "smallsnail-wh"
    },
    component: resolve => require(["./views/pc/index.vue"], resolve),
    children: [
      {
        path: "",
        name: "home",
        component: resolve => require(["./views/pc/bbs/home.vue"], resolve),
        meta: {
          title: "home"
        }
      },
      {
        path: "messages",
        name: "bbs-messages",
        component: resolve =>
          require(["./views/pc/user/messages.vue"], resolve),
        meta: {
          title: "messages"
        }
      },
      {
        path: "user",
        name: "user",
        component: resolve =>
          require(["./views/pc/user/user-info.vue"], resolve),
        meta: {
          title: "user"
        }
      },
      {
        path: "user/:id",
        name: "user-id",
        component: resolve =>
          require(["./views/pc/user/user-page.vue"], resolve),
        meta: {
          title: "user"
        }
      }
    ]
  },
  {
    path: "/qq",
    meta: {
      title: "smallsnail-wh"
    },
    component: resolve => require(["./views/pc/index.vue"], resolve),
    children: [
      {
        path: "",
        name: "qq-home",
        component: resolve => require(["./views/pc/bbs/home.vue"], resolve),
        meta: {
          title: "home"
        }
      }
    ]
  },
  {
    path: "/login",
    meta: {
      title: "smallsnail-wh"
    },
    component: resolve => require(["./views/pc/login.vue"], resolve)
  },
  {
    path: "/bbs",
    meta: {
      title: "smallsnail-wh"
    },
    component: resolve => require(["./views/pc/index.vue"], resolve),
    children: [
      {
        path: "home",
        name: "bbs-home",
        component: resolve => require(["./views/pc/bbs/home.vue"], resolve),
        meta: {
          title: "home"
        }
      },
      {
        path: "home/:title",
        name: "bbs-home-title",
        component: resolve => require(["./views/pc/bbs/home.vue"], resolve),
        meta: {
          title: "home"
        }
      },
      {
        path: "detail/:id",
        name: "bbs-detail-id",
        component: resolve => require(["./views/pc/bbs/detail.vue"], resolve),
        meta: {
          title: "detail"
        }
      },
      {
        path: "card/:id",
        name: "bbs-card-id",
        component: resolve => require(["./views/pc/bbs/card.vue"], resolve),
        meta: {
          title: "card"
        }
      }
    ]
  },
  {
    path: "/blog",
    meta: {
      title: "smallsnail-wh"
    },
    component: resolve => require(["./views/pc/index.vue"], resolve),
    children: [
      {
        path: "",
        name: "blog-home",
        component: resolve => require(["./views/pc/blog/home.vue"], resolve),
        meta: {
          title: "blog"
        }
      },
      {
        path: "create",
        name: "blog-create",
        component: resolve => require(["./views/pc/blog/create-article.vue"], resolve),
        meta: {
          title: "blog"
        }
      },
      {
        path: "create/success",
        name: "blog-create",
        component: resolve => require(["./views/pc/blog/create-article-success.vue"], resolve),
        meta: {
          title: "blog"
        }
      },
      {
        path: "detail/:id",
        name: "blog-detail-id",
        component: resolve => require(["./views/pc/blog/article-detail.vue"], resolve),
        meta: {
          title: "blog"
        }
      },
      {
        path: "user",
        name: "blog-user",
        component: resolve => require(["./views/pc/blog/user-article.vue"], resolve),
        meta: {
          title: "blog"
        }
      },
      {
        path: "update/:id",
        name: "blog-update-id",
        component: resolve => require(["./views/pc/blog/update-article.vue"], resolve),
        meta: {
          title: "blog"
        }
      }
    ]
  },
  {
    path: "/mlogin",
    meta: {
      title: "smallsnail-wh"
    },
    component: resolve => require(["./views/mobile/mlogin.vue"], resolve)
  },
  {
    path: "/mobile",
    meta: {
      title: "smallsnail-wh"
    },
    component: resolve => require(["./views/mobile/index.vue"], resolve),
    children: [
      {
        path: "",
        name: "mobile-home",
        component: resolve => require(["./views/mobile/bbs/home.vue"], resolve),
        meta: {
          title: "home"
        }
      },
      {
        path: "home/:title",
        name: "mobile-home-title",
        component: resolve => require(["./views/mobile/bbs/home.vue"], resolve),
        meta: {
          title: "home"
        }
      },
      {
        path: "detail/:id",
        name: "mobile-detail-id",
        component: resolve => require(["./views/mobile/bbs/detail.vue"], resolve),
        meta: {
          title: "detail"
        }
      },
      {
        path: "card/:id",
        name: "mobile-card-id",
        component: resolve => require(["./views/mobile/bbs/card.vue"], resolve),
        meta: {
          title: "card"
        }
      },

      {
        path: "messages",
        name: "mobile-messages",
        component: resolve => require(["./views/mobile/messages.vue"], resolve),
        meta: {
          title: "messages"
        }
      }
    ]
  }
];

// 路由配置
const RouterConfig = {
  mode: "history",
  routes: routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start();
  next();
});

router.afterEach(() => {
  iView.LoadingBar.finish();
  window.scrollTo(0, 0);
});

export default router;
