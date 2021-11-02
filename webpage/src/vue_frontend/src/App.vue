
<template>
  <v-app>
    <v-row style="margin: 0">
      
        <v-navigation-drawer
      class="deep-purple accent-4 px-4"
      dark
      permanent>
      <v-list>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link @click="switchPageSafe(item.path)">
          <v-list-item-icon class="v-list-item__icon-same-width">
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ $t(item.title) }}</v-list-item-title>
          </v-list-item-content>
      
        </v-list-item>

        <language-switcher></language-switcher>

        <v-btn block>
            Logout
          </v-btn>
      </v-list>
    </v-navigation-drawer>
     
      
    <v-main>
      <router-view/>
    </v-main>

    </v-row>

    

    

    
  </v-app>
</template>

<script>
import LanguageSwitcher, { methods } from './components/LanguageSwitcher.vue';
import {getBrowserLocale} from "@/util/i18n/LocalsUtil"
import {switchPageSafely} from "@/util/RouterUtil"


export default {
  components: { LanguageSwitcher },
  name: 'App',

  data: () => ({
    displayAppBar : true,
    items: [
          { title: 'main_page', icon: 'fas fa-home' ,path: "/"},
          { title: 'about', icon: 'fas fa-address-card' ,path: "/about"},
        ],
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
  },
  methods: {
    switchPageSafe(path) {
      switchPageSafely(this.$route, this.$router,path)
    },
  }
};
</script>
