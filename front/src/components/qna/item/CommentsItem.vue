<template>
  <b-container class="bv-example-row">
    <b-row class="mt-3">
      <b-col cols="11" class="bg-light p-2 pl-5 text-left">
        <span>
          <h6>
            <div>{{ userid }}</div>
            <div>{{ regtime }}</div>
          </h6></span
        >
        <div>
          {{ message }}
        </div>
      </b-col>
      <!-- <b-col class="p-2"
        ><b-button variant="danger" @click="deleteTodo">X</b-button></b-col
      > -->
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";

export default {
  name: "CommentsListItem",
  props: {
    commentsno: Number,
    userid: String,
    regtime: String,
    content: String,
  },
  computed: {
    message() {
      if (this.content) return this.content.split("\n").join("<br>");
      return "";
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  created() {
    console.log(this.commentsno);
  },
};
</script>

<style></style>
