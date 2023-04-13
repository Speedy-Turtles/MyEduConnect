<template>
    <v-col cols="md-4">
     <v-card class="mx-auto" max-width="400">
          <v-img :src="'http://localhost:8000'+AcceptedProffesor.Photo" height="250"></v-img>
          <v-card-title>{{AcceptedProffesor.LastName + " " + AcceptedProffesor.FirstName}}</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="6">{{ AcceptedProffesor.email }}</v-col>
              <v-col cols="6">{{ AcceptedProffesor.num_tlf }}</v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-btn color="red" @click="refuseUser(AcceptedProffesor.id)"><v-icon>mdi-delete</v-icon></v-btn>
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
        name:'Acceptedproffesor',
        props:{
          AcceptedProffesor:Object,
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