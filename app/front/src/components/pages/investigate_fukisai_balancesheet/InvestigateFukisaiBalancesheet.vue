<script setup lang="ts">
import { computed, Ref, ref } from "vue";
import SearchPoliticalOrganization from '../../common/search_political_organization/SearchPoliticalOrganization.vue';
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import getHoukokunen from "../../../dto/houkokunen/getHoukokunen";
import SearchBalancesheetFukisaiCapsuleInterface from "../../../dto/fukisai_balancesheet/searchBalancesheetFukisaiCapsuleDto";
import SearchBalancesheetFukisaiCapsuleDto from "../../../dto/fukisai_balancesheet/searchBalancesheetFukisaiCapsuleDto";
import TemplateWithTaskPlanInfoResultInterface from "../../../dto/common_check/templateWithTaskPlanInfoResultDto";
import TemplateWithTaskPlanInfoResultDto from "../../../dto/common_check/templateWithTaskPlanInfoResultDto";
import SearchWkTblFukisaiPagingCapsuleInterface from "../../../dto/fukisai_balancesheet/searchWkTblFukisaiPagingCapsuleDto";
import SearchWkTblFukisaiPagingCapsuleDto from "../../../dto/fukisai_balancesheet/searchWkTblFukisaiPagingCapsuleDto";
import SearchFukisaiBalancesheetResultInterface from "../../../dto/fukisai_balancesheet/searchFukisaiBalancesheetResultDto";
import SearchFukisaiBalancesheetResultDto from "../../../dto/fukisai_balancesheet/searchFukisaiBalancesheetResultDto";
import mockGetFukisaiMeisaiData from "./mock/mockGetFukisaiMeisaiData";
import getPagingOption from "../paging/getPagingOption";

// 作成条件Dto
const capsuleDto: Ref<SearchBalancesheetFukisaiCapsuleInterface> = ref(new SearchBalancesheetFukisaiCapsuleDto());
capsuleDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
capsuleDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
capsuleDto.value.checkTransactionDto = createCheckTransactionDto(true);
capsuleDto.value.fukisaiSearchConditionDto.houkokuNen = new Date().getFullYear() - 2;



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
    capsuleDto.value.fukisaiSearchConditionDto.poliOrgId = sendDto.politicalOrganizationId;
    capsuleDto.value.fukisaiSearchConditionDto.poliOrgCode = sendDto.politicalOrganizationCode;
    capsuleDto.value.fukisaiSearchConditionDto.poliOrgName = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

// 報告年リスト
const houkokunenList: Ref<SelectOptionInterface[]> = ref(getHoukokunen());





function onPreResearch() {
    alert("前回データ保存");
}
function onCancel() {
    alert("キャンセル");
}

// ラジオボタン設定
const searchCode: Ref<number> = ref(1);
const isSearchCode = computed(() => searchCode.value === 1);

// 作成条件を送った結果Dto    
const createReultDto: TemplateWithTaskPlanInfoResultInterface = new TemplateWithTaskPlanInfoResultDto();

function onCreateWkTbl() {
    capsuleDto.value.fukisaiSearchConditionDto.isSearchCode = isSearchCode.value;
    // TODO 作成作業

    // タスク計画を登録してBatchを非同期で動作した想定
    createReultDto.year = capsuleDto.value.fukisaiSearchConditionDto.houkokuNen;
    createReultDto.taskPlanCode = 250;
    createReultDto.message = "データ作成を実行しています…";

    alert(createReultDto.message);

    fukisaiResultDto.value.isOk = true;
}

// ページングDto
const searchWkTblDto: Ref<SearchWkTblFukisaiPagingCapsuleInterface> = ref(new SearchWkTblFukisaiPagingCapsuleDto());
searchWkTblDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchWkTblDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchWkTblDto.value.checkTransactionDto = createCheckTransactionDto(true);

// 作成後の実データ明細Dto
const fukisaiResultDto: Ref<SearchFukisaiBalancesheetResultInterface> = ref(new SearchFukisaiBalancesheetResultDto());
fukisaiResultDto.value.isOk = false;
fukisaiResultDto.value.message = "データ取得できませんでした";


