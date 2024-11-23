import { createRouter,createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";
 
const routes = [
    { path: "/", name: "TopPage", component: TopPage },
    { path: "/survey", name: "Survey", component: () => import("./components/pages/survey/InvestigateSurvey.vue") },
    { path: "/natural-search", name: "NaturalSearch", component: () => import("./components/pages/natural_search/NaturalSearch.vue") },
    { path: "/insert-balancesheet-single", name: "InsertBalancesheetSingle", component: () => import("./components/pages/insert_balancesheet_single/InsertBalancesheetSingle.vue") },
    { path: "/insert-usage-report-single", name: "InsertUsageReportSingle", component: () => import("./components/pages/insert_usage_report_single/InsertUsageReportSingle.vue") },

    { path: "/zip-upload-document", name: "ZipUploadDocument", component: () => import("./components/pages/zip_upload_document/ZipUploadDocument.vue") },
    { path: "/listup-balancesheet-plan", name: "ListupBalancesheetPlan", component: () => import("./components/pages/listup_balancesheet_plan/ListupBalancesheetPlan.vue") },
    { path: "/listup-balancesheet-plan-err", name: "ListupBalancesheetPlanErr", component: () => import("./components/pages/listup_balancesheet_plan_err/ListupBalancesheetPlanErr.vue") },
    { path: "/schedule-force/poli-org-regist/prepared", name: "ForcePreparedBatch", component: () => import("./components/pages/schedule_force/poli_org_regist/ForcePreparedBatch.vue") },
    { path: "/schedule-force/poli-org-regist/regist", name: "ForceRegistBatch", component: () => import("./components/pages/schedule_force/poli_org_regist/ForceRegistBatch.vue") },

    { path: "/audit-option-income", name: "AuditOptionIncome", component: () => import("./components/pages/audit_opinion_income/AuditOpinionIncome.vue") },
    { path: "/detect-balancesheet-defect", name: "DetectBalancesheetDefect", component: () => import("./components/pages/detect_balancesheet_defect/DetectBalancesheetDefect.vue")},
    { path: "/make-link-balancesheet", name: "MakeLinkBalancesheet", component: () => import("./components/pages/make_link_balancesheet/MakeLinkBalancesheet.vue")},
    { path: "/propse-accept-read-csv-template", name: "PropseAcceptReadCsvTemplate", component: () => import("./components/pages/propose_accept_read_csv_template/ProposeAcceptReadCsvTemplate.vue")},
 
    { path: "/propose-accept-not-regist-organization", name: "ProposeAcceptNotRegistOrganization", component: () => import("./components/pages/propose_accept_not_regist_organization/ProposeAcceptNotRegistOrganization.vue")},
 
    { path: "/component", name: "Component", component: () => import("./components/BaseComponent.vue") },
];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});
 
export default router;