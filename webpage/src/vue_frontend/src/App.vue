
<template>
  <v-app>
    <NavbarDesign :user="user" @update-user="user = $event"></NavbarDesign>
    <v-main >
      <router-view/>
    </v-main>
    
  </v-app>
</template>

<script>

import {getBrowserLocale} from "@/util/i18n/LocalsUtil"

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
  },
  methods: {
    
  }
};
</script>
