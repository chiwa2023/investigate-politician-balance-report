<script setup lang="ts">
import { Ref, ref } from "vue";
import SelectOptionInterface from '../../../dto/selectOptionDto';
import getHoukokunen from '../../../dto/houkokunen/getHoukokunen';
import SearchPoliticalOrganization from '../../common/search_political_organization/SearchPoliticalOrganization.vue';
import SearchPoliticalOrganizationLeastCapsuleDto from '../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto';
import SessionStorageCommonCheck from '../../../dto/common_check/sessionStorageCommonCheck';
import PoliticalOrganizationLeastInterface from '../../../dto/political_organization/politicalOrganizationLeastDto';
import PoliticalOrganizationLeastDto from '../../../dto/political_organization/politicalOrganizationLeastDto';
import createCheckTransactionDto from '../../../dto/common_check/createCheckTransactionDto';
import SearchDocumentCodeByOfferingCapsuleInterface from "../../../dto/renketsu_koufukin/searchDocumentCodeByOfferingCapsuleDto";
import SearchDocumentCodeByOfferingCapsuleDto from "../../../dto/renketsu_koufukin/searchDocumentCodeByOfferingCapsuleDto";
import RenketsuKofukinDocumentCodeOptionResultInterface from "../../../dto/renketsu_koufukin/renketsuKofukinDocumentCodeOptionResultDto";
import RenketsuKofukinDocumentCodeOptionResultDto from "../../../dto/renketsu_koufukin/renketsuKofukinDocumentCodeOptionResultDto";
import mockGetDocumentCode from "./mock/mockGetDocumentCode";
import CreateRenketsuKoufukinCapsuleInterface from "../../../dto/renketsu_koufukin/createRenketsuKoufukinCapsuleDto";
import CreateRenketsuKoufukinCapsuleDto from "../../../dto/renketsu_koufukin/createRenketsuKoufukinCapsuleDto";
import RenketsuKoufukinIncomeInterface from "../../../dto/renketsu_koufukin/renketsuKoufukinIncomeDto";
import RenketsuKoufukinIncomeDto from "../../../dto/renketsu_koufukin/renketsuKoufukinIncomeDto";
import FrameworkTaskPlanInterface from "../../../dto/frameworkTaskPlanDto";
import FrameworkTaskPlanDto from "../../../dto/frameworkTaskPlanDto";
import mockGetRenketsuIncome from "./mock/mockGetRenketsuIncome";
import RenketsuKoufukinWkTblInterface from "../../../dto/renketsu_koufukin/renketsuKoufukinWkTblDto";
import RenketsuKoufukinWkTblDto from "../../../dto/renketsu_koufukin/renketsuKoufukinWkTblDto";
import mockGetOutcomeSuccess from "./mock/mockGetOutcomeSuccess";
import getPagingOption from "../paging/getPagingOption";
import mockGetOutcomeDuplicate from "./mock/mockGetOutcomeDuplicate";
import mockGetOutcomeFailure from "./mock/mockGetOutcomeFailure";
import RenketsuKoufukinFailureInterface from "../../../dto/renketsu_koufukin/renketsuKoufukinFailureDto";
import RenketsuKoufukinFailureDto from "../../../dto/renketsu_koufukin/renketsuKoufukinFailureDto";

// 紐づけ文書候補取得
const searchDocumentCodeCapsuleDto: Ref<SearchDocumentCodeByOfferingCapsuleInterface> = ref(new SearchDocumentCodeByOfferingCapsuleDto());
const documentCodeResultDto: Ref<RenketsuKofukinDocumentCodeOptionResultInterface> = ref(new RenketsuKofukinDocumentCodeOptionResultDto());
const listHoukokuNen: Ref<SelectOptionInterface[]> = ref(getHoukokunen());
searchDocumentCodeCapsuleDto.value.houkokuNen = parseInt(listHoukokuNen.value[0].value);

