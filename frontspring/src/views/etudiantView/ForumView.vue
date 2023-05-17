<template>
    <div class="forum">
        <Navbar></Navbar>
            <div class="main">
               <Forum></Forum>
            </div>
            <footer>
                <Footer></Footer>
            </footer>
    </div>
</template>
<script>
import Navbar from "@/components/etudiant/layouts/Navbar.vue";
import Footer from "@/components/layouts/Footer.vue";
import Forum from "@/components/etudiant/forum/Forum.vue";
export default {
    components:{
        Navbar,Footer,Forum
    }
   
}
</script>
<style scoped>
.main{
    margin-top: 40px;
    height: auto;
}
.forum{
    overflow: hidden;
}
</style>