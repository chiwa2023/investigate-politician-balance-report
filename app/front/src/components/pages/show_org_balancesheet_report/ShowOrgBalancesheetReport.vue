<script setup lang="ts">
import { computed, ref, Ref, WritableComputedRef } from "vue";
import SearchPoliticianAllLeastCapsuleDto from "../../../dto/politician_all/searchpoliticianAllLeastCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SearchPoliticianAll from "../../common/search_politician_all/SearchPoliticianAll.vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticianAllLeastInterface from "../../../dto/politician_all/politicianAllLeastDto";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import mockGetOrganizationBlanacesheetList from "./mock/mockGetOrganizationBlanacesheetList";
import ShowBalancesheetCoverAll from "../../common/political_org_balancesheet_report/01cover_all/ShowBalancesheetCoverAll.vue";
import ShowBalancesheetOverview from "../../common/political_org_balancesheet_report/02balace_overview/ShowBalancesheetOverview.vue";
import ShowBalancesheetIncomePublih from "../../common/political_org_balancesheet_report/03income_publish/ShowBalancesheetIncomePublih.vue";
import ShowBalancesheetIncomeBollow from "../../common/political_org_balancesheet_report/04income_bollow/ShowBalancesheetIncomeBollow.vue";
import ShowBalancesheetIncomeSubsidy from "../../common/political_org_balancesheet_report/05income_subsidy/ShowBalancesheetIncomeSubsidy.vue";
import ShowBalancesheetIncoemOther from "../../common/political_org_balancesheet_report/06income_other/ShowBalancesheetIncoemOther.vue";
import ShowBalancesheetIncomeDonatePersonal from "../../common/political_org_balancesheet_report/07income_donate_personal/ShowBalancesheetIncomeDonatePersonal.vue";
import ShowBalancesheetIncomeDonateCorp from "../../common/political_org_balancesheet_report/08income_donate_corp/ShowBalancesheetIncomeDonateCorp.vue";
import ShowBalancesheetIncomeDonatePoliOrg from "../../common/political_org_balancesheet_report/09income_donate_poli_org/ShowBalancesheetIncomeDonatePoliOrg.vue";
import ShowBalancesheetIncomeMediatePersonal from "../../common/political_org_balancesheet_report/10income_mediate_personal/ShowBalancesheetIncomeMediatePersonal.vue";
import ShowBalancesheetIncomeMediateCorp from "../../common/political_org_balancesheet_report/11income_mediate_corp/ShowBalancesheetIncomeMediateCorp.vue";
import ShowBalancesheetIncomeMediatePoliOrg from "../../common/political_org_balancesheet_report/12income_mediate_poli_org/ShowBalancesheetIncomeMediatePoliOrg.vue";
import ShowBalancesheetIncomeDonateAnonymous from "../../common/political_org_balancesheet_report/13income_donate_anonymous/ShowBalancesheetIncomeDonateAnonymous.vue";
import ShowBalancesheetIncomePartySpecific from "../../common/political_org_balancesheet_report/14income_party_specific/ShowBalancesheetIncomePartySpecific.vue";
import ShowBalancesheetIncomePartyPersonal from "../../common/political_org_balancesheet_report/15income_party_personal/ShowBalancesheetIncomePartyPersonal.vue";
import ShowBalancesheetIncomePartyCorp from "../../common/political_org_balancesheet_report/16income_party_corp/ShowBalancesheetIncomePartyCorp.vue";
import ShowBalancesheetIncomePartyPoliOrg from "../../common/political_org_balancesheet_report/17income_party_poli_org/ShowBalancesheetIncomePartyPoliOrg.vue";
import ShowBalancesheetIncomePartyMediatePersonal from "../../common/political_org_balancesheet_report/18income_party_mediate_personal/ShowBalancesheetIncomePartyMediatePersonal.vue";
import ShowBalancesheetIncomePartyMediateCorp from "../../common/political_org_balancesheet_report/19income_party_mediate_corp/ShowBalancesheetIncomePartyMediateCorp.vue";
import ShowBalancesheetIncomePartyMediatePoliOrg from "../../common/political_org_balancesheet_report/20income_party_mediate_poli_org/ShowBalancesheetIncomePartyMediatePoliOrg.vue";
import ShowBalancesheetOutcomeDetail from "../../common/political_org_balancesheet_report/21outcome_detail/ShowBalancesheetOutcomeDetail.vue";
import ShowBalancesheetOutcomeFuel from "../../common/political_org_balancesheet_report/22outome_fuel/ShowBalancesheetOutcomeFuel.vue";
import ShowBalancesheetOutcomeConsumable from "../../common/political_org_balancesheet_report/23outome_consumable/ShowBalancesheetOutcomeConsumable.vue";
import ShowBalancesheetOutcomeOffice from "../../common/political_org_balancesheet_report/24outome_office/ShowBalancesheetOutcomeOffice.vue";
import ShowBalancesheetOutcomeOrgActive from "../../common/political_org_balancesheet_report/25outome_org_active/ShowBalancesheetOutcomeOrgActive.vue";
import ShowBalancesheetOutcomeElection from "../../common/political_org_balancesheet_report/26outome_election/ShowBalancesheetOutcomeElection.vue";
import ShowBalancesheetOutcomePublish from "../../common/political_org_balancesheet_report/27outome_publish/ShowBalancesheetOutcomePublish.vue";
import ShowBalancesheetOutcomeCommercial from "../../common/political_org_balancesheet_report/28outome_commercial/ShowBalancesheetOutcomeCommercial.vue";
import ShowBalancesheetOutcomeParty from "../../common/political_org_balancesheet_report/29outome_party/ShowBalancesheetOutcomeParty.vue";
import ShowBalancesheetOutcomeOtherBuiss from "../../common/political_org_balancesheet_report/30outome_other_buiss/ShowBalancesheetOutcomeOtherBuiss.vue";
import ShowBalancesheetOutcomeResearch from "../../common/political_org_balancesheet_report/31outome_research/ShowBalancesheetOutcomeResearch.vue";
import ShowBalancesheetOutcomeDonate from "../../common/political_org_balancesheet_report/32outome_donate/ShowBalancesheetOutcomeDonate.vue";
import ShowBalancesheetOutcomeOtherCost from "../../common/political_org_balancesheet_report/33outome_other_cost/ShowBalancesheetOutcomeOtherCost.vue";
import ShowBalancesheetOutcomeSubsidy from "../../common/political_org_balancesheet_report/34outome_subsidy/ShowBalancesheetOutcomeSubsidy.vue";
import ShowBalancesheetOverviewEstate from "../../common/political_org_balancesheet_report/35overview_estate/ShowBalancesheetOverviewEstate.vue";
import ShowBalancesheetEstateLand from "../../common/political_org_balancesheet_report/36estate_land/ShowBalancesheetEstateLand.vue";
import ShowBalancesheetEstateBuilding from "../../common/political_org_balancesheet_report/37estate_building/ShowBalancesheetEstateBuilding.vue";
import ShowBalancesheetEstateSurface from "../../common/political_org_balancesheet_report/38estate_surface/ShowBalancesheetEstateSurface.vue";
import ShowBalancesheetEstateEffects from "../../common/political_org_balancesheet_report/39estate_effects/ShowBalancesheetEstateEffects.vue";
import ShowBalancesheetEstateDeposit from "../../common/political_org_balancesheet_report/40estate_deposit/ShowBalancesheetEstateDeposit.vue";
import ShowBalancesheetEstateTrust from "../../common/political_org_balancesheet_report/41estate_trust/ShowBalancesheetEstateTrust.vue";
import ShowBalancesheetEstateBonds from "../../common/political_org_balancesheet_report/42estate_bonds/ShowBalancesheetEstateBonds.vue";
import ShowBalancesheetEstateInvests from "../../common/political_org_balancesheet_report/43estate_invests/ShowBalancesheetEstateInvests.vue";
import ShowBalancesheetEstateLoan from "../../common/political_org_balancesheet_report/44estate_loan/ShowBalancesheetEstateLoan.vue";
import ShowBalancesheetEstateCaution from "../../common/political_org_balancesheet_report/45estate_caution/ShowBalancesheetEstateCaution.vue";
import ShowBalancesheetEstateFacilityChicket from "../../common/political_org_balancesheet_report/46estate_facility_chicket/ShowBalancesheetEstateFacilityChicket.vue";
import ShowBalancesheetEstateBorrowed from "../../common/political_org_balancesheet_report/47estate_borrowed/ShowBalancesheetEstateBorrowed.vue";
import ShowBalancesheetRealEstate from "../../common/political_org_balancesheet_report/48real_estate/ShowBalancesheetRealEstate.vue";
import ShowBalancesheetOath from "../../common/political_org_balancesheet_report/49oath/ShowBalancesheetOath.vue";
import ShowBalancesheetNotCollectRecipt from "../../common/political_org_balancesheet_report/50not_collect_recipt/ShowBalancesheetNotCollectRecipt.vue";
import ShowBalancesheetTransferState from "../../common/political_org_balancesheet_report/51transfer_state/ShowBalancesheetTransferState.vue";


