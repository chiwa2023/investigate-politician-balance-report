<script setup lang="ts">
import { computed, type ComputedRef, type Ref, ref, toRaw } from "vue";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import InputAddressDto from "../../../dto/Input_address/inputAddressDto";
import mockMakeSuggestPostalList from "./mock/mockMakeSuggestPostalList";
import mockMakeSuggestBlockList from "./mock/mockMakeSuggestBlockList";
import mockMakeSuggestBuildingList from "./mock/mockMakeSuggestBuildingList";

//props,emit
const props = defineProps<{ editDto: InputAddressDto }>();
const emits = defineEmits(["sendCancelInputAddress", "sendInputAddressInterface"]);

/** 入力用Dto */
const inputAddressDto: ComputedRef<InputAddressDto> = computed(() => { return props.editDto });


/** 住所郵便番号まで */
const selectedAddressPostal: Ref<string> = ref("");
const listPostalSuggest: Ref<SelectOptionInterface[]> = ref([]);
const listBackupPostalSuggest: Ref<SelectOptionInterface[]> = ref([]);

/** 住所郵便番地まで */
const selectedAddressBlock: Ref<string> = ref("");
const listBlockSuggest: Ref<SelectOptionInterface[]> = ref([]);
const listBackupBlockSuggest: Ref<SelectOptionInterface[]> = ref([]);

/** 住所郵便建物 */
const selectedAddressBuilding: Ref<string> = ref("");
const listBuildingSuggest: Ref<SelectOptionInterface[]> = ref([]);

/** 郵便番号変更 */
function mockMakeAddressPostal() {
    listPostalSuggest.value.splice(0);
    //  郵便番号の形式となったらリストを取得する
    if (3 === inputAddressDto.value.postalcode1.length && 4 === inputAddressDto.value.postalcode2.length) {
        listPostalSuggest.value = mockMakeSuggestPostalList();
        listBackupPostalSuggest.value = structuredClone(toRaw(listPostalSuggest.value));
    }

}

/** 住所郵便番号候補選択時 */
function selectSuggestPostal() {
    inputAddressDto.value.addressPostal = selectedAddressPostal.value;
    // TODO 現状は選択肢でコードと名称だけだが、公共団体コードなどを紐づけて利用する
    inputAddressDto.value.lgCode = "123456";
    //下層のサジェストの作成
    listBlockSuggest.value = mockMakeSuggestBlockList();
    listBackupBlockSuggest.value = structuredClone(toRaw(listBlockSuggest.value));
}

/** 住所番地候補選択時 */
function selectSuggestBlock() {
    inputAddressDto.value.addressBlock = selectedAddressBlock.value;

    // TODO 現状は選択肢でコードと名称だけだが、公共団体コードなどを紐づけて利用する
    inputAddressDto.value.machiazaId = "2345678";
    inputAddressDto.value.blkId = "123";
    inputAddressDto.value.rsdtId = "456";

    //下層のサジェストの作成
    listBuildingSuggest.value = mockMakeSuggestBuildingList();
}

/** 住所建物候補選択時 */
function selectSuggestBuilding() {
    inputAddressDto.value.addressBuilding = selectedAddressBuilding.value;
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
                @input="mockMakeAddressPostal">&nbsp;-&nbsp;
            <input v-model="inputAddressDto.postalcode2" type="text" class="code-input" @input="mockMakeAddressPostal">
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
            <textarea v-model="inputAddressDto.addressPostal"
                :disabled="!inputAddressDto.isEditAddressPostal"></textarea>
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
            <span>地方公共団体コード</span><input type="text" v-model="inputAddressDto.lgCode"  class="code-input" disabled="true">
            <span class="left-space">町字Id</span><input type="text" v-model="inputAddressDto.machiazaId"  class="code-input" disabled="true">
            <span class="left-space">街区Id</span><input type="text" v-model="inputAddressDto.blkId"  class="code-input" disabled="true">
            <span class="left-space">住居Id</span><input type="text" v-model="inputAddressDto.rsdtId" class="code-input" disabled="true">
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
