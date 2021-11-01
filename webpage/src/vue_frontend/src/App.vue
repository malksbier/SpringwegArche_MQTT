
<template>
  <v-app>
    <v-app-bar
      app color="primary" dark>

      <language-switcher></language-switcher>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import LanguageSwitcher from './components/LanguageSwitcher.vue';
import {getBrowserLocale} from "@/util/i18n/LocalsUtil"

export default {
  components: { LanguageSwitcher },
  name: 'App',

  data: () => ({
    //
  }),

  created() {
    this.$i18n.locale = getBrowserLocale({ countryCodeOnly: true });

    /*
     this.$axios.post('http://localhost:8080/login/register', {
   
        username: "Adminn",
        password: "Adminn",
    
      }).then(function (response) {
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      }).then(function () {
        console.log("done");
      });

    */
  }
};
</script>
