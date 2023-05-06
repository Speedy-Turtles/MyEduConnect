<template>
    <div class="navbar">
         <v-toolbar  class="toolbar px-5" >
            
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
                    v-for="link in (!test_idetudiant || store.Isetudiant=='false' ? links_ensignat : links)"
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
                <v-toolbar-title class="mr-5 hidden-xs-only hidden-md-only">
                    <img src="../../../../public/etudiant/images/logo-makeiteasy-no-background.svg" alt="" width="100px" srcset="">
                </v-toolbar-title>
                <!-- --------------------titre/-------------------------- -->
                <v-toolbar-items  class="hidden-sm-and-down">
                    <!-- --------------------tooltips-------------------------- -->
                    <v-tooltip bottom v-for="link in (!test_idetudiant || store.Isetudiant=='false'  ? links_ensignat : links)" :key="link.titre">
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
                        <v-badge color="red" :content="getNbrNotifNotSeen"
                        v-if="getNbrNotifNotSeen!=0"
                        :values="getNbrNotifNotSeen"
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

                    <!-- <v-list-item>
                        
                        <v-list-item-action>
                            <v-btn
                                plain
                            >
                                <v-icon class="pa-2">mdi-shield-outline</v-icon>
                                <span class="">personal info </span>
                            </v-btn>
                        </v-list-item-action>
                    </v-list-item> -->

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
                     
                <!-- --------------------items/-------------------------- -->
        </v-toolbar> 
    </div>
</template>

<script>
import {AuthUser} from "@/store/Store.js"
import gererNotifEtud from "@/service/NotifEtudiant/gererNotifEtud"
    export default {
        setup(){
            const store=AuthUser();
            return{store}
        },
        name:'Navbar',
        data(){
            return{
                links:[
                    {titre:'home',link:'home',desc:'Home',icon:'mdi-home',route:'/etudiant'},
                    {titre:'Document',link:'document',desc:'Check Documents',icon:'mdi-table-edit',route:'/etudiant/document'},
                    {titre:'Forum',link:'forum',desc:'Go to Forum',icon:'mdi-comment-text-outline',route:'/etudiant/forum'},
                    {titre:'Club',link:'club',desc:'Enjoy Clubs',icon:'mdi-star-outline',route:'/etudiant/club'},
                    {titre:'Help',link:'help',desc:'How Can We help You !',icon:'mdi-wrench',route:'/etudiant/help'}
                ],

                notifications:[],
                notificationNotSeen:[],

                links_ensignat:[
                    {titre:'home',link:'home',desc:'Home',icon:'mdi-home',route:'/etudiant'},
                    {titre:'Forum',link:'forum',desc:'Go to Forum',icon:'mdi-comment-text-outline',route:'/etudiant/forum'},
                    {titre:'vote',link:'vote',desc:'chose your chef',icon:' mdi-vote',route:'/etudiant/vote'},
                    {titre:'Help',link:'help',desc:'How Can We help You !',icon:'mdi-wrench',route:'/etudiant/help'}
                ],
                messages:10,
                test_ischef:false,
                test_idetudiant:false

            }
        },
       mounted(){
            window.Echo.channel('NotifRealtime').listen('notif',(e)=>{
                this.getNotifs();
                this.getNotifsNotSeen();
             })
        },
       methods:{
        logout(){
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
         created(){
            this.test_ischef=this.store.Ischef;
            this.test_idetudiant=this.store.Isetudiant;
            this.getNotifs();
            this.getNotifsNotSeen();
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
 }

</script>
<style scoped>
#demoFont {
font-family: "Montserrat", sans-serif;
font-size: 21px;
letter-spacing: 1.8px;
word-spacing: -1.6px;
font-weight: 700;
/* text-decoration: underline solid rgb(68, 68, 68); */
font-style: italic;
font-variant: small-caps;
text-transform: uppercase;
cursor: pointer;
}
.primary{
    color: #525fe1;
}
#btn-links{
font-family: "Times New Roman", Times, serif;
font-size: 21px;
letter-spacing: 1.8px;
word-spacing: -1.4px;
color: #525FE1;
font-weight: bolder;
font-style: italic;
font-variant: small-caps;
text-transform: uppercase;
}
.toolbar{
    position: fixed;
    z-index: 999;
    width: 100%;
   
}
</style>