<script setup lang="ts">
import { ref, Ref } from "vue";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import ReadCsvNoOpenCapsuleInterface from "../../../dto/storage/readCsvNoOpenCapsuleDto";
import ReadCsvNoOpenCapsuleDto from "../../../dto/storage/readCsvNoOpenCapsuleDto";
import SaveStorageResultDto from "../../../dto/storage/saveStorageResultDto";
import ReadCsvNoOpenHasHeaderPageActionConditionDto from "../../../dto/read_csv/readCsvNoOpenHasHeaderPageActionConditionDto";

//props,emit
const props = defineProps<{
    conditionDto: ReadCsvNoOpenHasHeaderPageActionConditionDto,
    displayText: string,
}>();

const emits = defineEmits(["sendReadCsvNoOpenCapsuleInterface"]);

// ヘッダ有無
const hasHeader:boolean = props.conditionDto.hasHeader;

// 文字コード固定
const charsetValue:string = props.conditionDto.charsetValue;
//文字コード
const selectedCharCode = ref(charsetValue);
// 文字コード固定
const isFixShowCharset:boolean = !props.conditionDto.isFixShowCharset;

//ファイル指定ダイアログ
const selectFileInput: Ref<HTMLInputElement | undefined> = ref<HTMLInputElement>();
/**
 * ファイル選択ダイアログを表示する
 */
function onReadButton() {
    if (selectFileInput.value !== undefined) {
        selectFileInput.value.click();
    }
}

/**
 * 指定されたファイルを読み込む
 */
async function readTextFile() {
    //ファイルの読み取り
    if (selectFileInput.value !== null) {
        if (selectFileInput.value !== undefined) {
            if (selectFileInput.value.files !== null) {
                const file: File = selectFileInput.value.files[0];
                const reader: FileReader = new FileReader();
                reader.readAsText(file, selectedCharCode.value);
                reader.onload = async () => {
                    if (reader.result !== null) {

                        const capsuleDto:ReadCsvNoOpenCapsuleInterface = new ReadCsvNoOpenCapsuleDto();

                        //必要な情報がそろったので親へデータを渡す
                        //const readCsvFileCapsuleDto: ReadCsvFileCapsuleDto = new ReadCsvFileCapsuleDto();
                        //セッションストレージ取得
                        capsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
                        capsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
                        //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
                        capsuleDto.checkTransactionDto = createCheckTransactionDto(true);

                        capsuleDto.content = String(reader.result);
                        capsuleDto.hasHeader = hasHeader;

                        // TODO アップロード(fetch)処理

                        const storageResultDto:SaveStorageResultDto = new SaveStorageResultDto;
                        storageResultDto.shoshouId = 100;
                        emits("sendReadCsvNoOpenCapsuleInterface", storageResultDto);

                        //readCsvFileCapsuleDto.fileContent = String(reader.result);
                        //readCsvFileCapsuleDto.fileName = file.name;
                        //csvファイルデータからcsv設定データに変換
                        //const url = "http://localhost:8080/read-csv-by-file/practice";

                        //const method = "POST";
                        //const body = JSON.stringify(readCsvFileCapsuleDto);
                        //const headers = {
                        //    'Accept': 'application/json',
                        //    'Content-Type': 'application/json'
                        //};

                        //fetch(url, { method, headers, body })
                        //    .then(async (response) => {

                        //        sendCsvAndStragedShoshouDto.value = await response.json();
                        //        readData.value = sendCsvAndStragedShoshouDto.value.listAllCsv;
                        //        emits("sendGeneralCsvDataInterface", sendCsvAndStragedShoshouDto.value);
                        //
                        //    })
                        //    .catch((error) => { alert(error); });
                    }
                };
            }
        }
    }
}


</script>
<template>
    <div class="left-area">
        読取りファイルの指定<br>
       {{ props.displayText }} 
    </div>
    <div class="right-area">
        <input ref="selectFileInput" type="file" accept=".csv" @change="readTextFile" style="display:none;">
        <button @click="onReadButton">ファイルを指定して読み取り</button>
            文字が読めない場合
        <select v-model="selectedCharCode" :disabled="isFixShowCharset">
            <option value="UTF-8">UTF-8</option>
            <option value="Shift_JIS">Shift_JIS(Windows)</option>
        </select>
    </div>
    <div class="clear-both"></div>
</template>
<style scoped></style>
