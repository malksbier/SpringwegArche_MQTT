<template>
<div class="navbar">
    <nav>
        <!-- OBERE LEISTE -->
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
            <v-btn light  v-if="user == 0" @click="loginDialog = true">
                <span> {{ $t("sign_in") }} </span>
                <v-icon right>fas fa-sign-in-alt</v-icon>
            </v-btn>
        </v-app-bar>

        <!-- Linke LEISTE -->
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
    <!-- Login -->
    <v-dialog persistent 
      v-model="loginDialog"
      width="600px" > 
        <v-card> 
           
            <v-card-title class="pb-0" >
                <span class="text-h5 ">{{ $t("login") }} </span>
            </v-card-title>

            <v-btn plain @click="loginDialog = false" class="pa-4" style="position: absolute; right:0px; top: 0px; border: 0px !important">
                <v-icon>fas fa-times</v-icon>
            </v-btn>

            <div class="px-5">
                <v-form v-model="loginValid">

                <v-text-field
                v-model="userLogin.username"
                required type="username" name="username" autocomplete="on"
                :label="$t('username')" 
                :rules="getUsernameRules()"
                hide-details="auto"
                ></v-text-field>

                <v-text-field
                v-model="userLogin.password"
                :disabled="enterPasswortResetCode"
                required type="password" name="password" autocomplete="on"
                :label="$t('password')" 
                :rules="getPasswordRules()"
                hide-details="auto"
                ></v-text-field>

                <div v-if="enterPasswortResetCode"> 
                    <v-text-field
                        v-model="newPassword"
                        type="password" name="password" autocomplete="off"
                        :disabled="enterPasswordResetVerificationCodeDisabled"
                        required 
                        :label="$t('new_password')" 
                        :rules="getPasswordRules()"
                        hide-details="auto"
                    ></v-text-field>

                    <v-text-field
                        :label="$t('verificationCode')"
                        :disabled="enterPasswordResetVerificationCodeDisabled"
                        v-if="enterPasswortResetCode"
                        v-model="passwordResetVerificationCode"
                    ></v-text-field>
                </div>
                

                <p v-if="loginErrorText" class="text-h6 pt-2 my-0 error--text">
                    <v-icon color="red lighten-2">fas fa-exclamation-triangle</v-icon>
                    {{ $t(loginErrorText) }} 
                </p>
                <p v-if="loginSuccsesText" class="text-h6 pt-2 my-0">
                    {{ $t(loginSuccsesText) }} 
                </p>

                </v-form>
            </div>

            <v-spacer></v-spacer>
            
            <v-card-actions class="pt-3">
                <v-btn
                    :disabled="enterPasswordResetVerificationCodeDisabled"
                    v-if="!enterPasswortResetCode"
                    light
                    text
                    @click="triggerPasswordReset();">
                        {{ $t("password_forget") }}
                </v-btn>
                <v-btn
                    v-if="enterPasswortResetCode"
                    :disabled="enterPasswordResetVerificationCodeDisabled"
                    light text
                    @click="verificatePassword()">
                        {{ $t("do_verification") }}
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn
                    :disabled="loginBlock"
                    light
                    text
                    @click="loginDialog = false; registrationDialog = true; enterPasswortResetCode=false;">
                        {{ $t("do_registration") }}
                </v-btn>
                <v-btn
                    :disabled="loginBlock"
                    dark 
                    @click="signIn()">
                        {{ $t("sign_in") }}
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
     <!-- Registratration-->
    <v-dialog persistent 
      v-model="registrationDialog"
      width="600px" > 
        <v-card> 
           
            <v-card-title class="pb-0" >
                <span class="text-h5 ">{{ $t("registration") }} </span>
            </v-card-title>

            <v-btn plain @click="registrationDialog = false" class="pa-4" style="position: absolute; right:0px; top: 0px; border: 0px !important">
                <v-icon>fas fa-times</v-icon>
            </v-btn>

            <div class="px-5">
                <v-form v-model="registrationValid">

                <v-text-field
                required
                :disabled="registrationBlock"
                v-model="userRegistration.username"
                type="username" name="username" autocomplete="on"
                :label="$t('username')" 
                :rules="getUsernameRules()"
                hide-details="auto"
                ></v-text-field>

                <v-text-field
                v-model="userRegistration.password"
                type="password" name="password" autocomplete="on"
                :disabled="registrationBlock"
                required 
                :label="$t('password')" 
                :rules="getPasswordRules()"
                hide-details="auto"
                ></v-text-field>

                <v-text-field
                required
                v-model="userRegistration.email"
                type="email" name="email" autocomplete="on"
                :disabled="registrationBlock"
                :label="$t('email')" 
                :rules="getEmailRules()"
                hide-details="auto"
                ></v-text-field>

                <v-text-field
                    :label="$t('verificationCode')"
                    v-if="enterEmailVerificationCode"
                    :disabled="enterEmailVerificationCodeDisabled"
                    v-model="emailVerificationCode"
                ></v-text-field>

                <p v-if="registrationErrorText" class="text-h6 pt-2 my-0 error--text">
                    <v-icon color="red lighten-2">fas fa-exclamation-triangle</v-icon>
                    {{ $t(registrationErrorText) }} 
                </p>
                <p v-if="registrationSuccsesText" class="text-h6 pt-2 my-0">
                    {{ $t(registrationSuccsesText) }} 
                </p>

                </v-form>
            </div>

            <v-spacer></v-spacer>
            
            <v-card-actions class="pt-3">
                <v-btn
                    v-if="enterEmailVerificationCode"
                    :disabled="enterEmailVerificationCodeDisabled"
                    light text
                    @click="verificateEmail()">
                        {{ $t("do_verification") }}
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn
                    light text
                    @click="registrationDialog= false; loginDialog = true;">
                        {{ $t("do_login") }}
                </v-btn>
                <v-btn
                    v-if="!registrationBlock"
                    :disabled="registrationBlock"
                    dark 
                    @click="register()">
                        {{ $t("do_registration") }}
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
    
