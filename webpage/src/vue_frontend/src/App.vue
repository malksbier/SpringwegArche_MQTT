
<template>
  <v-app>
    <NavbarDesign :user="user" @update-user="user = $event"></NavbarDesign>
    <!--
    <v-row style="margin: 0">
      
        <v-navigation-drawer
      class="deep-purple accent-4 px-4"
      dark  v-bind:width="200"
      permanent expand-on-hover>
      <v-list>
        <v-list-item
          v-for="item in items"
          :key="item.title"
           @click="switchPageSafe(item.path)">
          <v-list-item-icon class="v-list-item__icon-same-width">
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content link>  
            <v-list-item-title>{{ $t(item.title) }}</v-list-item-title>
          </v-list-item-content>
      
        </v-list-item>

        <language-switcher></language-switcher>

        <v-btn block>
            Logout
          </v-btn>
      </v-list>
    </v-navigation-drawer>
     
    -->   
    <v-main >
      <router-view/>
    </v-main>

 
    

    

    
  </v-app>
</template>

<script>
import Navbar from './components/Navbar.vue';
import LanguageSwitcher, { methods } from './components/LanguageSwitcher.vue';
import {getBrowserLocale} from "@/util/i18n/LocalsUtil"
import {switchPageSafely} from "@/util/RouterUtil"
import PageNotFound from './views/PageNotFound.vue';
import NavbarDesign from './components/Navbar.vue';


export default {
  components: { 
    //LanguageSwitcher, 
    NavbarDesign,
    //PageNotFound
     },
  name: 'App',
  
  data: () => ({
    user : 0, // 0 means null
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
