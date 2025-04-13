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
import CreateSummaryChartData from './createSummaryChartData';
import mockGetKeinenUsageData from './mock/mockGetKeinenUsageData';
import keinenUsageSurface02103SummaryByYearInterface from '../../../dto/keinen_henka/keinenUsageSurface02103SummaryByYearDto';
import keinenUsageSurface02103SummaryByYearDto from '../../../dto/keinen_henka/keinenUsageSurface02103SummaryByYearDto';
import CreateUsageSummaryChartData from './CreateUsageSummaryChartData';

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
const datafunc = new CreateUsageSummaryChartData();
const selectedChartItem: Ref<string> = ref("未選択");
const optionIncome: Ref<SelectOptionInterface[]> = ref(datafunc.createOptionIncome());
const optionOutcome: Ref<SelectOptionInterface[]> = ref(datafunc.createOptionOutcome());

// データを再描画する
function changeData() {
    chartData.value = datafunc.allData(selectedChartItem.value, listResult.value);
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
const listResult: Ref<keinenUsageSurface02103SummaryByYearInterface[]> = ref([]);
const dtoView: Ref<keinenUsageSurface02103SummaryByYearInterface> = ref(new keinenUsageSurface02103SummaryByYearDto());
const yearView: Ref<string> = ref("");

const viewOfferingDate = computed(() => dtoView.value.surfaceEntity.offeringDate.toLocaleDateString("JPN"));
const viewDaihyou = computed(() => dtoView.value.surfaceEntity.daihyouName);
const viewKaikei = computed(() => dtoView.value.surfaceEntity.accountManagerName);

// 検索報告年リスト
const listHoukokuNenView: Ref<SelectOptionInterface[]> = ref([]);

function onSearch() {
    //リスト取得
    listResult.value = mockGetKeinenUsageData(keinenHenkaCapsuleDto.value.houkokuNenStart, keinenHenkaCapsuleDto.value.houkokuNenEnd);

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
        <input type="text" v-model="dtoView.surfaceEntity.dantaiName" disabled="true" class="text-input"><br>
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
        収入(様式9その2)
    </div>

    <div class="left-area">
        (1)政党交付金(支部政党交付金)の総額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.partyAllKoufukin" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        (2)前年末政党基金(支部基金)残高
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.lastYearRest" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        (3)政党交付金(支部政党交付金)による支出総額(4)+(5)
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.outcomeKoufukinAll" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        (4)政党交付金(支部政党交付金)支出充当総額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.outcomeKoufukin" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        (5)政党基金(支部基金)支出充当総額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.outcomeShibuKikin" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        (6)政党基金(支部基金)の積み立てに充てるために取り崩した政党基金(支部基金)の額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.withdrawalFunds" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        (7)政党基金(支部基金)の積み立て総額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.accumulateFunds" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政党基金(支部基金)の運用により収受した果実の総額
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.fundsSumGain" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        (8)本年末政党支部基金(支部基金)残高(2)-(5)-(6)+(7)
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.thisYearRest" disabled="true" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        備考
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoView.summaryEntity.bikouDiffer" disabled="true" class="text-input">
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        支出(様式8その3)
    </div>

    <div class="one-line">

        <table>
            <tr>
                <th style="width: 30%;">
                    項目
                </th>
                <th>
                    全金額
                </th>
                <th>
                    政党交付金充当額
                </th>
                <th>
                    政党基金充当額
                </th>
                <th>
                    備考
                </th>
            </tr>



            <tr>
                <td>
                    全政治活動
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllActionAll" disabled="true"
                        class="text-input">

                </td>
                <td>
                   <input type="text" v-model="dtoView.summaryEntity.totalAllActionJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllActionJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllActionBikou" disabled="true"
                        class="text-input">
                    </td>
            </tr>
            <tr>
                <td>
                    全総計
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllAmountAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllAmountJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllAmountJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllAmountBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    全事業総計
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllJigyouAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllJigyouJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllJigyouJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalAllJigyouBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    備品消耗品費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalBihinAll" disabled="true" class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalBihinJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalBihinJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalBihinBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    調査研究費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalChousaAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalChousaJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalChousaJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalChousaBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    事務所費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJimushoAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJimushoJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJimushoJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJimushoBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    人件費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJinkenhiAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJinkenhiJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJinkenhiJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalJinkenhiBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    経費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKeihiAll" disabled="true" class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKeihiJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKeihiJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKeihiBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    寄附
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKifuAll" disabled="true" class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKifuJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKifuJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKifuBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    機関誌発行
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKikanshiAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKikanshiJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKikanshiJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKikanshiBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    光熱費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKounetsuhiAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKounetsuhiJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKounetsuhiJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalKounetsuhiBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    パーティ費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalPartyAll" disabled="true" class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalPartyJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalPartyJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalPartyBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    宣伝費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSendenAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSendenJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSendenJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSendenBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    選挙費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSenkyoAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSenkyoJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSenkyoJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSenkyoBikou" disabled="true"
                       class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    支部交付金
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalShibuKoufuAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalShibuKoufuJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalShibuKoufuJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalShibuKoufuBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    その他事業費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaJigyouAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaJigyouJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaJigyouJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaJigyouBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    その他経費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaKeihiAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaKeihiJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaKeihiJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSonotaKeihiBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
            <tr>
                <td>
                    組織費
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSoshikiAll" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSoshikiJutoKoufukin" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSoshikiJutoMyFunds" disabled="true"
                        class="text-input">
                </td>
                <td>
                    <input type="text" v-model="dtoView.summaryEntity.totalSoshikiBikou" disabled="true"
                        class="text-input">
                </td>
            </tr>
        </table>
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
        <GChart type="ColumnChart" :data="chartData" :options="chartOptions" class="left-space" style="width:85%">
        </GChart>
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
