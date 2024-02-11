import { createRouter, createWebHistory } from 'vue-router'

import Home from '@/views/home-page.vue'
import About from '@/views/about-page.vue'
import NotFound from '@/views/not-found-page.vue'

const routes = [
    {path:'/', name: 'Home', component: Home},
    {path:'/about', name: 'About', component: About},
    {path:'/:pathMatch(.*)*', name: 'NotFound', component: NotFound},
]

const router = createRouter({
    history:createWebHistory(),
    routes: routes,
})

export default router