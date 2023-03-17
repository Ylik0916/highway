<template>
  <div v-if="message">
    <el-row>
      <h3 style="font-weight: bold">养护信息</h3>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>养护人:</span><span>{{message.maintainPeople}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>开始时间:</span><span>{{message.beginTime}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>结束时间:</span><span>{{message.overTime}}</span></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>养护地点:</span><span>{{message.maintainSite}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>养护经费:</span><span>{{message.maintainFund}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>养护情况:</span><span>{{message.maintainCase}}</span></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>备注:</span><span>{{message.maintainRemark}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content" style="display: flex; align-items: center;">
        <span>养护图片:</span>
        <span>
           <image-preview :src="message.maintainImg" :width="50" :height="50"/></span></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Dconserve",
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
