<template>
  <div data-aos="fade-right" class="signup_interface" >
    <v-container class="mt-1 py-5" fluid>
       <v-card  style="height:100%" class="mt-2 card" elavation="3">
          <v-row >
             <v-col>
                <v-toolbar class="mb-4" >
                    <v-toolbar-title>
                          <img :src="logo" width="100px" alt="logo_app">
                    </v-toolbar-title>
                    <v-spacer></v-spacer>
                      <v-btn dense >
                       <v-icon color="#4F5BD8">mdi-home</v-icon>  
                      </v-btn>
                 </v-toolbar>
                 <div class="mx-5 px-5">
                    <p class=" font-weight-bold header">Create account </p>
                    <p >Get access to exclusive features by creating an account</p>
         <form @submit.prevent="Register()" enctype="multipart/form-data">
                        <div class="row">
                             <div class="col-lg-6">
                                <v-text-field
                                    name="Name"
                                    label="LastName"
                                    :error-messages="lastname_error"
                                    v-model="form.lastname"
                                     append-icon="mdi-rename-outline"
                                    type="text"
                                    dense
                                    outlined
                                    placeholder="Enter LastName"
                                ></v-text-field>
                             </div>
                             <div class="col-lg-6">
                                <v-text-field
                                    name="Name"
                                    :error-messages="firstname_error"
                                    v-model="form.firstname"
                                    label="FirstName"
                                    append-icon="mdi-rename-outline"
                                    type="text"
                                    outlined
                                    dense
                                    placeholder="Enter FirstName"
                                ></v-text-field>
                             </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                               <v-text-field
                                   name="Cin"
                                   label="Cin"
                                   v-model="form.cin"
                                   :error-messages="cin_error"
                                   append-icon="mdi-card-account-details"
                                   type="text"
                                   dense
                                   outlined
                                   placeholder="Enter Cin"
                               ></v-text-field>
                            </div>
                            <div class="col-lg-6">
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
                       </div>

                    <div class="row">
                        <div class="col-lg-6">
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
                        <div class="col-lg-6">
                           <v-text-field
                               name="Numtelf"
                               v-model="form.num_tlf"
                               :error-messages="tlf_error"
                               label="Numero phone"
                               append-icon="mdi-phone"
                               type="text"
                               outlined
                               dense
                               placeholder="Enter numero Phone"
                           ></v-text-field>
                        </div>
                   </div>

                <div class="row">
                    <div class="col-lg-6">
                       <input
                           name="image"
                           type="file"
                           class="form-control"
                           ref="photo"
                           @change="saveImage()"
                       >
                       <br>
                       <small style="color:red" v-if="photo_error!=''">
                         {{ photo_error[0] }}
                       </small>
                    </div>
                    <div class="col-lg-6">
                       <v-text-field
                           name="Birth day"
                           v-model="form.birth_day"
                           :error-messages="birthday_error"
                           label="Birth day"
                           type="date"
                           outlined
                           dense
                           placeholder="Enter Birth day"
                       ></v-text-field>
                    </div>
               </div>

               <div class="row">
                <div class="col-lg-6">
                    <v-radio-group
                    :error-messages="sex_error"
                      v-model="form.sex"
                      row
                     >
                     <template v-slot:label>
                        <div style="font-size: 18px;">Sex</div>
                      </template>
                     <v-radio
                        label="Man"
                        color="primary"
                        value="Man"
                   ></v-radio>
                   <v-radio
                        label="Woman"
                        color="error"
                        value="Woman"
                 ></v-radio>
                  </v-radio-group>
                </div>
                <div class="col-lg-6 mb-5">
                   <v-select
                        label="Role"
                        :items="role"
                        :error-messages="selectRole_error"
                        @change="CheckRole()"
                        v-model="form.selectRole"
                   ></v-select>
                   <small style="color:red" v-if="message_error!=''">
                    {{ message_error }}
                   </small>
                </div>
           </div>
           <hr color="#2B3277" size="1px" class="mb-3">
            <div class="float-end">
                <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                    Create my account
                </v-btn>
            </div>
            <br>
            <div class="text-center mt-5">
                <p>
                    Already have an account  ? <span style="color:#4F5BD8;font-weight:bold;">
                        <router-link to="/signin"> Sign In</router-link></span> 
                </p>
            </div>
    </form>
            <v-dialog
                    v-model="dialog"
                        width="500"
                  >
              <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                  Chose Classe
               </v-card-title>
        <form >
          <div class="mx-5 px-5">
            <v-select
                :items="specialtes"
                v-model="specialite_id"
                item-text="type"
                :error-messages="Specialite_error"
                item-value="id"
                @change="choseClasse()"  
                 label="chose Your Specialite"
            ></v-select>
          </div>

          <div class="mx-5 px-5">
            <v-select
                :disabled="specialite_id=='' ? true : false "
                :items="classes"
                v-model="classe_id"
                :error-messages="classe_error"
                item-text="nom"
                item-value="id"
                label="chose Your Classe"
            ></v-select>
          </div>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
          color="gray"
          text
          @click="dialog = false"
        >
          Close
        </v-btn>
          <v-btn
            color="primary"
            text
            @click="ConfirmClasse()"
          >
            Confirm
          </v-btn>
        </v-card-actions>
      </form>
      </v-card>
    </v-dialog>
                 </div>
             </v-col>
             <v-col class="text-center hidden-sm-and-down second_part" >
                <div class="mt-5 py-5" data-aos="fade-up">
                    <img elavation="5"  class="mt-5 py-5 " width="80%" height="400px"  :src="interfaces[indice_interface].image" alt="">
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

