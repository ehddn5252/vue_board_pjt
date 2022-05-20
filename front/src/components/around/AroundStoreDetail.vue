<template>
  <b-container v-if="aroundStore" class="bv-example-row">
    <b-row>
      <b-col
        ><h3>{{ aroundStore.bizesNm }}</h3></b-col
      >
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning"
          >주소 : {{ aroundStore.rdnmAdr }}</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "AroundStoreDetail",
  computed: {
    ...mapState(["aroundStore"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
