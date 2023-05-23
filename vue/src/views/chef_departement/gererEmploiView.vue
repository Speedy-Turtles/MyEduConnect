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
                                    <v-btn @click="dialog = classe.id; selectedClasse = classe; getEmploiByclass()">
                                        <v-icon>mdi-calendar</v-icon>
                                        emploi
                                    </v-btn>
                                    <div style="z-index: 10000000000000000;">
                                        <template>
                                            <v-row justify="center">
                                                <v-dialog fullscreen hide-overlay v-model="currentClasse"
                                                    transition="dialog-bottom-transition">
                                                    <v-card>
                                                        <v-toolbar dark color="primary">
                                                            <v-btn icon dark @click=" dialog = 0; selectedClasse = {}">
                                                                <v-icon>mdi-close</v-icon>
                                                            </v-btn>
                                                            <v-toolbar-title>update emploi {{ selectedClasse.nom
                                                            }}</v-toolbar-title>
                                                            <v-spacer></v-spacer>
                                                            <v-toolbar-items>
                                                                <v-btn dark text @click=" dialogChange=false;dialog = 0; selectedClasse = {}">
                                                                    Save
                                                                </v-btn>
                                                            </v-toolbar-items>
                                                        </v-toolbar>
                                                        <v-row class="allModule">
                                                            <v-col class="emploiTable" style="width:80%;height: 80%;">
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
                                                                                    {{ seance }}
                                                                                </th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody class="pa-5">
                                                                            <tr v-for="  jour   in   Object.keys(emploi)  ">
                                                                                <td class="pa-5">{{ jour }}</td>
                                                                                <td v-for="  classe,index   in  Object.values(emploi[jour])"
                                                                                    class="pa-3">
                                                                                    <div>
                                                                                        <div v-if="classe.length == 0">
                                                                                            no seance yet !
                                                                                        </div>
                                                                                        <div v-else
                                                                                            style="background-color: green;">
                                                                                            <p>{{ classe[0].user.LastName }}
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <v-btn icon color="orange"
                                                                                        @click=" selectChange(index, jour,classe)">
                                                                                        <v-icon>mdi-pen</v-icon>
                                                                                    </v-btn>    
                                                                                </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </template>
                                                                </v-simple-table>
                                                            </v-col>
                                                            <v-col v-if="dialogChange" justify="center" align='center'>
                                                                <v-card width="100%" height="100%">
                                                                    <v-toolbar color="primary" dark>update
                                                                        seance</v-toolbar>
                                                                    <div style="width='80%';margin-top: 15%;">
                                                                        <v-row justify="center" width="80%">
                                                                                                <v-select  v-model="choosenProff" label="Professors" :items="proffesorsSelect"></v-select>
                                                                        </v-row>
                                                                        <v-row  justify="center" align="center">
                                                                            <v-select v-model="choosensalle"  label="salle" :items="sallesSelect"></v-select>
                                                                        </v-row>
                                                                        <v-row  justify="center" align="center">
                                                                            <v-select v-model="choosenmatiere" label="matiere" :items="matieresselect"></v-select>
                                                                        </v-row>
                                                                    </div>
                                                                    <v-card-actions class="justify-end">
                                                                        <v-btn icon :loading="loaderSave" color="green"
                                                                            @click="SaveSeance()"><v-icon>mdi-content-save</v-icon></v-btn>
                                                                    </v-card-actions>
                                                                </v-card>
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
            <v-snackbar v-model="snackbar" :timeout="timeout" :color="color" style="z-index:50000;">
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
import gestionclasse from '@/service/classe/gestionclasse';
import gestionProffesors from '@/service/proffesors/gestionProffesors';
import gestionEmploi from '@/service/chefDepEmploi/gestionEmploi'
export default {
    name: '',
    components: {
        navbar,
        navigationDrawer
    },
    created() {
        this.getAllClasses();
        this.getAcceptedProffesors();
        this.getAllMatieres();
        this.getAllSalles();
    },
    data() {
        return {
            matieresselect:[],
            sallesSelect:[],
            dialog: false,
            dialogChange: false,
            cureentPage: 'emploi',
            classes: [],
            dialog: 0,
            selectedClasse: {},
            seances: ["", "Séance 1", "Séance 2", "Séance 3", "Séance 4", "Séance 5", "Séance 6"],
            seances2: ["Séance 1", "Séance 2", "Séance 3", "Séance 4", "Séance 5", "Séance 6"],
            emploi: [],
            selectedProff: null,
            selectedSeance: {},
            proffesors: [],
            choose: "prof",
            dropzone: null,
            loaderEmploi: false,
            matieres: [],
            salles: [],
            proffesorsSelect:[],
            choosenmatiere:null,
            choosensalle:null,
            choosenProff:null,
            loaderSave:false
        }
    },
    methods: {
        getAllMatieres() {
            gestionEmploi.getMatieres().then(response => {
                this.matieres = response.data.data;
                this.matieres.forEach((x)=>{
                    console.log(x);
                    this.matieresselect.push({text:x.nom,value:x.id});
                });
                console.log(this.matieres);
            })
        },
        getAllSalles() {
            gestionEmploi.getSalle().then(response => {
                this.salles = response.data.data;
                this.salles.forEach((x)=>{
                    console.log(x);
                    this.sallesSelect.push({text:x.nom,value:x.id});
                });
                console.log(this.salles);
                console.log(this.sallesSelect);
            });
        },
        selectProff(proffesor) {
            this.selectedProff = proffesor;
            console.log(this.selectedProff);
        },
        getAllClasses() {
            gestionclasse.getAllClasses().then(response => {
                this.classes = response.data.data;
                console.log(this.classes);
            })
        },
        grapDropzone() {
            if (this.choose == "prof") {
                console.log("here");
                this.dropzone = document.querySelector('.dropzone-profe');
            }
            else if (this.choose == "matiere") {
                this.dropzone = document.querySelector('.dropzone-matiere');
            } else {
                this.dropzone = document.querySelector('.dropzone-salle');
            }
        },
        onPosChanged: function (positionDiff, absolutePosition, event) {
            // console.log("left corner", absolutePosition.left);
            // console.log("top corner", absolutePosition.top);
            //document.querySelector('.dropzone-profe').style.setProperty('display', '')
            //this.dropzone=document.querySelector('.dropzone-profe');
            // this.dropzone=this.$refs.dropzoneprofe[0];
            // console.log(this.dropzone.getBoundingClientRect());
            // let extdropzone=document.querySelector('#dropzoneprofe');
            // extdropzone.style.display="";
            // console.log(extdropzone.getBoundingClientRect());
            // this.dropzone=extdropzone;
            /*this.$nextTick(() => {
                this.grapDropzone();
                const component = this.dropzone;
                const rect = component.getBoundingClientRect();
                console.log(rect.width, rect.height, rect.left, rect.top);
            });
            let isOverDropZone = (
                absolutePosition.left >= this.dropzone.offsetLeft &&
                absolutePosition.left <= this.dropzone.offsetLeft + this.dropzone.offsetWidth &&
                absolutePosition.top >= this.dropzone.offsetTop &&
                absolutePosition.top <= this.dropzone.offsetTop + this.dropzone.offsetHeight
            );*/
            // this.grapDropzone();
            // this.dropzone.style.backgroundColor = "green!important";
            // this.dropzone.style.opacity = "0.5!important";
            // this.dropzone.innerHTML = "<h1>Drop here</h1>";
        },
        selectChange(seance, jour,classe) {
            console.log('aaa');
            if(classe.length!=0){
                this.choosenProff={value:classe[0].user.id,text:classe[0].user.LastName};
                this.choosenmatiere={value:classe[0].salle.id,text:classe[0].salle.nom};
                this.choosenmatiere={value:classe[0].matiere.id,text:classe[0].matiere.nom};
            }
            this.selectedSeance.seance = seance+1;
            this.selectedSeance.jour = jour;
            this.selectedSeance.emploi_id = this.selectedClasse.emploi_id;
            //this.dialog=true;
            // console.log(this.selectedSeance);
            // console.log(this.$refs.changes);
            // this.$refs.changes[1].style.display = "grid";
            console.log("aa");
            this.dialogChange = true;
            console.log(this.dialogChange);
            console.log(this.selectedSeance);
        },
        getAcceptedProffesors() {
            gestionProffesors.getAcceptedProffesors().then(response => {
                this.proffesors = response.data.data;
                this.proffesors.forEach((x)=>{
                    this.proffesorsSelect.push({text:x.LastName,value:x.id});
                });
                console.log(this.proffesors);
            })
        },
        getEmploiByclass() {
            console.log(this.selectedClasse.id);
            let classe = {
                class_id: this.selectedClasse.id,
            }
            this.loaderEmploi = true;
            gestionEmploi.getEmploi(this.selectedClasse.id).then(response => {
                this.emploi = response.data.data;
                console.log(this.emploi);
                this.loaderEmploi = true;
            })
        },
        SaveSeance(){
            this.loaderSave=true;
            this.selectedSeance.user_id=this.choosenProff;
            this.selectedSeance.matiere_id=this.choosenmatiere;
            this.selectedSeance.salle_id=this.choosensalle;
            this.selectedSeance.duree='1.30';
            console.log(this.selectedSeance);
            gestionEmploi.storeSeance(this.selectedSeance).then(response=>{
                this.loaderSave=false;
                console.log(response.data.data);
                this.getEmploiByclass();
            }).catch(error=>{
                console.log(error);
            });
            this.dialogChange=false;
            this.selectedSeance=null;
        }
    },
    computed: {
        currentClasse(classeid) {
            return this.dialog != 0 && this.dialog == this.selectedClasse.id ? true : false;
        },
    },
    mounted() {
            this.$nextTick(() => {
                const component = this.$refs.dropzoneprofe[0];
                const rect = component.getBoundingClientRect();
                console.log(rect.width, rect.height, rect.left, rect.top);
            });
        },
}
</script>
<style scoped>
.main_content {
    margin-left: 20%;
    margin-top: 5%;
}

.drag {
    cursor: pointer;
}

.classes {
    margin-top: 10%;
}

@media screen and (max-width: 600px) {
    .main_content {
        margin-left: 0%;
    }

    .classes {
        margin-top: 20%;
    }
}

@media screen and (max-width: 768px) {
    .main_content {
        margin-left: 0%;
    }

    .classes {
        margin-top: 20%;
    }

}

.pos {
    /*center an element*/
    margin-top: 20%;
    left: 20%;
    transform: translate(-50%, -50%);
}

.dropzone-prof {
    width: 400px;
    height: 200px;
    border: 1px solid black;
    background-color: #f2f2f2;
    text-align: center;
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
}
</style>