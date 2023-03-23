<template>
  <div id="app">
    <router-view />
    <theme-picker />
  </div>
</template>
<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  components: { ThemePicker },
    metaInfo() {
        return {
            title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
            titleTemplate: title => {
                return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
            }
        }
    }
};
</script>
<style>

#app .theme-picker {
  display: none;
}
.bigBox{
  background-color: #ededf7;
  box-shadow: #97a8be;
  border-radius: 10px;
  height: 800px;
  font-size: 18px;
  border: 1px #ededf7 ridge;
  overflow: scroll;
}

/*去除滚动条显示*/
.bigBox::-webkit-scrollbar{display:none}
.smallBox{
  background-color: white;
  margin: 30px auto auto;
  font-size: 18px;
  box-shadow: #97a8be;
  width: 91%;
  border-radius: 10px;

}
</style>
