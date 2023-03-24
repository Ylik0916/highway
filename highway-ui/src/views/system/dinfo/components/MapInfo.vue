<template>
  <div style="width: 100%;height: 100%;padding: 10px 0">
    <div id="container" style="height: 100%;"></div>
  </div>
</template>
<script>
import AMapLoader from '@amap/amap-jsapi-loader';

window._AMapSecurityConfig = {
  // 安全密钥
  securityJsCode: "b1aaae6809cb12e3996c66188565c767",
};

export default {
  name: 'MapInfo',
  props: ['items'],
  data(){
    return{
      //此处不声明 map 对象，可以直接使用 this.map赋值或者采用非响应式的普通对象来存储。
      // 地图实例
      map: null,
      // 标记点
      marker: "",
      // 位置信息
      positionMap: {
        lng: "",
        lat: "",
        address: "",
        //地区编码
        adcode: "",
      },
      disease: null
    }
  },
  methods:{
    initMap(){
      if (this.items){
        AMapLoader.load({
          key:"e896c26ae06f407a927dad5e14a0feb0",             // 申请好的Web端开发者Key，首次调用 load 时必填
          version:"2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        }).then((AMap)=>{
          this.map = new AMap.Map("container",{  //设置地图容器id
            viewMode:"3D",    //是否为3D地图模式
            zoom:10,           //初始化地图级别
            center:[this.items.diseaseLongitude,this.items.diseaseLatitude], //初始化地图中心点位置
          });
        }).catch(e=>{
          console.log(e);
        })
      }
    },
  },
  mounted(){
    //DOM初始化完成进行地图初始化
    this.initMap();
  }
}
</script>

