<template>
 <div id="test">
       <div>
           <h1>Todo</h1>
           <draggable  :group="{ name: 'app', put: false }" :options="dragOptions" v-model="Waiting" 
             @change="onUserMove"  
             @add="checkMove('waiting')"
             >
          <p v-for="user in Waiting" :key="user.id">
            {{ user.id}}
            <img   width="100px" height="100px" :src="user.photo" >
          </p>
        </draggable>
       </div>
       <div>
        <h1>inProgress</h1>
      <draggable  :options="dragOptions" v-model="Accpted" group="app"
      @change="onUserMove"  
      @add="checkMove('inProgress')"
    >
        <p v-for="user in Accpted" :key="user.id">
          {{ user.id}}
          <img   width="100px" height="100px" :src="user.photo" >
        </p>
      </draggable>
     </div>
     <div>
      <h1>Completed</h1>
    <draggable   :options="dragOptions"  v-model="Rejeter" group="app"
    @change="onUserMove"  
    @add="checkMove('completed')"
     >
      <p v-for="user in Rejeter" :key="user.id">
        {{ user.id}}
          <img width="100px" height="100px" :src="user.photo" >
      </p>
    </draggable>
   </div>
   <br>
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
            this.fetchData();
           }).catch((err)=>{
             console.log(err);
           });
         }else if(this.test=="completed"){
          serviceUser.UpdateStatus({id:this.id,status:2}).then((res)=>{
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
   justify-content: space-around;
   margin:50px 0 ;
 }
 p{
  cursor: pointer;
 }
</style>
