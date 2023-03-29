<template>
    <div class="nouveaute">
        <v-layout flex wrap>
            <v-flex xl4 md3  xs12 class="hidden-sm-and-down pa-5" data-aos="fade-left">
                <v-container>
                    <img src="../../../../public/etudiant/images/logo-novelty-no-background.svg"  width="200px" height="200px">
                    <p class="">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Animi consequatur quam alias a atque ut quos. Minus officiis veritatis maiores.</p>
                    <img src="../../../../public/etudiant/images/logoiset.png" alt="" width="200px" height="200px" >  
                </v-container>
            </v-flex>
           

            <v-flex xl8 md9  xs12 class="pa-5" >
                    <v-layout row>
                        <v-flex class="pa-5"><h1>Novelty</h1></v-flex>
                    </v-layout>
                    <div id="card" v-for="i in 5" data-aos="fade-down">
                        <v-layout row wrap >
                            <v-flex xl4 md3 sm4 xs12 class=""><img src="../../../../public/etudiant/images/p1.png" width="150px" alt="" srcset=""></v-flex>
                            <v-flex xl8 md9 sm8 xs12 class="card_title">
                                <v-layout>
                                    <v-flex>
                                        <h5>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae, delectus?</h5>
                                    </v-flex> 
                                </v-layout>
                                <v-layout>
                                    <v-flex class="card_date">
                                        <span >25/08/2023</span>
                                    </v-flex>
                                </v-layout>
                                    
                                
                            </v-flex>
                        </v-layout>
                        <v-layout row>
                            <v-flex class="card_desc"><p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Sunt modi doloribus magnam expedita voluptatem placeat blanditiis cum. Debitis, culpa in.</p></v-flex>
                        </v-layout>
                    </div>
                    <v-btn class="float-end" plain>Suivant<v-icon>mdi-arrow-right</v-icon></v-btn>
            </v-flex>
        </v-layout>

    </div>
</template>
<script>
export default {

}
</script>
<style scoped>
#card{
    border: 0.25px solid rgb(213, 211, 211);
    margin-top: 20px;
    margin-bottom: 50px;
    padding: 40px;
    border-radius:10px ;
    border-left: 4px solid rgb(213, 211, 211);
}
.card_title{
    margin-top: 10px;
    font-size: 19px;
    margin-bottom: 10px;
    font-weight: bold;
}
.card_desc{
    margin-top: 10px;
}
.card_date{
    padding-top: 37px;
    font-size: 15px !important;
    font-weight: lighter !important;
}
</style>