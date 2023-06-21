<template>
  <!-- 水球百分比 -->
  <div class="box">
    <h3><b>链路实际使用情况</b></h3>
    <h4>
      带宽速率<span style="font-size: 25px; font-weight: 600">{{ rate }}</span
      >Mbps
    </h4>
    <div id="circlepercent"></div>
  </div>
</template>

<script>
import WebsocketLink from "@/api/webscoket/index.js";
import Percent from "@/api/charts/percent/index.js";
export default {
  name: "percent",
  data() {
    return {
      url: "/traffic",
      protol: "getAllBandWidthProportion",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      params: {
        title: "水球百分比",
        containerId: "circlepercent",
        data: "",
      },
      ws: "",
      metricValue: 0,
      circle: "",
      wsInstance: "",
      rate: 700,
    };
  },
  methods: {
    init() {
      this.initWebSocket();
      this.circle = new Percent(this.params);
      this.circle.init(this.metricValue);
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
        this.rate = (data.rate / 1000000).toFixed(2);
        if (data.metricValue !== 0) {
          console.log(data.metricValue);
          this.circle.init(data.metricValue.toFixed(2));
        }
      } catch (e) {
        // console.log(e);
        let obj = { metricValue: 0.0, rate: 0.0 };

        console.log(JSON.stringify(obj));
      }
    },
    setLineEcharts(data) {
      if (!this.line) {
        this.line = new Line("全局包速率", "byte", data).init(data);
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
    // new Percent(this.params).init(0.54);
  },
};
</script>

<style scoped>
.box {
  width: 250px;
  height: 400px;
  background: #ffffff;
  border: 6px solid #ffffff;
  margin: 10px;
  margin-left: 20px;
  box-shadow: -8px -8px 15px rgba(255, 255, 255, 1),
    8px 8px 25px rgba(0, 0, 0, 0.15);
}
#circlepercent {
  width: 200px;
  height: 200px;
  margin: 0 auto;
}
.box h3 {
  width: 250px;
  margin: 20px auto;
  text-align: center;
}
.box h4 {
  width: 250px;
  margin: 20px auto;
  text-align: center;
}
</style>