<template>
    <div> 
        <div class="container text-center">
            <v-card class="elevation-12">
                <v-toolbar dark color="#5094df">
                   <v-toolbar-title >
                      <div class="d-flex justify-content-center">
                          <p style="text-align:center !important">Add Suggestion</p>
                      </div>
                   </v-toolbar-title>
                </v-toolbar>
                <v-card-text>
                        <form @submit.prevent="AddSugg()" > 
                            <v-text-field
                            name="Name"
                            v-model="formdata.message"
                            :error-messages="message_error"
                            label="Message"
                            type="text"
                            placeholder="Enter Message"
                      ></v-text-field>
                      <v-select
                         v-model="formdata.type"
                        :items="type"
                        :error-messages="type_error"
                        label="Chose Type "
                    ></v-select>
                            <div class="text-center mt-2 py-2">
                                <v-btn :loading="load" type="submit" class="mx-2 " color="blue" style="color:#fff !important">
                                    Add
                                </v-btn>
                           </div>
                        </form>
                 </v-card-text>
            </v-card>
        </div>
        <v-snackbar
        v-model="snackbar_add"
      >
         {{ message }}
        <template v-slot:action="{ attrs }">
          <v-btn
            color="indigo"
            text
            v-bind="attrs"
            @click="snackbar_add = false"
          >
            Fermer
          </v-btn>
        </template>
      </v-snackbar>
    </div>
</template>

<script>
import serviceSug from "@/service/GererSuggestion/suggestion.js"
import { required }   from 'vuelidate/lib/validators'
export default{
    validations:{
        formdata:{
            message:{
             required
         },
         type:{
            required
         }
        }
    },
    name:'sugg',
    data(){
        return{
            snackbar_add:false,
        message:'',
            load:false,
            type:['Social','educative','Personel'],
            formdata:{
            message:'',
            type:''
        },
        }
    },
    methods:{
        AddSugg(){
             this.$v.formdata.$touch();
             if (this.$v.formdata.$invalid) {
                this.load = false;
                return;
              }
              this.load = true;
              serviceSug.addSuggestion({
                'type':this.formdata.type,
                'message':this.formdata.message
              }).then((res)=>{
                this.snackbar_add=true;
                this.load = false;
                this.formdata.type="";
                this.formdata.message="";
                this.message="Suggestion added with success";
              }).catch((error)=>{
                console.log(error);
                this.load = false;
              })
      },
    },
    computed:{
          message_error(){
              const errors=[];
            if (!this.$v.formdata.message.$dirty) return errors;
                  !this.$v.formdata.message.required && errors.push('Message required');
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