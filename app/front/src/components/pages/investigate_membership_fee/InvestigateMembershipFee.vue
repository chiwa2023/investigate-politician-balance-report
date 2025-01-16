<script setup lang="ts">
import { computed, Ref, ref } from "vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import getHoukokunen from "../../../dto/houkokunen/getHoukokunen";
import mockGetPoliParty from "./mock/mockGetPoliParty";
import MembershipFeeSearchCapsuleDto from "../../../dto/membership_fee/membershipFeeSearchCapsuleDto";
import FeeSummaryDto from "../../../dto/membership_fee/feeSummaryDto";
import mockGetFeeInsuDoubutsuList from "./mock/mockGetFeeInsuDoubutsuList";
import mockGetFeeInsuShokubutsuList from "./mock/mockGetFeeInsuShokubutsuList";
import SearchPoliOrgBalancesheetMembershipFeeSummaryResultInterface from "../../../dto/membership_fee/searchPoliOrgBalancesheetMembershipFeeSummaryResultDto";
import SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto from "../../../dto/membership_fee/searchPoliOrgBalancesheetMembershipFeeSummaryResultDto";
import getPagingOption from "../paging/getPagingOption";

//政治団体検索コンポーネント
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const searchPoliticalOrganizationLeastCapsuleDto: SearchPoliticalOrganizationLeastCapsuleDto = new SearchPoliticalOrganizationLeastCapsuleDto();
searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

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

const poliOrgLeastDto: Ref<PoliticalOrganizationLeastInterface> = ref(new PoliticalOrganizationLeastDto());
/**
 * 政治団体検索選択
 * @param sendDto 選択Dto
 */
