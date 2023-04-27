<template>
    <div>
        <navbar />
        <navigationDrawer current-page="neauveuate" />
        <div class="main_content">
            <v-tabs>
                <v-tab>
                    <v-btn text @click="currentProf = 'list'">view neauvtly<v-icon>mdi-view-module</v-icon></v-btn>
                </v-tab>
                <v-tab>
                    <v-btn text @click="currentProf = 'add'">add a new neauvtly<v-icon>mdi-plus</v-icon></v-btn>
                </v-tab>
            </v-tabs>
            <v-row>

            </v-row>
            <v-container style="margin-top: 3%!important;">
                <v-row class="md-6">
                    <v-text-field label="Search neavautes" class="mx-4" @input="searchNeauvate()"
                        v-model="search"></v-text-field>
                </v-row>
                <v-col align="center" justify="center">
                    <v-btn v-if="loader == true" text :loading="loader" disabled color="blue-grey" class="ma-2 white--text">
                    </v-btn>
                </v-col>
                <v-row v-if="noData">
                    <v-col align="center" justify="center">
                        <h4> no data found</h4>
                    </v-col>
                </v-row>
                <v-row md="6" v-if="loader == false && currentProf == 'list'">
                    <v-col md="6" class="b-5" v-for="neauv in neavautes" style="margin-bottom: 3%;" :key="neauv.id">
                        <v-card>
                            <v-row md="6">
                                <v-col md="6">
                                    <v-img :src="neauv.photo" height="50%" width="100%" contain></v-img>
                                </v-col>
                                <v-col md="6">
                                    <v-row>
                                        <v-col>
                                            <v-card-title>{{ neauv.titre }}</v-card-title>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col>
                                            <v-card-text>
                                                {{ neauv.descripition }}
                                            </v-card-text>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col justify="end" align="right">
                                            <v-card-actions>
                                                <v-btn color="orange"
                                                    @click="editneauvtly(neauv.id)"><v-icon>mdi-pen</v-icon></v-btn>
                                                <v-btn color="red"
                                                    @click="dialog = true; SelectedDelete = neauv.id"><v-icon>mdi-delete</v-icon></v-btn>
                                            </v-card-actions>
                                        </v-col>
                                    </v-row>
                                </v-col>
                            </v-row>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row v-if="currentProf=='list'" align="center" justify="center">
                    <v-pagination v-model="page" :length="totalPages" :disabled="loading" :hide-disabled="true" @input="getNeavaute"></v-pagination>
                </v-row>
            </v-container>
            <v-container v-if="currentProf == 'add'">
                <v-card elavation="4">
                    <v-card-title class="text-center">
                        <h1 class="text-capitilazied">add a neauvautly</h1>
                    </v-card-title>

                    <v-sheet height="500" width="500" class="mx-auto" elavation="2">
                        <v-form method="post" @submit.prevent="addNeauvte()">
                            <v-text-field label="neauvautly title" :counter="30" v-model="title"></v-text-field>
                            <v-textarea v-model="description" clearable clear-icon="mdi-close-circle"
                                label="neauvautly description" :counter="255"></v-textarea>
                            <v-file-input accept="image/*" v-model="photo" label="neaveautly image"></v-file-input>
                            <v-btn type="submit" block class="mt-2" color="primary" :loading="loaderAdd">add
                                neauvautly</v-btn>
                        </v-form>
                    </v-sheet>

                </v-card>
            </v-container>
            <v-container v-if="currentProf == 'update'">
                <v-card elavation="4">
                    <v-card-title class="text-center">
                        <h1 class="text-capitilazied">add a neauvautly</h1>
                    </v-card-title>

                    <v-sheet height="500" width="500" class="mx-auto" elavation="2">
                        <v-form method="post" @submit.prevent="updateNeauvtly()">
                            <v-text-field label="neauvautly title" :counter="30" v-model="titleUpdate"></v-text-field>
                            <v-textarea v-model="descriptionUpdate" clearable clear-icon="mdi-close-circle"
                                label="neauvautly description" :counter="255"></v-textarea>
                            <v-text>recent photo : </v-text>
                            <v-img :src="updatedNeauvtly.photo" height="150" width="150"></v-img>
                            <v-file-input accept="image/*" v-model="photoUpdate"
                                label="update neaveautly image"></v-file-input>

                            <v-btn type="submit" block class="mt-2" color="primary" :loading="loaderUpdate">update
                                neauvautly</v-btn>
                        </v-form>
                    </v-sheet>

                </v-card>
            </v-container>
        </div>
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
                                are you sure you want to delete this neauvautly
                            </v-card-title>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red darken-1" text @click="DeleteNeavaute()" :loading="loaderRefuseBtn">
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
import gererNeavaute from '@/service/neavaute/gererNeavaute';

