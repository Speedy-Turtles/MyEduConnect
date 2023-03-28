<template>
    <div class="footer">

        
            <!-- <div class="footer1"> -->
                <!-- <v-container>
                    <v-layout row wrap>
                       <v-flex xl4 md4 sm12 xs12 class="pa-5">
                            <img src="../../../public/etudiant/images/logo-no-background-white.svg" width="200px">
                            <p class="py-5">MyEduconnect is an education website that provides  features a social network for students to connect with peers, share ideas, and build communities.</p>
                            
                       </v-flex>

                       <v-flex xl4 md4 sm6 xs12 class="pa-5 ">
                            <img src="../../../public/etudiant/images/logo-no-background-team.svg" width="200px" alt="" class="mb-5">
                            <h3>Talel Mejri</h3>
                            <h3>Amine Hkimi</h3>
                            <h3>Malek Naamen</h3>
                            <h3>Ghassen Ahmed</h3>
                       </v-flex>

                       <v-flex xl4 md4  sm6 xs12 class="pa-5">
                            <img src="../../../public/etudiant/images/logo-no-background-fonctions.svg" width="200px" alt="" class="mb-5">
                            <h3>Documents</h3>
                            <h3>Forum</h3>
                            <h3>Club</h3>
                            <h3>Help Desk</h3>
                       </v-flex>



                    </v-layout>
                </v-container>
            </div>
             -->
            <div class="footer2">
                <v-container id="container">
                    <v-layout row wrap >
                        <v-flex xl4 md4 sm4 xs12  class="footer-2-span float-start" ><v-icon color="white">mdi-copyright</v-icon> <span class="white--text align-center">2023 MyEduConnect.All rights reserved.</span></v-flex>
                        <v-flex xl4 md4 sm4 xs12 class="hidden-xs-only float-right"><img class="footer-2-img" src="../../../../public/etudiant/images/logo-no-background-white.svg"></v-flex>
                        <v-flex xl4 md4 sm4 xs12 class="footer-2-span align-center"><span class="white--text float-xl-end float-md-end float-sm-end " id="span-reserved">Speedy Turtles</span></v-flex>
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
<style>
.footer1{
    background-color:#5094df ;
    height: auto;
    
    margin-top: 70px;
    padding-top: 50px;
    padding-bottom: 50px;
}
.footer2{
    height: auto;
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

/* @media only screen and (max-width: 600px) {
    .footer-2-span{
        margin-left: 114px;
        font-size: 17px;
        
    }
} */
</style>