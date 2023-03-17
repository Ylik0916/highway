<template>
  <div v-if="message">
    <el-row>
      <h3 style="font-weight: bold">病害详情</h3>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>所属路线:</span><span>{{message.pathName}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>中心桩号:</span><span>{{message.diseaseStake}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>上报时间:</span><span>{{message.reportDate}}</span></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content"><span>病害经度:</span><span>{{message.diseaseLongitude}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>病害纬度:</span><span>{{message.diseaseLatitude}}</span></div></el-col>
      <el-col :span="8"><div class="grid-content"><span>病害信息:</span><span>{{message.diseaseMessage}}</span></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content" style="display: flex; align-items: center;"><span>病害图片:</span><span>
          <image-preview :src="message.diseaseImg" :width="50" :height="50"/>
      </span></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
      <el-col :span="8"><div class="grid-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Dinfomatio",
  props: ['message'],
  data() {
    return {
    }
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
