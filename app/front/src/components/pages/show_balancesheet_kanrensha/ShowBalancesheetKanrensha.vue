<script setup lang="ts">
import { computed, Ref, ref } from "vue";
import SelectOptionInterface from '../../../dto/selectOptionDto';
import getHoukokunen from '../../../dto/houkokunen/getHoukokunen';
import SessionStorageCommonCheck from '../../../dto/common_check/sessionStorageCommonCheck';
import createCheckTransactionDto from '../../../dto/common_check/createCheckTransactionDto';
import KifuJougenConditionCapsuleInterface from "../../../dto/kifu_jougen/kifuJougenConditionCapsuleDto";
import KifuJougenConditionCapsuleDto from "../../../dto/kifu_jougen/kifuJougenConditionCapsuleDto";
import SearchKifuJougenMeisaiBalancesheetResultInterface from "../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import SearchKifuJougenMeisaiBalancesheetResultDto from "../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import SearchKanrensha from "../../common/search_kanrensha/SearchKanrensha.vue";
import SearchKanrenshaLeastCapsuleDto from "../../../dto/kanrensha/searchKanrenshaLeastCapsuleDto";
import KanrenshaLeastInterface from "../../../dto/kanrensha/kanrenshaLeastDto";
import KanrenshaLeastDto from "../../../dto/kanrensha/kanrenshaLeastDto";
import YoushikiKbnIncomeConstants from "../../../dto/balancesheet/youshikiKbnIncomeConstants";
import YoushikiEdaKbnIncomeConstants from "../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import YoushikiEdaKbnOutcomeConstants from "../../../dto/balancesheet/youshikiEdaKbnOutcomeConstants";
import KanrenshaBalancesheetConditionCapsuleInterface from "../../../dto/kanrensha/kanrenshaBalancesheetConditionCapsuleDto";
import KanrenshaBalancesheetConditionCapsuleDto from "../../../dto/kanrensha/kanrenshaBalancesheetConditionCapsuleDto";
import getPagingOption from "../paging/getPagingOption";
import IncomeAndOutcomeNaturalSearchResultInterface from "../../../dto/natural_search/incomeAndOutcomeNaturalSearchResultDto";
import IncomeAndOutcomeNaturalSearchResultDto from "../../../dto/natural_search/incomeAndOutcomeNaturalSearchResultDto";
import mockCreateSearchResultDto from "../natural_search/mock/mockCreateSearchResultDto";

//経年変化検索条件Dto
const kanrenshaBalancesheetCapsuleDto: Ref<KanrenshaBalancesheetConditionCapsuleInterface> = ref(new KanrenshaBalancesheetConditionCapsuleDto());
const listHoukokuNen: Ref<SelectOptionInterface[]> = ref(getHoukokunen());

// 上限表示用Dto
const searchResultDto: Ref<IncomeAndOutcomeNaturalSearchResultInterface> = ref(new IncomeAndOutcomeNaturalSearchResultDto());

// ラジオボタン設定
const searchCode: Ref<number> = ref(1);
const isSearchCode = computed(() => searchCode.value === 1);

//政治団体検索コンポーネント
const isVisibleSearchKanrenshaLeast: Ref<boolean> = ref(false);
const searchKanrenshaLeastCapsuleDto: SearchKanrenshaLeastCapsuleDto = new SearchKanrenshaLeastCapsuleDto();
searchKanrenshaLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchKanrenshaLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchKanrenshaLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

/**
 * 政治団体検索コンポーネント表示
 */
function onSearchPoliticalOrgnaization() {
    isVisibleSearchKanrenshaLeast.value = true;
}

/**
 * 政治団体検索キャンセル
 */
function recieveCancelSearchKanrenshaLeast() {
    //非表示
    isVisibleSearchKanrenshaLeast.value = false;
}

const kanrenshaLeastDto: Ref<KanrenshaLeastInterface> = ref(new KanrenshaLeastDto());
/**
 * 政治団体検索選択
 * @param sendDto 選択Dto
 */
function recieveKanrenshaLeastInterface(sendDto: KanrenshaLeastInterface) {

    kanrenshaLeastDto.value = sendDto;

    //政治団体を設定
    kanrenshaBalancesheetCapsuleDto.value.relationId = sendDto.relationId;
    kanrenshaBalancesheetCapsuleDto.value.relationCode = sendDto.relationCode;
    kanrenshaBalancesheetCapsuleDto.value.relationName = sendDto.relationName;

    //非表示
    isVisibleSearchKanrenshaLeast.value = false;
}

