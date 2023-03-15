<template>
  <div class="body2">
  <div class="body3">
    <el-tabs v-model="activeName" type="card" style="width: 100%" @tab-click="handleClick" tab-position="top">
      <el-tab-pane label="乡镇" name="first"><Township></Township></el-tab-pane>
      <el-tab-pane label="建制村" name="second">桥梁信息</el-tab-pane>
      <el-tab-pane label="现代产业园" name="third">路资路产</el-tab-pane>
      <el-tab-pane label="标识标牌" name="fourth">隧道信息</el-tab-pane>
    </el-tabs>
  </div>
  </div>
</template>

<script>
import {getInformation} from "@/api/system/information";
import Township from "@/views/system/township"

export default {
  name: "index",
  components: {Township},
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
      console.log(123)
    },

  }
}
</script>

