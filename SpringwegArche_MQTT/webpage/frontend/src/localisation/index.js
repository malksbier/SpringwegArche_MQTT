import { createI18n } from 'vue-i18n'
import messages from "./locals/locals"

  const i18n = createI18n({
    locale: 'de', // set locale
    fallbackLocale: 'en', // set fallback locale
    messages: messages, // set locale messages
    legacy: false,
    globalInjection: true,
  })

  export default i18n