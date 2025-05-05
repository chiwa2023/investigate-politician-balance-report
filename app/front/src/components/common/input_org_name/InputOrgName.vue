<script setup lang="ts">
import { computed, type ComputedRef } from 'vue';
import type InputOrgNameInterface from '../../../dto/input_org_name/inputOrgNameDto';
const props = defineProps<{ editDto: InputOrgNameInterface }>();

const inputDto: ComputedRef<InputOrgNameInterface> = computed(() => { return props.editDto });;

</script>
<template>
    <div class="left-area">
        団体名かな
    </div>
    <div class="right-area">
        <input type="text" v-model="inputDto.orgNameKana" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        団体名
    </div>
    <div class="right-area">
        <input type="text" v-model="inputDto.orgName" class="text-input">
    </div>
    <div class="clear-both"></div>

</template>
<style scoped></style>
