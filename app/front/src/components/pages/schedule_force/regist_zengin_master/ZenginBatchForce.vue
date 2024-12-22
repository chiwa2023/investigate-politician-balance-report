<script setup lang="ts">
//仮

function onPickupIdo(){
    alert("csvワークテーブルから異動テーブルに差異を抽出");
}

function onTransferIdoCreate(){
    alert("作成側に差異を配信");
}


function onCopyMasterFromIdo(){
    alert("異動テーブルからマスタを更新");
}

</script>
<template>
    <h1>金融機関支店マスタ(スケジュール処理を強制)</h1>
    <div class="left-area">
        異動テーブル差異抽出
    </div>
    <div class="right-area">
        <button @click="onPickupIdo">実行</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        異動内容を作成側配信
    </div>
    <div class="right-area">
        <button @click="onTransferIdoCreate">実行</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        マスタテーブル更新(新規・移転)
    </div>
    <div class="right-area">
        <button @click="onCopyMasterFromIdo">実行</button>
    </div>
    <div class="clear-both"></div>

</template>
<style scoped></style>