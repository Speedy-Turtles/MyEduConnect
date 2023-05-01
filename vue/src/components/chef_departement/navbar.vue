<template>
    <div>
        <v-toolbar elevation="4" rounded fixed>
            <img src="../../assets/logo-no-background.svg" width="100px" alt="">
            <v-spacer></v-spacer>
            <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn icon v-bind="attrs" v-on="on" style="margin-right: 2%;">
                        <v-badge color="red" content="6">
                            <v-icon>mdi-bell</v-icon>
                        </v-badge>
                    </v-btn>
                </template>
                <v-list>
                    <v-list-item-title>
                        <v-list-item class="notification">notifications 1</v-list-item>
                        <v-list-item class="notification">notifications 2</v-list-item>
                        <v-list-item class="notification">notifications 3</v-list-item>
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
export default {
    name: 'navbar',
    setup(){
            const store=AuthUser();
            return{store}
        },
    data() {
        return {

        }
    },
    methods:{
        logout(){
            this.store.logout();
            this.$router.push({name:'signin'});
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
</style>