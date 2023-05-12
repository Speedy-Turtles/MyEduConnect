import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'

import vuetify from './plugins/vuetify'
import Vuelidate from "./plugins/vuelidate";
Vue.config.productionTip = false
import { createPinia, PiniaVuePlugin } from 'pinia'
Vue.use(PiniaVuePlugin)
const pinia = createPinia()
new Vue({
  router,
  pinia,
  vuetify,
  Vuelidate,
  render: h => h(App)
}).$mount('#app')
