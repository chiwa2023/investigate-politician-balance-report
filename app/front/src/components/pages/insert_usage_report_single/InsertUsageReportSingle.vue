<script setup lang="ts">
import { ref, Ref } from "vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import CheckPrivilegeDto from "../../../dto/common_check/checkPrivilegeDto";
import ReadXmlUsageReportResultInterface from "../../../dto/read_xml/usgae/readXmlUsageReportResultDto";
import ReadXmlUsageReportResultDto from "../../../dto/read_xml/usgae/readXmlUsageReportResultDto";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import RegistPartyUsageReportCapsuleDto from "../../../dto/read_xml/usgae/registPartyUsageReportCapsuleDto";
import RegistPartyUsageReportCapsuleInterface from "../../../dto/read_xml/usgae/registPartyUsageReportCapsuleDto";
import RegistPartyUsageReportResultInterface from "../../../dto/read_xml/usgae/registPartyYsageReportResultDto";
import RegistPartyUsageReportResultDto from "../../../dto/read_xml/usgae/registPartyYsageReportResultDto";
import PartyUsageXmlUpload from "../../common/party_usage_xml_upload/PartyUsageXmlUpload.vue";

//権限確認Dto
const privilegeDto: Ref<CheckPrivilegeDto> = ref(SessionStorageCommonCheck.getPrivilege());

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
const readXmlResultDto: Ref<ReadXmlUsageReportResultInterface> = ref(new ReadXmlUsageReportResultDto());

    /**
* 登録準備処理が行われた政治資金収支報告書Dtoを取得する
* @param resultDto Back側で保存されたXML
*/
function recieveReadXmlUsageResultInterface(resultDto: ReadXmlUsageReportResultInterface) {
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

    const registCapsuleDto: RegistPartyUsageReportCapsuleInterface = new RegistPartyUsageReportCapsuleDto();
    registCapsuleDto.checkPrivilegeDto = privilegeDto.value;
    registCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    registCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);
    registCapsuleDto.saveStorageResultDto = readXmlResultDto.value.saveStorageResultDto;
    registCapsuleDto.documentPropertyDto = readXmlResultDto.value.documentPropertyDto;

    // 政治団体を正しく指定したデータを保存
    const url = "http://localhost:9080/insert-political-party-usage-report";
    const method = "POST";
    const body = JSON.stringify(registCapsuleDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    const registResuldDto: Ref<RegistPartyUsageReportResultInterface> = ref(new RegistPartyUsageReportResultDto());
    fetch(url, { method, headers, body })
        .then(async (response) => {
            registResuldDto.value = await response.json();
            alert(registResuldDto.value.message);
        })
        .catch((error) => { alert(error); });
}

</script>
<template>
    <h1> 政党交付金使途報告書登録(1件)</h1>
    <!-- 政党交付金使途報告書1件登録 -->
    <PartyUsageXmlUpload @send-read-xml-usage-result-interface="recieveReadXmlUsageResultInterface">
    </PartyUsageXmlUpload>
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
            <td>{{ readXmlResultDto.documentPropertyDto.nendo }}</td>
            <td>{{ readXmlResultDto.coverDto.partyName }}</td>
            <td>{{ readXmlResultDto.coverDto.delegateName }}</td>
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
