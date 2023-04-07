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
              <div class="ma-5 pa-5" id="home">
                <v-container>
                    <v-row>
                      <h2>
                        <span style="color: #5094df">{{
                          ViewCurrent.toUpperCase().substring(0, ViewCurrent.length - 3)
                        }}</span
                        >{{
                          ViewCurrent.toUpperCase().substring(
                            ViewCurrent.length,
                            ViewCurrent.length - 3
                          )
                        }}
                      </h2>
                      <v-spacer></v-spacer>
                      </v-row>
                </v-container>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
import sidebarVue from '../../components/Admin/sidebar.vue'
export default{
    name:'dashboard',
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
        sidebarVue
    }
}
</script>

<style scoped>
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