// 検索結果
const createCapsuleDto: Ref<CreateRenketsuKoufukinCapsuleInterface> = ref(new CreateRenketsuKoufukinCapsuleDto());
const renketsuKoufukinIncomeDto: Ref<RenketsuKoufukinIncomeInterface> = ref(new RenketsuKoufukinIncomeDto());
const frameworkTaskPlanDto: FrameworkTaskPlanInterface = new FrameworkTaskPlanDto();

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

    // 政治団体を設定
    searchDocumentCodeCapsuleDto.value.poliOrgId = sendDto.politicalOrganizationId;
    searchDocumentCodeCapsuleDto.value.poliOrgCode = sendDto.politicalOrganizationCode;
    searchDocumentCodeCapsuleDto.value.poliOrgName = sendDto.politicalOrganizationName;
    searchDocumentCodeCapsuleDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    searchDocumentCodeCapsuleDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    searchDocumentCodeCapsuleDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない;

    // 文書コードを取得
    // TODO 文書コード取得作業
    // 下記UrlでBack側アクセス
    // const url:string = "/renketsu-koufukin/get-document-code";
    documentCodeResultDto.value = mockGetDocumentCode(String(searchDocumentCodeCapsuleDto.value.houkokuNen));

    // 最新の値を設定
    createCapsuleDto.value.documentCodeBalance = parseInt(documentCodeResultDto.value.listBalance[documentCodeResultDto.value.listBalance.length - 1].value);
    createCapsuleDto.value.documentCodeUsage = parseInt(documentCodeResultDto.value.listUsage[documentCodeResultDto.value.listUsage.length - 1].value);

    // 非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}



function onCreateWkTbl() {
    createCapsuleDto.value.houkokuNen = searchDocumentCodeCapsuleDto.value.houkokuNen;
    createCapsuleDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    createCapsuleDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    createCapsuleDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない;

    // TODO 作成作業

    // 下記UrlとCreateRenketsuKoufukinCapsuleInterfaceでBack側アクセス
    // const url:string = "/renketsu-koufukin/create-wktbl";

    // タスク計画を登録してBatchを非同期で動作した想定
    const now: Date = new Date();
    frameworkTaskPlanDto.year = now.getFullYear();
    frameworkTaskPlanDto.taskPlanCode = 250;
    frameworkTaskPlanDto.message = "データ作成を実行しています…";

    alert(frameworkTaskPlanDto.message);

    renketsuKoufukinIncomeDto.value.isOk = true;
    renketsuKoufukinIncomeDto.value.message = "取得できました";
}


function onSearch() {

    // 作成確認のために作成時に取得したタスク同一識別コードを送信

    // TODO 送信作業
    if (renketsuKoufukinIncomeDto.value.isOk) {

        // 下記URLとCreateRenketsuKoufukinCapsuleInterfaceでBackアクセス(収入)
        // const url:string = "/renketsu-koufukin/income";

        renketsuKoufukinIncomeDto.value = mockGetRenketsuIncome(renketsuKoufukinIncomeDto.value, searchDocumentCodeCapsuleDto.value.houkokuNen);

        alert(renketsuKoufukinIncomeDto.value.message);
        // 支出はページングがあるので別途呼び出す
        onSearchOutcomeSuccess();
        onSearchOutcomeDuplicate();
        onSearchOutcomeFailure();
    } else {
        alert(renketsuKoufukinIncomeDto.value.message);
    }

}

const outcomeSuccessDto: Ref<RenketsuKoufukinWkTblInterface> = ref(new RenketsuKoufukinWkTblDto());
const listPageingSuccess: Ref<SelectOptionInterface[]> = ref([]);
function onSearchOutcomeSuccess() {

    // 下記URLとCreateRenketsuKoufukinCapsuleInterfaceでBackアクセス(支出・成功)
    // const url:string = "/renketsu-koufukin/income";
    outcomeSuccessDto.value = mockGetOutcomeSuccess(searchDocumentCodeCapsuleDto.value.houkokuNen);
    listPageingSuccess.value = getPagingOption(outcomeSuccessDto.value.countAll, outcomeSuccessDto.value.offset);
}

const outcomeDuplicateDto: Ref<RenketsuKoufukinWkTblInterface> = ref(new RenketsuKoufukinWkTblDto());
const listPageingDuplicate: Ref<SelectOptionInterface[]> = ref([]);
function onSearchOutcomeDuplicate() {
    // 下記URLとCreateRenketsuKoufukinCapsuleInterfaceでBackアクセス(支出・重複)
    // const url:string = "/renketsu-koufukin/income";

    outcomeDuplicateDto.value = mockGetOutcomeDuplicate(searchDocumentCodeCapsuleDto.value.houkokuNen);
    listPageingDuplicate.value = getPagingOption(outcomeDuplicateDto.value.countAll, outcomeDuplicateDto.value.offset);

}

