<script setup lang="ts">
import { computed, ref, Ref, WritableComputedRef } from "vue";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SearchPoliticalPartyLeastCapsuleDto from "../../../dto/political_party/searchPoliticalPartyLeastCapsuleDto";
import SearchPoliticalParty from "../../common/search_political_party/SearchPoliticalParty.vue";
import PoliticalPartyLeastInterface from "../../../dto/political_party/politicalPartyLeastDto";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import mockGetPartyUsageReportList from "./mock/mockGetPartyUsageReportList";
import ShowUsageBalanceOverview from "../../common/party_usgae_report/02balance_overview/ShowUsageBalanceOverview.vue";
import ShowUsageCoverAll from "../../common/party_usgae_report/01cover/ShowUsageCoverAll.vue";
import ShowUsageTransferStatement from "../../common/party_usgae_report/19transfer_statement/ShowUsageTransferStatement.vue";
import ShowUsageNotCorrectRecipt from "../../common/party_usgae_report/18not_correct_reciept/ShowUsageNotCorrectRecipt.vue";
import ShowUsageOath from "../../common/party_usgae_report/17oath/ShowUsageOath.vue";
import ShowUsagePartyFunds from "../../common/party_usgae_report/15party_funds/ShowUsagePartyFunds.vue";
import ShowUsageSubsidyDetail from "../../common/party_usgae_report/16subsidy_detail/ShowUsageSubsidyDetail.vue";
import ShowUsageCostOther from "../../common/party_usgae_report/13cost_other/ShowUsageCostOther.vue";
import ShowUsageDonation from "../../common/party_usgae_report/14donation/ShowUsageDonation.vue";
import ShowUsagePartOpen from "../../common/party_usgae_report/11party_open/ShowUsagePartOpen.vue";
import ShowUsageCommercial from "../../common/party_usgae_report/10commercial/ShowUsageCommercial.vue";
import ShowUsageMagazine from "../../common/party_usgae_report/09magazine/ShowUsageMagazine.vue";
import ShowUsageElection from "../../common/party_usgae_report/08election/ShowUsageElection.vue";
import ShowUsageOrganization from "../../common/party_usgae_report/07organization/ShowUsageOrganization.vue";
import ShowUsageOffice from "../../common/party_usgae_report/06office/ShowUsageOffice.vue";
import ShowUsageEquipment from "../../common/party_usgae_report/05equipmennt/ShowUsageEquipment.vue";
import ShowUsageSubsidyBranch from "../../common/party_usgae_report/04subsidy_branch/ShowUsageSubsidyBranch.vue";
import ShowUsageCostDetail from "../../common/party_usgae_report/03cost_detail/ShowUsageCostDetail.vue";
import ShowUsageBuissinessOther from "../../common/party_usgae_report/12buissiness_other/ShowUsageBuissinessOther.vue";


//政治家検索コンポーネント
const isVisibleSearchPoliticalPartyLeast: Ref<boolean> = ref(false);
const searchPoliticalPartyLeastCapsuleDto: SearchPoliticalPartyLeastCapsuleDto = new SearchPoliticalPartyLeastCapsuleDto();
searchPoliticalPartyLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticalPartyLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticalPartyLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない


const politicalPartyId: Ref<number> = ref(0);
const politicalPartyCode: Ref<number> = ref(0);
const politicalPartyName: Ref<string> = ref("");

/**
 * 政治家検索コンポーネント表示
 */
function onSearchPoliticalPartyLeast() {
    isVisibleSearchPoliticalPartyLeast.value = true;
}

/**
 * 政治家検索キャンセル
 */
function recieveCancelSearchPoliticalPartyLeast() {
    //非表示
    isVisibleSearchPoliticalPartyLeast.value = false;
}

/**
 * 政治家検索選択
 * @param sendDto 選択Dto
 */
