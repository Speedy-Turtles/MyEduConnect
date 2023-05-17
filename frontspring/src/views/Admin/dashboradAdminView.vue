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
                <div v-if="test_ischef==true || storeAuth.Ischef=='true'"  class="ma-5 pa-5" id="home">
                    <div v-if="store.view=='stat' ">
                          <!-- <statistique></statistique> -->
                    </div>
                    <div  v-else-if="store.view=='user'">
                         <GererUser></GererUser>
                    </div>
                    <div  v-else-if="store.view=='spec'">
                         <GererSpecialiteVue></GererSpecialiteVue>
                    </div>
                    <div  v-else-if="store.view=='edit'">
                       <modifierProfil></modifierProfil>
                  </div>
                  <div  v-else-if="store.view=='classe'">
                      <GererClasse></GererClasse>
               </div>
                </div>

                <div v-else class="ma-5 pa-5" id="home">
                  <div v-if="store.view=='stat' ">
                        <!-- <statistique></statistique> -->
                  </div>
                  <div  v-else-if="store.view=='edit'">
                     <modifierProfil></modifierProfil>
                </div>
              </div>

            </div>
        </transition>
    </div>
</template>

<script>
import userinfo from "@/service/UserInfo/userInfo.js";
import GererClasse from "@/components/Admin/classe/GererClasse.vue";
//import statistique from '@/components/Admin/statistique.vue';
import HeaderDashboard from '@/components/Admin/headerAdmin.vue';
import sidebarVue from '../../components/Admin/sidebar.vue'
import GererUser from "../../components/Admin/GererUser.vue"
import modifierProfil from "@/components/EditProfil/ModifierProfil.vue";
import {CurentView} from "@/store/storeView.js";
import {AuthUser} from "@/store/AuthStore.js";
import GererSpecialiteVue from '../../components/Admin/Specialite/GererSpecialite.vue';
export default{
  created(){
    this.greeting();
    this.test_ischef=this.storeAuth.Ischef;
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
            test_ischef:false
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
        if(this.storeAuth.user['welcome_field']!=1){
            let test=new SpeechSynthesisUtterance("welcome "+this.storeAuth.user['firstName']);
            speechSynthesis.speak(test);
            setTimeout(() => {
                userinfo.updateWelcome(this.storeAuth.user['email']).then((res)=>{
                  userinfo.getUserAuthentifie().then((res)=>{
                        this.storeAuth.SetUser(res.data);
                   })
                }).catch((err)=>{
                })
            }, 1000);
        }
      }
    },
    components:{
       sidebarVue,HeaderDashboard,GererUser,modifierProfil,
       GererSpecialiteVue,GererClasse
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