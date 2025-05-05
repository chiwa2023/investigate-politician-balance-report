<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import InputPersonName from './InputPersonName.vue';
import type InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';


// props,emit
const props = defineProps<{ editDto: InputPersonNameInterface, isRaiseEditView: boolean }>();
const emits = defineEmits(["sendCancelInputPersonName", "sendInputPersonNameInterface"]);
const isInput: Ref<boolean> = ref(false);

// 編集Dto
const  inputNameDto: ComputedRef<InputPersonNameInterface> = computed(() => { return props.editDto });

function onInputPersonName() {

    // 表示
    isInput.value = true;
}


function recieveCancelInputPersonName() {

    // 非表示
    isInput.value = false;
}

function recieveInputPersonNameInterface(sendDto: InputPersonNameInterface) {

    inputNameDto.value.firstName = sendDto.firstName;
    inputNameDto.value.lastName = sendDto.lastName;
    inputNameDto.value.middleName = sendDto.middleName;
    inputNameDto.value.firstNameKana = sendDto.firstNameKana;
    inputNameDto.value.lastNameKana = sendDto.lastNameKana;
    inputNameDto.value.middleNameKana = sendDto.middleNameKana;

    inputNameDto.value.allNameKana = inputNameDto.value.lastNameKana + "　" + inputNameDto.value.middleNameKana + inputNameDto.value.firstNameKana;
    inputNameDto.value.allName = inputNameDto.value.lastName + "　" + inputNameDto.value.middleName + inputNameDto.value.firstName;;

    // 非表示
    isInput.value = false;
}

</script>
<template>
    <div class="left-area">
        姓名かな
    </div>
    <div class="right-area">
        <input type="text" v-model="inputNameDto.allNameKana" class="max-input" disabled="true"><span
            class="left-space"><button @click="onInputPersonName">編集</button> </span>
        <br>
        <span>姓<input type="text" class="text-input" v-model="inputNameDto.lastNameKana" disabled="true"> </span>
        <span class="left-space">ミドルネーム<input type="text" v-model="inputNameDto.middleNameKana" class="text-input"
                disabled="true"> </span>
        <span class="left-space">名<input type="text" v-model="inputNameDto.firstNameKana" class="text-input"
                disabled="true"> </span>

    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        姓名漢字
    </div>
    <div class="right-area">
        <input type="text" v-model="inputNameDto.allName" class="max-input" disabled="true">
        <br>
        <span>姓<input type="text" v-model="inputNameDto.lastName" class="text-input" disabled="true"> </span>
        <span class="left-space">ミドルネーム<input type="text" v-model="inputNameDto.middleName" class="text-input"
                disabled="true"> </span>
        <span class="left-space">名<input type="text" v-model="inputNameDto.firstName" class="text-input" disabled="true">
        </span>
    </div>
    <div class="clear-both"></div>

    <div v-if="isInput" class="overBackground"></div>
    <div v-if="isInput">
        <div class="overComponent">
            <InputPersonName v-if="isInput" :edit-dto="inputNameDto" @send-cancel-input-person-name="recieveCancelInputPersonName"
                @send-input-person-name-interface="recieveInputPersonNameInterface">
                ></InputPersonName>
        </div>
    </div>

</template>
<style scoped></style>
