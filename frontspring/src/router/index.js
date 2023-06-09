import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

import login from '../views/Auth/LoginView'
import SignUp from '../views/Auth/SignUpView'
import forgotPasswordView from "../views/Auth/ForgotPassword/forgotPasswordView.vue";
import ChangerPassword from "../views/Auth/ForgotPassword/ChangerPasswordView.vue"
import dashboradAdminView from "../views/Admin/dashboradAdminView.vue"


import HomeEtudiantView from '../views/etudiantView/HomeEtudiantView.vue'
import DocumentView from '../views/etudiantView/DocumentView.vue'
import ForumView from '../views/etudiantView/ForumView.vue'
import EditProfilView from "../views/etudiantView/EditProfilView.vue"

import chat from "../components/etudiant/forum/chat.vue"

Vue.use(VueRouter)

const routes = [
  
  {
    path: '/chat:id',
    name: 'chat',
    component: chat
  },
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/SignUp',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/forgotPasswordView',
    name: 'forgotPasswordView',
    component: forgotPasswordView
  },
  {
    path: '/dashboradAdminView',
    name: 'dashboradAdminView',
    component: dashboradAdminView
  },
  {
    path: '/ChangerPassword',
    name: 'ChangerPassword',
    component: ChangerPassword
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
    path: '/etudiant',
    name: 'homeEtudiant',
    component: HomeEtudiantView
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
    path: '/etudiant/EditProfilView',
    name: 'EditProfilView',
    component: EditProfilView
},
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
