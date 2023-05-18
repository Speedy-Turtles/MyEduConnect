<template>
    <div class="all-content">

        <v-row>
            <v-col>
                <v-card max-width="400" max-height="400" variant="outlined" class="ml-2" elavation="4">
                    <div class="ml-4">
                        <v-icon color="#525fe1">
                            mdi-file-document
                        </v-icon>
                        <p class="text-bold text-h5">
                            nombre des documents officielles
                        <div class="line"></div>
                        </p>
                        <v-container gap="3">
                            <v-row>
                                <v-icon color="#a6cd49">
                                    mdi-checkbox-marked-circle
                                </v-icon>
                                <p class="text-h5 mt-3">
                                    demande accepte
                                <div class="number-stat text-center" style="background:#a6cd49">5</div>
                                </p>
                            </v-row>
                            <v-row>
                                <v-icon color="#525fe1">
                                    mdi-clock-outline
                                </v-icon>
                                <p class="text-h5 mt-3">
                                    demande en cours
                                <div class="number-stat text-center" style="background:#525fe1">5</div>
                                </p>
                            </v-row>
                            <v-row>
                                <v-icon color="red">
                                    mdi-close
                                </v-icon>
                                <p class="text-h5 mt-3">
                                    demande refuse
                                <div class="number-stat text-center" style="background:red">5</div>
                                </p>
                            </v-row>
                        </v-container>
                    </div>
                </v-card>
            </v-col>
            <v-col>
                <v-card max-width="400" max-height="400" variant="outlined" class="ml-2" elavation="4">
                    <div class="ml-4">
                        <v-container class="gap-5 grid-gp">
                            <v-row class="mb-4">
                                <v-icon color="#525fe1">
                                    mdi-account-school
                                </v-icon>
                                <p class="text-bold text-h5">
                                    nombre des etudiant
                                <div class="number-stat text-center" style="background:#525fe1;margin-top: -3%;">5</div>

                                </p>
                                <div class="line"></div>
                            </v-row>
                            <v-row class="mb-4">
                                <v-icon color="#525fe1">
                                    mdi-account-tie
                                </v-icon>
                                <p class="text-bold text-h5">
                                    nombre des enseignants
                                <div class="number-stat text-center" style="background:#525fe1;margin-top: -3%;">5</div>
                                </p>
                                <div class="line"></div>
                            </v-row>
                        </v-container>
                    </div>
                </v-card>
            </v-col>
        </v-row>
        <v-row class="ml-2">
            <v-col>
                <v-card max-width="350px" max-height="160px">
                    <v-container>
                        <v-row>
                            <v-col class="pa-2 ma-2">
                                <p class="text-h5">
                                    nombre des classes
                                </p>
                                <div class="number-stat" style="color: black;display: inline;">15</div>
                            </v-col>
                            <v-col align-self="end" class="pa-2 ma-2">
                                <router-link to="/chef_departement/gererspecialite">
                                    <div class="line line-hor">
                                        <v-icon style="transform: rotate(-90deg);color: white;">mdi-arrow-right</v-icon>
                                    </div>
                                </router-link>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card>
            </v-col>
            <v-col>
                <v-card max-width="350px" max-height="160px">
                    <v-container>
                        <v-row>
                            <v-col class="pa-2 ma-2">
                                <p class="text-h5">
                                    nombre des specialite
                                </p>
                                <div class="number-stat" style="color: black;display: inline;">3</div>
                            </v-col>
                            <v-col align-self="end" class="pa-2 ma-2">
                                <router-link to="/chef_departement/gererspecialite">
                                    <div class="line line-hor">
                                        <v-icon style="transform: rotate(-90deg);color: white;">mdi-arrow-right</v-icon>
                                    </div>
                                </router-link>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card>
            </v-col>
            <v-col>
                <v-card max-width="350px" max-height="160px">
                    <v-container>
                        <v-row>
                            <v-col class="pa-2 ma-2">
                                <p class="text-h5">
                                    neauveaute ajoute
                                </p>
                                <div class="number-stat" style="color: black;display: inline;">0</div>
                            </v-col>
                            <v-col align-self="end" class="pa-2 ma-2">
                                <router-link to="/chef_departement/gererneaveaute">
                                    <div class="line line-hor">
                                        <v-icon style="transform: rotate(-90deg);color: white;">mdi-arrow-right</v-icon>
                                    </div>
                                </router-link>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card>
            </v-col>
        </v-row>
        <v-row class="mt-2 ml-2">
            <v-chart class="chart" :option="option" autoresize />
        </v-row>
    </div>
</template>
<script>
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart, BarChart } from 'echarts/charts';
import {
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    GridComponent
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import { defineComponent } from 'vue';

use([
    CanvasRenderer,
    PieChart,
    BarChart,
    GridComponent,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
]);
export default defineComponent({
    name: 'statistique',
    components: {
        VChart,
    },
    data() {
        return {
            option: {
                title: {
                    text: 'nombre des etudiants par niveau'
                },
                tooltip: {},
                legend: {
                    data: ['etudiants']
                },
                xAxis: {
                    data: ['premiere anne', 'deuxieme anne', 'troisieme anne']
                },
                yAxis: {
                },
                series: [
                    {
                        name: 'Etudiants',
                        type: 'bar',
                        data: [0, 1, 2]
                    }
                ]
            },
            provide: {
                [THEME_KEY]: 'light',
            },
            nbr_demande:null,
            nbr_etudiants:null,
            nbr_proffesor:null,
            nbr_classes:null,
            nbr_specialite:null,
            nbr_neauv:null,
        }
    },
});
</script>
<style scoped>
.line {
    height: 10px;
    width: 360px;
    background-color: #9848fe;
    border-radius: 2rem 2rem 2rem 2rem;
    align-self: center;
}

.number-stat {
    height: 40px;
    width: 40px;
    padding: 5px;
    margin: 2% 0% 0% 5%;
    font-size: 2rem;
    line-height: 2rem;
    border-radius: 50%;
    color: whitesmoke;
}

.grid-gp>* {
    margin-top: 30px;
}

.line-hor {
    transform: rotate(90deg);
    height: 80%;
    width: 105%;
    align-self: flex-end;
    background-color: #525fe1;
    margin-left: 50%;
    margin-top: -50%;
    border-radius: 1 1 1 1;
    align-items: center;
    cursor: pointer;
    text-align: center;
}

.chart {
    height: 50vh;
}
</style>