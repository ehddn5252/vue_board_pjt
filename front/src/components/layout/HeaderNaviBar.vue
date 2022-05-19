<template>
  <div>
    <b-navbar
      toggleable="lg"
      id="header"
      :class="{
        'fixed-top': true,
        'header-scrolled': !view.topOfPage,
        'shadow-lg': !view.topOfPage,
      }"
    >
      <b-navbar-brand href="#" class="container d-flex align-items-center">
        <h1 class="logo text-light">
          <router-link to="/">
            <span
              >Happy<span
                id="logo-a "
                style="color: rgb(217, 219, 252); font-size: 26px"
                >House</span
              ></span
            >
          </router-link>
        </h1>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'home' }" class="link"
              ><b-icon icon="house" font-scale="1.5"></b-icon> 홈</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="link"
              ><b-icon icon="journal" font-scale="1.5"></b-icon>
              게시판</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'qna' }" class="link"
              ><b-icon icon="journal" font-scale="1.5"></b-icon>
              QnA</router-link
            ></b-nav-item
          >

          <!-- <b-nav-item href="#"

            ><router-link :to="{ name: 'instagram' }" class="link"
              ><b-icon icon="instagram" font-scale="1.5"></b-icon>
              인별그램</router-link
            ></b-nav-item
          > -->
          <b-nav-item href="#"
            ><router-link :to="{ name: 'house' }" class="link"
              ><b-icon icon="house-fill" font-scale="1.5"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'environ' }" class="link"
              ><b-icon icon="house-fill" font-scale="1.5"></b-icon>
              환경</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'interestinfo' }" class="link"
              ><b-icon icon="calendar-check" font-scale="1.5"></b-icon>
              주변상권조회</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "HeaderNaviBar",
  data() {
    return {
      view: {
        topOfPage: true,
      },
    };
  },
  beforeMount() {
    window.addEventListener("scroll", this.handleScroll);
  },
  methods: {
    handleScroll() {
      if (window.pageYOffset > 0) {
        if (this.view.topOfPage) this.view.topOfPage = false;
      } else {
        if (!this.view.topOfPage) this.view.topOfPage = true;
      }
    },
  },
};
</script>

<style scoped>
h1.logo * {
  font-size: 24px;
  text-shadow: rgba(0, 0, 0, 0.267) 1px 1px 1px;
  line-height: 1;
  font-weight: 700;
  letter-spacing: 1px;
  font-family: Poppins, sans-serif;
  text-transform: uppercase;
}
</style>
