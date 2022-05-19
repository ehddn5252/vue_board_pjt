<template>
  <b-container class="bv-example-row">
    <b-row class="mt-4 mb-4 text-center">
      <b-col v-if="comments.length">
        <b-col>
          <comments-item
            v-for="comment in comments"
            :key="comment.commentsno"
            v-bind="comment"
          />
        </b-col>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import CommentsItem from "@/components/qna/item/CommentsItem";
import http from "@/api/http";

export default {
  name: "CommentsList",
  data() {
    return {
      comments: [],
    };
  },
  props: {
    qnano: Number,
  },
  components: {
    CommentsItem,
  },
  method: {
    updateComments() {
      console.log("변경~~~~~~~~");
      http.get(`/comments/${this.qnano}`).then(({ data }) => {
        console.log(data);
        this.comments = data;
      });
    },
    // changeComments() {
    //   console.log("CommentsList");
    //   this.$emit("changeComments");
    // },
  },
  created() {
    console.log(this.qnano);
    console.log(`/comments/${this.qnano}`);
    http.get(`/comments/${this.qnano}`).then(({ data }) => {
      console.log(data);
      this.comments = data;
    });
  },
};
</script>

<style></style>
