
<template>
  <v-app>
    <v-app-bar
      app color="primary" dark>

    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),

  created() {
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
  }
};
</script>
