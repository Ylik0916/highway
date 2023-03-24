<template>
    <div id="container"></div>
</template>
<script>
import AMapLoader from '@amap/amap-jsapi-loader';

window._AMapSecurityConfig = {
  // 安全密钥
  securityJsCode: "b1aaae6809cb12e3996c66188565c767",
};

export default {
    name: 'MapContainer',
    props: ['getMapCode'],
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
        }
    },
    methods:{
        initMap(){
            AMapLoader.load({
                key:"e896c26ae06f407a927dad5e14a0feb0",             // 申请好的Web端开发者Key，首次调用 load 时必填
                version:"2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
                plugins:["AMap.Geocoder", "AMap.AutoComplete"],       // 需要使用的的插件列表，如比例尺'AMap.Scale'等
            }).then((AMap)=>{
                this.map = new AMap.Map("container",{  //设置地图容器id
                    viewMode:"3D",    //是否为3D地图模式
                    zoom:10,           //初始化地图级别
                    // center:[105.602725,37.076636], //初始化地图中心点位置
                });
                //点击获取经纬度;
                this.map.on("click", (e) => {
                    // 获取经纬度
                    this.positionMap.lng = e.lnglat.lng;
                    this.positionMap.lat = e.lnglat.lat;
                    this.sendMap(this.positionMap.lng,this.positionMap.lat);

                    // 清除点
                    this.map.remove(this.marker);
                    // 标记点
                    // 自动适应显示想显示的范围区域
                    this.marker = new AMap.Marker({
                        position: new AMap.LngLat(this.positionMap.lng, this.positionMap.lat),
                    });
                    this.map.add(this.marker);
                    this.$parent.$parent.closeMap();
                });
            }).catch(e=>{
                console.log(e);
            })
        },
        // 标记点
        sendMap(lng,lat) {
          this.getMapCode(lng,lat);
        },
    },
    mounted(){
        //DOM初始化完成进行地图初始化
        this.initMap();
    }
}
</script>
<style  scoped>
    #container{
        padding:0px;
        margin: 0px;
        width: 100%;
        height: 700px;
    }
</style>
