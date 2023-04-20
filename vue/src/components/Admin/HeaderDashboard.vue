<template>
    <div  class="ma-5 mb-5">
        <v-container >
            <v-row>
              <h2>
                <span style="color: #5094df">{{
                  (store_view.getView).toUpperCase().substring(0, (store_view.getView).length - 3)
                }}</span
                >{{
                  (store_view.getView).toUpperCase().substring(
                    (store_view.getView).length,
                    (store_view.getView).length - 3
                  )
                }}
              </h2>
              <v-spacer></v-spacer>
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                 <v-btn
                 plain
                 v-bind="attrs"
                 v-on="on"
                 > 
                   <v-badge content="4" color="green">
                    <v-icon >mdi-bell</v-icon>
                  </v-badge>
                 </v-btn>
                </template>
                <v-list>
                <v-list-item
                >
                    <v-list-item-title>Notifications</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                 <v-btn
                 plain
                 v-bind="attrs"
                 v-on="on"
                 >
                    <v-avatar v-if="(store.user['Photo']).indexOf('storage')==-1" >
                         <span>{{store.user['Photo']}}</span>
                    </v-avatar>
                    <v-avatar v-else >
                       <img :src="'http://127.0.0.1:8000'+store.user['Photo']" alt="">
                  </v-avatar>
                 </v-btn>
                </template>
                <v-list>
                <v-list-item
                >
                    <v-list-item-title>{{store.user['FirstName']}}</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
              </v-row>
        </v-container>
    </div>
</template>

<script>
import {AuthUser} from "@/store/Store.js";
import {CurentView} from "@/store/StoreView.js";
export default {
  setup(){
    const store = AuthUser();
     const store_view = CurentView();
     return {store,store_view};
  },
};

</script>

<style scoped>

</style>