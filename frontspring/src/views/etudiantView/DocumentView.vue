<template>
    <div class="document">
        <Navbar></Navbar>
            <div class="main">
               <Document></Document>
            </div>
            <footer>
                <Footer></Footer>
            </footer>
    </div>
</template>
<script>
import Navbar from "@/components/etudiant/layouts/Navbar.vue";
import Document from "@/components/etudiant/document/Document.vue";
import Footer from "@/components/layouts/Footer.vue";
export default {
    components:{
        Navbar,Footer,Document
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