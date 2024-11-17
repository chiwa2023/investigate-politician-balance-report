<script setup lang="ts">
import { ref, Ref } from "vue";
import TaskPlanBalancesheetDetailEntity from "../../../entity/taskPlanBalancesheetDetailEntity";
import TemplateFrameworkCapsuleDto from "../../../dto/template/templateFrameworkCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";

// 詳細リスト
const listDetail: Ref<TaskPlanBalancesheetDetailEntity[]> = ref([]);

const capsuleDto: TemplateFrameworkCapsuleDto = new TemplateFrameworkCapsuleDto();
//セッションストレージ取得
capsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
capsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
//編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
capsuleDto.checkTransactionDto = createCheckTransactionDto(true);

// エラーリスリストを取得(文字コード部分)
const url = "http://localhost:9080/listup-balancesheet-charset/normal";
const method = "POST";
const body = JSON.stringify(capsuleDto);
const headers = {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
};
fetch(url, { method, headers, body })
    .then(async (response) => {
        listDetail.value = await response.json();
    })
    .catch((error) => { alert(error); });

// 政治団体部分エラーリスト

/*　処理未処理  */
function convertFinishedText(data: boolean): string {
    if (data) {
        return "処理完了";// Backの取得状況か基本的に発生しない
    } else {
        return "未処理";
    }
}
</script>
<template>
    <h1>政治資金収支報告書解析予定リスト</h1>
    <div class="one-line">
        収支報告書の解析予定を一覧しています(ファイル保存段階:誤解が出そうなら削除)
        <table>
            <tr>
                <th>保存時間</th>
                <th>ファイル名</th>
                <th>未処理</th>
                <th>文字コード</th>
            </tr>
            <tr v-for="detailEntity in listDetail" :key="detailEntity.taskPlanBalancesheetDetailId">
                <td>{{ detailEntity.insertTimestamp }}</td>
                <td>{{ detailEntity.fileName }}</td>
                <td>{{ detailEntity.charset }}</td>
                <td>{{ convertFinishedText(detailEntity.isFinished) }}</td>
            </tr>
        </table>
    </div>
    <div class="clear-both"></div>
    <hr>
    <div class="one-line">
        収支報告書の解析予定リストを一覧しています
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
