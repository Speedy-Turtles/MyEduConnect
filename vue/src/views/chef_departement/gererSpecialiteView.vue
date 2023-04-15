<template>
    <div>
        <navbar />
        <navigationDrawer :current-page="cureentPage" />
        <div class="main_content">
            <v-row v-if="currentProf=='list'"> <v-col>
                    <v-btn @click="currentProf = 'add'" color="#3f51b5" class="float-right"
                        text-color="white"><v-icon>mdi-plus</v-icon>add specialite</v-btn>
                </v-col></v-row>
            <v-row>
                <v-col align="center" justify="center">
                    <v-btn v-if="currentProf == 'list'" text :loading="loader" disabled color="blue-grey"
                        class="ma-2 white--text">
                    </v-btn>
                </v-col>
            </v-row>
            <v-container v-if="Specialites.length == 0 && currentProf == 'list'">
                <v-row align="center" justify="center">
                    <h1 style="text-align=center;color: #3f51b5;">Oops ! there's no data available</h1>
                </v-row>
            </v-container>
            <div v-if="currentProf == 'list'">
                <v-text-field v-model="search" label="Search Specialite" class="mx-4" @input="searchSpecialite()"></v-text-field>
                <v-simple-table height="300px">
                    <template v-slot:default>
                        
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
                                    <v-btn @click="currentProf = 'add'" color="#3f51b5"
                                    text-color="white"><v-icon>mdi-plus</v-icon></v-btn><!--testi itha table feregh ana button tafycheh -->
                                </th>
                            </tr>
                        </thead>
                        
                        <tbody v-if="loader==false">
                            <tr v-if="noData">
                    <td align="center" justify="center">
                        <h4> no data found</h4>
                    </td>
                        </tr>
                            <tr v-for="Specialite in Specialites" :key="Specialite.id">
                                <td>{{ Specialite.id }}</td>
                                <td>{{ Specialite.type }}</td>
                                <td>{{ Specialite.niveau }}</td>
                                <td>{{ Specialite.classes.length }}</td>
                                <td>
                                    <v-row>
                                        <v-col>
                                            <v-btn color="#3f51b5" :loading="loaderAcceptBtn"
                                                @click="getSpecialite(Specialite.id)"><v-icon>mdi-pen</v-icon></v-btn>
                                        </v-col>
                                        <v-col>
                                            <v-btn color="#ffcf59"
                                                @click="dialog = true; deleteId = Specialite.id"><v-icon>mdi-delete</v-icon></v-btn>
                                        </v-col>
                                        <v-col>
                                            <router-link :to="{name:'gererClasses', params:{id:Specialite.id} }" class="text-decoration-none"><v-btn color="#a6cd49"><v-icon>mdi-information-outline</v-icon></v-btn></router-link>
                                        </v-col>
                                    </v-row>
                                </td>
                            </tr>
                        </tbody>
                    </template>
                </v-simple-table>
            </div>
            <div class="add-specialite" v-if="currentProf == 'add'">
                <template>
                    <v-card elavation="4" height="450">
                        <v-btn color="#525fe1" @click="currentProf = 'list'"><v-icon>mdi-arrow-left</v-icon> back to
                            dashboard</v-btn>
                        <v-card-title class="text-center">add a speciality</v-card-title>
                        <v-card-body>
                            <v-sheet width="500" class="mx-auto">
                                <v-form validate-on="submit" method="post" ref="form" v-model="valid"
                                    @submit.prevent="addSpeciality()">
                                    <v-label>speciaity name</v-label>
                                    <v-text-field v-model="name" :counter="10" :rules="nameRules" required></v-text-field>
                                    <v-label>niveau</v-label>
                                    <v-select :items="niveaux" v-model="niveau" :rules="niveauRules" required></v-select>
                                    <v-btn type="submit" class="float-right" color="#525fe1" :disabled="!valid"
                                        :loading="AddLoader">add a speciality</v-btn>
                                </v-form>
                            </v-sheet>
                        </v-card-body>
                    </v-card>
                </template>
            </div>
            <div class="add-specialite" v-if="currentProf == 'update'">
                <template>
                    <v-card elavation="4" height="450">
                        <v-card-title class="text-center">update speciality</v-card-title>
                        <v-card-body>
                            <v-sheet width="500" class="mx-auto">
                                <v-form validate-on="submit" method="post" ref="form2" v-model="valid2"
                                    @submit.prevent="updateSpeciality()">
                                    <v-label>speciaity name</v-label>
                                    <v-text-field v-model="editSpecailite.type" :counter="10" :rules="nameRules"
                                        required></v-text-field>
                                    <v-label>niveau</v-label>
                                    <v-select :items="niveaux" v-model="editSpecailite.niveau" :rules="niveauRules"
                                        required></v-select>
                                        <v-row>
                                            <v-col>
                                    <v-btn type="submit" class="float-right" color="#525fe1" :disabled="!valid2"
                                        :loading="loaderUpdate">update speciality</v-btn>
                                    </v-col>
                                    <v-col>
                                        <v-btn class="float-right" color="secondary" @click="currentProf='list'"
                                        >cancel</v-btn>
                                    </v-col>
                                    </v-row>
                                </v-form>
                            </v-sheet>
                        </v-card-body>
                    </v-card>
                </template>
            </div>
        </div>
        <template class="text-center" v-if="Specialites.length != 0 && currentProf == 'list'">
            <v-row align="center" justify="center" style="margin-top: 5%;margin-left: 6%;">
                <v-card elavation="4">
                    <div class="text-center">
                        <v-pagination v-model="page" :length="totalPages" :disabled="loading" :hide-disabled="true" @input="getAllSpecialite"></v-pagination>
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
        <div class="confirm">
            <template>
                <v-row justify="center">
                    <v-dialog v-model="dialog" max-width="500">
                        <v-card>
                            <v-card-title class="text-h5">
                                are you sure you want to delete this speciality
                            </v-card-title>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red darken-1" text @click="deleteSpeciality()" :loading="loaderRefuseBtn">
                                    Delete
                                </v-btn>
                                <v-btn color=" darken-1" text @click="dialog = false">
                                    cancel
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-row>
            </template>
        </div>
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
            valid2: false,
            deleteId: null,
            valid: true,
            dialog: false,
            cureentPage: "specialite",
            Specialites: [],
            loader: false,
            snackbar: false,
            editSpecailite: null,
            text: '',
            timeout: 2000,
            color: '',
            loaderAcceptBtn: false,
            loaderRefuseBtn: false,
            loaderUpdate: false,
            page: 1,
            search: '',
            currentProf: "list",
            niveaux: ['tronc commun', 'deuxieme anne', 'troisieme anne'],
            nameRules: [
                v => !!v || 'Name is required',
                v => (v && v.length <= 10) || 'Name must be less than 10 characters',
            ],
            niveauRules: [
                v => !!v || 'Niveau is required',
            ],
            name: '',
            niveau: null,
            AddLoader: false,
            search:'',
            page:1,
            totalPages:0,
        }
    },
    methods: {
        getAllSpecialite() {
            this.loader = true;
            gestionspecialite.getSpecialte(this.page).then(response => {
                console.log(response.data);
                this.Specialites = response.data.data.data;
                this.totalPages = response.data.data.last_page;
                this.loader = false;
            })
        },
        addSpeciality() {
            this.AddLoader = true;
            this.$refs.form.validate();
            if (this.valid == true) {
                if (this.niveau == "tronc commun") {
                    this.niveau = 1;
                } else if (this.niveau == 'deuxieme anne') {
                    this.niveau = 2;
                } else {
                    this.niveau = 3;
                }
                let specialite = {
                    "type": this.name,
                    "niveau": this.niveau
                }
                gestionspecialite.addSpecialite(specialite).then(response => {
                    this.AddLoader = false;
                    this.snackbar = true;
                    this.text = "speciality added succesfully";
                    this.color = "green";
                    this.name = '';
                    this.niveau = null;
                    this.currentProf = "list";
                    this.getAllSpecialite();
                }).catch((error) => {
                    this.AddLoader = false;
                    this.snackbar = true;
                    this.text = "there's was an error submitting your request";
                    this.color = "red";
                })
            }
        },
        deleteSpeciality() {
            this.loaderRefuseBtn = true;
            gestionspecialite.deleteSpecialite(this.deleteId).then(response => {
                console.log(response.data);
                this.loaderRefuseBtn = false;
                this.dialog = false;
                this.snackbar = true;
                this.text = "specialite deleted succesfully";
                this.color = "green";
                this.deleteId = null;
                this.getAllSpecialite();
            }).catch((error) => {
                this.loaderRefuseBtn = false;
                this.dialog = false;
                this.snackbar = true;
                this.deleteId = null;
                this.text = "there's was an error submitting your request";
                this.color = "red";
            })
        },
        getSpecialite(id) {
            this.loaderAcceptBtn = true;
            gestionspecialite.getSpecialiteByid(id).then(response => {
                this.editSpecailite = response.data.data;
                console.log(this.editSpecailite);
                if (this.editSpecailite.niveau == 1) {
                    this.editSpecailite.niveau = "tronc commun";
                } else if (this.editSpecailite.niveau == 2) {
                    this.editSpecailite.niveau = 'deuxieme anne';
                } else {
                    this.editSpecailite.niveau = 'troisieme anne';
                }
                this.loaderAcceptBtn = false;
                this.currentProf = "update";
            }).catch((error) => {
                this.snackbar = true;
                this.text = "there's was an error submitting your request";
                this.color = "red";
            })
        },
        updateSpeciality() {
            this.loaderUpdate = true;
            this.$refs.form2.validate();
            if (this.valid2 == true) {
                if (this.editSpecailite.niveau == "tronc commun") {
                    this.editSpecailite.niveau = 1;
                } else if (this.editSpecailite.niveau == 'deuxieme anne') {
                    this.editSpecailite.niveau = 2;
                } else {
                    this.editSpecailite.niveau = 3;
                }
                gestionspecialite.updateSpecialite(this.editSpecailite.id, this.editSpecailite).then(response => {
                    this.loaderUpdate = false;
                    this.currentProf = "list";
                    this.snackbar = true;
                    this.text = "speciality updated succesfully";
                    this.color = "green";
                    this.editSpecailite = null;
                    this.getAllSpecialite();
                }).catch((error) => {
                    this.snackbar = true;
                    this.loaderUpdate = false;
                    this.text = "there was a problem submiting your request";
                    this.color = "red";
                })
            }
        },
        searchSpecialite(){
            gestionspecialite.searchSpecialite(this.search,this.page).then(response=>{
                this.Specialites=response.data.data.data;
                this.totalPages = response.data.data.last_page;
                console.log(this.Specialites);
            }).catch((error)=>{
                this.snackbar = true;
                this.text = "there was a problem submiting your request";
                this.color = "red";
            })
        },
    },
    computed: {
        noData() {
            return this.Specialites.length == 0 ? true : false;
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

.confirm {
    z-index: 800000000 !important;
}

@media screen and (max-width:768px) {
    .main_content {
        margin-left: 5%;
        margin-top: 10%;
    }
}
</style>