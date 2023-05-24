<template>
    <div>
         <v-btn :loading="load" @click="dialog_delete=true">delete all</v-btn>
         <div class="text-center mb-5">
            <div class="row gap-4 p-5 mt-3">
                <div class="col-lg-4  mx-2 text-center">
                    <v-select
                         v-model="type_suggestion"
                         :items="type"
                         @change="fetchData()"  
                         label="Chose Type "
                     ></v-select>
              </div>
              <v-spacer></v-spacer>
              <div class="col-lg-1 ">
                    <v-select
                         v-model="per_page"
                        :items="items"
                        @change="fetchData()"
                        outlined
                        dense
                    ></v-select>
                    </div>
                </div>
         </div>
               <div v-if="loading==false" class="text-center">
                 <v-progress-circular
                        indeterminate
                        color="primary"
                      ></v-progress-circular>
               </div>
                    <div v-else-if="suggestion==''" class="container text-center" style="margin-top:10%;">
                        <div class="not_found">
            
                          <v-layout column>
                            <v-flex>
                              <img src="../../../public/etudiant/images/interdit.jpg" id="interdit" width="70px">
                            </v-flex>
                            <v-flex>
                              <div class="novote">
                                  No Suggestion Now
                              </div>
                           </v-flex>
                            <v-flex>
                              <div class="check">
                                please check later
                              </div>
                            </v-flex>
                          </v-layout>
                        </div>
                   </div>
            <div v-else class="row">
               <div  v-for="sugg in suggestion" :key="sugg.id"  class="col-lg-4">
                  <v-card 
                      max-width="344"
                      outlined
                    >
                    <v-list-item three-line>
                      <v-list-item-content>
                        <div class="text-overline mb-4">
                            {{sugg.user['FirstName']}} {{sugg.user['LastName']}}
                        </div>
                        <v-list-item-title class="text-h6 mb-1">
                            {{ sugg.Message }}
                        </v-list-item-title>
                        <v-list-item-subtitle>{{sugg.type}}</v-list-item-subtitle>
                      </v-list-item-content>
                      <v-list-item-avatar size="80">
                            <v-avatar v-if="sugg.user['Photo'].length>2" size="70" >
                                    <img  :src="'http://localhost:8000'+sugg.user['Photo']"/>
                            </v-avatar>
                        <v-avatar color="blue" size="70"  v-else>
                            <span class="white--text">{{ sugg.user['Photo'] }}</span>
                        </v-avatar>
                    </v-list-item-avatar>
                    </v-list-item>
                    <v-card-actions>
                      <v-btn
                        rounded
                        color="error"
                        @click="deleteSugg(sugg)"
                      >
                        delete
                      </v-btn>
                    </v-card-actions>
                  </v-card>
              </div>
         </div>
          <div class="mt-4 text-center " >
            <div >
                <v-btn  :disabled="pagination.prev_page==null" @click="changerPage(pagination.current_page-1)" small>
                 <v-icon>
                   mdi-chevron-left
                </v-icon>
                </v-btn>
                <v-btn  v-for="num in (Math.ceil (pagination.per_page ? (pagination.total / pagination.per_page) : 1))" :key="num"
                   class="mx-2 mb-1"
                   color="primary"
                   @click="changerPage(num)"
                    small
                    :disabled="pagination.current_page==num"
                    x-small>
                   {{num}}
                </v-btn>
                <v-btn :disabled="pagination.next_page==null" small  @click="changerPage(pagination.current_page+1)" >
                 <v-icon>
                   mdi-chevron-right
                </v-icon>
                </v-btn>
            </div>
          </div>

          <v-dialog  
          transition="dialog-bottom-transition"
          max-width="600"
          v-model="dialog_delete"
        >
         <template >
           <v-card>
             <v-toolbar
               color="danger"
               dark
             >Delete All Suggestion </v-toolbar>
             <v-card-text>
               <div class="text-h2 pa-12">Do You Want Delete All Suggestion</div>
             </v-card-text>
             <v-card-actions class="justify-end">
               <v-btn 
                 text
                 :loading="load"
                  @click="delete_all()"
               >Delete</v-btn>
               <v-btn
               text
               @click="dialog_delete=false"
             >Close</v-btn>
             </v-card-actions>
           </v-card>
         </template>
       </v-dialog> 


          <v-dialog  v-if="item_selected!=''"
          transition="dialog-bottom-transition"
          max-width="600"
          v-model="dialog"
        >
         <template >
           <v-card>
             <v-toolbar
               color="danger"
               dark
             >Delete Suggestion </v-toolbar>
             <v-card-text>
               <div class="text-h2 pa-12">Suggestion by {{item_selected.user['FirstName']}}</div>
             </v-card-text>
             <v-card-actions class="justify-end">
               <v-btn 
                 text
                 :loading="load"
                  @click="confrimDelete(item_selected.id)"
               >Delete</v-btn>
               <v-btn
               text
               @click="dialog=false"
             >Close</v-btn>
             </v-card-actions>
           </v-card>
         </template>
       </v-dialog> 
       <v-snackbar
       v-model="snackbar"
     >
        {{ message }}
       <template v-slot:action="{ attrs }">
         <v-btn
           color="indigo"
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
import suggestionService from '@/service/GererSuggestion/suggestion.js';
export default{
    name:"suggestion",
    mounted(){
      window.Echo.channel('NotifRealtime').listen('notif',(e)=>{
        this.fetchData();
        this.changerPage(1);
      })
   },
    created() {
        this.fetchData();
        this.changerPage(1);
    },
    data(){
        
        return{
            pagination:{
              next_page:0,
              prev_page:0,
              per_page:0,
              total:0,
              current_page:1
            },
            type_suggestion:'',
            snackbar:false,
            dialog_delete:false,
            message:'',
            load:false,
            per_page:3,
            item_selected:[],
            dialog:false,
            suggestion:[],
            search:'',
            items:[3,5,10,100,1000],
            loading:false,
            type:['All','Social','educative','Personel']
        }
    },
    methods: {
        deleteSugg(sugg){
            this.dialog=true;
            this.item_selected=sugg;
        },
        changerPage(a){
            this.pagination.current_page=a;
            this.fetchData();
        },
        fetchData(){
            suggestionService.getAllSuggestion(this.type_suggestion,this.per_page,this.pagination.current_page).then((response)=>{
                this.loading=true;
                this.suggestion=response.data.data.data;
                this.pagination.current_page=response.data.data.current_page;
                this.pagination.total=response.data.data.total;
                this.pagination.next_page=response.data.data.next_page_url?.split("=")[1];
                this.pagination.prev_page=response.data.data.prev_page_url?.split("=")[1];
                this.pagination.per_page=response.data.data.per_page;
            })
        },
        confrimDelete(id){
            this.load=true;
            suggestionService.deleteSuggestion(id).then((res)=>{
                this.snackbar=true;
                this.message=res.data.data;
                this.dialog=false;
                this.load=false;
                this.fetchData();
            });
        },
        delete_all(){
          this.load=true;
          suggestionService.delete_all_sugg().then((res)=>{
                this.snackbar=true;
                this.message=res.data.data;
                this.dialog_delete=false;
                this.load=false;
                this.fetchData();
          })
        }
    },
    computed:{

    },
    components:{

    }
}
</script>

<style scoped>

</style>