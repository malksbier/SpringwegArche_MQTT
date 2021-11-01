import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import i18n from './i18n'

Vue.config.productionTip = false

Vue.prototype.$axios = axios;

new Vue({
  router,
  vuetify,
  i18n,
  render: h => h(App)
}).$mount('#app')
