<script setup lang="ts">
import { Ref, ref } from "vue";
import { Router, useRouter } from "vue-router";
import UserAccountDto from "../../../dto/user_account/userAccountDto";

/* このページではログインがされていないのが前提なのでログインチェックをしない */

//router    
const routerDestination: Router = useRouter();

//編集用Dto
const userAccountDto: Ref<UserAccountDto> = ref(new UserAccountDto());

/* 入力内容の保存 */
function onSave() {
    alert('申請');
    //TODO 以下の処理を行う

    //リセット用のURLをメールアカウントに送信する
    //2要素認証用のコードを電話番号に送信する

    //申請が完了したらログインページに遷移する
    routerDestination.push("/login_user");

}
</script>
<template>
    <h1>パスワードのリセット申し込み</h1>

    <div style="text-align: center">
        <div
            style="width: 50%;margin-left: auto;margin-right: auto;border-width: 2px;border-style: solid;margin-top: 5%;">
            <br>
            パスワードが不明なメールアドレスまたは電話番号を入力する<br>
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
                        v-model="userAccountDto.loginUserTel2" class="code-input">－<input type="text"
                        v-model="userAccountDto.loginUserTel3" class="code-input">
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
