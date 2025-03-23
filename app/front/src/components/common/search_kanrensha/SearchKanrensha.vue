<script setup lang="ts">
import { Ref, ref } from "vue";
import SearchKanrenshaLeastCapsuleDto from "../../../dto/kanrensha/searchKanrenshaLeastCapsuleDto";
import KanrenshaLeastInterface from "../../../dto/kanrensha/kanrenshaLeastDto";
import KanrenshaLeastDto from "../../../dto/kanrensha/kanrenshaLeastDto";
import YoushikiEdaKbnIncomeConstants from "../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import mockGetKanrenshaLeast from "./mock/mockGetKanrenshaLeast";

//props,emit
const props = defineProps<{ searchDto: SearchKanrenshaLeastCapsuleDto, }>();
const emits = defineEmits(["sendCancelSearchKanrenshaLeast", "sendKanrenshaLeastInterface"]);

/** 表示行 */
const list: Ref<KanrenshaLeastInterface[]> = ref([]);
/** ラジオボタン選択 */
const selectedRow: Ref<number> = ref(0);

/** 編集フラグ */
const isEditable: Ref<boolean> = ref(!props.searchDto.checkTransactionDto.isSelectOnly);

/**  
 * 選択行を通知する
 * @param rowId その行のDtoのId
 */
function onSelectChange(rowId: number) {
    if (true === isEditable.value) {
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
    const selectedDto: KanrenshaLeastInterface = list.value.filter((dto) => dto.relationId == rowId)[0];
    emits("sendKanrenshaLeastInterface", selectedDto);
}
/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelSearchKanrenshaLeast");
}

/**  
 * 削除ボタンを押下された行を削除する
 * @param rowId その行のDtoのId
 */
function deleteRow(rowId: number) {
    const newList: KanrenshaLeastInterface[] = list.value.filter((dto) => dto.relationId != rowId);
    list.value = newList;
}
/**  
 * 最終行のあとに行追加を行う
 */
function addRow() {
    let maxId = 0;
    for (const dto of list.value) {
        if (maxId < dto.relationId) {
            maxId = dto.relationId;
        }
    }
    const addDto: KanrenshaLeastInterface = new KanrenshaLeastDto();
    addDto.relationId = maxId;
    list.value.push(addDto);
}

const searchWords: Ref<string> = ref("");
/**  
 * 検索条件に基づき検索を行う
 */
async function onSearch() {
    //セッションストレージ取得
    const searchKanrenshaLeastCapsuleDto: SearchKanrenshaLeastCapsuleDto = new SearchKanrenshaLeastCapsuleDto();
    searchKanrenshaLeastCapsuleDto.checkSecurityDto = props.searchDto.checkSecurityDto;
    searchKanrenshaLeastCapsuleDto.checkPrivilegeDto = props.searchDto.checkPrivilegeDto;
    searchKanrenshaLeastCapsuleDto.checkTransactionDto = props.searchDto.checkTransactionDto;
    
    //独自変数設定
    searchKanrenshaLeastCapsuleDto.searchWords = searchWords.value;
    searchKanrenshaLeastCapsuleDto.isSearchPerson = props.searchDto.isSearchPerson;
    searchKanrenshaLeastCapsuleDto.isSearchCorp = props.searchDto.isSearchCorp;
    searchKanrenshaLeastCapsuleDto.isSearchPoliOrg = props.searchDto.isSearchPoliOrg;

    //Mockリストの取得
    list.value = mockGetKanrenshaLeast(searchKanrenshaLeastCapsuleDto);
}
</script>
<template>
    <h3>関連者検索</h3>
    <div class="online">
        検索条件の指定
    </div>
    <div class="left-area-component">
        区分条件
    </div>
    <div class="right-area-component">
        <input type="checkbox" v-model="searchDto.isSearchPerson">個人<input type="checkbox"
            v-model="searchDto.isSearchCorp" class="left-space">企業・団体<input type="checkbox"
            v-model="searchDto.isSearchPoliOrg" class="left-space">政治団体
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
                <th style="width:20%;">コード</th>
                <th style="width:10%;">区分</th>
                <th>名前</th>
                <th v-if="isEditable" style="width:20%;">&nbsp;</th>
            </tr>
            <tr v-for="searchedDto in list" :key="searchedDto.relationId">
                <td style="text-align: center;"><input type="radio" id="searchedDto.relationId"
                        :value="searchedDto.relationId" v-model="selectedRow"
                        @click="onSelectChange(searchedDto.relationId)" /></td>
                <td style="text-align: right;">{{ searchedDto.relationCode }}</td>
                <td>{{ YoushikiEdaKbnIncomeConstants.convertText(searchedDto.relationKbn) }}</td>
                <td>{{ searchedDto.relationName }}</td>
                <td v-if="isEditable" style="text-align: center;"><button
                        @click="deleteRow(searchedDto.relationId)">削除</button></td>
            </tr>
        </table>
        <button v-if="isEditable" @click="addRow">新規行追加</button>
    </div>
    <br>
    <div class="footer" v-if="!isEditable">
        <button @click="onCancel">キャンセル</button>
        <button @click="onSelect" class="left-space">選択</button>
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
