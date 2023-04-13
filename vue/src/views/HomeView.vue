<template>
  <div id="home">
    <div>
      <Navbar/>
    </div>
    <main>
      <Content>
      </Content>
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
#home{
  overflow: hidden;
}
*{
    scroll-behavior: smooth;
}
</style>
