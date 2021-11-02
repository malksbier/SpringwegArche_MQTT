<template>
    <nav>
        <v-toolbar flat app dark elevation="4">
            <v-app-bar-nav-icon  @click="showNavigationDrawer = !showNavigationDrawer">
                <v-icon > fas fa-bars </v-icon>
            </v-app-bar-nav-icon>
            <v-spacer></v-spacer>
            <v-toolbar-title> APPNAME </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-btn flat light> 
                <span>sign_out</span>
                <v-icon right>fas fa-sign-out-alt</v-icon>
            </v-btn>
            <v-btn flat light>
                <span>sign_in</span>
                <v-icon right>fas fa-sign-in-alt</v-icon>
            </v-btn>
        </v-toolbar>

        <v-navigation-drawer app v-model="showNavigationDrawer" > 
            <div style="height: 100%" @mouseleave="hideNavigationDrawer()">
                <p >NAVIGATION</p>
            </div>  
        </v-navigation-drawer>
    </nav>
</template>

<script>
export default {
    data() {
        return {
            showNavigationDrawer: false
        }
    },
    methods: {
        hideNavigationDrawer() {
            console.log("test");
            this.showNavigationDrawer = false;
        },
    }
}
</script>
