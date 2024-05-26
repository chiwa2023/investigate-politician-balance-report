import { createRouter,createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";
 
const routes = [
    { path: "/", name: "TopPage", component: TopPage },
    { path: "/survey", name: "Survey", component: () => import("./components/survey/InvestigateSurvey.vue") },
    { path: "/audit-option-income", name: "AuditOptionIncome", component: () => import("./components/audit_opinion_income/AuditOpinionIncome.vue") },
    { path: "/component", name: "Component", component: () => import("./components/BaseComponent.vue") },
];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});
 
export default router;