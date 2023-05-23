<template>
    <div class="etudiant">
         <nav >
            <div class="navbar">
               <Navbar></Navbar>
            </div>
        </nav>
        <main>
            <Home></Home>
        </main>
        <footer>
            <Footer></Footer>
        </footer>
    </div>
</template>
<script>
import userinfo from "@/service/UserInfo/userInfo.js";
import Navbar from "@/components/etudiant/layouts/Navbar.vue";
import Footer from "@/components/layouts/Footer.vue";
import Home from "@/components/etudiant/home/Home.vue";
import {AuthUser} from "@/store/AuthStore.js";
export default {
    name:'homeEtudiant',
    components:{
        Navbar,Home,Footer
    },
    setup(){
    const storeAuth=AuthUser();
    return {storeAuth}
     },
    data(){
        return{
           
        }
    },
    methods:{
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
    created(){
        this.greeting();
    }

}
</script>
<style scoped>
.etudiant{
   overflow: hidden;
    
}
</style>