</div>
</template>

<script>
import {switchPageSafely} from "@/util/RouterUtil";
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
            apiIp: "http://localhost:8080",

            showNavigationDrawer: false,
            loginDialog: false,
            registrationDialog: false,
            loginValid: false,
            registrationValid: false,
            loginErrorText: "",
            registrationErrorText: "",
            loginSuccsesText: "",
            registrationSuccsesText: "",
            registrationBlock: false,
            loginBlock: false,

            enterEmailVerificationCode: false,
            enterEmailVerificationCodeDisabled: false,
            emailVerificationCode: "",

            enterPasswortResetCode: false,
            enterPasswordResetVerificationCodeDisabled: false,
            passwordResetVerificationCode: "",
            newPassword: "",


            userRegistration: {
                username: "",
                password: "",
                email: "",
                language: "",
            },
            userLogin: {
                username: "",
                password: "",
            },
            userEmailVerificationCode: {
                username: "",
                code: "",
            },
            userPasswordResetInit: {
                username: "",
            },
            userPasswordReset: {
                username: "",
                code: "",
                newPassword: "",
            },

            routesNotLogedIn: 
            [
                { title: 'main_page', icon: 'fas fa-home' ,path: "/"},
                { title: 'about', icon: 'fas fa-address-card' ,path: "/about"},
            ],

            routesLogedIn:
            [
                { title: 'main_page', icon: 'fas fa-home' ,path: "/"},
                { title: 'mqtt_page', icon: 'fas fa-wrench' ,path: "/mqtt"},
                { title: 'about', icon: 'fas fa-address-card' ,path: "/about"},
            ],

            routes: [],
        }
    },
    created: function () {
        this.routes = this.routesNotLogedIn;
    },
    methods: {
        hideNavigationDrawer() {
            this.showNavigationDrawer = false;
        },
        triggerPasswordReset() {
            
                console.log("trigger password code: " + this.userLogin.username);

                var _this = this;

                this.userPasswordResetInit.username = this.userLogin.username;

                this.$axios.post(_this.apiIp + '/login/triggerPasswordReset', _this.userPasswordResetInit).then(function (response) {
                    if(response.status == 200) {
                        _this.loginSuccsesText = response.data;
                        _this.loginErrorText = "";

                        _this.enterPasswortResetCode = true; _this.newPassword='';
                        _this.enterPasswordResetVerificationCodeDisabled = false;
                    }
                }).catch(function (error) {
                    _this.loginErrorText = error.response.data;
                });
            
        },
        verificatePassword() {
            console.log("check password code: " + this.emailVerificationCode);
            var code = this.passwordResetVerificationCode.trim();

            this.userPasswordReset.username = this.userLogin.username;
            this.userPasswordReset.code = code;
            this.userPasswordReset.newPassword = this.newPassword

            var _this = this;

            this.$axios.post(_this.apiIp + '/login/validatePasswordReset', _this.userPasswordReset).then(function (response) {
                if(response.status == 200) {
                    _this.loginSuccsesText = response.data;
                    _this.loginErrorText = "";
                    _this.enterEmailVerificationCodeDisabled = true;
                    
                    _this.userLogin.password = _this.userPasswordReset.newPassword;
                    _this.enterPasswordResetVerificationCodeDisabled = true;

                    _this.enterPasswortResetCode = false;
                }
            }).catch(function (error) {
                _this.loginErrorText = error.response.data;
            });
        },
        verificateEmail() {
            console.log("check email code: " + this.emailVerificationCode);
            var code = this.emailVerificationCode.trim();
            
            this.userEmailVerificationCode.username = this.userRegistration.username;
            this.userEmailVerificationCode.code = code;

            var _this = this;

            this.$axios.post(_this.apiIp + '/login/autheticateEmail', _this.userEmailVerificationCode).then(function (response) {
                if(response.status == 200) {
                    _this.registrationSuccsesText = response.data;
                    _this.registrationErrorText = "";
                    _this.enterEmailVerificationCodeDisabled = true;
                    
                    _this.userLogin.username = _this.userRegistration.username;
                    _this.userLogin.password = _this.userRegistration.password;
                }
            }).catch(function (error) {
                _this.registrationErrorText = error.response.data;
            });
        },
        signIn(username, password) {
            if(this.enterPasswortResetCode) {
                // reset to login if Pasword reset is activ
                this.enterPasswortResetCode = false;
                this.enterPasswordResetVerificationCodeDisabled = false;
            } else {
                // true login
                var _this = this;
 
                if(this.loginValid) {

                    this.loginErrorText = "";
                    this.loginSuccsesText = "";

                    this.$axios.post(_this.apiIp + '/login/autheticate', this.userLogin).then(function (response) {
                        if(response.status == 200) {
                            _this.loginSuccsesText = response.data;
                        
                            var data = response.data;
                            const matches = [...data.matchAll(/'(.*?)'/g)];
                            var jswToken = matches[0][1];

                            if(jswToken.startsWith("Bearer")) {
                                _this.loginSuccsesText = "login_succesful";

                                _this.loginBlock = true;
                                _this.loginDialog = false;

                                _this.routes = _this.routesLogedIn;

                                var myUser = jswToken;
                                _this.$emit("update-user", myUser);
                            } else {
                                _this.loginErrorText = "login_unsuccesful";
                            }
                        }
                    }).catch(function (error) {
                        _this.loginErrorText = error.response.data;
                    });
                } else {
                    this.loginErrorText = "missing_inputs"
                }
            }
        },
        register() {
            var _this = this;
            if(this.registrationValid) {
               
               this.registrationErrorText = "";
               this.registrationSuccsesText = "";

                this.userRegistration.language = this.$i18n.locale;
               this.$axios.post(_this.apiIp +  '/login/register', this.userRegistration).then(function (response) {
                    if(response.status == 200) {
                        if(response.data == "user_created_enter_code") {
                            // Nutzer hat sich erfolgreich registriert und muss email code eingeben
                            _this.enterEmailVerificationCode = true;
                        } 
                        
                        _this.registrationSuccsesText = response.data;
                        _this.registrationBlock = true;
                    }
                }).catch(function (error) {
                    _this.registrationErrorText = error.response.data;
                });

           } else {
               this.registrationErrorText = "missing_inputs"
           }
        },
        signOut() {
            this.resetData();

            this.routes = this.routesNotLogedIn;

            var myUser = 0;
            this.$emit("update-user", myUser);
        },
        switchPageSafe(path) {
            switchPageSafely(this.$route, this.$router,path)
        },


        getEmailRules() {
            return [
                value => !!value || this.$t("required"),
                value => {
                    const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                    return pattern.test(value) || this.$t('invalid_e-mail')
                },
                value => (value || '').length >= 6 || this.$t('min_6_characters'),
            ];
        },
        getUsernameRules() {
            return [
                value => !!value || this.$t("required"),
                value => (value || '').length <= 20 || this.$t('max_20_characters'),
                value => (value || '').length >= 6 || this.$t('min_6_characters'),
            ];
        },
        getPasswordRules() {
            return [
                value => !!value || this.$t("required"),
                value => (value || '').length >= 6 || this.$t('min_6_characters')
            ];
        },

        resetData() {
            this.loginDialog = false;
            this.registrationDialog = false;
            this.loginValid = false;
            this.registrationValid = false;
            this.loginErrorText = "";
            this.registrationErrorText = "";
            this.loginSuccsesText = "";
            this.registrationSuccsesText = "";
            this.registrationBlock = false;
            this.loginBlock = false;

            this.enterEmailVerificationCode = false;
            this.enterEmailVerificationCodeDisabled = false;
            this.emailVerificationCode = "";
            this.enterPasswortResetCode = false,
            this.enterPasswordResetVerificationCodeDisabled = false,
            this.passwordResetVerificationCode = "",
            this.newPassword = "",

            this.userRegistration.username = "";
            this.userRegistration.password = "";
            this.userRegistration.email = "";
            this.userRegistration.language = "";
        
            this.userLogin.username = "";
            this.userLogin.password = "";
        
            this.userEmailVerificationCode.username = "";
            this.userEmailVerificationCode.code = "";

            this.userPasswordReset.username = "";
            this.userPasswordReset.code = "";
            this.userPasswordReset.newPassword = "";

        }
    }
}
</script>
