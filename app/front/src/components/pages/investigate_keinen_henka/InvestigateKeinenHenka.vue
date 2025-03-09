<script setup lang="ts">
import { GChart } from 'vue-google-charts';
import { computed, Ref, ref } from "vue";
import KeinenHenkaConditionCapsuleInterface from '../../../dto/keinen_henka/keinenHenkaConditionCapsuleDto';
import KeinenHenkaConditionCapsuleDto from '../../../dto/keinen_henka/keinenHenkaConditionCapsuleDto';
import SearchPoliticalOrganization from '../../common/search_political_organization/SearchPoliticalOrganization.vue';
import SearchPoliticalOrganizationLeastCapsuleDto from '../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto';
import SessionStorageCommonCheck from '../../../dto/common_check/sessionStorageCommonCheck';
import createCheckTransactionDto from '../../../dto/common_check/createCheckTransactionDto';
import PoliticalOrganizationLeastInterface from '../../../dto/political_organization/politicalOrganizationLeastDto';
import PoliticalOrganizationLeastDto from '../../../dto/political_organization/politicalOrganizationLeastDto';
import SelectOptionInterface from '../../../dto/selectOptionDto';
import SelectOptionDto from '../../../dto/selectOptionDto';
import getHoukokunen from '../../../dto/houkokunen/getHoukokunen';
import KeinenHenkaSurfaceAndSummaryByYearInterface from '../../../dto/keinen_henka/keinenHenkaSurfaceAndSummaryByYearDto';
import KeinenHenkaSurfaceAndSummaryByYearDto from '../../../dto/keinen_henka/keinenHenkaSurfaceAndSummaryByYearDto';
import mockGetKeinenHenkaData from './mock/mockGetKeinenHenkaData';

// チャートオプション
const chartData = ref([
    ['Year', 'Sales', 'Expenses', 'Profit'],
    ['2014', 1000, 400, 200],
    ['2015', 1170, 460, 250],
    ['2016', 660, 1120, 300],
    ['2017', 1030, 540, 350]
]);

// チャートオプション
const chartOptions = {
    chart: {
        title: 'Company Performance',
        subtitle: 'Sales, Expenses, and Profit: 2014-2017',
    }
}

function changeData() {
    chartData.value = [
        ['Year', 'Sales', 'Expenses', 'Profit'],
        ['2020', 2000, 1400, 200],
        ['2021', 2170, 1460, 250],
        ['2022', 2660, 3120, 300],
        ['2023', 1030, 1540, 350]
    ]
}

//経年変化検索条件Dio
const keinenHenkaCapsuleDto: Ref<KeinenHenkaConditionCapsuleInterface> = ref(new KeinenHenkaConditionCapsuleDto());

// 検索報告年リスト
const listHoukokuNenStart: Ref<SelectOptionInterface[]> = ref(getHoukokunen());
const listHoukokuNenEnd: Ref<SelectOptionInterface[]> = ref(getHoukokunen());



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
    keinenHenkaCapsuleDto.value.poliOrgId = sendDto.politicalOrganizationId;
    keinenHenkaCapsuleDto.value.poliOrgCode = sendDto.politicalOrganizationCode;
    keinenHenkaCapsuleDto.value.poliOrgName = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}


// 検索結果
const listResult: Ref<KeinenHenkaSurfaceAndSummaryByYearInterface[]> = ref([]);
const dtoView: Ref<KeinenHenkaSurfaceAndSummaryByYearInterface> = ref(new KeinenHenkaSurfaceAndSummaryByYearDto());
const yearView: Ref<string> = ref("");

const viewOfferingDate = computed(() => dtoView.value.offeringBalancesheet01And20SurfaceEntity.offeringDate.getFullYear()
    + "-" + dtoView.value.offeringBalancesheet01And20SurfaceEntity.offeringDate.getMonth()
    + "-" + dtoView.value.offeringBalancesheet01And20SurfaceEntity.offeringDate.getDate());
const viewDaihyou = computed(() => dtoView.value.offeringBalancesheet01And20SurfaceEntity.daihyoushaNameFirst + "　"
    + dtoView.value.offeringBalancesheet01And20SurfaceEntity.daihyoushaNameLast);
const viewKaikei = computed(() => dtoView.value.offeringBalancesheet01And20SurfaceEntity.kaikeiSekininshaNameFirst + "　"
    + dtoView.value.offeringBalancesheet01And20SurfaceEntity.kaikeiSekininshaNameLast);

// 検索報告年リスト
const listHoukokuNenView: Ref<SelectOptionInterface[]> = ref([]);

function onSearch() {
    //リスト取得
    listResult.value = mockGetKeinenHenkaData(keinenHenkaCapsuleDto.value.houkokuNenStart, keinenHenkaCapsuleDto.value.houkokuNenEnd);

    //開始年・終了年に合わせて表示年を準備
    for (let year = keinenHenkaCapsuleDto.value.houkokuNenStart; year <= keinenHenkaCapsuleDto.value.houkokuNenEnd; year++) {
        listHoukokuNenView.value.push(createYearOption(year));
    }
    yearView.value = keinenHenkaCapsuleDto.value.houkokuNenStart + "";
    onChangeViewYear();
}

function createYearOption(year: number): SelectOptionInterface {
    const yearText: string = year + "";
    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = yearText;
    dto.text = yearText;
    return dto;
}

function onChangeViewYear() {
    // 選択されている報告年のデータに切り替える
    dtoView.value = listResult.value.filter(e => e.houkokuNen == parseInt(yearView.value))[0];
}

</script>
<template>
    <h1>経年変化</h1>

    <div class="one-line">
        検索条件
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="keinenHenkaCapsuleDto.houkokuNenStart">
            <option v-for="option of listHoukokuNenStart" :key="option.value">{{ option.text }}</option>
        </select>
        <span class="left-space">から</span>
        <select class="left-space" v-model="keinenHenkaCapsuleDto.houkokuNenEnd">
            <option v-for="option of listHoukokuNenEnd" :key="option.value">{{ option.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="keinenHenkaCapsuleDto.poliOrgCode" disabled="true" class="code-input">
        <input type="text" v-model="keinenHenkaCapsuleDto.poliOrgName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button>
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
    <div class="clear-both"></div>
    <div class="left-area">
        表示報告年
    </div>
    <div class="right-area">
        <select v-model="yearView" @change="onChangeViewYear">
            <option v-for="option of listHoukokuNenView" :key="option.value">{{ option.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet01And20SurfaceEntity.dantaiNameKana" disabled="true"
            class="text-input"><br>
        <input type="text" v-model="dtoView.offeringBalancesheet01And20SurfaceEntity.dantaiName" disabled="true"
            class="text-input">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        文書提出日
    </div>
    <div class="right-area">
        <input type="textr" v-model="viewOfferingDate" disabled="true" class="code-input">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        役員
    </div>
    <div class="right-area">
        <input type="text" v-model="viewDaihyou" disabled="true" class="text-input"><br>
        <input type="text" v-model="viewKaikei" disabled="true" class="text-input">
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        収入(様式7その2)
    </div>
    <div class="clear-both"></div>

    <div class="one-line">
        収入(様式7その13)
    </div>
    <div class="clear-both"></div>


    <h3>グラフ導入動作確認</h3>
    <GChart type="ColumnChart" :data="chartData" :options="chartOptions"></GChart>
    <br>
    <button @click="changeData">再描画</button>


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
