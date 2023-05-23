<template>
    <div class="chat">
        <Navbar></Navbar>
            <div class="main">
               <Chat></Chat>
            </div>
            <Footer></Footer>
    </div>
</template>
<script>
import Navbar from '@/components/etudiant/layout/Navbar.vue'
import Footer from '@/components/etudiant/layout/Footer.vue'
import Chat from "@/components/etudiant/forum/Chat.vue"
    export default {
        name:'chatInbox',
        components:{
            Chat,Navbar,Footer
        },

    }
</script>
<style scoped>
.main{
    margin-top: 10vh;
   height: auto;
}
</style>