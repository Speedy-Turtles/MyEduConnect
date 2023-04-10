<template>
    <div>
        <div v-if="!CheckSession" class="text-center mb-5">
            <v-card
            class="mx-auto"
            color="#26c6da"
            dark
            max-width="400"
          >
            <v-card-title>
              <v-icon
                large
                left
              >
                mdi-vote
              </v-icon>
              <span class="text-h6 font-weight-light">Vote</span>
            </v-card-title>
            <v-card-text class="text-h5 font-weight-bold">
              "Chose Your Chef"
            </v-card-text>
            <v-card-actions>
              <v-list-item class="grow">
                <v-list-item-content>
                    <v-btn @click="dialog=true" color="white" style="color:#26c6da">Start New Vote</v-btn>
                </v-list-item-content>
              </v-list-item>
            </v-card-actions>
          </v-card>
        </div>
        <div v-else> 
          <v-card
          class="mx-auto"
          color="#26c6da"
          dark
          max-width="400"
        >
          <v-card-title>
            <v-icon
              large
              left
            >
              mdi-vote
            </v-icon>
            <span class="text-h6 font-weight-light">{{session['Titre_Vote']}}</span>
            <v-spacer></v-spacer>
            <span >{{session['etat']==1 ? 'en cours' : session['etat']==0 ? 'suspence' : 'annuler'}}</span>
          </v-card-title>
          <v-card-text class="text-h5 font-weight-bold">
            Date Debut: {{ session['DateDebut'] }}
          </v-card-text>
          <v-card-text class="text-h5 font-weight-bold">
            Date Fin: {{ session['DateFin'] }}
          </v-card-text>
          <v-card-actions>
            <v-list-item class="grow">
              <v-chip @click="Suspende(session['id'])" color="info" class="mx-1">suspende</v-chip>
              <v-chip @click="annuler(session['id'])" color="red" class="mx-1">Annuler</v-chip>
              <v-row
                align="center"
                justify="end"
              >
                <v-icon class="mr-1">
                  mdi-account
                </v-icon>
                <span class="subheading">{{countnbrVoted}}</span>
              </v-row>
            </v-list-item>
          </v-card-actions>
        </v-card>
        </div>
            <v-dialog
                    v-model="dialog"
                        width="500"
                  >
              <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                  Add Vote Session
               </v-card-title>
         <form class="py-2 mt-2" @submit.prevent="startNewVote()">
          <div class="mx-5 px-5">
            <v-text-field
            name="Birth day"
            v-model="form.titre"
            :error-messages="titre_error"
            label="Titre Session"
            type="text"
            outlined
            dense
            placeholder="Enter Titre Session"
        ></v-text-field>
          </div>
          <div class="mx-5 px-5">
            <v-text-field
              name="Date Debut"
              v-model="form.date_debut"
              :error-messages="dateDebut_error"
              label="Date Debut"
              type="date"
              outlined
              dense
              placeholder="Enter Date Debut"
        ></v-text-field>
          </div>
          <div class="mx-5 px-5">
            <v-text-field
              name="Date Fin"
              v-model="form.date_fin"
              :error-messages="dateFin_error"
              label="Date Fin"
              type="date"
              outlined
              dense
              placeholder="Enter Date Fin"
        ></v-text-field>
          </div>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
          color="gray"
          text
          @click="dialog = false"
        >
          Close
        </v-btn>
          <v-btn
            color="primary"
            text
            @click="startNewVote()"
          >
            Confirm
          </v-btn>
        </v-card-actions>
      </form>
      </v-card>
    </v-dialog>
<div class="mt-5 py-2" >
 <v-card elavation="7" style="padding:25px">
     <h4 class="text-center py-4">List Votes</h4>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th  class="text-left">
                  Id
                </th>
                <th  class="text-left">
                  Titre Vote
                </th>
                <th  class="text-left">
                  Winner
                </th>
              </tr>
            </thead>
            <tbody >
              <tr >
                <td colspan="6" class="text-center">Not Found</td>
              </tr>
            </tbody>
          </template>  
        </v-simple-table>
      </v-card>
    </div>
    </div>
</template>
<script>

const dateValidator=(date)=>{
  return new Date(date).setHours(0, 0, 0, 0) >= new Date().setHours(0, 0, 0, 0);
};
import {required,minLength,maxLength} from "vuelidate/lib/validators"
import service_admin from "@/service/admin/gererAdmin.js";
import service_vote from "@/service/Vote/vote.js"
export default{
    name:"vote",
    mounted(){
      service_admin.test_Vote().then((res)=>{
          this.CheckSession=res.data.status;
          this.session=res.data.data;
          this.count(this.session['id']);
      })

      
    
    },
    validations:{
      form:{
        titre:{
          required,
          min:minLength(4),
          max:maxLength(10),
        },
        date_debut:{
          required,
          dateValidator,
        },
        date_fin:{
          required,
          dateValidator,
          minDate(val,{date_debut}){
            return( new Date(val).setHours(0,0,0,0)>=new Date(date_debut).setHours(0,0,0,0));
          }
        }
      }
    },
    data(){
        return{
            dialog:false,
            CheckSession:false,
            session:[],
            form:{
              titre:'',
              date_debut:'',
              date_fin:''
            },
            countnbrVoted:0
        }
    },
    methods:{
        startNewVote(){
          this.$v.form.$touch();
            if(this.$v.form.$invalid){
                this.loading=false;
                return;
            }
          service_admin.startVote({
                  titre:this.form.titre,
                  date_debut:this.form.date_debut,
                  date_fin:this.form.date_fin
                }).then((res)=>{
                  console.log(res.data);
                  this.dialog=false;
                }).catch((error)=>{
                  console.log(error.response);
                })
        },
        count(id){
          service_vote.countVoted(id).then((res)=>{
              this.countnbrVoted=res.data.data;
          })
        },
        Suspende(id){
          service_vote.suspende(id).then((res)=>{
             console.log(res.data);
          })
        },
        annuler(id){
          service_vote.annuler(id).then((res)=>{
             console.log(res.data);
          })
        }
    },
    computed:{
      titre_error(){
           const error=[];
           if(!this.$v.form.titre.$dirty) return error;
            !this.$v.form.titre.required && error.push("Titre required");
            !this.$v.form.titre.min && error.push("Please enter Titre with a minimum of 4 characters");
            !this.$v.form.titre.max && error.push("Please enter Titre with a maximum of 10 characters");
            return error;
      },
      dateDebut_error(){
         const error=[];
           if(!this.$v.form.date_debut.$dirty) return error;
            !this.$v.form.date_debut.required && error.push("Date Debut required");
            !this.$v.form.date_debut.dateValidator && error.push("Please enter Date Valid");
            return error;
      },
      dateFin_error(){
         const error=[];
           if(!this.$v.form.date_fin.$dirty) return error;
            !this.$v.form.date_fin.required && error.push("Date Fin required");
            !this.$v.form.date_fin.dateValidator && error.push("Please enter Date Fin Valid");
            !this.$v.form.date_fin.minDate && error.push("Please enter Date after Date Debut");
            return error;
      }
    }
}
</script>

<style scoped>
.content{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 60vh;
}
</style>