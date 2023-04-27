
<template>
    <div class="main">
        <v-container class="py-5">
            <v-layout row wrap class="py-5 justify-space-around" >
                <v-flex xs12 md6 sm12  xl6 class="px-5" data-aos="fade-left">
                    
                        <div class="title">
                            <span class="title-word title-word-1">My</span>
                            <span class="title-word title-word-2">Edu</span>
                            <span class="title-word title-word-3">Connect</span>
                        </div>
                        <p class="p2">{{ p2 }}</p>
                    <p class="p1 hidden-sm-and-down">{{ p1 }}</p>
                </v-flex>
                <v-flex xs12 md6 sm12 xl4 data-aos="fade-right">
                    <img  v-if="selected" :src="selected"   id="img"  alt="" width="100%" height="350px" >
                </v-flex>
            </v-layout>
        </v-container>
            <!-- --------------------------------------section-2 ------------------------->
                <div class="section-2">
                    <v-container>
                            <div class="content">
                                <v-layout row wrap class="justify-content-center">
                                    <v-flex xl3 lg3 md3 sm6 xs6   class="pa-5" v-for="acteur in acteurs" :key="acteur.person">
                                        <h3 class="white--text justify-center">{{acteur.person}}  {{ acteur.nombre }}</h3>
                                    </v-flex>
                                </v-layout>
                            </div>
                    </v-container>
                </div>
           
        
            <!-- --------------------------------------section-2/ ------------------------->
            <v-container>
            <!-- --------------------------------------section-3 ------------------------->
                <v-layout row  wrap class="justify-space-between " id="">
                    <v-flex xl4 lg4 md6 sm12 xs12 class="pa-5 ml-5 align-center">
                        <div class="iset">
                            <p class="p-iset">L'Institut Supérieur des Etudes Technologiques de Bizerte</p>
                            <v-layout row>
                                    <v-flex xl8 lg6 md6 sm12 xs12 class="pt-5"><p>Un Institut supérieur des études technologiques est un institut universitaire tunisien</p></v-flex>
                                    <v-flex xl4 lg6 md6 sm12 xs12 class="hidden-sm-and-down"><img src="../../../../public/etudiant/images/logoiset.png" width="100%" height="150px"></v-flex>
                            </v-layout>
                        </div>
                    </v-flex>
               
                    <v-flex xl2 lg3 md4 sm6 xs12 class="pa-5" v-for="fonction in fonctions "
                                :key="fonction.titre"
                                >
                            <!-- -------------------card------------------------ -->
                            <v-card
                                class="mx-auto"
                                max-width="344"
                                height="100%"
                                elevation="7"
                                shaped
                                data-aos="fade-down"
                                
                            >
                            <v-list-item three-line>
                            <v-list-item-content>
                                <v-list-item-avatar
                                tile
                                size="70"
                            > <img :src="fonction.avatar" alt="" srcset="" >
                            <v-list-item-title class="text-h6 mb-1">
                                   <strong> {{fonction.titre}}</strong>
                                </v-list-item-title>
                            </v-list-item-avatar>
                                
                                <v-list-item-subtitle></v-list-item-subtitle>
                                <p class="fonction-desc">{{fonction.desc}}</p>
                            </v-list-item-content>
                            </v-list-item>
                        </v-card>
                        <!-- -------------------card/------------------------ -->
                </v-flex>
            </v-layout>
            <!-- --------------------------------------section-3/------------------------->
        </v-container>
        <div class="text-center" v-if="snackbar">

            <v-snackbar
            v-model="snackbar"
            :timeout="timeout"
            color="green--text"
            >
            Welcome {{store.user['FirstName']}} !
            <template v-slot:action="{ attrs }">
                <v-btn
                color="red"
                text
                v-bind="attrs"
                @click="snackbar = false"
                >
                Close
                </v-btn>
            </template>
            </v-snackbar>
          
        </div>
        
    </div>
   

