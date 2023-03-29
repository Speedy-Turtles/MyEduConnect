<template>
    <div class="etudiant">
        <Navbar/>
        <main>
            <Home :photos="photos"></Home>
        </main>
        <footer>
            <Footer></Footer>
        </footer>
    </div>
</template>
<script>
import Navbar from '@/components/etudiant/Navbar.vue'
import Home from '@/components/etudiant/Home.vue'
import Footer from '@/components/etudiant/Footer.vue'
export default {
    components:{
        Navbar,Home,Footer
    },
    data(){
        return{
            photos:[
                require("../../../public/etudiant/images/p1.png"),
                require("../../../public/etudiant/images/p2.png"),
                require("../../../public/etudiant/images/p3.png"),
                require("../../../public/etudiant/images/p4.png")
        ]
        }
    }
}
</script>
<style>
</style>