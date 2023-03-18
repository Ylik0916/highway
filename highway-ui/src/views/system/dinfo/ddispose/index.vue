<template>
  <div v-if="message" style="border-top: 1px solid darkgray;">
    <el-row>
      <h3 style="font-weight: bold">处置信息</h3>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>管养单位:</span><span>{{message.maintainUnit}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>行驶方向:</span><span>{{message.drivingDirection}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>损坏情况:</span><span>{{message.damageCase}}</span></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>横向位置:</span><span>{{message.horizontalPositions}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>处置措施:</span><span>{{message.disposeMeasure}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>预估信息:</span><span>{{message.forecastCase}}</span></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>备注:</span><span>{{message.disposeRemark}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content" style="display: flex; align-items: center;">
        <span>处置图片:</span>
        <span>
          <image-preview :src="message.disposeImg" :width="50" :height="50"/></span></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "Ddispose",
  props: ['message'],
  data() {
    return {}
  },
  created() {
  },
  methods: {}
};
</script>
<style>
.el-row {
  margin-bottom: 10px;
}
.el-col {
  border-radius: 4px;
}
.grid-content span:nth-child(1){
  margin-right: 0.5em;
}
.grid-content {
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
}
</style>
