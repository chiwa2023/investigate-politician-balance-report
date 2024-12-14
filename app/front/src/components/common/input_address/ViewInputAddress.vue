<script setup lang="ts">
import { computed, WritableComputedRef } from "vue";
import InputAddressDto from "../../../dto/Input_address/inputAddressDto";

//props,emit
const props = defineProps<{ editDto: InputAddressDto }>();

/** 入力用Dto */
const inputAddressDto: WritableComputedRef<InputAddressDto> = computed(() => {return props.editDto});

</script>
<template>
    <div class="left-area-in">
        郵便番号
    </div>
    <div class="right-area">
        <input v-model="inputAddressDto.postalcode1" type="text" class="code-input" disabled="true">&nbsp;-&nbsp;
        <input v-model="inputAddressDto.postalcode2" type="text" class="code-input" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area-in">
        住所1
    </div>
    <div class="right-area">
        <textarea v-model="inputAddressDto.addressPostal" disabled="true"></textarea>
    </div>
    <div class="clear-both"></div>

    <div class="left-area-in">
        番地
    </div>
    <div class="right-area">
        <textarea v-model="inputAddressDto.addressBlock" disabled="true"></textarea>
    </div>
    <div class="clear-both"></div>

    <div class="left-area-in">
        建物
    </div>
    <div class="right-area">
        <textarea v-model="inputAddressDto.addressBuilding" disabled="true"></textarea>
    </div>
    <div class="clear-both"></div>

    <div class="left-area-in">
        電話番号
    </div>
    <div class="right-area">
        <input v-model="inputAddressDto.tel1" type="text" class="code-input" disabled="true">&nbsp;-&nbsp;
        <input v-model="inputAddressDto.tel2" type="text" class="code-input" disabled="true">&nbsp;-&nbsp;
        <input v-model="inputAddressDto.tel3" type="text" class="code-input" disabled="true">
    </div>
    <div class="clear-both"></div>

</template>
<style scoped>
</style>
