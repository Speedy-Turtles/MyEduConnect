<template>
    <div class="all-content">
        <v-card max-width="400" max-height="300" variant="outlined" class="ml-2" elavation="4">
            <v-row>
                <v-col>
                    <div class="ml-4">
                        <v-icon>
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
                </v-col>
                <v-col>
                    
                </v-col>
            </v-row>
        </v-card>
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
    name: 'statistique'
});
</script>
<style scoped>
.line {
    height: 10px;
    width: 300px;
    background-color: #9848fe;
    border-radius: 2rem 2rem 2rem 2rem;
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
}</style>