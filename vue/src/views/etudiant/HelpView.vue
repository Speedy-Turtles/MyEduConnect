<template>
    <div class="help">

            <Navbar></Navbar>
            <div class="main">
               <h1>help</h1>
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
<style>
.main{
   height: auto;
}
</style>