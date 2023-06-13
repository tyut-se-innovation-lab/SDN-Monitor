<template>
  <div>
    <div id="relate"></div>
    <button @click="sendWs" class="send">
      <svg
        t="1686576109286"
        class="icon"
        viewBox="0 0 1024 1024"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        p-id="2373"
        width="20"
        height="20"
      >
        <path
          d="M347.648 841.376c42.24 19.392 89.248 30.208 138.752 30.208 183.808 0 332.8-148.992 332.8-332.8 0-68.096-20.448-131.424-55.552-184.16l73.504-73.504C890.24 353.248 921.6 442.368 921.6 538.784c0 240.352-194.848 435.2-435.2 435.2-67.424 0-131.264-15.328-188.224-42.688l7.328 27.392c7.328 27.328-8.896 55.392-36.192 62.72s-55.392-8.896-62.72-36.192l-39.744-148.352c-7.328-27.328 8.896-55.392 36.192-62.72L351.392 734.4c27.328-7.328 55.392 8.896 62.72 36.192s-8.896 55.392-36.192 62.72l-30.272 8.128zM589.28 115.84l-21.056-36.448c-14.144-24.48-5.76-55.808 18.752-69.952s55.808-5.76 69.952 18.752l76.8 133.024c14.144 24.48 5.76 55.808-18.752 69.952l-133.024 76.8c-24.48 14.144-55.808 5.76-69.952-18.752s-5.76-55.808 18.752-69.952l14.08-8.128a334.88 334.88 0 0 0-58.432-5.12c-183.808 0-332.8 148.992-332.8 332.8 0 40.64 7.296 79.616 20.64 115.616l-77.792 77.792C67.488 673.952 51.2 608.288 51.2 538.816c0-240.352 194.848-435.2 435.2-435.2 35.424 0 69.888 4.224 102.88 12.224z"
          p-id="2374"
        ></path>
      </svg>
    </button>
  </div>
</template>

<script>
import Relate from "@/api/charts/relate/index.js";
import Data from "@/api/charts/relate/data.js";
import Graph from "@/api/charts/relate/graph.js";
import WebsocketLink from "@/api/webscoket/index.js";
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
    /**
     * 初始化
     */
    init() {
      this.initWebSocket();
      setTimeout(() => {
        this.sendWs();
      }, 200);
    },
    /**
     * 初始化websocket
     */
    initWebSocket() {
      this.wsInstance = new WebsocketLink(this.url, this.protol, this.wsData);
      this.ws = this.wsInstance.init();
      this.ws.onmessage = this.wstOnMessage;
    },
    /**
     * websocket发送信息
     */
    wstOnMessage(MessageEvent) {
      let data = MessageEvent.data;
      try {
        let dataHandler = new Data(JSON.parse(JSON.parse(data)));
        let objArr = dataHandler.getPointNum();
        let pointData = objArr[0];
        let nodeInfo = objArr[1];
        dataHandler.linkRemoveDuplicates();
        let link = dataHandler.getSide();
        let graph = new Graph(pointData, 100, 200, 200);
        graph.init();
        let point = graph.pointsCoordinate;
        dataHandler.switchData(point, nodeInfo);

        let node = dataHandler.flatteningData(nodeInfo);
        // console.log(link, node);
        new Relate("玫瑰图", "relate", { node, link }).init();
      } catch {
        console.log("数据不符,解析错误");
      }
    },
    /**
     * 发送ws请求
     */
    sendWs() {
      this.wsInstance.sendWs({});
    },
  },
  mounted() {
    this.init();
  },
};
</script>

<style>
#relate {
  width: 650px;
  height: 650px;
  padding: 20px;
  margin: 0 auto;
}
.send {
  width: 60px;
  height: 50px;
  position: absolute;
  bottom: 10%;
  right: 0;
  left: 0;
  margin: 0 auto;
  border-radius: 5px;
  border: solid 1px;
  background-color: white;
}
</style>