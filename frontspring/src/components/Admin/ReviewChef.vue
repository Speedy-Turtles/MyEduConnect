<template>
    <div>
      <h2 class="text-center">Faculty Tree</h2>
      <v-container fluid>
        <v-row>
          <v-col align="center" justify="center">
            <v-skeleton-loader v-if="loader"  
                        max-width="180"
                        type="card"
                    ></v-skeleton-loader>
            <div class="director-box" @drop="onDrop" @dragover.prevent style="width: 600px"  :class="{ 'drag-over': isDragOver }"  @dragenter="dragEnter" v-if="!loader" 
            @dragleave="dragLeave">
              <h3>Chef Department</h3>
              <v-btn icon class="float-right" color="red" v-if="director" @click="dialog=true">
                <v-icon>mdi-delete</v-icon>
              </v-btn>
              <div v-if="director" class="director-card">
                <v-avatar size="100">
                  <img :src="director.photo" class="director-image" />
                </v-avatar>
                <div class="director-details">
                  <p class="director-name">{{ director.firstName + " " + director.lastName }}</p>
                  <p class="director-position">{{  director.role.role_name }}</p>
                </div>
              </div>
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col v-for="prof in proffesors" :key="prof.id">
            <div class="employee-card" :draggable="false" @dragstart="onDragStart(prof)">
              <v-avatar size="100">
                <img :src="prof.photo" />
              </v-avatar>
              <div class="employee-details">
                <p>{{ prof.firstName + " " + prof.lastName }}</p>
                <p>{{  prof.role.role_name }}</p>
              </div>
            </div>
          </v-col>
        </v-row>
      </v-container>
      <v-snackbar
      v-model="snackbar"
      color="green"
    >
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn
          color="pink"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
    <v-dialog
      v-model="dialog"
      max-width="290"
    >
      <v-card>
        <v-card-title>do you really want to delete the chef department</v-card-title>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="green darken-1"
            text
            @click="dialog = false"
          >
            Disagree
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="deleteChef()" :loading="loader_delete"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    </div>
  </template>
  
  <script>
  import gererChef from '@/service/Admin/gererChef';
import draggable from 'vuedraggable';
  export default {
    created(){
        this.getAllEnseignats();
        this.getChef();
    },
    data() {
      return {
        director: null,
        isDragOver: false,
        proffesors:[],
        snackbar:false,
        text:'',
        loader_delete:false,
        dialog:false,
        loader:false
      };
    },
    methods: {
      changeChef(){
        this.loader=true;
        gererChef.changeChef(this.director.id).then(response=>{
            this.loader=false;
            this.snackbar=true;
            this.text="chef dep changed succesfully";
        });
      },
      onDragStart(employee) {
        event.dataTransfer.setData('text/plain', JSON.stringify(employee));
      },
      onDrop() {
        event.preventDefault();
        const employee = JSON.parse(event.dataTransfer.getData('text/plain'));
        this.director = employee;
        this.isDragOver = false;
        this.changeChef();
      },
      deleteDirector() {
        this.director = null;
      },
      dragEnter() {
      this.isDragOver = true;
    },
    dragLeave() {
      this.isDragOver = false;
    },
    getAllEnseignats(){
        gererChef.getProffesors().then(response=>{
            this.proffesors=response.data;
            console.log(this.proffesors);
        })
    },
    getChef(){
      gererChef.getChef().then(response=>{
        console.log(response);
          this.director=response.data;
      })
    },
    deleteChef(){
      this.loader_delete=true;
      gererChef.deleteChef(this.director.id).then(response=>{
        this.snackbar=true;
        this.text="chef department deleted succesfully";
        this.loader_delete=false;
        this.dialog=false;
        this.getChef();
      })
    },
    
    },  
    computed:{
      drag(){
          return this.director==null ? true : false;
      }
    }
  };
  </script>
  
  <style>
  .director-box {
    border: 2px dashed #1976d2; /* Blue color */
    padding: 20px;
    text-align: center;
    min-height: 200px;
    background-color: #e3f2fd; /* Light blue background */
  }
  
  .director-card {
    margin-top: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    background-color: #bbdefb; /* Light blue card background */
    border-radius: 8px;
    padding: 20px;
  }
  
  .director-image {
    width: 100px;
    height: 100px;
    border-radius: 50%;
  }
  
  .director-details {
    margin-top: 10px;
    text-align: center;
  }
  
  .director-name {
    font-size: 20px;
    font-weight: bold;
  }
  
  .director-position {
    font-size: 16px;
    color: #555;
  }
  
  .employee-card {
    margin-bottom: 20px;
    text-align: center;
    cursor: move;
    background-color: #fff; /* White card background */
    border: 1px solid #e0e0e0; /* Light gray border */
    border-radius: 8px;
    padding: 10px;
  }
  
  .employee-details {
    margin-top: 10px;
  }
  .drag-over {
  background-color: #cfd8dc; /* Light gray background when dragged over */
    }
  </style>
  