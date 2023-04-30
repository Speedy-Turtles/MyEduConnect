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
                            v-model="FirstName"
                            label="Name"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                            <v-text-field
                            v-model="Lastname"
                            label="Lastname"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                            <v-text-field
                            v-model="numero_tlf"
                            label="N°tlf"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                            <v-text-field
                            v-model="cin"
                            label="Cin"
                          ></v-text-field>
                        </div>
                        <div class="col-lg-6">
                             <v-text-field
                                 readonly
                                 v-model="email"
                                 type="email"
                                 label="email"
                              ></v-text-field>
                        </div>
                        <div class="col-lg-6 mt-3">
                            <v-text-field
                                name="Birth day"
                                v-model="birth_day"
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
                <v-btn :loading="load" style="color:#fff" class="mx-2" type="submit" color="#5094df">
                     Updated
                </v-btn>
              </v-card-actions>
              </form>
             </v-card-text>

             <v-card-text v-else-if="page==1">
                page2

                <!-- <form  @submit.prevent="ChangerPassword()" >
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
                             label="Ancien Mot de passe"
                           ></v-text-field>
                         </div>
                         <div class="col-lg-12">
                            <v-text-field
                            v-model="new_password"
                            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                            :type="show1 ? 'text' : 'password'"
                            @click:append="show1 = !show1"
                            :error-messages="new_password_error"
                            label="Nouvelle Mot de passe"
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
                            label="Confirme Mot de passe"
                          ></v-text-field>
                        </div>
                     </div>
                    </div>
                  </div>
                <v-divider></v-divider>
               <v-card-actions class="justify-end">
                 <v-btn class="mx-2" type="submit" color="green" :loading="load"
                     >
                  Changer
                 </v-btn>
                 <router-link  v-if="store.isAdmin!=1" style="text-decoration:none !important" to="/">
                 <v-btn  type="submit" color="gray"
                      >
                     Fermer
                 </v-btn>
              </router-link>
               </v-card-actions>
               </form> -->


              </v-card-text>

              <v-card-text v-else>
                page3


                <!-- <form  @submit.prevent="ChangerEmail()" >
                  <div class="mx-5 px-5">
                     <div class="row col-lg-6">
                         <div class="col-lg-12">
                             <v-text-field
                               readonly
                               v-model="email"
                               label="email Current"
                           ></v-text-field>
                         </div>
                         <div class="col-lg-12">
                          <v-text-field
                            v-model="email_new"
                            :error-messages="email_new_error"
                            label="Nouvelle Email"
                        ></v-text-field>
                      </div>
                     </div>
                  </div>
                <v-divider></v-divider>
               <v-card-actions class="justify-end">
                 <v-btn class="mx-2" type="submit" color="green"
                 :loading="load"
                     >
                  Changer
                 </v-btn>
                 <router-link  v-if="store.isAdmin!=1" style="text-decoration:none !important" to="/">
                 <v-btn  type="submit" color="gray"
                      >
                     Fermer
                 </v-btn>
              </router-link>
               </v-card-actions>
               </form> -->
              </v-card-text>
            </v-card>
          </template>
    </div>
</template>

<script>
export default{
    data() {
        return {
            page:0,
            birth_day:"",
            FirstName:"",
            Lastname:"",
            numero_tlf:"",
            cin:"",
            email:""
        }
    },
}
</script>