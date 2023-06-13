<template>
  <div id="portbyte"></div>
</template>

<script>
import WebsocketLink from "@/api/webscoket/index.js";
import Line from "@/api/charts/line/index.js";
export default {
  name: "node",
  data() {
    return {
      url: "/traffic",
      protol: "getPortByteRate",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
      wsInstance: "",
      lineData: [],
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
        this.setLineEcharts(this.lineData);
      } catch (e) {}
    },
    setLineEcharts(data) {
      new Line("端口输入输出字节速率", "portbyte", data).init();
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
#portbyte {
  width: 600px;
  height: 600px;
  margin: 0 auto;
}
</style>