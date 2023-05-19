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
                    <v-flex class="py-5 mt-5" v-if="loding_page==false">
                            <div class="text-center py-5 mt-5">
                                <v-progress-circular
                                  indeterminate
                                  color="primary"
                                ></v-progress-circular>
                                </div>
                    </v-flex>
                    <v-flex  xl6 md6 sm12 xs12 v-for="document in All_Document " :key="document.id">
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
                                <v-list-item>
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
                                <!-- <button @click="accepterdemande(document.id)">Accepter demande </button> -->
                                <v-card-actions v-if="demandeCheck==0">
                                    <v-btn
                                        outlined
                                        rounded
                                        color="warning"
                                    >
                                      En cours
                                    </v-btn>
                                </v-card-actions>
                                <v-card-actions v-else-if="demandeCheck==1">
                                <v-btn
                                    outlined
                                    rounded
                                    :loading="loading_download"
                                    color="success"
                                    @click="downloadFile()"
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
                                        :disabled="(document.nombreDispo-etat?.nombre)<=0 ? true : false"
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



export default {
    name:'document',
   created(){
       this.getDocument();
       this.getDemande();
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
        langDocument:"",
        loading3: false,
        All_demandes:[],
        niveau:0,
        select_langue:"f",
        etat:0,
        demandeCheck:[]
        
    }),

    methods: {
        getDocument(){
            service_doc.getAllDocment().then((res)=>{
                this.All_Document=res.data;
                 this.loding_page=true;
                console.log(this.All_Document[0].nombreDispo);
            }).catch((err)=>{
                console.log(err);
            })
        },
        reserve(id){
            service_doc.addDemande(id,this.select_langue).then((res)=>{
                console.log(res.data)
            }).catch((err)=>{
                console.log(err);
            })
        },
         downloadFile() {
        service_doc.gererPdf().then(response => {
            const blob = new Blob([response.data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'Document.pdf');
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            window.URL.revokeObjectURL(url);
            this.updateDemande();
          })
          .catch(error => {
              console.log(error);
          });
    },
    getDemandeById(id){
        service_doc.getDemande(id).then((res)=>{
            
            console.log(res.data[0].etat);
            this.etat=res.data[0].etat;
        }).catch((err)=>{
            console.log(err);
        })
    },
    getDemande(){
        service_doc.getDemandeUser().then((res)=>{
            this.demandeCheck=res.data.etat;
            console.log(this.demandeCheck);
        }).catch((err)=>{
            console.log(err);
        })
    },
    updateDemande(){
        service_doc.updateDemande().then((res)=>{
            console.log(res.data);
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