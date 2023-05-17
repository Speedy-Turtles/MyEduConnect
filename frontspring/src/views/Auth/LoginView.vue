<template>
    <div data-aos="fade-left" class="sign_interface" >
      <v-container class="mt-1 py-5" fluid>
         <v-card style="height:100%" class="mt-2 card" elavation="3">
            <v-row >
               <v-col>
                  <v-toolbar class="mb-5" >
                      <v-toolbar-title>
                            <img :src="logo" width="100px" alt="logo_app">
                      </v-toolbar-title>
                      <v-spacer></v-spacer>
                      <router-link to="/">
                        <v-btn dense >
                           <v-icon color="#4F5BD8">mdi-home</v-icon>  
                        </v-btn>
                      </router-link>
                   </v-toolbar>
                   <div class="mx-5 px-5 mt-5 py-5" style="margin-top:15% !important">
                    <div  v-if="message!=''">
                        <v-alert type="success" >
                            {{ message }}
                    </v-alert>
                    </div>
                 
                      <p class=" font-weight-bold header">Log in</p>
                      <p class="text-justify">Welcome to MyEduConnect please put your login credentials bellow 
                       <br> to start using the app.  </p>
                      <form @submit.prevent="Login()" >
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
                             <v-text-field
                                 name="Password"
                                 label="Password"
                                 :error-messages="password_error"
                                 v-model="password"
                                 :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                 :type="show ? 'text' : 'password'"
                                 @click:append="show = !show"
                                 dense
                                 outlined
                                 placeholder="Enter Password"
                             ></v-text-field>
                             <div class="mb-5">
                                  <p  color="#4F5BD8" style="color:#2B3277;font-weight:bold;">
                                    <router-link to="/forgotPasswordView">Forgot Password ?</router-link>  </p>   
                             </div>
                             <hr color="#2B3277" size="1px" class="mb-3">
                             <div class="float-end">
                                     <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
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
                      <img elavation="5"  class="mt-5 py-5 " width="80%" height="300px"  :src="interfaces[indice_interface].image" alt="">
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
         <v-snackbar
         color="error"
         v-model="snackbar"
       >
         {{ message_error }}
         <template v-slot:action="{ attrs }">
            <v-btn  color="#fff" text v-if="btn_renvoyer" @click="dialog_renvoyer=true">
                Renvoyer
            </v-btn>
           <v-btn
             color="#000"
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
  import {AuthUser} from "@/store/AuthStore.js";
  import authService from "@/service/AuthService/Auth.js";
  import {required,email} from "vuelidate/lib/validators"
  import serviceEdit from "@/service/EditProfil/EditProfile.js"
  export default{
      name:"login",
      validations:{
        email:{
            required,
            email,
        },
        password:{
            required
        }
      },
      setup(){
        const store=AuthUser();
        return{store}
      },
      data(){
          return{
              email:"",
              password:"",
              dialog_renvoyer:false,
              show:false,
              btn_renvoyer:false,
              snackbar:false,
              message_error:'',
              email_renvoyer:"",
              message:"",
              loading:false,
              indice_interface:1,
              interfaces:[
                 {id:1,image:require("../../assets/iterfaces/interface_chef.png"),titre:"Interface Chef Departement",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:2,image:require("../../assets/iterfaces/interfaceEtudiant.png"),titre:"Interface Etudiant",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:3,image:require("../../assets/iterfaces/interface_ens.png"),titre:"Interface Ensignat",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:4,image:require("../../assets/iterfaces/interface_admin.png"),titre:"Interface Admin",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                 {id:5,image:require("../../assets/iterfaces/interface_chef.png"),titre:"Interface Technicien",
                 description:"Lorem ipsum dolor sit, amet consectetur adipisicing elit. Non neque aliquam eius!"},
                ],
           
              logo:require("../../assets/Logo_app/logo-no-background.png")
          }
      },
      created(){
        if(this.$route.query.content){
            this.message=this.$route.query.content;
            setTimeout(()=>this.message="",3000);
        }
        if(this.$route.query.email){
            this.VerifyEmail(this.$route.query.email);
        }
        if(this.$route.query.email && this.$route.query.email_new ){
            this.store.logout();
            this.updatedEmail(this.$route.query.email,this.$route.query.email_new);
          }
      },
      methods:{
          Login(){
            this.$v.email.$touch();
            this.$v.password.$touch();
            if(this.$v.email.$invalid && this.$v.password.$invalid){
                return;
            }
                this.loading=true;
                authService.login(this.email,this.password).then((res)=>{
                this.loading=false;
               if(this.store.Isetudiant==true){
                   this.$router.push({name:"homeEtudiant"});
                }else if(this.store.IsAdmin==true){
                   this.$router.push({name:"dashboradAdminView"});
                }else if(this.store.Isens==true){
                    this.$router.push({name:"homeEtudiant"});
                }
              }).catch((error)=>{
                this.loading=false;
                this.message_error=error.response.data;
                this.snackbar=true;
              })
          },
          VerifyEmail(email){
          authService.VerifyEmail(email).then((res)=>{
                if(res.status==200){
                    this.message=res.data;
                }else{
                    this.message_error=res.data;
                    this.snackbar=true;
                }
                 this.$router.replace({'query':null});
            })
          },
          updatedEmail(old_email,new_email){
            serviceEdit.updateEmail(
              {
                 "email_old":old_email,
                 "email_new":new_email
               }).then((res)=>{
                 this.message=res.data;
                 this.$router.replace({'query':null});
               }).catch((error)=>{
                 console.log(error);
                 this.$router.replace({'query':null});
               })
          }
      },
      components:{
          
      },
      computed:{
        email_error(){
            const error=[];
            if(!this.$v.email.$dirty) return error;
            !this.$v.email.required && error.push("Email Required");
            !this.$v.email.email && error.push("Email invalid");
            return error;
        },
        password_error(){
            const error=[];
             if (!this.$v.password.$dirty) return error;
             !this.$v.password.required && error.push('Password Required');
             return error;
        },
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