<template>
    <div class="club">
            <Navbar></Navbar>
            <div class="main mt-5">
               <vote></vote>
            </div>
            <Footer></Footer>
    </div>
</template>
<script>
import vote from "@/components/ensignant/vote.vue"
import Navbar from '@/components/etudiant/layout/Navbar.vue'
import Footer from '@/components/etudiant/layout/Footer.vue'
export default {
    components:{
        Navbar,Footer,vote
    }
}
</script>
<style scoped>
.main{
   height: auto;
}
</style>