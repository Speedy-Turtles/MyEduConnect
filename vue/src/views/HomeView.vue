<template>
  <div>
    <div>
      <Navbar />
    </div>
    <v-main>
      <Content>
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
  components: {
    Navbar,
    Content,
    Footer
  },
}
</script>
