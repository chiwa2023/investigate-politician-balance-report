<script setup lang="ts">
import { ref, Ref } from "vue";
import EditIncome from "../../common/edit_income/EditIncome.vue";
import CompareAuditIncome from "../../common/compare_audit_income/CompareAuditIncome.vue";
import ShowBalancesheetIncomeHistory from "../../common/show_balancesheet_income_history/ShowBalancesheetIncomeHistory.vue";
import SearchBalancesheetIncome from "../../common/search_balancesheet_income/SearchBalancesheetIncome.vue";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import AuditOpinionIncomeDto from "../../../dto/audit_opinion/auditOpinionIncomeDto";
import AuditOpinionIncomeInterface from "../../../dto/audit_opinion/auditOpinionIncomeDto";
import SelectOptionInterface from "../../../dto/selectOptionDto";

//検索コンポーネント表示／非表示
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const isVisibleSearchAuditOpinionIncome: Ref<boolean> = ref(false);

//政治団体指定(政治団体を指定しないと収支項目を検索させない)
const enableSelectIncome: Ref<boolean> = ref(true);

//対象政治団体
const politicalOrgnaizationId: Ref<number> = ref(0);
const politicalOrgnaizationCode: Ref<number> = ref(0);
const politicalOrgnaizationName: Ref<string> = ref("");

//収支報告書収入項目
const auditOpinionIncomeId: Ref<number> = ref(0);
const auditOpinionIncomeCode: Ref<number> = ref(0);
const auditOpinionIncomeItemName: Ref<string> = ref("");

//比較用最新と選択データ
const auditOpinionIncomeNewestDto: Ref<AuditOpinionIncomeInterface> = ref(new AuditOpinionIncomeDto());
const auditOpinionIncomeSelectedDto: Ref<AuditOpinionIncomeInterface> = ref(new AuditOpinionIncomeDto());

//入力用Dto
const listAgreement:Ref<SelectOptionInterface[]> = ref([]);
const auditOpinionIncomeAuditoDto: Ref<AuditOpinionIncomeDto> = ref(new AuditOpinionIncomeDto());

/**
 * 処理履歴から最新データを受信
 * @param newestDto 最新データ
 * @param list 賛成意見候補リスト
 */
function recieveNewestIncome(newestDto: AuditOpinionIncomeInterface,list:SelectOptionInterface[]) {
    auditOpinionIncomeNewestDto.value = newestDto;
    
    listAgreement.value.splice(0);
    for(const option of list){
        listAgreement.value.push(option);
    }    
}

/**
 * 処理履歴から選択されたデータを受信
 * @param selectedDto 選択データ
 */
function recieveSelectIncome(selectedDto: AuditOpinionIncomeInterface) {
    auditOpinionIncomeSelectedDto.value = selectedDto;
}

/**
 * 政治団体検索キャンセル
 */
function recieveCancelSearchPoliticalOrganizationLeast() {
    enableSelectIncome.value = true;
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

    //収入項目を初期化
    auditOpinionIncomeId.value = 0;
    auditOpinionIncomeCode.value = 0;
    auditOpinionIncomeItemName.value = "";

    enableSelectIncome.value = false;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;

}

/**
 * 収支報告書収入項目検索キャンセル
 */
function recieveCancelSearchBalancesheetIncome() {
    //非表示
    isVisibleSearchAuditOpinionIncome.value = false;
}

/**
 * 収支報告書収入項目検索選択
 * @param selectedDto 選択Dto
 */
function recieveBalancesheetIncomeInterface(selectedDto: AuditOpinionIncomeInterface) {

    //収入項目をセット
    auditOpinionIncomeId.value = selectedDto.balancesheetIncomeId;
    auditOpinionIncomeCode.value = selectedDto.balancesheetIncomeCode;
    auditOpinionIncomeItemName.value = selectedDto.itemName;

    //非表示
    isVisibleSearchAuditOpinionIncome.value = false;
}

/**
 * 政治団体検索コンポーネント表示
 */
function onSearchPoliticalOrgnaization() {
    isVisibleSearchPoliticalOrganizationLeast.value = true;
}

/**
 * 収支報告書収入項目コンポーネント表示
 */
