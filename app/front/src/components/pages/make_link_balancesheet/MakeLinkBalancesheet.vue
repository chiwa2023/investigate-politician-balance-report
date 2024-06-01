<script setup lang="ts">
import { onBeforeMount, ref, Ref } from "vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PublicationFormalItemInterface from "../../../dto/make_balancesheet_link/publicationFormalItemDto";
import PublicationFormalItemDto from "../../../dto/make_balancesheet_link/publicationFormalItemDto";

import PublicationInformalDataDto from "../../../dto/make_balancesheet_link/publicationInformalDataDto";
import PublicationInformalDataInterface from "../../../dto/make_balancesheet_link/publicationInformalDataDto";
import mockGetPublicLink from "./mock/mockGetPublicLink";
import mockGetInformalData from "./mock/mockGetInformalData";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";
import SearchElectionCommission from "../../common/search_election_commission/SearchElectionCommission.vue";
import ElectionCommissionLeastInterface from "../../../dto/election_commission/electionCommissionDto";
import { useRoute } from "vue-router";

//政治団体
const politicalOrgnaizationId: Ref<number> = ref(0);
const politicalOrgnaizationCode: Ref<number> = ref(0);
const politicalOrgnaizationName: Ref<string> = ref("");

const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const isVisibleSearchElectionCommision: Ref<boolean> = ref(false);

//公式情報リスト
const listAllFormal: Ref<PublicationFormalItemInterface[]> = ref([]);
const listFilterFormal: Ref<PublicationFormalItemInterface[]> = ref([]);

const formalDto: Ref<PublicationFormalItemInterface> = ref(new PublicationFormalItemDto());


//非公式情報リスト
const listAllInformal: Ref<PublicationInformalDataInterface[]> = ref([]);
const listFilterInformal: Ref<PublicationInformalDataInterface[]> = ref([]);

//提出年
const selectedOfferingYear: Ref<string> = ref("2024");
const listOfferingYear: Ref<SelectOptionInterface[]> = ref([]);

//提出日
const selectedOfferingDate: Ref<string> = ref("1");
const listOfferingDate: Ref<SelectOptionInterface[]> = ref([]);

//router
const route = useRoute();
const isDisableSelect: Ref<boolean> = ref(false);

//版管理ページからの遷移であればデータ呼び出し
onBeforeMount(() => {
    if (route.query.rowId !== undefined) {
        //データ読み込み
        formalDto.value = mockGetPublicLink()[0];
        formalDto.value.listInformalData.push(mockGetInformalData()[0]);

        // 1件だけを固定で表示       
        listAllFormal.value.push(formalDto.value);
        for (const dto of formalDto.value.listInformalData) {
            listAllInformal.value.push(dto);
        }

        //政治団体設定に値を入れる
        politicalOrgnaizationId.value = formalDto.value.politicalOrgnaizationId;
        politicalOrgnaizationCode.value = formalDto.value.politicalOrgnaizationCode;
        politicalOrgnaizationName.value = formalDto.value.politicalOrgnaizationName;

        //リストを作成してフィルタ
        selectedOfferingYear.value = String(formalDto.value.offeringYear);
        selectedOfferingDate.value = formalDto.value.offeringDate;
        createList();
        filterList();
        //必要な項目の選択を禁止
        isDisableSelect.value = true;
    }
});

/**
 * 政治団体検索コンポーネント表示
 */
function onSearchPoliticalOrgnaization() {
    isVisibleSearchPoliticalOrganizationLeast.value = true;
}

/**
 * 選挙管理委員会検索コンポーネント表示
 */
function onSearchElectionCommition() {
    isVisibleSearchElectionCommision.value = true;
}

/**
 * 政治団体検索キャンセル
 */