function recieveSearchPoliticalPartyLeastInterface(sendDto: PoliticalPartyLeastInterface) {
    politicalPartyId.value = sendDto.politicalPartyId;
    politicalPartyCode.value = sendDto.politicalPartyCode;
    politicalPartyName.value = sendDto.politicalPartyName;

    listOfferingYear.value = mockGetPartyUsageReportList();

    //非表示
    isVisibleSearchPoliticalPartyLeast.value = false;
}

//提出年・提出日
const selectedOfferingYear: Ref<string> = ref("");
const listOfferingYear: Ref<SelectOptionInterface[]> = ref([]);


//提出年選択
const isEnableOfferingYear: WritableComputedRef<boolean> = computed(() => {
    if (politicalPartyId.value === 0) {
        return true;
    } else {
        return false;

    }
});

//領域表示選択
const isEanableShowAreaSelect: WritableComputedRef<boolean> = computed(() => {
    if (selectedOfferingYear.value === "") {
        return true;
    } else {
        return false;

    }
});


//表示領域
const showArea: Ref<string> = ref("");


//表紙(その1)
const COVER_ALL: string = "1";
//収支の状況(その2)
const BALNCE_OVERVIEW: string = "2";
//支出項目別の内訳(その3)
const COST_DETAIL: string = "3";
//支部政党交付金
const SUBSIDY_BRANCH: string = "4";
//備品
const EQUIPMENT: string = "5";
//事務所
const OFFICE: string = "6";
//組織
const ORGANIZATION: string = "7";
//選挙
const ELECTION: string = "8";
//機関誌
const MAGAGINE: string = "9";
//宣伝
const COMMERCIAL: string = "10";
//パーティ
const PARTY_OPEN: string = "11";
//その他事業
const BUSSINESS_OTHER: string = "12";
//寄附
const DONATION: string = "13";
//その他の経費
const COST_OTHER: string = "14";
//支部政党交付金の内訳
const SUBSIDY_DETAIL: string = "15";
//政党基金の内訳
const PARTY_FUNDS: string = "16";
//宣誓書
const OATH: string = "17";
//領収書を徴しがたかった支出の明細書
const NOT_COLLECT_RECIPT: string = "18";
//振込明細書に掛かる支出の目的書
const TRANSFER_STATEMENT: string = "19";

