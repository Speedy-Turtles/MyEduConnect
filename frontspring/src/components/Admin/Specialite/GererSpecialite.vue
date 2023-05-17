<template>
    <div>
        <v-card elavation="7" style="padding:25px">
            <h4 class="text-center py-4">Specialite</h4>
                  <div class="row gap-4 p-5 mt-3">
                      <div class="col-lg-3 mx-2 text-center">
                        <v-text-field
                            v-model="search"
                            @keyup="FetchData()"
                            append-icon="mdi-magnify"
                            label="Rechercher"
                            single-line
                            hide-details
                       ></v-text-field>  
                    </div>
                    
                      <div class="col-lg-1 mt-4 mx-2 text-center">
                        <v-select
                            v-model="per_page"
                            :items="items"
                            @change="FetchData()"
                            outlined
                            dense
                      ></v-select>
                    </div>
                      <v-spacer></v-spacer>
                      <div class="col-lg-3  text-center">
                        <div class="row">
                          <div class="col-lg-12 text-center">
                            <v-tooltip bottom>
                              <template v-slot:activator="{ on, attrs }">
                                  <v-btn
                                  @click="AddCategory=true"
                                  class="mb-5"
                                  v-bind="attrs"
                                  v-on="on"
                                  outlined
                                  fab
                                  color="#E84C03"
                                >
                                  <v-icon>mdi-book-education</v-icon>
                                </v-btn>
                              </template>
                              <span>Ajouter Specialte</span>
                            </v-tooltip>
                      </div>
                          </div> 
                      </div>
                      </div>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th  class="text-center ">
                  Id
                </th>
                <th  class="text-center ">
                  Niveau
                </th>
                <th  class="text-center ">
                  Type
                </th>
                <th class="text-center">
                  Operation
                </th>
              </tr>
            </thead>
            <tbody v-if="spec==''">
                <tr>
                   <td colspan="5" class="text-center">No data</td>
                </tr>
            </tbody>
            <tbody v-else>
                <tr v-for="val in spec" :key="val.id">
                   <td class="text-center">{{val.idspec}}</td>
                   <td class="text-center">{{val.niveau}}</td>
                   <td class="text-center">{{val.type}}</td>
                   <td class="text-center">
                       <v-btn color="error"  class="mx-2">
                          <v-icon>
                            mdi-trash-can
                          </v-icon>
                       </v-btn>
                       <v-btn  color="warning" class="mx-2 ">
                          <v-icon>
                            mdi-circle-edit-outline
                          </v-icon>
                       </v-btn>
                   </td>
                </tr>
            </tbody>
             <div class="text-center mt-2">
              <v-btn small :disabled="testPrev==true"  @click="changerPage(pageCurrent-1 )">
                <v-icon>
                  mdi-chevron-left
               </v-icon>
             </v-btn> 
             <v-btn v-for="num in countPage" :key="num"
                @click="changerPage(num)"
                class="mx-2"
                color="primary"
                :disabled="num==pageCurrent"
                small
                x-small
              >
             {{num}}
           </v-btn>
              <v-btn small :disabled="testNext==true"  @click="changerPage(pageCurrent+1)">
                 <v-icon>
                    mdi-chevron-right
                 </v-icon>
              </v-btn>
            </div> 
          </template>  
          
        </v-simple-table>
      </v-card>
    </div>
</template>

<script>
import serviceSpec from "@/service/GereSpecialite/gereSpec.js"
export default{
    name:'spec',
    created(){
      this.FetchData();
    },
    data() {
        return {
          spec:[],
          search:"",
          countPage:[],
          per_page:2,
          items:[2,5,10,15],
          pageCurrent:0,
          testNext:true,
          testPrev:true
        }
    },
    methods: {
      FetchData(){
        serviceSpec.chercherSpec(this.pageCurrent,this.search,this.per_page).then((res)=>{
          this.countPage=res.data.count_page;
          this.spec=res.data.spec.content;
          this.pageCurrent=res.data.page;
          this.testNext=res.data.spec.last;
          this.testPrev=res.data.spec.first;
        })
      },
      changerPage(num){
        if(num<0){
          num=0;
        }
        this.pageCurrent=num;
        this.FetchData();
      }
    },
}
</script>