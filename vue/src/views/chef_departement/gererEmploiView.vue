<template>
    <div>
        <navbar />
        <navigationDrawer :current-page="cureentPage" />
        <div class="main_content">
            <div class="classes">
                <v-row>
                    <v-col v-for="classe in   classes  ">
                        <v-card>
                            <v-row align="center" justify="center">
                                <v-avatar size="70" color="primary">
                                    {{ classe.nom }}
                                </v-avatar>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-card-title>
                                        <h3> {{ classe.nom }} </h3>
                                    </v-card-title>
                                </v-col>
                                <v-col align="center" justify="end">
                                    <v-btn @click="dialog = classe.id; selectedClasse = classe">
                                        <v-icon>mdi-calendar</v-icon>
                                        emploi
                                    </v-btn>
                                    <div style="z-index: 10000000000000000;">
                                        <template>
                                            <v-row justify="center">
                                                <v-dialog fullscreen hide-overlay v-model=" currentClasse "
                                                    transition="dialog-bottom-transition">
                                                    <v-card>
                                                        <v-toolbar dark color="primary">
                                                            <v-btn icon dark @click=" dialog = 0; selectedClasse = {} ">
                                                                <v-icon>mdi-close</v-icon>
                                                            </v-btn>
                                                            <v-toolbar-title>update emploi {{ selectedClasse.nom
                                                                }}</v-toolbar-title>
                                                            <v-spacer></v-spacer>
                                                            <v-toolbar-items>
                                                                <v-btn dark text @click=" dialog = 0; selectedClasse = {} ">
                                                                    Save
                                                                </v-btn>
                                                            </v-toolbar-items>
                                                        </v-toolbar>
                                                        <v-row class="allModule">
                                                        <v-col class="emploiTable" style="width:80%;height: 80%;" >
                                                            <v-row align='center' justify="center">
                                                                <h1 class="mt-5"> emploi de classe {{
                                                                    selectedClasse.nom }}</h1>
                                                            </v-row>
                                                            <v-simple-table>
                                                                <template v-slot:default>
                                                                    <thead>
                                                                        <tr>
                                                                            <th class="text-left pa-5"
                                                                                v-for="  seance   in   seances  ">
                                                                                {{seance}}
                                                                            </th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody class="pa-5">
                                                                        <tr v-for="  jour   in   emploi  ">
                                                                            <td class="pa-5">{{ jour.day }}</td>
                                                                            <td v-for="  classe   in  jour.seances"
                                                                                class="pa-3">
                                                                                <div :id="'dropzone'+classe.id">
                                                                                    {{classe.sance}}<br>A.Chaloueh
                                                                                    <span>I0.9</span>
                                                                                </div>
                                                                                <v-btn icon color="orange"
                                                                                    @click=" selectChange(classe, jour) ">
                                                                                    <v-icon>mdi-pen</v-icon>
                                                                                </v-btn>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </template>
                                                            </v-simple-table>
                                                        </v-col>
                                                        <v-col ref="changes" class="changes" style="display: none;">

                                                        </v-col>
                                                    </v-row>
                                                    </v-card>
                                                </v-dialog>
                                            </v-row>
                                        </template>
                                    </div>
                                </v-col>
                            </v-row>
                        </v-card>
                    </v-col>
                </v-row>
            </div>

        </div>
    </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import gestionclasse from '@/service/classe/gestionclasse';
import { Draggable } from 'draggable-vue-directive'
export default {
    name: '',
    components: {
        navbar,
        navigationDrawer
    },
    directives: {
            Draggable,
    },
    created() {
        this.getAllClasses();
    },
    data() {
        return {
            cureentPage: 'emploi',
            classes: [],
            dialog: 0,
            selectedClasse: {},
            seances: ["", "Séance 1", "Séance 2", "Séance 3", "Séance 4", "Séance 5", "Séance 6"],
            seances2: ["Séance 1", "Séance 2", "Séance 3", "Séance 4", "Séance 5", "Séance 6"],
            emploi: [
                {
                    day: 'Monday',
                    seances: [
                        { id: 1, sance: 'Class 1' },
                        { id: 2, sance: 'Class 2' },
                        { id: 3, sance: 'Class 3' },
                        { id: 4, sance: 'Class 4' },
                        { id: 5, sance: 'Class 5' },
                        { id: 6, sance: 'Class 6' }
                    ]
                },
                {
                    day: 'Tuesday',
                    seances: [
                        { id: 7, sance: 'Class A' },
                        { id: 8, sance: 'Class B' },
                        { id: 9, sance: 'Class C' },
                        { id: 10, sance: 'Class D' },
                        { id: 11, sance: 'Class E' },
                        { id: 12, sance: 'Class F' }
                    ]
                },
                {
                    day: 'Wednesday',
                    seances: [
                        { id: 13, sance: 'Class G' },
                        { id: 14, sance: 'Class H' },
                        { id: 15, sance: 'Class I' },
                        { id: 16, sance: 'Class J' },
                        { id: 17, sance: 'Class K' },
                        { id: 18, sance: 'Class L' }
                    ]
                },
                {
                    day: 'Thursday',
                    seances: [
                        { id: 19, sance: 'Class M' },
                        { id: 20, sance: 'Class N' },
                        { id: 21, sance: 'Class O' },
                        { id: 22, sance: 'Class P' },
                        { id: 23, sance: 'Class Q' },
                        { id: 24, sance: 'Class R' }
                    ]
                },
                {
                    day: 'Friday',
                    seances: [
                        { id: 25, sance: 'Class S' },
                        { id: 26, sance: 'Class T' },
                        { id: 27, sance: 'Class U' },
                        { id: 28, sance: 'Class V' },
                        { id: 29, sance: 'Class W' },
                        { id: 30, sance: 'Class X' }
                    ]
                },
                {
                    day: 'Saturday',
                    seances: [
                        { id: 31, sance: 'Class Y' },
                        { id: 32, sance: 'Class Z' },
                        { id: 33, sance: 'Class AA' },
                    ]
                }
            ],
            selectedSeance: {},
        }
    },
    methods: {
        getAllClasses() {
            gestionclasse.getAllClasses().then(response => {
                this.classes = response.data.data;
                console.log(this.classes);
            })
        },
        selectChange(seance, jour) {
            this.selectedSeance.seance = seance;
            this.selectedSeance.jour = jour.day;
            this.selectedSeance.classe = this.selectedClasse.nom;
            console.log(this.$refs.changes);
            this.$refs.changes[1].style.display="grid";
            console.log(this.selectedSeance);
        }
    },
    computed: {
        currentClasse(classeid) {
            return this.dialog != 0 && this.dialog == this.selectedClasse.id ? true : false;
        },
    }
}
</script>
<style scoped>
.main_content {
    margin-left: 20%;
    margin-top: 5%;
}

.classes {
    margin-top: 10%;
}</style>