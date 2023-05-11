<template>
    <div class="etudiant">  
        <nav >
            <div class="navbar">
                <Navbar ></Navbar>
            </div>
        </nav>
        <main>
           <ModifierProfil></ModifierProfil>
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
import ModifierProfil from "@/components/EditProfil/ModifierProfil.vue"
export default {
components:{
Navbar,Home,Footer,NouveauteCard,ModifierProfil
},
data(){
return{
   
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
<style scoped>
.etudiant{
    overflow: hidden;
   
}
main{
    margin-top: 150px;
    margin-bottom: 100px;
}

</style>