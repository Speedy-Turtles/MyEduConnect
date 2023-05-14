<template>
    <div id="tab1">
        <div class="navbar">
         <v-toolbar   class="toolbar">
            <a type="hidden" href=""></a>
            <!-- --------------------menu-------------------------- -->
                <v-menu offset-y transition="slide-x-transition" 
                right
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn
                        
                        v-bind="attrs"
                        v-on="on"
                        plain
                        class="hidden-md-and-up grey--text mr-3"
                        >
                        <v-icon size="43px" color="primary">
                            mdi-menu
                        </v-icon>
                        </v-btn>
                    </template>
                    <v-list>
                        <v-list-item
                        v-for="link in (!test_idetudiant || store.Isetudiant=='false' ? links_ens : links)"
                        :key="link.titre"
                        >
                            <v-list-item-title>
                                <v-btn plain router :to="link.route">{{link.titre}} </v-btn>
                            </v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>
                <!-- --------------------menu/-------------------------- -->
                <!-- --------------------titre-------------------------- -->
                <v-toolbar-title class="mr-5 hidden-sm-and-down">
                    <img src="../../../assets/logo-no-background.svg" class="ml-10" alt="" width="110px" srcset="">
                </v-toolbar-title>
                <!-- --------------------titre/-------------------------- -->

                <v-toolbar-items  class="hidden-sm-and-down">
                    <!-- --------------------tooltips-------------------------- -->
                    <v-tooltip bottom v-for="link in  (!test_idetudiant || store.Isetudiant=='false' ? links_ens : links)" :key="link.titre">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                            plain
                            v-bind="attrs"
                            v-on="on"
                            id="btn-links"
                            class="black--text"
                            router :to="link.route"
                            >
                            <v-icon class="px-3">{{ link.icon }}</v-icon>
                            {{ link.titre }} 
                            </v-btn>
                        </template>
                        <span>{{ link.desc  }}</span>
                    </v-tooltip>
                    
                    <!-- --------------------tooltips/-------------------------- -->
                </v-toolbar-items>
                <v-spacer></v-spacer>
                <v-toolbar-title class="mr-5 hidden-md-and-up">
                    <img src="../../../assets/logo-no-background.svg" class="ml-10" alt="" width="110px" srcset="">
                </v-toolbar-title>

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
                     @click="chagerEtatNotif()"
                     >
                        <v-icon size="35px ">mdi-bell</v-icon>
                         <v-badge color="red" :content="getNbrNotifNotSeen ? getNbrNotifNotSeen : '0'"
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
                    
                        <v-list-item-avatar>
                            <v-avatar size="43px">
                                <v-img  :src="notif.photo"></v-img>
                            </v-avatar>
                        </v-list-item-avatar>
                      
                        <v-list-item-content>
                            <v-list-item-title class="px-5 justify-center">
                                {{ notif.msg }}<br><span class="date" v-if="notif.date!=null"> Since : {{ notif.date }}</span>
                            </v-list-item-title>
                        </v-list-item-content> 
                      
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
                    </v-list-item>
                        <v-divider></v-divider>
                        <v-list-item class="mt-5">
                            <v-btn 
                            plain
                            v-if="notifications.length!=0"
                            @click="deleteAllNotif()"
                            >
                                <v-list-item-title class="red--text ml-15"> 
                                    Clear All<v-icon class="pa-1 red--text">mdi-delete</v-icon>
                                </v-list-item-title>
                            </v-btn>
                        </v-list-item>  
                    </v-list>
                </v-menu>

                <v-menu offset-y 
                transition="slide-x-transition" 
                left
                >
                    <template v-slot:activator="{ on, attrs }">
                    <v-btn   plain v-bind="attrs"
                     v-on="on"
                     >
                         <v-avatar   size="43px">
                             <img :src="store.user['photo']">
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
                                    <v-btn router to="/dashboradAdminView">
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
        </v-toolbar> 
    </div>
    </div>
</template>

<script>
import gererNotifEtud from "@/service/Notification/gererNotif.js"
import {AuthUser} from "@/store/AuthStore.js"
export default {
    created(){
            this.test_ischef=this.store.Ischef;
            this.test_idetudiant=this.store.Isetudiant;
            this.getNotifs();
            this.getNotifsNotSeen();
    },
    setup(){
        const store=AuthUser()
        return{store}
    },
    data(){
        return{
            notifications:[],
          notificationNotSeen:[],
            test_ischef:false,
            dialog:false,
            test_idetudiant:false,
            links:[
                    {titre:'home',link:'home',desc:'Home',icon:'mdi-home',route:'/etudiant'},
                    {titre:'Document',link:'document',desc:'Check Documents',icon:'mdi-table-edit',route:'/etudiant/document'},
                    {titre:'Forum',link:'forum',desc:'Go to Forum',icon:'mdi-comment-text-outline',route:'/etudiant/forum'},
            ],
            links_ens:[
                    {titre:'home',link:'home',desc:'Home',icon:'mdi-home',route:'/etudiant'},
                    {titre:'Forum',link:'forum',desc:'Go to Forum',icon:'mdi-comment-text-outline',route:'/etudiant/forum'},
            ]
        }
    },  
    methods:{
        logout(){
            this.store.logout();
            this.$router.push({name:"login"});
        },
        getNotifs(){
            gererNotifEtud.getNotifs().then((res)=>{
                for(let i=0;i<res.data.length;i++){
                    this.notifications.push({idNotif:res.data[i].id,msg:res.data[i].message,etat:res.data[i].etat,date:(res.data[i].created_at)?.substring(0,10),photo:res.data[i].userEnvoi['photo']})
                }  
            }
            )
        },
        deleteNotificationById(id){
            gererNotifEtud.deleteNotificationById(id).then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
                this.getNotifs();
                this.getNotifsNotSeen();
            }).catch((err)=>{
                console.log(err);
            })
        },
        deleteAllNotif(){
            gererNotifEtud.deleteAllNotif().then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
            }).catch((err)=>{
                console.log(err);
            })
        },
        chagerEtatNotif(){
            gererNotifEtud.updateNotif()
            .then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
                this.getNotifsNotSeen();
                this.getNotifs();
            }).catch((err)=>{
                console.log(err);
            })
        },
        getNotifsNotSeen(){
            gererNotifEtud.getNotifNotSeen().then((res)=>{
                for(let i=0;i<(res.data).length;i++){
                    this.notificationNotSeen.push({idNotif:res.data[i].id,msg:res.data[i].message,etat:res.data[i].etat})
                }
            }).catch((err)=>{
                console.log(err);
            })
         },

    },
    components: {
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
    },
   
}
</script>
<style scoped>
.toolbar{
    position: fixed;
    z-index: 999;
    width: 100%;
}
</style>