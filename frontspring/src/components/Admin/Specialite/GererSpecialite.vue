<template>
  <div v-if="updateSpec && !AddSepc ">
    <UpdateSpecVue
                    :Spec="SelectUpdateVal"
                    @close_update="close_update"
                    :updateSpec="updateSpec"
    ></UpdateSpecVue>
  </div >
  <div  v-else-if="!updateSpec && AddSepc ">
    <AddSpecialite
    @close_update="close_update"
    :updateSpec="updateSpec"
></AddSpecialite>
  </div>
    <div v-else>
        <v-card class="card" elavation="7" style="padding:25px">
            <!--<h4 class="text-center py-4">Specialite</h4> !-->
                  <div class="row gap-4 p-5 mt-3">
                      <div class="col-lg-3 mx-2 text-center">
                        <v-text-field
                            v-model="search"
                            @keyup="FetchData()"
                            append-icon="mdi-magnify"
                            label="Rechercher"
                            single-line
                            hide-details
                       ></v-text-field>  
                    </div>
                    
                      <div class="col-lg-1 mt-4 mx-2 text-center">
                        <v-select
                             color="blue"
                              v-model="per_page"  
                              :items="items"
                              @change="FetchData()"
                              outlined
                              dense
                      ></v-select>
                    </div>
                      <v-spacer></v-spacer>
                      <div class="col-lg-3  text-center">
                        <div class="row">
                          <div class="col-lg-12 text-center">
                            <v-tooltip bottom  >
                              <template v-slot:activator="{ on, attrs }">
                                  <v-btn
                                  @click="AddSepc=true"
                                  class="mb-5"
                                  v-bind="attrs"
                                  v-on="on"
                                  
                                  fab
                                  color="white"
                                >
                                  <v-icon size="28px">mdi-book-education</v-icon>
                                </v-btn>
                              </template>
                              <span>Add Specialte</span>
                            </v-tooltip>
                      </div>
                          </div> 
                      </div>
                      </div>
        <v-simple-table class="card">
          <template v-slot:default>
            <thead>
              <tr>
                <th  class="text-center ">
                  Id
                </th>
                <th  class="text-center ">
                  Niveau
                </th>
                <th  class="text-center ">
                  Type
                </th>
                <th class="text-center">
                  Operation
                </th>
              </tr>
            </thead>
            <tbody v-if="spec==''">
                <tr>
                   <td colspan="5" class="text-center">No data</td>
                </tr>
            </tbody>
            <tbody v-else>
                <tr v-for="val in spec" :key="val.id">
                   <td class="text-center">{{val.idspec}}</td>
                   <td class="text-center">{{val.niveau}}</td>
                   <td class="text-center">{{val.type}}</td>
                   <td class="text-center">
                       <v-btn color="error" @click="deleteSpec(val)"  class="mx-2">
                          <v-icon>
                            mdi-trash-can
                          </v-icon>
                       </v-btn>
                       <v-btn @click="UpdateSpec(val)"  color="warning" class="mx-2 ">
                          <v-icon>
                            mdi-circle-edit-outline
                          </v-icon>
                       </v-btn>
                       <v-tooltip bottom  >
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                              v-bind="attrs"
                              v-on="on"
                              @click="ShowAddClass(val)"
                              style="color:#fff !important"
                              color="blue"
                            >
                            <v-icon>mdi-plus</v-icon>
                          </v-btn>
                        </template>
                        <span>Add Classe</span>
                      </v-tooltip>
                   </td>
                </tr>
            </tbody>
             <div class="text-center mt-2">
              <v-btn small :disabled="testPrev==true"  @click="changerPage(pageCurrent-1 )">
                <v-icon>
                  mdi-chevron-left
               </v-icon>
             </v-btn> 
             <v-btn v-for="num in countPage" :key="num"
                @click="changerPage(num)"
                class="mx-2"
                color="primary"
                :disabled="num==pageCurrent"
                small
                x-small
              >
             {{num}}
           </v-btn>
              <v-btn small :disabled="testNext==true"  @click="changerPage(pageCurrent+1)">
                 <v-icon>
                    mdi-chevron-right
                 </v-icon>
              </v-btn>
            </div> 
          </template>  
          
        </v-simple-table>
      </v-card>
      <v-dialog  v-if="IdSpecSelected!=0"
      transition="dialog-bottom-transition"
      max-width="600"
      v-model="dialogAddclasse"
    >
     <template >
       <v-card>
         <v-toolbar
           color="blue"
           dark
         >Add Classe </v-toolbar>
         <v-card-text>
          <v-text-field
              name="Name"
              v-model="classe_name"
              required
              label="Name"
              type="text"
              placeholder="Enter Name"
            ></v-text-field>
         </v-card-text>
         <v-card-actions class="justify-end">
           <v-btn 
            :loading="load"
             text
              @click="AddClasse()"
           >Add</v-btn>
           <v-btn
           text
           @click="dialogAddclasse=false"
         >fermer</v-btn>
         </v-card-actions>
       </v-card>
     </template>
   </v-dialog> 

      <v-dialog  v-if="item_selected!=''"
      transition="dialog-bottom-transition"
      max-width="600"
      v-model="dialog"
    >
     <template >
       <v-card>
         <v-toolbar
           color="danger"
           dark
         >Supprimer Specialte </v-toolbar>
         <v-card-text>
           <div class="text-h2 pa-12">{{item_selected.type}} Niveau {{item_selected.niveau}}</div>
         </v-card-text>
         <v-card-actions class="justify-end">
           <v-btn 
             text
             :loading="load"
              @click="confrimDelete(item_selected.idspec)"
           >Supprimer</v-btn>
           <v-btn
           text
           @click="dialog=false"
         >fermer</v-btn>
         </v-card-actions>
       </v-card>
     </template>
   </v-dialog> 

   <v-dialog
                    v-model="dialog_classe"
                        width="500"
                  >
              <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                  Chose Classe
               </v-card-title>
        <form >
          <div class="mx-5 px-5">
            <v-select
                :items="specialtes"
                v-model="specialite_id"
                item-text="type"
                :error-messages="Specialite_error"
                item-value="idspec"
                @change="choseClasse()"  
                 label="chose Your Specialite"
            ></v-select>
          </div>

          <div class="mx-5 px-5">
            <v-select
                :disabled="specialite_id=='' ? true : false "
                :items="classes"
                v-model="classe_id"
                :error-messages="classe_error"
                item-text="nom"
                item-value="idclasse"
                label="chose Your Classe"
            ></v-select>
          </div>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
          color="gray"
          text
          @click="dialog_classe = false"
        >
          Close
        </v-btn>
          <v-btn
            color="primary"
            text
            @click="ConfirmClasse()"
          >
            Confirm
          </v-btn>
        </v-card-actions>
      </form>
      </v-card>
    </v-dialog>
  <v-snackbar
   v-model="snackbar"
 >
    {{ message }}
   <template v-slot:action="{ attrs }">
     <v-btn
       color="indigo"
       text
       v-bind="attrs"
       @click="snackbar = false"
     >
       Fermer
     </v-btn>
   </template>
 </v-snackbar>
    </div>
