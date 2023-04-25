<template>
    <div class="">
      <v-container fluid>
         <!-- --------------------------------------search------------------------------ -->
         
         <div class="serach">
               <v-layout flex wrap>
                  <v-flex xl4 md4 lg4 sm12 xs12 class="pa-5">
                     <div class="container-input">
                        <input type="text" placeholder="Search By Name" name="text" class="input">
                        <svg fill="#000000" width="20px" height="20px" viewBox="0 0 1920 1920" xmlns="http://www.w3.org/2000/svg">
                           <path d="M790.588 1468.235c-373.722 0-677.647-303.924-677.647-677.647 0-373.722 303.925-677.647 677.647-677.647 373.723 0 677.647 303.925 677.647 677.647 0 373.723-303.924 677.647-677.647 677.647Zm596.781-160.715c120.396-138.692 193.807-319.285 193.807-516.932C1581.176 354.748 1226.428 0 790.588 0S0 354.748 0 790.588s354.748 790.588 790.588 790.588c197.647 0 378.24-73.411 516.932-193.807l516.028 516.142 79.963-79.963-516.142-516.028Z" fill-rule="evenodd"></path>
                        </svg>
                     </div>
                  </v-flex>
                  <v-flex xl4 md4 lg4 sm6 xs12>
                     <v-select
                     class="mr-5 pa-5"
                     :items="jours"
                     label="Day"
                     
                     ></v-select>
                  </v-flex>
                  <v-flex xl4 md4 lg4 sm6 xs12>
                     <v-select
                     class="mr-5 pa-5"
                     :items="seances2"
                     label="Session"
                     >
                     </v-select>
                  </v-flex>
               </v-layout>
         </div>
         <!-- --------------------------------------Emploi------------------------------ -->
         <div class="emploi">
         <!-- --------------------------------------titre------------------------------- -->
         <div class="titrediv mb-5">
            <h2 color="red" class="titre">Timetable</h2>
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
                  <tr
                     v-for="jour in jours"
                  >
                     <td class="pa-5">{{ jour }}</td>
                     <td v-for="i in 6" class="pa-3">Poo<br>A.Chaloueh <span>I0.9</span></td>
                  </tr>
                  </tbody>
               </template>
            </v-simple-table>
         </div>
            <v-btn color="blue darken-1 mt-10 float-end pa-5">
               Generate Emploi
            </v-btn>
         </div>
            <!-- --------------------------------------Emploi/------------------------------ -->
      </v-container>
    </div>
    
  </template>
  <script>
  export default {
   data(){
      return{
        seances:["      ","Séance 1","Séance 2","Séance 3","Séance 4","Séance 5","Séance 6"],
        seances2:["Séance 1","Séance 2","Séance 3","Séance 4","Séance 5","Séance 6"],
        jours:["Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"],
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
  border: solid 1px #333;
  transition: all .2s ease-in-out;
  outline: none;
  opacity: 0.8;
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



 </style>