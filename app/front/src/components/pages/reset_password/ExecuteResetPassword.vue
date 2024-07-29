<script setup lang="ts">
import { Ref, ref } from "vue";
import mockPasswordComplexStatus from "./mock/mockPasswordComplexStatus";
import { RouteLocationNormalizedLoaded, Router, useRoute, useRouter } from "vue-router";
import UserAccountDto from "../../../dto/user_account/userAccountDto";
import mockGetUserAccount from "../regist_user/mock/mockGetUserAccount";

//router    
const routerDestination: Router = useRouter();//遷移先への移動用
const routeSource: RouteLocationNormalizedLoaded = useRoute();//遷移元からの情報取得用

//呼び出し用コードを取得
const oneTimeRecognizeNo: Ref<string> = ref("");
if (routeSource.query.oneTimeRecognizeNo !== undefined && routeSource.query.oneTimeRecognizeNo !== null) {
    oneTimeRecognizeNo.value = routeSource.query.oneTimeRecognizeNo.toString();
}

//編集用Dto
const userAccountDto: Ref<UserAccountDto> = ref(mockGetUserAccount(oneTimeRecognizeNo.value));
const loginUserPassword2: Ref<string> = ref("");

function onSave() {

    //パスワードは一致している必要がある
    if (userAccountDto.value.loginUserPassword === loginUserPassword2.value) {
        alert('申請');
        //TODO 以下の処理を行う
        //パスワードを保存する
        //申請が完了したらログインページに遷移する
        routerDestination.push("/login_user" + "?directPath=/");
    } else {
        alert('確認用パスワードと入力パスワードが異なります');
    }
}

//パスワード表示／非表示処理
const isPasswordVisible: Ref<boolean> = ref(false);
const passwordInputType: Ref<string> = ref("password");
function changeVisiblePassword() {
    isPasswordVisible.value = !isPasswordVisible.value;

    if (isPasswordVisible.value) {
        passwordInputType.value = "text";
    } else {
        passwordInputType.value = "password";
    }
}

</script>
<template>
    <h1>パスワードのリセット実行</h1>
    <div style="text-align: center">
        <div
            style="width: 50%;margin-left: auto;margin-right: auto;border-width: 2px;border-style: solid;margin-top: 5%;">
            <br>
            パスワードの入力<br>
            <br>
            <div style="width: 99%;margin-left: auto;margin-right: auto;">
                <div class="left-area-center">
                    メールアドレス
                </div>
                <div class="right-area-center">
                    <input type="text" v-model="userAccountDto.loginUserMailAddress" disabled="true">
                </div>
                <div class="clear-both"><br></div>

                <div class="left-area-center">
                    電話番号
                </div>
                <div class="right-area-center">
                    <input type="text" v-model="userAccountDto.loginUserTel1" class="code-input" disabled="true">－<input
                        type="text" v-model="userAccountDto.loginUserTel2" class="code-input" disabled="true">－<input
                        type="text" v-model="userAccountDto.loginUserTel3" class="code-input" disabled="true">
                </div>
                <div class="clear-both"><br></div>

                <!-- 送信されたコード -->
                <div class="left-area-center">
                    コード入力(電話番号)
                </div>
                <div class="right-area-center">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar1"
                        class="one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar2"
                        class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar3"
                        class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar4"
                        class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar5"
                        class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar6"
                        class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar7"
                        class="left-space-narrow one-char-input">
                    <input type="text" v-model="userAccountDto.recognizeTelephonCodeDto.codeChar8"
                        class="left-space-narrow one-char-input">
                </div>
                <div class="clear-both"><br></div>

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
            <button @click="onSave">保存</button><br>
            <br>
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
