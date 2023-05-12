<template>
    <div data-aos="fade-right" class="changer_interface" >
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
                      <p class=" font-weight-bold header">Change Password </p>
                        <form @submit.prevent="ChangerPassword()" >
                              <div class="col-lg-12">
                                 <v-text-field
                                     name="Email"
                                     label="E-mail"
                                     v-model="form.email"
                                     :error-messages="email_error"
                                     append-icon="mdi-account"
                                     type="text"
                                     outlined
                                     dense
                                     placeholder="Enter E-mail"
                                 ></v-text-field>
                              </div>
                              <div class="col-lg-12">
                                <v-text-field
                                    name="Password"
                                    label="Password"
                                    v-model="form.password"
                                    :error-messages="password_error"
                                    :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                    :type="show ? 'text' : 'password'"
                                    @click:append="show = !show"
                                    dense
                                    outlined
                                    placeholder="Enter Password"
                            ></v-text-field>
                             </div>
                             <div class="col-lg-12">
                                <v-text-field
                                    name="ConfirmPassword"
                                    label="Confirm Password"
                                    v-model="form.confirmPassword"
                                    :error-messages="confirm_error"
                                    :append-icon="showconfirm ? 'mdi-eye' : 'mdi-eye-off'"
                                    :type="showconfirm ? 'text' : 'password'"
                                    @click:append="showconfirm = !showconfirm"
                                    dense
                                    outlined
                                    placeholder="Enter Password"
                            ></v-text-field>
                             </div>
                             <div style="display:flex;justify-content: center;" >
                                <div class="col-lg-6">
                                    <v-otp-input
                                        length="8"
                                        v-model="form.code"
                                    ></v-otp-input>
                                    <small style="color:red" >
                                        {{ tokenerror[0] }}
                                   </small>
                                </div>
                             </div>
                            <hr color="#2B3277" size="1px" class="mb-3">
                        <div class="float-end">
                            <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                                 Change Password
                             </v-btn>
                         </div>
                        <br>
                        <div class="text-center mt-5 py-5">
                         <p>
                            <router-Link to="/signin"> Back to login  </router-Link> 
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
         <v-snackbar
         v-model="snackbar"
       >
         {{ message_error }}
   
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
      </v-container>
    </div>
  </template>
  <script>
import Servicelogin from "@/service/AuthService/Auth";
import {required,email,sameAs,minLength,maxLength,numeric} from "vuelidate/lib/validators";
import service_info from "@/service/UserInfo/userInfo.js";
  export default{
      name:"forgotpassword",
      validations:{
        form:{
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
            password:{
              required,
              minLength:minLength(6),
              maxLength:maxLength(10),
              containsUppercase: function(value) {
                  return /[A-Z]/.test(value);
              },
              containsLowercase: function(value) {
                  return /[a-z]/.test(value);
              },
              containsNumber: function(value) {
                  return /[0-9]/.test(value);
              },
              containsSpecial: function(value) {
                  return /[#?!@$%^&*-]/.test(value);
              }
             },
             confirmPassword:{
                required,
                same:sameAs('password')
            },
            code:{
                required,
                async existCode(val){
                    if(val==""){
                        return true;
                    }
                    const res=await service_info.TestExistcode(val);
                    return !res.data;
                }
            }
        }
      },
      data(){
          return{
            form:{
                email:"",
                password:"",
                confirmPassword:"",
                code:""
            },
              loading:false,
              show:false,
              snackbar:false,
              message_error:"",
              showconfirm:false,
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
        ChangerPassword(){
            this.$v.form.$touch();
            if(this.$v.form.$invalid){
                return;
            }
            this.loading=true;
            Servicelogin.changerPassword({
                email:this.form.email,
                password:this.form.password,
                token:this.form.code})
            .then((res)=>{
                this.loading=false;
                this.$router.push({name:"login",query:{content:res.data}});
            }).catch((error)=>{
                this.loading=false;
                this.snackbar=true;
                this.message_error=error.response.data;
            })
        }
      },
      computed:{
        password_error(){
            const error=[];
             if (!this.$v.form.password.$dirty) return error;
             !this.$v.form.password.required && error.push('Password required');
             !this.$v.form.password.maxLength && error.push('Please enter a password with a maximum of 10 characters');
             !this.$v.form.password.minLength && error.push('Please enter a password with a minimum of 6 characters');
             !this.$v.form.password.containsUppercase && error.push('Password must contain capital letters');
             !this.$v.form.password.containsLowercase && error.push('Password must contain lower case letters');
             !this.$v.form.password.containsNumber && error.push('Password must contain numbers');
             !this.$v.form.password.containsSpecial && error.push('password must contain a special character');
             return error;
        },
          email_error(){
            const error=[];
            if(!this.$v.form.email.$dirty) return error;
            !this.$v.form.email.required && error.push("Email Required");
            !this.$v.form.email.email && error.push("Email invalid");
            !this.$v.form.email.exist && error.push("Email Already used");
            return error;
        },
        confirm_error(){
            const error=[];
            if(!this.$v.form.confirmPassword.$dirty) return error;
            !this.$v.form.confirmPassword.same && error.push("Confirm password should be like Password");
            !this.$v.form.confirmPassword.required && error.push("Confirm Password Required");
            return error;
        },
        tokenerror(){
            const error=[];
            if(!this.$v.form.code.$dirty) return error;
            !this.$v.form.code.required && error.push("Code Required");
            !this.$v.form.code.existCode && error.push("Token not found");
            return error;
        }
      },
      components:{
          
      }
  }
  
  </script>
  
  <style scoped>
  .changer_interface{
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