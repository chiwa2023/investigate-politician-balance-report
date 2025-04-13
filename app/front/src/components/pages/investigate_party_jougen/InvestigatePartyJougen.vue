<script setup lang="ts">
import { computed, Ref, ref } from "vue";
import SelectOptionInterface from '../../../dto/selectOptionDto';
import getHoukokunen from '../../../dto/houkokunen/getHoukokunen';
import SearchPoliticalOrganization from '../../common/search_political_organization/SearchPoliticalOrganization.vue';
import SearchPoliticalOrganizationLeastCapsuleDto from '../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto';
import SessionStorageCommonCheck from '../../../dto/common_check/sessionStorageCommonCheck';
import PoliticalOrganizationLeastInterface from '../../../dto/political_organization/politicalOrganizationLeastDto';
import PoliticalOrganizationLeastDto from '../../../dto/political_organization/politicalOrganizationLeastDto';
import createCheckTransactionDto from '../../../dto/common_check/createCheckTransactionDto';
import KifuJougenConditionCapsuleInterface from "../../../dto/kifu_jougen/kifuJougenConditionCapsuleDto";
import KifuJougenConditionCapsuleDto from "../../../dto/kifu_jougen/kifuJougenConditionCapsuleDto";
import SearchKifuJougenMeisaiBalancesheetResultInterface from "../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import SearchKifuJougenMeisaiBalancesheetResultDto from "../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import KobetsuKiseiMeisaiInterface from "../../../entity/kobetsuKiseiMeisaiEntity";
import mockGetPartyJougenData from "./mock/mockGetPartyJougenData";
import YoushikiEdaKbnIncomeConstants from "../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import getPagingOption from "../paging/getPagingOption";

//経年変化検索条件Dto
const partyJougenCapsuleDto: Ref<KifuJougenConditionCapsuleInterface> = ref(new KifuJougenConditionCapsuleDto());
const listHoukokuNen: Ref<SelectOptionInterface[]> = ref(getHoukokunen());

// 上限表示用Dto
const searchResultDto: Ref<SearchKifuJougenMeisaiBalancesheetResultInterface> = ref(new SearchKifuJougenMeisaiBalancesheetResultDto());

// 明細表示用Dto
const listMeisai: Ref<KobetsuKiseiMeisaiInterface[]> = ref([]);

// 政治団体検索コンポーネント
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
    partyJougenCapsuleDto.value.poliOrgId = sendDto.politicalOrganizationId;
    partyJougenCapsuleDto.value.poliOrgCode = sendDto.politicalOrganizationCode;
    partyJougenCapsuleDto.value.poliOrgName = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

// ラジオボタン設定
const searchCode: Ref<number> = ref(1);
const isSearchCode = computed(() => searchCode.value === 1);
// Paging
const listPage: Ref<SelectOptionInterface[]> = ref([]);
function onSearch() {
    alert("検索");
    partyJougenCapsuleDto.value.isNameSearch = isSearchCode.value;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(個別規制)
    //const urlKobetsu: string = "http://localhost:9080/party-jougen/search"

    searchResultDto.value = mockGetPartyJougenData();
    listPage.value = getPagingOption(searchResultDto.value.countAll, 25);
}

function onShowMeisai(index: number) {
    listMeisai.value = searchResultDto.value.listTradingGroup[index].listTradingMeisai;
}

function convertEdaKbnText(edKbn: number) {
    return YoushikiEdaKbnIncomeConstants.convertText(edKbn);
}
</script>
<template>
    <h1>パーティ参加費上限チェッカー</h1>

    <div class="one-line">
        検索条件
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="partyJougenCapsuleDto.houkokuNen">
            <option v-for="option of listHoukokuNen" :key="option.value">{{ option.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="partyJougenCapsuleDto.poliOrgCode" disabled="true" class="code-input">
        <input type="text" v-model="partyJougenCapsuleDto.poliOrgName" disabled="true" class="left-space text-input">
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
        検索
    </div>
    <div class="right-area">
        <span><button @click="onSearch">検索する</button></span>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        検索結果
    </div>

    <h3>検索結果(集計)</h3>
    <div class="one-line">
        <!-- ページング -->
        <select v-model="searchResultDto.pageNumber">
            <option v-for="option in listPage" :key="option.value" :value="option.value"> {{ option.text }}</option>
        </select>
        <table>
            <tr>
                <th>購入者区分</th>
                <th>購入者</th>
                <th>購入者住所</th>
                <th>合計金額</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="dto, index of searchResultDto.listTradingGroup" :key="index">
                <td>{{ convertEdaKbnText(dto.youshikiEdaKbn) }}</td>
                <td>({{ dto.relationCode }})<br>{{ dto.partnerName }}</td>
                <td>{{ dto.partnerAddress }}</td>
                <td>{{ dto.sumKifu }}</td>
                <td><button @click="onShowMeisai(index)">明細表示</button></td>
            </tr>

        </table>
    </div>
    <div class="clear-both"><br></div>

    <h3>検索結果(詳細)</h3>

    <table>
        <tr>
            <th>発生日</th>
            <th>政治団体名</th>
            <th>代表者</th>
            <th>項目</th>
            <th>金額</th>
            <th>収入を得た相手</th>
            <th>相手先住所</th>
        </tr>
        <tr v-for="(lineDto, index) in listMeisai" :key="index">
            <td>{{ lineDto.accrualDate }}</td>
            <td>{{ lineDto.dantaiName }}</td>
            <td>{{ lineDto.daihyouName }}</td>
            <td>{{ lineDto.itemName }}</td>
            <td>{{ lineDto.kingaku }}</td>
            <td>({{ lineDto.relationCode }}) <br> {{ lineDto.partnerName }}</td>
            <td>{{ lineDto.partnerJuusho }}</td>
        </tr>
    </table>

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
<style scoped></style>
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
