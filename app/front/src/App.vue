<script setup lang="ts">
//import HelloWorld from './components/HelloWorld.vue'
import  SurveyIndicatorDto from './dto/surveyIndicator';

import SurveyIndicator from './components/survey/SurveyIndicator.vue';

const indicatorValue:SurveyIndicatorDto = new SurveyIndicatorDto();
indicatorValue.indicatorExplain = "計算式";
indicatorValue.indicatorAnswer = 99999;
indicatorValue.indicatorAnswerText = "99,999";
indicatorValue.indicatorUnit = "%";
indicatorValue.indicatorResultDetail = "適当な説明";

</script>

<template>
  <!--

  <div>
    <a href="https://vitejs.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a>
  </div>
  <HelloWorld msg="Vite + Vue" />
  -->


  <SurveyIndicator :indicator="indicatorValue"></SurveyIndicator>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
