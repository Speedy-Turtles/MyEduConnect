<template>
    <div class="document">
        <v-layout flex wrap>
            <v-flex xl4 md3  xs12 class="hidden-sm-and-down pa-5" data-aos="fade-left">
                <v-container>
                    <img src="../../../../public/etudiant/images/logo-documnt-no-background.svg"  width="200px" height="200px">
                    <p class="">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Animi consequatur quam alias a atque ut quos. Minus officiis veritatis maiores.</p>
                    <img src="../../../../public/etudiant/images/logoiset.png" alt="" width="200px" height="200px" >  
                </v-container>
            </v-flex>
           

            <v-flex xl8 md9  xs12 class="pa-5" >
                    <v-layout row>
                        <v-flex class="pa-5"><h1>Document</h1></v-flex>
                    </v-layout>
                    <v-layout row wrap>
                        <v-flex xl6 md6 sm12 xs12 v-for="i in 3 ">
                            <v-card
                                :loading="loading"
                                class="mx-auto mb-4"
                                max-width="350"
                                outlined
                                
                            >
                               <img src="../../../../public/etudiant/images/document.png" alt="" srcset="" width="300px" height="200px"> 
                                <v-list-item three-line>
                                <v-list-item-content>
                                    <v-list-item-title class="text-h7 mb-1">
                                    document name
                                    </v-list-item-title>
                                    <v-list-item-subtitle>description</v-list-item-subtitle>
                                    
                                </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-checkbox
                                    label="Francais"
                                    >
                                        
                                    </v-checkbox>
                                    <v-checkbox
                                    label="Arabe"
                                    >
                                        
                                    </v-checkbox>
                                </v-list-item>
                                <v-card-actions>
                                <v-btn
                                    outlined
                                    rounded
                                    text
                                    @click="reserve"
                                >
                                    demande
                                </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-flex>
                    </v-layout>
            </v-flex>
        </v-layout>
    </div>
</template>
<script>
export default {
    data: () => ({
      loading: false,
      selection: 1,
    }),

    methods: {
      reserve () {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
    },
}
</script>
<style>
    .v-card{
        max-width: 351px;
        height: 420px;     
    }
</style>