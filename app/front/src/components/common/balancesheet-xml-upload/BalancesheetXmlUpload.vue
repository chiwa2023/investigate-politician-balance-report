<script setup lang="ts">
import { ref, Ref } from "vue";
import ReadXmlCapsuleInterface from "../../../dto/read_xml/readXmlCapsuleDto";
import ReadXmlCapsuleDto from "../../../dto/read_xml/readXmlCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import Sheet070100CoverAndOrganizationDetailsInterface from "../../../dto/balancesheet/sheet01/sheet070100CoverAndOrganizationDetailsDto";
import Sheet070100CoverAndOrganizationDetailsDto from "../../../dto/balancesheet/sheet01/sheet070100CoverAndOrganizationDetailsDto";
import ReadXmlBalancesheetResultInterface from "../../../dto/read_xml/readXmlBalancesheetResultDto";
import ReadXmlBalancesheetResultDto from "../../../dto/read_xml/readXmlBalancesheetResultDto";

const emits = defineEmits(["sendReadXmlBalancesheetResultInterface"]);

const selectFileInput = ref<HTMLInputElement>();

// 公式ソフトウェアでは名前、住所の連結には全角スペース
const BLANK: Ref<string> = ref("　");

const readXmlResultDto: Ref<ReadXmlBalancesheetResultInterface> = ref(new ReadXmlBalancesheetResultDto());

const cover070100Dto: Ref<Sheet070100CoverAndOrganizationDetailsInterface> = ref(new Sheet070100CoverAndOrganizationDetailsDto());

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
                        const url = "http://localhost:9080/xml-balancesheet/read";
                        const method = "POST";
                        const body = JSON.stringify(readXmlCapsuleDto);
                        const headers = {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        };

                        fetch(url, { method, headers, body })
                            .then(async (response) => {
                                readXmlResultDto.value = await response.json();
                                cover070100Dto.value = readXmlResultDto.value.coverDto;
                                emits("sendReadXmlBalancesheetResultInterface", readXmlResultDto.value);

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
        {{ cover070100Dto.houkokuNen }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>政治団体名称</label>
    </div>
    <div class="right-area">
        {{ cover070100Dto.dantaiName01 }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>代表者名</label>
    </div>
    <div class="right-area">
        {{ cover070100Dto.daihyoushaNameLast + BLANK + cover070100Dto.daihyoushaNameFirst }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>会計責任者</label>
    </div>
    <div class="right-area">
        {{ cover070100Dto.kaikeiSekinnshaNameLast + BLANK + cover070100Dto.kaikeiSekinnshaNameFirst }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>事務所住所</label>
    </div>
    <div class="right-area">
        {{ cover070100Dto.jimushoJusho + BLANK + cover070100Dto.jimushoJushoTatemono }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label> 団体区分</label>
    </div>
    <div class="right-area">
        {{ cover070100Dto.dantaiKbn }}
    </div>
    <div style="clear:both" />
    <div class="left-area">
        <label>資金管理団体の有無</label>
    </div>
    <div class="right-area">
        {{ cover070100Dto.umuShikinKanrenDantai }}
    </div>
</template>
<style scoped></style>
