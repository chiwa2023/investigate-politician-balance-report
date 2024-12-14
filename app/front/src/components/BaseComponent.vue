<script setup lang="ts">
import { Ref, ref } from "vue";
import InputAddressDto from "../dto/Input_address/inputAddressDto";
import RelationPersonInterface from "../dto/relation/relationPersonDto";
import BalancesheetXmlUpload from "./common/balancesheet-xml-upload/BalancesheetXmlUpload.vue";
import InputAddressCode from "./common/input_address_code/InputAddressCode.vue";

// 編集
const editDto: Ref<InputAddressDto> = ref(new InputAddressDto());
editDto.value.addressAll = "東京都千代田区"
editDto.value.orginAddressAll = "東京都千代田区"

//住所入力表示フラグ
const isVisibleInputAddress: Ref<boolean> = ref(false);

/** 住所 */
const editId: Ref<string> = ref("");
/**
 * 住所入力コンポーネント表示
 */
function onInputAddress(id: string) {
    editId.value = id;
    isVisibleInputAddress.value = true;
}

/**
 * 住所入力キャンセル
 */
function recieveCancelInputAddress() {
    //非表示
    isVisibleInputAddress.value = false;
}

/**
 * 住所入力選択
 * @param sendDto 選択Dto
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {

    editDto.value.postalcode1 = sendDto.postalcode1;
    editDto.value.postalcode2 = sendDto.postalcode2;

    editDto.value.addressPostal = sendDto.addressPostal;
    editDto.value.addressBlock = sendDto.addressBlock;
    editDto.value.addressBuilding = sendDto.addressBuilding;

    editDto.value.addressAll = editDto.value.addressPostal + editDto.value.addressBlock + "　" + editDto.value.addressBuilding;

    editDto.value.tel1 = sendDto.tel1;
    editDto.value.tel2 = sendDto.tel2;
    editDto.value.tel3 = sendDto.tel3;

    editDto.value.lgCode = sendDto.lgCode;
    editDto.value.machiazaId = sendDto.machiazaId;
    editDto.value.blkId = sendDto.blkId;
    editDto.value.rsdtId = sendDto.rsdtId;

    //非表示
    isVisibleInputAddress.value = false;
}

</script>
<template>
    <h1>コンポーネントをページと関係なく作成するための台紙</h1>

    {{ editDto.addressAll }}<br>
    {{ editDto.orginAddressAll }}<br>

    <hr>

    <InputAddressCode :selected-dto="editDto" @send-cancel-input-address="recieveCancelInputAddress"
        @send-input-address-interface="recieveInputAddressInterface"></InputAddressCode>

</template>
<style scoped></style>