//政治家検索コンポーネント
const isVisibleSearchPoliticianAllLeast: Ref<boolean> = ref(false);
const searchPoliticianAllLeastCapsuleDto: SearchPoliticianAllLeastCapsuleDto = new SearchPoliticianAllLeastCapsuleDto();
searchPoliticianAllLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticianAllLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticianAllLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

const politicianAllId: Ref<number> = ref(0);
const politicianAllCode: Ref<number> = ref(0);
const politicianAllName: Ref<string> = ref("");

/**
 * 政治家検索コンポーネント表示
 */
function onSearchPoliticianAllLeast() {
    isVisibleSearchPoliticianAllLeast.value = true;
}

/**
 * 政治家検索キャンセル
 */
function recieveCancelSearchPoliticianAllLeast() {
    //非表示
    isVisibleSearchPoliticianAllLeast.value = false;
}

/**
 * 政治家検索選択
 * @param sendDto 選択Dto
 */
function recieveSearchPoliticianAllLeastInterface(sendDto: PoliticianAllLeastInterface) {
    politicianAllId.value = sendDto.politicianAllId;
    politicianAllCode.value = sendDto.politicianAllCode;
    politicianAllName.value = sendDto.politicianAllName;

    //非表示
    isVisibleSearchPoliticianAllLeast.value = false;
}


