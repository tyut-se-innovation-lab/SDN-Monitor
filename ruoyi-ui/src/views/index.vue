<template>
  <div class="home">
    <Title />
    <div class="bottom-box">
      <div class="left">
        <Link />
      </div>
      <div class="right">
        <div class="top">
          <Rose />
          <PercentCircle />
          <List />
        </div>
        <div class="bottom">
          <Ring />
          <Byte />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PercentCircle from "@/views/home/link/circle.vue";
import Link from "@/views/home/link/index.vue";
import List from "@/views/home/attack/list.vue";
import Ring from "@/views/home/attack/ring.vue";
import Rose from "@/views/home/attack/rose.vue";
import Byte from "@/views/home/rate/byte.vue";
import Title from "@/views/home/title/index.vue";
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.5",
      attackData: [],
    };
  },
  components: {
    Link,
    List,
    Ring,
    Rose,
    Byte,
    Title,
    PercentCircle,
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
  mounted() {},
};
</script>

<style scoped>
* {
  background: #eaeef0;
}
.home {
  display: flex;
  flex-wrap: wrap;
}
.top {
  display: flex;
}
.bottom {
  display: flex;
}
.bottom-box {
  display: flex;
}
</style>