const listPage:Ref<SelectOptionInterface[]> = ref([]);

function onSearch() {

    // 作成確認のために作成時に取得したタスク同一識別コードを送信
    searchWkTblDto.value.taskPlanCode = createReultDto.taskPlanCode;
    searchWkTblDto.value.year = createReultDto.year;

    // TODO 送信作業
    if (fukisaiResultDto.value.isOk) {
        fukisaiResultDto.value = mockGetFukisaiMeisaiData();
        listPage.value = getPagingOption(fukisaiResultDto.value.countAll,50);
        alert(fukisaiResultDto.value.message);
    } else {
        alert(fukisaiResultDto.value.message);
    }
}

function warningClass(isDiffer: boolean): string {

    if (isDiffer) {
        return "td_warning";
    } else {
        return "td_normal";
    }
}

</script>
<template>
    <h1>政治資金収支報告書不記載チェッカー</h1>

    <div class="one-line">
        抽出条件<br>
        ※前回調査分が残っている場合は削除されます<button @click="onPreResearch">保存</button>
    </div>
    <div class="clear-both"></div>


    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="capsuleDto.fukisaiSearchConditionDto.houkokuNen">
            <option v-for="option of houkokunenList" :key="option.value">{{ option.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="capsuleDto.fukisaiSearchConditionDto.poliOrgCode" disabled="true"
            class="code-input">
        <input type="text" v-model="capsuleDto.fukisaiSearchConditionDto.poliOrgName" disabled="true"
            class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索基準
    </div>
    <div class="right-area">
        <input type="radio" v-model="searchCode" :value="1">コード基準<span class="left-space"><input type="radio"
                v-model="searchCode" :value="2">原文書団体名基準</span>
    </div>
    <div class="clear-both"></div>



    <div class="left-area">
        データ抽出
    </div>
    <div class="right-area">
        <button @click="onCreateWkTbl">データ抽出</button>
    </div>
    <div class="clear-both"><br></div>


    <div class="one-line">
        抽出結果<span class="left-space"><button @click="onSearch">更新する</button></span>
    </div>
    <div class="clear-both"></div>

    <div class="one-line">
        <!-- ページング -->
        <select v-model="fukisaiResultDto.pageNumber">
            <option v-for="option in listPage" :key="option.value" :value="option.value"> {{ option.text }}</option>
        </select>

        <div v-for="groupDto, index in fukisaiResultDto.listTradingGroup" :key="index">
            {{ groupDto.politicalOrgName }} と {{ groupDto.partnerName }} の明細<br>
            <table>
                <tr>
                    <th>記載団体</th>
                    <th>発生日</th>
                    <th>様式区分</th>
                    <th>項目名</th>
                    <th>収入</th>
                    <th>支出</th>
                    <th>取引団体</th>
                </tr>

                <tr v-for="meisaiEntity in groupDto.listMeisai" :key="meisaiEntity.wkTblFukisaiBalancesheetId">
                    <td>({{ meisaiEntity.daihyouName }}) <br> {{ meisaiEntity.politicalOrganizationName }}</td>
                    <td>{{ meisaiEntity.accrualDate }} </td>
                    <td>{{ meisaiEntity.youshikiKbn }} </td>
                    <td>{{ meisaiEntity.itemName }}</td>
                    <td>{{ meisaiEntity.kingakuInput }}</td>
                    <td>{{ meisaiEntity.kingakuOutput }}</td>
                    <td>({{ meisaiEntity.partnerName }}) <br> {{ meisaiEntity.relationPoliticalOrgName }}</td>
                </tr>

                <tr>
                    <td :class="warningClass(groupDto.isKingakuDiffer)" colspan="4">&nbsp;</td>
                    <td :class="warningClass(groupDto.isKingakuDiffer)">{{ groupDto.sumIncome }}</td>
                    <td :class="warningClass(groupDto.isKingakuDiffer)">{{ groupDto.sumOutcome }}</td>
                    <td :class="warningClass(groupDto.isKingakuDiffer)">&nbsp;</td>
                </tr>
            </table>
            <br>
        </div>

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

td.td_warning {
    background-color: red;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
