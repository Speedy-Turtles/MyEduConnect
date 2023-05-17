<template>
    <div class="text-center py-5 mt-5">
        <v-container>
            <div class="row">
                <div class="col-lg-4">
                        <v-card style="padding: 15px;" class="edit-profile ">
                            <div class="mb-5">
                            <span class="text">
                                {{store.user['firstName'].charAt(0).toUpperCase() +""+store.user['firstName'].substring(1,(store.user['firstName']).length)}}
                            </span>
                            <span class="mb-3 text">
                                {{" "+store.user['lastName'].charAt(0).toUpperCase() +""+store.user['lastName'].substring(1,(store.user['lastName']).length)}}
                            </span>
                        </div>
                            <div class="mb-5">
                           <v-avatar size="120" >
                                <img :src="store.user['photo']" alt="">
                            </v-avatar>
                        </div>
                        <v-text-field
                        name="file"
                        id="file"
                        :error-messages="photo_error"
                        label="Your Photo"
                         @change="saveImage()"
                         type="file"
                        ></v-text-field>
                                    
                          <v-btn type="submit" style="color:#fff" class="mt-5" color="#5094df"  :loading="loadimage" @click="UploadImage()">Upload</v-btn>
                             <p class="py-3" >
                                Member Since: {{ store.user['created_at'].substring(0,10) }}
                             </p>
                         </v-card>
                </div>
                <div class="col-lg-8">
                    <v-card >
                        <editInfoPersonnel></editInfoPersonnel>
                    </v-card>
                 </div>
            </div>
         </v-container>
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
import {required} from "vuelidate/lib/validators"
import {AuthUser} from "@/store/AuthStore.js";
import serviceEdit from "@/service/EditProfil/EditProfile.js"
import seviceInfo from "@/service/UserInfo/userInfo.js"
import editInfoPersonnel from "@/components/EditProfil/editInfoPersonnel.vue"
export default{
    setup(){
        const store = AuthUser();
        return {store};
    },
    validations:{
         photo:{
               required,
               typeFile(val){
                     const tab_ext_dispo=['jpg','gif','png','svg','jpeg'];
                     const extention=val.split(';')[0].split('/')[1];
                     return tab_ext_dispo.find((v)=>v==extention) ? true : false ;
                }
            }
    },
    name:"modifier",
    data(){
        return{
            photo:"",
            loadimage:false,
            snackbar:false,
            message:""
        }
    },
    methods: {
        saveImage(){
            const file = document.querySelector("#file").files[0];
            const reader = new FileReader();
            reader.onloadend = () => {
                this.photo= reader.result;
           };
           reader.readAsDataURL(file);
        },
        UploadImage(){
            this.loadimage=true;
            this.$v.photo.$touch();
            if(this.$v.photo.$invalid){
                this.loadimage=false;
                return;
            }
            serviceEdit.uploadPhoto({
                 'photo':this.photo
                }).then((res)=>{
                seviceInfo.getUserAuthentifie().then((res)=>{
                    this.store.SetUser(res.data);
                })
                this.loadimage=false;
                this.message=res.data;
                this.snackbar=true;
            }).catch((error)=>{
                this.loadimage=false;
                console.log(error);
            })
        }
    },
    computed:{
        photo_error(){
            const error=[];
            if(!this.$v.photo.$dirty) return error;
            !this.$v.photo.required && error.push("image Required");
            !this.$v.photo.typeFile && error.push("image must be of type ( jpg, jpeg, png, svg, gif)");
            return error;
        },
    },
    components:{
       editInfoPersonnel
    }
}
</script>

<style scoped>
.text{
    font-family: "Times New Roman", Times, serif;
    letter-spacing: 2px;
    word-spacing: 2px;
    color: #1976D2;
    font-size: 30px;
    display: inline-block;
}

</style>