<template>
    <div>
        <v-card elavation="7" style="padding:25px">
            <h4 class="text-center py-4">Classes</h4>
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
                            v-model="per_page"
                            :items="items"
                            @change="FetchData()"
                            outlined
                            dense
                      ></v-select>
                    </div>
                      <v-spacer></v-spacer>
                      <div class="col-lg-4 mt-3 text-center">
                        <v-select
                               :items="specialtes"
                                v-model="specialite_id"
                                item-text="type"
                                item-value="idspec"
                                @change="FetchData()"  
                                label="chose Your Specialite"
                    ></v-select>
                  </div>
                      </div>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th  class="text-center ">
                  Id
                </th>
                <th  class="text-center ">
                  Name
                </th>
                <th  class="text-center ">
                  Specialite
                </th>
                <th class="text-center">
                  Operation
                </th>
              </tr>
            </thead>
            <tbody v-if="classes==''">
                <tr>
                   <td colspan="5" class="text-center">No data</td>
                </tr>
            </tbody>
            <tbody v-else>
                <tr v-for="val in classes" :key="val.idclasse">
                   <td class="text-center">{{val.idclasse}}</td>
                   <td v-if="update == 'update' + val.idclasse">
                    <form @submit.prevent="UpdateClasse(val.idclasse)" >
                        <v-text-field :error-messages="name_error" label="classe name"  class="mx-4"  v-model="updateName"></v-text-field>
                        <v-btn type="submit">
                            <v-icon>  mdi-circle-edit-outline</v-icon>
                        </v-btn>
                    </form>
                </td>
                <td v-else>{{ val.nom }}</td>
                   <td class="text-center">{{val.spec['type']}}</td>
                   <td class="text-center">
                       <v-btn @click="update = 'update' + val.idclasse"  color="warning" class="mx-2 ">
                          <v-icon>
                            mdi-circle-edit-outline
                          </v-icon>
                       </v-btn>
                       <v-btn color="error" @click="deleteSpec(val)">
                          <v-icon>
                               mdi-trash-can
                          </v-icon>
                       </v-btn>
                   </td>
                </tr>
            </tbody>
             <div style="display:wrap;justify-content: center;">
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
         >Supprimer Classe </v-toolbar>
         <v-card-text>
           <div class="text-h2 pa-12">Classe {{item_selected.nom}}</div>
         </v-card-text>
         <v-card-actions class="justify-end">
           <v-btn 
             text
             :loading="load"
              @click="confrimDelete(item_selected.idclasse)"
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
            :loading="load"
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
import serviceSpec from "@/service/GereSpecialite/gereSpec.js"
import gererClassee from "@/service/classe/classe.js"
export default{
  validations:{
      specialite_id:{
        required
      },
      classe_id:{
        required
      },
      updateName:{
        required
      }
    },
    name:'classe',
    created(){
             serviceSpec.GetAllSpec().then((res)=>{
                this.specialtes=res.data;
              })
             this.FetchData();
    },
    data(){
        return{
          spec:[],
          search:"",
          updateName:"",
          update:"",
          dialog_classe:false,
          message:"",
          snackbar:false,
          classe_id:"",
          item_selected:[],
          specialite_id:"",
          classe_choise:"",
          specialtes:[],
          specialite_id:"",
          classes:[],
          IdSpecSelected:0,
          countPage:[],
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
    methods:{
      deleteSpec(val){
        this.item_selected=val;
        this.dialog=true;
      },
      choseClasse(){
        serviceSpec.GetClasse(this.specialite_id).then((res)=>{
                this.classes=res.data;
        })
      },
      changerPage(num){
        if(num<0){
          num=0;
        }
        this.pageCurrent=num;
        this.FetchData();
      },
        FetchData(){
            gererClassee.chercherclasse(this.pageCurrent,this.search,this.per_page,this.specialite_id).then((res)=>{
                 this.countPage=res.data.count_page;
                 this.classes=res.data.classe.content;
                 this.pageCurrent=res.data.page;
                 this.testNext=res.data.classe.last;
                 this.testPrev=res.data.classe.first;
        })
      },
      confrimDelete(id){
        this.load=true;
        gererClassee.deleteClasse(id).then((res)=>{
          if(res.data==true){
            this.load=false;
            this.item_selected=[];
            this.message="Delete Classe with success";
            this.snackbar=true;
            this.dialog=false;
            this.FetchData();
          }else{
              this.dialog_classe=true;
              this.classe_choise=id;
              this.load=false;
              this.FetchData();
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
              gererClassee.ChangerClasse(this.classe_choise,this.classe_id).then((res)=>{
                this.message="Classe Add To All Users in this Specialite";
                this.snackbar=true;
                this.dialog_classe=false;
                this.classe_choise="";
                this.dialog=false;
                this.load=false;
                this.FetchData();
              })
              this.FetchData();
      },
      UpdateClasse(id){
              this.$v.updateName.$touch();
              if(this.$v.updateName.$invalid){
                   return;
              }
              gererClassee.UpdateClasse(id,this.updateName).then((res)=>{
                this.update="";
                this.updateName="";
                this.message=res.data;
                this.snackbar=true;
                this.FetchData();
              }).catch((eror)=>{
                console.log("error");
              })
      }
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
          },
        name_error(){
              const error=[];
              if(!this.$v.updateName.$dirty) return error;
              !this.$v.updateName.required && error.push("Name Required");
              return error;
          }
    }
}
</script>