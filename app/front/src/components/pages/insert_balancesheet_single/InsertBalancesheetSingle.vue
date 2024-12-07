<script setup lang="ts">
import { ref, Ref } from "vue";
import BalancesheetXmlUpload from '../../common/balancesheet-xml-upload/BalancesheetXmlUpload.vue';
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import CheckPrivilegeDto from "../../../dto/common_check/checkPrivilegeDto";
import ReadXmlBalancesheetResultInterface from "../../../dto/read_xml/readXmlBalancesheetResultDto";
import ReadXmlBalancesheetResultDto from "../../../dto/read_xml/readXmlBalancesheetResultDto";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import RegistPoliticalOrgBalancesheetReportCapsuleDto from "../../../dto/read_xml/registPoliticalOrgBalancesheetReportCapsuleDto";
import RegistPoliticalOrgBalancesheetReportResultInterface from "../../../dto/read_xml/registPoliticalOrgBalancesheetReportResultDto";
import RegistPoliticalOrgBalancesheetReportResultDto from "../../../dto/read_xml/registPoliticalOrgBalancesheetReportResultDto";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";

//権限確認Dto
const privilegeDto: Ref<CheckPrivilegeDto> = ref(SessionStorageCommonCheck.getPrivilege());

// 公式ソフトウェアでは名前、住所の連結には全角スペース
const BLANK: Ref<string> = ref("　");

//検索コンポーネント表示／非表示
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);

// 政治団体新規登録該否
const isNewPoliticalOrg: Ref<boolean> = ref(true); // コード標準化されているのが前提なので TODO コード標準化がされたら削除する

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
    //政治団体を設定
    readXmlResultDto.value.documentPropertyDto.politicalOrganizationId = sendDto.politicalOrganizationId;
    readXmlResultDto.value.documentPropertyDto.politicalOrganizationCode = sendDto.politicalOrganizationCode;
    readXmlResultDto.value.documentPropertyDto.politicalOrganizationName = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

// 書証保存、解析結果Dto
const readXmlResultDto: Ref<ReadXmlBalancesheetResultInterface> = ref(new ReadXmlBalancesheetResultDto());
/**
 * 登録準備処理が行われた政治資金収支報告書Dtoを取得する
 * @param resultDto Back側で保存されたXML
 */
function recieveReadXmlBalancesheetResultInterface(resultDto: ReadXmlBalancesheetResultInterface) {
    readXmlResultDto.value = resultDto;
}

/** キャンセル */
function onCancel() {
    alert("キャンセル");
}

/** 保存 */
function onSave() {

    // 政治団体を必ず選択する必要があります(一括登録に備えて個別Dtoに設定を持っている)
    if (readXmlResultDto.value.documentPropertyDto.politicalOrganizationId === 0) {
        if (!readXmlResultDto.value.documentPropertyDto.isAddOrganization) {
            alert("政治団体を新規登録するか、登録済の政治団体を選択してください");
            return;
        }
    }

    const registCapsuleDto: RegistPoliticalOrgBalancesheetReportCapsuleDto = new RegistPoliticalOrgBalancesheetReportCapsuleDto();
    registCapsuleDto.checkPrivilegeDto = privilegeDto.value;
    registCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    registCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);
    registCapsuleDto.saveStorageResultDto = readXmlResultDto.value.saveStorageResultDto;
    registCapsuleDto.documentPropertyDto = readXmlResultDto.value.documentPropertyDto;

    // 政治団体を正しく指定したデータを保存
    const url = "http://localhost:9080/insert-political-orgnaization-balancesheet-report";
    const method = "POST";
    const body = JSON.stringify(registCapsuleDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    const registResuldDto: Ref<RegistPoliticalOrgBalancesheetReportResultInterface> = ref(new RegistPoliticalOrgBalancesheetReportResultDto());
    fetch(url, { method, headers, body })
        .then(async (response) => {
            registResuldDto.value = await response.json();
            alert(registResuldDto.value.message);
        })
        .catch((error) => { alert(error); });
}

</script>
<template>
    <h1>政治資金収支報告書登録(1件)</h1>
    <!-- 政治資金収支報告書1件登録 -->
    <BalancesheetXmlUpload @send-read-xml-balancesheet-result-interface="recieveReadXmlBalancesheetResultInterface">
    </BalancesheetXmlUpload>
    <hr>
    <h3>登録内容</h3>
    政治団体が推測されています。システム内で登録される一貫した政治団体と結びつくように修正してください<br>
    {{ privilegeDto.loginUserName }}さんがファイルアップロードしました(まだ本登録は終わっていません)。
    <table>
        <tr>
            <th>登録時間</th>
            <th>報告年</th>
            <th>原文書政治団体</th>
            <th>原文書代表者名</th>
            <th>システム政治団体</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <tr>
            <td>{{ readXmlResultDto.saveStorageResultDto.registTimeText }}</td>
            <td>{{ readXmlResultDto.documentPropertyDto.houkokuNen }}</td>
            <td>{{ readXmlResultDto.coverDto.dantaiName01 }}</td>
            <td>{{ readXmlResultDto.coverDto.daihyoushaNameLast + BLANK + readXmlResultDto.coverDto.daihyoushaNameFirst
                }}</td>
            <td>{{ readXmlResultDto.documentPropertyDto.politicalOrganizationName }}</td>
            <td><button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button></td>
            <td><input type="checkbox" v-model="readXmlResultDto.documentPropertyDto.isAddOrganization"
                    :disabled="isNewPoliticalOrg">読み取り内容で新規登録する</td>
        </tr>
    </table>

    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :isEditable="false"
                @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
            </SearchPoliticalOrganization>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">保存</button>
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
