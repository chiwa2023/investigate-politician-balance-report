<script setup lang="ts">
import SurveyIndicatorDto from "./../../../dto/surveyIndicatorDto";
import SelectOptionDto from "./../../../dto/selectOptionDto";
import createMockSurveyGroupOptions from "./mock/createMockSurveyGroupOptions";
import getMockSurveySelector from "./mock/getMockSurveySelector";
import getMockSurveyView from "./mock/getMockSurveyView";
import { Ref, ref ,onBeforeMount ,watch} from "vue";
import SurveyIndicator from "./SurveyIndicator.vue";
import SurveySelecterInterface from "../../../dto/survey/surveySelecterDto";
import getAllOrganizationUnique from "./getAllOrganizationUnique";
import getAllYearTeishutsuUnique from "./getAllYearTeishutsuUnique";
import filterYearTeishutsu from "./filterYearTeishutsu";
import filterOrganization from "./filterOrganization";

//選択リスト
const yearList: Ref<SelectOptionDto[]> = ref([]);
const organizationList: Ref<SelectOptionDto[]> = ref([]);
const surveyList: Ref<SelectOptionDto[]> = ref([]);
const surveyIndicatorList: Ref<SurveyIndicatorDto[]> = ref([]);
const noSelect: string = "";
const isNotCompleteSelect:Ref<boolean> = ref(true);

//選択された項目
const selectedYear: Ref<string> = ref(noSelect);
const selectedOrganaization: Ref<string> = ref(noSelect);
const selectedSurvey: Ref<string> = ref(noSelect);
const surveySelectorList: Ref<SurveySelecterInterface[]> = ref([]);

/** 初期設定 */
onBeforeMount(async () => {
    //初期リスト
    surveySelectorList.value = getMockSurveySelector();

    //初期表示用に候補全リストを生成(ユーザさんが概要を把握するためだけ用)
    yearList.value  = getAllYearTeishutsuUnique(surveySelectorList.value);
    organizationList.value  = getAllOrganizationUnique(surveySelectorList.value);
    
    //調査指標グループを生成
    surveyList.value  = createMockSurveyGroupOptions();
});

/* 選択された検索条件に従って指標表示を更新する */
watch(selectedSurvey, () => {
    if(selectedYear.value !== noSelect && selectedOrganaization.value !== noSelect){
        surveyIndicatorList.value = getMockSurveyView(Number(selectedSurvey.value)); 
    }
});

/* 検索基準を変更する */
const isYearCondition:Ref<boolean> = ref(true);
const yearConditionText = "提出年を変更すると政治団体が絞り込まれます";
const organizationConditionText = "政治団体を変更すると提出年が絞り込まれます";
const conditionText:Ref<string> = ref(yearConditionText);
/**
 * 提出年が変更時
 */
function changeCondition(){
    isYearCondition.value = !isYearCondition.value;
    if(isYearCondition.value){
        conditionText.value = yearConditionText;
        selectedOrganaization.value = noSelect;
    }
    else{
        conditionText.value = organizationConditionText;
        selectedYear.value = noSelect;
    }
    organizationList.value  = getAllOrganizationUnique(surveySelectorList.value);
    yearList.value  = getAllYearTeishutsuUnique(surveySelectorList.value);
    surveyIndicatorList.value = [];
    selectedSurvey.value = noSelect;
}

/* 提出年が変更されたので団体を絞込 */
watch(selectedYear, () => {
    if(!isYearCondition.value){
        //政治団体を変更すると提出年を更新する場合は離脱
        if(selectedOrganaization.value !== noSelect && selectedYear.value !== noSelect){
            isNotCompleteSelect.value = false;
        }
        else{
            isNotCompleteSelect.value = true;
        }
        return;
    }
    //団体リストをフィルタ
    organizationList.value = filterOrganization(surveySelectorList.value,Number(selectedYear.value));
    selectedOrganaization.value = noSelect;
    //調査グループは無選択状態
    selectedSurvey.value = noSelect;
    surveyIndicatorList.value = [];
});

/* 団体が変更されたので提出年を絞込 */
watch(selectedOrganaization, () => {
    if(isYearCondition.value){
        //提出年を選択したら政治団体リストを更新する場合は離脱
        if(selectedOrganaization.value !== noSelect && selectedYear.value !== noSelect){
            isNotCompleteSelect.value = false;
        }
        else{
            isNotCompleteSelect.value = true;
        }
        return;
    }
    //年リストをフィルタ
    yearList.value = filterYearTeishutsu(surveySelectorList.value,Number(selectedOrganaization.value));
    selectedYear.value = noSelect;
    //調査グループは無選択状態
    selectedSurvey.value = noSelect;
    surveyIndicatorList.value = [];
});
</script>
<template>
    <h1>(仮)survey</h1>
    <div class="one-line">
        {{ conditionText }}<button @click="changeCondition">切替え</button><br>
    </div>
    <div class="left-area">
    <select v-model="selectedYear">
        <option v-for="option in yearList" v-bind:value="option.value" v-bind:key="option.value">{{ option.text }}
        </option>
    </select>
    </div>
    <div class="right-area">
        <span v-for="option in organizationList" :key="option.value">
        <input type="radio" :value="option.value" v-model="selectedOrganaization"/><label>{{ option.text }}</label>
    </span>
    </div>
    <br />
    <div class="left-area">
        <label>調査指標グループ</label>
    </div>
    <div class="right-area">
    <span v-for="option in surveyList" :key="option.value">
        <input type="radio" :value="option.value" :id="option.value" v-model="selectedSurvey" :disabled="isNotCompleteSelect"/><label>{{ option.text }}</label>
    </span>
    </div>
    <div class="one-line">
        <label>調査結果の表示</label><br>
        <div v-for="option in surveyIndicatorList" :key="option.indicatorTitle">
            <SurveyIndicator :indicator="option" ></SurveyIndicator>
        </div>
    </div>
</template>
<style scoped></style>
