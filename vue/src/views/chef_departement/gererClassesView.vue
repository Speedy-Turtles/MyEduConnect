<template>
    <div>
        <v-container fluid class="allContent"></v-container>
        <template>
        <v-card elavation="4" style="margin-top: 2%;margin-left: 3%;" class="content">
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
                    <v-list-item v-for="item in items" :key="item.title" link>
                        <v-list-item-icon>
                            <v-icon>{{ item.icon }}</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
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
                        </tr>
                        <tr v-for="classe in classes" :key="classe.id">
                        <td>{{ classe.id }}</td>
                        <td>{{ classe.nom }}</td>
                        </tr>
                    </thead>
                </template>
            </v-simple-table>
        </v-card>
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
}
</style>