<script setup lang="ts">
import { Ref, ref } from "vue";
import { RouteLocationNormalizedLoaded, Router, useRoute, useRouter } from "vue-router";
import mockPasswordComplexStatus from "../reset_password/mock/mockPasswordComplexStatus";
import UserAccountDto from "../../../dto/user_account/userAccountDto";
import mockGetUserAccount from "./mock/mockGetUserAccount";

//router    
const routerDestination: Router = useRouter();//遷移先への移動用
const routeSource: RouteLocationNormalizedLoaded = useRoute();//遷移元からの情報取得用

//呼び出し用コードを取得
const oneTimeRecognizeNo: Ref<string> = ref("");
if (routeSource.query.oneTimeRecognizeNo !== undefined && routeSource.query.oneTimeRecognizeNo !== null) {
    oneTimeRecognizeNo.value = routeSource.query.oneTimeRecognizeNo.toString();
}

//編集用Dto
const userAccountDto:Ref<UserAccountDto> = ref(mockGetUserAccount(oneTimeRecognizeNo.value));

//パスワード確認用
const loginUserPassword2: Ref<string> = ref("");

//パスワード表示／非表示処理
const isPasswordVisible: Ref<boolean> = ref(false);
const passwordInputType: Ref<string> = ref("password");
function changeVisiblePassword() {
    //表示フラグ反転
    isPasswordVisible.value = !isPasswordVisible.value;
    if (isPasswordVisible.value) {
        passwordInputType.value = "text";
    } else {
        passwordInputType.value = "password";
    }
}

/* 保存処理 */
function onSave() {
    if (userAccountDto.value.loginUserPassword === loginUserPassword2.value) {
        alert("申請");
        //TODO 作業内容
        //電話番号送信コードの確認
        //パスワードの登録
        //ユーザ登録のタスク登録
        //ログインページ経由のトップページ
        routerDestination.push("/login_user"+"?directPath=/");
    } else {
        alert("パスワード確認入力が異なっています");
    }
}
</script>
<template>
    <h1>アカウントを作成します(疎通確認)</h1>

    <div style="text-align: center">
        <div style="width: 50%;margin-left: auto;margin-right: auto;border-width: 2px;border-style: solid;">
            <br>
            メールアドレスと電話番号に入力間違いがないか確認をします<br>
            <br>
            <div style="width: 99%;margin-left: auto;margin-right: auto;">
                <!-- 入力済のメアドと電話番号 -->
                <div class="left-area-center">
                    メールアドレス
                </div>
                <div class="right-area-center">
                    <input type="email" v-model="userAccountDto.loginUserMailAddress" disabled="true">
                </div>
                <div class="clear-both"></div>
                <div class="left-area-center">
                    電話番号
                </div>
                <div class="right-area-center">
                    <input type="text" v-model="userAccountDto.loginUserTel1" class="code-input" disabled="true">－<input type="text"
                        v-model="userAccountDto.loginUserTel2" class="code-input" disabled="true">－<input type="text"
                        v-model="userAccountDto.loginUserTel3" class="code-input" disabled="true">
                </div>
                <div class="clear-both"><br></div>

                <!-- 送信されたコード -->
                <div class="left-area-center">
                    コード入力(電話番号)
                </div>
                <div class="right-area-center">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar1" class="one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar2" class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar3" class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar4" class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar5" class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar6" class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar7" class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar8" class="left-space-narrow one-char-input">
                </div>
                <div class="clear-both"><br></div>

                <!-- パスワード -->
                <div class="left-area-center" style="vertical-align:top;">
                    <span @click="changeVisiblePassword"><img v-show="!isPasswordVisible"
                            src="../../../assets/password_hidden.png" style="height:1.35em"><img
                            v-show="isPasswordVisible" src="../../../assets/password_visible.png"
                            style="height:1.35em">&nbsp;パスワード</span>
                </div>
                <div class="right-area-center">
                    <input :type="passwordInputType" v-model="userAccountDto.loginUserPassword">
                </div>
                <div class="clear-both"></div>
                <div class="left-area-center">
                    パスワード(確認入力)
                </div>
                <div class="right-area-center">
                    <input :type="passwordInputType" v-model="loginUserPassword2">
                </div>
                <div class="clear-both"></div>
                <div class="left-area-center">
                    パスワード強度
                </div>
                <div class="right-area-center">
                    {{ mockPasswordComplexStatus(userAccountDto.loginUserPassword) }}
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
