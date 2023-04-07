<template>
  <div :class=" small ? 'small' : 'large'">
    <nav  :class="etatsidbar==false ? 'close' : '' ">
      <ul>
          <li :class=" small ? 'none_btn_open_close_sidebar' : ''"  class="mb-5">
               <div  href="#"  :class="etatsidbar ? 'close' : '' " class="logo">
                   <img  src="../../assets/logo-no-background.svg" alt="">
               </div>
          </li>
            <li>
              <a  @click="changerView('test')"  href="#" :class="ViewCurrent=='test' ? 'active' : ''">
                  <v-icon  class="icon">mdi-home</v-icon>
                  <span :class=" small ? 'none_btn_open_close_sidebar' : ''"  class="nav-item">test</span>
                 </a>
              </li>
               <li>
                  <a @click="changerView('test1')" href="#" :class="ViewCurrent=='test1' ? 'active' : ''">
                     <v-icon  class="icon"  >mdi-home</v-icon>
                     <span  :class=" small ? 'none_btn_open_close_sidebar' : ''" class="nav-item">test1</span>
                 </a>
              </li>
          <li class="btn_open_close"  :class="etatsidbar==true ? 'close' : '' ">
                <v-btn :class=" small ? 'none_btn_open_close_sidebar' : ''"    fab  @click="changer()">
                     <v-icon style="color:#5094df" v-if="etatsidbar" class="px-1">mdi-arrow-right-bold</v-icon>
                     <v-icon   style="color:#5094df" v-else class="px-1">mdi-arrow-left-bold</v-icon>
               </v-btn>
          </li>
        </ul>
  </nav>
</div>
</template>

<script>

export default{
  name:"sidebar",
  props:{
      etatsidbar:Boolean,
      ViewCurrent:String,
      small:Boolean
  },
  data(){
      return{

      }
  },
  methods:{
      changer(){
          this.etatsidbar=this.etatsidbar==true ? false : true;
          this.$emit("changreetat", this.etatsidbar);
      },
      changerView(data){
          this.$emit('changerView',data);
      }
  }, 
}
</script>

<style scoped>

.large nav{
      background: #5094df;
      position: absolute;
      left: 0;
      top: 0;
      height: 100vh;
      z-index: 999999999;
      width: 90px;
      bottom: 0;
      overflow: hidden;
      transition: width 0.2s linear ;
      box-shadow: 0 20px 35px rgba(0,0,0,0.1);
      scroll-behavior: smooth;
      color: #fff ;
  }
  .small nav {
    width:95% !important;
    margin: 10px ;
    overflow: none;
    border-radius: 25px;
    position: fixed;
    z-index: 1000;
    height: 10% !important;
    bottom: 0;
    display: flex;
    text-align: center;
    background-color: #5094df;
  }

.small nav ul {
      display: inline-flex;
      align-items: center;
  }
 .large .logo{
      text-align: center;
      display: flex;
      justify-content: center;
      transition: all  0.5s ease;
      margin:20px 0 0 15px;
  }
  .small .logo{
    text-align: center;
    display: flex;
    justify-content: center;
    transition: all  0.5s ease;
    margin: 10px;
    margin-top:25px;
    width: 60px;
}
  .logo img{
      width: 100px;
      height: 100px;
      margin-left: 25px;
      border-radius: 50%;
  }
  .logo span{
      font-weight: bold;
      padding: 15px;
      font-size: 18px;
      text-transform: uppercase;
  }

  .large a{
      position: relative;
      font-size: 14px;
      display: table;
      width: 300px;
      padding: 10px;
      text-decoration: none;
  }

  .small a{
    position: relative;
    font-size: 14px;
    display: table;
    width: 25px;
    padding: 2px;
    text-decoration: none;
}

  li{
      list-style-type: none;
  }

  
  .large ul li .active{
    background: #fff;
    border-top-left-radius: 30px;
    border-bottom-left-radius: 30px;
  }

  .small ul li .active{
    background: #fff;
    border-radius: 25px;
  }

  .active .nav-item,.active .icon{
    font-size: 18px;
    color:#5094df !important;
  }

  ul li a{
    position: relative;
    display: block;
    padding: 0 10px;
    color:#fff;
    height: 67px;
    line-height:34px;
  }

.large .active .icon{
    position: relative;
    width: 70px;
    height: 40px;
    top: 14px;
    font-size: 25px;
    text-align: center;
    margin-left:-10px;
}  

.small  .icon{
  position: relative;
  width: 70px;
  height: 40px;
  top: 14px;
  font-size: 15px;
  text-align: center;
}  
.small .active .icon{
  position: relative;
  width: 70px;
  height: 40px;
  top: 14px;
  font-size: 20px;
  text-align: center;
}  
  .icon{
      position: relative;
      width: 70px;
      height: 40px;
      top: 14px;
      font-size: 25px;
      text-align: center;
      margin-left:-10px;
      color:#fff;
  }
  .nav-item{
      position: relative;
      top: 19px;
      margin-left: 10px;
  }
  nav.close{
      width: 280px;
      transition:all 0.5s ease;
  }

  .logo img{
    margin-left:-32px;
    border-radius: 50%;
}
   .logo.close img{
      width: 70px;
      height: 70px;
      margin-left:-32px;
      border-radius: 50%;
  }
   .btn_open_close{
      position: absolute;
      bottom: 0; 
      margin-left: 70px;
      margin-bottom: 20px;
      transition: all 0.5s ease-in-out;
  }
  .none_btn_open_close_sidebar{
    display: none;
  }
   .btn_open_close.close{
      position: absolute;
      bottom: 0; 
      margin-left: 0;
      margin-bottom: 20px;
  }


</style>