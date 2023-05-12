import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import HomeEtudiantView from '../views/etudiantView/HomeEtudiantView.vue'
import DocumentView from '../views/etudiantView/DocumentView.vue'
import ForumView from '../views/etudiantView/ForumView.vue'

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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
