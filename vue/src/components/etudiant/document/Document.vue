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
                        <v-flex class="py-5 mt-5" v-if="loding_page==false">
                            <div class="text-center py-5 mt-5">
                                <v-progress-circular
                                  indeterminate
                                  color="primary"
                                ></v-progress-circular>
                                </div>
                        </v-flex>
                        <v-flex v-else xl6 md6 sm12 xs12 v-for="document in All_Document " :key="document.id">
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
                                    <p style="display: none;">
                                        {{ test=All_demandes.find(v=>v.iddoc==document.id) }}
                                   </p>
                                    <v-list-item-subtitle>Nombre Dispo : {{document.NombreDispo}}</v-list-item-subtitle>
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
                                            value="Français"

                                        ></v-radio>
                                        <v-radio
                                            label="Arabe"
                                            value="Arabe"
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
                                    @click="GenerPdf((document.Type).split(' ')[0],document.id)"
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
                    </v-layout>
            </v-flex>
        </v-layout>
    </div>
</template>
<script>
import {AuthUser} from "@/store/Store.js";
import jsPDF from 'jspdf';
import fr from "@/languages/fr.js"
import ar from "@/languages/ar.js"
import 'jspdf-autotable'
import {AmiriRegular} from "@/assets/fontArabic/amiri.js";
import service_doc from "@/service/Document/gererDocument.js"
import infouser from "@/service/UserInfo/userinfo.js"
export default {
   mixins:[ar,fr],
   created(){
        this.init_demande();
        this.init_doc();
        this.info_perosnnel();
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
        langDocument:"ar",
        loading3: false,
        All_demandes:[],
        selection: 1,
        classe_current:"",
        spec:"",
        niveau:0,
        select_langue:'Arabe',
    }),

    methods: {
        info_perosnnel(){
            infouser.GetNiveau().then((res)=>{
            this.niveau=res.data.data.specialite['niveau'];
            this.classe_current=res.data.data.nom;
            this.spec=res.data.data.specialite.type;
        })
        },
        translate(prop){
          return(this[this.langDocument][prop]);
        },
        generePrsence(){
                var niveau="";
                if(this.niveau==1){
                     niveau=this.langDocument=="ar" ? "الاولة" : "premiere";
                    }else if(this.niveau==2){
                        niveau=this.langDocument=="ar" ? "الثانية" : "deuxieme" ;
                    }else{
                        niveau=this.langDocument=="ar" ? "الثالثة" : "troisime" ;
                    }
                    var attestation="الاجازة في تكنولوجيات الاعلامية";
                    var specialite_current="";
                    if(this.spec=="dsi"){
                        specialite_current=this.langDocument=="ar" ? "تطوير نضام المعلومات" : "DSI";
                    }else if(this.spec=="rsi"){
                        specialite_current=this.langDocument=="ar" ? "الثانية" : "RSi" ;
                    }else{
                        specialite_current=this.langDocument=="ar" ? "الثالثة" : "SEM" ;
                    }
                    const doc = new jsPDF();
                    doc.addFileToVFS('Amiri-Regular.ttf',AmiriRegular);
                    doc.addFont('Amiri-Regular.ttf', 'Amiri', 'normal');
                    doc.setFont('Amiri');
                    doc.text(this.translate('الجمهورية'),158,10);
                    doc.text(this.translate('وزارة'),140,20);
                    doc.text(this.translate('الادارة'),140,30);
                    doc.text(this.translate('المعهد'),100,40,{align:'center'})
                    doc.setFontSize(20);
                    doc.text(this.translate('شهادة'),100,50,{align:'center'});
                    doc.text('2023 - 2022',100,60,{align:'center'});
                    doc.setFontSize(18);
                    doc.text(`${this.translate("يشهد")} ${this.store.user['sex']=="Man" ?  `${this.translate('الطالب')}` :  `${this.translate('الطالبة')}` }`,65,75);
                    doc.text(`${this.store.user['FirstName']} : ${this.translate('الاسم')}`,170,90);
                    doc.text(`${this.store.user['LastName']} : ${this.translate("اللقب")}`,170,100);
                    doc.text(`${this.store.user['Birth_day']} : ${this.translate("المولود")}`,143,110);
                    doc.text(this.translate("ب  تونس"),120,110);
                    doc.text(`${this.store.user['Cin']} : ${this.translate("بطاقة")}`,102,120);
                    doc.text(`${this.classe_current} ${this.store.user['sex']=="Man" ? `${this.translate('مرسم')}` : `${this.translate('مرسمة')}` } ${this.translate('بالسنة')}  : ${ niveau } ,  ${this.translate('الفوج')} `,123,130);
                    doc.text(`${this.store.user['Birth_day']} : ${this.translate("المولود")}`,143,110);
                    doc.text( this.translate('الاجازة') +' :'+attestation,111,141);
                    doc.text( this.translate('الاختصاص') +' :'+specialite_current,128,151);
                    doc.text(this.translate('رقم')+' : '+ Math.ceil(Math.random(999999,99999999)),172,161);
                    doc.text(`${this.translate('تواصل')} ${this.store.user['sex']=="Man" ? `${this.translate('دراسته')}` : `${this.translate('دراستها')}`} ${this.translate("بانتضام")}`,92,175);
                    doc.text(`${this.translate('سلمت')} ${this.store.user['sex']=="Man" ? `${this.translate('المعني')}` : `${this.translate('المعنية')}`} ${this.translate("بالامر")}`,68,185);
                    doc.text(this.translate("بنزرت") + ' '+ (new Date().getFullYear())+'/'+(new Date().getMonth()+1)+"/"+(new Date().getDate()),10,201);
                    doc.text(this.translate("الكاتب"),20,213);
                    doc.text(this.translate("كمال"),18,222);
                    doc.setDrawColor(0, 0, 0);
                    doc.line(10, 240, 200, 240);//x,r,w,r
                    doc.setFontSize(12)
                    doc.text(this.translate("مركب"),115,248);
                    doc.text(this.translate("الهاتف"),111,255);
                    doc.save(`${this.store.user['Cin']}.pdf`);    
        },
        inittialiser_demande(id){
            this.loading_download=true;
            service_doc.initailiser_demande({docId:id}).then((res)=>{
                 console.log(res.data);
                 this.init_demande();
                 this.init_doc();
                 this.info_perosnnel();
                 this.loading_download=false;
                 this.$router.go();
            })
          },
        GenerPdf(type,id){
           
            if(type=="Attestation"){
                this.generePrsence();
            }else if(type=="Stage"){
                const doc = new jsPDF();
                doc.text("hello",40,450)
                doc.save("type.pdf");
            }
            this.inittialiser_demande(id);
          },   
         
        init_doc(){
            service_doc.getAllDocment().then((res)=>{
                this.All_Document=res.data.data;
                this.loding_page=true;
            });
         },
        init_demande(){
            service_doc.getAlldemande().then((res)=>{
                 for(let i=0;i<(res.data.data).length;i++){
                     this.All_demandes.push({iddoc:res.data.data[i].document_id,etat:res.data.data[i].etat,nombre:res.data.data[i].nombre});
                 }
                 console.log(this.All_demandes);
            })
        },
        accepterdemande(iddoc){
            service_doc.AccepterDocument({docId:iddoc}).then((res)=>{
                 console.log(res.data);
                 this.init_demande();
                 this.init_doc();
                 this.info_perosnnel();
                 this.$router.go();
            })
        },
      reserve (id) {
        /*if(i==1){
            this.loading1 = true;
        }else if (i==2){
            this.loading2 = true;
        }else{
            this.loading3 = true;
        }
        setTimeout(() => (this.loading1 = false,this.loading2 = false,this.loading3 = false), 2000)*/
        this.loading_demander=true;
        service_doc.addDocument({document_id:id,langue:this.select_langue}).then((res)=>{
             console.log(res.data);
             this.init_demande();
             this.init_doc();
             this.info_perosnnel();
             this.loading_demander=false;
           //  this.$router.go();
        })
      },
    },
}
</script>
<style scoped>
    .v-card{
        max-width: 351px;
        height: 420px;     
    }
</style>