</template>
<script>
import {AuthUser} from "@/store/Store.js";
export default {
    
    mounted(){
      
    },
    setup(){
        const store=AuthUser();
        return{store}
      },
data(){
    return{
        p1:' This platform is designed to facilitate communication and collaboration among students, thereby creating a sense of community and fostering academic success. In addition, Myeduconnect offers a wide range of resources and tools that are specifically tailored to the needs of college students, such as study guides, course materials, and career advice. ',
        p2:' is a website that caters specifically to college students .',
        selected:null,
        langDocument:"ar",
        classe_current:"",
        spec:"",
        niveau:0,
        acteurs:[
            {person:'Etudiants',nombre:'+50',icon:''},
            {person:'Enseignants',nombre:'+20',icon:''},
            {person:'Chef Departement',nombre:'+10',icon:''},
            {person:'Technecien',nombre:'+10',icon:''}
        ],
        fonctions:[
            {titre:'Document',desc:'Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla, sunt.',avatar:require('../../../../public/etudiant/images/document.png')},
            {titre:'Forums',desc:'Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla, sunt.',avatar:require('../../../../public/etudiant/images/forum.png')},
            {titre:'Clubs   ',desc:'Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla, sunt.',avatar:require('../../../../public/etudiant/images/club.png')},
        ],
        snackbar:false,
        timeout:3000
    }
},
props:['photos'],
methods:{
    translate(prop){
         return(this[this.langDocument][prop]);
    },
    randomPhoto(imgs){
        return imgs[Math.floor(Math.random()*this.photos.length)]
    },
   
},
created(){
    this.selected=this.randomPhoto(this.photos)
    this.snackbar=true
}
}
</script>
<style scoped>
.p2{
font-family: Verdana, Geneva, sans-serif;
font-size: 30px;
color: #000000;
font-weight: 400;
text-decoration: none solid rgb(68, 68, 68);
font-style: normal;
font-variant: small-caps;

}
h6{
    margin-left: 82px;
}
.p1{
font-family: Verdana, Geneva, sans-serif;
font-size: 15px;
text-align: justify;
color: #000000;
font-weight: 400;
text-decoration: none solid rgb(68, 68, 68);
font-style: normal;
font-variant: small-caps;
}
.main{
    margin-top: 70px;
}
#img{
    clip-path: polygon(0 0, 100% 7%, 100% 94%, 0 93%);

}

.title-word {
  animation: color-animation 4s linear infinite;
}

.title-word-1 {
  --color-1: #f66641;
  --color-2: #525fe1;
  --color-3: #ff60aa;
}

.title-word-2 {
  --color-1: #ffcf59;
  --color-2: #5094df;
  --color-3: #a6cd49;
}

.title-word-3 {
  --color-1: #ACCFCB;
  --color-2: #E4A9A8;
  --color-3: #ACCFCB;
}

@keyframes color-animation {
  0%    {color: var(--color-1)}
  32%   {color: var(--color-1)}
  33%   {color: var(--color-2)}
  65%   {color: var(--color-2)}
  66%   {color: var(--color-3)}
  99%   {color: var(--color-3)}
  100%  {color: var(--color-1)}
}

.content{
    padding-top: 70px;
}

.title {
  font-family: "Montserrat", sans-serif;
  font-weight: 800;
  font-size: 8.5vw;
  text-transform: uppercase;
}
.p-iset{
font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
font-size: 30px;
letter-spacing: 2px;
word-spacing: 2px;
color: #000000;
font-weight: normal;
text-decoration: none;
font-style: normal;
font-variant: normal;
text-transform: none;
}
.v-card{
    height: 150px;
}
.fonction-desc{
    font-size: 19px;
}

.section-2{
    background:#525fe1;
    width: 100%;
    height: auto;
    padding-bottom: 20px;
    margin-bottom: 50px;
    margin-top: 50px;
    clip-path: polygon(0 5%, 100% 0, 100% 100%, 100% 100%, 100% 100%, 0 93%, 0 21%);
}
