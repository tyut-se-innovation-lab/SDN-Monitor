
<template>
  <!-- 南丁格尔玫瑰图 -->
  <div id="rose"></div>
</template>

<script>
import Rose from "@/api/charts/rose/index.js";
export default {
  name: "rose",
  data() {
    return {
      title: "Rose",
    };
  },
  mounted() {
    new Rose("攻击分类", "rose", { width: 450, height: 400 }).init();
  },
};
</script>

<style scoped>
#rose {
  width: 450px;
  height: 400px;
  position: relative;
  background: #ffffff;
  border: 6px solid #ffffff;
  margin: 10px;
  margin-left: 20px;
  box-shadow: -8px -8px 15px rgba(255, 255, 255, 1),
    8px 8px 25px rgba(0, 0, 0, 0.15);
}
</style>