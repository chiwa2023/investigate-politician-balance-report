<script setup lang="ts">
import { Ref, ref } from "vue";
import InputRejectReason from "./InputRejectReason.vue";
import NotRegistOrganizaionDto from "../../../dto/start_manage_political_organization/notRegistOrganizaionDto";
import mockGetNoteRegitOrganizationList from "./mock/mockGetNoteRegitOrganizationList";
import DownloadGeneralFile from "../../common/download_general_file/DownloadGeneralFile.vue";

//申請中リスト
const listPropose: Ref<NotRegistOrganizaionDto[]> = ref(mockGetNoteRegitOrganizationList());

//操作中データ
const editDto: Ref<NotRegistOrganizaionDto> = ref(new NotRegistOrganizaionDto());

/** ラジオボタン選択 */
const selectedRow: Ref<number> = ref(0);

//キャンセル
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

    alert("却下理由" + reasonText);
    //却下理由入力コンポーネントを非表示にする
    isVisivleInputRejectReason.value = false;
}

async function onAccept() {

    alert("登録");
}

/**  
 * 選択行を通知する
 * @param rowId その行のDtoのId
 */
function onSelectChange(rowId: number) {
    editDto.value = listPropose.value.filter((dto) => dto.notRegistOrganizaionId === rowId)[0];
}

</script>
<template>
    <h1>政治団体登録申請一覧</h1>

    <h3>登録申請中政治団体</h3>
    <div class="one-line">
        <table>
            <tr>
                <th>&nbsp;</th>
                <th>政治団体名称</th>
                <th>選管コード</th>
                <th>選管名称</th>
            </tr>

            <tr v-for="dto in listPropose" :key="dto.notRegistOrganizaionId">
                <td style="text-align: center;"><input type="radio" id="dto.notRegistOrganizaionId"
                        :value="dto.notRegistOrganizaionId" v-model="selectedRow"
                        @click="onSelectChange(dto.notRegistOrganizaionId)" /></td>
                <td>{{ dto.politicalOrganizationName }}</td>
                <td>{{ dto.electionCommissionLeastDto.electionCommissionCode }}</td>
                <td>{{ dto.electionCommissionLeastDto.electionCommissionName }}</td>
            </tr>
        </table>
    </div>
    <div class="clear-both"></div>

    <h3>編集する政治団体</h3>
    <div class="left-area">
        提出選挙管理委員会
    </div>
    <div class="right-area">
        <input type="number" v-model="editDto.electionCommissionLeastDto.electionCommissionCode" disabled="true"
            class="code-input">
        <input type="text" v-model="editDto.electionCommissionLeastDto.electionCommissionName" disabled="true"
            class="left-space name-input">
    </div>
    <div class="clear-both"><br></div>

    <div class="left-area">
        政治団体名
    </div>
    <div class="right-area">
        <input type="text" v-model="editDto.politicalOrganizationName" class="name-input" disabled="true">
    </div>
    <div class="clear-both"><br></div>

    <div class="left-area">
        登録に必要な書証
    </div>
    <div class="right-area">
        <DownloadGeneralFile :save-storage-result-dto="editDto.saveStorageResultDto"></DownloadGeneralFile>
    </div>
    <div class="clear-both"><br></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onRefuse" class="footer-button">却下</button>
        <button @click="onAccept" class="footer-button">承認</button>
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
