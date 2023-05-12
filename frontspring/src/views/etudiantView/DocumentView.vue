<template>
    <div class="document">
        <Navbar></Navbar>
            <div class="main">
               <h1>Document</h1>
            </div>
            <footer>
                <Footer></Footer>
            </footer>
    </div>
</template>
<script>
import Navbar from "@/components/etudiant/layouts/Navbar.vue";
import Footer from "@/components/layouts/Footer.vue";
export default {
    components:{
        Navbar,Footer
    }
   
}
</script>
<style scoped>
.main{
    margin-top: 100px;
   height: 100vh;
}
.document{
    overflow: hidden;
}
</style>