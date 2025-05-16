<script setup lang="ts">
import { type Ref, ref, toRaw } from "vue";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import InputAddressDto from "../../../dto/Input_address/inputAddressDto";
import mockMakeSuggestPostalList from "./mock/mockMakeSuggestPostalList";
import mockMakeSuggestBlockList from "./mock/mockMakeSuggestBlockList";
import mockMakeSuggestBuildingList from "./mock/mockMakeSuggestBuildingList";
import PostalCodeCapsuleInterface from "../../../dto/postal/postalCodeCapsuleDto";
import PostalCodeCapsuleDto from "../../../dto/postal/postalCodeCapsuleDto";
import type SelectOptionNumberInterface from "../../../dto/selectOptionNumberDto";
import type PostalCodeBlockResultInterface from "../../../dto/postal/postalCodeBlockResultDto";
import type PostalCodeBuildingResultInterface from "../../../dto/postal/postalCodeBuildingResultDto";

//props,emit
const props = defineProps<{ editDto: InputAddressDto }>();
const emits = defineEmits(["sendCancelInputAddress", "sendInputAddressInterface"]);

/** 入力用Dto */
const inputAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
inputAddressDto.value = structuredClone(toRaw(props.editDto));    
//onst addressPostal: Ref<string> = ref(props.editDto.addressPostal);
//const addressBlock: Ref<string> = ref(props.editDto.addressBlock);

/** 住所郵便番号まで */
const selectedAddressPostal: Ref<number> = ref(-1);
const listPostalSuggest: Ref<SelectOptionNumberInterface[]> = ref([]);
const listBackupPostalSuggest: Ref<SelectOptionNumberInterface[]> = ref([]);

/** 住所郵便番地まで */
const selectedAddressBlock: Ref<string> = ref("");
const listBlockSuggest: Ref<SelectOptionInterface[]> = ref([]);
const listBackupBlockSuggest: Ref<SelectOptionInterface[]> = ref([]);

/** 住所郵便建物 */
const selectedAddressBuilding: Ref<string> = ref("");
const listBuildingSuggest: Ref<SelectOptionInterface[]> = ref([]);


/** 地方自治体住居検索 */
const isGyouseiku: Ref<boolean> = ref(false);

/** 郵便番号取得 */
function getAddressPostal() {
    listPostalSuggest.value.splice(0);
    //  郵便番号の形式となったらリストを取得する
    if (3 === inputAddressDto.value.postalcode1.length && 4 === inputAddressDto.value.postalcode2.length) {

        // 検索条件の設定
        const conditionDto: PostalCodeCapsuleInterface = new PostalCodeCapsuleDto();
        conditionDto.postal1 = inputAddressDto.value.postalcode1;
        conditionDto.postal2 = inputAddressDto.value.postalcode2;

        // TODO 住所候補をBack側から取得する
        // const url = "http://localhost:7080/postal-search/postal";
        // const method = "POST";
        // const body = JSON.stringify(conditionDto);
        // const headers = {
        //     'Accept': 'application/json',
        //     'Content-Type': 'application/json'
        // };
        // fetch(url, { method, headers, body })
        //     .then(async (response) => {
        //         listInquireAnswer.value = await response.json();
        //         listInquireAnswer.value[0];
        //     })
        //     .catch((error) => { alert(error); });

        const resultDto = mockMakeSuggestPostalList();
        listPostalSuggest.value = resultDto.listOptions;
        listBackupPostalSuggest.value = structuredClone(toRaw(listPostalSuggest.value));
        isGyouseiku.value = resultDto.isGyouseikuData;

        // 1件だけの時は値を決定して番地までデータを検索
        if (listPostalSuggest.value.length === 1) {
            selectedAddressPostal.value = listPostalSuggest.value[0].value;
            searchBlock();
        }
    }
}

/** 住所郵便番号候補選択時 */
function selectSuggestPostal() {
    const text: string = listPostalSuggest.value.filter(e => e.value === selectedAddressPostal.value)[0].text;
    inputAddressDto.value.addressPostal = text;
    inputAddressDto.value.rsdtAddressPostl = text;

    searchBlock();
}

