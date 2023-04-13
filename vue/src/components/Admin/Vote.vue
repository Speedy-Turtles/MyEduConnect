<template>
    <div>
        <div  class="text-center mb-5">
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
            :loading="loading"
            @click="startNewVote()"
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
    Vote added
    <template v-slot:action="{ attrs }">
      <v-btn
        color="pink"
        text
        v-bind="attrs"
        @click="snackbar = false"
      >
        Close
      </v-btn>
    </template>
  </v-snackbar>
        <div v-if="session==''">
            <v-alert class="text-center"   border="top"
                color="red lighten-2"
                dark>
                  No Session
             </v-alert>
         </div>

        <div  v-else> 
            <div class="row">
               <div class="col-lg-4" v-for="data in session" :key="data.id">
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
            <span class="text-h6 font-weight-light">{{data['Titre_Vote']}}</span>
            <v-spacer></v-spacer>
            <span :style="data['etat']==1 ? 'color:green' : data['etat']==0 ? 'color:yellow' : 'color:'" >{{data['etat']==1 ? 'in progress' : data['etat']==0 ? 'suspended' : ''}}</span>
          </v-card-title>
          <v-card-text style="font-size:18px" class="text-center font-weight-bold">
            <p>
              Date Debut: {{ data['DateDebut'] }}
            </p>
             <p>
              Date Fin: {{ data['DateFin'] }}
             </p>
            <p>
               <v-btn @click="getListNominated(data['id'])" color="warning">List Nominated</v-btn>
            </p>
          </v-card-text>
          <v-card-actions>
            <v-list-item class="grow">
              <v-chip @click="Suspende(data['id'])" color="info" class="mx-1">{{data['etat']==1 ? 'suspended' : 'Start Vote'}}</v-chip>
              <v-chip @click="annuler(data['id'])" color="red" class="mx-1">Reject</v-chip>
              <v-chip @click="Terminer(data['id'])" color="primary" class="mx-1">Finished</v-chip>
              <v-row
                align="center"
                justify="end"
              >
                <v-icon class="mr-1">
                  mdi-account
                </v-icon>
                <span class="subheading">
                  {{ data['votes_count'] }} 
                </span>
              </v-row>
            </v-list-item>
          </v-card-actions>
        </v-card>
        </div>
       </div>
    </div>

    <v-dialog
      v-model="dialog_nominated"
      persistent
      max-width="290"
  >
  <div v-if="ListNominated==''">
    <v-card >
      <v-card-title class=" p-5 m-2 text-center">
           No User Nominated Yet
      </v-card-title>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="green darken-1"
          text
          @click="dialog_nominated=false"
        >
          Close
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
   <div  v-else>
    <v-card >
        <div v-for="user in ListNominated" :key="user['id']" class="mb-2" elavation="5">
          <v-card-title class=" justify-center">
            <v-avatar v-if="user['Photo'].indexOf('storage')==-1" >
               <span>{{user['Photo']}}</span>
           </v-avatar>
           <v-avatar v-else >
             <img :src="'http://127.0.0.1:8000'+user['Photo']" alt="">
           </v-avatar> 
         </v-card-title>
         <v-card-text style="font-weight:bold;text-align: center;">FirstName : {{ user['FirstName'] }} <br> LastName :{{user['LastName'] }}</v-card-text>
         <hr>
        </div>  
      <v-card-actions>
        <v-btn
        color="green darken-1"
        text
        @click="dialog_nominated=false"
      >
          Close
      </v-btn>
      </v-card-actions>
    </v-card>
  </div>
  </v-dialog>

<div class="mt-5 py-2" >
 <v-card elavation="7" style="padding:25px">
     <h4 class="text-center py-4">List Votes</h4>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th  class="text-center">
                  Id
                </th>
                <th  class="text-center">
                  Titre Vote
                </th>
                <th  class="text-center">
                  Winner
                </th>
                <th  class="text-center">
                  Operation
                </th>
              </tr>
            </thead>
            <tbody v-if="voteTerminer==''">
              <tr>
                <td colspan="6" class="text-center">Not Found</td>
              </tr>
            </tbody>
            <tbody v-else>
              <tr v-for="vote in voteTerminer" :key="vote.id">
                  <td class="text-center">{{vote['id']}}</td>
                  <td class="text-center" >{{vote['Titre_Vote']}}</td>
                  <td v-if="vote['etat']==2" class="text-center" > <v-chip color="red" style="color:#fff">Vote Rejected</v-chip> </td>
                  <td v-else class="text-center" >Talel</td>
                  <td  class="text-center" >
                     <v-btn @click="deleteSession(vote['id'])" style="color:#fff" color="red">Delete</v-btn>
                  </td>
              </tr>
            </tbody>
          </template>  
        </v-simple-table>
        <v-snackbar
        v-model="snackbar_delete"
      >
        Vote Session Deleted
        <template v-slot:action="{ attrs }">
          <v-btn
            color="pink"
            text
            v-bind="attrs"
            @click="snackbar_delete = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
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
    created(){
      service_admin.test_Vote().then((res)=>{
          this.CheckSession=res.data.status;
          this.session=res.data.data;
          this.countNbrVote();
      });
      service_vote.getSessionTerminer().then((res)=>{
        this.voteTerminer=res.data.data;
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
            loading:false,
            CheckSession:false,
            snackbar:false,
            snackbar_delete:false,
            ListNominated:[],
            dialog_nominated:false,
            session:[],
            voteTerminer:[],
            form:{
              titre:'',
              date_debut:'',
              date_fin:''
            },
            countnbrVoted:0
        }
    },
    methods:{
        Terminer(id){
          service_vote.TermenierSession(id).then((res)=>{
             console.log(res.data.data);
          })
        },
        startNewVote(){
          this.$v.form.$touch();
            if(this.$v.form.$invalid){
                return;
            }
          this.loading=true;
          service_admin.startVote({
                  titre:this.form.titre,
                  date_debut:this.form.date_debut,
                  date_fin:this.form.date_fin
                }).then((res)=>{
                  this.loading=false;
                  this.snackbar=true;
                  this.dialog=false;
                }).catch((error)=>{
                  console.log(error.response.data);
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
        },
        deleteSession(id){
          service_vote.deleteSession(id).then((res)=>{
             this.snackbar_delete=true;
          })
        },
        getListNominated(id){
          service_vote.getUserNomanitedByIdSession(id).then((res)=>{
            this.ListNominated=res.data.data[0]['users'];
            this.dialog_nominated=true;
          }).catch((error)=>{
            console.log(error.response);
          })
        },
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