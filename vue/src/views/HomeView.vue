<template>
  <div class="home">
  
    <nav>
      <Navbar/>
    </nav>
    <main>
      <Content></Content>
    </main>
    <footer>
      <Footer></Footer>
    </footer>
  </div>
</template>

<script>
import Navbar from "@/components/home/Navbar.vue"
import Content from "@/components/home/Content.vue"
import Footer from "@/components/etudiant/layout/Footer.vue"
import preloaderVue from '../components/Loading/preloader.vue'
export default {
  name: 'Home',
  data(){
    return{
    }
  },
  methods:{
  },
  components: {
    Navbar,
    Content,
    Footer,
    preloaderVue
  },
}
</script>
<style scoped>

body{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}


*{
    scroll-behavior: smooth;
}
.home{
  overflow: hidden;
}
</style>
