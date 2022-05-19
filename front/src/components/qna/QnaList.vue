<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <div class="m-3 row justify-content-end">
        <form id="searchform" class="form-inline" method="get">
          <!-- <input type="hidden" name="pg" value="1" /> -->

          <b-form-select v-model="key" :options="options"></b-form-select>
          <b-form-input
            v-model.trim="word"
            @keypress.enter="searchQna"
          ></b-form-input>
          <b-button variant="primary" @click="searchQna"> 검색 </b-button>
        </form>
      </div>
    </b-row>
    <b-row>
      <b-col v-if="qnas && qnas.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <qna-list-item v-for="qna in qnas" :key="qna.qnano" v-bind="qna" />
          </tbody>
        </b-table-simple>
      </b-col>
      <b-col v-else> 작성된 QnA가 없습니다. </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>

    <div
      class="m-3 row justify-content-center"
      @click="clickPage"
      v-html="`${navigator}`"
    ></div>
  </b-container>
</template>

<script>
import http from "@/api/http";
import QnaListItem from "@/components/qna/item/QnaListItem";

export default {
  name: "QnaList",
  components: {
    QnaListItem,
  },
  data() {
    return {
      pg: 1,
      qnas: [],
      word: "",
      key: "userid",
      options: [
        { value: "userid", text: "아이디" },
        { value: "qnano", text: "글번호" },
        { value: "subject", text: "제목" },
      ],
      navigator: "",
    };
  },
  created() {
    http.get(`/qna/list?pg=1&key=&word=`).then(({ data }) => {
      this.qnas = data.qnalist;
      this.navigator = data.navigation.navigator;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaRegister" });
    },
    // 검색 버튼 눌렸을 때
    searchQna(event) {
      event.preventDefault();
      if (this.word == "") {
        alert("모든 목록 조회!!!");
      }
      http
        .get(`/qna/list?pg=1&key=${this.key}&word=${this.word}`)
        .then(({ data }) => {
          this.qnas = data.qnalist;
          this.navigator = data.navigation.navigator;
        });
    },

    clickPage(e) {
      if (e.target.classList.contains("page-link")) {
        this.pg = e.target.name;
        http
          .get(`/qna/list?pg=${this.pg}&key=${this.key}&word=${this.word}`)
          .then(({ data }) => {
            this.qnas = data.qnalist;
            this.navigator = data.navigation.navigator;
          });
      }
    },

    linkGen(pageNum) {
      return pageNum === 1 ? "?" : `?page=${pageNum}`;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
