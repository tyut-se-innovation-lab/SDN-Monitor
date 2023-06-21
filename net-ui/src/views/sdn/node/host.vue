<template>
  <div>
    <el-table :data="hostData" style="width: 100%">
      <el-table-column prop="apId" label="日期"> </el-table-column>
      <el-table-column prop="hostId" label="姓名"> </el-table-column>
      <el-table-column prop="ip" label="ip地址"> </el-table-column>
      <el-table-column prop="mac" label="物理地址"> </el-table-column>
      <el-table-column prop="tpId" label="地址"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
import WebsocketLink from "@/api/webscoket/index.js";
export default {
  name: "node",
  data() {
    return {
      hostData: [],
      url: "/device",
      protol: "getHost",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
      wsInstance: "",
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
        this.hostData = data;
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
    this.wsInstance.close();
  },
};
</script>

<style>
</style>