export default {
    components: {
        navbar,
        navigationDrawer
    },
    data() {
        return {
            neavautes: [],
            loader: false,
            loaderUpdate: false,
            currentProf: 'list',
            title: '',
            description: '',
            photo: [],
            titleUpdate: '',
            descriptionUpdate: '',
            photoUpdate: [],
            snackbar: false,
            text: '',
            color: '',
            timeout: 2000,
            loaderAdd: false,
            dialog: false,
            loaderRefuseBtn: false,
            SelectedDelete: null,
            title: '',
            description: '',
            photo: [],
            updatedNeauvtly: null,
            search: '',
            page: 1,
            totalPages: 0,
        }
    },
    created() {
        // this.tkalem("welcome in my edu connect");
        this.getNeavaute();
    },
    methods: {
        tkalem(text) {
            let test = new SpeechSynthesisUtterance(text);
            speechSynthesis.speak(test);
        },
        getNeavaute() {
            this.loader = true;
            gererNeavaute.getNeavaute(this.page).then(response => {
                this.neavautes = response.data.data.data;
                console.log(response.data);
                this.totalPages = response.data.data.last_page;
                this.loader = false;
            })
        },
        base64() {
            const file = document.querySelector("#file").files[0];
            const reader = new FileReader();
            reader.onloadend = () => {
                this.formdata.file_food = reader.result;

            };
            reader.readAsDataURL(file);
        },
        addNeauvte() {
            let neauvautly = {
                'titre': this.title,
                'descripition': this.description,
                'photo': "",
            }
            const fileReader = new FileReader();
            fileReader.onload = () => {
                this.loaderAdd = true;
                neauvautly.photo = fileReader.result;
                console.log(JSON.stringify(neauvautly));
                gererNeavaute.addNeavaute(neauvautly).then(response => {
                    this.snackbar = true;
                    this.text = "neauvautly added succesfully";
                    this.color = "green";
                    this.title = '';
                    this.description = '';
                    this.photo = [];
                    this.getNeavaute();
                    this.loaderAdd = false;
                    this.currentProf = "list";
                }).catch((error) => {
                    this.snackbar = true;
                    this.text = 'there was an error submitting your request';
                    this.color = 'red';
                })
            };
            fileReader.readAsDataURL(this.photo);
        },
        DeleteNeavaute() {
            this.loaderRefuseBtn = true;
            gererNeavaute.DeleteNeauv(this.SelectedDelete).then(response => {
                this.loaderRefuseBtn = false;
                this.snackbar = true;
                this.text = "neauvautly deleted succesfully";
                this.color = "green";
                this.dialog = false;
                this.getNeavaute();
            }).catch((error) => {
                this.snackbar = true;
                this.text = 'there was an error submitting your request';
                this.color = 'red';
            })
        },
        editneauvtly(id) {
            gererNeavaute.getNeavauteById(id).then(response => {
                this.updatedNeauvtly = response.data.data;
                this.titleUpdate = this.updatedNeauvtly.titre;
                this.descriptionUpdate = this.updatedNeauvtly.descripition;
                this.currentProf = 'update';
            }).catch((error) => {
                this.snackbar = true;
                this.text = 'there was an error submitting your request';
                this.color = 'red';
            })

        },
        updateNeauvtly() {
            if (this.photoUpdate.length != 0) {
                let neauvautly = {
                    'titre': this.titleUpdate,
                    'descripition': this.descriptionUpdate,
                    'photo': "",
                }
                const fileReader = new FileReader();
                fileReader.onload = () => {
                    this.loaderUpdate = true;
                    neauvautly.photo = fileReader.result;
                    gererNeavaute.updateNeavaute(this.updatedNeauvtly.id, neauvautly).then(response => {
                        this.snackbar = true;
                        this.text = "neauvautly updated succesfully";
                        this.color = "green";
                        this.titleUpdate = '';
                        this.descriptionUpdate = '';
                        this.photoUpdate = [];
                        this.updatedNeauvtly = null;
                        this.getNeavaute();
                        this.loaderUpdate = false;
                        this.currentProf = "list";
                    }).catch((error) => {
                        this.snackbar = true;
                        this.text = 'there was an error submitting your request';
                        this.color = 'red';
                    })
                };
                fileReader.readAsDataURL(this.photoUpdate);
            } else {
                let neauvautly = {
                    'titre': this.titleUpdate,
                    'descripition': this.descriptionUpdate,
                    'photo': this.updatedNeauvtly.photo,
                }
                gererNeavaute.updateNeavaute(this.updatedNeauvtly.id, neauvautly).then(response => {
                    this.snackbar = true;
                    this.text = "neauvautly updated succesfully";
                    this.color = "green";
                    this.titleUpdate = '';
                    this.descriptionUpdate = '';
                    this.photoUpdate = [];
                    this.updatedNeauvtly = null;
                    this.getNeavaute();
                    this.loaderUpdate = false;
                    this.currentProf = "list";
                }).catch((error) => {
                    this.snackbar = true;
                    this.text = 'there was an error submitting your request';
                    this.color = 'red';
                })
            }
        },
        searchNeauvate() {
            this.loader = true;
            gererNeavaute.searchNeavaute(this.search,this.page).then(response => {
                this.loader = false;
                this.neavautes = response.data.data.data;
                this.totalPages = response.data.data.last_page;
            })
        }
    },
    computed: {
        noData() {
            return this.neavautes.length == 0 ? true : false;
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