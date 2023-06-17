<template>
  <div class="title">
    <h1>
      <b>{{ title }}</b>
    </h1>
    <div class="box">
      <p>
        主机数<span style="font-size: 25px; font-weight: 600">{{
          hostNumber
        }}</span
        >台
      </p>
      <p>
        交换机数<span style="font-size: 25px; font-weight: 600">{{
          switchNumber
        }}</span
        >台
      </p>
      <p>
        链路数<span style="font-size: 25px; font-weight: 600">{{
          linkNumber
        }}</span
        >条
      </p>
    </div>
  </div>
</template>

<script>
import Data from "@/api/charts/relate/data.js";
import WebsocketLink from "@/api/webscoket/index.js";
export default {
  name: "relate",
  data() {
    return {
      title: "OpenDaylight的流量监控及调度与深度学习网络入侵检测",
      url: "/device",
      protol: "getTopology",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
      wsInstance: "",
      hostNumber: 0,
      switchNumber: 0,
      linkNumber: 0,
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
        //数据处理
        let dataHandler = new Data(JSON.parse(JSON.parse(data)));
        let objArr = dataHandler.getPointNum();
        let pointData = objArr[0];
        dataHandler.linkRemoveDuplicates();
        let link = dataHandler.getSide();
        this.linkNumber = link.length;
        this.switchNumber = Object.keys(pointData).length;
        Object.keys(pointData).map((value) => {
          this.hostNumber += pointData[value];
        });
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

<style scoped>
* {
  margin: 0;
  padding: 0;
}
.title h1 {
  width: 800px;
  text-align: center;
  margin: 0 auto;
}
.title {
  width: 100%;
  margin: 10px 0;
  padding: 0;
}
.box {
  display: flex;
  justify-content: space-around;
}
p {
  width: 100px;
  margin-top: 10px;
  text-align: center;
}
</style>