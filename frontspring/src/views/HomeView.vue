<template>
  <div class="home">
    <header>
    <Navbar></Navbar>
  </header>
      <Content></Content>
      <Footer></Footer>
  </div>
</template>

<script>

  import Navbar from "@/components/layouts/Navbar.vue";
  import Footer from "@/components/layouts/Footer.vue";
  import Content from "@/components/home/Content.vue";
  export default {
    name: 'Home',

    components: {
      Navbar,Content,Footer
    },
  }
</script>
<style scoped>
body{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.home{
  overflow: hidden;
}

* {
	font-family: 'DM Sans', sans-serif;
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	outline: none;
	border: none;
	text-decoration: none;
	text-transform: capitalize;
	transition: all .2s linear;
}

html {
	font-size: 62.5%;
	overflow-x: hidden;
	scroll-padding-top: 9rem;
	scroll-behavior: smooth;
}

html::-webkit-scrollbar{
	width: 1rem;
}

html::-webkit-scrollbar-track{
	background: transparent;
}

html::-webkit-scrollbar-thumb{
	background: #12c2b9;
}
</style>