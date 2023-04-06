<template>
    <div>
        <navbar />
        {{ pendingRequests.length }}
        {{ AcceptedProffesors.length }}
        <navigationDrawer current-page="enseignatns" />
        <div class="main_content">

            <v-card elavation="2">
                <v-row>
                    <v-btn color="success" @click="currentProf = 'accepted'">accepted proffesors</v-btn>
                    <v-btn color="orange" @click="currentProf = 'pending'">pending requests</v-btn>
                </v-row>
            </v-card>
            <v-row>
                <v-col>
            <v-btn text align="center" justify="center" :loading="loader" :disabled="loader" color="blue-grey"
                class="ma-2 white--text">
            </v-btn>
        </v-col>
        </v-row>
            <div v-show="loader == false">
                <div v-if="currentProf == 'pending' && pendingRequests.length != 0">
                    <v-container fluid>
                        <v-row align="center" justify="center" v-for="pendingRequest in pendingRequests"
                            :key="pendingRequest.id">
                            <proffesorCard :loaderaccept="loaderaccept" :pendingRequest="pendingRequest" @accept-user="acceptUser"
                                @refuse-user="refuseUser"></proffesorCard>
                        </v-row>
                    </v-container>
                </div>
                <div v-if="currentProf == 'pending' && pendingRequests.length == 0">
                    <v-container>
                        <v-row>
                            <h1>there's no pending requests right now !</h1>
                        </v-row>
                        <v-row>
                            <v-img src="../../../public/chef_departement/noRequests.svg"></v-img>
                        </v-row>
                    </v-container>
                </div>
                <div v-show="currentProf == 'accepted' && AcceptedProffesors.length != 0">
                    <v-container fluid>
                        <v-row align="center" justify="center" v-for="AcceptedProffesor in AcceptedProffesors"
                            :key="AcceptedProffesor.id">
                            <AcceptedProffesor :AcceptedProffesor="AcceptedProffesor" @refuse-user="refuseUser">
                            </AcceptedProffesor>
                        </v-row>
                    </v-container>
                </div>
                <div v-show="currentProf == 'accepted' && AcceptedProffesors.length == 0">
                    <v-container>
                        <v-row>
                            <h1>there's now accepted proffesors</h1>
                        </v-row>
                        <v-row>
                            <v-img src="../../../public/chef_departement/teacher.svg"></v-img>
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
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import proffesorCard from '@/components/chef_departement/enseignants/proffesorCard.vue'
import AcceptedProffesor from '@/components/chef_departement/enseignants/AcceptedProffesor.vue';
import gestionProffesors from '@/service/proffesors/gestionProffesors.js'
import Userinfo from '@/service/UserInfo/userinfo.js'
export default {
    components: {
        navbar,
        navigationDrawer,
        proffesorCard,
        AcceptedProffesor,
    },
    created() {
        this.getPendingRequests();
        this.getAcceptedProffesors();
    },
    data() {
        return {
            currentProf: 'pending',
            pendingRequests: [],
            AcceptedProffesors: [],
            snackbar: false,
            text: '',
            timeout: 2000,
            color: '',
            loader: false,
            loaderaccept:true,
        }
    },
    methods: {
        getPendingRequests() {
            this.loader = true;
            gestionProffesors.getPendingRequests().then((response) => {
                this.pendingRequests = response.data.data;
                console.log(this.pendingRequests);
                this.loader = false;
            })
        },
        getAcceptedProffesors() {
            gestionProffesors.getAcceptedProffesors().then((response) => {
                this.AcceptedProffesors = response.data.data;
                console.log(this.AcceptedProffesors);
            })
        },
        acceptUser(id) {
            console.log("aaa");
            console.log(id);
            let status = { 'status': 1 };
            this.loaderaccept=true;
            Userinfo.ActiveUser(id, status).then((response) => {
                this.text = "user activated succesfully";
                this.snackbar = true;
                this.color = 'green';
                this.loaderaccept=false;
                this.getPendingRequests();
                
            })
        },
        refuseUser(id) {
            let status = { 'status': 2 };
            Userinfo.ActiveUser(id, status).then((response) => {
                this.text = "user rejected succesfully";
                this.snackbar = true;
                this.color = 'green';
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