/** 住所番地までを検索 */
function searchBlock() {
    // 検索条件の設定
    const conditionDto: PostalCodeCapsuleInterface = new PostalCodeCapsuleDto();
    conditionDto.selectedPostal = selectedAddressPostal.value;
    conditionDto.isGyouseikuData = isGyouseiku.value;

    // TODO 番地まで住所候補をBack側から取得する
    // const url = "http://localhost:7080/postal-search/block";
    // const method = "POST";
    // const body = JSON.stringify(conditionDto);
    // const headers = {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json'
    // };
    // fetch(url, { method, headers, body })
    //     .then(async (response) => {
    //         listInquireAnswer.value = await response.json();
    //         listInquireAnswer.value[0];
    //     })
    //     .catch((error) => { alert(error); });

    const resultDto: PostalCodeBlockResultInterface = mockMakeSuggestBlockList();
    listBlockSuggest.value = resultDto.listOptions;
    listBackupBlockSuggest.value = structuredClone(toRaw(listBlockSuggest.value));
    isGyouseiku.value = resultDto.isGyouseikuData;
    inputAddressDto.value.lgCode = resultDto.lgCode;

    // 1件だけの時は値を決定して建物までデータを検索
    if (listBlockSuggest.value.length === 1) {
        selectedAddressBlock.value = listBlockSuggest.value[0].value;
        searchBuilding();
    }

}

/** 住所番地候補選択時 */
function selectSuggestBlock() {
    const text = listBlockSuggest.value.filter(e => e.value === selectedAddressBlock.value)[0].text;
    inputAddressDto.value.addressBlock = text;
    inputAddressDto.value.rsdtAddressBlock = text;
    // TODO 現状は選択肢でコードと名称だけだが、公共団体コードなどを紐づけて利用する
    inputAddressDto.value.machiazaId = "2345678";
    inputAddressDto.value.blkId = "123";
    inputAddressDto.value.rsdtId = "456";

    //下層のサジェストの作成
    searchBuilding();
}

function searchBuilding() {

    // 検索条件の設定
    const conditionDto: PostalCodeCapsuleInterface = new PostalCodeCapsuleDto();
    conditionDto.selectedBlock = selectedAddressBlock.value;
    conditionDto.postalCode = inputAddressDto.value.postalcode1 + inputAddressDto.value.postalcode2;
    conditionDto.lgCode = inputAddressDto.value.lgCode;
    conditionDto.isGyouseikuData = isGyouseiku.value;

    // TODO 建物まで住所候補をBack側から取得する
    // const url = "http://localhost:7080/postal-search/building";
    // const method = "POST";
    // const body = JSON.stringify(conditionDto);
    // const headers = {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json'
    // };
    // fetch(url, { method, headers, body })
    //     .then(async (response) => {
    //         listInquireAnswer.value = await response.json();
    //         listInquireAnswer.value[0];
    //     })
    //     .catch((error) => { alert(error); });

    const resultDto: PostalCodeBuildingResultInterface = mockMakeSuggestBuildingList();
    listBuildingSuggest.value = resultDto.listOptions;

}


/** 住所建物候補選択時 */
function selectSuggestBuilding() {
    inputAddressDto.value.addressBuilding = selectedAddressBuilding.value;
    inputAddressDto.value.rsdtAddressBuilding  = selectedAddressBuilding.value;
}

/** 住所郵便番号フィルタ時 */
const filterPostal: Ref<string> = ref("");
function filterSuggestPostal() {
    const tempList = structuredClone(toRaw(listBackupPostalSuggest.value));
    listPostalSuggest.value = tempList.filter((dto) => { if (dto.text.includes(filterPostal.value)) { return true; } });
}

/** 住所番地フィルタ時 */
const filterBlock: Ref<string> = ref("");
function filterSuggestBlock() {
    const tempList = structuredClone(toRaw(listBackupBlockSuggest.value));
    listBlockSuggest.value = tempList.filter((dto) => { if (dto.text.includes(filterBlock.value)) { return true; } });
    listBlockSuggest.value;
}

