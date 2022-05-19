<template>
  <div class="container">
    <div class="login-page">
      <b-row class="mt-4 mb-4 text-center">
        <!-- <b-col class="sm-3">
        <b-form-input
          v-model.trim="dongCode"
          placeholder="동코드 입력...(예 : 11110)"
          @keypress.enter="sendKeyword"
        ></b-form-input>
      </b-col>
      <b-col class="sm-3" align="left">
        <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
      </b-col> -->
        <div>name</div>
        <b-col class="sm-3">
          <b-form-select
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            v-model="dongCode"
            :options="dongs"
            @change="searchAroundStore"
          ></b-form-select>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "AroundStoreSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "dongs"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions([
      "getSidoAroundMap",
      "getSido",
      "getGugun",
      "getDong",
      "getHouse",
      "getAroundStoreList",
    ]),
    ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    getSidoAround() {
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchAroundStore() {
      console.log("codes");
      console.log(this.sidoCode);
      console.log(this.gugunCode);
      console.log(this.dongCode);
      console.log("=============");
      const datas = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        dongCode: this.dongCode,
      };
      if (this.dongCode) this.getAroundStoreList(datas);
    },
  },
};
</script>

<style></style>
