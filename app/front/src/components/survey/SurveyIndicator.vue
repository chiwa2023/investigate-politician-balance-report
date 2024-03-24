<script setup lang="ts">

import { Ref,computed,ref } from "vue";
import  SurveyIndicatorDto from "../../dto/surveyIndicatorDto";
import  SurveyIndicatorInterface from "../../dto/surveyIndicatorDto";
import  TerminologyHelp from "../common/TerminologyHelp.vue";

const props = defineProps<{
    indicator: SurveyIndicatorDto
}>();
let indicatorValue: Ref<SurveyIndicatorInterface> = computed(() => props.indicator);

/** 用語ヘルプを表示する */
const isVisibleTerminology:Ref<boolean> = ref(false); 

/**
 * 用語ヘルプコンポーネントを表示する
 */
function viewTerminology(){
    isVisibleTerminology.value = true;
}
/** 用語ヘルプを非表示にする */
function recieveCloseTerminology(){
    isVisibleTerminology.value = false;
}
const terminologyId = 0;
</script>

<template>
    <!-- TODO cssについては修正を行う -->
    <div style="clear: both;" />
    <div>
        <div style="float: left;width: 17%; text-align: right;padding-right: 1%;">
            <span style="text-align: left;">
            <button @click="viewTerminology">計算式</button>
            </span>
            <span style="text-align: right;margin-left: 5%;">
            <label>{{ indicatorValue.indicatorTitle }}</label>は
            </span>
        </div>
        <div style="float: left;width: 82%;">
            <input type="text" v-model="indicatorValue.indicatorAnswer" :disabled="true" style="text-align:right;">&nbsp;
            <input type="text" v-model="indicatorValue.indicatorUnit" :disabled="true" style="text-align:right;width: 5%;">です。<br />
            <input type="text" v-model="indicatorValue.indicatorResultDetail" :disabled="true" style="width: 95%;">
        </div>
    </div>
    <TerminologyHelp v-if="isVisibleTerminology" :terminologyId="terminologyId" :terminology-name="indicatorValue.indicatorTitle" :help-explain="indicatorValue.indicatorExplain"  @send-close-terminology="recieveCloseTerminology"></TerminologyHelp>
</template>

<style scoped></style>