<script setup lang="ts">
import { onBeforeMount, ref, Ref } from "vue";
import TaskPlanBalancesheetDetailInterface from "../../../entity/taskPlanBalancesheetDetailEntity";
import TemplateFrameworkCapsuleDto from "../../../dto/template/templateFrameworkCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import WkTblPoliOrgBalancesheetReportInterface from "../../../entity/wkTblPoliOrgBalancesheetReportEntity";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import UpdateBalancesheetTaskPlanCapsuleInterface from "../../../dto/political_organization/updateBalancesheetTaskPlanCapsuleDto";
import UpdateBalancesheetTaskPlanCapsuleDto from "../../../dto/political_organization/updateBalancesheetTaskPlanCapsuleDto";
import UpdateBalancesheetWkTblCapsuleInterface from "../../../dto/political_organization/updateBalancesheetWkTblCapsuleDto";
import UpdateBalancesheetWkTblCapsuleDto from "../../../dto/political_organization/updateBalancesheetWkTblCapsuleDto";
import TemplateFrameworkResultInterface from "../../../dto/template/templateFrameworkResultDto";

// 公式ソフトウェアでは名前、住所の連結には全角スペース
const BLANK: Ref<string> = ref("　");

/** 公式政治資金収支報告書XML */
const SOFT_BALANCESHEET: string = "収支報告書作成ソフト";
/** 公式政党交付金使途報告書XML */
const SOFT_PARTY_USAGE: string = "使途等報告書作成ソフト";

// 詳細リスト
const listDetail: Ref<TaskPlanBalancesheetDetailInterface[]> = ref([]);

// 準備リスト
const listPrepared: Ref<WkTblPoliOrgBalancesheetReportInterface[]> = ref([]);

//検索コンポーネント表示／非表示
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);

// 政治団体新規登録該否
const isNewPoliticalOrg: Ref<boolean> = ref(true); // コード標準化されているのが前提なので TODO コード標準化がされたら削除する

let editEntityId: number = 0;
/**
 * 政治団体検索コンポーネント表示
 */
function onSearchPoliticalOrgnaization(selectedId: number) {
    editEntityId = selectedId;
    isVisibleSearchPoliticalOrganizationLeast.value = true;
}

/**
* 政治団体検索キャンセル
*/
function recieveCancelSearchPoliticalOrganizationLeast() {
    // 政治団体検索を行ったが、該当がなかった場合は登録許可する
    isNewPoliticalOrg.value = false;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

/**
 * 政治団体検索選択
 * @param sendDto 選択Dto
 */
function recievePoliticalOrganizationLeastInterface(sendDto: PoliticalOrganizationLeastInterface) {

    // 選択したEntityをフィルタ(Idなので一意が保証)
    const editEntity = listPrepared.value.filter(e1 => { return e1.wkTblPoliOrgBalancesheetReportId === editEntityId; }
    )[0];

    // 政治団体を設定
    editEntity.politicalOrganizationId = sendDto.politicalOrganizationId;
    editEntity.politicalOrganizationCode = sendDto.politicalOrganizationCode;
    editEntity.politicalOrganizationName = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}


//初期表示
onBeforeMount(async () => {
    // 文書指定ミス、文字コード未指定
    onSearchDetail();

    // 政治団体未指定
    onsSearchPrepared()
});

// 初期表示検索用Dto
const capsuleDto: TemplateFrameworkCapsuleDto = new TemplateFrameworkCapsuleDto();
//セッションストレージ取得
capsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
capsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
//編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
capsuleDto.checkTransactionDto = createCheckTransactionDto(true);

function onSearchDetail() {

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

}

function onsSearchPrepared() {

    // エラーリスリストを取得(政治団体部分)
    const url = "http://localhost:9080/listup-balancesheet-poli-org/error";
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

function onsUpdateDetail(editId: number) {

    const capsuleEntityDto: UpdateBalancesheetTaskPlanCapsuleInterface = new UpdateBalancesheetTaskPlanCapsuleDto();
    //セッションストレージ取得
    capsuleEntityDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleEntityDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    capsuleEntityDto.checkTransactionDto = createCheckTransactionDto(true);
    capsuleEntityDto.taskPlanBalancesheetDetailEntity
        = listDetail.value.filter(e1 => { return e1.taskPlanBalancesheetDetailId === editId; })[0];

    // タスク予定を更新
    const url = "http://localhost:9080/update-balancesheet-prepare/task-plan";
    const method = "POST";
    const body = JSON.stringify(capsuleEntityDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
    fetch(url, { method, headers, body })
        .then(async (response) => {
            const resultDto:TemplateFrameworkResultInterface = await response.json();
            alert(resultDto.message);
        })
        .catch((error) => { alert(error); });
}

function onsUpdatePrepared(editId: number) {

    const capsuleWkTblDto: UpdateBalancesheetWkTblCapsuleInterface = new UpdateBalancesheetWkTblCapsuleDto();
    //セッションストレージ取得
    capsuleWkTblDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleWkTblDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    capsuleWkTblDto.checkTransactionDto = createCheckTransactionDto(true);
    capsuleWkTblDto.wkTblPoliOrgBalancesheetReportEntity
        = listPrepared.value.filter(e1 => { return e1.wkTblPoliOrgBalancesheetReportId === editId; })[0];

    // ワークテーブルを更新
    const url = "http://localhost:9080/update-balancesheet-prepare/worktable";
    const method = "POST";
    const body = JSON.stringify(capsuleWkTblDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
    fetch(url, { method, headers, body })
        .then(async (response) => {
            const resultDto:TemplateFrameworkResultInterface = await response.json();
            alert(resultDto.message);
        })
        .catch((error) => { alert(error); });

}
</script>
<template>
    <h1>政治資金収支報告書解析予定エラーリスト</h1>

    <div class="one-line">
        収支報告書の解析未処理リストを一覧しています({{ listDetail.length }}件)
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
                <td><button @click="onsUpdateDetail(detailEntity.taskPlanBalancesheetDetailId)">変更</button> </td>
            </tr>
        </table>
    </div>
    <div class="clear-both"></div>
    <hr>
    <div class="one-line">
        収支報告書の解析未処理リストを一覧しています(政治団体未定)({{ listPrepared.length }}件)
        <table>
            <tr>
                <th>登録時間</th>
                <th>報告年</th>
                <th>ファイル名</th>
                <th>原文書政治団体</th>
                <th>原文書代表者名</th>
                <th>システム政治団体</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="xmlDto in listPrepared" :key="xmlDto.wkTblPoliOrgBalancesheetReportId">
                <td>{{ xmlDto.insertTimestamp }}</td>
                <td>{{ xmlDto.houkokuNen }}</td>
                <td>{{ xmlDto.fileName }}</td>
                <td>{{ xmlDto.dantaiName01 }}</td>
                <td>{{ xmlDto.daihyoushaNameLast + BLANK + xmlDto.daihyoushaNameFirst
                    }}</td>
                <td>{{ xmlDto.politicalOrganizationName }}</td>
                <td><button class="left-space"
                        @click="onSearchPoliticalOrgnaization(xmlDto.wkTblPoliOrgBalancesheetReportId)">政治団体検索</button>
                </td>
                <td><button @click="onsUpdatePrepared(xmlDto.wkTblPoliOrgBalancesheetReportId)">変更</button></td>
            </tr>
        </table>
    </div>

    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :isEditable="false"
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
