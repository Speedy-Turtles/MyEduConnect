import Vue from 'vue'
import VueRouter from 'vue-router'
import signup from "../views/Auth/SignUpView.vue"
import signin from "../views/Auth/LoginView.vue"
import forgotPassword from "../views/Auth/ForgotPassword/ForgotPasswordView.vue"
import ChangePassword from "../views/Auth/ForgotPassword/ChangePasswordView.vue"
import dashboardView from '../views/chef_departement/dashboardChefDepartementView.vue'
import gererDemandeView from '../views/chef_departement/gererDemandeView.vue'
import gererEnseignatView from '../views/chef_departement/gererEnseignatView.vue'
import gererEtudiantView from '../views/chef_departement/gererEtudiantView.vue'
import gererNeaveauteView from '../views/chef_departement/gererNeaveauteView.vue'
import gererSpecialiteView from '../views/chef_departement/gererSpecialiteView.vue'
import HomeView from '../views/HomeView.vue'
import EtudiantView from '../views/etudiant/EtudiantView'
import DocumentView from '../views/etudiant/DocumentView'
import ForumView from '../views/etudiant/ForumView'
import ClubView from '../views/etudiant/ClubView'
import HelpView from '../views/etudiant/HelpView'



import gererClassesView from '../views/chef_departement/gererClassesView.vue'
import dashboardAdminView from "../views/Admin/DashboardView.vue"
import testView from "../views/test/TestView.vue"

Vue.use(VueRouter)


const routes = [{
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/signup',
        name: 'signup',
        component: signup
    },
    {
        path: '/signin',
        name: 'signin',
        component: signin
    },

    {
        path: '/forgotpassword',
        name: 'forgotpassword',
        component: forgotPassword
    },
    {
        path: '/ChangePassword',
        name: 'ChangePassword',
        component: ChangePassword
    },
    {
        path: '/chef_departement',
        name: 'dashboardChef',
        component: dashboardView
    }, {
        path: '/chef_departement/gererdemande',
        name: 'gererDemande',
        component: gererDemandeView
    }, {
        path: '/chef_departement/gererenseignat',
        name: 'gererEnseignat',
        component: gererEnseignatView
    }, {
        path: '/chef_departement/gereretudiant',
        name: 'gererEtudiant',
        component: gererEtudiantView
    }, {
        path: '/chef_departement/gererneaveaute',
        name: 'gererNeaveaute',
        component: gererNeaveauteView
    },
    {
        path: '/chef_departement/gererspecialite',
        name: 'gererspecialite',
        component: gererSpecialiteView
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path: '/etudiant',
        name: 'etudiant',
        component: EtudiantView
    },
    {
        path: '/etudiant/document',
        name: 'document',
        component: DocumentView
    },
    {
        path: '/etudiant/forum',
        name: 'forum',
        component: ForumView
    },
    {
        path: '/etudiant/club',
        name: 'club',
        component: ClubView
    },
    {
        path: '/etudiant/help',
        name: 'help',
        component: HelpView
    },
    {
        path: '/dashboardAdminView',
        name: 'dashboardAdminView',
        component: dashboardAdminView
    },
    {
        path: '/specialite/:id',
        name: 'gererClasses',
        component: gererClassesView
    },
    {
        path: '/testView',
        name: 'testView',
        component: testView
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/AboutView.vue')
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router