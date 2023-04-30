<template>
    <div  class="ma-5 mb-5">
        <v-container >
            <v-row>
              <h2>
                <span style="color: #5094df">{{
                  (store_view.getView).toUpperCase().substring(0, (store_view.getView).length - 3)
                }}</span
                >{{
                  (store_view.getView).toUpperCase().substring(
                    (store_view.getView).length,
                    (store_view.getView).length - 3
                  )
                }}
              </h2>
              <v-spacer></v-spacer>
              <v-menu offset-y
                
              >
                  <template v-slot:activator="{ on, attrs }">
                   <v-btn
                   plain
                   v-bind="attrs"
                   v-on="on"
                   @click="chagerEtatNotif"
                   >
                      <v-icon size="35px">mdi-bell</v-icon>
                      <v-badge color="red" :content="getNbrNotifNotSeen!=0 ? getNbrNotifNotSeen : '0'"

                      ></v-badge>
                   </v-btn>
                   
                  </template>
                  <v-list v-if="notifications.length==0">
                  <v-list-item  class="mt-5">
                      <v-list-item-title>No notifications !</v-list-item-title>
                  </v-list-item>
                 </v-list>
                  <v-list v-else>
                  <v-list-item
                      v-for="notif in notifications" :key="notif.id"
                  >
                      <v-list-item-title>{{ notif.msg }}</v-list-item-title>
                      </v-list-item>
                      <v-list-item class="mt-5">
                          <v-btn 
                          plain
                          v-if="notifications.length!=0"
                          @click="deleteAllNotif()"
                          >
                              <v-list-item-title class="red--text"> 
                                  
                                  Clear All<v-icon class="pa-1 red--text">mdi-delete</v-icon>
                              </v-list-item-title>
                          </v-btn>
                      </v-list-item>
                  </v-list>
              </v-menu>
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                 <v-btn
                 plain
                 v-bind="attrs"
                 v-on="on"
                 >
                    <v-avatar v-if="(store.user['Photo']).indexOf('storage')==-1" >
                         <span>{{store.user['Photo']}}</span>
                    </v-avatar>
                    <v-avatar v-else >
                       <img :src="'http://127.0.0.1:8000'+store.user['Photo']" alt="">
                  </v-avatar>
                 </v-btn>
                </template>
                <v-list>
                <v-list-item class="text-center"
                >
                    <v-list-item-title>{{store.user['FirstName']}}</v-list-item-title>
                    </v-list-item>
                </v-list>
                <v-list-item>
                    <v-list-item-action>
                        <v-btn plain @click="changerView('edit')">
                            <v-icon class="pa-2">mdi-account-cog</v-icon>
                            <span class="">Setting</span>
                        </v-btn>
                    </v-list-item-action>
                </v-list-item>
                <v-list-item>
                  <v-list-item-action>
                      <v-btn plain @click="logout()">
                          <v-icon class="pa-2">mdi-logout</v-icon>
                          <span class="">log out</span>
                      </v-btn>
                  </v-list-item-action>
              </v-list-item>
            </v-menu>
              </v-row>
        </v-container>
    </div>
</template>

<script>
import gererNotifEtud from "@/service/NotifEtudiant/gererNotifEtud"
import {AuthUser} from "@/store/Store.js";
import {CurentView} from "@/store/StoreView.js";
export default {
  created(){
            this.getNotifs();
            this.getNotifsNotSeen();
         },
  setup(){
     const store = AuthUser();
     const store_view = CurentView();
     return {store,store_view};
  },
  data(){
    return{
          notifications:[],
          notificationNotSeen:[],
    }
  },
  methods:{
      changerView(data){
        this.store_view.suppView();
        this.store_view.setView(data);
      },
       logout(){
            this.store_view.suppView();
            this.store.logout();
            this.$router.push({name:'signin'});
        },
        getNotifs(){
            gererNotifEtud.getNotifEtud().then((res)=>{
                for(let i=0;i<(res.data.data).length;i++){
                    this.notifications.push({idNotif:res.data.data[i].id,msg:res.data.data[i].message,etat:res.data.data[i].etat})
                }
            }
            )
        },
        deleteAllNotif(){
            gererNotifEtud.deleteAllNotif().then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
            })
        },
        chagerEtatNotif(){
            gererNotifEtud.updateNotif();
        },
        getNotifsNotSeen(){
            gererNotifEtud.getNotifNotSeen().then((res)=>{
                for(let i=0;i<(res.data.data).length;i++){
                    this.notificationNotSeen.push({idNotif:res.data.data[i].id,msg:res.data.data[i].message,etat:res.data.data[i].etat})
                }
            })
         },
  },
  computed:{
            getNbrNotif(){
                const nbrNotif=this.notifications.length
                return nbrNotif;
            },
            getNbrNotifNotSeen(){
                    const nbrNotifNotSeen=this.notificationNotSeen.length
                    return nbrNotifNotSeen;
            }
         }   
};

</script>

<style scoped>

</style>