const outcomeFailureDto: Ref<RenketsuKoufukinFailureInterface> = ref(new RenketsuKoufukinFailureDto());
const listPageingFailure: Ref<SelectOptionInterface[]> = ref([]);
function onSearchOutcomeFailure() {

    // 下記URLとCreateRenketsuKoufukinCapsuleInterfaceでBackアクセス(支出・失敗)
    // const url:string = "/renketsu-koufukin/income";
    outcomeFailureDto.value = mockGetOutcomeFailure(searchDocumentCodeCapsuleDto.value.houkokuNen);
    listPageingFailure.value = getPagingOption(outcomeFailureDto.value.countAll, outcomeFailureDto.value.offset);
}

</script>
<template>
    <h1>交付金連結</h1>

    <div class="one-line">
        検索条件
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="searchDocumentCodeCapsuleDto.houkokuNen">
            <option v-for="option of listHoukokuNen" :key="option.value">{{ option.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="searchDocumentCodeCapsuleDto.poliOrgCode" disabled="true" class="code-input">
        <input type="text" v-model="searchDocumentCodeCapsuleDto.poliOrgName" disabled="true"
            class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        連結文書
    </div>
    <div class="right-area">
        <span>
            政治資金収支報告書：
            <select v-model="createCapsuleDto.documentCodeBalance">
                <option v-for="dto of documentCodeResultDto.listBalance" :value="dto.value" :key="dto.value">{{ dto.text
                }}</option>
            </select>
        </span>
        <span class="left-space">
            政党交付金使途報告書：
            <select v-model="createCapsuleDto.documentCodeUsage">
                <option v-for="dto of documentCodeResultDto.listUsage" :value="dto.value" :key="dto.value">{{ dto.text
                }}</option>
            </select>
        </span>

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

    <h3>収入</h3>
    <div class="one-line">
        検索結果(紐づけ成功)
        <table>
            <tr>
                <td>発生日</td>
                <td>費目</td>
                <td>支出名</td>
                <td>項目</td>
                <td>交付金</td>
                <td>自団体</td>
                <td>合計</td>
                <td>取引相手</td>
            </tr>
            <tr v-for="dto of renketsuKoufukinIncomeDto.listSuccess" :key="dto.wkTblRenketsuKoufukinId">
                <td>{{ dto.accrualDateValue.toLocaleDateString("JPN") }}</td>
                <td>{{ dto.usageHimoku }}</td>
                <td>{{ dto.usageShishutsuName }}</td>
                <td>{{ dto.usageUsageItem }}</td>
                <td>{{ dto.amountKoufukin }}</td>
                <td>{{ dto.amountMyFunds }}</td>
                <td>{{ dto.amountAll }}</td>
                <td>{{ dto.payeeName }} <br> {{ dto.payeeAddress }}</td>
            </tr>
        </table>


        <br>
        検索結果(重複)
        <table>
            <tr>
                <td>発生日</td>
                <td>費目</td>
                <td>支出名</td>
                <td>項目</td>
                <td>交付金</td>
                <td>自団体</td>
                <td>合計</td>
                <td>取引相手</td>
            </tr>
            <tr v-for="dto of renketsuKoufukinIncomeDto.listDuplicate" :key="dto.wkTblRenketsuKoufukinId">
                <td>{{ dto.accrualDateValue.toLocaleDateString("JPN") }}</td>
                <td>{{ dto.usageHimoku }}</td>
                <td>{{ dto.usageShishutsuName }}</td>
                <td>{{ dto.usageUsageItem }}</td>
                <td>{{ dto.amountKoufukin }}</td>
                <td>{{ dto.amountMyFunds }}</td>
                <td>{{ dto.amountAll }}</td>
                <td>{{ dto.payeeName }} <br> {{ dto.payeeAddress }}</td>
            </tr>
        </table>

        <br>
        検索結果(紐づけ失敗)
        <table>
            <tr>
                <td>発生日</td>
                <td>項目</td>
                <td>交付金</td>
                <td>合計</td>
                <td>取引相手</td>
            </tr>
            <tr v-for="entity of renketsuKoufukinIncomeDto.listFailure" :key="entity.worktableId">
                <td>{{ entity.accrualDateValue.toLocaleDateString("JPN") }}</td>
                <td>{{ entity.itemName }}</td>
                <td>{{ entity.amount }}</td>
                <td>{{ entity.totalAmount }}</td>
                <td>({{ entity.relationPersonCodeDelegate }}) <br> {{ entity.relationPersonNameDelegate }}</td>
            </tr>
        </table>


    </div>

    <h3>支出</h3>

    <div class="one-line">
        検索結果(紐づけ成功)
        <!-- ページング -->
        <select v-model="outcomeSuccessDto.pageNumber" @change="onSearchOutcomeSuccess()">
            <option v-for="option in listPageingSuccess" :key="option.value" :value="option.value"> {{ option.text
                }}
            </option>
        </select>
        <table>
            <tr>
                <td>発生日</td>
                <td>費目</td>
                <td>支出名</td>
                <td>項目</td>
                <td>交付金</td>
                <td>自団体</td>
                <td>合計</td>
                <td>取引相手</td>
            </tr>
            <tr v-for="dto of outcomeSuccessDto.listSuccess" :key="dto.wkTblRenketsuKoufukinId">
                <td>{{ dto.accrualDateValue.toLocaleDateString("JPN") }}</td>
                <td>{{ dto.usageHimoku }}</td>
                <td>{{ dto.usageShishutsuName }}</td>
                <td>{{ dto.usageUsageItem }}</td>
                <td>{{ dto.amountKoufukin }}</td>
                <td>{{ dto.amountMyFunds }}</td>
                <td>{{ dto.amountAll }}</td>
                <td>{{ dto.payeeName }} <br> {{ dto.payeeAddress }}</td>
            </tr>
        </table>


        <br>
        検索結果(重複)
        <!-- ページング -->
        <select v-model="outcomeDuplicateDto.pageNumber" @change="onSearchOutcomeDuplicate()">
            <option v-for="option in listPageingDuplicate" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select>
        <table>
            <tr>
                <td>発生日</td>
                <td>費目</td>
                <td>支出名</td>
                <td>項目</td>
                <td>交付金</td>
                <td>自団体</td>
                <td>合計</td>
                <td>取引相手</td>
            </tr>
            <tr v-for="dto of outcomeDuplicateDto.listSuccess" :key="dto.wkTblRenketsuKoufukinId">
                <td>{{ dto.accrualDateValue.toLocaleDateString("JPN") }}</td>
                <td>{{ dto.usageHimoku }}</td>
                <td>{{ dto.usageShishutsuName }}</td>
                <td>{{ dto.usageUsageItem }}</td>
                <td>{{ dto.amountKoufukin }}</td>
                <td>{{ dto.amountMyFunds }}</td>
                <td>{{ dto.amountAll }}</td>
                <td>{{ dto.payeeName }} <br> {{ dto.payeeAddress }}</td>
            </tr>
        </table>

        <br>
        検索結果(紐づけ失敗)
        <!-- ページング -->
        <select v-model="outcomeFailureDto.pageNumber" @change="onSearchOutcomeFailure()">
            <option v-for="option in listPageingFailure" :key="option.value" :value="option.value"> {{
                option.text
            }}
            </option>
        </select>
        <table>
            <tr>
                <td>発生日</td>
                <td>費目</td>
                <td>支出名</td>
                <td>項目</td>
                <td>交付金</td>
                <td>自団体</td>
                <td>合計</td>
                <td>取引相手</td>
            </tr>
            <tr v-for="entity of outcomeFailureDto.listFailure" :key="entity.worktableId">
                <td>{{ entity.accrualDateValue.toLocaleDateString("JPN") }}</td>
                <td>{{ entity.sheetHimoku }}</td>
                <td>{{ entity.shishutsuKbnName }}</td>
                <td>{{ entity.usageItem }}</td>
                <td>{{ entity.amountKoufukin }}</td>
                <td>{{ entity.amountMyFunds }}</td>
                <td>{{ entity.amountAll }}</td>
                <td>{{ entity.payeeName }} <br> {{ entity.address }}</td>
            </tr>

        </table>

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

th {
    border-style: solid;
    border-width: 1px;
}
</style>
