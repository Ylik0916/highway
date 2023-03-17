<template>
  <div class="bigBox">
  <div class="smallBox">
    <el-tabs v-model="activeName" type="card" style="width: 100%" @tab-click="handleClick" tab-position="top">
      <el-tab-pane label="乡镇" name="first"><Township></Township></el-tab-pane>
      <el-tab-pane label="建制村" name="second"><Village></Village></el-tab-pane>
      <el-tab-pane label="现代产业园" name="third"><Industrial></Industrial></el-tab-pane>
      <el-tab-pane label="标识标牌" name="fourth"><Signage></Signage></el-tab-pane>
    </el-tabs>
  </div>
  </div>
</template>

<script>
import Township from "@/views/system/township"
import Village from "@/views/system/village";
import Signage from "@/views/system/signage";
import Industrial from "@/views/system/industrial";
export default {
  name: "index",
  components: {Industrial, Signage, Village, Township},
  data(){
    return {
      activeName: 'first',
    }
  },
  methods:{
    /**
     * 标签页的点击事件
     * @param tab
     * @param event
     */
    handleClick() {

    },

  }
}
</script>

