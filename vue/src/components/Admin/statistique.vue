<template>
    <div class="mt-5 py-5 ">
            <div class="row container-fluid">
              <div   v-for="dat in All_data" :key="dat.id"   class="col-lg-3">
                <v-skeleton-loader v-if="loading"  
                        max-width="180"
                        type="card"
                    ></v-skeleton-loader>
                  <v-card  
                    v-if="!loading" 
                    max-width="180"
                    max-height="180"
                    variant="outlined"
                   >
                <v-list-item   style="padding: 10px;" class="text-center">
                  <div>
                    <div class="text-overline-h4 mb-3">
                      <v-icon size="35px" color="blue" >{{dat.icon}}</v-icon>
                    </div>
                    <div  class="text-h6 mb-2">
                      {{ dat.nbr }}
                    </div>
                    <div class="text-h6">{{dat.titre}}</div>
                  </div>
                </v-list-item>
              </v-card>
            </div>
            </div>
                <div class="card row mt-5">
                     <v-chart  class="chart col-lg-6" :option="option"  autoresize/>
                     <v-chart class="chart col-lg-6" :option="option_two"  autoresize/>
              </div> 
    </div>
</template>

<script>
import service_admin from "@/service/admin/gererAdmin.js";
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart,BarChart} from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import {  defineComponent } from 'vue';

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
    name:"stat",
    mounted(){
        this.getStatique();
    },
    data(){
        return{
            loading:true,
            All_data:[
                {icon:"mdi-account-wrench",nbr:0,titre:"Nombre of technicien"},
                {icon:"mdi-account-school",nbr:0,titre:"Nombre of Etudiant"},
                {icon:"mdi-account-tie",nbr:0,titre:"Nombre of Ensignat"},
                {icon:"mdi-account-cog",nbr:0,titre:"Nombre of Chef Departement"},
            ],
     option_two :{
        title: {
          text: 'Static Etudiant'
        },
        tooltip: {},
        legend: {
          data: ['sales']
        },
        xAxis: {
             data: ['Accpted', 'refused', 'En cours']
        },
        yAxis: {
           
        },
        series: [
          {
            name: 'sales',
            type: 'bar',
            data: [0, 1, 2]
          }
        ]
      },
     option : {
         title: {
         text: `Static Student `,
         left: 'center',
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)',
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['Man', 'Women'],
      },
      series: [
        {
          name: 'Static Studesdsdnt',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: [
            { value:0, name: 'Man' },
            { value:0, name: 'Women' },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)',
            },
          },
        },
         ],
      }
        }
    },
    provide: {
         [THEME_KEY]: 'dark',
    },
  methods:{
    getStatique(){
        service_admin.getStat().then((res)=>{
            const data=res.data.data;
            this.All_data[3].nbr=data.nb_chef;
            this.All_data[2].nbr=data.nbr_ens;
            this.All_data[1].nbr=data.nb_etudiant;
            this.All_data[0].nbr=data.nb_technicien;
            this.option.series[0].data[0].value=data.nb_man;
            this.option.series[0].data[1].value=data.nb_woman;
            this.option_two.series[0].data=[data.nb_etudiant,data.nbr_etudiant_refused,data.nbr_etudiant_encours,10];
            //this.option_two.yAxis.data=[0,1]
            this.loading=false;
        });   
    }
  },
    computed:{
        newStat(){
            return [
            { value:this.nb_etudiant_man, name: 'Man' },
            { value:this.nb_etudiant_women, name: 'Women' },
          ]
        }
    },
    components:{
        VChart
    },
});
</script>

<style scoped >
  .chart {
    height: 50vh;
    width: 30vw;
  }
  
  @media screen and (max-width:750px) {
    .chart {
      height: 30vh;
      width: 50vw;
    }
  }

  @media screen and (max-width:500px) {
    .chart {
      height: 30vh;
      width: 70vw;
    }
  }

</style>