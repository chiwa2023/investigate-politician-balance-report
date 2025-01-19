<script setup lang="ts">
import CheckSecurityDto from "./dto/common_check/checkSecurityDto";
import CheckPrivilegeDto from "./dto/common_check/checkPrivilegeDto";
import SessionStorageCommonCheck from "./dto/common_check/sessionStorageCommonCheck";
import { useRouter } from "vue-router";

//router    
const routerDestination = useRouter();//遷移先への移動用

//本来はログインページで設定されているセキュリティ・権限情報は
//未実装のため、このページでパスする実装する。
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

// 直接アクセスしかされないので、ログインされているかをチェックする
// ログインされていない場合はログインページへ
const securityDto: CheckSecurityDto = SessionStorageCommonCheck.getSecurity();
if (0 === securityDto.loginUserId) {
    // loginUserIdが0=初期値の場合は、ログインしていないのでログインページに遷移する
    routerDestination.push("/login_user"+"?directPath=/");
}

</script>
<template>
    <h1>(仮)Top Page</h1>
    <RouterLink to="/audit-option-income">監査意見収入</RouterLink><br>
    <RouterLink to="/detect-balancesheet-defect">政治資金収支報告書欠損検出(版管理)</RouterLink><br>
    <RouterLink to="/make-link-balancesheet">政治資金収支報告書リンク編集</RouterLink><br>
    <RouterLink to="/propose-accept-not-regist-organization">未登録政治団体登録承認</RouterLink><br>
    <hr>
    <RouterLink to="/inquire-nationarity">国籍問い合わせ(寄付者)</RouterLink><br>
    <RouterLink to="/investigate-membership-fee">党費チェッカー</RouterLink><br>
    <RouterLink to="/investigate-fukisai-balancesheet">不記載チェッカー</RouterLink><br>
    <RouterLink to="/investigate-kifu-jougen">寄付上限チェッカー</RouterLink><br>

    <RouterLink to="/regist-zengin-master/upload-csv">全銀協金融機関支店最新csv登録</RouterLink><br>
    <RouterLink to="/regist-zengin-master/show-ido-list">全銀協金融機関支店異動(廃止店舗異動指定)</RouterLink><br>
    <RouterLink to="/schedule-force/regist-zengin-master">【暫定】強制：全銀協最新情報更新バッチ</RouterLink><br>

    <RouterLink to="/show-org-balancesheet-report">政治資金収支報告書照会(公式フォーマット)</RouterLink><br>
    <RouterLink to="/show-party-usage-report">政党交付金使途報告書(公式フォーマット)</RouterLink><br>
    <RouterLink to="/survey">基礎調査</RouterLink><br>
    <hr>
    <RouterLink to="/component">コンポーネント作成台紙</RouterLink><br>
</template>
<style scoped></style>
