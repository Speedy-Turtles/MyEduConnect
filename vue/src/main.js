import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store/Store'
import vuetify from './plugins/vuetify'
import Vuelidate from "./plugins/vuealidate";
import {interceptors} from "./interceptors/interceptors.js";
import 'aos/dist/aos.css';
Vue.config.productionTip = false
import { createPinia, PiniaVuePlugin } from 'pinia'
Vue.use(PiniaVuePlugin)
const pinia = createPinia()
interceptors();

new Vue({
  router,
  store,
  Vuelidate,
  vuetify,
  pinia,
  render: h => h(App)
}).$mount('#app')
