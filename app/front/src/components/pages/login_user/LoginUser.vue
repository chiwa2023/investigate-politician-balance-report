<script setup lang="ts">
import { Ref, ref } from "vue";
import { RouteLocationNormalizedLoaded, Router, useRoute, useRouter } from "vue-router";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import CheckSecurityDto from "../../../dto/common_check/checkSecurityDto";
import CheckPrivilegeDto from "../../../dto/common_check/checkPrivilegeDto";

//ログイン情報(メアドとパスワード)
const loginUserMailAddress: Ref<string> = ref("");
const loginUserPassword: Ref<string> = ref("");

//router    
const routerDestination:Router = useRouter();//遷移先への移動用
const routeSource:RouteLocationNormalizedLoaded = useRoute();//遷移元からの情報取得用

//遷移元からの遷移先情報
let path:string = "";
if(routeSource.query.directPath !== undefined){
    path = routeSource.query.directPath+"";
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



//ログイン
function onLogin() {

    //TODO メールアドレスとパスワードを使用してログインする

    //仮でサンプルユーザを設定 TODO 機能実装次第削除する
    const loginUserId: number = 1007;
    const loginUserCode: number = 995;
    const loginUserName: string = "会計責任者 正夫";
    //const politicalOrganizationId: number = 2345;
    //const politicalOrganizationCode: number = 2333;
    //const politicalOrganizationName: string = "サンプル政治団体";

    //落ちない値を設定したセキュリティDto
    const checkSecurityDto: CheckSecurityDto = new CheckSecurityDto();
    checkSecurityDto.isRaiseExcception = false;
    checkSecurityDto.isResult = true;
    checkSecurityDto.loginUserId = loginUserId;
    checkSecurityDto.loginUserCode = loginUserCode;

    //落ちない値を設定した権限Dto
    const checkPrivilegeDto: CheckPrivilegeDto = new CheckPrivilegeDto();
    checkPrivilegeDto.isRaiseExcception = false;
    checkPrivilegeDto.isResult = true;
    checkPrivilegeDto.loginUserId = loginUserId;
    checkPrivilegeDto.loginUserCode = loginUserCode;
    checkPrivilegeDto.loginUserName = loginUserName;
    //checkPrivilegeDto.politicalOrganizationId = politicalOrganizationId;
    //checkPrivilegeDto.politicalOrganizationCode = politicalOrganizationCode;
    //checkPrivilegeDto.politicalOrganizationName = politicalOrganizationName;

    //セッションストレージ保存
    SessionStorageCommonCheck.setSecurity(checkSecurityDto);
    SessionStorageCommonCheck.setPrivilege(checkPrivilegeDto);

    //T元のページへ遷移
    routerDestination.push(path);
}
</script>
<template>
    <div style="text-align: center">
        <div
            style="width: 50%;margin-left: auto;margin-right: auto;border-width: 2px;border-style: solid;margin-top: 5%;">
            <br>
            ユーザログイン<br>
            <br>
            <div style="width: 99%;margin-left: auto;margin-right: auto;">
                <div class="left-area-center">
                    メールアドレス
                </div>
                <div class="right-area-center">
                    <input v-model="loginUserMailAddress">
                </div>
                <div class="clear-both"><br></div>
                <div class="left-area-center">
                    <span @click="changeVisiblePassword"><img v-show="!isPasswordVisible"
                            src="../../../assets/password_hidden.png" style="height:1.35em"><img
                            v-show="isPasswordVisible" src="../../../assets/password_visible.png"
                            style="height:1.35em">&nbsp;パスワード</span>
                </div>
                <div class="right-area-center">
                    <input :type="passwordInputType" v-model="loginUserPassword">
                </div>
                <div class="clear-both"><br></div>
            </div>
            <button @click="onLogin">ログイン</button><br>
            <br>
            <br>
            <RouterLink to="/regist_account">新規ユーザ登録</RouterLink><br>
            <br>
            <RouterLink to="/reset_password/propose">※パスワードを忘れたので再発行</RouterLink><br>
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
