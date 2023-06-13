<template>
  <div>
    <!-- <el-select v-model="isSwitch" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option> -->
    <div id="portpack"></div>
  </div>
</template>

<script>
import WebsocketLink from "@/api/webscoket/index.js";
import Line from "@/api/charts/line/index.js";
export default {
  name: "node",
  data() {
    return {
      url: "/traffic",
      protol: "getPortPackRate",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
      wsInstance: "",
      switchData: [],
    };
  },
  methods: {
    init() {
      this.initWebSocket();
      setTimeout(() => {
        this.sendWs();
      }, 500);
    },
    initWebSocket() {
      this.wsInstance = new WebsocketLink(this.url, this.protol, this.wsData);
      this.ws = this.wsInstance.init();
      this.ws.onmessage = this.wstOnMessage;
    },
    wstOnMessage(MessageEvent) {
      try {
        let data = JSON.parse(MessageEvent.data);
        // console.log(data);
        this.lineData.push([new Date(), data.metricValue]);
        let last = new Date().getTime();
        let first = new Date(this.lineData[0][0]).getTime();
        if (last - first > 300000) {
          this.lineData.shift();
        }
        this.setLineEcharts(this.lineData);
      } catch (e) {}
    },
    setLineEcharts(data) {
      new Line("端口输入输出包速率", "portpack", data).init();
    },
    sendWs() {
      // this.wsInstance.sendWs({"s1-eth3"});
    },
  },
  mounted() {
    this.init();
  },
  destroy() {
    this.wsInstance.close();
  },
};
</script>

<style scoped>
#portpack {
  width: 1000px;
  height: 600px;
  margin: 0 auto;
}
</style>