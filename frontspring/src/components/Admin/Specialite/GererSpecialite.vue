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
                <th  class="text-left ">
                  Id
                </th>
                <th  class="text-left ">
                  Nom
                </th>
                <th  class="text-left ">
                  Niveau
                </th>
                <th class="text-left">
                  Operation
                </th>
              </tr>
            </thead>
           
            <tbody >
                
            </tbody>
            <!-- <div class="text-center mt-2">
              <v-btn small :disabled="pagination.prevpage==null"  @click="changerPage(pagination.curentpage-1)">
                <v-icon>
                  mdi-chevron-left
               </v-icon>
             </v-btn>
             <v-btn v-for="num in (Math.ceil(pagination.per_page ? pagination.total/pagination.per_page : 1))" :key="num"
               @click="changerPage(num)"
                class="mx-2"
                color="primary"
                :disabled="num==pagination.curentpage"
                small
                x-small
              >
             {{num}}
           </v-btn>
              <v-btn small :disabled="pagination.nextpage==null"  @click="changerPage(pagination.curentpage+1)">
                 <v-icon>
                    mdi-chevron-right
                 </v-icon>
              </v-btn>
            </div> -->
          </template>  
          
        </v-simple-table>
      </v-card>
    </div>
</template>

<script>
export default{
    name:'spec',
    data() {
        return {
            
        }
    },
    methods: {
        
    },
}
</script>