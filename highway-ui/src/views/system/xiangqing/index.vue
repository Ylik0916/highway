<template>
<div >
  <div style="font-size: 18px;font-family: 'Comic Sans MS',serif">
    <ul style="list-style: none;float: left;" >
      <li>路线编码 : {{this.form.routeCoding}}</li>
      <li>行政区域 : {{this.form.adminiStrative}}</li>
      <li>建设类型 : {{this.form.typeConstruction}}</li>
    </ul>
    <ul style="list-style: none;float: left" >
      <li>路线里程 : {{this.form.routeMileage}}</li>
      <li>路线类型 : {{this.form.routeType}}</li>
      <li>起点名称 : {{this.form.startingPointname}}</li>
    </ul>
    <ul style="list-style: none;float: left" >
      <li>终点名称 : {{this.form.endPointName}}</li>
      <li>起点经度 : {{this.form.startingPointLongitude}}</li>
      <li>终点经度 : {{this.form.endPointLongitude}}</li>
    </ul>
    <ul style="list-style: none;float: left" >
      <li>起点纬度 : {{this.form.startLatitude}}</li>
      <li>终点纬度 : {{this.form.endLatitude}}</li>
      <li>起点桩号 : {{this.form.startingPointStation}}</li>
    </ul>
    <ul style="list-style: none;float: left" >
      <li>终点桩号 : {{this.form.endPointStation}}</li>
      <li>路段数量 : {{this.form.numberRoadSegments}}</li>
    </ul>
  </div>
</div>
</template>

<script>
import {getInformation} from "@/api/system/information";

export default {
  name: "index_",
  dicts: ['construction_type', 'route_type'],

  data(){
    return {
      form:[],
      rules: {
      }
    }
  },
  methods:{
    /** 修改按钮操作 */
    handleUpdate(row){
      getInformation(row).then(response => {
        this.form = response.data;
      });
    }
  },
  mounted() {
    this.handleUpdate(this.$route.query.id)
  }
}
</script>
<style scoped>

li{
  margin-top: 15px;
  margin-left: 15px;

}
</style>
