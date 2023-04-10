<template>
  <div class="home">
    <nav>
      <Navbar/>
    </nav>
    <main>
      <Content></Content>
    </main>
      <Footer></Footer>
  </div>
</template>

<script>
import Navbar from "@/components/home/Navbar.vue"
import Content from "@/components/home/Content.vue"
import Footer from "@/components/etudiant/layout/Footer.vue"
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
    Footer
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
