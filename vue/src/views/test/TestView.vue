<template>
    <div class="mt-5 p-5">
        <v-menu  class="mt-5" offset-y>
            <template v-slot:activator="{ on, attrs }">
             <v-btn
                plain
                v-bind="attrs"
                v-on="on"
             > 
               <v-badge  :content="notif_yet.length ? notif_yet.length : '0'" color="green">
                <v-icon @click="ShowAllNotif()" >mdi-bell</v-icon>
              </v-badge>
             </v-btn>
            </template>
            <v-list>
            <v-list-item
            >
                <v-list-item-title  v-for="notif in All_notif" :key="notif.id" >
                    <p>
                      {{ notif.message }}
                    </p>
                </v-list-item-title>
                </v-list-item>
            </v-list>
        </v-menu>
        <div v-if="session==''">
          <v-alert class="text-center"   border="top"
              color="red lighten-2"
              dark>
                No Session
           </v-alert>
       </div>
        <div v-else> 
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
            </v-card-title>
            <v-card-text class="text-h5 font-weight-bold">
              Date Debut: {{ data['DateDebut'] }}
            </v-card-text>
            <v-card-text class="text-h5 font-weight-bold">
              Date Fin: {{ data['DateFin'] }}
            </v-card-text>
            <v-card-text>
            <p style="display: none;">
              {{ index_nominated = UserNominated.findIndex((v)=>v.iduser==store.user['id'] &&
                v.vote_session_id==data['id']) }}
                {{ index_voted = UserVoted.findIndex((v)=>v.iduser==store.user['id'] &&
                v.vote_session_id==data['id']) }}
            </p>
           </v-card-text>
            <v-card-actions>
              <v-list-item  class="grow text-center">
                    <v-btn  :disabled="index_nominated!=-1 ? true : false"   @click="nominated(data.id)" :loading="votedLoading" class="primary mx-2">Nominated</v-btn>
                    <v-btn  :disabled="index_voted!=-1 ? true : false ||  data['etat']==0 " :loading="dialog_nominated" @click="getListNominated(data['id'])" class="info">Vote</v-btn>
              </v-list-item>

            </v-card-actions>
          </v-card>
          </div>
          </div>
          </div>
          <v-dialog
              v-model="dialog_nominated"
              scrollable
              max-width="300px"
            >
              <v-card>
                <v-card-title>Select Nominated</v-card-title>
                <v-divider></v-divider>
                <v-card-text style="height: 300px;">
                  <v-radio-group
                    :error-messages="nominated_error"
                    v-model="SelectNominated"
                    column
                  >
                  <div v-for=" user  in ListNominated" :key="user['id']">
                    <v-toolbar  >
                        <v-toolbar-title>
                            <v-radio 
                                :label="user['FirstName']"
                                :value="user['id']"
                             ></v-radio>
                        </v-toolbar-title>
                        <v-spacer></v-spacer>
                          <v-menu
                          bottom
                          offset-y
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn
                              class="ma-2"
                              v-bind="attrs"
                              v-on="on"
                              text
                            >
                                <v-icon color="#4F5BD8">mdi-dots-vertical</v-icon>  
                                </v-btn>
                          </template>
                          <v-list>
                            <v-list-item
                            >
                              <v-list-item-title @click="selectUser(user['id'])">More Info</v-list-item-title>
                            </v-list-item>
                          </v-list>
                        </v-menu>
                     </v-toolbar>
                  </div>
                  </v-radio-group>
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog_nominated = false"
                  >
                    Close
                  </v-btn>
                  <v-btn
                    color="blue darken-1"
                    text
                    :loading="loading_vote"
                    @click="AddVote()"
                  >
                    Vote
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
    </div>
</template>
<script>
import {AuthUser} from "@/store/Store.js";
import service_notif from "../../service/notif/notif.js"
import service_admin from "@/service/admin/gererAdmin.js";
import servcie_notif from "@/service/notif/notif.js";
import service_vote from "@/service/Vote/vote.js";
import {required} from "vuelidate/lib/validators"
import service_info from "@/service/UserInfo/userinfo.js"
export default{
    validations:{
        SelectNominated:{
            required
        }
    },
    setup() {
        const store=AuthUser();
        return{store}
    },
    mounted(){
        this.allSession();

        service_vote.getUserNominated().then((res)=>{
            for(let i=0;i<(res.data.data).length;i++){
                this.UserNominated.push({iduser:res.data.data[i].user_id,vote_session_id:res.data.data[i].vote_session_id});
            }
        })

        service_vote.getUserVoted().then((res)=>{
          for(let i=0;i<(res.data.data).length;i++){
                this.UserVoted.push({iduser:res.data.data[i].user_id,vote_session_id:res.data.data[i].vote_session_id});
            }
        })

        service_notif.getnotif().then((res)=>{
            this.All_notif=res.data.data.Allnotif;
            this.notif_yet=res.data.data.notifyet;
        })

       /*service_vote.checkUser().then((res)=>{
           this.checkUserNominated=res.data.status;
       })*/
       
       service_vote.ListNominated().then((res)=>{
           this.ListNominated=res.data.data;
       })
    },
    data(){
        return{
            loading_vote:false,
            CheckSession:false,
            UserNominated:[],
            UserVoted:[],
            dialog_nominated:false,
            session:[],
            idsession:"",
            votedLoading:false,
            session_id:'',
            dialog:false,
            SelectNominated:'',
            checkUserVoted:false,
            notif_yet:[],
            All_notif:[],
            ListNominated:[],
            dialog2:false,
            selectUserMoreInfo:[]
        }
    },
    methods:{
      allSession(){
        service_admin.test_Vote().then((res)=>{
          this.CheckSession=res.data.status;
          this.session=res.data.data;
       })
      },
        ShowAllNotif(){
            servcie_notif.shownotif().then((res)=>{
                console.log(res.data.data);
            })
        },
        nominated(id){
            this.votedLoading=true;
            service_vote.AddUserNominated_Session({idsession:id}).then((res)=>{
                this.votedLoading=false;
                this.allSession();
            })
        },
        refreshDialog2(){
            this.selectUserMoreInfo=[];
            this.dialog2=false;
        },
        selectUser(id){
            service_info.GetUser(id).then((res)=>{
                this.selectUserMoreInfo=res.data.data;
                this.dialog2=true;
            })
        },
        AddVote(){
            this.$v.SelectNominated.$touch();
            if(this.$v.SelectNominated.$invalid){
                return;
            }
            this.loading_vote=true;
           service_vote.addvote({session_id:this.idsession,id_nominated:this.SelectNominated}).then((res)=>{
              console.log(res.data.data);
              this.loading_vote=false;
              this.dialog_nominated=false;
              this.idsession="";
           })
        },
        checkuservoted(id){
            service_vote.checkUserVoted(id).then((res)=>{
                this.checkUserVoted=res.data.status;
            })
        },
        getListNominated(id){
          service_vote.getUserNomanitedByIdSession(id).then((res)=>{
            this.ListNominated=res.data.data[0]['users'];
            this.dialog_nominated=true;
            this.idsession=res.data.data[0]['id'];
          }).catch((error)=>{
            console.log(error.response);
          })
        }
    },
    computed:{
        nominated_error(){
            const error=[];
            if(!this.$v.SelectNominated.$dirty) return error;
            !this.$v.SelectNominated.required && error.push("Nominated Required");
            return error;
        },
    }
}
</script>