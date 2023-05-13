<template>
    <div class="document" >
        <v-layout flex wrap mt-5 py-5>
            <v-flex xl4 md3  xs12 class="hidden-sm-and-down pa-5" data-aos="fade-left">
                <v-container>
                    <img src="../../../assets/logo-documnt-no-background.svg"  width="200px" height="200px">
                    <p class="">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Animi consequatur quam alias a atque ut quos. Minus officiis veritatis maiores.</p>
                    <img src="../../../assets/logoiset.png" alt="" width="200px" height="200px" >  
                </v-container>
            </v-flex>
            <v-flex xl8 md9  xs12 class="pa-5" >
                    <v-layout row>
                        <v-flex class="pa-5"><h1 class="titre">Document</h1></v-flex>
                    </v-layout>
                    <v-flex  xl6 md6 sm12 xs12 v-for="(document,index) in All_Document " :key="document.id">
                            <v-card
                                :loading="loading1"
                                class="mx-auto mb-4"
                                max-width="350"
                                outlined
                            >
                               <img src="../../../assets/document.png" alt="" srcset="" width="300px" height="200px"> 
                                <v-list-item three-line>
                                <v-list-item-content>
                                    <v-list-item-title class="text-h7 mb-1">
                                    {{document.type}}
                                    </v-list-item-title>
                                    <p style="display: none;">
                                        {{ test=All_demandes.find(v=>v.iddoc==document.id) }}
                                        
                                   </p>
                                    <v-list-item-subtitle>Nombre Dispo : {{document.nombreDispo}}</v-list-item-subtitle>
                                    <v-list-item-subtitle>Nombre Available : {{!test ? 0 : test?.nombre}}</v-list-item-subtitle>
                                </v-list-item-content>
                                
                                </v-list-item>
                                <v-list-item v-if="index==0">
                                    <v-radio-group
                                    v-model="select_langue"
                                    row
                                    >
                                        <v-radio
                                            label="Français"
                                            value="f"
                                        ></v-radio>
                                        <v-radio 
                                            label="Arabe"
                                            value="a"
                                        ></v-radio>
                                    </v-radio-group>
                                </v-list-item>
                                <v-list-item v-else>
                                    <v-radio-group
                                     v-model="select_langue_fr"
                                      row
                                    >
                                        <v-radio
                                            disabled
                                            label="Français"
                                            value="f"
                                        ></v-radio>
                                    </v-radio-group>
                                </v-list-item>
                                <!-- <button @click="accepterdemande(document.id)">Accepter demande </button> -->
                                <v-card-actions v-if="test && test.etat==0">
                                    <v-btn
                                        outlined
                                        rounded
                                        color="warning"
                                    >
                                      En cours
                                    </v-btn>
                                </v-card-actions>
                                <v-card-actions v-else-if="test && test.etat==1">
                                <v-btn
                                    outlined
                                    rounded
                                    :loading="loading_download"
                                    color="success"
                                    @click="GenerPdf((document.Type).split(' ')[0],document.id,document.demandes[0].Langue)"
                                >
                                    Download 
                                </v-btn>
                                </v-card-actions>
                                <v-card-actions v-else>
                                    <v-btn
                                        outlined
                                        rounded
                                        :loading="loading_demander"
                                        color="primary"
                                        :disabled="(document.NombreDispo-test?.nombre)<=0 ? true : false"
                                        @click="reserve(document.id)"
                                    >
                                        demande
                                    </v-btn>
                                    </v-card-actions>
                            </v-card>
                        </v-flex>

            </v-flex>
        </v-layout>
    </div>
</template>
<script>
import {AuthUser} from "@/store/AuthStore";
// import jsPDF from 'jspdf';
// import fr from "@/languages/fr.js"
// import ar from "@/languages/ar.js"
// import 'jspdf-autotable'
// import {AmiriRegular} from "@/assets/fontArabic/amiri.js";
import service_doc from "@/service/DocumentService/document"
import userinfo from "@/service/UserInfo/userInfo"
import "@/plugins/axios"
export default {
    name:'document',
   created(){
       this.getDocument();
   },
   setup(){
        const store=AuthUser();
        return{store}
      },
   name:'Document',
    data: () => ({
        All_Document:[],
        loading1: false,
        loding_page:false,
        loading_demander:false,
        loading_download:false,
        loading2: false,
        select_langue_fr:'f',
        langDocument:"",
        loading3: false,
        All_demandes:[],
        selection: 1,
        classe_current:"",
        spec:"",
        niveau:0,
        select_langue:"f",
        
    }),

    methods: {
        getDocument(){
            service_doc.getAllDocment().then((res)=>{
                this.All_Document=res.data;
                this.loding_page=true;
                console.log(this.All_Document);
            }).catch((err)=>{
                console.log(err);
            })
        }
    },
}
</script>
<style scoped>
    .v-card{
        max-width: 351px;
        height: 420px; 
    }
    /* .titre{
        margin-top: 50px;
   color: #444444;
   font-family: Georgia, serif;
   font-size: 30px;
   letter-spacing: 2px;
   word-spacing: 2px;
   color: #000000;
   font-weight: normal;
   text-decoration: none;
   font-style: normal;
   font-variant: normal;
   text-transform: none;
 } */
    
</style>