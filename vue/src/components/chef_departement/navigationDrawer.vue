<template>
    <aside class="nav-drawer" id="navDrawer">
        <div class="justify-center">
        </div>
        <ul>
            <router-link class="link" to="/chef_departement/"><v-list-item class="justify-center"><v-btn rounded
                        @click="selectActive('home')" class="btn-drawer btn-width"
                        :class="[currentPage == 'home' ? 'active' : '']"><v-icon>mdi-home</v-icon><span
                            class="hidden_description">home</span></v-btn></v-list-item>
            </router-link>
            <router-link class="link" to="/chef_departement/gererspecialite"> <v-list-item class="justify-center"><v-btn
                        rounded @click="selectActive('specialite')" class="btn-drawer btn-width"
                        :class="[currentPage == 'specialite' ? 'active' : '']"><v-icon>mdi-school</v-icon> <span
                            class="hidden_description"> gerer les
                            specialite </span></v-btn></v-list-item></router-link>

            <router-link class="link" to="/chef_departement/gererenseignat"> <v-list-item class="justify-center"><v-btn
                        rounded @click="selectActive('enseignatns')" class="btn-drawer btn-width"
                        :class="[currentPage == 'enseignatns' ? 'active' : '']"><v-icon>mdi-account-box</v-icon> <span
                            class="hidden_description"> gerer les
                            enseignats </span></v-btn></v-list-item></router-link>

            <router-link class="link" to="/chef_departement/gereretudiant"><v-list-item class="justify-center"><v-btn
                        rounded @click="selectActive('etudiant')" class="btn-drawer btn-width"
                        :class="[currentPage == 'etudiant' ? 'active' : '']"><v-icon>mdi-account-school</v-icon> <span
                            class="hidden_description"> gerer
                            les etudiants </span></v-btn></v-list-item></router-link>

            <router-link class="link" to="/chef_departement/gererdemande"><v-list-item class="justify-center"><v-btn rounded
                        @click="selectActive('demande')" class="btn-drawer btn-width"
                        :class="[currentPage == 'demande' ? 'active' : '']"><v-icon>mdi-file-document</v-icon> <span
                            class="hidden_description"> gerer
                            les demandes </span></v-btn></v-list-item></router-link>
            <router-link class="link" to="/chef_departement/gererneaveaute"><v-list-item class="justify-center"><v-btn
                        rounded @click="selectActive('neauveuate')" class="btn-drawer btn-width"
                        :class="[currentPage == 'neauveuate' ? 'active' : '']"><v-icon>mdi-newspaper</v-icon> <span
                            class="hidden_description"> gerer
                            les neauveuate </span></v-btn></v-list-item></router-link>
            <router-link class="link" to="/chef_departement/gereremploi"><v-list-item class="justify-center"><v-btn
                        rounded @click="selectActive('emploi')" class="btn-drawer btn-width"
                        :class="[currentPage == 'emploi' ? 'active' : '']"><v-icon> mdi-calendar</v-icon> <span
                            class="hidden_description"> gerer
                            les emplois </span></v-btn></v-list-item></router-link>
        </ul>


        <v-btn class="btn-change-dash justify-center" fab @click="changeDash()"><v-icon>{{ currentIcon }}</v-icon></v-btn>
    </aside>
</template>

<script>
import { AuthUser } from "@/store/Store.js"
export default {
    name: 'navigationDrawer',
    props: [
        'currentPage',
    ],
    setup() {
        const store = AuthUser();
        return { store }
    },
    data() {
        return {
            currentIcon: 'mdi-arrow-left'
        }
    },
    methods: {
        selectActive(pageName) {
            this.currentPage = pageName;
        },
        changeDash() {
            this.currentIcon == 'mdi-arrow-right' ? this.currentIcon = 'mdi-arrow-left' : this.currentIcon = 'mdi-arrow-right';
            document.querySelector("#navDrawer").classList.toggle("min-width");
            document.querySelectorAll(".hidden_description").forEach(x => x.classList.toggle("none"));
            document.querySelectorAll(".btn-drawer").forEach(x => x.classList.toggle("btn-width"));
        }
    }
}
</script>
<style scoped>
body {
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
    transition: all 300ms ease;
}

.nav-drawer v-btn {
    background: transparent !important;
}

.btn-drawer {
    color: #525fe1 !important;
    margin-top: 30px;
}

.active {
    background: #ffcf59 !important;
}

.btn-change-dash {
    position: absolute;
    bottom: 50px;
    color: #525fe1 !important;
    margin-left: 25%;
}

.none {
    display: none !important;
}

.min-width {
    width: 92px !important;
}

.link {
    text-decoration: none;
}

@media screen and (max-width:768px) {
    .nav-drawer {
        height: 10% !important;
        width: 100%;
        position: fixed;
        z-index: 1000;
        height: 10% !important;
        display: grid !important;
        bottom: 0;
        grid-template-columns: 1fr 1fr 1fr 1fr !important;
        border-radius: 0;
    }

    .nav-drawer ul {
        margin-left: -30% !important;
        display: inline-flex;
        gap: 0px !important;
        justify-content: center;
        align-items: center;
        padding: 0;
        margin-bottom: 10% !important;

    }

    .hidden_description,
    .btn-change-dash,
    .avatar {
        display: none;
    }

    .btn-drawer {
        min-width: 0px !important;
        width: 7px !important;
        font-size: 2px !important;
        margin-left: 2% !important;
    }
}
</style>