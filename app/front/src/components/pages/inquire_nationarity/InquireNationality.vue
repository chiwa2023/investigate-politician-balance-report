<script setup lang="ts">
import { Ref, ref } from "vue";
import RelationPersonInterface from '../../../dto/relation/relationPersonDto';
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import mockGetRelationPerson from "./mock/mockGetRelationPerson";
import InputAddress from "../../common/input_address/InputAddress.vue";
import InputAddressDto from "../../../dto/Input_address/inputAddressDto";
import InputAddressCode from "../../common/input_address_code/InputAddressCode.vue";

//政治団体
const politicalOrgnaizationId: Ref<number> = ref(0);
const politicalOrgnaizationCode: Ref<number> = ref(0);
const politicalOrgnaizationName: Ref<string> = ref("");

//政治団体検索コンポーネント
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const searchPoliticalOrganizationLeastCapsuleDto: SearchPoliticalOrganizationLeastCapsuleDto = new SearchPoliticalOrganizationLeastCapsuleDto();
searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

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
    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

const poliOrgLeastDto: Ref<PoliticalOrganizationLeastInterface> = ref(new PoliticalOrganizationLeastDto());
/**
 * 政治団体検索選択
 * @param sendDto 選択Dto
 */
function recievePoliticalOrganizationLeastInterface(sendDto: PoliticalOrganizationLeastInterface) {

    poliOrgLeastDto.value = sendDto;

    //政治団体を設定
    politicalOrgnaizationId.value = sendDto.politicalOrganizationId;
    politicalOrgnaizationCode.value = sendDto.politicalOrganizationCode;
    politicalOrgnaizationName.value = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}
//　問い合わせ関連者リスト
const listInquireGroup: Ref<RelationPersonInterface[]> = ref([]);

//　問い合わせ関連者回答リスト
const listInquireAnswer: Ref<RelationPersonInterface[]> = ref([]);

const date: Date = new Date();
const year = date.getFullYear() - 2;

const startDate: Ref<string> = ref(year + "-" + "01-01");
const endDate: Ref<string> = ref(year + "-" + "12-31");

function onSearch() {
    // mockから取得
    listInquireGroup.value = mockGetRelationPerson(poliOrgLeastDto.value);
}

//住所入力表示フラグ
const isVisibleInputAddress: Ref<boolean> = ref(false);

// 編集対象Id
const editId: Ref<string> = ref("");
const editInputAddress: Ref<InputAddressDto> = ref(new InputAddressDto());
/**
* 住所入力コンポーネント表示
*/
function onInputAddress(id: string) {
    editId.value = id;
    // 編集用住所を準備する
    const editDto: RelationPersonInterface = listInquireGroup.value.filter((e) => e.inquireId == editId.value)[0];
    editInputAddress.value.addressAll = editDto.addressAll;
    editInputAddress.value.addressPostal = editDto.addressPostal;
    editInputAddress.value.addressBlock = editDto.addressBlock;
    editInputAddress.value.addressBuilding = editDto.addressBuilding;
    editInputAddress.value.orginAddressAll = editDto.orginAddressAll;

    editInputAddress.value.postalcode1 = editDto.postalcode1;
    editInputAddress.value.postalcode2 = editDto.postalcode2;

    editInputAddress.value.tel1 = editDto.tel1;
    editInputAddress.value.tel2 = editDto.tel2;
    editInputAddress.value.tel3 = editDto.tel3;

    isVisibleInputAddress.value = true;
}


/**
 * 住所入力キャンセル
 */
function recieveCancelInputAddress() {
    //非表示
    isVisibleInputAddress.value = false;
}

