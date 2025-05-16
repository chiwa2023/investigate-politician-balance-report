<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from "vue";
import InputAddressDto from "../../../dto/Input_address/inputAddressDto";
import InputAddress from "./InputAddress.vue";

//props,emit
const props = defineProps<{ editDto: InputAddressDto, isRaiseEditView: boolean }>();
const emits = defineEmits(["sendInputAddressInterface", "sendCanceelinputAddress"]);

/** 入力用Dto */
const inputAddressDto: ComputedRef<InputAddressDto> = computed(() => { return props.editDto });

const isInput: Ref<boolean> = ref(false);

const backupAddressDto: Ref<InputAddressDto>= ref(new InputAddressDto());

/**
 * 関連者検索コンポーネント表示
 */
function onInputAddress() {
    backupAddressDto.value = structuredClone(toRaw(inputAddressDto.value));
    isInput.value = true;
}

/**
 * 関連者検索キャンセル
 */
function recieveCancelInputAddress() {


    inputAddressDto.value.addressAll = backupAddressDto.value.tel1;
    inputAddressDto.value.orginAddressAll = backupAddressDto.value.tel1;

    inputAddressDto.value.postalcode1 = backupAddressDto.value.postalcode1;
    inputAddressDto.value.postalcode2 = backupAddressDto.value.postalcode2;

    inputAddressDto.value.addressPostal = backupAddressDto.value.addressPostal;
    inputAddressDto.value.isEditAddressPostal = backupAddressDto.value.isEditAddressPostal;
    inputAddressDto.value.addressBlock = backupAddressDto.value.addressBlock;
    inputAddressDto.value.isEditAddressBlock = backupAddressDto.value.isEditAddressBlock;
    inputAddressDto.value.addressBuilding = backupAddressDto.value.addressBuilding;
    inputAddressDto.value.isEditAddressBuilding = backupAddressDto.value.isEditAddressBuilding;

    inputAddressDto.value.lgCode = backupAddressDto.value.lgCode;
    inputAddressDto.value.machiazaId = backupAddressDto.value.machiazaId;
    inputAddressDto.value.rsdtId = backupAddressDto.value.rsdtId;
    inputAddressDto.value.blkId = backupAddressDto.value.blkId;

    inputAddressDto.value.tel1 = backupAddressDto.value.tel1;
    inputAddressDto.value.tel2 = backupAddressDto.value.tel2;
    inputAddressDto.value.tel3 = backupAddressDto.value.tel3;
    
    //非表示
    isInput.value = false;
}

/**
 * 関連者検索選択
 * @param sendDto 選択Dto
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {

    inputAddressDto.value.addressAll = sendDto.tel1;
    inputAddressDto.value.orginAddressAll = sendDto.tel1;

    inputAddressDto.value.postalcode1 = sendDto.postalcode1;
    inputAddressDto.value.postalcode2 = sendDto.postalcode2;

    inputAddressDto.value.addressPostal = sendDto.addressPostal;
    inputAddressDto.value.isEditAddressPostal = sendDto.isEditAddressPostal;
    inputAddressDto.value.addressBlock = sendDto.addressBlock;
    inputAddressDto.value.isEditAddressBlock = sendDto.isEditAddressBlock;
    inputAddressDto.value.addressBuilding = sendDto.addressBuilding;
    inputAddressDto.value.isEditAddressBuilding = sendDto.isEditAddressBuilding;

    inputAddressDto.value.lgCode = sendDto.lgCode;
    inputAddressDto.value.machiazaId = sendDto.machiazaId;
    inputAddressDto.value.rsdtId = sendDto.rsdtId;
    inputAddressDto.value.blkId = sendDto.blkId;

    inputAddressDto.value.tel1 = sendDto.tel1;
    inputAddressDto.value.tel2 = sendDto.tel2;
    inputAddressDto.value.tel3 = sendDto.tel3;

    //非表示
    isInput.value = false;
}

</script>
<template>
    <div class="left-area">
        郵便番号
    </div>
    <div class="right-area">
        <input v-model="inputAddressDto.postalcode1" type="text" class="code-input" disabled="true">&nbsp;-&nbsp;
        <input v-model="inputAddressDto.postalcode2" type="text" class="code-input" disabled="true">
        <button v-if="isRaiseEditView" @click="onInputAddress" class="left-space"> 編集</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所1
    </div>
    <div class="right-area">
        <textarea v-model="inputAddressDto.addressPostal" disabled="true"></textarea>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        番地
    </div>
    <div class="right-area">
        <textarea v-model="inputAddressDto.addressBlock" disabled="true"></textarea>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        建物
    </div>
    <div class="right-area">
        <textarea v-model="inputAddressDto.addressBuilding" disabled="true"></textarea>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所コード
    </div>
    <div class="right-area">
        <span>地方公共団体コード</span><input v-model="inputAddressDto.lgCode" type="text" class="code-input" disabled="true">
        <span class="left-space">町字Id</span><input v-model="inputAddressDto.machiazaId" type="text" class="code-input"
            disabled="true">
        <span class="left-space">街区Id</span><input v-model="inputAddressDto.blkId" type="text" class="code-input"
            disabled="true">
        <span class="left-space">住居Id</span><input v-model="inputAddressDto.rsdtId" type="text" class="code-input"
            disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        電話番号
    </div>
    <div class="right-area">
        <input v-model="inputAddressDto.tel1" type="text" class="code-input" disabled="true">&nbsp;-&nbsp;
        <input v-model="inputAddressDto.tel2" type="text" class="code-input" disabled="true">&nbsp;-&nbsp;
        <input v-model="inputAddressDto.tel3" type="text" class="code-input" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div v-if="isInput" class="overBackground"></div>
    <div v-if="isInput">
        <div class="overComponent">
            <InputAddress v-if="isInput" :edit-dto="inputAddressDto"
                @send-cancel-input-address="recieveCancelInputAddress"
                @send-input-address-interface="recieveInputAddressInterface">
                ></InputAddress>
        </div>
    </div>

</template>
<style scoped></style>
