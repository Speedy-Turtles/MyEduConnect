<<<<<<< HEAD
<template>
  <v-app id="app">
     <preloaderVue></preloaderVue>
      <router-view v-slot="{ Component }">
         <transition name="fade"  mode="out-in">
                 <component :is="Component" />
          </transition>
       </router-view>
  </v-app>
</template>

<script>
import AOS from "aos";
import 'animate.css';

import preloaderVue from './components/Loading/preloader.vue';
export default {
  name: 'App',
  components:{
    preloaderVue
  },
  mounted() {
      AOS.init();
      //test Websockets ,Pusher
      // window.Echo.channel('NotifRealtime').listen('notif',(e)=>{
    	//     console.log(e);
      // })
    },
 data(){
  return{
   
  }
 }
};
</script>

<style scoped>
#app{
  /* background: #FFF9EB; */
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.fade-enter,.fade-leave-to{
  opacity: 0; 
  transform: translateX(2em);
}
.fade-enter-active,.fade-leave-active{
  transition: all .3s ease;
}

</style>
=======
<template>
  <v-app id="app">
     <preloaderVue></preloaderVue>
      <router-view v-slot="{ Component }">
         <transition name="fade"  mode="out-in">
                 <component :is="Component" />
          </transition>
       </router-view>
  </v-app>
</template>

<script>
import AOS from "aos";
import 'animate.css';

import preloaderVue from './components/Loading/preloader.vue';
export default {
  name: 'App',
  components:{
    preloaderVue
  },
  mounted() {
      AOS.init();
      //test Websockets ,Pusher
      // window.Echo.channel('NotifRealtime').listen('notif',(e)=>{
    	//     console.log(e);
      // })
    },
 data(){
  return{
   
  }
 }
};
</script>

<style scoped>
#app{
  /* background: #FFF9EB; */
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.fade-enter,.fade-leave-to{
  opacity: 0; 
  transform: translateX(2em);
}
.fade-enter-active,.fade-leave-active{
  transition: all .3s ease;
}

</style>
>>>>>>> c475a485596fd9a763dff634b70b71401dae7c73
