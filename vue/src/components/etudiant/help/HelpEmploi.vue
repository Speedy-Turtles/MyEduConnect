<template>
    <div class="content">
      <v-container fluid>
         <!-- --------------------------------------search------------------------------ -->
         
         <div class="serach">
               <v-layout flex wrap>

                  <v-flex xl3 md3 lg3 sm6 xs12 class="pa-5">
                     <div class="container-input">
                        <input type="text" placeholder="Search By Name" name="text" class="input">
                        <svg fill="#000000" width="20px" height="20px" viewBox="0 0 1920 1920" xmlns="http://www.w3.org/2000/svg">
                           <path d="M790.588 1468.235c-373.722 0-677.647-303.924-677.647-677.647 0-373.722 303.925-677.647 677.647-677.647 373.723 0 677.647 303.925 677.647 677.647 0 373.723-303.924 677.647-677.647 677.647Zm596.781-160.715c120.396-138.692 193.807-319.285 193.807-516.932C1581.176 354.748 1226.428 0 790.588 0S0 354.748 0 790.588s354.748 790.588 790.588 790.588c197.647 0 378.24-73.411 516.932-193.807l516.028 516.142 79.963-79.963-516.142-516.028Z" fill-rule="evenodd"></path>
                        </svg>
                     </div>
                  </v-flex>
                  <v-flex xl3 md3 lg3 sm6 xs12>
                     <v-select
                     class="mr-5 pa-5"
                     :items="jours"
                     label="Day"
                     
                     ></v-select>
                  </v-flex>
                  <v-flex xl3 md3 lg3 sm6 xs12>
                     <v-select
                     class="mr-5 pa-5"
                     
                     label="Session"
                     >
                     </v-select>
                  </v-flex>
                  <v-flex xl3 md3 lg3 sm6 xs12 class="pa-5">
                     <button id="search">
                        <div class="svg-wrapper-1">
                           <div class="svg-wrapper">
                              <svg height="24" width="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                              <path d="M0 0h24v24H0z" fill="none"></path>
                              <path d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z" fill="currentColor"></path>
                              </svg>
                           </div>
                        </div>
                        <span>Search</span>
                     </button>
                  </v-flex>
               </v-layout>
         </div>
         <!-- --------------------------------------Emploi------------------------------ -->
         <div class="emploi">
         <!-- --------------------------------------titre------------------------------- -->
         <div class="titrediv mb-5">
            <h2 color="red" class="titre">Timetable : {{ classe }}</h2>
         </div>
          <!-- --------------------------------------titre/------------------------------- -->
         <div class="emploiTable">
            <v-simple-table>
               <template v-slot:default>
                  <thead>
                  <tr>
                     <th class="text-left pa-5" v-for="seance in seances">
                        {{seance}}
                     </th> 
                  </tr>
                  </thead>

                  
                  <tbody class="pa-5">
                  <tr>
                      <td class="pa-5">Lundi</td>
                      <td class="pa-5" v-for="lun in lundi">
  
                        <div class="div2" v-if="lun.length>0">
                          <v-layout row class="pa-2">
                            <v-flex justify-center><span class="duree align-center"> {{ lun[0].numero }}</span> </v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex><span class="matiere">{{lun[0].matiere.nom}}</span></v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="nom">{{(lun[0].user.FirstName).substring(0,1)}}.{{ lun[0].user.LastName }}</span> - <span class="salle">{{ lun[0].salle.nom }}</span> </v-flex>
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="salle"></span></v-flex>
                          </v-layout> 
                        </div>

                        <div class="" v-else>
                          <v-divider></v-divider>
                        </div>
                            
                      </td> 
                  </tr>

                  <tr>
                      <td class="pa-5">Mardi</td>
                      <td class="pa-5" v-for="mar in mardi">
  
                        <div class="div2" v-if="mar.length>0">
                          <v-layout row class="pa-2">
                            <v-flex justify-center><span class="duree align-center"> {{ mar[0].numero }}</span> </v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex><span class="matiere">{{mar[0].matiere.nom}}</span></v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="nom">{{(mar[0].user.FirstName).substring(0,1)}}.{{ mar[0].user.LastName }}</span> - <span class="salle">{{ lun[0].salle.nom }}</span> </v-flex>
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="salle"></span></v-flex>
                          </v-layout> 
                        </div>

                        <div class="pa-5" v-else>
                          <v-divider></v-divider>
                        </div>
                      </td> 
                  </tr> 
                  

                  <tr>
                      <td class="pa-5">Mercredi</td>
                      <td class="pa-5" v-for="mer in mercredi">
  
                        <div class="div2" v-if="mer.length>0">
                          <v-layout row class="pa-2">
                            <v-flex justify-center><span class="duree align-center"> {{ mer[0].numero }}</span> </v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex><span class="matiere">{{mer[0].matiere.nom}}</span></v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="nom">{{(mer[0].user.FirstName).substring(0,1)}}.{{ mer[0].user.LastName }}</span> - <span class="salle">{{ lun[0].salle.nom }}</span> </v-flex>
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="salle"></span></v-flex>
                          </v-layout> 
                        </div>

                        <div class="pa-5" v-else>
                          <v-divider></v-divider>
                        </div>
                            
                      </td> 
                  </tr>
                  
                  <tr>
                      <td class="pa-5">Jeudi</td>
                      <td class="pa-5" v-for="jeu in jeudi">
  
                        <div class="div2" v-if="jeu.length>0">
                          <v-layout row class="pa-2">
                            <v-flex justify-center><span class="duree align-center"> {{ jeu[0].numero }}</span> </v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex><span class="matiere">{{jeu[0].matiere.nom}}</span></v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="nom">{{(jeu[0].user.FirstName).substring(0,1)}}.{{ jeu[0].user.LastName }}</span> - <span class="salle">{{ lun[0].salle.nom }}</span> </v-flex>
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="salle"></span></v-flex>
                          </v-layout> 
                        </div>

                        <div class="pa-5" v-else>
                          <v-divider></v-divider>
                        </div>
                            
                      </td> 
                  </tr>

                  <tr>
                      <td class="pa-5">Vendredi</td>
                      <td class="pa-5" v-for="ven in vendredi">
  
                        <div class="div2" v-if="ven.length>0">
                          <v-layout row class="pa-2">
                            <v-flex justify-center><span class="duree align-center"> {{ ven[0].numero }}</span> </v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex><span class="matiere">{{ven[0].matiere.nom}}</span></v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="nom">{{(ven[0].user.FirstName).substring(0,1)}}.{{ ven[0].user.LastName }}</span> - <span class="salle">{{ lun[0].salle.nom }}</span> </v-flex>
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="salle"></span></v-flex>
                          </v-layout> 
                        </div>

                        <div class="pa-5" v-else>
                          <v-divider></v-divider>
                        </div>
                            
                      </td> 
                  </tr>

                  <tr>
                      <td class="pa-5">Samedi</td>
                      <td class="pa-5" v-for="sam in samedi">
  
                        <div class="div2" v-if="sam.length>0">
                          <v-layout row class="pa-2">
                            <v-flex justify-center><span class="duree align-center"> {{ sam[0].numero }}</span> </v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex><span class="matiere">{{sam[0].matiere.nom}}</span></v-flex>
                          </v-layout>
                          <v-layout row class="pa-2">
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="nom">{{(sam[0].user.FirstName).substring(0,1)}}.{{ sam[0].user.LastName }}</span> - <span class="salle">{{ lun[0].salle.nom }}</span> </v-flex>
                            <v-flex xl6 md6 lg6 sm6 xs6><span class="salle"></span></v-flex>
                          </v-layout> 
                        </div>

                        <div class="pa-5" v-else>
                          <v-divider></v-divider>
                        </div>
                            
                      </td> 
                  </tr>
                  </tbody>
               </template>
            </v-simple-table>
         </div>
         <button class="cssbuttons-io-button float-end mt-10" @click="gererEmploi()">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M1 14.5a6.496 6.496 0 0 1 3.064-5.519 8.001 8.001 0 0 1 15.872 0 6.5 6.5 0 0 1-2.936 12L7 21c-3.356-.274-6-3.078-6-6.5zm15.848 4.487a4.5 4.5 0 0 0 2.03-8.309l-.807-.503-.12-.942a6.001 6.001 0 0 0-11.903 0l-.12.942-.805.503a4.5 4.5 0 0 0 2.029 8.309l.173.013h9.35l.173-.013zM13 12h3l-4 5-4-5h3V8h2v4z"></path></svg>
            <span >Download</span>
         </button>
         </div>
            <!-- --------------------------------------Emploi/------------------------------ -->
      </v-container>
    </div>
    
  </template>
  <script>
  import emploiService from "@/service/Emploi/gererEmploi"
  import { AuthUser } from '@/store/Store';
  export default {
    setup(){
            const store=AuthUser();
            return{store}
        },
    created(){
      this.getEmploi();
    },
   data(){
      return{
        classe:"",
        jours:['Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
        lundi:[],
        mardi:[],
        mercredi:[],
        jeudi:[],
        vendredi:[],
        samedi:[],
        seances:{
          1:'            ',
          2:'8h30 - 10h00',
          3:'10h10 - 11h40',
          4:'11h50 - 13h20',
          5:'12h40 - 14h20',
          6:'14h20 - 15h50',
          7:'16h00 - 17h30',

        },
        num_seance:[1,2,3,4,5,6]


      }
   },
   methods:{
    getEmploi(){
      emploiService.getEmploi().then((res)=>{
        //this.jours=res.data.data;
        //console.log(this.jours);
        this.classe=res.data.classe[0]['nom'];
        console.log(this.classe);
        this.lundi=res.data.data['lundi'];
        console.log(this.lundi)
        //console.log(res.data.data['lundi']);
        this.mardi=(res.data.data['mardi']);
        this.mercredi=(res.data.data['mercredi']);
        this.jeudi=(res.data.data['jeudi']);
        this.vendredi=(res.data.data['vendredi']);
        this.samedi=(res.data.data['samedi']);
        //console.log(res.data.data)
        //console.log(res.data.data);
        //console.log(this.lundi[1][0].user['FirstName']);
      }).catch((err)=>{
        console.log(err);
      })
    },
    gererEmploi(){
      window.location.href = 'http://localhost:8000/api/pdf/gererEmploi/'+this.store.user['id'];
                setTimeout(() => {
                    window.location.href="http://localhost:8081/etudiant/help";
                 }, 2000);
    }
   }
  }
 </script>
 <style scoped>
 .titrediv{
   margin-top: 97px;
 }
 .serach{
   margin-top: 97px;
 }
 .titre{
   color: #444444;
   font-family: Georgia, serif;
   font-size: 25px;
   letter-spacing: 2px;
   word-spacing: 2px;
   color: #000000;
   font-weight: normal;
   text-decoration: none;
   font-style: normal;
   font-variant: normal;
   text-transform: none;
   text-shadow: 1px 0px 1px #CCCCCC, 0px 1px 1px #EEEEEE, 2px 1px 1px #CCCCCC, 1px 2px 1px #EEEEEE, 3px 2px 1px #CCCCCC, 2px 3px 1px #EEEEEE, 4px 3px 1px #CCCCCC, -40px -40px 0px rgba(206,89,55,0.01);
 }

 .container-input {
  position: relative;
}

.input {
  width: 170px;
  padding: 10px 0px 10px 40px;
  border-radius: 9999px;
  border: solid 1px #5094df;
  transition: all .2s ease-in-out;
  outline: none;
  opacity: 0.8;
 
}
.input::placeholder{
   color: #525fe1;
}

.container-input svg {
  position: absolute;
  top: 50%;
  left: 10px;
  transform: translate(0, -50%);
}

.input:focus {
  opacity: 1;
  width: 300px;
}

#search {
  font-family: inherit;
  font-size: 15px;
  background: royalblue;
  color: white;
  padding: 0.7em 1em;
  padding-left: 0.9em;
  display: flex;
  align-items: center;
  border: none;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.2s;
}

