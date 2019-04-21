import Vue from "vue";
import VueRouter from "vue-router";
import iView from "iview";

Vue.use(VueRouter);

const routers = [
  {
    path: "*",
    component: resolve => require(["./views/error404.vue"], resolve)
  },
  {
    path: "",
    name: "login",
    component: resolve => require(["./views/login.vue"], resolve),
    meta: {
      title: "login"
    }
  },
  {
    path: "/base",
    meta: {
      title: "base",
      requiresAuth: true
    },
    component: resolve => require(["./views/base.vue"], resolve),
    children: [
      {
        path: "",
        name: "welcome",
        component: resolve => require(["./views/welcome.vue"], resolve),
        meta: {
          title: "welcome"
        }
      },
      {
        path: "menu",
        name: "menu",
        component: resolve => require(["./views/sys/menu.vue"], resolve),
        meta: {
          title: "menu"
        }
      },
      {
        path: "role",
        name: "role",
        component: resolve => require(["./views/sys/role.vue"], resolve),
        meta: {
          title: "role"
        }
      },
      {
        path: "system-user",
        name: "system-user",
        component: resolve => require(["./views/sys/system-user.vue"], resolve),
        meta: {
          title: "user"
        }
      },
      {
        path: "email",
        name: "email",
        component: resolve => require(["./views/email/email.vue"], resolve),
        meta: {
          title: "email"
        }
      },
      {
        path: "card",
        name: "cardmanage",
        component: resolve => require(["./views/postcard/card.vue"], resolve),
        meta: {
          title: "card"
        }
      },
      {
        path: "i-edit",
        name: "interest-edit",
        component: resolve =>
          require(["./views/interest/interest-edit.vue"], resolve),
        meta: {
          title: "interest"
        }
      },
      {
        path: "i-create",
        name: "interest-create",
        component: resolve =>
          require(["./views/interest/interest-create.vue"], resolve),
        meta: {
          title: "interest"
        }
      },
      {
        path: "i-delete",
        name: "interest-delete",
        component: resolve =>
          require(["./views/interest/interest-delete.vue"], resolve),
        meta: {
          title: "interest"
        }
      },
      {
        path: "banner",
        name: "banner",
        component: resolve => require(["./views/banner/banner.vue"], resolve),
        meta: {
          title: "banner"
        }
      },
      {
        path: "article",
        name: "article",
        component: resolve => require(["./views/article/article.vue"], resolve),
        meta: {
          title: "article"
        }
      },
      {
        path: "ordinary-user",
        name: "ordinary-user",
        component: resolve => require(["./views/user/ordinary-user.vue"], resolve),
        meta: {
          title: "article"
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
  // let token = window.localStorage.getItem("currentUser_token");
  // if (
  //   to.matched.some(record => record.meta.requiresAuth) &&
  //   (!token || token === null)
  // ) {
  //   next({
  //     path: "/",
  //     query: { redirect: to.fullPath }
  //   });
  // }
  iView.LoadingBar.start();
  next();
});

router.afterEach(() => {
  iView.LoadingBar.finish();
  window.scrollTo(0, 0);
});

export default router;