function onSearchBalancesheetIncome() {
    isVisibleSearchAuditOpinionIncome.value = true;
}

/** キャンセル処理 */
function onCancel() {
    alert("キャンセル");

}

/** 保存処理 */
function onSave() {
    alert("保存");
}
</script>
<template>
    <h1>監査意見付記(収入)</h1>

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
        収入項目
    </div>
    <div class="right-area">
        <input type="number" v-model="auditOpinionIncomeCode" disabled="true" class="code-input">
        <input type="text" v-model="auditOpinionIncomeItemName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchBalancesheetIncome" :disabled="enableSelectIncome">収支報告書収入項目検索</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        書証Id
    </div>
    <div class="right-area">
        <input type="number" v-model="auditOpinionIncomeNewestDto.shoshouId" disabled="true" class="code-input">
    </div>
    <div class="clear-both" />

    <div class="left-area">
        書証区分
    </div>
    <div class="right-area">
        <input type="number" v-model="auditOpinionIncomeNewestDto.shoshouKbn" disabled="true" class="code-input">
        <button class="left-space">書証を参照</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        書証行数
    </div>
    <div class="right-area">
        <input type="number" v-model="auditOpinionIncomeNewestDto.readingLine" disabled="true" class="code-input">行目
    </div>
    <div class="clear-both" />

    <div class="one-line">
        <!-- 処理履歴コンポーネント -->
        <ShowBalancesheetIncomeHistory :incomeCode="auditOpinionIncomeCode" @sendNewestIncome="recieveNewestIncome"
            @sendSelectIncome="recieveSelectIncome"></ShowBalancesheetIncomeHistory>
    </div>

    <div class="one-line">
        <!-- 最新比較コンポーネント -->
        <CompareAuditIncome :newsetDto="auditOpinionIncomeNewestDto" :selectedDto="auditOpinionIncomeSelectedDto">
        </CompareAuditIncome>
    </div>

    <div class="one-line">
        <h2>意見付記入力</h2>
        <table>
            <tr>
                <td style="width:35%;vertical-align: text-top;padding-left: 1%;">
                    <h3>追加意見</h3>
                    <div class="left-area">
                        意見人数
                    </div>
                    <div class="right-area">
                        <input type="number" v-model="auditOpinionIncomeAuditoDto.researcherAmount" disabled="true" class="code-input">人目
                    </div>
                    <div class="clear-both" />

                    <div class="left-area">
                        結論
                    </div>
                    <div class="right-area">
                        <select v-model="auditOpinionIncomeAuditoDto.auditAgreeItemValue">
                            <option v-for="optionDto in listAgreement" :key="optionDto.value" :value="optionDto.value">{{ optionDto.text  }}</option>
                        </select>を支持
                    </div>
                    <div class="clear-both" />

                    <div class="left-area">
                        追加意見
                    </div>
                    <div class="right-area">
                        <textarea v-model="auditOpinionIncomeAuditoDto.auditDetailOpinion" class="code-input"></textarea>
                    </div>
                    <div class="clear-both" />

                </td>
                <td style="width:55%;padding-left: 1%;">
                    <EditIncome :incomeDto="auditOpinionIncomeAuditoDto"></EditIncome>
                </td>
            </tr>
        </table>
        <br>
        <div class="footer">
            <button @click="onCancel" class="footer-button">キャンセル</button>
            <button @click="onSave" class="footer-button">保存</button>
        </div>
    </div>
    <!-- ベースを操作禁止するレイヤ -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
    <div v-if="isVisibleSearchAuditOpinionIncome" class="overBackground"></div>
    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :isEditable="false"
                @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
            </SearchPoliticalOrganization>
        </div>
    </div>
    <!-- 収支報告書収入項目検索コンポーネント -->
    <div v-if="isVisibleSearchAuditOpinionIncome">
        <div class="overComponent">
            <SearchBalancesheetIncome :isEditable="false"
                @send-cancel-search-balancesheet-income="recieveCancelSearchBalancesheetIncome"
                @send-balancesheet-income-interface="recieveBalancesheetIncomeInterface"></SearchBalancesheetIncome>
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
