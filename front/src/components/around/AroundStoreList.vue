<template>
  <b-container
    v-if="aroundStores && aroundStores.length != 0"
    class="bv-example-row mt-3"
  >
    <around-store-list-item
      v-for="(aroundStore, index) in aroundStores"
      :key="index"
      :aroundStore="aroundStore"
    >
    </around-store-list-item>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import AroundStoreListItem from "@/components/around/item/AroundStoreListItem.vue";
import { mapState } from "vuex";

export default {
  name: "AroundStoreList",
  components: {
    AroundStoreListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["aroundStores"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