function recieveCancelSearchPoliticalOrganizationLeast() {
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

    //リンクリストを取得
    listAllFormal.value = mockGetPublicLink();
    listAllInformal.value = mockGetInformalData();

    //リストを作成してフィルタ
    createList();
    selectedOfferingYear.value = listOfferingYear.value[0].value;
    filterList();
    selectedOfferingDate.value = listOfferingDate.value[0].value;
    formalDto.value = listFilterFormal.value[0];

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

/** 取得したリストからセレクトボタンを生成 */
function createList() {

    //提出年選択リスト
    const savedYear: number[] = [];
    listOfferingYear.value.splice(0);
    // 公式情報
    for (const publicItemDto of listAllFormal.value) {
        const year: number = publicItemDto.offeringYear;
        if (!savedYear.includes(year)) {
            const optionDto: SelectOptionInterface = new SelectOptionDto();
            optionDto.value = String(year);
            optionDto.text = String(year);
            listOfferingYear.value.push(optionDto);
            savedYear.push(year);
        }
    }
    //公式情報期間終了などで非公式情報しか残っていない場合
    for (const publicItemDto of listAllInformal.value) {
        const year: number = publicItemDto.offeringYear;
        if (!savedYear.includes(year)) {
            const optionDto: SelectOptionInterface = new SelectOptionDto();
            optionDto.value = String(year);
            optionDto.text = String(year);
            listOfferingYear.value.push(optionDto);
            savedYear.push(year);
        }
    }

}

/** 提出年・提出日でフィルタ */
function filterList() {
    const intYear: number = parseInt(selectedOfferingYear.value);
    const intDate: string = selectedOfferingDate.value;

    listFilterFormal.value = listAllFormal.value
        .filter((dto) => dto.offeringYear === intYear)
        .filter((dto) => dto.offeringDate === intDate);

    listFilterInformal.value = listAllInformal.value
        .filter((dto) => dto.offeringYear === intYear)
        .filter((dto) => dto.offeringDate === intDate);

    if (listFilterFormal.value.length > 0) {
        formalDto.value = listFilterFormal.value[0];
    } else {
        formalDto.value = new PublicationFormalItemDto();
    }

    const listFilterInformalYear: Ref<PublicationInformalDataInterface[]> = ref([]);
    listFilterInformalYear.value = listAllInformal.value
        .filter((dto) => dto.offeringYear === intYear);

    //提出回数選択リスト
    const savedDate: string[] = [];
    listOfferingDate.value.splice(0);
    for (const informalItemDto of listFilterInformalYear.value) {
        const dateText: string = informalItemDto.offeringDate;
        if (!savedDate.includes(dateText)) {
            const optionDto: SelectOptionInterface = new SelectOptionDto();
            optionDto.value = dateText;
            optionDto.text = dateText;
            listOfferingDate.value.push(optionDto);
            savedDate.push(dateText);
        }
    }
}

/**
 * 選挙管理委員会検索キャンセル
 */
function recieveCancelSearchElectionCommissionLeast() {
    //非表示
    isVisibleSearchElectionCommision.value = false;
}

/**
 * 選挙管理委員会検索選択
 * @param sendDto 選択Dto
 */
function recieveSearchElectionCommissionLeastInterface(sendDto: ElectionCommissionLeastInterface) {

    formalDto.value.electionCommitionId = sendDto.electionCommissionId;
    formalDto.value.electionCommitionCode = sendDto.electionCommissionCode;
    formalDto.value.electionCommitionName = sendDto.electionCommissionName;

    //非表示
    isVisibleSearchElectionCommision.value = false;
}

/**
 * 公式データを削除
 * @param rowId 選択された公式データId
 */
function onDeleteFormal(rowId: number) {
    const newList: PublicationFormalItemInterface[] = listAllFormal.value.filter((dto) => dto.publicationFormalItemId != rowId);
    listAllFormal.value = newList;
    createList();
    filterList();
}

/**
 * 非公式データを削除
 * @param rowId 該当行のId
 */
function deleteRow(rowId: number) {

    const newList: PublicationInformalDataInterface[] = listAllInformal.value.filter((dto) => dto.publicationInformalId != rowId);
    listAllInformal.value = newList;

    filterList();
}
/** 非公式データを追加 */
function addRow() {
    let maxId = 0;
    for (const dto of listAllInformal.value) {
        if (maxId < dto.publicationInformalId) {
            maxId = dto.publicationInformalId;
        }
    }
    const addDto: PublicationInformalDataInterface = new PublicationInformalDataDto();
    addDto.publicationInformalId = maxId++;

    addDto.offeringYear = parseInt(selectedOfferingYear.value);
    addDto.offeringDate = selectedOfferingDate.value;

    listAllInformal.value.push(addDto);
    filterList();
}

/** 参照ページへ遷移 */
function onShowBalancesheet() {
    alert("参照ページへ遷移");
}

/** 読み取り保存ページへ遷移 */
function onImportBalancesheet() {
    alert("保存ページへ遷移");
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
    <h1>政治資金収支報告書参照設定</h1>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="politicalOrgnaizationCode" disabled="true" class="code-input">
        <input type="text" v-model="politicalOrgnaizationName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization" :disabled="isDisableSelect">政治団体検索</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        提出年
    </div>
    <div class="right-area">
        <select v-model="selectedOfferingYear" @change="filterList" :disabled="isDisableSelect">
            <option v-for="(dto) in listOfferingYear" :key="dto.value">{{ dto.text }}</option>
        </select>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        最大提出回数
    </div>
    <div class="right-area">
        <input type="number" v-model="listOfferingDate.length" disabled="true">
    </div>
    <div class="clear-both" />

    <div class="left-area">
        提出日
    </div>
    <div class="right-area">
        <select v-model="selectedOfferingDate" @change="filterList" :disabled="isDisableSelect">
            <option v-for="(dto) in listOfferingDate" :key="dto.value">{{ dto.text }}</option>
        </select>
    </div>
    <div class="clear-both" />


    <h3> 公式情報</h3>

    <div class="left-area">
        提出回
    </div>
    <div class="right-area">
        <input type="number" v-model="formalDto.offeringYear" disabled="true">年&nbsp;
        提出日<input type="date" v-model="formalDto.offeringDate" disabled="true">
        <button @click="onDeleteFormal(formalDto.publicationFormalItemId)">削除</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        提出選挙管理員会
    </div>
    <div class="right-area">
        <input type="number" v-model="formalDto.electionCommitionCode" disabled="true" class="code-input">
        <input type="text" v-model="formalDto.electionCommitionName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchElectionCommition">選挙管理委員会を検索</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        リンクURL
    </div>
    <div class="right-area">
        <textarea v-model="formalDto.publicationLinkUrl"></</textarea>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        掲載状況
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="formalDto.isClosed">現在掲載されていない
    </div>
    <div class="clear-both" />

    <div class="left-area">
        公式掲載日
    </div>
    <div class="right-area">
        <input type="date" v-model="formalDto.publishingDate">
    </div>
    <div class="clear-both" />

    <div class="left-area">
        システムにXMLで保存
    </div>
    <div class="right-area">
        <div v-if="formalDto.isImported">
            <button @click="onShowBalancesheet">参照</button>
        </div>
        <div v-if="!formalDto.isImported">
            <button @click="onImportBalancesheet">読み取りして保存するページへ移動</button>
        </div>
    </div>
    <div class="clear-both" />

    <h3> 非公式情報</h3>

    <div class="one-line">
        <table>
            <tr>
                <th>提出年</th>
                <th>提出日</th>
                <th>サイト名称</th>
                <th>URL</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="(dto) in listFilterInformal" :key="dto.publicationInformalId">
                <td>{{ dto.offeringYear }}</td>
                <td>{{ dto.offeringDate }}</td>
                <td><input type="text" v-model="dto.linkLabel"></td>
                <td><textarea type="url" v-model="dto.linkUrl"></textarea></td>
                <td>
                    <div v-if="dto.isImported">
                        <button @click="onShowBalancesheet">参照</button>
                    </div>
                    <div v-if="!dto.isImported">
                        <button @click="onImportBalancesheet">読み取りして保存するページへ移動</button>
                    </div>
                </td>
                <td><button @click="deleteRow(dto.publicationInformalId)">削除</button>
                </td>
            </tr>
        </table>
        <button @click="addRow">新規行追加</button>

    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">保存</button>
    </div>

    <!-- ベースを操作禁止するレイヤ -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
    <div v-if="isVisibleSearchElectionCommision" class="overBackground"></div>
    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :isEditable="false"
                @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
            </SearchPoliticalOrganization>
        </div>
    </div>
    <!-- 選挙管理委員会検索コンポーネント -->
    <div v-if="isVisibleSearchElectionCommision">
        <div class="overComponent">
            <SearchElectionCommission :isEditable="false"
                @send-cancel-search-election-commission-least="recieveCancelSearchElectionCommissionLeast"
                @send-election-commission-least-interface="recieveSearchElectionCommissionLeastInterface">
            </SearchElectionCommission>

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
