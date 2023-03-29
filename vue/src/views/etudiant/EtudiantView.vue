<template>
            <div class="etudiant">
                <nav>
                    <div class="navbar">
                        <Navbar></Navbar>
                    </div>
                </nav>
                <main>
                    <Home :photos="photos"></Home>
                    <NouveauteCard />
                </main>
                <footer>
                    <Footer></Footer>
                </footer>
                </div>
</template>
<script>
import Navbar from '@/components/etudiant/layout/Navbar.vue'
import Home from '@/components/etudiant/home/Home.vue'
import Footer from '@/components/etudiant/layout/Footer.vue'
import NouveauteCard from '@/components/etudiant/home/NouveauteCard.vue'

export default {
    components:{
        Navbar,Home,Footer,NouveauteCard,
    },
    data(){
        return{
            photos:[
                require("../../../public/etudiant/images/p1.png"),
                require("../../../public/etudiant/images/p2.png"),
                require("../../../public/etudiant/images/p3.png"),
                require("../../../public/etudiant/images/p4.png")
        ],
        links:[
                    {titre:null,link:'home',route:'',desc:'Home',icon:'mdi-home'},
                    {titre:'Document',link:'document',route:'',desc:'Check Documents',icon:'mdi-table-edit'},
                    {titre:'Forum',link:'forum',route:'',desc:'Go to Forum',icon:'mdi-comment-text-outline'},
                    {titre:'Club',link:'club',route:'',desc:'Enjoy Clubs',icon:'mdi-star-outline'},
                    {titre:'Help',link:'help',route:'',desc:'How Can We help You !',icon:'mdi-wrench'}
                ],
        messages:10,
        }
    },
    methods:{
       
    }
}
</script>
<style>
</style>