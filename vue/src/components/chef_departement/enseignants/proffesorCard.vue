<template>
    <v-col cols="md-4">
     <v-card class="mx-auto" max-width="400" v-show="!loader">
      <v-img :src="'http://localhost:8000'+pendingRequest.Photo" height="250"></v-img>
          <v-card-title>{{pendingRequest.LastName + " " + pendingRequest.FirstName}}</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="6">{{ pendingRequest.email }}</v-col>
              <v-col cols="6">{{ pendingRequest.num_tlf }}</v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-btn color="success" @click="acceptUser(pendingRequest.id)" :loading="loaderaccept" :disabled="loaderaccept"><v-icon>mdi-check</v-icon></v-btn>
            <v-btn color="red" @click="refuseUser(pendingRequest.id)"><v-icon>mdi-cancel</v-icon></v-btn>
          </v-card-actions>
        </v-card>
        <v-snackbar
      v-model="snackbar"
      :timeout="timeout"
    >
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn
          color="blue"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
    </v-col>
</template>
<script>

    export default{
        name:'proffesorCard',
        props:{
          pendingRequest:Array,
          loader:Boolean
        },
        data(){
          return{
            snackbar: false,
      text: '',
      timeout: 2000,
          }
        },
        methods:{
          acceptUser(id){
            console.log(id);
            this.$emit('accept-user',id);
          },
          refuseUser(id){
            this.$emit('refuse-user',id);
          }
        }
    }
</script>
<style scoped>
.v-card__title {
    font-size: 1.5em;
    font-weight: bold;
  }
</style>