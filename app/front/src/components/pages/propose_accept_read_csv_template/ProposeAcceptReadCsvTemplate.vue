<script setup lang="ts">
import { Ref, ref } from "vue";
import PropseCsvReadTemplateEntity from "../../../entity/proposeCsvReadTemplateEntity";
import SelectOptionsArrayInterface from "../../../dto/selectOptionsArrayDto";
import SelectOptionsArrayDto from "../../../dto/selectOptionsArrayDto";
import CsvCellInterface from "../../../dto/read_csv/csvCell";
import { ReadCsvEntityCapsuleDto } from "../../../dto/read_csv/readCsvEntityCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import axios from "axios";
import SendCsvAndStragedShoshouDto from "../../../dto/read_csv/sendCsvAndStragedShoshouDto";
import showErrorMessage from "../../../dto/common_check/showErrorMessage";
import InputRejectReason from "./InputRejectReason.vue";
import SearchProposeReadCsvTemplate from "../../common/search_propose_read_csv_template/SearchProposeReadCsvTemplate.vue";
import TemplateFrameworkResultDto from "../../../dto/template/templateFrameworkResultDto";

//申請中読み取りCSVテンプレート表示フラグ
const isVisibleSearchProposeAcceptReadCsvTemplate: Ref<boolean> = ref(false);

//編集対象データ
const editEntity: Ref<PropseCsvReadTemplateEntity> = ref(new PropseCsvReadTemplateEntity());

/**
* 申請中CSV読み取りテンプレート検索コンポーネント表示
*/
function onSearchProposeAcceptReadCsvTemplate() {
    isVisibleSearchProposeAcceptReadCsvTemplate.value = true;
}


/**
 * 申請中CSV読み取りテンプレート検索キャンセル
*/
function recieveCancelProposeAcceptReadCsvTemplate() {
    //非表示
    isVisibleSearchProposeAcceptReadCsvTemplate.value = false;
}

//csv項目指定リストの指定用配列
const listPointItems: Ref<SelectOptionsArrayInterface[]> = ref([]);
//csv読み取りデータ
const viewCsvReadData: Ref<[CsvCellInterface[]]> = ref([[]]);
//類似データ存在
const hasSimilarData: Ref<boolean> = ref(false);
const listSimilar: Ref<string[][]> = ref([[]]);

/**
* 申請中CSV読み取りテンプレート検索選択
* @param sendEntity 選択Entity
*/
async function recievePoliticalOrganizationLeastInterface(sendEntity: PropseCsvReadTemplateEntity) {

    //選択されたEntityが編集対象
    editEntity.value = sendEntity;

    //すでに設定済の項目指定を呼び出したEntityをもとに復元する
    const pointColumn: string[] = editEntity.value.arrayText.split(",");
    listPointItems.value.splice(0);
    for (const selectedText of pointColumn) {
        let dto = new SelectOptionsArrayDto();
        dto.selectedOption = selectedText;
        listPointItems.value.push(dto);
    }

    //申請時に使用したcsvデータを呼び出す
    const readCsvFileCapsuleDto: ReadCsvEntityCapsuleDto = new ReadCsvEntityCapsuleDto();

    readCsvFileCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    readCsvFileCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    readCsvFileCapsuleDto.checkTransactionDto = createCheckTransactionDto(false);

    readCsvFileCapsuleDto.proposeCsvReadTemplateEntity = editEntity.value;

    //実接続
    const urlLoad = "http://localhost:8080/read-csv-by-entity/practice";
    await axios.post(urlLoad, readCsvFileCapsuleDto)
        .then((response) => {
            const sendCsvAndStragedShoshouDto: SendCsvAndStragedShoshouDto = response.data

            viewCsvReadData.value = sendCsvAndStragedShoshouDto.listAllCsv;
        })
        .catch((error) => showErrorMessage(error.status));


    //類似データがあるかを確認する
    const urlSimilar = "http://localhost:8080/propose-csv-read/search-similar";
    await axios.post(urlSimilar, readCsvFileCapsuleDto)
        .then((response) => {
            listSimilar.value = response.data
        })
        .catch((error) => showErrorMessage(error.status));

    //類似リストが0の時は領域ごと表示しない
    if(listSimilar.value.length >0){
        hasSimilarData.value = true;
    }
    else{
        hasSimilarData.value = false;
    }

    //検索コンポーネント非表示
    isVisibleSearchProposeAcceptReadCsvTemplate.value = false;
}

function onCancel() {
    alert("キャンセル");
}

//却下理由コンポーネント表示
const isVisivleInputRejectReason: Ref<boolean> = ref(false);

function onRefuse() {
    //却下理由入力コンポーネントを表示する
    isVisivleInputRejectReason.value = true;
}


function recieveCancelInputRejectReason() {
    //却下理由入力コンポーネントを非表示にする
    isVisivleInputRejectReason.value = false;
}

