<template>
  <div id="byte"></div>
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
      }, 400);
    },
    initWebSocket() {
      if (!this.wsInstance) {
        console.log(this.wsInstance);
        this.wsInstance = new WebsocketLink(this.url, this.protol, this.wsData);
        this.ws = this.wsInstance.init();
        this.ws.onmessage = this.wstOnMessage;
      }
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
          width: 600,
          height: 400,
        });
      }

      this.line.init(data);
    },
    sendWs() {
      this.wsInstance.sendWs({});
    },
  },
  mounted() {
    this.init();
  },
  beforeDestroy() {
    this.wsInstance.close();
  },
};
</script>

<style scoped>
#byte {
  width: 600px;
  height: 400px;
  margin: 0 auto;
}
</style>