<template>
    <div class="forum">

            <Navbar></Navbar>
            <div class="main">
               <h1>forum</h1>
               <Forum></Forum>
            </div>
            <Footer></Footer>
    </div>
</template>
<script>
import Navbar from '@/components/etudiant/layout/Navbar.vue'
import Footer from '@/components/etudiant/layout/Footer.vue'
import Forum from '@/components/etudiant/forum/Forum.vue'
export default {
    components:{
        Navbar,Footer,Forum
    }
}
</script>
<style scoped>
.main{
   height: auto;
}
</style>