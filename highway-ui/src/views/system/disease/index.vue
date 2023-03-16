<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="病害详情" name="Info"/>
      <el-tab-pane label="待上报" name="Reported"/>
      <el-tab-pane label="待处置" name="Dispose"/>
      <el-tab-pane label="待养护" name="Conserve"/>
    </el-tabs>
    <transition>
      <component :is="activeName"></component>
    </transition>
  </div>
</template>
<script>
import Reported from "@/views/system/disease/reported";
import Info from "@/views/system/disease/info";
import Dispose from "@/views/system/disease/dispose";
import Conserve from "@/views/system/disease/conserve";
export default {
  name:"Disease",
  data() {
    return {
      activeName: 'Info',
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  },
  components: {
    Reported,
    Info,
    Dispose,
    Conserve
  }
};
</script>