</script>
<template>
    <h1>政党交付金使途報告書照会</h1>

    <div class="left-area">
        政党
    </div>
    <div class="right-area">
        <input type="number" v-model="politicalPartyCode" disabled="true" class="code-input">
        <input type="text" v-model="politicalPartyName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalPartyLeast">政党検索</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        提出年・提出日
    </div>
    <div class="right-area">
        <select v-model="selectedOfferingYear" :disabled="isEnableOfferingYear">
            <option v-for="(dto) in listOfferingYear" :key="dto.value">{{ dto.text }}</option>
        </select>
    </div>
    <div class="clear-both" />


    <div class="one-line">
        {{ selectedOfferingYear }}分を表示しています
        <select v-model="showArea" class="left-space" :disabled="isEanableShowAreaSelect">
            <option :value="COVER_ALL"> 表紙(その1)</option>
            <option :value="BALNCE_OVERVIEW">収支の状況(その2)</option>
            <option :value="COST_DETAIL">支出項目別の内訳(その3)</option>
            <option :value="SUBSIDY_BRANCH">支部政党交付金</option>
            <option :value="EQUIPMENT">備品</option>
            <option :value="OFFICE">事務所</option>
            <option :value="ORGANIZATION">組織</option>
            <option :value="ELECTION">選挙</option>
            <option :value="MAGAGINE">機関誌</option>
            <option :value="COMMERCIAL">宣伝</option>
            <option :value="PARTY_OPEN">パーティ</option>
            <option :value="BUSSINESS_OTHER">その他事業</option>
            <option :value="DONATION">寄附</option>
            <option :value="COST_OTHER">その他の経費</option>
            <option :value="SUBSIDY_DETAIL">支部政党交付金の内訳</option>
            <option :value="PARTY_FUNDS">政党基金の内訳</option>
            <option :value="OATH">宣誓書</option>
            <option :value="NOT_COLLECT_RECIPT">領収書を徴しがたかった支出の明細書</option>
            <option :value="TRANSFER_STATEMENT"> 振込明細書に掛かる支出の目的書</option>
        </select>
        <br>
        <!-- 表紙(その1) -->
        <div v-if="showArea === COVER_ALL">
            <ShowUsageCoverAll></ShowUsageCoverAll>
        </div>
        <!-- 収支の状況(その2) -->
        <div v-if="showArea === BALNCE_OVERVIEW">
            <ShowUsageBalanceOverview></ShowUsageBalanceOverview>
        </div>
        <!-- 支出項目別の内訳(その3) -->
        <div v-if="showArea === COST_DETAIL">
            <ShowUsageCostDetail></ShowUsageCostDetail>
        </div>
        <!-- 支部政党交付金 -->
        <div v-if="showArea === SUBSIDY_BRANCH">
            <ShowUsageSubsidyBranch></ShowUsageSubsidyBranch>
        </div>
        <!-- 備品 -->
        <div v-if="showArea === EQUIPMENT">
            <ShowUsageEquipment></ShowUsageEquipment>
        </div>
        <!-- 事務所 -->
        <div v-if="showArea === OFFICE">
            <ShowUsageOffice></ShowUsageOffice>
        </div>
        <!-- 組織 -->
        <div v-if="showArea === ORGANIZATION">
            <ShowUsageOrganization></ShowUsageOrganization>
        </div>
        <!-- 選挙 -->
        <div v-if="showArea === ELECTION">
            <ShowUsageElection></ShowUsageElection>
        </div>
        <!-- 機関誌 -->
        <div v-if="showArea === MAGAGINE">
            <ShowUsageMagazine></ShowUsageMagazine>
        </div>
        <!-- 宣伝 -->
        <div v-if="showArea === COMMERCIAL">
            <ShowUsageCommercial></ShowUsageCommercial>
        </div>
        <!-- パーティ -->
        <div v-if="showArea === PARTY_OPEN">
            <ShowUsagePartOpen></ShowUsagePartOpen>
        </div>
        <!-- その他事業 -->
        <div v-if="showArea === BUSSINESS_OTHER">
            <ShowUsageBuissinessOther></ShowUsageBuissinessOther>
        </div>
        <!-- 寄附 -->
        <div v-if="showArea === DONATION">
            <ShowUsageDonation></ShowUsageDonation>
        </div>
        <!-- その他の経費 -->
        <div v-if="showArea === COST_OTHER">
            <ShowUsageCostOther></ShowUsageCostOther>
        </div>
        <!-- 支部政党交付金の内訳 -->
        <div v-if="showArea === SUBSIDY_DETAIL">
            <ShowUsageSubsidyDetail></ShowUsageSubsidyDetail>
        </div>
        <!-- 政党基金の内訳 -->
        <div v-if="showArea === PARTY_FUNDS">
            <ShowUsagePartyFunds></ShowUsagePartyFunds>
        </div>
        <!-- 宣誓書 -->
        <div v-if="showArea === OATH">
            <ShowUsageOath></ShowUsageOath>
        </div>
        <!-- 領収書を徴しがたかった支出の明細書 -->
        <div v-if="showArea === NOT_COLLECT_RECIPT">
            <ShowUsageNotCorrectRecipt></ShowUsageNotCorrectRecipt>
        </div>
        <!-- 振込明細書に掛かる支出の目的書 -->
        <div v-if="showArea === TRANSFER_STATEMENT">
            <ShowUsageTransferStatement></ShowUsageTransferStatement>
        </div>
    </div>

    <!-- 政治家検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalPartyLeast" class="overBackground"></div>
    <div v-if="isVisibleSearchPoliticalPartyLeast">
        <div class="overComponent">
            <SearchPoliticalParty :search-dto="searchPoliticalPartyLeastCapsuleDto"
                @send-cancel-search-political-party="recieveCancelSearchPoliticalPartyLeast"
                @send-political-party-interface="recieveSearchPoliticalPartyLeastInterface">
            </SearchPoliticalParty>

        </div>
    </div>

</template>
<style scoped></style>
