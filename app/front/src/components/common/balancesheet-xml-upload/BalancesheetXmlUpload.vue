<script setup lang="ts">
import { ref, Ref } from 'vue';
import getMockAllBookDto from './mock/getMockAllBookDto';
import AllBookDto from '../../../dto/balancesheet/allBookDto';

const selectFileInput = ref<HTMLInputElement>();

const houkokuNen: Ref<number> = ref(0);
const dantaiName: Ref<string> = ref("");
const dantaiManagerName: Ref<string> = ref("");
const dantaiOfficerName: Ref<string> = ref("");
const dantaiJimushoAddress: Ref<string> = ref("");
const dantaiKbn: Ref<string> = ref("");
const umuShikinKanriDantai: Ref<number> = ref(0);

const allBookDto: Ref<AllBookDto> = ref(new AllBookDto());
function onReadButton() {
    selectFileInput.value?.click();
}

const selectFileName:Ref<string> = ref("");
const readTextFile = async () => {
    //ファイル名を表示
    if (selectFileInput.value !== null) {
        if (selectFileInput.value !== undefined) {
            if (selectFileInput.value.files !== null) {
                const file = selectFileInput.value.files[0];
                selectFileName.value = file.name;
            }
        }
    }
    
    //データを取得して表示
    allBookDto.value = await getMockAllBookDto();
    
    houkokuNen.value = allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.houkokuNen;
    dantaiName.value = allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.dantaiName;
    dantaiManagerName.value = allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.daihyoushaNameLast + allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.daihyoushaNameFirst;
    dantaiOfficerName.value = allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.kaikeiSekinnshaNameLast + allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.kaikeiSekinnshaNameFirst;
    dantaiJimushoAddress.value = allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.jimushoJusho + allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.jimushoJushoTatemono;
    dantaiKbn.value = allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.dantaiKbn;
    umuShikinKanriDantai.value = allBookDto.value.allSheet0701CoverAndOrganizationDetailsDto.sheet070100CoverAndOrganizationDetailsDto.umuShikinKanrenDantai    
};

</script>
<template>
    <div class="one-line">
        政治資金収支報告書XMLファイル(SHUSHI.xml)を指定して読み取り<input ref="selectFileInput" type="file" accept=".xml" @change="readTextFile" style="visibility: hidden;"><br>
        &nbsp;<input v-model="selectFileName" type="text" disabled="true" style="width: 50%;"><button @click="onReadButton" style="margin-left: 1%;">ファイルを指定</button><br>
    </div>
    <div class="left-area">
        <label>発行年</label>
    </div>
    <div class="right-area">
        {{ houkokuNen }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>政治団体名称</label>
    </div>
    <div class="right-area">
        {{ dantaiName }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>代表者名</label>
    </div>
    <div class="right-area">
        {{ dantaiManagerName }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>会計責任者</label>
    </div>
    <div class="right-area">
        {{ dantaiOfficerName }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>事務所住所</label>
    </div>
    <div class="right-area">
        {{ dantaiJimushoAddress }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label> 団体区分</label>
    </div>
    <div class="right-area">
        {{ dantaiKbn }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>資金管理団体の有無</label>
    </div>
    <div class="right-area">
        {{ umuShikinKanriDantai }}
    </div>
</template>
<style scoped></style>