function recievePoliticalOrganizationLeastInterface(sendDto: PoliticalOrganizationLeastInterface) {

    poliOrgLeastDto.value = sendDto;

    //政治団体を設定
    capsuleDto.value.searchConditionDto.politicalOrgnaizationId = sendDto.politicalOrganizationId;
    capsuleDto.value.searchConditionDto.politicalOrgnaizationCode = sendDto.politicalOrganizationCode;
    capsuleDto.value.searchConditionDto.politicalOrgnaizationName = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

// 報告年リスト
const houkokunenList: Ref<SelectOptionInterface[]> = ref(getHoukokunen());

// 政党リスト
const politicalPartyList: Ref<SelectOptionInterface[]> = ref(mockGetPoliParty());

// 月入力制御
const isMonthInput = computed(() => {
    if (capsuleDto.value.searchConditionDto.feeYear === 0) {
        capsuleDto.value.searchConditionDto.feeMonth = 1;
        return false;
    } else {
        return true;;
    }
});
// 年入力制御
const isYearInput = computed(() => {
    if (capsuleDto.value.searchConditionDto.feeMonth === 0) {
        capsuleDto.value.searchConditionDto.feeYear = 1;
        return false;
    } else {
        return true;;
    }
});

// 政治団体入力制御
const isPoliOrgInput = computed(() => {
    if (capsuleDto.value.searchConditionDto.poliPartyCode === "0") {
        return false;
    } else {
        return true;;
    }
});


// 検索条件
const capsuleDto: Ref<MembershipFeeSearchCapsuleDto> = ref(new MembershipFeeSearchCapsuleDto());
capsuleDto.value.searchConditionDto.houkokunen = 2022;
capsuleDto.value.searchConditionDto.poliPartyCode = "0";

function onCancel() {
    alert("キャンセル");
}

//const feeList: Ref<PoliOrgFeeInsuDto[]> = ref([]);

const resultDto: Ref<SearchPoliOrgBalancesheetMembershipFeeSummaryResultInterface> = ref(new SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto());

const listPage: Ref<SelectOptionInterface[]> = ref([]);

function onSearchList() {
    capsuleDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    capsuleDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

    // 下記UrlとMembershipFeeSearchCapsuleDtoでBack側アクセスする
    // const url:string = "/invest-membership-fee/search-summary"

    if (capsuleDto.value.searchConditionDto.poliPartyCode === "987") {

        resultDto.value.listSummary = mockGetFeeInsuShokubutsuList();
        resultDto.value.countAll = 452;
    } else {
        resultDto.value.listSummary = mockGetFeeInsuDoubutsuList();
        resultDto.value.countAll = 190;
    }

    listPage.value = getPagingOption(resultDto.value.countAll, 100);
}

const feeSummaryDto: Ref<FeeSummaryDto> = ref(new FeeSummaryDto());
function onSearchSummary() {
    let fee = 0;
    let insu = 0;

    // 下記Urlと(Back側CalcMebershipFeeInsuCapsuleDto)でBack側アクセスする
    // const url:string = "/invest-membership-fee/calc-sum"

    if (resultDto.value.listSummary.length) {
        for (const dto of resultDto.value.listSummary) {
            fee += dto.fee;
            insu += dto.insu;
        }
    } else {
        fee = 12345;
        insu = 77;

    }

    feeSummaryDto.value.sumFee = fee;
    feeSummaryDto.value.sumInsu = insu;
}

// 5万円以上で警告する
function cellAlert(average: number): string {
    if (average > 50000) {
        return "alert";
    } else {
        return "";
    }
}
</script>
<template>
    <h1>党費チェッカー</h1>

    <div class="one-line">
        抽出条件<br>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="capsuleDto.searchConditionDto.houkokunen">
            <option v-for="option of houkokunenList" :key="option.value">{{ option.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政党
    </div>
    <div class="right-area">
        <select v-model="capsuleDto.searchConditionDto.poliPartyCode">
            <option v-for="poliOption of politicalPartyList" :key="poliOption.value" :value="poliOption.value">{{
                poliOption.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        党費
    </div>
    <div class="right-area">
        月額：<input type="number" v-model="capsuleDto.searchConditionDto.feeMonth" class="code-input"
            :disabled="isMonthInput">
        年額(換算)：<input type="number" v-model="capsuleDto.searchConditionDto.feeYear" class="code-input"
            :disabled="isYearInput">
        <span class="left-space">0入力で月額／年額(換算)入替え</span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        水準
    </div>
    <div class="right-area">
        <span>注意：党費の<input type="number" v-model="capsuleDto.searchConditionDto.levelAttention"
                class="code-input">倍</span>
        <span class="left-space">警告：党費の<input type="number" v-model="capsuleDto.searchConditionDto.levelWarning"
                class="code-input">倍</span>
        <span class="left-space">※テストデータでは有効ではない</span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="capsuleDto.searchConditionDto.politicalOrgnaizationCode" disabled="true"
            class="code-input">
        <input type="text" v-model="capsuleDto.searchConditionDto.politicalOrgnaizationName" disabled="true"
            class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization" :disabled="isPoliOrgInput">政治団体検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearchList">党所属政治団体一覧</button>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        党集計<br>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        全体計
    </div>
    <div class="right-area">
        党費 {{ feeSummaryDto.sumFee }} <span class="left-space">
            会員数 {{ feeSummaryDto.sumInsu }} </span>
        <span class="left-space"><button @click="onSearchSummary">集計</button> </span>
    </div>
    <div class="clear-both"></div>

    <div class="one-line">
        確認結果<br>

        <!-- ページング -->
        <select v-model="resultDto.posPage">
            <option v-for="option in listPage" :key="option.value" :value="option.value"> {{ option.text }}</option>
        </select>

        <table>
            <tr>
                <th>判定</th>
                <th>政治団体</th>
                <th>党費</th>
                <th>員数</th>
                <th>頭割り</th>
                <th>代表者</th>
            </tr>
            <tr v-for="row of resultDto.listSummary" :key="row.politicalOrgnaizationId">
                <td>{{ row.rating }}</td>
                <td>{{ row.politicalOrgnaizationCode }}<br>{{ row.politicalOrgnaizationName }}</td>
                <td>{{ row.fee }}</td>
                <td>{{ row.insu }}</td>
                <td :class="cellAlert(row.average)">{{ row.average }}</td>
                <td>{{ row.poliOrgDaihyoushaCode }}<br> {{ row.poliOrgDaihyoushaName }} </td>
            </tr>
        </table>
        ※住所・名前なしで個人が寄付できる上限が50,000円のため、超えたときは赤セルで警告する
    </div>
    <div class="clear-both"></div>



    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :search-dto="searchPoliticalOrganizationLeastCapsuleDto"
                @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
            </SearchPoliticalOrganization>
        </div>
    </div>



</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

td.alert {
    background-color: red;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
