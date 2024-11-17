<script setup lang="ts">
import { onBeforeMount, ref, Ref } from "vue";
import TaskPlanBalancesheetDetailInterface from "../../../entity/taskPlanBalancesheetDetailEntity";
import TemplateFrameworkCapsuleDto from "../../../dto/template/templateFrameworkCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";

/** 公式政治資金収支報告書XML */
const SOFT_BALANCESHEET: string = "収支報告書作成ソフト";
/** 公式政党交付金使途報告書XML */
const SOFT_PARTY_USAGE: string = "使途等報告書作成ソフト";

// 詳細リスト
const listDetail: Ref<TaskPlanBalancesheetDetailInterface[]> = ref([]);

//初期表示
onBeforeMount(async () => {
    // 表示の時に検索を行う
    const capsuleDto: TemplateFrameworkCapsuleDto = new TemplateFrameworkCapsuleDto();
    //セッションストレージ取得
    capsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    capsuleDto.checkTransactionDto = createCheckTransactionDto(true);

    // エラーリスリストを取得(文字コード部分)
    const url = "http://localhost:9080/listup-balancesheet-charset/error";
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
});


// TODO 政治団体部分エラーリスト

</script>
<template>
    <h1>政治資金収支報告書解析予定エラーリスト</h1>

    <div class="one-line">
        収支報告書の解析未処理リストを一覧しています
        <table>
            <tr>
                <th>保存時間</th>
                <th>ファイル名</th>
                <th>文字コード</th>
                <th>処理</th>
                <th>文書</th>
                <th>変更　　</th>
            </tr>
            <tr v-for="detailEntity in listDetail" :key="detailEntity.taskPlanBalancesheetDetailId">
                <td>{{ detailEntity.insertTimestamp }}</td>
                <td>{{ detailEntity.fileName }}</td>
                <td>処理予定なし</td>
                <td><select v-model="detailEntity.charset">
                        <option>Shift_JIS</option>
                        <option>UTF-8</option>
                        <option>UTF-16</option>
                    </select></td>
                <td> <select v-model="detailEntity.documentKey">
                        <option :value="SOFT_BALANCESHEET">政治資金収支報告書</option>
                        <option :value="SOFT_PARTY_USAGE">政党交付金使途報告書</option>
                    </select>
                </td>
                <td><button>変更</button> </td>
            </tr>
        </table>
    </div>
    <div class="clear-both"></div>
    <hr>
    <div class="one-line">
        収支報告書の解析未処理リストを一覧しています(政治団体未定)
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
