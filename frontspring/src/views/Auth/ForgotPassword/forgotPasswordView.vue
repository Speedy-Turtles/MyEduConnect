<template>
    <div data-aos="fade-right" class="forgot_interface" >
      <v-container class="mt-1 py-5" fluid>
         <v-card  style="height:100%" class="mt-2 card" elavation="3">
            <v-row >
               <v-col>
                  <v-toolbar class="mb-4">
                      <v-toolbar-title>
                            <img :src="logo" width="100px" alt="logo_app">
                      </v-toolbar-title>
                      <v-spacer></v-spacer>
                        <v-btn dense >
                             <v-icon color="#4F5BD8">mdi-home</v-icon>  
                        </v-btn>
                   </v-toolbar>
                   <div class="mx-5 px-5 mt-5 py-5" style="margin-top:15% !important">
                      <p class=" font-weight-bold header">Password recovery </p>
           <form @submit.prevent="ForgotPassword()" >
                              <div class="col-lg-12">
                                 <v-text-field
                                     name="Email"
                                     label="E-mail"
                                     v-model="email"
                                     :error-messages="email_error"
                                     append-icon="mdi-account"
                                     type="text"
                                     outlined
                                     dense
                                     placeholder="Enter E-mail"
                                 ></v-text-field>
                              </div>
                              
                            <hr color="#2B3277" size="1px" class="mb-3">
                        <div class="float-end">
                            <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                                 Reset My Password
                             </v-btn>
                         </div>
                        <br>
                        <div class="text-center mt-5 py-5">
                         <p>
                            <router-Link to="/login"> Back to login  </router-Link> 
                         </p>
                         </div>
                </form>
             </div>
         </v-col>
         <v-col class="text-center hidden-sm-and-down second_part" >
                  <div class="mt-5 py-5" data-aos="fade-up">
                      <img elavation="5" class="mt-5 py-5 " width="80%" height="300px" :src="interfaces[indice_interface].image" alt="">
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
                              <v-btn fab  @click="indice_interface=indice_interface-1" :disabled="indice_interface+1==1">
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
                              <v-btn fab @click="indice_interface=indice_interface+1" :disabled="indice_interface+1==interfaces.length">
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
import loginService from "@/service/AuthService/Auth.js";
import {required,email} from "vuelidate/lib/validators";
import service_info from "@/service/UserInfo/userInfo.js";
  export default{
      name:"forgotpassword",
      validations:{
               email:{
                  required,
                  email,
                  async exist(val){
                      if(val==""){
                          return true;
                      }
                      const response=await service_info.TestEmail(val);
                      return !response.data;
                  }
               },
      },
      data(){
          return{
              email:"",
              loading:false,
              indice_interface:1,
              interfaces:[
                   {id:1,image:require("../../../assets/iterfaces/interface_chef.png"),titre:"Interface Chef Departement",
                   description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                   {id:2,image:require("../../../assets/iterfaces/interfaceEtudiant.png"),titre:"Interface Etudiant",
                   description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                   {id:3,image:require("../../../assets/iterfaces/interface_chef.png"),titre:"Interface Ensignat",
                   description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                   {id:4,image:require("../../../assets/iterfaces/interface_chef.png"),titre:"Interface Admin",
                   description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                   {id:5,image:require("../../../assets/iterfaces/interface_chef.png"),titre:"Interface Technicien",
                   description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                  ],
              logo:require("../../../assets/Logo_app/logo-no-background.png")
          }
      },
      methods:{
        ForgotPassword(){
            this.$v.email.$touch();
            if(this.$v.email.$invalid){
                return;
            }
            this.loading=true;
            loginService.forgotPassword(this.email).then((res)=>{
                this.loading=false;
                this.$router.push({name:"login",query:{content:res.data}});
            })
        }
      },
      computed:{
          email_error(){
              const error=[];
              if(!this.$v.email.$dirty) return error;
              !this.$v.email.required && error.push("Email Required");
              !this.$v.email.email && error.push("Email invalid");
              !this.$v.email.exist && error.push("Email Not exist");
              return error;
          },
      },
      components:{
          
      }
  }
  
  </script>
  
  <style scoped>
  .forgot_interface{
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