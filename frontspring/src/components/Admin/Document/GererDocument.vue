<template>
    <div>
        <v-card>
        <template class="mt-5 py-5">
            <v-card >
                <v-tabs
                fixed-tabs
                background-color="#5094df"
                dark
              >
                <v-tab @click="page=0">
                    Document En cours
                </v-tab>
                <v-tab @click="page=1">
                    Document Accepter
                </v-tab>
                <v-tab @click="page=2">
                    Document Rejeter
                </v-tab>
              </v-tabs>
              <v-card-text v-if="page==0" >
                    <div v-if="waitingDoc==''">
                        <div class="text-center">
                            <p class="NoData">
                                No data
                             </p>
                        </div>
                    </div>
                    <div v-else class="row ">
                        <v-card class="col-lg-4 mx-2" v-for="doc in waitingDoc"
                         :key="doc"
                        max-width="344"
                        outlined
                      >
                      <v-list-item three-line>
                        <v-list-item-content>
                          <div class="text-overline mb-4">
                              {{doc.user['firstName']}}  {{doc.user['lastName']}}
                          </div>
                          <v-list-item-title class="text-h5 mb-1">
                            {{doc.document['type']}}
                          </v-list-item-title>
                          <v-list-item-subtitle>Nombre Document Total <b>{{doc.nombre}} </b></v-list-item-subtitle>
                          <v-list-item-subtitle>Nombre Document Dispo <b>{{(doc.document['nombreDispo']-doc.nombre)}} </b></v-list-item-subtitle>
                      </v-list-item-content>
                        <v-list-item-avatar
                          tile
                          size="80"
                          color="grey"
                        >
                          <img :src="doc.user['photo']">
                      </v-list-item-avatar>
                      </v-list-item>
                        <v-card-actions>
                          <v-btn
                            outlined
                            rounded
                            text
                            color="green"
                            @click="changerEtat(doc.user['id'],doc.document['id'],1)"
                          >
                            Accepter
                          </v-btn>
                          <v-btn
                            outlined
                            rounded
                            text
                            color="error"
                            @click="changerEtat(doc.user['id'],doc.document['id'],2)"
                          >
                            Rejeter
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </div>
             </v-card-text>
             <v-card-text v-else-if="page==1">
                <div v-if="AcceptedDoc==''">
                    <div class="text-center">
                        <p class="NoData">
                            No data
                         </p>
                    </div>
                </div>
                <div v-else class="row">
                    <v-card class="col-lg-4 mx-2" v-for="doc in AcceptedDoc"
                     :key="doc.id"
                    max-width="344"
                    outlined
                  >
                    <v-list-item three-line>
                      <v-list-item-content>
                        <div class="text-overline mb-4">
                            {{doc.user['firstName']}}  {{doc.user['lastName']}}
                        </div>
                        <v-list-item-title class="text-h5 mb-1">
                          {{doc.document['type']}}
                        </v-list-item-title>
                        <v-list-item-subtitle>Nombre Document Total <b>{{doc.nombre}} </b></v-list-item-subtitle>
                        <v-list-item-subtitle>Nombre Document Dispo <b>{{(doc.document['nombreDispo']-doc.nombre)}} </b></v-list-item-subtitle>
                    </v-list-item-content>
                      <v-list-item-avatar
                        tile
                        size="80"
                        color="grey"
                      >
                        <img :src="doc.user['photo']">
                    </v-list-item-avatar>
                    </v-list-item>
                    <v-card-actions>
                      <v-btn
                        outlined
                        rounded
                        text
                        color="error"
                        @click="changerEtat(doc.user['id'],doc.document['id'],2)"
                      >
                        Rejeter
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </div>
              </v-card-text>
              <v-card-text v-else>
                <div v-if="RejeterDoc==''">
                    <div class="text-center">
                         <p class="NoData">
                            No data
                         </p>
                    </div>
                </div>
                <div v-else class="row">
                    <v-card v-for="doc in RejeterDoc"
                     :key="doc.id"
                    class="col-lg-4 mx-2"
                    max-width="344"
                    outlined
                  >
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="text-overline mb-4">
                          {{doc.user['firstName']}}  {{doc.user['lastName']}}
                      </div>
                      <v-list-item-title class="text-h5 mb-1">
                        {{doc.document['type']}}
                      </v-list-item-title>
                      <v-list-item-subtitle>Nombre Document Total <b>{{doc.nombre}} </b></v-list-item-subtitle>
                      <v-list-item-subtitle>Nombre Document Dispo <b>{{(doc.document['nombreDispo']-doc.nombre)}} </b></v-list-item-subtitle>
                  </v-list-item-content>
                    <v-list-item-avatar
                      tile
                      size="80"
                      color="grey"
                    >
                      <img :src="doc.user['photo']">
                  </v-list-item-avatar>
                  </v-list-item>
                    <v-card-actions>
                      <v-btn
                        outlined
                        rounded
                        text
                        color="error"
                        @click="deleteDoc(doc.user['id'],doc.document['id'])"
                      >
                        Delete
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </div>
              </v-card-text>
            </v-card>
          </template>
        </v-card>
        <v-snackbar
        color="#5094df"
        v-model="snackbar"
      >
        {{ message }}
        <template v-slot:action="{ attrs }">
          <v-btn
            color="#000"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
    </div>
</template>

<script>
import serviceDoc from "@/service/GererDoc/gererDoc.js"
export default{
    name:'doc',
    created(){
        this.fetchData();
    },
    data(){
        return{
            message:"",
            snackbar:false,
            page:0,
            waitingDoc:[],
            AcceptedDoc:[],
            RejeterDoc:[]
        }
    },
    methods:{
        fetchData(){
            serviceDoc.getDoc().then((res)=>{
                this.waitingDoc=res.data.waiting;
                this.AcceptedDoc=res.data.accepted;
                this.RejeterDoc=res.data.Rejeter;
            });
        },
     changerEtat(userid,documentid,etat){
            serviceDoc.ChangerEtat(documentid,userid,etat).then((res)=>{
                // console.log(documentid+" "+userid+" "+etat);
                // console.log(res.data);
                this.snackbar=true;
                if(etat==1){
                    this.message="User Demande ACCEPTED";
                }else{
                    this.message="User Demande REJECTED";
                }
                this.fetchData();
            })
        },
        deleteDoc(iduser,iddoc){
            serviceDoc.DeleteUserDoc(iddoc,iduser).then((res)=>{
                this.snackbar=true;
                this.message="Demande Deleted with success";
                this.fetchData();
            })
        }
    },
    components:{
       
    }
}
</script>

<style scoped>
.NoData{
    font-size: 20px;
    font-weight: 600;
    margin-top:15px;
    color:#000;
}
</style>