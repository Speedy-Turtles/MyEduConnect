import Vue from 'vue'
import VueRouter from 'vue-router'
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

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
