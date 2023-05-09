<template>
    <div v-if="preloader"  class="preloader">
        <img data-aos="zoom-in-up" :src="require('@/assets/Logo_app/logo-no-background.png')" alt="">
    </div>
</template>
<script>
export default{
    name:"loader",
    mounted(){
        setTimeout(()=>this.preloader=false,2000);
    },
    data(){
        return{
            preloader:true,
        }
    }
}
</script>

<style scoped>
img{
    width:300px ;
    height: 300px;
}
    .preloader{
        background-color:  #fff;
        height: 100vh;
        position: fixed;
        z-index:99999999;
        top:0;
        left:0;
        width: 100vw;
        display: flex;
        min-height: 100vh;
        justify-content: center;
        align-items: center;
    }

    @media screen and (max-width: 700px) {
            img{
                width:200px ;
                height: 200px;
            }
    }
    
</style>