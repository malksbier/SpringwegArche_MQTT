
<template>
  <v-app>
    <NavbarDesign :user="user" @update-user="user = $event"></NavbarDesign>
    <!--
    <v-row style="margin: 0">
      
        <v-navigation-drawer
      class="deep-purple accent-4 px-4"
      dark  v-bind:width="200"
      permanent expand-on-hover>
      
    </v-navigation-drawer>
     
    -->   
    <v-main >
      <router-view/>
    </v-main>

 
    

    

    
  </v-app>
</template>

<script>
import Navbar from './components/Navbar.vue';

import {getBrowserLocale} from "@/util/i18n/LocalsUtil"

import PageNotFound from './views/PageNotFound.vue';
import NavbarDesign from './components/Navbar.vue';


export default {
  components: { 
    NavbarDesign,
  },
  name: 'App',
  
  data: () => ({
    user : "0", // 0 means null
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
    
  }
};
</script>
