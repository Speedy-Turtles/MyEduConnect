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
               
                transition="slide-x-transition" 
                left
                class="mt-7"
                max-width="auto"
                min-width="300"
                >
                
                    <template v-slot:activator="{ on, attrs }">
                     <v-btn
                     plain
                     v-bind="attrs"
                     v-on="on"
                     @click="chagerEtatNotif"
                     >
                        <v-icon size="35px ">mdi-bell</v-icon>
                        <v-badge color="red" :content="getNbrNotifNotSeen"
                        v-if="getNbrNotifNotSeen!=0"
                        :values="getNbrNotifNotSeen"
                        ></v-badge>
                     </v-btn>
                     
                    </template>
               
                        <v-list>
                            <v-list-item class="text-h5">
                                Notifications
                            </v-list-item>
                        </v-list>
                        <v-divider></v-divider>
                    <v-list v-if="notifications.length==0">
                    <v-list-item  class="mt-5 red--text">
                        
                        <v-list-item-title class="text-h7 ml-8 mb-8">No notifications is available</v-list-item-title>
                    </v-list-item>
                   </v-list>
                   
                    <v-list v-else>
                    <v-list-item
                        v-for="notif in notifications" :key="notif.id"
                    >
                   
                    
                        <!-- ---------------avatar-------------------- -->
                        <v-list-item-avatar>
                            <v-avatar v-if="notif.user_envoi_photo.length>2" size="43px">
                                <v-img  :src="'http://localhost:8000'+notif.user_envoi_photo"></v-img>
                            </v-avatar>
                            <v-avatar color="blue" v-else>
                                <span class="white--text">{{ notif.user_envoi_photo }}</span>
                            </v-avatar>
                        </v-list-item-avatar>
                        <!-- --------------- /avatar-------------------- -->

                         <!-- --------------- msg + date -------------------- -->
                        <v-list-item-content>
                            <v-list-item-title class="px-5 justify-center">{{ notif.msg }}<br><span class="date" v-if="notif.date!=null"> Since : {{ notif.date }}</span></v-list-item-title>
                        </v-list-item-content> 
                        <!-- --------------- /msg + date -------------------- -->

                        <!--------------------button----------------------->
                        <v-list-item-action>
                            <v-col cols="auto">
                            <v-dialog
                                transition="dialog-bottom-transition"
                                max-width="400"
                            >
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn
                                    plain
                                    @click="dialog==true"
                                    v-bind="attrs"
                                    v-on="on"
                                    >
                                        <v-icon>mdi-close</v-icon>
                                    </v-btn>
                                </template>
                                <template v-slot:default="dialog">
                                <v-card>
                                    <v-card-text>
                                    <div class="text-h5 pa-5">Delete Notification</div>
                                    </v-card-text>
                                    <v-card-actions class="justify-end">
                                    <v-btn
                                        text
                                        @click="dialog.value = false"
                                    >No</v-btn>
                                    <v-btn
                                        text
                                        @click="deleteNotificationById(notif.idNotif),dialog.value = false"
                                    >Delete</v-btn>
                                    </v-card-actions>
                                </v-card>
                                </template>
                            </v-dialog>
                            </v-col>
                        </v-list-item-action>
                        <!-------------------- /button----------------------->
                        
                    </v-list-item>
                    
                        <v-divider></v-divider>
                        <!-- <v-list-item class="mt-5">
                            <v-btn 
                            plain
                            v-if="notifications.length!=0"
                            @click="deleteAllNotif()"
                            >
                                <v-list-item-title class="red--text ml-15"> 
                                    Clear All<v-icon class="pa-1 red--text">mdi-delete</v-icon>
                                </v-list-item-title>
                            </v-btn>
                        
                        </v-list-item> -->
                    </v-list>
                </v-menu>
              
                <template v-slot:activator="{ on, attrs }">
                 <v-btn
                 plain
                 v-bind="attrs"
                 v-on="on"
                 >
                    <v-avatar>
                       <img :src="store.user['Photo']" alt="">
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
            
              <v-menu offset-y 
                transition="slide-x-transition" 
                left
                >
                    <template v-slot:activator="{ on, attrs }">
                    <v-btn   plain v-bind="attrs"
                     v-on="on"
                     >
                        <v-avatar v-if="store.user['Photo'].indexOf('storage')!=-1" size="43px">
                            <img :src="'http://localhost:8000'+store.user['Photo']" alt="" srcset="">
                         </v-avatar>

                         <v-avatar color="primary" v-else size="43px">
                             <span class="white--text">{{ store.user['Photo'] }}</span>
                         </v-avatar>
                     </v-btn>
                    </template>
                    <v-list>
                    <v-list-item>
                        <v-list-item-title>
                            <v-btn
                                plain
                                block
                                class="py-3"
                            >
                            {{store.user['FirstName']}}
                            </v-btn>
                        </v-list-item-title>
                     </v-list-item>
                    <v-list-item>
                        
                        <v-list-item-action>
                            <v-btn
                                plain
                                router to="/etudiant/EditProfilView"
                            >
                                <v-icon class="pa-2">mdi-wrench</v-icon>
                                <span class="">Settings</span>
                            </v-btn>
                        </v-list-item-action>
                        
                    </v-list-item>

                    <v-list-item v-if="test_ischef==true || store.Ischef=='true'">
                        <v-list-item-action>
                            <v-btn
                                plain
                            >
                                <v-icon class="pa-2">mdi-swap-horizontal</v-icon>
                                <span >
                                    <v-btn router to="/chef_departement">
                                        switch
                                    </v-btn>
                                </span>
                            </v-btn>
                        </v-list-item-action>
                    </v-list-item>

                    <v-list-item >
                        <v-list-item-action>
                            <v-btn
                                plain
                                @click="logout()"
                            >
                                <v-icon class="pa-2">mdi-logout</v-icon>
                                <span >log out</span>
                            </v-btn>
                        </v-list-item-action>
                    </v-list-item>

                    </v-list>
                </v-menu>  
            </v-row>
        </v-container>
    </div>
</template>

<script>
//import gererNotifEtud from "@/service/NotifEtudiant/gererNotifEtud"
import {AuthUser} from "@/store/AuthStore.js";
import {CurentView} from "@/store/storeView.js";
export default {
  created(){
           /* this.getNotifs();
            this.getNotifsNotSeen();*/
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
          dialog:false
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
        /*getNotifs(){
            gererNotifEtud.getNotifEtud().then((res)=>{
                for(let i=0;i<res.data.data.length;i++){
                    this.notifications.push({idNotif:res.data.data[i].id,msg:res.data.data[i].message,etat:res.data.data[i].etat,date:(res.data.data[i].created_at)?.substring(0,10),user_envoi_photo:res.data.data[i].user_envoi['Photo']})
                    
                }  
            }
            )
        },
        deleteNotificationById(id){
            gererNotifEtud.deleteNotificationById(id);
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
         },*/
  },
  computed:{
           /* getNbrNotif(){
                const nbrNotif=this.notifications.length
                return nbrNotif;
            },
            getNbrNotifNotSeen(){
                    const nbrNotifNotSeen=this.notificationNotSeen.length
                    return nbrNotifNotSeen;
            }*/
         }   
};

</script>

<style scoped>

</style>