<template>
  <div class="PageNotFound" style="height: 100%">

    <v-layout justify-center style="padding-top: 50px" class="">
 
        <v-col>
          <h1 style="text-align: center;"> {{ $t("page_not_found") }} </h1>
          <p style="text-align: center;"> {{ $t("page_not_found_hint") }} </p>
          <v-icon x-large color="red lighten-2" style="width:100%;">fas fa-exclamation-triangle</v-icon>
        </v-col>
      
      
    </v-layout>
    
  </div>
</template>

<script>


  export default {
    name: 'PageNotFound',
  }
</script>
