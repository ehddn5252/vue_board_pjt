<template>
  <b-container
    v-if="environs && environs.length != 0"
    class="bv-example-row mt-3"
  >
    <environ-list-item
      v-for="(environ, index) in environs"
      :key="index"
      :environ="environ"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import EnvironListItem from "@/components/environ/EnvironListItem.vue";
import { mapState } from "vuex";

export default {
  name: "EnvironList",
  components: {
    EnvironListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["environs"]),
  },
};
</script>

<style></style>
