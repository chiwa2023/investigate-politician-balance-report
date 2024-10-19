<script setup lang="ts">
import { Ref, ref, onBeforeMount } from "vue";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import ProposeCsvReadTemplateEntity from "../../../entity/proposeCsvReadTemplateEntity";
import TemplateFrameworkCapsuleDto from "../../../dto/template/templateFrameworkCapsuleDto";
import { getFinancialKbnName } from "../../../dto/financial/financialOrgConstants";

//props,emit
const props = defineProps<{ isEditable: boolean }>();
const emits = defineEmits(["sendCancelSearchProposeReadCsvTemplate", "sendProposeReadCsvtemplateInterface"]);

/** 表示行 */
const list: Ref<ProposeCsvReadTemplateEntity[]> = ref([]);
/** ラジオボタン選択 */
const selectedRow: Ref<number> = ref(0);

//初期表示
onBeforeMount(async () => {
    //検索条件を設定していないので表示の時に検索を行う
    onSearch();
});

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
    const selectedDto: ProposeCsvReadTemplateEntity = list.value.filter((dto) => dto.proposeCsvReadTemplateId == rowId)[0];
    emits("sendProposeReadCsvtemplateInterface", selectedDto);
}
/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelSearchProposeReadCsvTemplate");
}

/**  
 * 削除ボタンを押下された行を削除する
 * @param rowId その行のDtoのId
 */
function deleteRow(rowId: number) {
    const newList: ProposeCsvReadTemplateEntity[] = list.value.filter((dto) => dto.proposeCsvReadTemplateId != rowId);
    list.value = newList;
}
/**  
 * 最終行のあとに行追加を行う
 */
function addRow() {
    let maxId = 0;
    for (const dto of list.value) {
        if (maxId < dto.proposeCsvReadTemplateId) {
            maxId = dto.proposeCsvReadTemplateId;
        }
    }
    const addDto: ProposeCsvReadTemplateEntity = new ProposeCsvReadTemplateEntity();
    addDto.proposeCsvReadTemplateId = maxId + 1;
    list.value.push(addDto);
}

//検索語
//const searchWords: Ref<string> = ref("");

/**  
 * 検索条件に基づき検索を行う
 */
async function onSearch() {
    //実接続
    //セッションストレージ取得
    const templateFrameworkCapsuleDto: TemplateFrameworkCapsuleDto = new TemplateFrameworkCapsuleDto;

    templateFrameworkCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    templateFrameworkCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    templateFrameworkCapsuleDto.checkTransactionDto = createCheckTransactionDto(false);

    //独自変数設定はなし

    //申請中の読み取りCSV仕様を一覧する
    const url = "http://localhost:8080/propose-read-csv-template/search-all";
    const method = "POST";
    const body = JSON.stringify(templateFrameworkCapsuleDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    fetch(url, { method, headers, body })
        .then(async (response) => {

            list.value = await response.json();

        })
        .catch((error) => { alert(error); });
}

/**
 * 金融機関区分名称を取得する
 * @param kbn 金融機関区分名称
 */
function getKbnName(kbn: number): string {
    return getFinancialKbnName(kbn);
}
</script>
<template>
    <h3>申請中csv読み取りテンプレート</h3>
    <!-- 
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
    -->
    <div class="online">
        検索結果の表示

        <table style="width:95%;">
            <tr>
                <th style="width:10%;">&nbsp;</th>
                <th>コード</th>
                <th>名前</th>
                <th>区分名称</th>
                <th>金融機関コード</th>
                <th>金融機関名称</th>
                <th v-if="props.isEditable" style="width:20%;">&nbsp;</th>
            </tr>
            <tr v-for="searchedDto in list" :key="searchedDto.proposeCsvReadTemplateId">
                <td style="text-align: center;"><input type="radio" id="searchedDto.proposeCsvReadTemplateId"
                        :value="searchedDto.proposeCsvReadTemplateId" v-model="selectedRow"
                        @click="onSelectChange(searchedDto.proposeCsvReadTemplateId)" /></td>
                <td style="text-align: right;">{{ searchedDto.proposeCsvReadTemplateCode }}</td>
                <td>{{ searchedDto.proposeCsvReadTemplateName }}</td>
                <td>{{ getKbnName(searchedDto.financialOrgKbn) }}</td>
                <td>{{ searchedDto.financialOrgCode }}</td>
                <td>{{ searchedDto.financialOrgName }}</td>
                <td v-if="props.isEditable" style="text-align: center;"><button
                        @click="deleteRow(searchedDto.proposeCsvReadTemplateId)">削除</button></td>
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
