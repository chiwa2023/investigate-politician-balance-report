<script setup lang="ts">

import { Ref,computed } from 'vue'
import  SurveyIndicatorDto from '../../dto/surveyIndicator'
import  SurveyIndicatorInterface from '../../dto/surveyIndicator'

const props = defineProps<{
    indicator: SurveyIndicatorDto
}>();

let indicatorValue: Ref<SurveyIndicatorInterface> = computed(() => props.indicator);

</script>

<template>
    <!-- TODO cssについては修正を行う -->
    <div>
        <div style="float: left; margin-right: 1%;width:8%;text-align: center;">
            <button>計算式</button>
        </div>
        <div style="float: left; margin-right: 1%;width:20%;text-align: left;">
            <label>{{ indicatorValue.indicatorTitle }}</label>は<br>
        </div>
        <div style="float: right;margin-left: 1%;width:69%;">
            <input type="text" v-model="indicatorValue.indicatorAnswer" :disabled="true" style="text-align:right;">&nbsp;
            <input type="text" v-model="indicatorValue.indicatorUnit" :disabled="true" style="text-align:right;width: 5%;">です。<br />
            <input type="text" v-model="indicatorValue.indicatorResultDetail" :disabled="true" style="width: 95%;">
        </div>
    </div>
</template>

<style scoped></style>