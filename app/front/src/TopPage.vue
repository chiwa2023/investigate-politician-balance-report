<script setup lang="ts">
import CheckSecurityDto from "./dto/common_check/checkSecurityDto";
import CheckPrivilegeDto from "./dto/common_check/checkPrivilegeDto";
import SessionStorageCommonCheck from "./dto/common_check/sessionStorageCommonCheck";

//本来はログインページで設定されているセキュリティ・権限情報は
//未実装のため、このページでパスする実装をする。
//TODO ログインの仕様が決定次第修正する
/* 値設定は基本的に1回のみ */

//ログイン成功したユーザ想定
//        checkPrivilegeDto.setLoginUserId(1007L); // CHECKSTYLE:OFF
//        checkPrivilegeDto.setLoginUserCode(995); // CHECKSTYLE:OFF
//        checkPrivilegeDto.setPoliticalOrganizationId(2345L); // CHECKSTYLE:OFF
//        checkPrivilegeDto.setPoliticalOrganizationCode(2333); // CHECKSTYLE:OFF
//        checkPrivilegeDto.setPoliticalOrganizationName("サンプル政治団体");
const loginUserId:number = 392;
const loginUserCode:number = 335;
const loginUserName:string = "会計監査人　英雄";

//Passする値を設定したセキュリティDto
const checkSecurityDto: CheckSecurityDto = new CheckSecurityDto();
checkSecurityDto.isRaiseExcception = false;
checkSecurityDto.isResult = true;
checkSecurityDto.loginUserId = loginUserId;
checkSecurityDto.loginUserCode = loginUserCode;

//Passする値を設定した権限Dto
const checkPrivilegeDto: CheckPrivilegeDto = new CheckPrivilegeDto();
checkPrivilegeDto.isRaiseExcception = false;
checkPrivilegeDto.isResult = true;
checkPrivilegeDto.loginUserId = loginUserId;
checkPrivilegeDto.loginUserCode = loginUserCode;
checkPrivilegeDto.loginUserName = loginUserName;

//セッションストレージ保存
SessionStorageCommonCheck.setSecurity(checkSecurityDto);
SessionStorageCommonCheck.setPrivilege(checkPrivilegeDto);
/* ここまで */

</script>
<template>
    <h1>(仮)Top Page</h1>
    <RouterLink to="/natural-search">登録済文書支出／収入項目検索(自然検索)</RouterLink><br>
    <RouterLink to="/insert-balancesheet-single">政治資金収支報告書1件登録</RouterLink><br>
    <RouterLink to="/insert-usage-report-single">政党交付金使途報告書1件登録</RouterLink><br>
    <hr>
    <RouterLink to="/audit-option-income">監査意見収入</RouterLink><br>
    <RouterLink to="/detect-balancesheet-defect">政治資金収支報告書欠損検出(版管理)</RouterLink><br>
    <RouterLink to="/make-link-balancesheet">政治資金収支報告書リンク編集</RouterLink><br>
    <RouterLink to="/propse-accept-read-csv-template">読み取りCSV申請承認</RouterLink><br>
    <RouterLink to="/propose-accept-not-regist-organization">未登録政治団体登録承認</RouterLink><br>
    <RouterLink to="/survey">基礎調査</RouterLink><br>
    <RouterLink to="/component">コンポーネント作成台紙</RouterLink><br>
</template>
<style scoped></style>
