import Vue from 'vue'
import VueRouter from 'vue-router'
import signup from "../views/Auth/SignUpView.vue"
import signin from "../views/Auth/LoginView.vue"
import forgotPassword from "../views/Auth/ForgotPassword/ForgotPasswordView.vue"
import ChangePassword from "../views/Auth/ForgotPassword/ChangePasswordView.vue"
import dashboardView from '../views/chef_departement/dashboardView.vue'
import HomeView from '../views/HomeView.vue'
import EtudiantView from '../views/etudiant/EtudiantView'
import DocumentView from '../views/etudiant/DocumentView'
import ForumView from '../views/etudiant/ForumView'
import ClubView from '../views/etudiant/ClubView'
import HelpView from '../views/etudiant/HelpView'

Vue.use(VueRouter)

const routes = [
  {
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
    path:'/etudiant',
    name:'etudiant',
    component:EtudiantView
  },
  {
    path: '/chef_departement',
    name: 'dashboard',
    component: dashboardView
    },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/etudiant',
    name:'etudiant',
    component:EtudiantView
  },
  {
    path:'/etudiant/document',
    name:'document',
    component:DocumentView
  },
  {
    path:'/etudiant/forum',
    name:'forum',
    component:ForumView
  },
  {
    path:'/etudiant/club',
    name:'club',
    component:ClubView
  },
  {
    path:'/etudiant/help',
    name:'help',
    component:HelpView
  },
  {
    path: '/chef_departement',
    name: 'dashboard',
    component: dashboardView
    },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router