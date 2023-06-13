<template>
  <div id="traffic">
    <Byte />
    <Pack />
    <PortByte />
    <PortPack />
  </div>
</template>

<script>
import Byte from "./byte/index.vue";
import Pack from "./pack/index.vue";
import PortByte from "./portbyte/index.vue";
import PortPack from "./portpack/index.vue";

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
  },
  mounted() {},
};
</script>

<style>
</style>