// Paging
const listPageIncome: Ref<SelectOptionInterface[]> = ref([]);
const listPageOutcome: Ref<SelectOptionInterface[]> = ref([]);
function onSearch() {
    alert("検索");
    kanrenshaBalancesheetCapsuleDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    kanrenshaBalancesheetCapsuleDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    kanrenshaBalancesheetCapsuleDto.value.checkTransactionDto = createCheckTransactionDto(true);
    kanrenshaBalancesheetCapsuleDto.value.isNameSearch = isSearchCode.value;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(収支報告書関連者基準検索)
    //const url: string = "http://localhost:9080/show-balancesheet/kanrensha"

    searchResultDto.value = mockCreateSearchResultDto();
    listPageIncome.value = getPagingOption(searchResultDto.value.countIncome, 50);
    listPageOutcome.value = getPagingOption(searchResultDto.value.countOutcome, 25);
}

function onCancel() {
    alert("キャンセル");
}

// 全チェック機能
const isAllDonate: Ref<boolean> = ref(false);
const isAllParty: Ref<boolean> = ref(false);
function onDonateAll() {
    kanrenshaBalancesheetCapsuleDto.value.isSearchDonatePerson = isAllDonate.value;
    kanrenshaBalancesheetCapsuleDto.value.isSearchDonateCorp = isAllDonate.value;
    kanrenshaBalancesheetCapsuleDto.value.isSearchDonatePoliOrg = isAllDonate.value;
}
function onPartyAll() {
    kanrenshaBalancesheetCapsuleDto.value.isSearchPartyPerson = isAllParty.value;
    kanrenshaBalancesheetCapsuleDto.value.isSearchPartyCorp = isAllParty.value;
    kanrenshaBalancesheetCapsuleDto.value.isSearchPartyPoliOrg = isAllParty.value;
}

</script>

