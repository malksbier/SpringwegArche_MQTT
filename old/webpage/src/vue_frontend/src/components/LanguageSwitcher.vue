<template>
  <div class="languageSwitcher">
        <v-list-item class="v-list-item-same-height"> 
          <v-list-item-icon class="v-list-item__icon-same-width" style="margin-top: 13px"> 
            <v-switch @change="languageToggled()" class="no-magrin no-padding"></v-switch>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{$t("own-language")}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      
        
  </div>
</template>

<script>

module.exports = {
  mounted() {
    const browserLocale = this.$i18n.locale;
    /*
    console.log("[languageSwitcher:mounted]");
    console.log("user prefered local: ", browserLocale);
    console.log("Active locale: ", this.$i18n.locale);
    */
    if(browserLocale == "de")  {
      this.languageIsEnglish = false;
      this.$i18n.locale = "de";
    } else {
      this.languageIsEnglish = true;
      this.$i18n.locale = "en";
    }
  },
  data: function() {
    return {
      languageIsEnglish : true,
    };
  },
  methods: {
    languageToggled() {
      this.languageIsEnglish = !this.languageIsEnglish;
      if(this.languageIsEnglish) {
        this.$i18n.locale = "en";
      } else {
        this.$i18n.locale = "de";
      }
      /*
      console.log("[languageSwitcher:changed]");
      console.log("Active locale: ", this.$i18n.locale); 
      */
    },
  }

  
};
</script>