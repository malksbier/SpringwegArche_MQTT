<template>
    <nav>
        <v-app-bar flat app dark elevation="4">
            <v-app-bar-nav-icon @click="showNavigationDrawer = !showNavigationDrawer" style="margin: 5px !important;">
                <v-icon> fas fa-bars </v-icon>
            </v-app-bar-nav-icon>
            <v-spacer></v-spacer>
            <v-toolbar-title> APPNAME </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-btn light v-if="user != 0" @click="signOut();"> 
                <span>sign_out</span>
                <v-icon right>fas fa-sign-out-alt</v-icon>
            </v-btn>
            <v-btn light  v-if="user == 0" @click="signIn();">
                <span>sign_in</span>
                <v-icon right>fas fa-sign-in-alt</v-icon>
            </v-btn>
        </v-app-bar>

        <v-navigation-drawer app v-model="showNavigationDrawer" > 
            <div style="height: 100%" @mouseleave="hideNavigationDrawer()">
                <p >NAVIGATION</p>
            </div>  
        </v-navigation-drawer>
    </nav>
</template>

<script>
export default {
    props: {
        user: {
            required: true
        }
    },
        
  
    computed: {

    },
    data() {
        return {
            showNavigationDrawer: false,
        }
    },
    methods: {
        hideNavigationDrawer() {
            this.showNavigationDrawer = false;
        },
        signIn() {
            console.log("signin");
            var myUser = 1;
            this.$emit("update-user", myUser);
        },
        signOut() {
            console.log("signOut");
            var myUser = 0;
            this.$emit("update-user", myUser);
        },
    }
}
</script>
