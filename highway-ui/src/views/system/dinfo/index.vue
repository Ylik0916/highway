<template>
  <div class="bigBox">
  <div class="smallBox">
    <div style="padding: 0px 20px;" >
      <el-row :gutter="20">
        <el-col :span="15">
          <div class="grid-content">
            <div v-for="item in compentList">
              <component :is="item" :message="diseaseItem"></component>
            </div>
          </div>
        </el-col>
        <el-col :span="9">
          <div class="grid-content" style="height: 790px" v-if="this.diseaseItem">
            <MapInfo :items="diseaseItem"/>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
  </div>
</template>


<script>
import Dinfomatio from "@/views/system/dinfo/dinfomatio";
import Ddispose from "@/views/system/dinfo/ddispose";
import Dconserve from "@/views/system/dinfo/dconserve";
import {getDisease} from "@/api/system/disease";
import MapInfo from "@/views/system/dinfo/components/MapInfo";
import MapContainer from "@/views/system/disease/components/MapContainer";


export default {
  name: "Dinfo",
  data() {
    return {
      compentList:this.$route.query.compentList,
      id:this.$route.query.id,
      diseaseItem:null,
      map: null,
    }
  },
  created() {
    this.getDiseaseOne();
  },
  components: {
    Dinfomatio,
    Ddispose,
    Dconserve,
    MapInfo,
    MapContainer
  },
  methods: {
    getDiseaseOne(){
      getDisease(this.id).then(response=>{
        this.diseaseItem=response.data;
      });
    },
    load(){
      this.compentList.length = 3;
    },
  },
};
</script>
