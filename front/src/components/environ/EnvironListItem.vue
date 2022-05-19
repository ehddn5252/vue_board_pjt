<template>
  <b-row
    class="m-2"
    @click="selectEnviron"
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
      {{ environ.name }}
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "EnvironListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    environ: Object,
  },
  methods: {
    ...mapActions(["detailEnviron"]),
    selectEnviron() {
      console.log("listRow : ", this.environ);
      // this.$store.dispatch("getenviron", this.environ);
      this.detailEnviron(this.environ);
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
