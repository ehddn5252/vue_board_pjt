<template>
  <b-row
    class="m-2"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      <!-- 상호명과 도로명주소가 나옴 -->
      [{{ aroundStore.bizesNm }}] {{ aroundStore.rdnmAdr }}
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "AroundStoreListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    aroundStore: Object,
  },
  methods: {
    ...mapActions(["detailAroundStore"]),
    selectAroundStore() {
      console.log("listRow : ", this.aroundStore);
      // this.$store.dispatch("getHouse", this.house);
      this.detailAroundStore(this.aroundStore);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
