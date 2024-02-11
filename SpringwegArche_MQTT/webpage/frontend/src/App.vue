<template>
  <!--
    <h1>{{ $t("message.hello") }}</h1>
    <img alt="Vue logo" src="./assets/logo.png">
    <br>
  -->
  

  <router-link to="/">home</router-link>
  <router-link to="/about">About</router-link>
  <div id="router-container">
    <routerView></routerView>
  </div>
  
</template>

<script>

export default {
  name: 'App',
  components: {
   
  }
}
</script>

<style>

</style>
