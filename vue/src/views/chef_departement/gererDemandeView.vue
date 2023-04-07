<template>
  <div>
    <navbar />
    <navigationDrawer current-page="demande" />
    <div class="main_content">
      <v-row>
        <v-col align="center" justify="center">
          <v-btn text :loading="loader" disabled color="blue-grey" class="ma-2 white--text">
          </v-btn>
        </v-col>
      </v-row>
      <v-ccontainer v-if="demandes.length==0">
                <v-row align="center" justify="center">
                  <h1 style="text-align=center;color: #3f51b5;">Oops ! there's no data available</h1>
                </v-row>
              </v-ccontainer>
      <div v-if="loader == false && demandes.length!=0">
        <v-row style="margin-bottom: 2%;" align="center">
          <h1>les demandes de de document passé</h1>
        </v-row>
        <v-simple-table height="300px">
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  type demande
                </th>
                <th class="text-left">
                  user
                </th>
                <th class="text-left">
                  nombre acquis
                </th>
                <th class="text-left">
                  langue
                </th>
                <th class="text-left">
                  etat
                </th>
                <th class="text-left">
                  operation
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="demande in demandes" :key="demande.id">
                <td>{{ demande.document.Type }}</td>
                <td>{{ demande.user.FirstName + " " + demande.user.LastName }}</td>
                <td>{{ demande.nombre }}</td>
                <td>{{ demande.Langue }}</td>
                <td> <v-chip color="orange">pending</v-chip></td>
                <td>
                  <v-row>
                    <v-col>
                      <v-btn color="green" @click="accepterDemandes(demande)"
                        :loading="loaderAcceptBtn"><v-icon>mdi-check</v-icon></v-btn>
                    </v-col>
                    <v-col>
                      <v-btn color="red" @click="RefuserDemande(demande)" :loading="loaderRefuseBtn"><v-icon>mdi-cancel</v-icon></v-btn>
                    </v-col>
                  </v-row>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </div>
    </div>
    <v-snackbar v-model="snackbar" :timeout="timeout" :color="color">
      {{ text }}
      <template v-slot:action="{ attrs }">
        <v-btn color="red" text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';
import gererDocument from '@/service/Document/gererDocument.js'

export default {
  components: {
    navbar,
    navigationDrawer
  },
  created() {
    this.getDemandes();
  },
  data() {
    return {
      demandes: [],
      snackbar: false,
      text: '',
      timeout: 2000,
      color: '',
      loader: false,
      loaderAcceptBtn: false,
      loaderRefuseBtn: false,
    }
  },
  methods: {
    getDemandes() {
      this.loader = true;
      gererDocument.getChefAllDepartment().then(response => {
        this.demandes = response.data.data;
        console.log(this.demandes);
        this.loader = false;
      })
    },
    accepterDemandes(demande) {
      this.loaderAcceptBtn = true;
      let doc = {
        "id_user": demande.user.id,
        "docId": demande.document.id,
      }
      gererDocument.AccepterChefDocument(doc).then(response => {
        this.loaderAcceptBtn = false;
        this.color = "green";
        this.snackbar = true;
        this.text = "request accepted with success";
        this.getDemandes();
      }).catch(e=>{
          this.color="red";
        this.snackbar=true;
        this.text = "there was an error submiting your request";
      })
    },
    RefuserDemande(demande) {
      this.loaderRefuseBtn = true;
      let doc = {
        "id_user": demande.user.id,
        "docId": demande.document.id,
      }
      gererDocument.ReffuserChefDoccument(doc).then(response => {
        this.loaderRefuseBtn = false;
        this.color = "green";
        this.snackbar = true;
        this.text = "request accepted with success";
        this.getDemandes();
      }).catch(e=>{
          this.color="red";
          this.snackbar=true;
          this.text = "there was an error submiting your request";
      })
    }
  }
}
</script>
<style scoped>
.main_content {
  margin-left: 20%;
  margin-top: 5%;
}
</style>