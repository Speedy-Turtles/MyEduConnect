import Vue from 'vue'
import VueRouter from 'vue-router'
import signup from "../views/Auth/SignUpView.vue"
import signin from "../views/Auth/LoginView.vue"
import forgotPassword from "../views/Auth/ForgotPassword/ForgotPasswordView.vue"
import ChangePassword from "../views/Auth/ForgotPassword/ChangePasswordView.vue"
import EtudiantView from "../views/etudiant/EtudiantView.vue"
import dashboardView from '../views/chef_departement/dashboardView.vue'
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
 

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router