<template>
    <div class="document" >
        <v-layout flex wrap mt-5 py-5>
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
                        <v-flex xl6 md6 sm12 xs12 v-for="document in All_Document " :key="document.id">
                            <v-card
                                :loading="loading1"
                                class="mx-auto mb-4"
                                max-width="350"
                                outlined
                            >
                               <img src="../../../../public/etudiant/images/document.png" alt="" srcset="" width="300px" height="200px"> 
                                <v-list-item three-line>
                                <v-list-item-content>
                                    <v-list-item-title class="text-h7 mb-1">
                                    {{document.Type}}
                                    </v-list-item-title>
                                    <v-list-item-subtitle>Nombre dispo : {{document.NombreDispo}}</v-list-item-subtitle>
                                    
                                </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-radio-group
                                    v-model="select_langue"
                                    row
                                    >
                                        <v-radio
                                            label="Français"
                                            value="Français"

                                        ></v-radio>
                                        <v-radio
                                            label="Arabe"
                                            value="Arabe"
                                        ></v-radio>
                                    </v-radio-group>
                                </v-list-item>
                                <v-card-actions>
                                <v-btn
                                    outlined
                                    rounded
                                    color="primary"
                                    @click="reserve(document.id)"
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
import service_doc from "@/service/Document/gererDocument.js"
export default {

   mounted(){
    service_doc.getAllDocment().then((res)=>{
         this.All_Document=res.data.data;
    });
    
   },
   name:'Document',
    data: () => ({
        All_Document:[],
        loading1: false,
        loading2: false,
        loading3: false,
        selection: 1,
        select_langue:'Arabe',
    }),

    methods: {
      reserve (id) {
        /*if(i==1){
            this.loading1 = true;
        }else if (i==2){
            this.loading2 = true;
        }else{
            this.loading3 = true;
        }
        setTimeout(() => (this.loading1 = false,this.loading2 = false,this.loading3 = false), 2000)*/
        service_doc.addDocument({document_id:id,langue:this.select_langue}).then((res)=>{
             console.log(res.data);
        })
      },
    },
}
</script>
<style scoped>
    .document{
        height: 100vh;
    }
     .v-card{
        max-width: 351px;
        height: 420px;     
    }
</style>