const dateValidator = (date) => {
    const res=new Date().getFullYear() - new Date(date).getFullYear();
    if(res > 19){
        return true;
    }else{
        return false;
    }
};

const dateValidator_max = (date) => {
    const res=new Date().getFullYear() - new Date(date).getFullYear();
    if(res < 70){
        return true;
    }else{
        return false;
    }
};
import service_classe from "@/service/classe/gestionclasse.js"
import service_specilate from "@/service/specialite/gestionspecilate.js"
import service_info from "@/service/UserInfo/userinfo.js";
import {required,minLength,maxLength,numeric,email} from "vuelidate/lib/validators"
import service_user from "@/service/AuthService/Auth.js";
export default{
    name:"signup",
    created(){
        service_specilate.getSpecialte().then((response)=>{
             this.specialtes=response.data.data;
        })
    },
    validations:{
        form:{
            lastname:{
                required,
                minlength:minLength(3),
                maxLength:maxLength(10)
             },
             firstname:{
                required,
                minlength:minLength(3),
                maxLength:maxLength(10)
             },
             cin:{
                required,
                numeric,
                minlength:minLength(8),
                maxLength:maxLength(8)
             },
             num_tlf:{
                required,
                numeric,
                minlength:minLength(8),
                maxLength:maxLength(8)
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
             sex:{
                required,
             },
             photo:{
                typeFile(val){
                    if( val === "" ){
                        return true;
                    };
                    const regex = new RegExp('\.(gif|jpe?g|svg|png)$');
                    return regex.test(val.type);
                }
             },
             birth_day:{
                required,
                dateValidator,
                dateValidator_max
             },
             email:{
                required,
                email,
                async exist(val){
                    if(val==""){
                        return true;
                    }
                    const response=await service_info.TestExistEmail(val);
                    return response.data.success;
                }
             },
            selectRole:{
                required,
             }
        },
        classe_id:{
            required
        },
       specialite_id:{
            required
        }   
    },
    data(){
        return{
            form:{
                lastname:"",
                firstname:"",
                cin:"",
                num_tlf:"",
                password:"",
                sex:"",
                photo:"",
                birth_day:"",
                email:"",
                selectRole:""
            },
            classe_id:"",
            message_error:"",
            specialite_id:"",
            classes:[],
            specialtes:[],
            loading:false,
            dialog:false,
            show:false,
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
    methods:{
        saveImage(){
            this.form.photo=this.$refs.photo.files[0];
            this.$v.form.photo.$touch();
        },
        Register(){
            this.$v.form.$touch();
            if(this.$v.form.$invalid){
                this.loading=false;
                return;
            }
            if(this.form.selectRole=="etudiant"){
                 this.$v.specialite_id.$touch();
                 this.$v.classe_id.$touch();
                 if(this.$v.specialite_id.$invalid && this.$v.classe_id.$invalid){
                     this.message_error="Classe and sepecialte are Required";
                     return;
                 }
            }
             this.loading=true;
              service_user.StoreUser({
                "firstname":this.form.firstname,
                "lastname":this.form.lastname,
                "email":this.form.email,
                "birthday":this.form.birth_day,
                "password":this.form.password,
                "Role_name":this.form.selectRole,
                "num_tlf":this.form.num_tlf,
                "photo":this.form.photo,
                "sex":this.form.sex,
                "cin":this.form.cin,
                "classe_id":this.classe_id}).then((res)=>{
                    this.form.firstname="";
                    this.form.lastname="";
                    this.form.email="";
                    this.form.birth_day="";
                    this.form.password="";
                    this.form.photo="";
                    this.form.num_tlf="";
                    this.form.sex="";
                    this.form.cin="";
                    this.classe_id="";
                    this.specialite_id="";
                    this.loading=false;
                    this.$router.push({name:"signin",query:{content:"Register successfully"}});
                }).catch((error)=>{
                     this.loading=false;
                     console.log(error);
                })
        },
        CheckRole(){
            if(this.form.selectRole==="etudiant"){
                this.dialog=true;
                this.message_error="";
            }
        },
        choseClasse(){
            service_classe.ClassesBySpecialte(this.specialite_id).then((res)=>{
                 this.classes=res.data.data;
            })
        },
        ConfirmClasse(){
            this.$v.specialite_id.$touch();
            this.$v.classe_id.$touch();
            if(this.$v.specialite_id.$invalid &&   this.$v.classe_id.$invalid){
                return;
            }
            this.dialog=false;
        }
    },
    computed:{
        lastname_error(){
            const error=[];
            if(!this.$v.form.lastname.$dirty) return error;
            !this.$v.form.lastname.required && error.push("LastName required");
            !this.$v.form.lastname.maxLength && error.push("Please enter LastName with a maximum of 10 characters");
            !this.$v.form.lastname.minlength && error.push("Please enter LastName with a minimum of 3 characters");
            return error;
        },
        firstname_error(){
            const error=[];
            if(!this.$v.form.firstname.$dirty) return error;
            !this.$v.form.firstname.required && error.push("FirstName required");
            !this.$v.form.firstname.maxLength && error.push("Please enter FirstName with a maximum of 10 characters");
            !this.$v.form.firstname.minlength && error.push("Please enter FirstName with a minimum of 3 characters");
            return error;
        },
        selectRole_error(){
            const error=[];
            if(!this.$v.form.selectRole.$dirty) return error;
            !this.$v.form.selectRole.required && error.push("Role required");
            /*!this.$v.classe_id.required && error.push("classe required for Student");
            !this.$v.specialite_id.required && error.push("Specialite required for Student");*/
            return error;
        },
        cin_error(){
            const error=[];
            if(!this.$v.form.cin.$dirty) return error;
            !this.$v.form.cin.required && error.push("Cin Required");
            !this.$v.form.cin.numeric && error.push("Cin must be integer");
            !this.$v.form.cin.minlength && error.push("Please enter Cin with a minimum of 8 numbers");
            !this.$v.form.cin.maxLength && error.push("Please enter Cin with a maximum of 8 numbers");
            return error;
        },
        tlf_error(){
            const error=[];
            if(!this.$v.form.num_tlf.$dirty) return error;
            !this.$v.form.num_tlf.required && error.push("Num Phone Required");
            !this.$v.form.num_tlf.numeric && error.push("Num Phone must be integer");
            !this.$v.form.num_tlf.minlength && error.push("Please enter Num Phone with a minimum of 8 numbers");
            !this.$v.form.num_tlf.maxLength && error.push("Please enter Num Phone with a maximum of 8 numbers");
            return error;
        },
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
        sex_error(){
            const error=[];
            if(!this.$v.form.sex.$dirty) return error;
            !this.$v.form.sex.required && error.push("Sex Required");
            return error;
        },
        photo_error(){
            const error=[];
            if(!this.$v.form.photo.$dirty) return error;
            !this.$v.form.photo.typeFile && error.push("image must be of type ( jpg, jpeg, png, svg, gif)");
            return error;
        },
        birthday_error(){
            const error=[];
            if(!this.$v.form.birth_day.$dirty) return error;
            !this.$v.form.birth_day.required && error.push("birth day Required");
            !this.$v.form.birth_day.dateValidator && error.push("The birth day field must be a date before");
            !this.$v.form.birth_day.dateValidator_max && error.push("The birth day field must be a date After");
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
        classe_error(){
            const error=[];
            if(!this.$v.classe_id.$dirty) return error;
            !this.$v.classe_id.required && error.push("Classe Required");
            return error;
        },
        Specialite_error(){
            const error=[];
            if(!this.$v.specialite_id.$dirty) return error;
            !this.$v.specialite_id.required && error.push("Specialite Required");
            return error;
        }
    },
    components:{
        
    }
}

</script>

<style scoped>
.signup_interface{
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