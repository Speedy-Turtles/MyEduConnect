import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import login from '../views/Auth/LoginView'
import SignUp from '../views/Auth/SignUpView'
import forgotPasswordView from "../views/Auth/ForgotPassword/forgotPasswordView.vue";
import ChangerPassword from "../views/Auth/ForgotPassword/ChangerPasswordView.vue"
Vue.use(VueRouter)

const routes = [
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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
