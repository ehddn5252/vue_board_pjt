<template>
  <b-container class="bv-example-row">
    <b-row class="text-center">
      <b-col>
        <b-input-group prepend="댓글" class="mt-3">
          <b-form-input
            v-on:submit.native.prevent="onSubmit"
            v-model.trim="content"
            v-on:keydown.enter.prevent="registComment"
          ></b-form-input>
          <b-input-group-append>
            <b-button variant="outline-success" @click="registComment"
              >등록</b-button
            >
          </b-input-group-append>
        </b-input-group>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
export default {
  name: "CommentsInput",
  data() {
    return {
      content: "",
    };
  },
  methods: {
    registComment() {
      const comment = {
        qnano: this.$store.state.qnano,
        content: this.content,
        // 나중에 동적으로 이름 바뀌도록 수정
        userid: "ssafy",
      };

      if (comment.content) {
        http
          .post(`/comments`, comment)
          .then(({ data }) => {
            console.log(data);
            this.comments = data;
          })
          .then(() => {
            // console.log(this);
            // this.$emit("changeComments");
            this.content = "";
            this.$store.dispatch("getComments", this.$store.state.qnano);
          });
      }
    },
  },
};
</script>

<style></style>
