<template>
    <div>
        <navbar />
        <navigationDrawer current-page="enseignatns" />
        <div class="main_content">
            <v-card elavation="2">
                <v-row>
                    <v-btn color="success" @click="currentProf = 'accepted'">accepted proffesors</v-btn>
                    <v-btn color="orange" @click="currentProf = 'pending'">pending requests</v-btn>
                </v-row>
            </v-card>
            <div v-show="currentProf == 'pending'">
                <v-container fluid>
                    <v-row align="center" justify="center" v-for="pendingRequest in pendingRequests"
                        :key="pendingRequest.id">
                        <proffesorCard :pendingRequest="pendingRequest" @accept-user="acceptUser" @refuse-user="refuseUser"></proffesorCard>
                    </v-row>
                </v-container>
            </div>
        </div>
        <v-snackbar v-model="snackbar" :timeout="timeout" :color="color">
            {{ text }}
            <template v-slot:action="{ attrs }">
                <v-btn color="red" text v-bind="attrs" @click="snackbar = false">
                    Close
                </v-btn>
            </template>
        </v-snackbar>
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import proffesorCard from '@/components/chef_departement/enseignants/proffesorCard.vue'
import gestionProffesors from '@/service/proffesors/gestionProffesors.js'
import Userinfo from '@/service/UserInfo/userinfo.js'
export default {
    components: {
        navbar,
        navigationDrawer,
        proffesorCard
    },
    created() {
        this.getPendingRequests();
    },
    data() {
        return {
            currentProf: 'pending',
            pendingRequests: [],
            snackbar: false,
            text: '',
            timeout: 2000,
            color:''
        }

    },
    methods: {
        getPendingRequests() {
            gestionProffesors.getPendingRequests().then((response) => {
                if (response.data != []) {
                    this.pendingRequests=response.data;
                }
            })
        },
        acceptUser(id){
            console.log("aaa");
            console.log(id);
            let status={'status':1};
            Userinfo.ActiveUser(id,status).then((response)=>{
                this.text="user activated succesfully";
                this.snackbar=true;
                this.color='green';
                this.getPendingRequests();
            })
        },
        refuseUser(id){
            let status={'status':2};
            Userinfo.ActiveUser(id,status).then((response)=>{
                this.text="user rejected succesfully";
                this.snackbar=true;
                this.color='green';
                this.getPendingRequests();
            })
        }
    },
}
</script>
<style scoped>
.main_content {
    margin-left: 20%;
    margin-top: 5%;
}

.v-card__title {
    font-size: 1.5em;
    font-weight: bold;
}
</style>