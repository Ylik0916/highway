<template>
<div class="body_">
  <div style="display: flex" class="body2">
    <ul style="list-style: none;" >
      <li>路线编码 : {{this.form.routeCoding}}</li>
      <li>行政区域 : {{this.form.adminiStrative}}</li>
      <li>建设类型 : {{this.form.typeConstruction}}</li>
      <li>路线里程 : {{this.form.routeMileage}}</li>
      <li>路线类型 : {{this.form.routeType}}</li>
    </ul>
    <ul style="list-style: none;"  class="ul23">
      <li>起点名称 : {{this.form.startingPointname}}</li>
      <li>终点名称 : {{this.form.endPointName}}</li>
      <li>起点经度 : {{this.form.startingPointLongitude}}</li>
      <li>终点经度 : {{this.form.endPointLongitude}}</li>
      <li>起点纬度 : {{this.form.startLatitude}}</li>

    </ul>
    <ul style="list-style: none;" class="ul23" >
      <li>终点纬度 : {{this.form.endLatitude}}</li>
      <li>起点桩号 : {{this.form.startingPointStation}}</li>
      <li>终点桩号 : {{this.form.endPointStation}}</li>
      <li>路段数量 : {{this.form.numberRoadSegments}}</li>
    </ul>
  </div>
  <div class="body3">
  <el-tabs v-model="activeName" type="card" style="width: 100%" @tab-click="handleClick" tab-position="top">
    <el-tab-pane label="路段信息" name="first"><section-information></section-information></el-tab-pane>
    <el-tab-pane label="桥梁信息" name="second">桥梁信息</el-tab-pane>
    <el-tab-pane label="路资路产" name="third">路资路产</el-tab-pane>
    <el-tab-pane label="隧道信息" name="fourth"><Tunnel></Tunnel></el-tab-pane>
    <el-tab-pane label="病害信息" name="firth">病害信息</el-tab-pane>
  </el-tabs>
  </div>
</div>

</template>

<script>
import {getInformation} from "@/api/system/information";
import SectionInformation from "@/views/system/sectionInformation";
import Tunnel from "@/views/system/tunnel";
export default {
  name: "index_",
  components: {SectionInformation,Tunnel},
  dicts: ['construction_type', 'route_type'],
  data(){
    return {
      form:[],
      activeName: 'first',
      rules: {
      }
    }
  },
  methods:{
    /**
     * 标签页的点击事件
     * @param tab
     * @param event
     */
    handleClick(tab, event) {
      console.log(tab, event);
    },
    /** 修改按钮操作 */
    handleUpdate(row){
      getInformation(row).then(response => {
        this.form = response.data;
      });
    }
  },
  mounted() {
    console.log(this.$route.query.id)
    this.handleUpdate(this.$route.query.id)
  }
}
</script>
<style scoped>
.body2 .ul23{
  margin-left: 150px;
}
.body2{
  background-color: white;
  box-shadow: #97a8be;
  border-radius: 10px;
  width: 91%;
  margin: 30px auto auto;
  font-size: 18px;
}
.body3{
  background-color: white;
  margin: 30px auto auto;
  font-size: 18px;
  box-shadow: #97a8be;
  width: 91%;
  display: flex;
  border-radius: 10px;
}
.body_{
  background-color: #ededf7;
  border: 1px #ededf7 ridge;
}
li{
  margin-top: 15px;

}
</style>
