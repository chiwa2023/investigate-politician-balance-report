<script setup lang="ts">
import { Ref, ref } from "vue";
import UserAccountDto from "../../../dto/user_account/userAccountDto";

//編集用Dto
const userAccountDto:Ref<UserAccountDto> = ref(new UserAccountDto());

/* 保存処理 */
function onSave() {
    alert("申請");
    //TODO 下記の処理を行う
    // 新規アカウント申請中であることを保存する
    // メールアドレスに承認用の直接アクセスするURLつきのメールを送信する
    // 電話番号に確認用のコードを送信する
}
</script>
<template>
    <h1>アカウントを作成します</h1>

    <div style="text-align: center">
        <div style="width: 50%;margin-left: auto;margin-right: auto;border-width: 2px;border-style: solid;">
            <br>
            新しいアカウント作成<br>
            <br>
            <div style="width: 99%;margin-left: auto;margin-right: auto;">
                <div class="left-area-center">
                    メールアドレス
                </div>
                <div class="right-area-center">
                    <input type="email" v-model="userAccountDto.loginUserMailAddress">
                </div>
                <div class="clear-both"><br></div>
                <div class="left-area-center">
                    電話番号
                </div>
                <div class="right-area-center">
                    <input type="text" v-model="userAccountDto.loginUserTel1" class="code-input">－<input type="text"
                        v-model="userAccountDto.loginUserTel2" class="code-input">－<input type="text" v-model="userAccountDto.loginUserTel3"
                        class="code-input">
                </div>
                <div class="clear-both"><br></div>

            </div>
            <button @click="onSave">申請</button><br>
            <br>
            <br>
            <RouterLink to="/login_user">ログインページに戻る</RouterLink><br>
        </div>
    </div>
</template>
<style scoped>
div.left-area-center {
    float: left;
    width: 34%;
    text-align: right;
    padding-right: 1%;
}

div.right-area-center {
    float: left;
    text-align: left;
    width: 65%;
}
</style>
