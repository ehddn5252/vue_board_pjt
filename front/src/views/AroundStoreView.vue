<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="person-lines-fill"></b-icon> AroundStore Service
    </h3>
    <b-col>
      <b-row>
        <b-col>
          <around-store-search-bar></around-store-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="6" align="left">
          <around-store-list />
        </b-col>
        <b-col cols="6">
          <around-store-detail></around-store-detail>
        </b-col>
      </b-row>
    </b-col>
    <b-col>
      <around-store-map></around-store-map>
    </b-col>
    <!-- <router-view></router-view> -->
  </b-container>
</template>

<script>
import AroundStoreSearchBar from "@/components/around/AroundStoreSearchBar.vue";
import AroundStoreDetail from "@/components/around/AroundStoreDetail.vue";
import AroundStoreList from "@/components/around/AroundStoreList.vue";
import AroundStoreMap from "@/components/around/AroundStoreMap.vue";

export default {
  name: "AroundStoreView",
  components: {
    AroundStoreSearchBar,
    AroundStoreDetail,
    AroundStoreList,
    AroundStoreMap,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
