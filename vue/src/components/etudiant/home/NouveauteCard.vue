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
                    <div class="NoNeauvelty ma-10 " v-if="neavautes.length==0">
                        <v-img src="../../../../public/etudiant/images/no-data-found-logo.png" width="550px"></v-img>
                        
                    </div>
                    <div id="card"  data-aos="fade-down" v-else>
                        <div class="Neauvelty" v-for="neauv in neavautes" :key="neauv.id">
                            <v-layout row wrap >
                            <v-flex xl4 md3 sm4 xs12 class=""> <img :src="neauv.photo" width="150px" alt="" srcset=""></v-flex>
                            <v-flex xl8 md9 sm8 xs12 class="card_title">
                                <v-layout>
                                    <v-flex>
                                        <h5>{{ neauv.titre }}</h5>
                                    </v-flex> 
                                </v-layout>
                                <v-layout>
                                    <v-flex class="card_date">
                                        <span >{{neauv.created_at}}</span>
                                    </v-flex>
                                </v-layout>
                                    
                            </v-flex>
                        </v-layout>
                        <v-layout row>
                            <v-flex class="card_desc"><p>{{ neauv.descripition }}</p></v-flex>
                        </v-layout>
                        </div>
                        
                    </div>
                    <v-layout row >
                        <v-flex v-if="neavautes.length>0">
                            <v-btn class="float-end" @click="this.page < this.totalPages ? this.pages++: ''" plain>Next<v-icon>mdi-arrow-right</v-icon></v-btn> 
                            <v-btn class="float-end"  @click="page--" plain><v-icon>mdi-arrow-left</v-icon>Previous</v-btn>
                               
                        </v-flex>                     
                    </v-layout>
                    
            </v-flex>
        </v-layout>

    </div>
</template>
<script>
import gererNeavaute from '@/service/neavaute/gererNeavaute';   
export default {
data(){
    return{
        neavautes: [],
        page: 1,
        totalPages: 0,
    }
},
methods:{
    getNeavaute() {
            this.loader = true;
            gererNeavaute.getNeavaute(this.page).then(response => {
                this.neavautes = response.data.data.data;
                console.log(response.data.data);
                this.totalPages = response.data.data.last_page;
                this.loader = false;
            })
        },
       
    
}, created() {
        
        this.getNeavaute();
    },
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
.msgErr{
    text-align: center;
    font-size: 30px;
    color: red;
}
</style>