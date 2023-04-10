<template>
    <div>
        <v-container fluid class="allContent"></v-container>
        <template>
            <v-card elavation="4" style="margin-top: 2%;margin-left: 4%;" class="content">
                <v-card-title class="text-center" color="">
                    <h1 class="text-center">gestion de classe de specialite {{ RecenetSpecialite.type }}</h1>
                </v-card-title>
                <v-navigation-drawer v-model="drawer" :mini-variant.sync="mini" permanent app>
                    <v-list-item class="px-2">
                        <v-list-item-avatar>
                            <v-avatar color="indigo" size="36">
                                <span class="white--text text-h5">{{ RecenetSpecialite.type }}</span>
                            </v-avatar>
                        </v-list-item-avatar>
                        <v-list-item-title>{{ RecenetSpecialite.type }}</v-list-item-title>
                        <v-btn icon @click.stop="mini = !mini">
                            <v-icon>mdi-chevron-left</v-icon>
                        </v-btn>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list dense>
                        <v-list-item link @click="currentProf='list'">
                            <v-list-item-icon>
                                <v-icon>mdi-format-list-bulleted</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                                <v-list-item-title>classes list</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                        <v-list-item link @click="currentProf='add'">
                            <v-list-item-icon>
                                <v-icon>mdi-plus</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                                <v-list-item-title>add classes</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        <router-link class="text-decoration-none" to="/chef_departement/gererspecialite">
                            <v-list-item link>
                                <v-list-item-icon>
                                    <v-icon>mdi-arrow-left</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>back to specialite List</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </router-link>
                    </v-list>
                </v-navigation-drawer>
                <v-content>
                    <v-text-field label="Search classes" class="mx-4"></v-text-field>
                    <v-simple-table height="300px">
                        <template v-slot:default>
                            <thead>
                                <tr>
                                    <th class="text-left">
                                        #
                                    </th>
                                    <th class="text-left">
                                        nom classe
                                    </th>
                                    <th>opeartion</th>
                                </tr>
                                <tr v-for="classe in classes" :key="classe.id" v-if="loader == false">
                                    <td>{{ classe.id }}</td>
                                    <td v-if="update=='update'+classe.id">
                                        <v-form method="post" @submit.prevent="UpdateClasse(classe.id)" ref="form2" v-model="valid2">
                                        <v-text-field label="classe name" class="mx-4" v-model="updateName" :rules="nameRules"></v-text-field>
                                        </v-form>
                                    </td>
                                    <td v-else>{{ classe.nom }}</td>
                                    <td>
                                        <v-row>
                                            <v-col>
                                                <v-btn color="#3f51b5" @click="updateName=classe.nom;update='update'+classe.id"><v-icon>mdi-pen</v-icon></v-btn>
                                            </v-col>
                                            <v-col>
                                                <v-btn color="#ffcf59" @click.stop="dialog = true;choosenDelete=classe.id"><v-icon>mdi-delete</v-icon></v-btn>
                                            </v-col>
                                        </v-row>
                                    </td>
                                    <v-divider></v-divider>
                                </tr>
                                <tr v-if="currentProf=='add' || classes.length==0">
                                    <td></td>
                                    <td>
                                        <v-form method="post" @submit.prevent="addClasse()" ref="form" v-model="valid">
                                        <v-text-field label="classe name" class="mx-4" v-model="nom" :rules="nameRules"></v-text-field>
                                        </v-form>
                                    </td>
                                </tr>
                            </thead>
                        </template>
                    </v-simple-table>
                </v-content>
                <v-content v-if="currentProf=='add'">
                        
                </v-content>
            </v-card>
            <template>
                <v-row justify="center">
                    <v-dialog v-model="dialog" max-width="400">
                        <v-card>
                            <v-card-title class="text-h5">
                                Are you sure you want to delete this classe
                            </v-card-title>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="green darken-1" text @click="dialog = false">
                                    Disagree
                                </v-btn>
                                <v-btn color="green darken-1" text @click="DeleteClasse()" :loading="loaderDelete">
                                    Agree
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-row>
            </template>
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
import gestionspecilate from '@/service/specialite/gestionspecilate';
import gestionclasse from '@/service/classe/gestionclasse';
export default {
    data() {
        return {
            valid2:true,
            valid:true,
            idSpecialite: null,
            drawer: true,
            items: [
                { title: 'classes list', icon: 'mdi-format-list-bulleted' },
                { title: 'add classes', icon: 'mdi-plus' },
                { title: 'back to specialite List', icon: 'mdi-arrow-left' },
            ],
            mini: true,
            RecenetSpecialite: null,
            classes: [],
            snackbar: false,
            text: '',
            color: '',
            timeout: 2000,
            loader: false,
            dialog: false,
            choosenDelete:null,
            loaderDelete:false,
            currentProf:'',
            nom:'',
            updateName:'',
            update:'',
            nameRules: [
                v => !!v || 'Name is required',
                v => (v && v.length <= 10) || 'Name must be less than 10 characters',
            ],
        }
    },
    created() {
        this.idSpecialite = this.$route.params.id;
        this.getSpecialiteByid();
        this.getClasses();
    },
    methods: {
        getSpecialiteByid() {
            gestionspecilate.getSpecialiteByid(this.idSpecialite).then(response => {
                this.RecenetSpecialite = response.data.data[0];
            })
        },
        getClasses() {
            gestionclasse.ClassesBySpecialte(this.idSpecialite).then(response => {
                this.classes = response.data.data;
                console.log(this.classes);
            }).catch((error) => {
                this.snackbar = true;
                this.text = "error must have been come";
                this.color = "red";
            })
        },
        DeleteClasse(){
            this.loaderDelete=true;
            gestionclasse.DeleteClasse(this.choosenDelete).then(response=>{
                this.loaderDelete=false;
                this.snackbar=true;
                this.text="classe deleted succesfully";
                this.color="green";
                this.getClasses();
                this.dialog=false;
            }).catch((error)=>{
                this.snackbar=true;
                this.text="an error have been come proccesing your request";
                this.color="red";
            })
        },
        addClasse(){
            this.$refs.form.validate();
            if(this.valid==true){
            let classe={
                nom:this.nom,
                specialite_id:this.idSpecialite
            }
            gestionclasse.AddClasse(classe).then(response=>{
                this.snackbar=true;
                this.text="classe added succesfully";
                this.color="green";
                this.nom='';
                this.currentProf='list';
                this.getClasses();
            }).catch((error)=>{
                this.snackbar=true;
                this.text="there was an error submitting your request";
                this.color="red";
            })
        }
        },
        UpdateClasse(id){
            if(this.valid2==true){
            let classe={
                nom:this.updateName,
            }
            gestionclasse.updateClasse(id,classe).then(response=>{
                this.snackbar=true;
                this.text="classe updated succesfully";
                this.color="green";
                this.updateName='';
                this.update='';
                this.getClasses();
            }).catch((error)=>{
                this.snackbar=true;
                this.text="there was an error submitting your request";
                this.color="red";
            })
        }
        }
    }
}
</script>
<style scoped>
.allContent {
    background-image: url('../../../public/chef_departement/Screenshot\ 2023-04-10\ 022821.png');
    background-size: cover;
    background-repeat: no-repeat;
    filter: blur(5px);
    height: 100vh;
    width: 100vw;
    position: fixed;
}

.content {
    position: relative;
    z-index: 1;
    height: 100vh;
    width: 100vw;
}
</style>