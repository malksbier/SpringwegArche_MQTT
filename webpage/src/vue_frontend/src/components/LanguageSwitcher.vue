<template>
  <div class="languageSwitcher">
        <v-row >
          <v-switch @change="languageToggled()"></v-switch>
          <p> {{$t("own-language")}} </p>
        </v-row>
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