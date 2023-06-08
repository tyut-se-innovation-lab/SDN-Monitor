<template>
  <div>
    <button @click="sendWs">发消息</button>
  </div>
</template>

<script>
import webscoketLink from "@/api/test/wsocket/index.js";
export default {
  name: "test",
  data() {
    return {
      url: "/device",
      protol: "getTopology",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
    };
  },
  methods: {
    sendWs() {
      this.ws.sendWs();
    },
  },
  mounted() {
    this.ws = new webscoketLink(this.url, this.protol, this.wsData);
    this.ws.init();
  },
};
</script>

<style>
</style>