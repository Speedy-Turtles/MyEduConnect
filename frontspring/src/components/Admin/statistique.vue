
<template>
    <div class="mt-5 py-5 ">
            <div class="content">
              <div  v-for="dat in All_data" :key="dat.id" >
                  <v-card  
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
                     <v-chart  class="chart col-lg-6 col-sm-12 col-xs-12"  :option="option"  autoresize/>
              </div> 
    </div>
</template>

<script>
import stat from "@/service/stat/stat.js";
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
                {icon:"mdi-account-school",nbr:0,titre:"Nombre of Etudiant"},
                {icon:"mdi-account-tie",nbr:0,titre:"Nombre of Ensignat"},
            ],
 
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
        stat.getMan().then((res)=>{
          this.option.series[0].data[0].value=res.data;
         
        });
        stat.getWomen().then((res)=>{
          this.option.series[0].data[1].value=res.data;
         
        });
        stat.getEtudiant().then((res)=>{
          this.All_data[0].nbr=res.data;
         
        });
        stat.getEnseignant().then((res)=>{
          this.All_data[1].nbr=res.data;
         
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
.content{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr ;
  grid-gap: 25px;
}

@media screen and (max-width:950px){
  .content{
    display: grid;
    grid-template-columns: 1fr 1fr ;
    grid-gap: 25px;
  }
  
}

@media screen and (max-width:530px){
  .content{
    display: grid;
    grid-template-columns: 1fr;
    grid-gap: 25px;
    text-align: center;
  }
}
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



