<template>
    <div>
        <v-card class="elevation-12">
            <v-toolbar dark color="#5094df">
               <v-toolbar-title >
                  <div class="d-flex justify-content-center">
                      <p style="text-align:center !important">Add Specialite  </p>
                  </div>
               </v-toolbar-title>
            </v-toolbar>
            <v-card-text>
                    <form @submit.prevent="AddSpecialite()" > 
                        <v-text-field
                            name="Name"
                            v-model="formdata.type"
                            :error-messages="type_error"
                            label="Type"
                            type="text"
                            placeholder="Enter Type"
                      ></v-text-field>

                      <v-text-field
                            name="Name"
                            v-model="formdata.niveau"
                            :error-messages="niveau_error"
                            label="Niveau"
                            type="text"
                            placeholder="Enter Niveau"
                      ></v-text-field>

                        <div class="text-center mt-2 py-2">
                            <v-btn :loading="load" type="submit" class="mx-2 " color="green" style="color:#fff !important">
                                Add
                            </v-btn>
                            <v-btn  @click="close_update()">
                               close
                           </v-btn>
                       </div>
                    </form>
             </v-card-text>
        </v-card>
    </div>
</template>

<script>
import service from "@/service/GereSpecialite/gereSpec.js"
import { required }   from 'vuelidate/lib/validators'
export default{
    created(){
            
    },
    validations:{
        formdata:{
            niveau:{
             required
         },
         type:{
            required
         }
        }
    },
    data(){
        return{
            load:false,
            formdata:{
                niveau:'',
                type:'',
            },
        }
    },
    props:{
        updateSpec:Boolean
    },
    name:'AddSpec',
    methods:{
        close_update(){
          this.$emit('close_update');
        },
        
        AddSpecialite(){
             this.$v.formdata.$touch();
             if (this.$v.formdata.$invalid) {
                this.load = false;
                return;
              }
              this.load = true;
                service.AddSpec({
                        "niveau":this.formdata.type,
                        "type":this.formdata.niveau,
                        "id":""
                    }).then(()=>{
                    this.load = false;
                    this.$emit('close_update');
         }).catch((error)=>{
            this.load = false;
            console.log('error');
         })
      }
    },
    computed:{
          niveau_error(){
              const errors=[];
            if (!this.$v.formdata.niveau.$dirty) return errors;
                  !this.$v.formdata.niveau.required && errors.push('niveau required');
             return errors;
            },
            type_error(){
            const errors=[];
              if (!this.$v.formdata.type.$dirty) return errors;
                !this.$v.formdata.type.required && errors.push('Type required');
                return errors;
            },
    }
}
</script>