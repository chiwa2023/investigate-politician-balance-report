<script setup lang="ts">
import { Ref, ref } from "vue";
import ZenginOrgChangeBranchInterface from "../../../entity/zenginOrgChangeBranchEntity";
import mockGetZenginChangeList from "./mock/mockGetZenginChangeList";
import SearchZenginFinancialOrg from "../../common/search_zengin_financial_org/SearchZenginFinancialOrg.vue";
import ZenginFinancialOrgInterface from "../../../dto/financial/zenginFinancialOrg";
import SearchZenginChangeConditionCapsuleDto from "../../../dto/financial/searchZenginChangeConditionCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";

// 金融機関検索窓
const isVisibleSearchZenginFinancialOrgLeast:Ref<boolean> = ref(false);
    const editId:Ref<number> = ref(0); 
function recieveCancelSearchZenginFinancialOrg(){
    isVisibleSearchZenginFinancialOrgLeast.value= false;
}

function recieveZenginFinancialOrgInterface(selectedDto: ZenginFinancialOrgInterface){

    const entity = listChange.value.filter((e) => e.zenginOrgChangeBranchId === editId.value)[0]

    entity.zenginOrgMoveId = selectedDto.zenginFinancialOrgId;
    entity.zenginOrgMoveCode = selectedDto.zenginFinancialOrgCode;
    entity.zenginOrgMoveName = selectedDto.zenginFinancialOrgName;

    isVisibleSearchZenginFinancialOrgLeast.value= false;
}

//　未処理非限定検索時のみ条件
// 検索条件
const conditionDto:Ref<SearchZenginChangeConditionCapsuleDto> = ref(new SearchZenginChangeConditionCapsuleDto());
const date: Date = new Date();
const dateStart: Date = new Date();
dateStart.setMonth(dateStart.getMonth() - 2);
conditionDto.value.startDate = dateStart.getFullYear() + "-" + (dateStart.getMonth() + 1) + "-" + dateStart.getDate();
conditionDto.value.endDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

// 異動内容
const isSearchAdd: Ref<boolean> = ref(true);
const isSearchDelete: Ref<boolean> = ref(true);
const isSearchMove: Ref<boolean> = ref(true);

// リスト
const listChange: Ref<ZenginOrgChangeBranchInterface[]> = ref([]);


function onSearch() {
    conditionDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    conditionDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();

    if(isSearchAdd){
        conditionDto.value.listChangeKbn.push(1);
    }
    if(isSearchDelete){
        conditionDto.value.listChangeKbn.push(2);
    }
    if(isSearchMove){
        conditionDto.value.listChangeKbn.push(3);
    }

    listChange.value = mockGetZenginChangeList();
}

function onSearchFinacialOrg(id:number) {
    editId.value = id;
    isVisibleSearchZenginFinancialOrgLeast.value= true;
}

function onCancel() {
    alert("キャンセル");
}

function onSave() {
    alert("送信");
}

function convertFinishedText(isFinished:boolean) {
    if(isFinished){
        return "反映完了";
    }else{
        return "作業中";
    }
}

</script>
<template>
    <h1>金融機関支店異動リスト</h1>

    <div class="one-line">
        抽出条件<br>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        未処理のみ
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="conditionDto.isMishoriOnly">未処理データのみ
    </div>
    <div class="clear-both"></div>

    <div v-if="!conditionDto.isMishoriOnly">
        <div class="left-area">
            期間
        </div>
        <div class="right-area">
            <input type="date" v-model="conditionDto.startDate">から<input type="date" v-model="conditionDto.endDate">
        </div>
        <div class="clear-both"></div>

        <div class="left-area">
            異動
        </div>
        <div class="right-area">
            <span><input type="checkbox" v-model="isSearchAdd">新規</span>
            <span class="left-space"><input type="checkbox" v-model="isSearchDelete">廃止</span>
            <span class="left-space"><input type="checkbox" v-model="isSearchMove">移転</span>
        </div>
        <div class="clear-both"></div>
        <div class="left-area">
            金融機関コード
        </div>
        <div class="right-area">
            <input type="text" v-model="conditionDto.financialCode" class="code-input">
        </div>
        <div class="clear-both"></div>
    </div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"><br></div>


    <div class="one-line">
        検索結果
        <table>
            <tr>
                <th>異動</th>
                <th>作業状態</th>
                <th>金融機関</th>
                <th>支店</th>
                <th>住所</th>
                <th>電話番号</th>
                <th>移転先</th>
                <th>移転検索　　</th>
            </tr>

            <!-- 取得行 -->
            <tr v-for="entity of listChange" :key="entity.zenginOrgChangeBranchId">
                <td>{{ entity.changeKbnText }}</td>
                <td>{{ convertFinishedText(entity.isFinished) }}</td>
                <td>({{ entity.orgCode }}) <br>{{ entity.orgName }}</td>
                <td>({{ entity.branchCode }}) <br>{{ entity.branchName }}</td>
                <td>({{ entity.postalCode }}) <br>{{ entity.branchAddress }}</td>
                <td>{{ entity.phonNumber }}</td>
                <td v-if="entity.changeKbn ===2"><span v-if="entity.zenginOrgMoveId != 0">({{ entity.zenginOrgMoveCode }}) <br>{{ entity.zenginOrgMoveName }} </span></td>
                <td v-if="entity.changeKbn !==2">-</td>
                <td v-if="entity.changeKbn ===2"><button @click="onSearchFinacialOrg(entity.zenginOrgChangeBranchId)">移転先検索</button></td>
                <td v-if="entity.changeKbn !==2">-</td>
            </tr>

        </table>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">送信</button>
    </div>

    <!-- 全銀金融機関検索コンポーネント -->
    <div v-if="isVisibleSearchZenginFinancialOrgLeast" class="overBackground"></div>
    <div v-if="isVisibleSearchZenginFinancialOrgLeast">
        <div class="overComponent">
            <SearchZenginFinancialOrg :is-editable="false" @send-cancel-search-zengin-financial-org="recieveCancelSearchZenginFinancialOrg" @send-zengin-financial-org-interface="recieveZenginFinancialOrgInterface">
            </SearchZenginFinancialOrg>

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
