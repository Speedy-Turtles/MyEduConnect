<template>
    <div class="club">

            <Navbar></Navbar>
            <div class="main">
               <h1>club</h1>
            </div>
            <Footer></Footer>
    </div>
</template>
<script>
import Navbar from '@/components/etudiant/layout/Navbar.vue'
import Footer from '@/components/etudiant/layout/Footer.vue'
export default {
    components:{
        Navbar,Footer
    }
}
</script>
<style scoped>
.main{
   height: auto;
}
</style>