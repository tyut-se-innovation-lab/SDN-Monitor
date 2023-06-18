<template>
  <div class="state">
    <h3><b>链路带宽状态</b></h3>
    <!-- <div
      v-for="(item, index) in attackData"
      class="part"
      :style="{ top: (index + 1) * 50 + 'px' }"
      :key="item.id"
    > -->
    <div v-for="(item, index) in attackData" class="part" :key="item.id">
      <div class="head">
        <p>{{ item.id }} {{ item.name }}</p>
        <p>{{ item.percent }}%</p>
      </div>
      <el-progress
        :percentage="item.percent"
        :text-inside="true"
        :format="format"
        class="progress"
      />
    </div>
  </div>
</template>

<script>
import WebsocketLink from "@/api/webscoket/index.js";
export default {
  name: "link1",
  data() {
    return {
      attackData: [],
      swInstance: "",
      sw: "",
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
    getSwitchInfo() {
      this.swInstance = new WebsocketLink("/device", "getSwitch", "");
      this.sw = this.swInstance.init();
      this.sw.onmessage = function (MessageEvent) {
        try {
          let data = JSON.parse(MessageEvent.data);
          data.map((value) => {
            value.ports.map((item) => {
              if (item.portId !== `${value.switchId}:LOCAL`) {
                let obj = {
                  name: item.portName,
                  percent: Math.floor(Math.random() * 100),
                };
                this.attackData.push(obj);
              }
            });
          });
        } catch (e) {}
        this.sortData();
        let n = 0;
        let arr = [];
        this.attackData.map((value) => {
          value.id = `No.${n + 1}`;

          if (n < 10) {
            arr[n] = value;
          }
          n++;
        });
        this.attackData = arr;
      }.bind(this);

      setTimeout(() => {
        this.swInstance.sendWs({});
      }, 500);
    },
  },
  mounted() {
    this.sortData();
    this.getSwitchInfo();
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
.state {
  width: 300px;
  position: relative;
  background: #ffffff;
  border: 6px solid #ffffff;
  margin: 10px;
  margin-left: 20px;
  box-shadow: -8px -8px 15px rgba(255, 255, 255, 1),
    8px 8px 25px rgba(0, 0, 0, 0.15);
}
.state h3 {
  margin-top: 10px;
  margin-bottom: 30px;
}
.head {
  width: 280px;
  display: flex;
  justify-content: space-between;
  font-size: 15px;
}
.part {
  margin-bottom: 40px;
  transition: top 1s;
  z-index: 0;
}
.progress {
  width: 280px;
}
.state div:nth-child(3) {
  z-index: -5;
}
</style>