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
                        <v-flex class="pa-5"><h1 class="titre">Document</h1></v-flex>
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
                        <v-flex xl6 md6 sm12 xs12 v-if="All_Document.length==0 ">
                            <div class="nodata">
                                <v-img src="../../../../public/etudiant/images/no-data-found-logo.png" width="550px"></v-img>
                            </div>
                        </v-flex>
                        <div class="div" v-else>
                            <v-flex  xl6 md6 sm12 xs12 v-for="document in All_Document " :key="document.id">
                            
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
                                            value="f"

                                        ></v-radio>
                                        <v-radio
                                            label="Arabe"
                                            value="a"
                                        ></v-radio>
                                    </v-radio-group>
                                </v-list-item>
                                <button @click="accepterdemande(document.id)">Accepter demande </button>
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
                        </div>
                        
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
        langDocument:"",
        loading3: false,
        All_demandes:[],
        selection: 1,
        classe_current:"",
        spec:"",
        niveau:0,
        select_langue:"",
    }),

    methods: {
        info_perosnnel(){
            infouser.GetNiveau().then((res)=>{
            this.niveau=res.data.data.specialite['niveau'];
            this.classe_current=res.data.data.nom;
            this.spec=res.data.data.specialite.type;
        }).catch((er)=>{
            console.log(er);
        })
        },
        translate(prop){
          return(this[this.langDocument][prop]);
        },
        generePrsenceArab(){
                var niveau="";
                if(this.niveau==1){
                     niveau=this.langDocument=="الاولة";
                    }else if(this.niveau==2){
                        niveau=this.langDocument=="الثانية";
                    }else{
                        niveau=this.langDocument=="الثالثة";
                    }
                    var attestation="الاجازة في تكنولوجيات الاعلامية";
                    var specialite_current="";
                    if(this.spec=="dsi"){
                        specialite_current=this.langDocument=="تطوير نضام المعلومات";
                    }else if(this.spec=="rsi"){
                        specialite_current=this.langDocument=="الثانية";
                    }else{
                        specialite_current=this.langDocument=="الثالثة";
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
        generePrsenceFrancais(){
            var niveau="";
                if(this.niveau==1){
                     niveau=this.langDocument=="premiere";
                    }else if(this.niveau==2){
                        niveau=this.langDocument=="deuxieme" ;
                    }else{
                        niveau=this.langDocument=="troisime" ;
                    }
                    var attestation="Licence en technologie de l'information";
                    var specialite_current="";
                    if(this.spec=="dsi"){
                        specialite_current=this.langDocument=="DSI";
                    }else if(this.spec=="rsi"){
                        specialite_current=this.langDocument=="RSi" ;
                    }else{
                        specialite_current=this.langDocument=="SEM" ;
                    }
                    const doc = new jsPDF();
                    doc.addFileToVFS('Amiri-Regular.ttf',AmiriRegular);
                    doc.addFont('Amiri-Regular.ttf', 'Amiri', 'normal');
                    doc.setFont('Amiri');
                    doc.text(this.translate('الجمهورية'),5,10);
                    doc.text(this.translate('وزارة'),5,20,{align:'justify'});
                    doc.text(this.translate('الادارة'),5,30);
                    doc.text(this.translate('المعهد'),100,40,{align:'center'})
                    doc.setFontSize(20);
                    doc.text(this.translate('شهادة'),100,50,{align:'center'});
                    doc.text('2023 - 2022',100,60,{align:'center'});
                    doc.setFontSize(18);
                    doc.text(`${this.translate("يشهد")} ${this.store.user['sex']=="Man" ?  `${this.translate('الطالب')}` :  `${this.translate('الطالبة')}` }`,3,75);
                    doc.text(`${this.translate('الاسم')}: ${this.store.user['FirstName']} `,5,90);
                    doc.text(`${this.translate("اللقب")}:${this.store.user['LastName']} `,5,100);
                    doc.text(`${this.translate("بطاقة")}:${this.store.user['Cin']}`,5,120);
                    doc.text(`${this.store.user['sex']=="Man" ? `${this.translate('مرسم')}` : `${this.translate('مرسمة')}` } ${this.translate('بالسنة')}  ${this.classe_current} : ${ niveau } ,  ${this.translate('الفوج')} `,5,130);
                    doc.text(`${this.translate("المولود")}:${this.store.user['Birth_day']},${this.translate("ب  تونس")}`,5,110);
                    doc.text( this.translate('الاجازة') +' :'+attestation,5,141);
                    doc.text( this.translate('الاختصاص') +' :'+specialite_current,5,151);
                    doc.text(this.translate('رقم')+' : '+ Math.ceil(Math.random(999999,99999999)),5,161);
                    doc.text(`${this.translate('تواصل')} ${this.store.user['sex']=="Man" ? `${this.translate('دراسته')}` : `${this.translate('دراستها')}`} ${this.translate("بانتضام")}`,5,175);
                    doc.text(`${this.translate('سلمت')} ${this.store.user['sex']=="Man" ? `${this.translate('المعني')}` : `${this.translate('المعنية')}`} ${this.translate("بالامر")}`,5,185);
                    doc.text(this.translate("بنزرت") + ' '+ (new Date().getFullYear())+'/'+(new Date().getMonth()+1)+"/"+(new Date().getDate()),5,201);
                    doc.text(this.translate("الكاتب"),5,213);
                    doc.text(this.translate("كمال"),5,222);
                    doc.setDrawColor(0, 0, 0);
                    doc.line(10, 240, 200, 240);//x,r,w,r
                    doc.setFontSize(12)
                    doc.text(this.translate("مركب"),5,248);
                    doc.text(this.translate("الهاتف"),5,255);
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
        GenerPdf(type,id,langue){
           console.log(langue)
            if(type=="Attestation"){
                if(langue==='a'){
                    this.langDocument="ar"
                    this.generePrsenceArab();
                }
                else{
                    this.langDocument="fr";
                    this.generePrsenceFrancais();
                }
            }else if(type=="Stage"){
                const doc = new jsPDF();
                doc.text("hello",40,450)
                doc.save("type.pdf");
            }
            this.inittialiser_demande(id);
            console.log(this.langDocument);
          },   
         
        init_doc(){
            service_doc.getAllDocment().then((res)=>{
                this.All_Document=res.data.data;
                this.loding_page=true;
                // console.log(this.All_Document[0].demandes[0].Langue)
            });
         },
        init_demande(){
            service_doc.getAlldemande().then((res)=>{
                 for(let i=0;i<(res.data.data)?.length;i++){
                     this.All_demandes.push({iddoc:res.data.data[i].document_id,etat:res.data.data[i].etat,nombre:res.data.data[i].nombre});
                 }
                 
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
        this.loading_demander=true;
        service_doc.addDocument({document_id:id,langue:this.select_langue}).then((res)=>{
             console.log(res.data);
             this.init_demande();
             this.init_doc();
             this.info_perosnnel();
             this.loading_demander=false;
        }).catch((errr)=>{
            console.log(errr.response.data);
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