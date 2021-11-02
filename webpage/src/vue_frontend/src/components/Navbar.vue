<template>
    <nav>
        <v-app-bar flat app dark elevation="4">
            <v-app-bar-nav-icon @click="showNavigationDrawer = !showNavigationDrawer" style="margin: 5px !important;">
                <v-icon> fas fa-bars </v-icon>
            </v-app-bar-nav-icon>
            <v-spacer></v-spacer>
            <v-toolbar-title> {{ $t("appname") }} </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-btn light v-if="user != 0" @click="signOut();"> 
                <span> {{ $t("sign_out") }} </span>
                <v-icon right>fas fa-sign-out-alt</v-icon>
            </v-btn>
            <v-btn light  v-if="user == 0" @click="signIn();">
                <span> {{ $t("sign_in") }} </span>
                <v-icon right>fas fa-sign-in-alt</v-icon>
            </v-btn>
        </v-app-bar>

        <v-navigation-drawer app v-model="showNavigationDrawer"> 
            <div style="height: 100%" @mouseleave="hideNavigationDrawer()">
            <v-list>
             
                    <h4 class="px-4 pt-6">{{ $t("navigation") }}</h4>
             
                

                <v-divider></v-divider>
                <v-list-item
                    v-for="route in routes"
                    :key="route.title"
                    @click="switchPageSafe(route.path)">
                        <v-list-item-icon class="v-list-item__icon-same-width">
                            <v-icon>{{ route.icon }}</v-icon>
                        </v-list-item-icon>

                        <v-list-item-content link>  
                            <v-list-item-title>{{ $t(route.title) }}</v-list-item-title>
                        </v-list-item-content>
      
                </v-list-item>
                <v-divider></v-divider>

                <language-switcher></language-switcher>

            </v-list>
            </div>
        </v-navigation-drawer>
    </nav>
</template>

<script>
import {switchPageSafely} from "@/util/RouterUtil"
import LanguageSwitcher from '@/components/LanguageSwitcher.vue';

export default {
    props: {
        user: {
            required: true
        }
    },
    components: {
        LanguageSwitcher
    },

    data() {
        return {
            showNavigationDrawer: false,

            routes: [
                { title: 'main_page', icon: 'fas fa-home' ,path: "/"},
                { title: 'about', icon: 'fas fa-address-card' ,path: "/about"},
        ],
        }
    },
    methods: {
        hideNavigationDrawer() {
            this.showNavigationDrawer = false;
        },
        signIn() {
            //console.log("signin");
            var myUser = 1;
            this.$emit("update-user", myUser);
        },
        signOut() {
            //console.log("signOut");
            var myUser = 0;
            this.$emit("update-user", myUser);
        },
        switchPageSafe(path) {
            switchPageSafely(this.$route, this.$router,path)
        },
    }
}
</script>
