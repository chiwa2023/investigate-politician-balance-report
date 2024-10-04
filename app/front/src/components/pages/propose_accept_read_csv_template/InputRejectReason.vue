<script setup lang="ts">
import { Ref, ref } from "vue";

//emit
const emits = defineEmits(["sendCancelInputRejectReason", "sendReasonText"]);

//却下理由
const reasonText: Ref<string> = ref("");

    const selectedOptionReason: Ref<string> = ref("");

/**
 * 入力キャンセル
 */
 function onCancel() {
    emits("sendCancelInputRejectReason");
}

/**
 * 入力された却下理由を親に送信
 */
function onSave() {
    emits("sendReasonText",reasonText.value);

}

/**
 * 選択された理由を前に挿入する
 */
function addContent() {
    reasonText.value = selectedOptionReason .value + "\r" + reasonText.value;
}

</script>
<template>
    <h3>却下</h3>

    <div class="left-area">
        理由テンプレート
    </div>
    <div class="right-area">
        <select v-model="selectedOptionReason" @change="addContent">
            <option>類似の仕様がすでに存在する</option>
            <option>新規申し込みだが変更だった</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        却下理由
    </div>
    <div class="right-area">
        <textarea v-model="reasonText"></textarea>
    </div>

    <div class="clear-both"></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">保存</button>
    </div>


</template>
<style scoped></style>
