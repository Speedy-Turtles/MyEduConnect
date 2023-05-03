<template>
    <div>
        <v-toolbar elevation="4" rounded fixed>
            <img src="../../assets/logo-no-background.svg" width="100px" alt="">
            <v-spacer></v-spacer>
            <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn icon v-bind="attrs" v-on="on" style="margin-right: 2%;" @click="changeEtatNotif()">
                        <v-badge color="red" :content="lengthNotseenNotif">
                            <v-icon>mdi-bell</v-icon>
                        </v-badge>
                    </v-btn>
                </template>
                <v-list>
                    <v-list-item-title>
                        <v-list-item v-if="notifications.length==0">
                            no notifications !
                        </v-list-item>
                        <v-list-item class="notification" v-if="notifications.length!=0" v-for="notif in notifications" :key="notif.id" :class="[notif.etat==0 ? 'notseen' : '' ]">
                            {{ notif.message }}
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
                    </v-list-item-title>
                </v-list>
            </v-menu>
            <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn plain v-bind="attrs" v-on="on">
                        <v-avatar size="43px" color="indigo">
                            <img v-if="store.user['Photo'].length!=2" :src="store.user['Photo']" alt="">
                            <span v-else> {{ store.user['Photo'] }}</span>
                        </v-avatar>
                    </v-btn>
                </template>
                <v-list>
                    <v-list-item>
                        <v-list-item-title>
                            <v-btn plain block class="py-3">
                                {{store.user['FirstName']}}
                            </v-btn>
                        </v-list-item-title>
                    </v-list-item>
                    <v-list-item>

                        <v-list-item-action>
                            <v-btn plain>
                                <v-icon class="pa-2">mdi-wrench</v-icon>
                                <span class="">Settings</span>
                            </v-btn>
                        </v-list-item-action>

                    </v-list-item>

                    <v-list-item>

                        <v-list-item-action>
                            <v-btn plain>
                                <v-icon class="pa-2">mdi-shield-outline</v-icon>
                                <span class="">personal info </span>
                            </v-btn>
                        </v-list-item-action>

                    </v-list-item>

                    <v-list-item>
                        <v-list-item-action>
                            <v-btn plain >
                                <v-icon class="pa-2">mdi-swap-horizontal</v-icon>
                                <v-btn text router to="/etudiant" >SWITCH</v-btn>
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

                  
                </v-list>
            </v-menu>
        </v-toolbar>
        
    </div>
    
</template>
<script>
import {AuthUser} from "@/store/Store.js"
import gererNotifEtud from "@/service/NotifEtudiant/gererNotifEtud";
export default {
    name: 'navbar',
    created(){
        this.getNotif();
        this.getNotifNotSenn();
        console.log(this.notifications);
    },
    setup(){
            const store=AuthUser();
            return{store}
        },
    data() {
        return {
            notifications:[],
            notSeenNotification:[],
        }
    },
    methods:{
        logout(){
            this.store.logout();
            this.$router.push({name:'signin'});
        },
        getNotif(){
            gererNotifEtud.getNotifEtud().then(response=>{
                console.log(response.data.data);
                this.notifications=response.data.data;
                console.log(this.notifications);
            })
        },
        getNotifNotSenn(){
            gererNotifEtud.getNotifNotSeen().then(response=>{
                this.notSeenNotification=response.data.data;
                console.log(this.notSeenNotification);
            })
        },
        changeEtatNotif(){
            gererNotifEtud.updateNotif().then(response=>{
                this.notSeenNotification=[];
                this.getNotif();
            });
        },
        deleteAllNotif(){
            gererNotifEtud.deleteAllNotif().then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
            })
        },
        },
        computed:{
            lengthNotseenNotif(){
                return this.notSeenNotification.length > 0 ? this.notSeenNotification.length : 0;
            }
        }
}
</script>
<style scoped>
.v-toolbar{
    position: fixed;
    width: 100%;
    z-index: 20000;
}

.notification {
    cursor: pointer;
}
.notseen{
    background: lightblue!important;
}
</style>