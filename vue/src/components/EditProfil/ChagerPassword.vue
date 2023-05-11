<template>
    <div class="justify-center">
       <form  @submit.prevent="ChangerPassword()">
            <div class="mx-5 px-5">
              <div class="col-xl-9 col-lg-6 text-center">
               <div class="row d-flex justify-content-center">
                   <div class="col-lg-12">
                       <v-text-field
                       v-model="old_password"
                       :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                       :type="show ? 'text' : 'password'"
                       @click:append="show = !show"
                       :error-messages="password_error"
                       label="Actuel Password"
                     ></v-text-field>
                   </div>
                   <div class="col-lg-12">
                      <v-text-field
                      v-model="new_password"
                      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                      :type="show1 ? 'text' : 'password'"
                      @click:append="show1 = !show1"
                      :error-messages="new_password_error"
                      label="New Password"
                    ></v-text-field>
                  </div>
                  <br>
                  <div class="col-lg-12">
                      <v-text-field
                      v-model="confirm_password"
                      :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                      :type="show2 ? 'text' : 'password'"
                      @click:append="show2 = !show2"
                      :error-messages="confirm_password_error"
                      label="Confirme New Password"
                    ></v-text-field>
                  </div>
               </div>
              </div>
            </div>
          <v-divider></v-divider>
         <v-card-actions class="justify-center">
           <v-btn class="mx-2" style="color:#fff" type="submit" color="#5094df" :loading="load"
               >
            Update
           </v-btn>
         </v-card-actions>
         </form>
         <v-snackbar
         color="#5094df"
         v-model="snackbar"
       >
         {{ message }}
         <template v-slot:action="{ attrs }">
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
    </div>
</template>

<script>
import serviceEdit from "@/service/editProfil/serviceEdit.js"
import {required,sameAs,minLength,maxLength} from "vuelidate/lib/validators"
export default{
    validations:{
        old_password:{
            required,
            async testPassword(val){
                if(val==""){
                    return true;
                }
                const response=await serviceEdit.CheckPassword(val);
                return response.data.success;
            }
        },
        new_password:{
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
        confirm_password:{
            required,
           sameAs:sameAs('new_password')
        },
    },
data(){
    return{
        show:false,
        show1:false,
        message:"",
        snackbar:false,
        show2:false,
        new_password:"",
        confirm_password:"",
        old_password:"",
        load:false
    }
},
methods:{
    ChangerPassword(){
        this.load=true;
        this.$v.new_password.$touch();
        this.$v.confirm_password.$touch();
        this.$v.old_password.$touch();
        if(this.$v.new_password.$invalid && this.$v.confirm_password.$invalid && this.$v.old_password.$invalid){
            this.load=false;
            return;
        }
        serviceEdit.ChangerPasswordActuel(this.new_password).then((res)=>{
            this.load=false;
            this.message=res.data.message;
            this.snackbar=true;
        }).catch((error)=>{
            this.load=false;
            console.log(error);
        })
    }
},
computed:{
    password_error(){
        const error=[];
             if (!this.$v.old_password.$dirty) return error;
             !this.$v.old_password.required && error.push('Password required');
             !this.$v.old_password.testPassword && error.push('Password Incorect');
             return error;
         },
        new_password_error(){
            const error=[];
             if (!this.$v.new_password.$dirty) return error;
             !this.$v.new_password.required && error.push('Password required');
             !this.$v.new_password.maxLength && error.push('Please enter a password with a maximum of 10 characters');
             !this.$v.new_password.minLength && error.push('Please enter a password with a minimum of 6 characters');
             !this.$v.new_password.containsUppercase && error.push('Password must contain capital letters');
             !this.$v.new_password.containsLowercase && error.push('Password must contain lower case letters');
             !this.$v.new_password.containsNumber && error.push('Password must contain numbers');
             !this.$v.new_password.containsSpecial && error.push('password must contain a special character');
             return error;
        },
        confirm_password_error(){
            const error=[];
             if (!this.$v.confirm_password.$dirty) return error;
             !this.$v.confirm_password.required && error.push('Password required');
             !this.$v.confirm_password.sameAs && error.push('Password should be same as new Password');
             return error;
        },
}
}
</script>