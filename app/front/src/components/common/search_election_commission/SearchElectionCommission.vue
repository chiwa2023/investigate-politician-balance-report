<script setup lang="ts">
import { Ref, ref } from "vue";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import ElectionCommissionLeastInterface from "../../../dto/election_commission/electionCommissionDto";
import ElectionCommissionLeastDto from "../../../dto/election_commission/electionCommissionDto";
import SearchElectionCommissionLeastCapsuleDto from "../../../dto/election_commission/searchElectionCommissionLeastCapsuleDto";
import mockGetElectionCommissionLeast from "./mock/mockGetElectionCommissionLeast";

//props,emit
const props = defineProps<{ isEditable: boolean }>();
const emits = defineEmits(["sendCancelSearchElectionCommissionLeast", "sendElectionCommissionLeastInterface"]);

/** 表示行 */
const list: Ref<ElectionCommissionLeastInterface[]> = ref([]);
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
    const selectedDto: ElectionCommissionLeastInterface = list.value.filter((dto) => dto.electionCommissionId == rowId)[0];
    emits("sendElectionCommissionLeastInterface", selectedDto);
}
/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelSearchElectionCommissionLeast");
}

/**  
 * 削除ボタンを押下された行を削除する
 * @param rowId その行のDtoのId
 */
function deleteRow(rowId: number) {
    const newList: ElectionCommissionLeastInterface[] = list.value.filter((dto) => dto.electionCommissionId != rowId);
    list.value = newList;
}
/**  
 * 最終行のあとに行追加を行う
 */
function addRow() {
    let maxId = 0;
    for (const dto of list.value) {
        if (maxId < dto.electionCommissionId) {
            maxId = dto.electionCommissionId;
        }
    }
    const addDto: ElectionCommissionLeastInterface = new ElectionCommissionLeastDto();
    addDto.electionCommissionId = maxId;
    list.value.push(addDto);
}

const searchWords: Ref<string> = ref("");
/**  
 * 検索条件に基づき検索を行う
 */
async function onSearch() {
    //Mockリストの取得

    //実接続
    //セッションストレージ取得
    const searchPoliticalOrganizationLeastCapsuleDto: SearchElectionCommissionLeastCapsuleDto = new SearchElectionCommissionLeastCapsuleDto();
    searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(!props.isEditable);

    //独自変数設定
    searchPoliticalOrganizationLeastCapsuleDto.searchWords = searchWords.value;

    //Mockリストの取得
    list.value = mockGetElectionCommissionLeast();
}
</script>
<template>
    <h3>選挙管理員会検索</h3>
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
            <tr v-for="searchedDto in list" :key="searchedDto.electionCommissionId">
                <td style="text-align: center;"><input type="radio" id="searchedDto.electionCommissionId"
                        :value="searchedDto.electionCommissionId" v-model="selectedRow"
                        @click="onSelectChange(searchedDto.electionCommissionId)" /></td>
                <td style="text-align: right;">{{ searchedDto.electionCommissionCode }}</td>
                <td>{{ searchedDto.electionCommissionName }}</td>
                <td v-if="props.isEditable" style="text-align: center;"><button
                        @click="deleteRow(searchedDto.electionCommissionId)">削除</button></td>
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
