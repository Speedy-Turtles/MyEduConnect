<template>
  <div id="home">
    <div>
      <Navbar @GetSelectTab="GetSelectTab"  />
    </div>
    <v-main>
      <Content :selectTab="selectTab" >
      </Content>
    </v-main>
    <footer>
      <Footer>
      </Footer>
    </footer>
  </div>
</template>

<script>
import Navbar from "@/components/home/Navbar.vue"
import Content from "@/components/home/Content.vue"
import Footer from "@/components/home/Footer.vue"
export default {
  name: 'Home',
  data(){
    return{
       selectTab:''
    }
  },
  methods:{
    GetSelectTab(a){
      this.selectTab=a;
    }
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
   scroll-behavior: smooth;
}
</style>
