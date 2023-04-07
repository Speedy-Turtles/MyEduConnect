<template>
    <v-col cols="md-4">
        <v-card class="mx-auto" max-width="400" v-show="!loader">
          <v-img :src="'http://localhost:8000'+PendingRequest.Photo" height="250"></v-img>
          <v-card-title>{{PendingRequest.LastName + " " + PendingRequest.FirstName}}</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="6">{{ PendingRequest.classe.nom }}</v-col>
              <v-col cols="6">{{ PendingRequest.email }}</v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-btn color="success" @click="acceptUser(PendingRequest.id)"><v-icon>mdi-check</v-icon></v-btn>
            <v-btn color="red" @click="refuseUser(PendingRequest.id)"><v-icon>mdi-cancel</v-icon></v-btn>
          </v-card-actions>
        </v-card>
    </v-col>
</template>
<script>
  export default{
    name:'StudentRaquest',
      data(){
        return{
          loader:false,
      }
    },
    props:{
      PendingRequest:Object
    },
    methods:{
      acceptUser(id) {
            console.log(id);
            this.$emit('accept-user', id);
        },
        refuseUser(id) {
            this.$emit('refuse-user', id);
        },
    }
  }
</script>
<style scoped>

</style>