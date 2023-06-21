<template>
  <div class="state">
    <el-table :data="attackData" style="width: 100%" height="350">
      <el-table-column prop="id" label="ID" width="65"> </el-table-column>
      <el-table-column prop="time" label="时间" width="185"> </el-table-column>
      <el-table-column prop="info" label="危害信息" width="175">
      </el-table-column>
      <el-table-column prop="ip" label="IP地址" width="165"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
import generateData from "@/api/home/index.js";
export default {
  name: "state",
  data() {
    return {
      attackData: [],
    };
  },
  computed: {
    topAttribute(index) {
      setTimeout(() => {
        return `top: ${(index + 1) * 50} + 'px'`;
      }, 50);
    },
  },
  methods: {
    format() {
      return "";
    },
    sortData() {
      this.attackData.sort((a, b) => {
        return b.percent - a.percent;
      });
    },
    modify() {
      this.attackData[1].percent = 65;
      this.attackData[2].percent = 78;
      sortData();
    },
    add() {
      this.attackData[this.attackData.length] = {
        name: "6",
        percent: 99,
        id: 6,
      };
      sortData();
    },
  },
  mounted() {
    this.sortData();
    this.attackData = generateData();
    console.log(this.attackData);
  },
};
</script>

<style scoped>
.state {
  width: 630px;
  padding: 10px;
  height: 400px;
  position: relative;
  position: relative;
  background: #ffffff;
  border: 6px solid #ffffff;
  margin: 10px;
  margin-left: 10px;
  box-shadow: -8px -8px 15px rgba(255, 255, 255, 1),
    8px 8px 25px rgba(0, 0, 0, 0.15);
}
.head {
  width: 200px;
  display: flex;
  justify-content: space-between;
  font-size: 15px;
}
.part {
  margin-top: 10px;
  transition: top 1s;
  position: absolute;
  z-index: 0;
}
.progress {
  width: 210px;
}
.state div:nth-child(3) {
  z-index: -5;
}
</style>