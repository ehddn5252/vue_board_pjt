<template>
  <b-container class="bv-example-row">
    <b-row class="text-center">
      <b-col>
        <b-input-group prepend="댓글" class="mt-3">
          <b-form-input
            v-model.trim="content"
            @keypress.enter="registComment"
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
  props: {
    qnano: Number,
  },
  methods: {
    registComment() {
      const comment = {
        qnano: this.qnano,
        content: this.content,
        // 나중에 동적으로 이름 바뀌도록 수정
        userid: "ssafy",
      };

      http.put(`/comments/${this.qnano}`, comment).then(({ data }) => {
        console.log(data);
        this.comments = data;
      });
      // if (todoItem.title) this.$store.dispatch("createTodo", todoItem);
      // this.todoTitle = "";
    },
  },
};
</script>

<style></style>