<template>
    <h1>政治資金収支報告書(関連者基準)</h1>

    <div class="one-line">
        検索条件
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="kanrenshaBalancesheetCapsuleDto.houkokuNen">
            <option v-for="option of listHoukokuNen" :key="option.value">{{ option.text }}</option>
        </select>
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
        関連者
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="searchKanrenshaLeastCapsuleDto.isSearchPerson">個人<input type="checkbox"
            v-model="searchKanrenshaLeastCapsuleDto.isSearchCorp" class="left-space">企業・団体<input type="checkbox"
            v-model="searchKanrenshaLeastCapsuleDto.isSearchPoliOrg" class="left-space">政治団体
        <br>
        <input type="number" v-model="kanrenshaBalancesheetCapsuleDto.relationCode" disabled="true" class="code-input">
        <input type="text" v-model="kanrenshaBalancesheetCapsuleDto.relationName" disabled="true"
            class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">関連者検索</button>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        様式区分(収入)
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchJournal">{{
            YoushikiKbnIncomeConstants.JOURNAL_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchBorrowed" class="left-space">{{
            YoushikiKbnIncomeConstants.BORROWED_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchRelatedlgrants" class="left-space">{{
            YoushikiKbnIncomeConstants.RELATEDLGRANTS_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchOtherIncome" class="left-space">{{
            YoushikiKbnIncomeConstants.OTHER_TEXT }}
        <br>
        <input type="checkbox" v-model="isAllDonate" @change="onDonateAll">{{ YoushikiKbnIncomeConstants.DONATE_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchDonatePerson" class="left-space">{{
            YoushikiEdaKbnIncomeConstants.PERSONAL_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchDonateCorp" class="left-space">{{
            YoushikiEdaKbnIncomeConstants.CORPORATION_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchDonatePoliOrg" class="left-space">{{
            YoushikiEdaKbnIncomeConstants.POLITIC_ORG_TEXT }}
        <br>
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchDonateMediate">{{
            YoushikiKbnIncomeConstants.DONATE_MEDIATE_TEXT }}(すべて)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchAnonymus" class="left-space">{{
            YoushikiKbnIncomeConstants.ANONYMUS_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchSpecificParty" class="left-space">{{
            YoushikiKbnIncomeConstants.SPECIFIC_PARTY_TEXT }}
        <br>
        <input type="checkbox" v-model="isAllParty" @change="onPartyAll">{{ YoushikiKbnIncomeConstants.PARTY_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchPartyPerson" class="left-space">{{
            YoushikiEdaKbnIncomeConstants.PERSONAL_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchPartyCorp" class="left-space">{{
            YoushikiEdaKbnIncomeConstants.CORPORATION_TEXT }}
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchPartyPoliOrg" class="left-space">{{
            YoushikiEdaKbnIncomeConstants.POLITIC_ORG_TEXT }}
        <br>
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchPartyMediate">{{
            YoushikiKbnIncomeConstants.PARTY_MEDIATE_TEXT }}(すべて)

    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        様式区分(支出)
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchKounetsuhi">{{
            YoushikiEdaKbnOutcomeConstants.KOUNETSUHI_TEXT }}(様式14)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchShoumouhin" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.SHOUMOUHIN_TEXT }}(様式14)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchJimusho" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.JIMUSHO_TEXT }}(様式14)
        <br>
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchActivation">{{
            YoushikiEdaKbnOutcomeConstants.ACTIVATION_TEXT }}(様式15)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchElection" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.ELECTION_TEXT }}(様式15)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchPaper" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.PAPER_TEXT }}(様式15)
        <br>
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchComercial">{{
            YoushikiEdaKbnOutcomeConstants.COMERCIAL_TEXT }}(様式15)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchPartyOutcome" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.PARTY_TEXT }}(様式15)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchBuissiness" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.BUISSINESS_TEXT }}(様式15)
        <br>
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchResearch">{{
            YoushikiEdaKbnOutcomeConstants.RESEARCH_TEXT }}(様式15)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchDonation" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.DONATION_TEXT }}(様式15)
        <input type="checkbox" v-model="kanrenshaBalancesheetCapsuleDto.isSearchOtherOutcome" class="left-space">{{
            YoushikiEdaKbnOutcomeConstants.OTHER_TEXT }}(様式15)
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        収入確認結果<br>
        <!-- ページング -->
        <select v-model="searchResultDto.pageNumberIncome">
            <option v-for="option in listPageIncome" :key="option.value" :value="option.value"> {{ option.text }}
            </option>
        </select>
        <table>
            <tr>
                <th>関連者</th>
                <th>関連者住所</th>
                <th>発生日</th>
                <th>様式区分</th>
                <th>項目名</th>
                <th>金額(収入)</th>
                <th>政治団体</th>
                <th>代表者</th>
            </tr>

            <tr v-for="dto of searchResultDto.listIncome" :key="dto.itemId">
                <td>{{ dto.partnerName }}</td>
                <td>{{ dto.partnerJuusho }}</td>
                <td>{{ dto.accrualDate }}</td>
                <td>{{ YoushikiKbnIncomeConstants.convertText(dto.youshikiKbn) }}</td>
                <td>{{ dto.itemName }}</td>
                <td>{{ dto.kingakuIncomeText }}</td>
                <td>{{ dto.dantaiName }}</td>
                <td>{{ dto.daihyouName }}</td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>


    <div class="one-line">
        支出確認結果<br>
        <!-- ページング -->
        <select v-model="searchResultDto.pageNumberOutcome">
            <option v-for="option in listPageOutcome" :key="option.value" :value="option.value"> {{ option.text }}
            </option>
        </select>
        <table>
            <tr>
                <th>関連者</th>
                <th>関連者住所</th>
                <th>発生日</th>
                <th>様式区分</th>
                <th>項目名</th>
                <th>金額(収入)</th>
                <th>政治団体</th>
                <th>代表者</th>
            </tr>
            <tr v-for="dto of searchResultDto.listOutcome" :key="dto.itemId">
                <td>{{ dto.partnerName }}</td>
                <td>{{ dto.partnerJuusho }}</td>
                <td>{{ dto.accrualDate }}</td>
                <td>{{ YoushikiEdaKbnOutcomeConstants.convertText(dto.youshikiKbn, dto.youshikiEdaKbn) }}</td>
                <td>{{ dto.itemName }}</td>
                <td>{{ dto.kingakuOutcomeText }}</td>
                <td>{{ dto.dantaiName }}</td>
                <td>{{ dto.daihyouName }}</td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- 関連者検索コンポーネント -->
    <div v-if="isVisibleSearchKanrenshaLeast" class="overBackground"></div>
    <div v-if="isVisibleSearchKanrenshaLeast">
        <div class="overComponent">
            <SearchKanrensha :search-dto="searchKanrenshaLeastCapsuleDto"
                @send-cancel-search-kanrensha-least="recieveCancelSearchKanrenshaLeast"
                @send-kanrensha-least-interface="recieveKanrenshaLeastInterface">
            </SearchKanrensha>
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
