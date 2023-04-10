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
        <div > 
            <v-card
                :disabled="session['etat']==0 ? true : false"
                v-if="session"
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
            </v-card-title>
            <v-card-text class="text-h5 font-weight-bold">
              Date Debut: {{ session['DateDebut'] }}
            </v-card-text>
            <v-card-text class="text-h5 font-weight-bold">
              Date Fin: {{ session['DateFin'] }}
            </v-card-text>
            <v-card-actions>
              <v-list-item  class="grow text-center">
                    <v-btn :disabled="checkUserNominated" @click="nominated(session.id)" class="primary mx-2">Nominated</v-btn>
                    <v-btn  :disabled="checkUserVoted" @click="dialog=true" class="info">Vote</v-btn>
              </v-list-item>
            <v-dialog
              v-model="dialog"
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
                  <div v-for="( list ) in ListNominated" :key="list.id">
                    <v-toolbar v-for="user in list.users" :key="user.id" class="mb-4" >
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
                    @click="dialog = false"
                  >
                    Close
                  </v-btn>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="AddVote()"
                  >
                    Vote
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

        <v-dialog
            v-if="selectUserMoreInfo!=''"
            v-model="dialog2"
            persistent
            max-width="290"
          >
            <v-card>
              <v-card-title class="text-h5 text-center">
                <v-avatar v-if="selectUserMoreInfo['Photo'].indexOf('storage')==-1" >
                    <span>{{selectUserMoreInfo['Photo']}}</span>
                </v-avatar>
                <v-avatar v-else >
                  <img :src="'http://127.0.0.1:8000'+selectUserMoreInfo['Photo']" alt="">
                </v-avatar>
              </v-card-title>
              <v-card-text class="text-h5">FirstName :{{ selectUserMoreInfo['FirstName'] }}</v-card-text>
              <v-card-text class="text-h5">LastName :{{selectUserMoreInfo['LastName'] }}</v-card-text>
              <v-card-text class="text-h5">Email :{{selectUserMoreInfo['email'] }}</v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="green darken-1"
                  text
                  @click="refreshDialog2()"
                >
                  Close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
            </v-card-actions>
          </v-card>
          </div>
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
        service_notif.getnotif().then((res)=>{
            this.All_notif=res.data.data.Allnotif;
            this.notif_yet=res.data.data.notifyet;
        })

        service_admin.test_Vote().then((res)=>{
          this.CheckSession=res.data.status;
          this.session=res.data.data;
          this.checkuservoted(this.session['id']);  
       })

       service_vote.checkUser().then((res)=>{
           this.checkUserNominated=res.data.status;
       })
       
       service_vote.ListNominated().then((res)=>{
           this.ListNominated=res.data.data;
       })

      

    },
    data(){
        return{
            CheckSession:false,
            checkUserNominated:false,
            session:[],
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
        ShowAllNotif(){
            servcie_notif.shownotif().then((res)=>{
                console.log(res.data.data);
            })
        },
        nominated(id){
            service_vote.AddUserNominated_Session({idsession:id}).then((res)=>{
                console.log(res.data.data);
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
           service_vote.addvote({session_id:this.session['id'],id_nominated:this.SelectNominated}).then((res)=>{
              console.log(res.data.data);
           })
        },
        checkuservoted(id){
            service_vote.checkUserVoted(id).then((res)=>{
                this.checkUserVoted=res.data.status;
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