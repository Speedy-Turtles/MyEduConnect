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
import Navbar from '@/components/etudiant/layout/Navbar.vue'
import Footer from '@/components/etudiant/layout/Footer.vue'
import Document from '@/components/etudiant/document/Document.vue'
export default {
    components:{
        Navbar,Footer,Document
    }
}
</script>
<style scoped>
.main{
   height: auto;
}
.document{
    overflow: hidden;
}
</style>