<template>
    <v-navigation-drawer 
     class="nav-drawer" id="navDrawer">
        <v-list class="justify-center light">
            <v-list-item
        prepend-avatar="../../assets/DSC_6972 (1).jpg"
        title="John Leider"
        nav
        >
            </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list>
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('home')" 
                    class="btn-drawer" :class="[currentPage=='home' ? 'active' : '']"><v-icon>mdi-home</v-icon><span class="hidden_description">home</span></v-btn></v-list-item>
                    <v-divider></v-divider>
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('specialite')" class="btn-drawer" :class="[currentPage=='specialite' ? 'active' : '']"><v-icon>mdi-school</v-icon> <span class="hidden_description"> gerer les
                    specialite </span></v-btn></v-list-item>
                    <v-divider></v-divider>
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('enseignatns')"  class="btn-drawer" :class="[currentPage=='enseignatns' ? 'active' : '']"><v-icon>mdi-account-box</v-icon> <span class="hidden_description"> gerer les
                    enseignats </span></v-btn></v-list-item>
                    <v-divider></v-divider>
            <v-list-item class="justify-center"><v-btn rounded @click="selectActive('etudiant')" class="btn-drawer" :class="[currentPage=='etudiant' ? 'active' : '']"><v-icon>mdi-account-school</v-icon> <span class="hidden_description"> gerer
                    les etudiants </span></v-btn></v-list-item>
                    <v-divider></v-divider>
            <v-list-item class="justify-center"><v-btn rounded  @click="selectActive('demande')" class="btn-drawer" :class="[currentPage=='demande' ? 'active' : '']"><v-icon>mdi-file-document</v-icon> <span class="hidden_description"> gerer
                    les demandes </span></v-btn></v-list-item>
        </v-list>


        <v-divider></v-divider>
        <v-btn  class="btn-change-dash justify-center" fab @click="changeDash()"><v-icon>{{currentState=='open' ?  'mdi-arrow-left' : 'mdi-arrow-right'}}</v-icon></v-btn>
    </v-navigation-drawer>
</template>

<script>
export default {
    name: 'navigationDrawer',
    data() {
        return {
            currentPage:'home',
            currentState:'open',
        }
    },
    methods:{
        selectActive(pageName){
            this.currentPage=pageName;
        },
        changeDash(){
                document.querySelector("#navDrawer").classList.toggle("min-width");
                document.querySelectorAll(".hidden_description").forEach(x => x.classList.toggle("none"));
                this.currentState=="closed";
        }
    }
}
</script>
<style>
.nav-drawer {
    background-color: #525fe1 !important;
    height: 175% !important;
    border-radius: 0% 20% 20% 0%;
    position: relative;

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
    width: 100px!important;
}
.min-width:hover{
    width: 100px!important;
}
</style>