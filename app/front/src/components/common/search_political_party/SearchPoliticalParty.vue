<script setup lang="ts">
import { Ref, ref } from "vue";
import PoliticalPartyInterface from "../../../dto/political_party/politicalPartyLeastDto";
import PoliticalPartyDto from "../../../dto/political_party/politicalPartyLeastDto";
import SearchPoliticalPartyCapsuleDto from "../../../dto/political_party/searchPoliticalPartyLeastCapsuleDto";
import mockGetPoliticalParty from "./mock/mockGetPoliticalParty";

//props,emit
const props = defineProps<{ searchDto:SearchPoliticalPartyCapsuleDto }>();
const emits = defineEmits(["sendCancelSearchPoliticalParty", "sendPoliticalPartyInterface"]);

/** 表示行 */
const list: Ref<PoliticalPartyInterface[]> = ref([]);
/** ラジオボタン選択 */
const selectedRow: Ref<number> = ref(0);
/** 編集フラグ */
const isEditable:Ref<boolean> = ref(!props.searchDto.checkTransactionDto.isSelectOnly); 



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
    const selectedDto: PoliticalPartyInterface = list.value.filter((dto) => dto.politicalPartyId == rowId)[0];
    emits("sendPoliticalPartyInterface", selectedDto);
}
/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelSearchPoliticalParty");
}

/**  
 * 削除ボタンを押下された行を削除する
 * @param rowId その行のDtoのId
 */
function deleteRow(rowId: number) {
    const newList: PoliticalPartyInterface[] = list.value.filter((dto) => dto.politicalPartyId != rowId);
    list.value = newList;
}
/**  
 * 最終行のあとに行追加を行う
 */
function addRow() {
    let maxId = 0;
    for (const dto of list.value) {
        if (maxId < dto.politicalPartyId) {
            maxId = dto.politicalPartyId;
        }
    }
    const addDto: PoliticalPartyInterface = new PoliticalPartyDto();
    addDto.politicalPartyId = maxId;
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
    const searchPoliticalPartyCapsuleDto: SearchPoliticalPartyCapsuleDto = new SearchPoliticalPartyCapsuleDto();
    searchPoliticalPartyCapsuleDto.checkSecurityDto = props.searchDto.checkSecurityDto;
    searchPoliticalPartyCapsuleDto.checkPrivilegeDto = props.searchDto.checkPrivilegeDto;
    searchPoliticalPartyCapsuleDto.checkTransactionDto = props.searchDto.checkTransactionDto;

    //独自変数設定
    searchPoliticalPartyCapsuleDto.searchWords = searchWords.value;

    //Mockリストの取得
    list.value = mockGetPoliticalParty();
}
</script>
<template>
    <h3>政党検索</h3>
    <div class="one-line">
        検索条件の指定
    </div>
    <div class="left-area-component">
        検索語
    </div>
    <div class="right-area-component">
        <input type="text" v-model="searchWords" style="margin-right:2%;"><button @click="onSearch">検索</button>
    </div>
    <br>
    <div class="one-line">
        検索結果の表示

        <table style="width:45%;">
            <tr>
                <th style="width:10%;">&nbsp;</th>
                <th style="width:30%;">コード</th>
                <th>名前</th>
                <th v-if="isEditable" style="width:20%;">&nbsp;</th>
            </tr>
            <tr v-for="searchedDto in list" :key="searchedDto.politicalPartyId">
                <td style="text-align: center;"><input type="radio" id="searchedDto.politicalPartyId"
                        :value="searchedDto.politicalPartyId" v-model="selectedRow"
                        @click="onSelectChange(searchedDto.politicalPartyId)" /></td>
                <td style="text-align: right;">{{ searchedDto.politicalPartyCode }}</td>
                <td>{{ searchedDto.politicalPartyName }}</td>
                <td v-if="isEditable" style="text-align: center;"><button
                        @click="deleteRow(searchedDto.politicalPartyId)">削除</button></td>
            </tr>
        </table>
        <button v-if="isEditable" @click="addRow">新規行追加</button>
    </div>
    <br>
    <div class="footer" v-if="!isEditable">
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
