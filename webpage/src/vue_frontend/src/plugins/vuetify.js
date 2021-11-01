import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import colors from 'vuetify/lib/util/colors'

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
          light: {
            primary: colors.red.darken1, // #E53935
            secondary: colors.red.lighten4, // #FFCDD2
            accent: colors.indigo.base, // #3F51B5
          },
          /*
          light: {
            primary: colors.brown.lighten3, // #BCAAA4
            secondary: colors.deepOrange.lighten2, // #FF8A65
            accent: colors.lime.accent2, // #EEFF41
          },
          dark: {
            primary: colors.brown.darken3, // #4E342E
            secondary: colors.green.darken4, // #1B5E20
            accent: colors.lime.darken3, // #AFB42B
          },
          */
        },
    },
});
