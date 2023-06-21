<template>
  <div>
    <el-table :data="switchData" style="width: 100%">
      <el-table-column prop="switchId" label="交换机ID"> </el-table-column>
      <el-table-column prop="hardAddress" label="硬件地址"> </el-table-column>
      <el-table-column prop="number" label="数量"> </el-table-column>
      <el-table-column prop="portId" label="端口Id"> </el-table-column>
      <el-table-column prop="portName" label="端口名字" width="100%">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import WebsocketLink from "@/api/webscoket/index.js";
import handlerData from "@/api/node/index.js";
export default {
  name: "node",
  data() {
    return {
      url: "/device",
      protol: "getSwitch",
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

        this.switchData = handlerData(data);
      } catch (e) {}
    },
    sendWs() {
      this.wsInstance.sendWs({});
    },
  },
  mounted() {
    this.init();
  },
  destroy() {
    //关闭webscoket
    this.wsInstance.close();
  },
};
</script>

<style>
</style>