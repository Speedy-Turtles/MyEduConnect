<template>
    <div data-aos="fade-left" class="sign_interface" >
      <v-container class="mt-1 py-5" fluid>
         <v-card class="mt-2 card" elavation="3">
            <v-row >
               <v-col>
                  <v-toolbar class="mb-5" >
                      <v-toolbar-title>
                            <img :src="logo" width="100px" alt="logo_app">
                      </v-toolbar-title>
                      <v-spacer></v-spacer>
                        <v-btn dense >
                         <v-icon color="#4F5BD8">mdi-home</v-icon>  
                        </v-btn>
                   </v-toolbar>
                   <div class="mx-5 px-5 mt-5 py-5" style="margin-top:15% !important">
                    <v-alert type="success" v-if="message!=''">
                            {{ message }}
                    </v-alert>
                      <p class=" font-weight-bold header">Log in</p>
                      <p class="text-justify">Welcome to MyEduConnect please put your login credentials bellow 
                       <br> to start using the app.  </p>
                      <form @submit="Login()" >
                                 <v-text-field
                                     name="Email"
                                     label="E-mail"
                                     append-icon="mdi-account"
                                     type="text"
                                     outlined
                                     dense
                                     placeholder="Enter E-mail"
                                 ></v-text-field>
                             <v-text-field
                                 name="Password"
                                 label="Password"
                                 :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                 :type="show ? 'text' : 'password'"
                                 @click:append="show = !show"
                                 dense
                                 outlined
                                 placeholder="Enter Password"
                             ></v-text-field>
                             <div class="mb-5">
                                  <p  color="#4F5BD8" style="color:#2B3277;font-weight:bold;">Forgot Password ?</p>   
                             </div>
                             <hr color="#2B3277" size="1px" class="mb-3">
                             <div class="float-end">
                                     <v-btn type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                                          Sign In
                                      </v-btn>
                              </div>
                              <br>
                             <div class="text-center mt-5 py-5">
                                <p>
                                   Don't Have An Account ?  <span style="color:#4F5BD8;font-weight:bold;">
                                      <router-Link to="/signup">Sign Up</router-Link>  </span> 
                               </p>
                             </div>
                      </form>
                   </div>
               </v-col>
               <v-col class="text-center hidden-sm-and-down second_part" >
                  <div class="mt-5 py-5" data-aos="fade-up">
                      <img elavation="5"  class="mt-5 py-5 " width="80%"  height="100%"  :src="interfaces[indice_interface].image" alt="">
                  </div>
                  <div class="mb-5">
                      <p style="color:#FFCF59" class="font-weight-bold">{{interfaces[indice_interface].titre}}</p>
                      <p >
                          {{interfaces[indice_interface].description}}
                      </p>
                  </div>
                  <div class="mt-5 py-5">
                      <v-row class="mt-5 py-5">
                          <v-col>
                              <v-btn @click="indice_interface=indice_interface-1" :disabled="indice_interface+1==1">
                                  <v-icon>mdi-chevron-left</v-icon>
                              </v-btn>
                          </v-col>
                          <v-col class="d-flex mx-5" >
                              <p  v-for="( interf,index ) in interfaces" :key="interf.id" >
                                  <v-chip :color="indice_interface==index ? '#FFCF59' :'grey'" @click="indice_interface=index" style="padding:15px;margin:0 10px;"  >
                                  </v-chip>
                              </p>
                          </v-col>
                          <v-col>
                              <v-btn @click="indice_interface=indice_interface+1" :disabled="indice_interface+1==interfaces.length">
                                  <v-icon>mdi-chevron-right</v-icon>
                              </v-btn>
                          </v-col>
                      </v-row>
                  </div>
               </v-col>
            </v-row>
         </v-card>
      </v-container>
    </div>
  </template>
  <script>
  export default{
      name:"signup",
      data(){
          return{
              sex:"",
              show:"password",
              message:"",
              selectRole:"",
              indice_interface:1,
              interfaces:[
                 {id:1,image:require("../../assets/iterfaces/interface_chef.png"),titre:"Interface Chef Departement",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:2,image:require("../../assets/iterfaces/interfaceEtudiant.png"),titre:"Interface Etudiant",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:3,image:require("../../assets/iterfaces/interface_chef.png"),titre:"Interface Ensignat",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:4,image:require("../../assets/iterfaces/interface_chef.png"),titre:"Interface Admin",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:5,image:require("../../assets/iterfaces/interface_chef.png"),titre:"Interface Technicien",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                ],
              role:['ensignat','etudiant',"chefDepartment","Technicien"],
              logo:require("../../assets/Logo_app/logo-no-background.png")
          }
      },
      created(){
        if(this.$route.query.content!="undefined"){
            this.message=this.$route.query.content;
            setTimeout(()=>this.message='',3000);
        }
      },
      methods:{
          Login(){
              
          },
      },
      components:{
          
      }
  }
  
  </script>
  
  <style scoped>
  .sign_interface{
      background-color: #BAC1CA;
      height: 100%;
      width: 100vw;
  }
  .card{
      margin: 25px;
  }
  .header{
      font-size: 25px;
  }
  .second_part{
     background-image: linear-gradient(to bottom,#4F5BD8,#2B3277);
     color:#fff;
  }
  </style>