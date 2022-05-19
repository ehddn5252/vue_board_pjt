import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueKakaoSdk from "vue-kakao-sdk";

const apiKey = "fdf614748efadd63bf7ce73b5ddad4f1";
Vue.use(VueKakaoSdk, { apiKey }); // apiKey 를 반드시 입력해줘야한다.

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
