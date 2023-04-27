<template>
    <div class="help">

            <Navbar></Navbar>
            <main>
                <HelpEmploi></HelpEmploi>
            </main>
            <Footer></Footer>
    </div>
</template>
<script>
import Navbar from '@/components/etudiant/layout/Navbar.vue'
import Footer from '@/components/etudiant/layout/Footer.vue'
import HelpEmploi from '@/components/etudiant/help/HelpEmploi.vue'
export default {
    components:{
        Navbar,Footer,HelpEmploi
    }
}
</script>
<style scoped>
main{
    height: 185vh;
 }
</style>