/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelInputAddress");
}

/**  
 * 入力内容を保存する
 */
function onSave() {
    // inputAddressDto.value.addressPostal = inputAddressDto.value.addressPostal;
    // inputAddressDto.value.addressBlock = addressBlock.value;
    emits("sendInputAddressInterface", inputAddressDto.value);
}

</script>
<template>
    <h3>住所入力</h3>
    <div style="overflow-y: scroll">

        <div class="left-area">
            郵便番号
        </div>
        <div class="right-area">
            <input v-model="inputAddressDto.postalcode1" type="text" class="code-input"
                @input="getAddressPostal">&nbsp;-&nbsp;
            <input v-model="inputAddressDto.postalcode2" type="text" class="code-input" @input="getAddressPostal">
        </div>
        <div class="clear-both"><br></div>

        <div class="left-area">
            住所1
        </div>
        <div class="right-area">
            <select v-model="selectedAddressPostal" @change="selectSuggestPostal">
                <option v-for="optionDto in listPostalSuggest" :key="optionDto.value" :value="optionDto.value">{{
                    optionDto.text }}</option>
            </select><span class="left-space">フィルタ<input v-model="filterPostal" type="text"
                    @input="filterSuggestPostal"></span><span class="left-space"><input
                    v-model="inputAddressDto.isEditAddressPostal" type="checkbox">編集</span><br>
            <textarea v-model="inputAddressDto.addressPostal" :disabled="!inputAddressDto.isEditAddressPostal"></textarea>
        </div>
        <div class="clear-both"><br></div>

        <div class="left-area">
            番地
        </div>
        <div class="right-area">
            <select v-model="selectedAddressBlock" @change="selectSuggestBlock">
                <option v-for="optionDto in listBlockSuggest" :key="optionDto.value" :value="optionDto.value">{{
                    optionDto.text }}</option>
            </select><span class="left-space">フィルタ<input v-model="filterBlock" type="text"
                    @input="filterSuggestBlock"></span><span class="left-space"><input
                    v-model="inputAddressDto.isEditAddressBlock" type="checkbox">編集</span><br>
            <textarea v-model="inputAddressDto.addressBlock" :disabled="!inputAddressDto.isEditAddressBlock"></textarea>
        </div>
        <div class="clear-both"></div>

        <div class="left-area">
            建物
        </div>
        <div class="right-area">
            <select v-model="selectedAddressBuilding" @change="selectSuggestBuilding">
                <option v-for="optionDto in listBuildingSuggest" :key="optionDto.value" :value="optionDto.value">{{
                    optionDto.text }}</option>
            </select><span class="left-space"><input v-model="inputAddressDto.isEditAddressBuilding"
                    type="checkbox">編集</span><br>
            <textarea v-model="inputAddressDto.addressBuilding"
                :disabled="!inputAddressDto.isEditAddressBuilding"></textarea>
        </div>
        <div class="clear-both"></div>
        <div class="left-area">
            住所コード
        </div>
        <div class="right-area">
            <span>地方公共団体コード</span><input type="text" v-model="inputAddressDto.lgCode" class="code-input"
                disabled="true">
            <span class="left-space">町字Id</span><input type="text" v-model="inputAddressDto.machiazaId"
                class="code-input" disabled="true">
            <span class="left-space">街区Id</span><input type="text" v-model="inputAddressDto.blkId" class="code-input"
                disabled="true">
            <span class="left-space">住居Id</span><input type="text" v-model="inputAddressDto.rsdtId" class="code-input"
                disabled="true">
        </div>
        <div class="clear-both"></div>

        <div class="left-area">
            電話番号
        </div>
        <div class="right-area">
            <input v-model="inputAddressDto.tel1" type="text" class="code-input">&nbsp;-&nbsp;
            <input v-model="inputAddressDto.tel2" type="text" class="code-input">&nbsp;-&nbsp;
            <input v-model="inputAddressDto.tel3" type="text" class="code-input">
        </div>
        <div class="clear-both"></div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">選択</button>
    </div>

</template>
<style scoped></style>
