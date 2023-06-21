<template>
  <div class="box">
    <div id="byte"></div>
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
      protol: "getAllByteRate",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
      wsInstance: "",
      line: "",
      lineData: {
        arr1: [],
      },
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
        this.lineData.arr1.push([new Date(), data.metricValue]);
        let last = new Date().getTime();
        let first = new Date(this.lineData.arr1[0][0]).getTime();
        if (last - first > 80000) {
          this.lineData.arr1.shift();
        }
        this.setLineEcharts(this.lineData);
      } catch (e) {}
    },
    setLineEcharts(data) {
      if (!this.line) {
        this.line = new Line("全局字节速率", "byte", {
          width: 660,
          height: 400,
        });
        this.line.init(data);
      } else {
        this.line.init(data);
      }
    },
    sendWs() {
      this.wsInstance.sendWs({});
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
.box {
  width: 680px;
}
#byte {
  width: 630px;
  height: 380px;
  margin: 0 auto;
  position: relative;
  background: #ffffff;
  border: 6px solid #ffffff;
  margin: 10px;
  margin-left: 20px;
  box-shadow: -8px -8px 15px rgba(255, 255, 255, 1),
    8px 8px 25px rgba(0, 0, 0, 0.15);
}
</style>