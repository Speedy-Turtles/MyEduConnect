<template>
    <div class="">
      <v-container>

         <!-- --------------------------------------titre------------------------------- -->
         <div class="titrediv mb-5">
            <h2 color="red" class="titre">Timetable</h2>
         </div>
          <!-- --------------------------------------titre/------------------------------- -->


          <!-- --------------------------------------Emploi------------------------------ -->
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
            <!-- --------------------------------------Emploi/------------------------------ -->
            <!-- --------------------------------------titre------------------------------- -->
         <div class="titrediv mb-5">
            <h2 color="red" class="titre">Timetable</h2>
         </div>
          <!-- --------------------------------------titre/------------------------------- -->

      </v-container>
    </div>
    
  </template>
  <script>
  export default {
   data(){
      return{
         desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
          },
        ],
        seances:["      ","Séance 1","Séance 2","Séance 3","Séance 4","Séance 5","Séance 6"],
        jours:["Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"],
      }
   }
  }
 </script>
 <style scoped>
 .titrediv{
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
 </style>