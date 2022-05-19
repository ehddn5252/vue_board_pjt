<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon> Environ Service
    </h3>
    <router-view></router-view>
    <b-row>
      <b-col>
        <environ-search-bar></environ-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <environ-list />
      </b-col>
      <b-col cols="6">
        <environ-detail />
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import EnvironSearchBar from "@/components/environ/EnvironSearchBar.vue";
import EnvironList from "@/components/environ/EnvironList.vue";
import EnvironDetail from "@/components/environ/EnvironDetail.vue";

export default {
  name: "EnvironView",
  components: {
    EnvironSearchBar,
    EnvironList,
    EnvironDetail,
  },
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
