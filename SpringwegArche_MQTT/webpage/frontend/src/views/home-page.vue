

<template>
    <div>
        <Navbar/>
        <Home/>

        <img src="@/assets/silvy.jpg"  class="contained top-background" alt="...">

   
    </div>
</template>

<script>
    import Home from './/../components/home-component.vue'
    import Navbar from './/../components/navbar-component.vue'

    export default {
        components: {
            Home,
            Navbar,
     
        }
    }
</script>