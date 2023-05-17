<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>

<style scoped>
#app{
  /* background: #FFF9EB; */
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.fade-enter,.fade-leave-to{
  opacity: 0; 
  transform: translateX(2em);
}
.fade-enter-active,.fade-leave-active{
  transition: all .3s ease;
}
</style>
