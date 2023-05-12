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
                          <test></test>
                          <!-- <statistique></statistique> -->
                    </div>
                    <div  v-else-if="store.view=='vote'">
                        <!-- <Vote></Vote> -->
                    </div>
                    <div  v-else-if="store.view=='edit'">
                       <!-- <modifierProfil></modifierProfil> -->
                  </div>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
import userinfo from "@/service/UserInfo/userInfo.js";
//import statistique from '@/components/Admin/statistique.vue';
import HeaderDashboard from '@/components/Admin/headerAdmin.vue';
import sidebarVue from '../../components/Admin/sidebar.vue'
import test from "../../components/Admin/test.vue"
//import modifierProfil from "@/components/EditProfil/ModifierProfil.vue";
//import Vote from '@/components/Admin/Vote.vue';
import {CurentView} from "@/store/storeView.js";
import {AuthUser} from "@/store/AuthStore.js";
export default{
  created(){
    this.greeting();
  },
  setup(){
    const store=CurentView();
    const storeAuth=AuthUser();
    return {store,storeAuth}
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
        },
      greeting(){
        if(this.storeAuth.user['welcome_field']==0){
            let test=new SpeechSynthesisUtterance("welcome"+this.storeAuth.user['FirstName']);
            speechSynthesis.speak(test);
            setTimeout(() => {
                userinfo.updateWelcome(this.storeAuth.user['email']).then((res)=>{
                    console.log(res)
                }).catch((err)=>{
                    console.log(err)
                })
            }, 1000);
        }
      }
    },
    components:{
       sidebarVue,HeaderDashboard,test
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