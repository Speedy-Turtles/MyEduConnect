<template>
    <div>
        <navbar />
        <navigationDrawer :current-page="cureentPage" />
        <div class="main_content">
            <div class="classes">
                <v-row>
                    <v-col v-for="classe in  classes ">
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
                                                        <div class="emploiTable">
                                                            <v-row align='center' justify="center">
                                                               <h1 style="magrin-top:15%!important"> emploi de classe {{ selectedClasse.nom }}</h1>
                                                            </v-row>
                                                            <v-simple-table>
                                                                <template v-slot:default>
                                                                    <thead>
                                                                        <tr>
                                                                            <th class="text-left pa-5"
                                                                                v-for=" seance  in  seances ">
                                                                                {{seance}}
                                                                            </th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody class="pa-5">
                                                                        <tr v-for=" jour  in  emploi ">
                                                                            <td class="pa-5">{{ jour.day }}</td>
                                                                            <td v-for=" classe  in jour.seances " class="pa-3">
                                                                                {{classe}}<br>A.Chaloueh <span>I0.9</span>
                                                                            <v-btn icon color="orange" @click="selectChange(classe,jour)">
                                                                                <v-icon>mdi-pen</v-icon>
                                                                            </v-btn>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </template>
                                                            </v-simple-table>
                                                        </div>
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
export default {
    name: '',
    components: {
        navbar,
        navigationDrawer
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
            seances:["      ","Séance 1","Séance 2","Séance 3","Séance 4","Séance 5","Séance 6"],
            seances2:["Séance 1","Séance 2","Séance 3","Séance 4","Séance 5","Séance 6"],
            jours:["Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"],
            emploi : [
            { day: 'Monday', seances: ['class1', 'class2', 'class3', 'class4', 'class5', 'class6'] },
            { day: 'Tuesday', seances: ['class1', 'class2', 'class3', 'class4', 'class5', 'class6'] },
            { day: 'Wednesday', seances: ['class1', 'class2', 'class3', 'class4', 'class5', 'class6'] },
            { day: 'Thursday', seances: ['class1', 'class2', 'class3', 'class4', 'class5', 'class6'] },
            { day: 'Friday', seances: ['class1', 'class2', 'class3', 'class4', 'class5', 'class6'] },
            { day: 'Saturday', seances: ['class1', 'class2', 'class3', 'class4', 'class5', 'class6'] },
            ],
            selectedSeance:{},
        }
    },
    methods: {
        getAllClasses() {
            gestionclasse.getAllClasses().then(response => {
                this.classes = response.data.data;
                console.log(this.classes);
            })
        },
        selectChange(seance,jour){
            this.selectedSeance.seance=seance;
            this.selectedSeance.jour=jour;
            for(classe in this.classes){
                if(classe.id==this.selectedClasse){
                    let className=classe.name;
                }
            }
            this.selectedSeance.classe=className;
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
}
</style>