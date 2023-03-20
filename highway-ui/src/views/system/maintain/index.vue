<template>
  <div class="bigBox">
  <div class="smallBox">
    <el-tabs type="card" v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="一般养护" name="Ordinary"/>
      <el-tab-pane label="精品示范" name="Delicacy"/>
    </el-tabs>
    <transition>
      <component :is="activeName"></component>
    </transition>
  </div>
  </div>
</template>
<script>
import Delicacy from "@/views/system/maintain/delicacy";
import Ordinary from "@/views/system/maintain/ordinary";
export default {
  name: "Maintain",
  data() {
    return {
      activeName: 'Ordinary',
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  },
  components: {
    Delicacy,
    Ordinary
  }
};
</script>