</template>

<script>
import {required} from "vuelidate/lib/validators";
import AddSpecialite from "@/components/Admin/Specialite/AddSpec.vue"
import UpdateSpecVue from "@/components/Admin/Specialite/UpdateSpec.vue"
import serviceSpec from "@/service/GereSpecialite/gereSpec.js"
export default{
    name:'spec',
    validations:{
      specialite_id:{
        required
      },
      classe_id:{
        required
      }
      
    },
    created(){
      this.FetchData();
    },
    data() {
        return {
          spec:[],
          search:"",
          dialog_classe:false,
          message:"",
          snackbar:false,
          dialogAddclasse:false,
          specialite_id:"",
          classe_id:"",
          specialtes:[],
          classes:[],
          IdSpecSelected:0,
          SelectUpdateVal:[],
          specialite_choisie:"",
          countPage:[],
          item_selected:[],
          updateSpec:false,
          per_page:2,
          items:[2,5,10,15],
          pageCurrent:0,
          load:false,
          classe_name:"",
          testNext:true,
          AddSepc:false,
          testPrev:true,
          dialog:false
        }
    },
    methods: {
      ShowAddClass(val){
        this.dialogAddclasse=true;
        this.IdSpecSelected=val.idspec;
      },
      AddClasse(){
        this.load=true;
        serviceSpec.AddClasse({
          "name":this.classe_name,
          "id":this.IdSpecSelected
        }).then((res)=>{
          this.load=false;
           this.dialogAddclasse=false;
           this.IdSpecSelected=0;
           this.FetchData();
           this.message="Classe Added";
           this.snackbar=true;
        })
      },
      close_update(){
        this.updateSpec=false;
        this.AddSepc=false;
        this.FetchData();
      },
      UpdateSpec(val){
        this.SelectUpdateVal=val;
        this.updateSpec=true;
      },
      FetchData(){
        serviceSpec.chercherSpec(this.pageCurrent,this.search,this.per_page).then((res)=>{
          this.countPage=res.data.count_page;
          this.spec=res.data.spec.content;
          this.pageCurrent=res.data.page;
          this.testNext=res.data.spec.last;
          this.testPrev=res.data.spec.first;
        })
      },
      changerPage(num){
        if(num<0){
          num=0;
        }
        this.pageCurrent=num;
        this.FetchData();
      },
      deleteSpec(val){
        this.item_selected=val;
        this.dialog=true;
      },
      confrimDelete(id){
        this.load=true;
        serviceSpec.deleteSpec(id).then((res)=>{
          if(res.data==true){
            this.load=false;
            this.item_selected=[];
            this.message="Delete speicialte with success";
            this.snackbar=true;
            this.dialog=false;
            this.FetchData();
          }else{
              this.dialog_classe=true;
              this.specialite_choisie=id;
              serviceSpec.GetAllSpec().then((res)=>{
                this.specialtes=res.data;
              })
              this.load=false;
          }
        
        })
      },
      choseClasse(){
        serviceSpec.GetClasse(this.specialite_id).then((res)=>{
                this.classes=res.data;
        })
      },
      ConfirmClasse(){
             this.$v.specialite_id.$touch();
              this.$v.classe_id.$touch();
              if(this.$v.specialite_id.$invalid && this.$v.classe_id.$invalid){
                   return;
              }
              serviceSpec.ChangerSpecialite(this.specialite_choisie,this.classe_id).then((res)=>{
                this.message="Classe Add To All Users in this Specialite";
                this.snackbar=true;
                this.dialog_classe=false;
                this.specialite_choisie="";
                this.dialog=false;
                this.load=false;
                this.FetchData();
              })
      }
    },
    components:{
      UpdateSpecVue,AddSpecialite
    },
    computed:{
         classe_error(){
              const error=[];
              if(!this.$v.classe_id.$dirty) return error;
              !this.$v.classe_id.required && error.push("Classe Required");
              return error;
          },
          Specialite_error(){
              const error=[];
              if(!this.$v.specialite_id.$dirty) return error;
              !this.$v.specialite_id.required && error.push("Specialite Required");
              return error;
          }
    }
}
</script>

<style scoped>
  .card{
    background-color: lightblue;
    font-size: 25px;
  }
  th{
    font-size: 25px;
  }
</style>