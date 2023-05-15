<template>
 <div id="test" class="row text-center">
       <div class="col-lg-4">
        <v-card class="elevation-12" :disabled="Waiting==''" style="min-height:200px">
          <v-toolbar dark color="yellow">
             <v-toolbar-title >Wating</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <draggable  :group="{ name: 'app', put: false }" :options="dragOptions" v-model="Waiting" 
            @change="onUserMove"  
            @add="checkMove('waiting')"
            >
                <v-card  v-for="user in Waiting" :key="user.id"
    class="mx-auto mb-5"
    max-width="344"
    color="grey lighten-4"
    outlined
    style="cursor: pointer;"
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{user.roleName}}
        </div>
        <v-list-item-title class="text-h5 mb-3">
          {{user.firstName + "  "+user.lastname}}
        </v-list-item-title>
        <v-list-item-subtitle>Date Created :{{(user.created_at).substring(0,10)}}</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="80"
      >
        <img :src="user.photo" alt="">
    </v-list-item-avatar>
    </v-list-item>
  </v-card>
       </draggable>
          </v-card-text>
       </v-card>
       </div>
       <div class="col-lg-4">
        <v-card class="elevation-12" :disabled="Accpted==''"  style="min-height:200px">
          <v-toolbar dark color="green">
             <v-toolbar-title >Accepted</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <draggable  :options="dragOptions" v-model="Accpted" group="app"
            @change="onUserMove"  
            @add="checkMove('inProgress')"
          >
              <v-card v-for="user in Accpted" :key="user.id" 
    class="mx-auto mb-4"
    max-width="344"
    color="grey lighten-4"
    outlined
    style="cursor: pointer;"
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{user.roleName}}
        </div>
        <v-list-item-title class="text-h5 mb-3">
          {{user.firstName + "  "+user.lastname}}
        </v-list-item-title>
        <v-list-item-subtitle>Date Created :{{(user.created_at).substring(0,10)}}</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="80"
      >
        <img :src="user.photo" alt="">
    </v-list-item-avatar>
    </v-list-item>
  </v-card>
            </draggable>
            </v-card-text>
            </v-card>
     </div>
     <div class="col-lg-4">
      <v-card class="elevation-12" :disabled="Rejeter==''"  style="min-height:200px">
        <v-toolbar dark color="red">
           <v-toolbar-title >Rejeter</v-toolbar-title>
        </v-toolbar>
          <v-card-text>
          <draggable   :options="dragOptions"  v-model="Rejeter" group="app"
          @change="onUserMove"  
          @add="checkMove('completed')"
           >
                <v-card v-for="user in Rejeter" :key="user.id"
                class="mx-auto mb-4"
                max-width="344"
                color="grey lighten-4"
                outlined
                style="cursor: pointer;"
              >
                <v-list-item three-line>
                  <v-list-item-content>
                    <div class="text-overline mb-4">
                      {{user.roleName}}
                    </div>
                    <v-list-item-title class="text-h5 mb-3">
                      {{user.firstName + "  "+user.lastname}}
                    </v-list-item-title>
                    <v-list-item-subtitle>Date Created :{{(user.created_at).substring(0,10)}}</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-avatar
                    tile
                    size="80"
                  >
                    <img :src="user.photo" alt="">
                </v-list-item-avatar>
                </v-list-item>
              </v-card>
          </draggable>
          </v-card-text>
          </v-card>
      </div>
       <v-snackbar
           color="green"
           v-model="snackbar"
         >
           {{ message }}
           <template v-slot:action="{ attrs }">
             <v-btn
               color="#E84C03"
               text
               v-bind="attrs"
               @click="snackbar = false"
             >
               Fermer
             </v-btn>
           </template>
         </v-snackbar>
 </div>
</template>

 <script>
  import serviceUser from "@/service/UserInfo/userInfo.js"
  import draggable from 'vuedraggable'

  export default{
    name:"GererUser",
    components: {
      draggable
    },
    mounted(){
      this.fetchData();
    },
    data(){
      return{
        snackbar: false,
        message: '',
        dragOptions: {
            animation: 200, 
            disabled: false,
            ghostClass: 'ghost'
          },
        Waiting:[],
        Accpted:[],
        Rejeter:[],
          test:"",
          id:0,
      }
    },
    methods:{
      fetchData(){
        serviceUser.GetUsers().then((res)=>{
            this.Waiting=res.data.filter((user)=>{
              return user.status==0;
            });
            this.Accpted=res.data.filter((user)=>{
              return user.status==1;
            });
            this.Rejeter=res.data.filter((user)=>{
              return user.status==2;
            });
        }).catch((err)=>{
          console.log(err);
        });
      },
      checkMove(name){
          this.test = name;
          this.update();
      },
      onUserMove(event) {
         let itemMoved = event.moved || event.added;
         if(!itemMoved) return;
         this.id=itemMoved.element.id ;
      },
      update(){
       if(this.id!=0){
          if(this.test=="inProgress"){
           serviceUser.UpdateStatus({id:this.id,status:1}).then((res)=>{
            this.snackbar = true;
            this.message = 'User Accepted';
            this.fetchData();
           }).catch((err)=>{
             console.log(err);
           });
         }else if(this.test=="completed"){
          serviceUser.UpdateStatus({id:this.id,status:2}).then((res)=>{
            this.snackbar = true;
            this.message = 'User Rejeter';
             this.fetchData();
           }).catch((err)=>{
             console.log(err);
           });
         }else if(this.test=="waiting"){
          serviceUser.UpdateStatus({id:this.id,status:0}).then((res)=>{
            this.snackbar = true;
            this.message = 'User Waiting';
             this.fetchData();
           }).catch((err)=>{
             console.log(err);
           });
         }
      } 
     },
    },
    watch:{
     
    }
}
</script>
<style scoped>

 #test{
   display: flex;
   justify-content: center;
   margin:50px 0 ;
 }
 p{
  cursor: pointer;
 }
</style>
