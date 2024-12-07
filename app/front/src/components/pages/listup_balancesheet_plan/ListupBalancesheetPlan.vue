<script setup lang="ts">
import { onBeforeMount, ref, Ref } from "vue";
import TaskPlanBalancesheetDetailEntity from "../../../entity/taskPlanBalancesheetDetailEntity";
import TemplateFrameworkCapsuleDto from "../../../dto/template/templateFrameworkCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import WkTblPoliOrgBalancesheetReportInterface from "../../../entity/wkTblPoliOrgBalancesheetReportEntity";

// 公式ソフトウェアでは名前、住所の連結には全角スペース
const BLANK: Ref<string> = ref("　");

// 詳細リスト
const listDetail: Ref<TaskPlanBalancesheetDetailEntity[]> = ref([]);

// 準備リスト
const listPrepared: Ref<WkTblPoliOrgBalancesheetReportInterface[]> = ref([]);

// 初期表示検索用Dto
const capsuleDto: TemplateFrameworkCapsuleDto = new TemplateFrameworkCapsuleDto();
//セッションストレージ取得
capsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
capsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
//編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
capsuleDto.checkTransactionDto = createCheckTransactionDto(true);

//初期表示
onBeforeMount(async () => {
    // 文書指定ミス、文字コード未指定
    onSearchDetail();

    // 政治団体未指定
    onsSearchPrepared()
});

function onSearchDetail() {
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

}

function onsSearchPrepared() {

    // エラーリスリストを取得(政治団体部分)
    const url = "http://localhost:9080/listup-balancesheet-poli-org/normal";
    const method = "POST";
    const body = JSON.stringify(capsuleDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
    fetch(url, { method, headers, body })
        .then(async (response) => {
            listPrepared.value = await response.json();
        })
        .catch((error) => { alert(error); });

}

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
        収支報告書の解析予定を一覧しています({{ listDetail.length }}件)(ファイル保存段階:誤解が出そうなら削除)
        <table>
            <tr>
                <th>保存時間</th>
                <th>ファイル名</th>
                <th>文字コード</th>
                <th>未処理</th>
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
        収支報告書の解析予定リストを一覧しています({{ listPrepared.length }}件)
        <table>
            <tr>
                <th>登録時間</th>
                <th>報告年</th>
                <th>ファイル名</th>
                <th>原文書政治団体</th>
                <th>原文書代表者名</th>
                <th>システム政治団体</th>
            </tr>
            <tr v-for="xmlDto in listPrepared" :key="xmlDto.wkTblPoliOrgBalancesheetReportId">
                <td>{{ xmlDto.insertTimestamp }}</td>
                <td>{{ xmlDto.houkokuNen }}</td>
                <td>{{ xmlDto.fileName }}</td>
                <td>{{ xmlDto.dantaiName01 }}</td>
                <td>{{ xmlDto.daihyoushaNameLast + BLANK + xmlDto.daihyoushaNameFirst
                    }}</td>
                <td>{{ xmlDto.politicalOrganizationName }}</td>
            </tr>
        </table>

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
