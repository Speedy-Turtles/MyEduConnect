<template>
    <div>
        <template class="mt-5 py-5">
            <v-card >
                <v-tabs
                fixed-tabs
                background-color="#5094df"
                dark
              >
                <v-tab @click="page=0">
                    Update Info Personnel
                </v-tab>
                <v-tab @click="page=1">
                    Update Password
                </v-tab>
                <v-tab @click="page=2">
                    Update Email
                </v-tab>
              </v-tabs>
              <v-card-text v-if="page==0" >
               <form  @submit.prevent="EditUser()" enctype="multipart/form-data">
                 <div class="mx-5 px-5">
                    <div class="row py-3">
                        <div class="col-lg-6">
                            <v-text-field
                            v-model="form.FirstName"
                            :error-messages="firstname_error"
                            label="Name"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                            <v-text-field
                            v-model="form.Lastname"
                            :error-messages="lastname_error"
                            label="Lastname"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                            <v-text-field
                            v-model="form.numero_tlf"
                            :error-messages="tlf_error"
                            label="N°tlf"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                            <v-text-field
                            v-model="form.cin"
                            :error-messages="cin_error"
                            label="Cin"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                             <v-text-field
                                 readonly
                                 v-model="form.email"
                                 type="email"
                                 label="email"
                              ></v-text-field>
                        </div>
                        <div class="col-lg-6 mt-3">
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
                 </div>
               <v-divider></v-divider>
              <v-card-actions class="justify-center py-3">
                <v-btn :loading="loadingEdit" style="color:#fff" class="mx-2" type="submit" color="#5094df">
                     Updated
                </v-btn>
              </v-card-actions>
              </form>
             </v-card-text>
             <v-card-text v-else-if="page==1">
               <changerPassword></changerPassword>
              </v-card-text>
              <v-card-text v-else>
                <ChangerEmail></ChangerEmail>
              </v-card-text>
            </v-card>
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
          </template>
    </div>
</template>

<script>
const maxdate=(date)=>{
    const res=new Date().getFullYear()-new Date(date).getFullYear();
    if(res>19){
        return true;
    }else{
        return false;
    }
}

const mindate=(date)=>{
    const res=new Date().getFullYear()-new Date(date).getFullYear();
    if(res<70){
        return true;
    }else{
        return false;
    }
}
import {required,minLength,maxLength,numeric} from "vuelidate/lib/validators"
import serviceInfor from "@/service/UserInfo/userinfo"
import serviceEdit from "@/service/editProfil/serviceEdit";
import {AuthUser} from "@/store/Store.js";
import changerPassword from "@/components/EditProfil/ChagerPassword.vue"
import ChangerEmail from "@/components/EditProfil/ChangerEmail.vue";
export default{
    setup(){
        const store = AuthUser();
        return {store};
    },
    validations:{
       form:{
        FirstName:{
            required,
            minlength:minLength(3),
            maxLength:maxLength(10)
        },
        Lastname:{
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
        numero_tlf:{
            required,
            numeric,
            minlength:minLength(8),
            maxLength:maxLength(8)
        }, 
        birth_day:{
            required,
            maxdate,
            mindate
        }
       }
    },
    created(){
        serviceInfor.getUserAuthentifie().then((res)=>{
            this.form.FirstName=res.data.data['FirstName'];
            this.form.Lastname=res.data.data['LastName'];
            this.form.numero_tlf=res.data.data['num_tlf'];
            this.form.cin=res.data.data['Cin'];
            this.form.email=res.data.data['email'];
            this.form.birth_day=res.data.data['Birth_day'];
        })
    },
    data() {
        return {
            page:0,
            loadingEdit:false,
            message:"",
            snackbar:false,
            form:{
                birth_day:"",
                FirstName:"",
                Lastname:"",
                numero_tlf:"",
                cin:"",
                email:""
            }
          
        }
    },
    methods:{
        EditUser(){
            this.loadingEdit=true;
            this.$v.form.$touch();
            if(this.$v.form.$invalid){
                this.loadingEdit=false;
                return;
            }
            serviceEdit.EditInfoPersonnel({
                "firstname":this.form.FirstName,
                "lastname":this.form.Lastname,
                "cin":this.form.cin,
                "num_tlf":this.form.numero_tlf,
                "birth_day":this.form.birth_day
            }).then((res)=>{
                serviceInfor.getUserAuthentifie().then((res)=>{
                    this.store.SetUser(res.data.data);
                });
                this.loadingEdit=false;
                this.message=res.data.message;
                this.snackbar=true;
            }).catch((error)=>{
                this.loadingEdit=false;
                console.log(error);
            })
        }
    },
    components:{
        changerPassword,ChangerEmail
    },
    computed:{
        birthday_error(){
            const error=[];
            if(!this.$v.form.birth_day.$dirty) return error;
            !this.$v.form.birth_day.required && error.push("birth day Required");
            !this.$v.form.birth_day.maxdate && error.push("The birth day field must be a date before");
            !this.$v.form.birth_day.mindate && error.push("The birth day field must be a date After");
            return error;
        },
        tlf_error(){
            const error=[];
            if(!this.$v.form.numero_tlf.$dirty) return error;
            !this.$v.form.numero_tlf.required && error.push("Num Phone Required");
            !this.$v.form.numero_tlf.numeric && error.push("Num Phone must be integer");
            !this.$v.form.numero_tlf.minlength && error.push("Please enter Num Phone with a minimum of 8 numbers");
            !this.$v.form.numero_tlf.maxLength && error.push("Please enter Num Phone with a maximum of 8 numbers");
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
        lastname_error(){
            const error=[];
            if(!this.$v.form.Lastname.$dirty) return error;
            !this.$v.form.Lastname.required && error.push("LastName required");
            !this.$v.form.Lastname.maxLength && error.push("Please enter LastName with a maximum of 10 characters");
            !this.$v.form.Lastname.minlength && error.push("Please enter LastName with a minimum of 3 characters");
            return error;
        },
        firstname_error(){
            const error=[];
            if(!this.$v.form.FirstName.$dirty) return error;
            !this.$v.form.FirstName.required && error.push("FirstName required");
            !this.$v.form.FirstName.maxLength && error.push("Please enter FirstName with a maximum of 10 characters");
            !this.$v.form.FirstName.minlength && error.push("Please enter FirstName with a minimum of 3 characters");
            return error;
        },
    }
}
</script>