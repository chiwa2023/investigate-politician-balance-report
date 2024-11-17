<script setup lang="ts">
import { ref, Ref } from "vue";
import ReadXmlCapsuleInterface from "../../../dto/read_xml/readXmlCapsuleDto";
import ReadXmlCapsuleDto from "../../../dto/read_xml/readXmlCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SaveStorageResultDto from "../../../dto/storage/saveStorageResultDto";

// TODO ある程度使用場所が固まった段階で定数Dtoに移す

/** 公式政治資金収支報告書XML */
const SOFT_BALANCESHEET: string = "収支報告書作成ソフト";
/** 公式政党交付金使途報告書XML */
const SOFT_PARTY_USAGE: string = "使途等報告書作成ソフト";

const documentKey: Ref<string> = ref(SOFT_BALANCESHEET);

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

                reader.onload = async () => {
                    if (reader.result !== null) {

                        //必要な情報がそろったのでBackデータを渡す
                        const readXmlCapsuleDto: ReadXmlCapsuleInterface = new ReadXmlCapsuleDto();
                        //セッションストレージ取得
                        readXmlCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
                        readXmlCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
                        //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
                        readXmlCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);

                        readXmlCapsuleDto.fileContent = String(reader.result);
                        readXmlCapsuleDto.fileName = file.name;
                        readXmlCapsuleDto.documentKey = documentKey.value;

                        // 時間を空けて展開するためにzipを書証として保存
                        const url = "http://localhost:9080/zip-document/expand-task";
                        const method = "POST";
                        const body = JSON.stringify(readXmlCapsuleDto);
                        const headers = {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        };

                        fetch(url, { method, headers, body })
                            .then(async (response) => {
                                const storageDto: SaveStorageResultDto = await response.json();
                                alert(storageDto.message);
                            })
                            .catch((error) => { alert(error); });
                    }

                }
            };

        }
    }
};

function onTopMenu() {
    alert("トップメニューに戻る");
}

</script>
<template>
    <h1>政治資金収支報告書／政党交付金使途報告書一括登録</h1>
    <div class="left-area">
        文書の種類
    </div>
    <div class="right-area">
        <select v-model="documentKey">
            <option :value="SOFT_BALANCESHEET">政治資金収支報告書</option>
            <option :value="SOFT_PARTY_USAGE">政党交付金使途報告書</option>
        </select>
    </div>
    <div class="clear-both"></div>
    <div class="one-line">
        zipファイルを指定してアップロード保存<input ref="selectFileInput" type="file" accept=".zip" @change="readTextFile"
            style="visibility: hidden;"><br>
        &nbsp;<input v-model="selectFileName" type="text" disabled="true" style="width: 50%;"><button
            @click="onReadButton" style="margin-left: 1%;">ファイルを指定</button><br>
    </div>

    <div class="footer">
        <button @click="onTopMenu" class="footer-button">メニューに戻る</button>
    </div>

</template>
<style scoped></style>
