﻿import { createRouter, createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";

const routes = [
    { path: "/", name: "TopPage", component: TopPage },
    { path: "/survey", name: "Survey", component: () => import("./components/pages/survey/InvestigateSurvey.vue") },
    { path: "/audit-option-income", name: "AuditOptionIncome", component: () => import("./components/pages/audit_opinion_income/AuditOpinionIncome.vue") },
    { path: "/detect-balancesheet-defect", name: "DetectBalancesheetDefect", component: () => import("./components/pages/detect_balancesheet_defect/DetectBalancesheetDefect.vue") },
    { path: "/make-link-balancesheet", name: "MakeLinkBalancesheet", component: () => import("./components/pages/make_link_balancesheet/MakeLinkBalancesheet.vue") },
    { path: "/component", name: "Component", component: () => import("./components/BaseComponent.vue") },
    { path: '/login_user', name: 'LoginUser', component: () => import("./components/pages/login_user/LoginUser.vue") },
    //ユーザ登録
    { path: '/regist_account', name: 'RegistAccount', component: () => import("./components/pages/regist_user/RegistUserAccount.vue") },
    { path: '/accept_account', name: 'AcceptUserAccount', component: () => import("./components/pages/regist_user/AcceptUserAccount.vue") },
    //パスワードリセット
    { path: '/reset_password/propose', name: 'PropseResetPassword', component: () => import("./components/pages/reset_password/PropseResetPassword.vue") },
    { path: '/reset_password/execute', name: 'ExecuteResetPassword', component: () => import("./components/pages/reset_password/ExecuteResetPassword.vue") },

    { path: "/propose-accept-not-regist-organization", name: "ProposeAcceptNotRegistOrganization", component: () => import("./components/pages/propose_accept_not_regist_organization/ProposeAcceptNotRegistOrganization.vue") },

    //公式フォーマット形式照会
    { path: "/show-org-balancesheet-report", name: "ShowOrgBalancesheetReport", component: () => import("./components/pages/show_org_balancesheet_report/ShowOrgBalancesheetReport.vue") },
    { path: "/show-party-usage-report", name: "ShowPartyUsageReport", component: () => import("./components/pages/show_party_usage_report/ShowPartyUsageReport.vue") },

    { path: "/inquire-nationarity", name: "InquireNationality", component: () => import("./components/pages/inquire_nationarity/InquireNationality.vue") },
];


const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;