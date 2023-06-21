<template>
  <div></div>
</template>

<script>
import commonLink from "@/api/test/common/index.js";
export default {
  name: "test",
  data() {
    return {};
  },
  mounted() {
    commonLink().then((res) => {
      console.log(res);
    });
  },
};
</script>

<style>
</style>