import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import Vuelidate from "./plugins/vuealidate";
import 'aos/dist/aos.css';
Vue.config.productionTip = false

new Vue({
  router,
  store,
  Vuelidate,
  vuetify,
  render: h => h(App)
}).$mount('#app')
