<template>
  <a id="custom-login-btn" @click="loginWithKakao">
    <img
      src="//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
      width="222"
      alt="카카오 로그인 버튼"
    />
  </a>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "LoginKakao",
  methods: {
    ...mapMutations("memberStore", ["SET_IS_LOGIN", "SET_IS_LOGIN_ERROR"]),

    loginWithKakao() {
      if (window.Kakao.Auth.getAccessToken()) {
        // window.Kakao.API.request({
        //   url: "/v1/user/unlink",
        //   success: function (response) {
        //     console.log(response);
        //   },
        //   fail: function (error) {
        //     console.log(error);
        //   },
        // });
        window.Kakao.Auth.setAccessToken(undefined);
      }

      window.Kakao.Auth.login({
        success: function (authObj) {
          console.log(authObj);
          console.log("데이터");
          console.log(authObj.access_token);
          sessionStorage.setItem("access-token", authObj.access_token);
          // this.SET_IS_LOGIN = true;
          // this.SET_IS_LOGIN_ERROR = false;
          // this.$router.push({ name: "home" });
        },
        fail: function (err) {
          console.log(JSON.stringify(err));
        },
      });
    },
  },
};
</script>
