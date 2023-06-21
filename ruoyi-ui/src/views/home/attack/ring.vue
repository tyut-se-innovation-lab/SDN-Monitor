<template>
  <div class="box">
    <div v-for="(item, index) in attackData" class="ring">
      <h3>
        <b>{{ item.name }}攻击比率</b>
      </h3>
      <div :id="`ring${index}`"></div>
    </div>
  </div>
</template>

<script>
import attackRank from "@/api/home/attack.js";
import Ring from "@/api/charts/ring/index.js";
export default {
  name: "ring",
  props: ["value", "name"],
  data() {
    return {
      attackData: [],
      ring: [],
    };
  },
  methods: {
    init() {
      for (let i = 0; i < this.attackData.length; i++) {
        this.ring[i] = new Ring(this.name, `ring${i}`, {
          width: 300,
          height: 300,
        }).init(this.attackData[i].value, this.attackData[i].name);
        // console.log(this)
      }
    },
  },
  mounted() {
    this.attackData = attackRank();
    setTimeout(() => {
      this.init();
    }, 500);
  },
};
</script>

<style scoped>
.box {
  display: flex;
}
.ring {
  width: 345px;
  height: 380px;
  position: relative;
  background: #ffffff;
  border: 6px solid #ffffff;
  margin: 10px;
  margin-left: 20px;
  box-shadow: -8px -8px 15px rgba(255, 255, 255, 1),
    8px 8px 25px rgba(0, 0, 0, 0.15);
}
#ring0 {
  width: 300px;
  height: 300px;
  margin: 0 auto;
}
#ring1 {
  width: 300px;
  height: 300px;
  margin: 0 auto;
}
</style>