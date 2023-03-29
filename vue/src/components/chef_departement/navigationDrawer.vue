<template>
    <aside
     class="nav-drawer" id="navDrawer">
        <vdiv class="justify-center">
            <v-list-item class="px-2">
        <v-list-item-avatar>
          <v-img src="../../assets/DSC_6972 (1).jpg"></v-img>
        </v-list-item-avatar>

        <v-list-item-title class="hidden_description">Hkimi Mohamed Amin</v-list-item-title>
      </v-list-item>
        </vdiv>
        <ul>
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('home')" 
                    class="btn-drawer" :class="[currentPage=='home' ? 'active' : '']"><v-icon>mdi-home</v-icon><span class="hidden_description">home</span></v-btn></v-list-item>
                    
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('specialite')" class="btn-drawer" :class="[currentPage=='specialite' ? 'active' : '']"><v-icon>mdi-school</v-icon> <span class="hidden_description"> gerer les
                    specialite </span></v-btn></v-list-item>
                    
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('enseignatns')"  class="btn-drawer" :class="[currentPage=='enseignatns' ? 'active' : '']"><v-icon>mdi-account-box</v-icon> <span class="hidden_description"> gerer les
                    enseignats </span></v-btn></v-list-item>
                    
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('etudiant')" class="btn-drawer" :class="[currentPage=='etudiant' ? 'active' : '']"><v-icon>mdi-account-school</v-icon> <span class="hidden_description"> gerer
                    les etudiants </span></v-btn></v-list-item>
                    
            <v-list-item class="justify-center"><v-btn rounded  @click="selectActive('demande')" class="btn-drawer" :class="[currentPage=='demande' ? 'active' : '']"><v-icon>mdi-file-document</v-icon> <span class="hidden_description"> gerer
                    les demandes </span></v-btn></v-list-item>
        </ul>


        <v-btn  class="btn-change-dash justify-center" fab @click="changeDash()"><v-icon >{{currentIcon}}</v-icon></v-btn>
    </aside>
</template>

<script>
export default {
    name: 'navigationDrawer',
    data() {
        return {
            currentPage:'home',
            currentIcon:'mdi-arrow-left'
        }
    },
    methods:{
        selectActive(pageName){
            this.currentPage=pageName;
        },
        changeDash(){
                this.currentIcon=='mdi-arrow-right' ? this.currentIcon='mdi-arrow-left' : this.currentIcon='mdi-arrow-right';
                document.querySelector("#navDrawer").classList.toggle("min-width");
                document.querySelectorAll(".hidden_description").forEach(x => x.classList.toggle("none"));
        }
    }
}
</script>
<style>
body{
    height: 40000px;
}
.nav-drawer {
    background-color: #525fe1 !important;
    height: 95% !important;
    width: 300px;
    border-radius: 0% 20% 20% 0%;
    position: relative;
    position: fixed;
    margin-top: 5%;
}
.nav-drawer v-btn{
    background: transparent!important;
}
.btn-drawer {
    color: #525fe1 !important;
}
.active{
    background: #ffcf59 !important;
}
.btn-change-dash{
    position: absolute;
    bottom: 50px;
    color: #525fe1!important;
    margin-left: 25%;
}
.none{
    display: none!important;
}
.min-width{
    width: 92px!important;
}
</style>