<template>
  <div>
    <div id="relate"></div>
    <button @click="sendWs">发送信息</button>
  </div>
</template>

<script>
import Relate from "@/api/charts/relate/index.js";
import Data from "@/api/charts/relate/data.js";
import Graph from "@/api/charts/relate/graph.js";
import WebsocketLink from "@/api/test/wsocket/index.js";
export default {
  name: "relate",
  data() {
    return {
      url: "/device",
      protol: "getTopology",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
      wsInstance: "",
    };
  },
  methods: {
    init() {},
    initWebSocket() {
      this.wsInstance = new WebsocketLink(this.url, this.protol, this.wsData);
      this.ws = this.wsInstance.init();
      this.ws.onmessage = this.wstOnMessage;
    },
    wstOnMessage(MessageEvent) {
      let data = MessageEvent.data;
      try {
        let dataHandler = new Data(JSON.parse(JSON.parse(data)));
        let objArr = dataHandler.getPointNum();
        let pointData = objArr[0];
        let nodeInfo = objArr[1];
        dataHandler.linkRemoveDuplicates();
        let link = dataHandler.getSide();
        console.log(pointData);
        let graph = new Graph(pointData, 100, 200, 200);
        graph.init();
        let point = graph.pointsCoordinate;
        console.log(point, nodeInfo);
        dataHandler.switchData(point, nodeInfo);

        let node = dataHandler.flatteningData(nodeInfo);
        console.log(link, node);
        new Relate("玫瑰图", "relate", { node, link }).init();
      } catch {
        console.log("数据不符,解析错误");
      }
    },
    sendWs() {
      this.wsInstance.sendWs({});
    },
  },
  mounted() {
    this.initWebSocket();
    setTimeout(() => {
      this.sendWs();
    }, 500);
  },
};
</script>

<style>
#relate {
  width: 650px;
  height: 650px;
  margin: 0 auto;
}
</style>