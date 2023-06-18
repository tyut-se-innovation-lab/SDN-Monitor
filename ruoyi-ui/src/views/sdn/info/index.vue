<template>
  <div id="traffic">
    <div class="graph1"><Byte /><PortByte /></div>
    <div class="graph2"><Topolopy /></div>
    <div class="graph3"><Pack /><PortPack /></div>
  </div>
</template>

<script>
import Byte from "@/views/sdn/traffic/byte/index.vue";
import Pack from "@/views/sdn/traffic/pack/index.vue";
import PortByte from "@/views/sdn/traffic/portbyte/index.vue";
import PortPack from "@/views/sdn/traffic/portpack/index.vue";
import Topolopy from "@/views/sdn/topology/index.vue";

export default {
  name: "traffic",
  data() {
    return {};
  },
  components: {
    Byte,
    Pack,
    PortByte,
    PortPack,
    Topolopy,
  },
  methods: {
    // gotoTopology,
  },
};
</script>

<style scoped>
#traffic {
  display: flex;
  flex-wrap: wrap;
}
</style>