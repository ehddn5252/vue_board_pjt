<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <div>
          {{ article.likeNum }}
          <span @click="clkLike">
            <b-icon icon="hand-thumbs-up" v-if="!isLike"></b-icon>
            <b-icon icon="hand-thumbs-up-fill" v-else></b-icon>
          </span>
        </div>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} </h3><div><h6>${article.userid}</div><div>조회수:${article.hit}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";

import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      isLike: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.put(`/board/hit/${this.$route.params.articleno}`).then(() => {
      http
        .get(`/board/${this.$route.params.articleno}/${this.userInfo.userId}`)
        .then(({ data }) => {
          this.article = data;
          this.isLike = data.isLike == 1 ? true : false;
        });
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boardDelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    clkLike() {
      if (this.isLike) {
        http
          .put(
            `/board/unlike/${this.$route.params.articleno}/${this.userInfo.userId}`,
          )
          .then(() => {
            http
              .get(
                `/board/${this.$route.params.articleno}/${this.userInfo.userId}`,
              )
              .then(({ data }) => {
                this.article = data;
                this.isLike = data.isLike == 1 ? true : false;
              });
          });
      } else {
        http
          .put(
            `/board/like/${this.$route.params.articleno}/${this.userInfo.userId}`,
          )
          .then(() => {
            http
              .get(
                `/board/${this.$route.params.articleno}/${this.userInfo.userId}`,
              )
              .then(({ data }) => {
                this.article = data;
                this.isLike = data.isLike == 1 ? true : false;
              });
          });
      }
      // 하트 수정
      this.isLike = !this.isLike;
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
