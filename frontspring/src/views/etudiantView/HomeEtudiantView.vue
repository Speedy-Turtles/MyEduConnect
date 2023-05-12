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
import Navbar from "@/components/etudiant/layouts/Navbar.vue";
import Footer from "@/components/layouts/Footer.vue";
import Home from "@/components/etudiant/home/Home.vue";
export default {
    name:'homeEtudiant',
    components:{
        Navbar,Home,Footer
    },
    data(){
        return{
           
        }
    }

}
</script>
<style scoped>
.etudiant{
   overflow: hidden;
    
}
</style>