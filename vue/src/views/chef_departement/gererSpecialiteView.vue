<template>
    <div>
        <navbar />
        <navigationDrawer :current-page="cureentPage" />
        <div class="main_content">
            <v-row>
                <v-col align="center" justify="center">
                    <v-btn text :loading="loader" disabled color="blue-grey" class="ma-2 white--text">
                    </v-btn>
                </v-col>
            </v-row>
            <v-container v-if="Specialites.length == 0">
                <v-row align="center" justify="center">
                    <h1 style="text-align=center;color: #3f51b5;">Oops ! there's no data available</h1>
                </v-row>
            </v-container>
            <div v-if="loader == false && Specialites.length != 0">
                <v-simple-table height="300px">

                    <template v-slot:default>
                        <v-text-field v-model="search" label="Search Specialite" class="mx-4"></v-text-field>
                        <thead>
                            <tr>
                                <th class="text-left">
                                    #
                                </th>
                                <th class="text-left">
                                    nom specialite
                                </th>
                                <th class="text-left">
                                    niveau specialite
                                </th>
                                <th>
                                    nombre de classe associé
                                </th>
                                <th>
                                    operation
                                </th>
                                <th>
                                    <v-btn color="#3f51b5" text-color="white"><v-icon>mdi-plus</v-icon></v-btn>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="Specialite in Specialites" :key="Specialite.id">
                                <td>{{ Specialite.id }}</td>
                                <td>{{ Specialite.type }}</td>
                                <td>{{ Specialite.niveau }}</td>
                                <td></td>
                                <td>
                                    <v-row>
                                        <v-col>
                                            <v-btn color="#3f51b5"
                                                :loading="loaderAcceptBtn"><v-icon>mdi-pen</v-icon></v-btn>
                                        </v-col>
                                        <v-col>
                                            <v-btn color="#ffcf59"
                                                :loading="loaderRefuseBtn"><v-icon>mdi-delete</v-icon></v-btn>
                                        </v-col>
                                        <v-col>
                                            <v-btn color="#a6cd49"
                                                :loading="loaderRefuseBtn"><v-icon>mdi-information-outline</v-icon></v-btn>
                                        </v-col>
                                    </v-row>
                                </td>
                            </tr>
                        </tbody>
                        </template>
                </v-simple-table>
            </div>
        </div>
        <template class="text-center" v-if="Specialites.length!=0">
                            <v-row align="center" justify="center" style="margin-top: 5%;margin-left: 6%;">
                            <v-card elavation="4">
                                <div class="text-center">
                                    <v-pagination v-model="page" :length="Specialites.length"></v-pagination>
                                </div>
                            </v-card>
                        </v-row>
                        </template>
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
import gestionspecialite from '@/service/specialite/gestionspecilate'
export default {
    components: {
        navbar,
        navigationDrawer
    },
    created() {
        this.getAllSpecialite();
    },
    data() {
        return {
            cureentPage: "specialite",
            Specialites: [],
            loader: false,
            snackbar: false,
            text: '',
            timeout: 2000,
            color: '',
            loaderAcceptBtn: false,
            loaderRefuseBtn: false,
            page:1,
        }
    },
    methods: {

        getAllSpecialite() {
            this.loader = true;
            gestionspecialite.getSpecialte().then(response => {
                console.log(response.data);
                this.Specialites = response.data.data;
                this.loader = false;
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

.tr {
    margin-bottom: 12% !important;
}

@media screen and (max-width:768px) {
    .main_content {
        margin-left: 5%;
        margin-top: 10%;
    }
}
</style>