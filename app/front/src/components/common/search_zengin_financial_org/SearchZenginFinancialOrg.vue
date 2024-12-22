<script setup lang="ts">
import { Ref, ref } from "vue";
import ZenginFinancialOrgInterface from "../../../dto/financial/zenginFinancialOrg";
import ZenginFinancialOrgDto from "../../../dto/financial/zenginFinancialOrg";
import SearchZenginFinancialOrgCapsuleDto from "../../../dto/financial/searchZenginFinancialOrgCapsuleDto";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import mockGetZenginFinancialOrg from "./mock/mockGetZenginFainancialOrg";

//props,emit
const props = defineProps<{ isEditable: boolean }>();
const emits = defineEmits(["sendCancelSearchZenginFinancialOrg", "sendZenginFinancialOrgInterface"]);

/** 表示行 */
const list: Ref<ZenginFinancialOrgInterface[]> = ref([]);
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
    const selectedDto: ZenginFinancialOrgInterface = list.value.filter((dto) => dto.zenginFinancialOrgId == rowId)[0];
    emits("sendZenginFinancialOrgInterface", selectedDto);
}

/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelSearchZenginFinancialOrg");
}

/**  
 * 削除ボタンを押下された行を削除する
 * @param rowId その行のDtoのId
 */
function deleteRow(rowId: number) {
    const newList: ZenginFinancialOrgInterface[] = list.value.filter((dto) => dto.zenginFinancialOrgId != rowId);
    list.value = newList;
}

/**  
 * 最終行のあとに行追加を行う
 */
function addRow() {
    let maxId = 0;
    for (const dto of list.value) {
        if (maxId < dto.zenginFinancialOrgId) {
            maxId = dto.zenginFinancialOrgId;
        }
    }
    const addDto: ZenginFinancialOrgInterface = new ZenginFinancialOrgDto();
    addDto.zenginFinancialOrgId = maxId;
    list.value.push(addDto);
}

const searchWords: Ref<string> = ref("");
/**  
 * 検索条件に基づき検索を行う
 */
async function onSearch() {
    //リストを取得する
    //実接続
    //セッションストレージ取得
    const zenginFinancialOrgCapsuleDto: SearchZenginFinancialOrgCapsuleDto = new SearchZenginFinancialOrgCapsuleDto();
    zenginFinancialOrgCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    zenginFinancialOrgCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    zenginFinancialOrgCapsuleDto.checkTransactionDto = createCheckTransactionDto(!props.isEditable);

    //独自変数設定
    //zenginFinancialOrgCapsuleDto.searchWords = searchWords.value;
    //const url = "http://localhost:8080/zengin-financial-org/search-table";
    //const method = "POST";
    //const body = JSON.stringify(zenginFinancialOrgCapsuleDto);
    //const headers = {
    //    'Accept': 'application/json',
    //    'Content-Type': 'application/json'
    //};
    //fetch(url, { method, headers, body })
    //    .then(async (response) => {
    //
    //        list.value = await response.json();
    //    })
    //    .catch((error) => { alert(error); });
    list.value = mockGetZenginFinancialOrg();
}
</script>
<template>
    <h3>全銀金融機関検索</h3>
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
            <tr v-for="variousDto in list" :key="variousDto.zenginFinancialOrgId">
                <td style="text-align: center;"><input type="radio" id="variousDto.ZenginFinancialOrgId"
                        :value="variousDto.zenginFinancialOrgId" v-model="selectedRow"
                        @click="onSelectChange(variousDto.zenginFinancialOrgId)" /></td>
                <td style="text-align: right;">{{ variousDto.zenginFinancialOrgCode }}</td>
                <td>{{ variousDto.zenginFinancialOrgName }}</td>
                <td v-if="props.isEditable" style="text-align: center;"><button
                        @click="deleteRow(variousDto.zenginFinancialOrgId)">削除</button></td>
            </tr>
        </table>
        <button v-if="props.isEditable" @click="addRow">新規行追加</button>
    </div>
    <div class="footer" v-if="!props.isEditable">
        <button @click="onCancel">キャンセル</button>
        <button @click="onSelect">選択</button>
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
