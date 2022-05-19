import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },

  // {
  //   path: "/interestinfo",
  //   name: "interestinfo",
  //   redirect: "/interestinfo/store",
  //   component: () => import("@/views/AroundStoreView.vue"),
  // },

  {
    path: "/interestinfo",
    name: "interestinfo",
    component: () => import("@/views/AroundStoreView.vue"),
    redirect: "/interestinfo/store",
    children: [
      {
        path: "store",
        name: "store",
        // component: () => import("@/components/around/AroundStoreSearchBar.vue"),
      },
    ],
  },

  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/QnaView.vue"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnaList",
        component: () => import("@/components/qna/QnaList.vue"),
      },
      {
        path: "write",
        name: "qnaRegister",
        component: () => import("@/components/qna/QnaRegister.vue"),
      },
      {
        path: "detail/:qnano",
        name: "qnaDetail",
        component: () => import("@/components/qna/QnaDetail.vue"),
      },
      {
        path: "modify/:qnano",
        name: "qnaModify",
        component: () => import("@/components/qna/QnaModify.vue"),
      },
      {
        path: "delete/:qnano",
        name: "qnaDelete",
        component: () => import("@/components/qna/QnaDelete.vue"),
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/QnaView.vue"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnaList",
        component: () => import("@/components/qna/QnaList.vue"),
      },
      {
        path: "write",
        name: "qnaRegister",
        component: () => import("@/components/qna/QnaRegister.vue"),
      },
      {
        path: "detail/:qnano",
        name: "qnaDetail",
        component: () => import("@/components/qna/QnaDetail.vue"),
      },
      {
        path: "modify/:qnano",
        name: "qnaModify",
        component: () => import("@/components/qna/QnaModify.vue"),
      },
      {
        path: "delete/:qnano",
        name: "qnaDelete",
        component: () => import("@/components/qna/QnaDelete.vue"),
      },
    ],
  },
  {
    path: "/instagram",
    name: "instagram",
    component: () => import("@/views/InstagramView.vue"),
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/environ",
    name: "environ",
    component: () => import("@/views/EnvironView.vue"),
  },
  {
    path: "/todo",
    name: "todo",
    component: () => import("@/views/TodoView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