/**
 * 住所入力選択
 * @param sendDto 選択Dto
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    // 保存
    const editDto: RelationPersonInterface = listInquireGroup.value.filter((e) => e.inquireId == editId.value)[0];

    editDto.postalcode1 = sendDto.postalcode1;
    editDto.postalcode2 = sendDto.postalcode2;

    editDto.addressPostal = sendDto.addressPostal;
    editDto.addressBlock = sendDto.addressBlock;
    editDto.addressBuilding = sendDto.addressBuilding;

    editDto.addressAll = editDto.addressPostal + editDto.addressBlock + "　" + editDto.addressBuilding;

    editDto.tel1 = sendDto.tel1;
    editDto.tel2 = sendDto.tel2;
    editDto.tel3 = sendDto.tel3;

    editDto.lgCode = sendDto.lgCode;
    editDto.machiazaId = sendDto.machiazaId;
    editDto.blkId = sendDto.blkId;
    editDto.rsdtId = sendDto.rsdtId;

    //非表示
    isVisibleInputAddress.value = false;
}


function onCancel() {
    alert("キャンセル");
}

function onSave() {

    // チェックされた対象だけに絞る
    const list: Ref<RelationPersonInterface[]> = ref([]);
    list.value = listInquireGroup.value.filter((dto) => dto.isSearch);

    if (list.value.length < 1) {
        alert("国籍を問い合わせる対象者が選択されていません");
    }

    // API接続時には不要な回答リスト初期処理
    listInquireAnswer.value.splice(0);

    // 外部APIに国籍情報問い合わせ
    //    const url = "http://localhost:9080/listup-party-usage-charset/normal";
    //    const method = "POST";
    //    const body = JSON.stringify(listInquireGroup.value);
    //    const headers = {
    //        'Accept': 'application/json',
    //        'Content-Type': 'application/json'
    //    };
    //    fetch(url, { method, headers, body })
    //        .then(async (response) => {
    //            listInquireAnswer.value = await response.json();
    //        })
    //        .catch((error) => { alert(error); });
    //}

    let counter: number = 1;
    for (const dto of list.value) {

        switch (counter % 3) {
            case 0:
                dto.nationalityAnswerKbn = "1";
                dto.nationalityAnswerKbnText = "日本国籍保持";
                break;

            case 1:
                dto.nationalityAnswerKbn = "2";
                dto.nationalityAnswerKbnText = "外国人籍";
                break;

            case 2:
                dto.nationalityAnswerKbn = "3";
                dto.nationalityAnswerKbnText = "基礎情報不備";
                break;

            default:
                break;
        }
        listInquireAnswer.value.push(dto);
        counter++;
    }
}
</script>
<template>
    <h1>国籍問い合わせ</h1>

    <div class="one-line">
        抽出条件<br>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="politicalOrgnaizationCode" disabled="true" class="code-input">
        <input type="text" v-model="politicalOrgnaizationName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        期間
    </div>
    <div class="right-area">
        <input type="date" v-model="startDate">から<input type="date" v-model="endDate">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索語
    </div>
    <div class="right-area">
        <input type="text" placeholder="未設定の予定" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">

    </div>
    <div class="right-area">
        <button @click="onSearch">この条件で関連者を検索</button>
    </div>
    <div class="clear-both"><br></div>


    <div class="one-line">
        問い合わせをしたい関連者をチェック<br>
        <table>
            <tr>
                <th>検索対象</th>
                <th>名前</th>
                <th>郵便番号</th>
                <th>住所</th>
                <th>電話番号</th>
                <th style="width: 10%;">追加</th>
            </tr>
            <tr v-for="personDto in listInquireGroup" :key="personDto.inquireId">
                <td>
                    <input type="checkbox" v-model="personDto.isSearch">
                </td>
                <td>
                    {{ personDto.kanrenshaCode }}<br>
                    {{ personDto.nameAll }}
                </td>
                <td>
                    {{ personDto.postalcode1 }}-{{ personDto.postalcode2 }}
                </td>
                <td>
                    {{ personDto.lgCode }} -{{ personDto.machiazaId }}-{{ personDto.blkId }} -
                    {{ personDto.rsdtId }}<br>
                    {{ personDto.addressAll }}
                </td>
                <td>
                    {{ personDto.tel1 }}-{{ personDto.tel2 }}-{{ personDto.tel3 }}
                </td>
                <td>
                    <button @click="onInputAddress(personDto.inquireId)">コード追加</button>
                </td>

            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>




    <div class="one-line" v-show="listInquireAnswer.length > 0">
        問い合わせを回答(時間がかかる場合は非同期移行)<br>
        <table>
            <tr>
                <th>問い合わせ回答</th>
                <th>名前</th>
                <th>郵便番号</th>
                <th>住所</th>
                <th>電話番号</th>
            </tr>
            <tr v-for="personDto in listInquireAnswer" :key="personDto.inquireId">
                <td>
                    {{ personDto.nationalityAnswerKbn }}<br>
                    {{ personDto.nationalityAnswerKbnText }}
                </td>
                <td>
                    {{ personDto.kanrenshaCode }}<br>
                    {{ personDto.nameAll }}
                </td>
                <td>
                    {{ personDto.postalcode1 }}-{{ personDto.postalcode2 }}
                </td>
                <td>
                    {{ personDto.lgCode }} -{{ personDto.machiazaId }}-{{ personDto.blkId }} -{{
                        personDto.rsdtId }}<br>
                    {{ personDto.addressAll }}
                </td>
                <td>
                    {{ personDto.tel1 }}-{{ personDto.tel2 }}-{{ personDto.tel3 }}
                </td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <!-- ベースを操作禁止するレイヤ -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :search-dto="searchPoliticalOrganizationLeastCapsuleDto"
                @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
            </SearchPoliticalOrganization>
        </div>
    </div>

    <!-- 住所入力コンポーネント -->
    <div v-if="isVisibleInputAddress" class="overBackground"></div>
    <div v-if="isVisibleInputAddress">
        <div class="overComponent">
            <InputAddressCode :selected-dto="editInputAddress" @send-cancel-input-address="recieveCancelInputAddress"
                @send-input-address-interface="recieveInputAddressInterface">
            </InputAddressCode>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">送信</button>
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
