<template>
    <div class="footer">
            <div class="footer2">
                <v-container id="container">
                    <v-layout row wrap >
                        <v-flex xl4 md4 sm4 xs12  class="footer-2-span float-start" ><v-icon color="white">mdi-copyright</v-icon> <span class="white--text align-center">2023 MyEduConnect.All rights reserved.</span></v-flex>
                        <v-flex xl4 md4 sm4 xs12 class="hidden-xs-only float-right"><img class="footer-2-img" src="../../../../public/etudiant/images/logo-no-background-white.svg"></v-flex>
                        <v-flex xl4 md4 sm4 xs12 class="footer-2-span align-center"><span class="white--text float-xl-end float-md-end float-sm-end " id="span-reserved">Speedy Turtles</span> </v-flex> 
                    </v-layout>
                    <v-layout>
                        <a href="#">
                            <v-btn class="button float-right mr-5">
                                <v-icon>mdi-arrow-up</v-icon>
                            </v-btn>
                        </a>
                    </v-layout>
                    
                </v-container>   
            </div>
    </div>

   
</template>
<script>
    export default {
        data(){
            return{
                
            }
        }
    }

</script>
<style scoped>
.container{
    margin-top:12vh ;
}
.footer1{
    background-color:#5094df ;
    height: auto;
    
    margin-top: 70px;
    padding-top: 50px;
    padding-bottom: 50px;
}
.footer2{
    height: 30vh;
    background-color:#525fe1 ;
    
}
.footer-2-span{
    font-size: 15px;
    padding-top: 46px;
   
}
.footer-2-img{
    padding-top: 20px;
    width: 126px;
    margin-left: 100px;
}
#span-reserved{
    margin-left: 71px;
}
h3,p{
    font-size: 16px;
    font-weight: lighter;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
h3{
    margin-left: 61px;
}
.button{
    position: absolute;
    left: 90%;
    bottom: 20px;
    position: fixed;
    z-index: 999;
    border-radius: 20px;
}
*{
    scroll-behavior: smooth;
    overflow: hidden;
}
</style>