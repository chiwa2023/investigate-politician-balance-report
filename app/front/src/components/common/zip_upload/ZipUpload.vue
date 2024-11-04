<script setup lang="ts">
import { ref, Ref } from "vue";
import ReadXmlCapsuleInterface from "../../../dto/read_xml/readXmlCapsuleDto";
import ReadXmlCapsuleDto from "../../../dto/read_xml/readXmlCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";

const emits = defineEmits(["sendReadXmlBalancesheetResultInterface"]);

const selectFileInput = ref<HTMLInputElement>();

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

                reader.readAsDataURL(file);
                alert(selectFileName.value);

                reader.onload = async () => {
                    if (reader.result !== null) {
                        //alert(new String(reader.result));

                        //必要な情報がそろったので親へデータを渡す
                        const readXmlCapsuleDto: ReadXmlCapsuleInterface = new ReadXmlCapsuleDto();
                        //セッションストレージ取得
                        readXmlCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
                        readXmlCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
                        //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
                        readXmlCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);

                        readXmlCapsuleDto.fileContent = String(reader.result);
                        readXmlCapsuleDto.fileName = file.name;

                        // 時間を空けて展開するためにzipを書証として保存
                        const url = "http://localhost:9080/zip-upload";
                        const method = "POST";
                        const body = JSON.stringify(readXmlCapsuleDto);
                        const headers = {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        };

                        fetch(url, { method, headers, body })
                            .then(async (response) => {
                                alert(await response.json());
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
        zipファイルを指定してアップロード保存<input ref="selectFileInput" type="file" accept=".zip" @change="readTextFile"
            style="visibility: hidden;"><br>
        &nbsp;<input v-model="selectFileName" type="text" disabled="true" style="width: 50%;"><button
            @click="onReadButton" style="margin-left: 1%;">ファイルを指定</button><br>
    </div>
    <!--
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
    -->
</template>
<style scoped></style>
