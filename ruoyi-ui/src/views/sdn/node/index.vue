<template>
  <div>
    <div class="select">
      <el-select v-model="isSwitch" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>

    <SwitchBoard v-if="isSwitch" />
    <Host v-else />
  </div>
</template>

<script>
import SwitchBoard from "./switchboard.vue";
import Host from "./host.vue";
export default {
  name: "node",
  data() {
    return {
      isSwitch: false,
      options: [
        {
          label: "交换机",
          value: true,
        },
        {
          label: "主机",
          value: false,
        },
      ],
    };
  },
  components: {
    SwitchBoard,
    Host,
  },
  methods: {},
  mounted() {},
};
</script>

<style scoped>
.select {
  margin: 20px;
}
</style>