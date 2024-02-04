import { createRouter,createWebHistory } from 'vue-router';
import TopPage from "./TopPage.vue";
 
const routes = [
    { path: '/', name: 'TopPage', component: TopPage },
    { path: '/survey', name: 'Survey', component: () => import("./components/survey/Survey.vue") },
    { path: '/component', name: 'Component', component: () => import("./components/BaseComponent.vue") },
]
const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
})
 
export default router;