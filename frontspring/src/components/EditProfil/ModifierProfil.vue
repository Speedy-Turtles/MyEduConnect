<template>
    <div class="text-center py-5 mt-5">
        <v-container>
            <div class="row">
                <div class="col-lg-4">
                        <v-card style="padding: 15px;" class="edit-profile ">
                            <div class="mb-5">
                            <span class="text">
                                {{store.user['FirstName'].charAt(0).toUpperCase() +""+store.user['FirstName'].substring(1,(store.user['FirstName']).length)}}
                            </span>
                            <span class="mb-3 text">
                                {{" "+store.user['LastName'].charAt(0).toUpperCase() +""+store.user['LastName'].substring(1,(store.user['LastName']).length)}}
                            </span>
                        </div>
                            <div class="mb-5">
                            <v-avatar size="120" v-if="(store.user['Photo']).indexOf('storage')==-1" >
                                 <span>{{store.user['Photo']}}</span>
                           </v-avatar>
                           <v-avatar size="120" v-else >
                                <img :src="'http://127.0.0.1:8000'+store.user['Photo']" alt="">
                            </v-avatar>
                        </div>
                             <form class="mb-4 py-4"  @submit.prevent="UploadImage()" enctype="multipart/form-data" >
                                    <input
                                      name="image"
                                      type="file"
                                      class="form-control"
                                      ref="photo"
                                       @change="saveImage()"
                                     >
                                     <div class="mb-2">
                                       <small style="color:red" v-if="photo_error!=''">
                                           {{ photo_error[0] }}
                                         </small>
                                    </div>
                                    <v-btn type="submit" style="color:#fff" class="mt-5" color="#5094df"  :loading="loadimage">Upload</v-btn>
                             </form>
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
import {AuthUser} from "@/store/Store.js";
import serviceEdit from "@/service/editProfil/serviceEdit.js"
import seviceInfo from "@/service/UserInfo/userinfo.js"
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
                    if( val === "" ){
                        return true;
                    };
                    const regex = new RegExp('\.(gif|jpe?g|svg|png)$');
                    return regex.test(val.type);
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
            this.photo=this.$refs.photo.files[0];
            this.$v.photo.$touch();
        },
        UploadImage(){
            this.loadimage=true;
            this.$v.photo.$touch();
            if(this.$v.photo.$invalid){
                this.loadimage=false;
                return;
            }
            serviceEdit.uploadPhoto({"photo":this.photo}).then((res)=>{
                seviceInfo.getUserAuthentifie().then((res)=>{
                    this.store.SetUser(res.data.data);
                })
                this.loadimage=false;
                this.message=res.data.message;
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