async function recieveReasonText(reasonText: string) {

    editEntity.value.judgeReason = reasonText;

    //編集データを保存する
    const readCsvFileCapsuleDto: ReadCsvEntityCapsuleDto = new ReadCsvEntityCapsuleDto();

    readCsvFileCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    readCsvFileCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    readCsvFileCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);

    readCsvFileCapsuleDto.proposeCsvReadTemplateEntity = editEntity.value;

    //却下実接続
    const url = "http://localhost:8080/propose-csv-read-reject/regist";
    await axios.post(url, readCsvFileCapsuleDto)
        .then((response) => {
            const resultDto: TemplateFrameworkResultDto = response.data
            alert(resultDto.message);
        })
        .catch((error) => showErrorMessage(error.status));

    //却下理由入力コンポーネントを非表示にする
    isVisivleInputRejectReason.value = false;
}


async function onAccept() {

    //編集データを保存する
    const readCsvFileCapsuleDto: ReadCsvEntityCapsuleDto = new ReadCsvEntityCapsuleDto();

    readCsvFileCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    readCsvFileCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    readCsvFileCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);

    readCsvFileCapsuleDto.proposeCsvReadTemplateEntity = editEntity.value;

    //許可実接続
    const url = "http://localhost:8080/propose-csv-read-accept/regist";
    await axios.post(url, readCsvFileCapsuleDto)
        .then((response) => {
            const resultDto: TemplateFrameworkResultDto = response.data
            alert(resultDto.message);
        })
        .catch((error) => showErrorMessage(error.status));

}


//ヘッダ有無表示
const headerClass = (index: number): string => {
    //先頭行以外はすべて空文字(未使用にすることはない)
    if (0 === index) {
        if (editEntity.value.hasHeader) {
            //ヘッダがある時だけ未使用(グレー背景) 
            //TODO 未使用がグレー表示だけでであるのがUI的に問題がありそうなので、仕様を決定次第修正する
            return "hasHeader";
        }
    }

    return "";
};

</script>
<template>
    <h1>csv読み取り仕様承認</h1>
    <div class="left-area">
        csv読み取り仕様
    </div>
    <div class="right-area">
        <input type="number" v-model="editEntity.proposeCsvReadTemplateCode" disabled="true" class="code-input">
        <input type="text" v-model="editEntity.proposeCsvReadTemplateName" disabled="true"
            class="left-space text-input">
        <button class="left-space" @click="onSearchProposeAcceptReadCsvTemplate">csv読み取り仕様を検索</button>
    </div>
    <div class="clear-both" />

    <div v-if="hasSimilarData">
        <h3>類似仕様を抽出しています…</h3>
        <table>
            <tr>
                <th>コード</th>
                <th>名称</th>
            </tr>
            <tr v-for="(line, indexLine) in listSimilar" :key="indexLine">
                <td v-for="(cell, indexCell) in line" :key="indexCell">
                    {{ cell }}
                </td>
            </tr>
        </table>
    </div>

    <h2>選択された読み取り仕様を承認する</h2>

    <div class="left-area">
        CSV読み取り形式
    </div>
    <div class="right-area">
        {{ editEntity.proposeCsvReadTemplateName }}
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        ヘッダの有無
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="editEntity.hasHeader">ヘッダの有無
    </div>
    <div class="clear-both"></div>
    <br>

    <div class="one-line" style="overflow: scroll;">
        <table>
            <tr>
                <th v-for="(itemSelect) in listPointItems" :key="itemSelect.selectedOption">
                    <select v-model="itemSelect.selectedOption">
                        <option v-for="option in itemSelect.options" v-bind:value="option.value"
                            v-bind:key="option.value">
                            {{ option.text }}
                        </option>
                    </select>
                </th>
            </tr>
            <tr v-for="(line, indexData) in viewCsvReadData" :key="indexData" :class="headerClass(indexData)">
                <td v-for="item in line" :key="item.data">
                    {{ item.data }}&nbsp;
                </td>
            </tr>
        </table>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onRefuse" class="footer-button">却下</button>
        <button @click="onAccept" class="footer-button">承認</button>
    </div>

    <!-- 申請中CSV読み取りテンプレート -->
    <div v-if="isVisibleSearchProposeAcceptReadCsvTemplate" class="overBackground"></div>
    <div v-if="isVisibleSearchProposeAcceptReadCsvTemplate">
        <div class="overComponent">
            <SearchProposeReadCsvTemplate :isEditable="false"
                @send-cancel-search-propose-read-csv-template="recieveCancelProposeAcceptReadCsvTemplate"
                @send-propose-read-csvtemplate-interface="recievePoliticalOrganizationLeastInterface">
            </SearchProposeReadCsvTemplate>
        </div>
    </div>

    <div v-if="isVisivleInputRejectReason" class="overBackground"></div>
    <div v-if="isVisivleInputRejectReason">
        <div class="overComponent">
            <InputRejectReason @send-cancel-input-reject-reason="recieveCancelInputRejectReason"
                @send-reason-text="recieveReasonText">
            </InputRejectReason>
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

tr.hasHeader {
    background-color: gray;
}
</style>
