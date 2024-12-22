<script setup lang="ts">
import { ref, Ref } from "vue";
import SaveStorageResultDto from '../../../dto/storage/saveStorageResultDto';
import ReadCsvNoShowHeader from '../../common/read_csv/ReadCsvNoShowHeader.vue';
import ZenginMasterCsvCapsuleInterface from "../../../dto/zegin_master/zenginMasterCsvCapsuleDto";
import ZenginMasterCsvCapsuleDto from "../../../dto/zegin_master/zenginMasterCsvCapsuleDto";
import ReadCsvNoOpenHasHeaderPageActionConditionDto from "../../../dto/read_csv/readCsvNoOpenHasHeaderPageActionConditionDto";

// 実際に解析に使うCapsukeDto
const capsuleDto: Ref<ZenginMasterCsvCapsuleInterface> = ref(new ZenginMasterCsvCapsuleDto());

const conditionDto: ReadCsvNoOpenHasHeaderPageActionConditionDto = new ReadCsvNoOpenHasHeaderPageActionConditionDto();
conditionDto.hasHeader = true; //TODO 現物入手次第修正する
conditionDto.charsetValue = "UTF-8"; // 文字コードはUTF-8指定が明記
conditionDto.isFixShowCharset = false; // 文字コードの修正の必要がない

function recieveReadCsvNoOpenCapsuleInterface1(recieveDto: SaveStorageResultDto) {
    capsuleDto.value.csvData1 = recieveDto;
}

function recieveReadCsvNoOpenCapsuleInterface2(recieveDto: SaveStorageResultDto) {
    capsuleDto.value.csvData2 = recieveDto;
}

function recieveReadCsvNoOpenCapsuleInterface3(recieveDto: SaveStorageResultDto) {
    capsuleDto.value.csvData3 = recieveDto;
}

function recieveReadCsvNoOpenCapsuleInterface4(recieveDto: SaveStorageResultDto) {
    capsuleDto.value.csvData4 = recieveDto;
}

function onCancel() {
    alert("キャンセル");
}
function onSave() {
    alert("実行");
}
</script>
<template>
    <h1>全銀協異動csvアップロード</h1>
    <!-- ファイル1 -->
    <ReadCsvNoShowHeader :condition-dto="conditionDto" :display-text="'ファイル1(0-199)'"
        @send-read-csv-no-open-capsule-interface="recieveReadCsvNoOpenCapsuleInterface1">
    </ReadCsvNoShowHeader>

    <!-- ファイル2 -->
    <ReadCsvNoShowHeader :condition-dto="conditionDto" :display-text="'ファイル2(200-999)'"
        @send-read-csv-no-open-capsule-interface="recieveReadCsvNoOpenCapsuleInterface2">
    </ReadCsvNoShowHeader>

    <!-- ファイル3 -->
    <ReadCsvNoShowHeader :condition-dto="conditionDto" :display-text="'ファイル3(1000-2999)'"
        @send-read-csv-no-open-capsule-interface="recieveReadCsvNoOpenCapsuleInterface3">
    </ReadCsvNoShowHeader>

    <!-- ファイル4 -->
    <ReadCsvNoShowHeader :condition-dto="conditionDto" :display-text="'ファイル4(3000-9999)'"
        @send-read-csv-no-open-capsule-interface="recieveReadCsvNoOpenCapsuleInterface4">
    </ReadCsvNoShowHeader>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">保存</button>
    </div>

</template>
<style scoped></style>
