<template>
    <div :class="small ? 'small' : 'large'" id="dashboard">
         <div class="sidebar_position">
            <sidebarVue
               :etatsidbar="etatsidbar"
               @changreetat="changreetat"
               :small="small"
            ></sidebarVue>
          </div>
          <transition name="fade" mode="out-in">
            <div :class="etatsidbar == true ? 'content close ' : 'content'">
                 <HeaderDashboard ></HeaderDashboard>
                <div  class="ma-5 pa-5" id="home">
                    <div v-if="store.view=='stat' ">
                          <statistique></statistique>
                    </div>
                    <div  v-else-if="store.view=='vote'">
                        <Vote></Vote>
                    </div>
                    <div  v-else>
                       <statistique></statistique>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
import statistique from '@/components/Admin/statistique.vue';
import HeaderDashboard from '@/components/Admin/HeaderDashboard.vue';
import sidebarVue from '../../components/Admin/sidebar.vue'
import Vote from '@/components/Admin/Vote.vue';
import {CurentView} from "@/store/StoreView.js";
export default{
  setup(){
    const store=CurentView();
    return {store}
  },
    name:'dashboard',
    data(){
        return{
            etatsidbar: false,
            show_all: true,
            snackbar: false,
            snackbar: false,
            snackbar_edit: false,
            small:false,
        }
    },
    mounted() {
        window.addEventListener('resize', this.onresize);
    },
    methods:{
        onresize(){
                if (window.innerWidth < 750) {
                       this.etatsidbar = true;
                       this.small = false;
                } else{
                       this.etatsidbar = false;
                       this.small = false;
                }
                if(window.innerWidth<500){
                        this.small = true;
                        this.etatsidbar = false;
               }
      }, 
        changreetat(etat){
            this.etatsidbar=etat;
        }
    },
    components:{
       sidebarVue,HeaderDashboard,statistique,Vote
    },
 
};
</script>

<style scoped>

.sidebar_position{
    position: fixed;
    z-index: 2 !important;
}
  .content {
    width: calc(100%-280px);
    position: relative;
    scroll-behavior: smooth;
    transition: all 0.2s ease-in-out;
  }

  .large .content.close{
    width: calc(100%-90px) !important;
    margin-left: 90px !important;
    transition: all 0.2s ease-in-out;
    scroll-behavior: smooth;
  }
  .large .content {
         margin-left: 280px ;
  }
  .small .content{
        margin-left: 0px !important;
  }



</style>