//政治団体検索コンポーネント
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const searchPoliticalOrganizationLeastCapsuleDto: SearchPoliticalOrganizationLeastCapsuleDto = new SearchPoliticalOrganizationLeastCapsuleDto();
searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

const politicalOrgnaizationId: Ref<number> = ref(0);
const politicalOrgnaizationCode: Ref<number> = ref(0);
const politicalOrgnaizationName: Ref<string> = ref("");

/**
 * 政治団体検索コンポーネント表示
 */
function onSearchPoliticalOrgnaization() {
    isVisibleSearchPoliticalOrganizationLeast.value = true;
}
/**
 * 政治団体検索キャンセル
 */
function recieveCancelSearchPoliticalOrganizationLeast() {
    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

/**
 * 政治団体検索選択
 * @param sendDto 選択Dto
 */
function recievePoliticalOrganizationLeastInterface(sendDto: PoliticalOrganizationLeastInterface) {

    //政治団体を設定
    politicalOrgnaizationId.value = sendDto.politicalOrganizationId;
    politicalOrgnaizationCode.value = sendDto.politicalOrganizationCode;
    politicalOrgnaizationName.value = sendDto.politicalOrganizationName;

    listOfferingYear.value = mockGetOrganizationBlanacesheetList();

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

//提出年・提出日
const selectedOfferingYear: Ref<string> = ref("");
const listOfferingYear: Ref<SelectOptionInterface[]> = ref([]);


//表示領域
const showArea: Ref<string> = ref("");

//領域表示選択
const isEanableShowAreaSelect: WritableComputedRef<boolean> = computed(() => {
    if (selectedOfferingYear.value === "") {
        return true;
    } else {
        return false;

    }
});


// その1(表紙)
const COVER_ALL: string = "1";
// その2(収支の状況)
const BALNCE_OVERVIEW: string = "2";
// その3(機関誌の発行)
const INCOME_PUBLISH: string = "3";
// その4(借入金)
const INCOME_BOLLOW: string = "4";
// その5(供与された交付金に係る収入)
const INCOME_SUBSIDY: string = "5";
// その6(その他の収入)
const INCOME_OTHER: string = "6";
// その7の1(個人寄附)
const INCOME_DONATE_PERSONAL: string = "7";
// その7の2(法人寄附)
const INCOME_DONATE_CORP: string = "8";
// その7の3(政治団体寄附)
const INCOME_DONATE_POLI_ORG: string = "9";
// その8の1(個人寄附のあっせん)
const INCOME_MEDIATE_PERSONAL: string = "10";
// その8の2(法人寄附のあっせん)
const INCOME_MEDIATE_CORP: string = "11";
// その8の3(政治団体寄附のあっせん)
const INCOME_MEDIATE_POLI_ORG: string = "12";
// その9(政党匿名寄附)
const INCOME_DONATE_ANONYMOUS: string = "13";
// その10(特定パーティ収入)
const INCOME_PARTY_SPECIFIC: string = "14";
// その11その1(政治資金パーティ収入(個人))
const INCOME_PARTY_PERSONAL: string = "15";
// その11その2(政治資金パーティ収入(法人))
const INCOME_PARTY_CORP: string = "16";
// その11その3(政治資金パーティ収入(政治団体))
const INCOME_PARTY_POLI_ORG: string = "17";
// その12その1(政治資金パーティあっせん収入(個人))
const INCOME_PARTY_MEDIATE_PERSONAL: string = "18";
// その12その2(政治資金パーティあっせん収入(法人))
const INCOME_PARTY_MEDIATE_CORP: string = "19";
// その12その3(政治資金パーティあっせん収入(政治団体))
const INCOME_PARTY_MEDIATE_POLI_ORG: string = "20";
// その13(支出の内訳)
const OUTCOME_DETAIL: string = "21";
// その14の1経常経費(光熱費)
const OUTCOME_FUEL: string = "22";
// その14の2経常経費(備品・消耗費)
const OUTCOME_CONSUMABLE: string = "23";
// その14の3経常経費(事務所費)
const OUTCOME_OFFICE: string = "24";
// その15の1政治活動費(組織活動費)
const OUTCOME_ORG_ACTIVE: string = "25";
// その15の2政治活動費(選挙関係費)
const OUTCOME_ELECTION: string = "26";
// その15の3政治活動費(機関誌の発行費)
const OUTCOME_PUBLISH: string = "27";
// その15の4政治活動費(宣伝事業費)
const OUTCOME_COMERCIAL: string = "28";
// その15の5政治活動費(政治資金パーティ費)
const OUTCOME_PARTY: string = "29";
// その15の6政治活動費(その他事業費)
const OUTCOME_OTHER_BUIS: string = "30";
// その15の7政治活動費(調査研究費)
const OUTCOME_RESEARCH: string = "31";
// その15の8政治活動費(寄附・交付金)
const OUTCOME_DONATE: string = "32";
// その15の9政治活動費(その他の経費)
const OUTCOME_OTHER_COST: string = "33";
// その16本部または支部に供出した交付金
const OUTCOME_SUBSIDY: string = "34";
// その17資産の状況
const OVERVIEW_ESTATE: string = "35";
// その18資産の内訳(土地)
const ESTATE_LAND: string = "36";
// その18資産の内訳(建物)
const ESTATE_BUILDING: string = "37";
// その18資産の内訳(地上権・貸借権)
const ESTATE_SURFACE: string = "38";
// その18資産の内訳(100万円を超える動産)
const ESTATE_EFFECTS: string = "39";
// その18資産の内訳(預金)
const ESTATE_DEPOSIT: string = "40";
// その18資産の内訳(金銭信託)
const ESTATE_TRUST: string = "41";
// その18資産の内訳(有価証券)
const ESTATE_BONDS: string = "42";
// その18資産の内訳(出資による権利)
const ESTATE_INVESTS: string = "43";
// その18資産の内訳(100万円を超える貸付)
const ESTATE_LOAN: string = "44";
// その18資産の内訳(100万円を超える敷金)
const ESTATE_CAUTION: string = "45";
// その18資産の内訳(100万円を超える施設利用権)
const ESTATE_FACILITY_CHIKET: string = "46";
// その18資産の内訳(100万円を超える借入金)
const ESTATE_BORROWED: string = "47";
// その19不動産の利用現況
const REAL_ESTATE: string = "48";
// その20宣誓書
const OATH: string = "49";
// その領収書を徴しがたかった支出
const NOT_COLLECT_RECIPT: string = "50";
// 振込明細書に書かる支出目的書
const TRANSFER_STATEMENT: string = "51";

</script>
<template>
    <h1>政治資金収支報告書照会</h1>

    <div class="left-area">
        政治家
    </div>
    <div class="right-area">
        <input type="number" v-model="politicianAllCode" disabled="true" class="code-input">
        <input type="text" v-model="politicianAllName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticianAllLeast">政治家検索</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="politicalOrgnaizationCode" disabled="true" class="code-input">
        <input type="text" v-model="politicalOrgnaizationName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        提出年・提出日
    </div>
    <div class="right-area">
        <select v-model="selectedOfferingYear">
            <option v-for="(dto) in listOfferingYear" :key="dto.value">{{ dto.text }}</option>
        </select>
    </div>
    <div class="clear-both" />

    <div class="one-line">
        {{ selectedOfferingYear }}分を表示しています
        <select v-model="showArea" class="left-space" :disabled="isEanableShowAreaSelect">
            <option :value="COVER_ALL"> その1(表紙)</option>
            <option :value="BALNCE_OVERVIEW"> その2(収支の状況)</option>
            <option :value="INCOME_PUBLISH"> その3(機関誌の発行収入)</option>
            <option :value="INCOME_BOLLOW"> その4(借入金)</option>
            <option :value="INCOME_SUBSIDY"> その5(供与された交付金に係る収入)</option>
            <option :value="INCOME_OTHER"> その6(その他の収入)</option>
            <option :value="INCOME_DONATE_PERSONAL"> その7の1(個人寄附)</option>
            <option :value="INCOME_DONATE_CORP"> その7の2(法人寄附)</option>
            <option :value="INCOME_DONATE_POLI_ORG">その7の3(政治団体寄附)</option>
            <option :value="INCOME_MEDIATE_PERSONAL"> その8の1(個人寄附のあっせん)</option>
            <option :value="INCOME_MEDIATE_CORP"> その8の2(法人寄附のあっせん)</option>
            <option :value="INCOME_MEDIATE_POLI_ORG">その8の3(政治団体寄附のあっせん)</option>
            <option :value="INCOME_DONATE_ANONYMOUS">その9(政党匿名寄附)</option>
            <option :value="INCOME_PARTY_SPECIFIC"> その10(特定パーティ収入)</option>
            <option :value="INCOME_PARTY_PERSONAL"> その11その1(政治資金パーティ収入個人)</option>
            <option :value="INCOME_PARTY_CORP"> その11その2(政治資金パーティ収入法人)</option>
            <option :value="INCOME_PARTY_POLI_ORG"> その11その3(政治資金パーティ収入政治団体)</option>
            <option :value="INCOME_PARTY_MEDIATE_PERSONAL"> その12その1(政治資金パーティあっせん収入個人)</option>
            <option :value="INCOME_PARTY_MEDIATE_CORP"> その12その2(政治資金パーティあっせん収入法人)</option>
            <option :value="INCOME_PARTY_MEDIATE_POLI_ORG"> その12その3(政治資金パーティあっせん収入政治団体)</option>
            <option :value="OUTCOME_DETAIL">その13(支出の内訳)</option>
            <option :value="OUTCOME_FUEL">その14の1経常経費(光熱費)</option>
            <option :value="OUTCOME_CONSUMABLE"> その14の2経常経費(備品・消耗費)</option>
            <option :value="OUTCOME_OFFICE"> その14の3経常経費(事務所費)</option>
            <option :value="OUTCOME_ORG_ACTIVE">その15の1政治活動費(組織活動費)</option>
            <option :value="OUTCOME_ELECTION">その15の2政治活動費(選挙関係費)</option>
            <option :value="OUTCOME_PUBLISH"> その15の3政治活動費(機関誌の発行費)</option>
            <option :value="OUTCOME_COMERCIAL"> その15の4政治活動費(宣伝事業費)</option>
            <option :value="OUTCOME_PARTY"> その15の5政治活動費(政治資金パーティ費)</option>
            <option :value="OUTCOME_OTHER_BUIS"> その15の6政治活動費(その他事業費)</option>
            <option :value="OUTCOME_RESEARCH"> その15の7政治活動費(調査研究費)</option>
            <option :value="OUTCOME_DONATE"> その15の8政治活動費(寄附・交付金)</option>
            <option :value="OUTCOME_OTHER_COST"> その15の9政治活動費(その他の経費)</option>
            <option :value="OUTCOME_SUBSIDY"> その16本部または支部に供出した交付金</option>
            <option :value="OVERVIEW_ESTATE"> その17資産の状況</option>
            <option :value="ESTATE_LAND"> その18資産の内訳(土地)</option>
            <option :value="ESTATE_BUILDING"> その18資産の内訳(建物)</option>
            <option :value="ESTATE_SURFACE"> その18資産の内訳(地上権・貸借権)</option>
            <option :value="ESTATE_EFFECTS"> その18資産の内訳(100万円を超える動産)</option>
            <option :value="ESTATE_DEPOSIT"> その18資産の内訳(預金)</option>
            <option :value="ESTATE_TRUST"> その18資産の内訳(金銭信託)</option>
            <option :value="ESTATE_BONDS"> その18資産の内訳(有価証券)</option>
            <option :value="ESTATE_INVESTS"> その18資産の内訳(出資による権利)</option>
            <option :value="ESTATE_LOAN"> その18資産の内訳(100万円を超える貸付)</option>
            <option :value="ESTATE_CAUTION"> その18資産の内訳(100万円を超える敷金)</option>
            <option :value="ESTATE_FACILITY_CHIKET"> その18資産の内訳(100万円を超える施設利用権)</option>
            <option :value="ESTATE_BORROWED"> その18資産の内訳(100万円を超える借入金)</option>
            <option :value="REAL_ESTATE">その19不動産の利用現況</option>
            <option :value="OATH">その20宣誓書</option>
            <option :value="NOT_COLLECT_RECIPT"> その領収書を徴しがたかった支出</option>
            <option :value="TRANSFER_STATEMENT">振込明細書に書かる支出目的書</option>
        </select>
        <br>

        <!-- その1(表紙) -->
        <div v-if="showArea === COVER_ALL">
            <ShowBalancesheetCoverAll></ShowBalancesheetCoverAll>
        </div>

        <!-- その2(収支の状況) -->
        <div v-if="showArea === BALNCE_OVERVIEW">
            <ShowBalancesheetOverview></ShowBalancesheetOverview>
        </div>

        <!-- その3(機関誌の発行) -->
        <div v-if="showArea === INCOME_PUBLISH">
            <ShowBalancesheetIncomePublih></ShowBalancesheetIncomePublih>
        </div>

        <!-- その4(借入金) -->
        <div v-if="showArea === INCOME_BOLLOW">
            <ShowBalancesheetIncomeBollow></ShowBalancesheetIncomeBollow>
        </div>

        <!-- その5(供与された交付金に係る収入) -->
        <div v-if="showArea === INCOME_SUBSIDY">
            <ShowBalancesheetIncomeSubsidy></ShowBalancesheetIncomeSubsidy>
        </div>

        <!-- その6(その他の収入) -->
        <div v-if="showArea === INCOME_OTHER">
            <ShowBalancesheetIncoemOther></ShowBalancesheetIncoemOther>
        </div>

        <!-- その7の1(個人寄附) -->
        <div v-if="showArea === INCOME_DONATE_PERSONAL">
            <ShowBalancesheetIncomeDonatePersonal></ShowBalancesheetIncomeDonatePersonal>
        </div>

        <!-- その7の2(法人寄附) -->
        <div v-if="showArea === INCOME_DONATE_CORP">
            <ShowBalancesheetIncomeDonateCorp></ShowBalancesheetIncomeDonateCorp>
        </div>

        <!-- その7の3(政治団体寄附) -->
        <div v-if="showArea === INCOME_DONATE_POLI_ORG">
            <ShowBalancesheetIncomeDonatePoliOrg></ShowBalancesheetIncomeDonatePoliOrg>
        </div>

        <!-- その8の1(個人寄附のあっせん) -->
        <div v-if="showArea === INCOME_MEDIATE_PERSONAL">
            <ShowBalancesheetIncomeMediatePersonal></ShowBalancesheetIncomeMediatePersonal>
        </div>

        <!-- その8の2(法人寄附のあっせん) -->
        <div v-if="showArea === INCOME_MEDIATE_CORP">
            <ShowBalancesheetIncomeMediateCorp></ShowBalancesheetIncomeMediateCorp>
        </div>

        <!-- その8の3(政治団体寄附のあっせん) -->
        <div v-if="showArea === INCOME_MEDIATE_POLI_ORG">
            <ShowBalancesheetIncomeMediatePoliOrg></ShowBalancesheetIncomeMediatePoliOrg>
        </div>

        <!-- その9(政党匿名寄附) -->
        <div v-if="showArea === INCOME_DONATE_ANONYMOUS">
            <ShowBalancesheetIncomeDonateAnonymous></ShowBalancesheetIncomeDonateAnonymous>
        </div>

        <!-- その10(特定パーティ収入) -->
        <div v-if="showArea === INCOME_PARTY_SPECIFIC">
            <ShowBalancesheetIncomePartySpecific></ShowBalancesheetIncomePartySpecific>
        </div>

        <!-- その11その1(政治資金パーティ収入(個人)) -->
        <div v-if="showArea === INCOME_PARTY_PERSONAL">
            <ShowBalancesheetIncomePartyPersonal></ShowBalancesheetIncomePartyPersonal>
        </div>

        <!-- その11その2(政治資金パーティ収入(法人)) -->
        <div v-if="showArea === INCOME_PARTY_CORP">
            <ShowBalancesheetIncomePartyCorp></ShowBalancesheetIncomePartyCorp>
        </div>

        <!-- その11その3(政治資金パーティ収入(政治団体)) -->
        <div v-if="showArea === INCOME_PARTY_POLI_ORG">
            <ShowBalancesheetIncomePartyPoliOrg></ShowBalancesheetIncomePartyPoliOrg>
        </div>

        <!-- その12その1(政治資金パーティあっせん収入(個人)) -->
        <div v-if="showArea === INCOME_PARTY_MEDIATE_PERSONAL">
            <ShowBalancesheetIncomePartyMediatePersonal></ShowBalancesheetIncomePartyMediatePersonal>
        </div>

        <!-- その12その2(政治資金パーティあっせん収入(法人)) -->
        <div v-if="showArea === INCOME_PARTY_MEDIATE_CORP">
            <ShowBalancesheetIncomePartyMediateCorp></ShowBalancesheetIncomePartyMediateCorp>
        </div>

        <!-- その12その3(政治資金パーティあっせん収入(政治団体)) -->
        <div v-if="showArea === INCOME_PARTY_MEDIATE_POLI_ORG">
            <ShowBalancesheetIncomePartyMediatePoliOrg></ShowBalancesheetIncomePartyMediatePoliOrg>
        </div>

        <!-- その13(支出の内訳) -->
        <div v-if="showArea === OUTCOME_DETAIL">
            <ShowBalancesheetOutcomeDetail></ShowBalancesheetOutcomeDetail>
        </div>

        <!-- その14の1経常経費(光熱費) -->
        <div v-if="showArea === OUTCOME_FUEL">
            <ShowBalancesheetOutcomeFuel></ShowBalancesheetOutcomeFuel>
        </div>

        <!-- その14の2経常経費(備品・消耗費) -->
        <div v-if="showArea === OUTCOME_CONSUMABLE">
            <ShowBalancesheetOutcomeConsumable></ShowBalancesheetOutcomeConsumable>
        </div>

        <!-- その14の3経常経費(事務所費) -->
        <div v-if="showArea === OUTCOME_OFFICE">
            <ShowBalancesheetOutcomeOffice></ShowBalancesheetOutcomeOffice>
        </div>

        <!-- その15の1政治活動費(組織活動費) -->
        <div v-if="showArea === OUTCOME_ORG_ACTIVE">
            <ShowBalancesheetOutcomeOrgActive></ShowBalancesheetOutcomeOrgActive>
        </div>

        <!-- その15の2政治活動費(選挙関係費) -->
        <div v-if="showArea === OUTCOME_ELECTION">
            <ShowBalancesheetOutcomeElection></ShowBalancesheetOutcomeElection>
        </div>

        <!-- その15の3政治活動費(機関誌の発行費) -->
        <div v-if="showArea === OUTCOME_PUBLISH">
            <ShowBalancesheetOutcomePublish></ShowBalancesheetOutcomePublish>
        </div>

        <!-- その15の4政治活動費(宣伝事業費) -->
        <div v-if="showArea === OUTCOME_COMERCIAL">
            <ShowBalancesheetOutcomeCommercial></ShowBalancesheetOutcomeCommercial>
        </div>

        <!-- その15の5政治活動費(政治資金パーティ費) -->
        <div v-if="showArea === OUTCOME_PARTY">
            <ShowBalancesheetOutcomeParty></ShowBalancesheetOutcomeParty>
        </div>

        <!-- その15の6政治活動費(その他事業費) -->
        <div v-if="showArea === OUTCOME_OTHER_BUIS">
            <ShowBalancesheetOutcomeOtherBuiss></ShowBalancesheetOutcomeOtherBuiss>
        </div>

        <!-- その15の7政治活動費(調査研究費) -->
        <div v-if="showArea === OUTCOME_RESEARCH">
            <ShowBalancesheetOutcomeResearch></ShowBalancesheetOutcomeResearch>
        </div>

        <!-- その15の8政治活動費(寄附・交付金) -->
        <div v-if="showArea === OUTCOME_DONATE">
            <ShowBalancesheetOutcomeDonate></ShowBalancesheetOutcomeDonate>
        </div>

        <!-- その15の9政治活動費(その他の経費) -->
        <div v-if="showArea === OUTCOME_OTHER_COST">
            <ShowBalancesheetOutcomeOtherCost></ShowBalancesheetOutcomeOtherCost>
        </div>

        <!-- その16本部または支部に供出した交付金 -->
        <div v-if="showArea === OUTCOME_SUBSIDY">
            <ShowBalancesheetOutcomeSubsidy></ShowBalancesheetOutcomeSubsidy>
        </div>

        <!-- その17資産の状況 -->
        <div v-if="showArea === OVERVIEW_ESTATE">
            <ShowBalancesheetOverviewEstate></ShowBalancesheetOverviewEstate>
        </div>

        <!-- その18資産の内訳(土地) -->
        <div v-if="showArea === ESTATE_LAND">
            <ShowBalancesheetEstateLand></ShowBalancesheetEstateLand>
        </div>

        <!-- その18資産の内訳(建物) -->
        <div v-if="showArea === ESTATE_BUILDING">
            <ShowBalancesheetEstateBuilding></ShowBalancesheetEstateBuilding>
        </div>

        <!-- その18資産の内訳(地上権・貸借権) -->
        <div v-if="showArea === ESTATE_SURFACE">
            <ShowBalancesheetEstateSurface></ShowBalancesheetEstateSurface>
        </div>

        <!-- その18資産の内訳(100万円を超える動産) -->
        <div v-if="showArea === ESTATE_EFFECTS">
            <ShowBalancesheetEstateEffects></ShowBalancesheetEstateEffects>
        </div>

        <!-- その18資産の内訳(預金) -->
        <div v-if="showArea === ESTATE_DEPOSIT">
            <ShowBalancesheetEstateDeposit></ShowBalancesheetEstateDeposit>
        </div>

        <!-- その18資産の内訳(金銭信託) -->
        <div v-if="showArea === ESTATE_TRUST">
            <ShowBalancesheetEstateTrust></ShowBalancesheetEstateTrust>
        </div>

        <!-- その18資産の内訳(有価証券) -->
        <div v-if="showArea === ESTATE_BONDS">
            <ShowBalancesheetEstateBonds></ShowBalancesheetEstateBonds>
        </div>

        <!-- その18資産の内訳(出資による権利) -->
        <div v-if="showArea === ESTATE_INVESTS">
            <ShowBalancesheetEstateInvests></ShowBalancesheetEstateInvests>
        </div>

        <!-- その18資産の内訳(100万円を超える貸付) -->
        <div v-if="showArea === ESTATE_LOAN">
            <ShowBalancesheetEstateLoan></ShowBalancesheetEstateLoan>
        </div>

        <!-- その18資産の内訳(100万円を超える敷金) -->
        <div v-if="showArea === ESTATE_CAUTION">
            <ShowBalancesheetEstateCaution></ShowBalancesheetEstateCaution>
        </div>

        <!-- その18資産の内訳(100万円を超える施設利用権) -->
        <div v-if="showArea === ESTATE_FACILITY_CHIKET">
            <ShowBalancesheetEstateFacilityChicket></ShowBalancesheetEstateFacilityChicket>
        </div>

        <!-- その18資産の内訳(100万円を超える借入金) -->
        <div v-if="showArea === ESTATE_BORROWED">
            <ShowBalancesheetEstateBorrowed></ShowBalancesheetEstateBorrowed>
        </div>

        <!-- その19不動産の利用現況 -->
        <div v-if="showArea === REAL_ESTATE">
            <ShowBalancesheetRealEstate></ShowBalancesheetRealEstate>
        </div>

        <!-- その20宣誓書 -->
        <div v-if="showArea === OATH">
            <ShowBalancesheetOath></ShowBalancesheetOath>
        </div>

        <!-- その領収書を徴しがたかった支出 -->
        <div v-if="showArea === NOT_COLLECT_RECIPT">
            <ShowBalancesheetNotCollectRecipt></ShowBalancesheetNotCollectRecipt>
        </div>

        <!-- 振込明細書に書かる支出目的書 -->
        <div v-if="showArea === TRANSFER_STATEMENT">
            <ShowBalancesheetTransferState></ShowBalancesheetTransferState>
        </div>

    </div>
    <!-- ベースを操作禁止するレイヤ -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :search-dto="searchPoliticalOrganizationLeastCapsuleDto"
                @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
            </SearchPoliticalOrganization>
        </div>
    </div>

    <!-- 政治家検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticianAllLeast" class="overBackground"></div>
    <div v-if="isVisibleSearchPoliticianAllLeast">
        <div class="overComponent">
            <SearchPoliticianAll :search-dto="searchPoliticianAllLeastCapsuleDto"
                @send-cancel-search-politician-all="recieveCancelSearchPoliticianAllLeast"
                @send-politician-all-interface="recieveSearchPoliticianAllLeastInterface">
            </SearchPoliticianAll>

        </div>
    </div>

</template>
<style scoped></style>
