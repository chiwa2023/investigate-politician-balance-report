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
import CreateSummaryChartData from './createSummaryChartData';

// チャートデータ(初期)
const chartData: Ref<(string | number)[][]> = ref([
    ['', ''],
    ['', 0]
]);

// チャートオプション
const chartOptions = {
    chart: {
        title: '集計表項目毎経年変化',
    }
}
// グラフ表示選択
const datafunc = new CreateSummaryChartData();
const selectedChartItem: Ref<string> = ref("未選択");
const optionIncome:Ref<SelectOptionInterface[]> = ref(datafunc.createOptionIncome());
const optionOutcome:Ref<SelectOptionInterface[]> = ref(datafunc.createOptionOutcome());

// データを再描画する
function changeData() {
    chartData.value = datafunc.allData(selectedChartItem.value,listResult.value);
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
    <div class="left-area">
        収入総額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.shunyuGokei" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        前年からの繰越額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.zennenKurikoshi"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        本年の収入額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.honnenShunyu" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        支出総額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.shishutsuGoukei"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        翌年への繰越額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.yokunenKurikoshi"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        個人の党費または会費を納入金額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kojiFutanGoukei"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        党費または会費を納入した員数
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kojiFutanSuu" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        個人寄付の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kojiFutanSuu" disabled="true"
            class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kojinKifuBikou" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        特定寄付合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.tokuteiKifuGoukei"
            disabled="true" class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.tokuteiKifuBikou"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        法人寄付合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.houjinKifuGoukei"
            disabled="true" class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.houjinKifuBiko" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        政治団体寄付合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.seijiDantaiKifuGoukei"
            disabled="true" class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.seijiDantaiKifuBikou"
            disabled="true" class="text-input">
    </div>

    <div class="left-area">
        寄付小計合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kifuShoukeiGoukei"
            disabled="true" class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kifuShoukeiBikou"
            disabled="true" class="text-input">
    </div>

    <div class="left-area">
        寄付のうちあっせんによるもの
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.assenGoukei" disabled="true"
            class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.assenBikou" disabled="true"
            class="text-input">
    </div>

    <div class="left-area">
        政党匿名寄付の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.tokumeiKifuGoukei"
            disabled="true" class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.tokumeiKifuBikou"
            disabled="true" class="text-input">
    </div>

    <div class="left-area">
        寄付総合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kifuSoGoukei" disabled="true"
            class="text-input">
        <span class="left-space">備考</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kifuSoGoukeiBikou"
            disabled="true" class="text-input">
    </div>

    <div class="clear-both"><br></div>

    <div class="one-line">
        支出(様式7その13)
    </div>
    <div class="left-area">
        人件費
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiJinkenhi" disabled="true"
            class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuJinkenhi" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        光熱費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiKohnetsuhi"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuKohnetsuhi"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        備品項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiBihinhi" disabled="true"
            class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuBihinhi" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        事務所費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiJimushohi"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuJimushohi" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        経費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiKeihiShoukei"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuKeihiShoukei"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        組織活動費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiSoshikiKatsudouhi"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuSoshikiKatsudouhi"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        選挙活動費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiSenkyoKatsudou"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuSenkyoKatsudou"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        その他項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiSonota" disabled="true"
            class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuSonota" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        機関誌発行項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiHakkou" disabled="true"
            class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuHakkou" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        宣伝費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiSenden" disabled="true"
            class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuSenden" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        政治資金パーティ開催項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiKaisaiPty"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuKaisaiPty" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        その他事業費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiSonotaJigyou"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuSonotaJigyou"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        調査研究費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiChousaKenkyu"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuChousaKenkyu"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        寄付金項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiKifukin" disabled="true"
            class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuKifukin" disabled="true"
            class="text-input">
    </div>
    <div class="left-area">
        その他の経費項目の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiSonotaKeihi"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuSonotaKeihi"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        活動費小計の合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiKatsudouhi"
            disabled="true" class="text-input">
        <span class="left-space">交付金に係る支出</span>
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.kohfuKatsudouhi"
            disabled="true" class="text-input">
    </div>
    <div class="left-area">
        現計合計
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.offeringBalancesheet0702And0713SummaryEntity.goukeiZenGohkei"
            disabled="true" class="text-input">
    </div>

    <div class="clear-both"></div>

    <h3>項目ごと遷移(グラフ)</h3>
    <div class="left-area">
        描画項目
    </div>
    <div class="right-area">
        <select v-model="selectedChartItem" @change="changeData">
            <option>{{ CreateSummaryChartData.NO_SELECT }}</option>
            <optgroup label="収入">
                <option v-for="dto of optionIncome" :key="dto.value">{{ dto.text }}</option>
            </optgroup>
            <optgroup label="支出">
                <option v-for="dto of optionOutcome" :key="dto.value">{{ dto.text }}</option>
            </optgroup>
        </select>
    </div>
    <!-- グラフ -->
    <div class="one-line">
        <GChart type="ColumnChart" :data="chartData" :options="chartOptions" class="left-space" style="width:85%"></GChart>
    </div>
    <br>

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
