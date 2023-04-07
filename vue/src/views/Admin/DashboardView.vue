<template>
    <div :class="small ? 'small' : 'large'" id="dashboard">
         <div class="sidebar_position">
            <sidebarVue
               @changerView="changerView"
               :ViewCurrent="ViewCurrent"
               :etatsidbar="etatsidbar"
               @changreetat="changreetat"
               :small="small"
            ></sidebarVue>
          </div>
          <transition name="fade" mode="out-in">
            <div :class="etatsidbar == true ? 'content close ' : 'content'">
                 <HeaderDashboard :ViewCurrent="ViewCurrent"></HeaderDashboard>
                <div class="ma-5 pa-5" id="home">
                  <div class="card">
                     <v-chart class="chart" :option="option"  autoresize/>
                  </div>  
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
import HeaderDashboard from '@/components/Admin/HeaderDashboard.vue';
import sidebarVue from '../../components/Admin/sidebar.vue'
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import { ref, defineComponent } from 'vue';

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
]);

export default defineComponent({
    name:'dashboard',
    
    provide: {
         [THEME_KEY]: 'dark',
    },
    data(){
        return{
            etatsidbar: false,
            show_all: true,
            snackbar: false,
            snackbar: false,
            ViewCurrent: "test",
            snackbar_edit: false,
            small:false,
        }
    },
    mounted() {
        window.addEventListener('resize', this.onresize);
    },
    methods:{
        onresize(){
        if (window.innerWidth < 750) {
                this.etatsidbar = true;
                this.small = false;
        } else{
                this.etatsidbar = false;
                this.small = false;
        }
        if(window.innerWidth<500){
                this.small = true;
                this.etatsidbar = false;
        }
      }, 
        changerView(data){
            this.ViewCurrent=data;
        },
        changreetat(etat){
            this.etatsidbar=etat;
        }
    },
    components:{
        VChart,sidebarVue,HeaderDashboard
    },
    setup() {
     const option = ref({
      title: {
        text: 'Static Student',
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
          name: 'Static Student',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: [
            { value: 335, name: 'Man' },
            { value: 310, name: 'Women' },
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
    });
    return { option };
  }
});
</script>

<style scoped>
.chart_box{
  width: 500px;
  height: 200px;
}
.chart {
  height: 50vh;
  width: 50vw;
}

@media screen and (max-width:600px) {
  .chart_box{
    width: 200px;
    height: 100px;
  }
  .chart {
    height: 30vh;
    width: 30vw;
  }
}
.sidebar_position{
    position: fixed;
    z-index: 2 !important;
}

  .content {
    width: calc(100%-280px);
    position: relative;
    scroll-behavior: smooth;
    transition: all 0.2s ease-in-out;
  }

  .large .content.close{
    width: calc(100%-90px) !important;
    margin-left: 90px !important;
    transition: all 0.2s ease-in-out;
    scroll-behavior: smooth;
  }
  .large .content {
         margin-left: 280px ;
  }
  .small .content{
        margin-left: 0px !important;
  }

</style>