<script setup lang="ts">
import { ref, Ref } from "vue";
import ReadXmlCapsuleInterface from "../../../dto/read_xml/readXmlCapsuleDto";
import ReadXmlCapsuleDto from "../../../dto/read_xml/readXmlCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import Sheet0801Dto from "../../../dto/usage/sheet0801Dto";
import Sheet0801Interface from "../../../dto/usage/sheet0801Dto";
import ReadXmlUsageReportResultInterface from "../../../dto/read_xml/usgae/readXmlUsageReportResultDto";
import ReadXmlUsageReportResultDto from "../../../dto/read_xml/usgae/readXmlUsageReportResultDto";

const emits = defineEmits(["sendReadXmlUsageResultInterface"]);

const selectFileInput = ref<HTMLInputElement>();

const readXmlResultDto: Ref<ReadXmlUsageReportResultInterface> = ref(new ReadXmlUsageReportResultDto());

const sheet0801Dto: Ref<Sheet0801Interface> = ref(new Sheet0801Dto());

/**
 * ファイル選択ダイアログを表示する
 */
function onReadButton() {
    selectFileInput.value?.click();
}

const selectFileName: Ref<string> = ref("");
const readTextFile = async () => {
    //ファイル名を表示
    if (selectFileInput.value !== null) {
        if (selectFileInput.value !== undefined) {
            if (selectFileInput.value.files !== null) {
                const file = selectFileInput.value.files[0];
                selectFileName.value = file.name;

                const reader: FileReader = new FileReader();
                reader.readAsText(file, "shift_jis");
                reader.onload = async () => {
                    if (reader.result !== null) {
                        //alert(String(reader.result));

                        //必要な情報がそろったので親へデータを渡す
                        const readXmlCapsuleDto: ReadXmlCapsuleInterface = new ReadXmlCapsuleDto();
                        //セッションストレージ取得
                        readXmlCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
                        readXmlCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
                        //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
                        readXmlCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);

                        readXmlCapsuleDto.fileContent = String(reader.result);
                        readXmlCapsuleDto.fileName = file.name;
                        readXmlCapsuleDto.charset = "Windows-31J";

                        // xmlを書証として保存、保存のない仮解析をして必要なところだけ戻す
                        const url = "http://localhost:9080/xml-party-usage/read";
                        const method = "POST";
                        const body = JSON.stringify(readXmlCapsuleDto);
                        const headers = {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        };

                        fetch(url, { method, headers, body })
                            .then(async (response) => {
                                readXmlResultDto.value = await response.json();
                                sheet0801Dto.value = readXmlResultDto.value.coverDto;
                                emits("sendReadXmlUsageResultInterface", readXmlResultDto.value);
                            })
                            .catch((error) => { alert(error); });
                    }

                }
            };

        }
    }
};

</script>
<template>
    <div class="one-line">
        政治資金収支報告書XMLファイル(SHUSHI.xml)を指定して読み取り<input ref="selectFileInput" type="file" accept=".xml"
            @change="readTextFile" style="visibility: hidden;"><br>
        &nbsp;<input v-model="selectFileName" type="text" disabled="true" style="width: 50%;"><button
            @click="onReadButton" style="margin-left: 1%;">ファイルを指定</button><br>
    </div>
    <div class="left-area">
        <label>発行年</label>
    </div>
    <div class="right-area">
        {{ sheet0801Dto.nendo }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>政治団体名称</label>
    </div>
    <div class="right-area">
        {{ sheet0801Dto.partyName }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>代表者名</label>
    </div>
    <div class="right-area">
        {{ sheet0801Dto.delegateName }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>会計責任者</label>
    </div>
    <div class="right-area">
        {{ sheet0801Dto.accountManagerName }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>事務所住所</label>
    </div>
    <div class="right-area">
        {{ sheet0801Dto.officeAddress }}
    </div>
    <div style="clear:both" />
</template>
<style scoped></style>