#search span {
  display: block;
  margin-left: 0.3em;
  transition: all 0.3s ease-in-out;
}

#search svg {
  display: block;
  transform-origin: center center;
  transition: transform 0.3s ease-in-out;
}

#search:hover .svg-wrapper {
  animation: fly-1 0.6s ease-in-out infinite alternate;
}

#search:hover svg {
  transform: translateX(1.2em) rotate(45deg) scale(1.1);
}

#search:hover span {
  transform: translateX(5em);
}

#search:active {
  transform: scale(0.95);
}

@keyframes fly-1 {
  from {
    transform: translateY(0.1em);
  }

  to {
    transform: translateY(-0.1em);
  }
}

.cssbuttons-io-button {
  display: flex;
  align-items: center;
  font-family: inherit;
  font-weight: 500;
  font-size: 17px;
  padding: 0.8em 1.5em 0.8em 1.2em;
  color: white;
  background: #ad5389;
  background: linear-gradient(0deg, royalblue 0%, rgba(132,116,254,1) 100%);
  border: none;
  box-shadow: 0 0.7em 1.5em -0.5em #4d36d0be;
  letter-spacing: 0.05em;
  border-radius: 20em;
}

.cssbuttons-io-button svg {
  margin-right: 8px;
}

.cssbuttons-io-button:hover {
  box-shadow: 0 0.5em 1.5em -0.5em #4d36d0be;
}

.cssbuttons-io-button:active {
  box-shadow: 0 0.3em 1em -0.5em #4d36d0be;
}
.matiere{
  font-size: 15px;
  font-weight: bold;
}
.duree{
  font-size: 10px;
}
.nom{
  text-align: center;
  font-size: 10px;
}
.salle{
  font-size: 10px;
}
.emploi{
  margin-bottom: 400px;
}
.empty-col{
  background-color: #444444;
}
 </style>