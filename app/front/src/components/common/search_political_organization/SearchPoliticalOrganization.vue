<script setup lang="ts">
import { Ref, ref } from "vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../dto/political_organization/politicalOrganizationLeastDto";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import mockGetPoliticalOrgLeast from "./mock/mockGetPoliticalOrgLeast";

//props,emit
const props = defineProps<{ isEditable: boolean }>();
const emits = defineEmits(["sendCancelSearchPoliticalOrganizationLeast", "sendPoliticalOrganizationLeastInterface"]);

/** 表示行 */
const list: Ref<PoliticalOrganizationLeastInterface[]> = ref([]);
/** ラジオボタン選択 */
const selectedRow: Ref<number> = ref(0);

/**  
 * 選択行を通知する
 * @param rowId その行のDtoのId
 */
function onSelectChange(rowId: number) {
    if (true === props.isEditable) {
        sendData(rowId);
    }
}

/**  
 * 入力内容を選択する
 */
function onSelect() {
    sendData(selectedRow.value);
}

/**  
 * 選択データを送信する
 * @param rowId その行のDtoのId
 */
function sendData(rowId: number) {
    //PrimaryIdをKeyにしているので、1件だけに絞られることが保証されている
    const selectedDto: PoliticalOrganizationLeastInterface = list.value.filter((dto) => dto.politicalOrganizationId == rowId)[0];
    emits("sendPoliticalOrganizationLeastInterface", selectedDto);
}
/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelSearchPoliticalOrganizationLeast");
}

/**  
 * 削除ボタンを押下された行を削除する
 * @param rowId その行のDtoのId
 */
function deleteRow(rowId: number) {
    const newList: PoliticalOrganizationLeastInterface[] = list.value.filter((dto) => dto.politicalOrganizationId != rowId);
    list.value = newList;
}
/**  
 * 最終行のあとに行追加を行う
 */
function addRow() {
    let maxId = 0;
    for (const dto of list.value) {
        if (maxId < dto.politicalOrganizationId) {
            maxId = dto.politicalOrganizationId;
        }
    }
    const addDto: PoliticalOrganizationLeastInterface = new PoliticalOrganizationLeastDto();
    addDto.politicalOrganizationId = maxId;
    list.value.push(addDto);
}

const searchWords: Ref<string> = ref("");
/**  
 * 検索条件に基づき検索を行う
 */
async function onSearch() {
    //セッションストレージ取得
    const searchPoliticalOrganizationLeastCapsuleDto: SearchPoliticalOrganizationLeastCapsuleDto = new SearchPoliticalOrganizationLeastCapsuleDto();
    searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(!props.isEditable);

    //独自変数設定
    searchPoliticalOrganizationLeastCapsuleDto.searchWords = searchWords.value;

    //Mockリストの取得
    list.value = mockGetPoliticalOrgLeast();
}
</script>
<template>
    <h3>政治団体検索</h3>
    <div class="online">
        検索条件の指定
    </div>
    <div class="left-area-component">
        検索語
    </div>
    <div class="right-area-component">
        <input type="text" v-model="searchWords" style="margin-right:2%;"><button @click="onSearch">検索</button>
    </div>
    <br>
    <div class="online">
        検索結果の表示

        <table style="width:45%;">
            <tr>
                <th style="width:10%;">&nbsp;</th>
                <th style="width:30%;">コード</th>
                <th>名前</th>
                <th v-if="props.isEditable" style="width:20%;">&nbsp;</th>
            </tr>
            <tr v-for="searchedDto in list" :key="searchedDto.politicalOrganizationId">
                <td style="text-align: center;"><input type="radio" id="searchedDto.politicalOrganizationId"
                        :value="searchedDto.politicalOrganizationId" v-model="selectedRow"
                        @click="onSelectChange(searchedDto.politicalOrganizationId)" /></td>
                <td style="text-align: right;">{{ searchedDto.politicalOrganizationCode }}</td>
                <td>{{ searchedDto.politicalOrganizationName }}</td>
                <td v-if="props.isEditable" style="text-align: center;"><button
                        @click="deleteRow(searchedDto.politicalOrganizationId)">削除</button></td>
            </tr>
        </table>
        <button v-if="props.isEditable" @click="addRow">新規行追加</button>
    </div>
    <br>
    <div class="footer" v-if="!props.isEditable">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSelect" class="footer-button">選択</button>
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
