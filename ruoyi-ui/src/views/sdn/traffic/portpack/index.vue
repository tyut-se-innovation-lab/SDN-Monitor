<template>
  <div>
    交换机：<el-select
      v-model="currentSwitch"
      placeholder="请选择"
      @change="changeSwitchPort"
    >
      <el-option
        v-for="item in switchs"
        :key="item"
        :label="item"
        :value="item"
      >
      </el-option>
    </el-select>
    端口：<el-select
      v-model="currentPort"
      placeholder="请选择"
      @change="changeSwitchPort"
    >
      <el-option v-for="item in ports" :key="item" :label="item" :value="item">
      </el-option>
    </el-select>
    <div id="portpack"></div>
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
      protol: "getPortPackRate",
      wsData: {
        token: "token-123456",
        data: "666",
      },
      ws: "",
      wsInstance: "",
      swInstance: "",
      switchData: {
        "openflow:1": [],
      },
      currentSwitch: "openflow:1",
      currentPort: "1",
      line: "",
      lineData: {
        in: [],
        out: [],
      },
    };
  },
  computed: {
    switchs() {
      return Object.keys(this.switchData);
    },
    ports() {
      let index = this.currentSwitch.split(":")[1];
      return this.switchData[Object.keys(this.switchData)[index]];
    },
  },
  methods: {
    init() {
      this.initWebSocket();
      this.getSwitchInfo();
      setTimeout(() => {
        this.sendWs({ port: "s1-eth1" });
      }, 700);
    },
    initWebSocket() {
      this.wsInstance = new WebsocketLink(this.url, this.protol, this.wsData);
      this.ws = this.wsInstance.init();
      this.ws.onmessage = this.wstOnMessage;
    },
    getSwitchInfo() {
      this.swInstance = new WebsocketLink("/device", "getSwitch", this.wsData);
      this.sw = this.swInstance.init();
      let obj = {};
      this.sw.onmessage = function (MessageEvent) {
        try {
          let data = JSON.parse(MessageEvent.data);

          data.map((value) => {
            value.ports.map((item) => {
              if (item.portId !== `${value.switchId}:LOCAL`) {
                if (!Array.isArray(obj[value.switchId]))
                  obj[value.switchId] = [];
                obj[value.switchId].push(item.number);
              }
            });
          });
          this.switchData = obj;
        } catch (e) {}
      }.bind(this);

      setTimeout(() => {
        this.swInstance.sendWs({});
      }, 700);
    },
    wstOnMessage(MessageEvent) {
      try {
        let data = JSON.parse(MessageEvent.data);
        if (data.direction === "in") {
          this.lineData.in.push([new Date(), data.metricValue]);
        } else if (data.direction === "out") {
          this.lineData.out.push([new Date(), data.metricValue]);
        }

        let last = new Date().getTime();
        let first = new Date(this.lineData.in[0][0]).getTime();
        if (last - first > 80000) {
          this.lineData.in.shift();
          this.lineData.out.shift();
        }
        this.setLineEcharts(this.lineData);
      } catch (e) {}
    },
    setLineEcharts(data) {
      if (!this.line) {
        this.line = new Line("端口包速率", "portpack", {
          width: 600,
          height: 400,
        });
      }

      this.line.init(data);
    },
    changeSwitchPort() {
      this.currentSwitchPort = `s${this.currentSwitch.split(":")[1]}-eth${
        this.currentPort
      }`;
      this.lineData.in = [];
      this.lineData.out = [];
      this.sendWs({ port: this.currentSwitchPort });
    },
    sendWs(data) {
      this.wsInstance.sendWs(data);
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
#portpack {
  width: 600px;
  height: 400px;
  margin: 0 auto;
}
</style>