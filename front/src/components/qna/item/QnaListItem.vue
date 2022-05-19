<template>
  <b-tr>
    <b-td>{{ qnano }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'qnaDetail', params: { qnano: qnano } }">{{
        subject
      }}</router-link>
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "QnaListItem",
  props: {
    qnano: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
