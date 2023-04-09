<template>
    <div :class="small ? 'small' : 'large'" id="dashboard">
         <div class="sidebar_position">
            <sidebarVue
               @changerView="changerView"
               :ViewCurrent="ViewCurrent"
               :etatsidbar="etatsidbar"
               @changreetat="changreetat"
               :small="small"
            ></sidebarVue>
          </div>
          <transition name="fade" mode="out-in">
            <div :class="etatsidbar == true ? 'content close ' : 'content'">
                 <HeaderDashboard :ViewCurrent="ViewCurrent"></HeaderDashboard>
                <div  class="ma-5 pa-5 " id="home">
                    <div  v-if="ViewCurrent=='stat'">
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

export default {
    name:'dashboard',
    data(){
        return{
            etatsidbar: false,
            show_all: true,
            snackbar: false,
            snackbar: false,
            ViewCurrent: "stat",
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
        changerView(data){
            this.ViewCurrent=data;
        },
        changreetat(etat){
            this.etatsidbar=etat;
        }
    },
    components:{
       sidebarVue,HeaderDashboard,statistique
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