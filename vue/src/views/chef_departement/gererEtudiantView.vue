<template>
    <div>
        <navbar />
        <navigationDrawer current-page="etudiant" />
        <div class="main_content">
            <div>
                <v-container fluid>
                    <v-row align="center" justify="center" v-for="PendingRequest in PendingRequests"
                        :key="PendingRequest.id">
                        <StudentRequest :pending-request="PendingRequest" @accept-user="acceptUser"
                            @refuse-user="refuseUser" />
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
        }
    },
    methods: {
        getPendingRequest() {
            gestionEtudiant.getPendingRequests().then(response => {
                console.log(response.data.data);
                this.PendingRequests = response.data.data;
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
<style>
.main_content {
    margin-left: 20%;
    margin-top: 5%;
}
</style>