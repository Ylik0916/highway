<template>
  <div>
    <div class="bigBox">
    <div style="display: flex">
    <div id="left" :style="{width:'720px',height:'400px'}" class="smallBox"></div>
    <div id="right" :style="{width:'520px',height:'400px'}" class="smallBox"></div>
    </div>
    <div style="display: flex" >
    <div id="xLeft" :style="{width:'520px',height:'400px'}" class="smallBox"></div>
    <div id="xRight" :style="{width:'720px',height:'400px'}" class="smallBox"></div>
    </div>
    </div>
  </div>
</template>
<script>
import {routeMileage, routeType} from "@/api/system/information";
import {diseaseCount, routeDisease} from "@/api/system/disease";

export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.4",
    };
  },
  mounted() {
    this.getRouteDisease(),
    this.getDiseaseCount();
    this.getRouteMileage();
    this.getRouteType();
  },
  methods: {
    getRouteDisease(){
      routeDisease().then(resp=>{
        let myChart = this.$echarts.init(document.getElementById('xRight'));
        myChart.setOption({
          title: {
            text: '病害高发信息统计',
            left: 'center'
          },
          xAxis: {
            type: 'category',
            data: resp.name
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              itemStyle : { normal: {label : {show: true}}},
              data: resp.value,
              type: 'line',
              smooth: true
            }
          ]
        })
      })
    },
    getDiseaseCount(){
      diseaseCount().then(resp=>{
        var sum;
        for (let i = 0; i < resp.length; i++) {
          sum+=Number.parseInt(resp[i].value)
        }
        let myChart = this.$echarts.init(document.getElementById('xLeft'));
        myChart.setOption({
          title: {
            text: '病害数量统计',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: 'right'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 40,
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: resp
            }
          ]
        })
      })
    },
    getRouteMileage(){
      routeMileage().then(resp=>{
        let myChart = this.$echarts.init(document.getElementById('left'));
        myChart.setOption({
          title: {
            text: '道路里程统计',
            left: 'center'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },

          xAxis: [
            {
              type: 'category',
              data: resp.name,
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          itemStyle:{
            color: '#2f4554'
          },
          yAxis: [
            {
              type: 'value'
            },
          ],
          series: [
            {
              name: 'Direct',
              type: 'bar',
              barWidth: '60%',
              data: resp.value
            }
          ]
        })
      });
    },
    getRouteType(){
      routeType().then(resp=>{
        let myChart = this.$echarts.init(document.getElementById('right'));
        myChart.setOption({
          title: {
            text: '路线类型统计',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: '50%',
              data: resp,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        })
      })
    },
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">

</style>

