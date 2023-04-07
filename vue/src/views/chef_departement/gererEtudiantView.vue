<template>
    <div>
        <navbar />
        <navigationDrawer current-page="etudiant" />
        <div class="main_content">
            <v-row>
                <v-col align="center" justify="center">
            <v-btn text  :loading="loader" :disabled="loader" color="blue-grey"
                class="ma-2 white--text">
            </v-btn>
        </v-col>
        </v-row>
            <div>
                <v-container fluid v-if="PendingRequests.length!=0">
                    <v-row align="center" justify="center" v-for="PendingRequest in PendingRequests" :key="PendingRequest.id">
                        <StudentRequest :pending-request="PendingRequest" @accept-user="acceptUser"
                            @refuse-user="refuseUser" />
                    </v-row>
                </v-container>
                <v-container fluid v-else>
                    <v-row align="center" justify="center">
                        <h1 align="center">there's no pending request from students right now</h1>
                        <v-img src="../../../public/chef_departement/Student stress-cuate.svg"></v-img>
                    </v-row>
                </v-container>
            </div>
            <div>
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
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import StudentRequest from '@/components/chef_departement/etudiant/StudentRequest.vue';
import gestionEtudiant from '@/service/proffesors/gestionEtudiant';
import Userinfo from '@/service/UserInfo/userinfo.js';
export default {
    components: {
        navbar,
        navigationDrawer,
        StudentRequest
    },
    created() {
        this.getPendingRequest();
    },
    data() {
        return {
            PendingRequests: [],
            snackbar:false,
            color:'',
            text:'',
            loader:false,
        }
    },
    methods: {
        getPendingRequest() {
            this.loader=true;
            gestionEtudiant.getPendingRequests().then(response => {
                console.log(response.data.data);
                this.PendingRequests = response.data.data;
                this.loader=false;
            })
        },
        acceptUser(id) {
            console.log("aaa");
            console.log(id);
            let status = { 'status': 1 };
            this.loaderaccept = true;
            Userinfo.ActiveUser(id, status).then((response) => {
                this.text = "user activated succesfully";
                this.snackbar = true;
                this.color = 'green';
                this.loaderaccept = false;
                this.getPendingRequest();

            })
        },
        refuseUser(id) {
            let status = { 'status': 2 };
            Userinfo.ActiveUser(id, status).then((response) => {
                this.text = "user rejected succesfully";
                this.snackbar = true;
                this.color = 'green';
                this.getPendingRequest();
            })
        }
    }
}
</script>
<style scoped>
.main_content {
    margin-left: 20%;
    margin-top: 5%;
}
</style>