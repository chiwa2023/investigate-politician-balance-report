﻿<script setup lang="ts">
import createCheckTransactionDto from '../../../../dto/common_check/createCheckTransactionDto';
import SessionStorageCommonCheck from '../../../../dto/common_check/sessionStorageCommonCheck';
import TemplateFrameworkCapsuleDto from '../../../../dto/template/templateFrameworkCapsuleDto';
import TemplateFrameworkResultInterface from '../../../../dto/template/templateFrameworkResultDto';

function onExecuteBalancesheet() {
    // 初期表示検索用Dto
    const capsuleDto: TemplateFrameworkCapsuleDto = new TemplateFrameworkCapsuleDto();
    //セッションストレージ取得
    capsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    capsuleDto.checkTransactionDto = createCheckTransactionDto(true);

    // 時間を空けて展開するためにzipを書証として保存
    const url = "http://localhost:9080/force-balancesheet/regist";
    const method = "POST";
    const body = JSON.stringify(capsuleDto);
    const headers = {
        'Accept': 'application/json',
       'Content-Type': 'application/json'
    };

    fetch(url, { method, headers, body })
        .then(async (response) => {
            const resultDto:TemplateFrameworkResultInterface = await response.json();
            alert(resultDto.message);
        })
        .catch((error) => { alert(error); });

}

function onExecutePartyUsage() {
    // 初期表示検索用Dto
    const capsuleDto: TemplateFrameworkCapsuleDto = new TemplateFrameworkCapsuleDto();
    //セッションストレージ取得
    capsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    //編集可否フラグがある場合は、そのフラグ(の反転した値)を照会フラグに設定する
    capsuleDto.checkTransactionDto = createCheckTransactionDto(true);

    // 時間を空けて展開するためにzipを書証として保存
    const url = "http://localhost:9080/force-party-usage/regist";
    const method = "POST";
    const body = JSON.stringify(capsuleDto);
    const headers = {
        'Accept': 'application/json',
       'Content-Type': 'application/json'
    };

    fetch(url, { method, headers, body })
        .then(async (response) => {
            const resultDto:TemplateFrameworkResultInterface = await response.json();
            alert(resultDto.message);
        })
        .catch((error) => { alert(error); });
}
</script>
<template>
    <h1>報告書一括処理(スケジュール処理を強制)</h1>
    <div class="left-area">
        政治資金収支報告書強制実行
    </div>
    <div class="right-area">
        <button @click="onExecuteBalancesheet">実行</button>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        政党交付金使途報告書強制実行
    </div>
    <div class="right-area">
        <button @click="onExecutePartyUsage">実行</button>
    </div>
    <div class="clear-both"></div>

</template>
<style scoped></style>
