<script setup lang="ts">
import { computed, type ComputedRef, } from 'vue';
import type InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';

//props,emit
const props = defineProps<{ editDto: InputPersonNameInterface }>();
const emits = defineEmits(["sendCancelInputPersonName", "sendInputPersonNameInterface"]);

// 編集Dto
const  inputNameDto: ComputedRef<InputPersonNameInterface> = computed(() => { return props.editDto });

function onCancel(){
    emits("sendCancelInputPersonName");
}

function onSave(){
    emits("sendInputPersonNameInterface",inputNameDto.value);
}

</script>
<template>
    <h3>姓名入力</h3>
    <div class="left-area">
        姓名かな
    </div>
    <div class="right-area">
        <span>姓<input type="text" v-model="inputNameDto.lastNameKana" class="text-input"> </span>
        <span class="left-space">ミドルネーム<input type="text" v-model="inputNameDto.middleNameKana" class="text-input"> </span>
        <span class="left-space">名<input type="text" v-model="inputNameDto.firstNameKana" class="text-input"> </span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        姓名漢字
    </div>
    <div class="right-area">
        <span>姓<input type="text" v-model="inputNameDto.lastName" class="text-input"> </span>
        <span class="left-space">ミドルネーム<input type="text" v-model="inputNameDto.middleName" class="text-input"> </span>
        <span class="left-space">名<input type="text" v-model="inputNameDto.firstName" class="text-input"> </span>
    </div>
    <div class="clear-both"></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">選択</button>
    </div>

</template>
<style scoped></style>
