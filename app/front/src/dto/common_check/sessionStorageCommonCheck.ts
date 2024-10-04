import CheckPrivilegeDto from "./checkPrivilegeDto";
import CheckSecurityDto from "./checkSecurityDto";

export default class SessionStorageCommonCheck {

    static setSecurity(checkSecurityDto: CheckSecurityDto) {
        const sessionStrage = window["sessionStorage"];
        sessionStrage.setItem("checkSecurityDto", JSON.stringify(checkSecurityDto));
    }

    static setPrivilege(checkPrivilegeDto: CheckPrivilegeDto) {
        const sessionStrage = window["sessionStorage"];
        sessionStrage.setItem("checkPrivilegeDto", JSON.stringify(checkPrivilegeDto));
    }

    static getPrivilege(): CheckPrivilegeDto {
        const sessionStrage = window["sessionStorage"];
        const checkPrivilegeDtoJsonText: string | null = sessionStrage.getItem("checkPrivilegeDto");
        const sessionPrivilegeDto: CheckPrivilegeDto = new CheckPrivilegeDto();
        if (checkPrivilegeDtoJsonText !== null) {
            // フィールドの存在を逐一確認しながら値を設定していく
            // NOTE:当然ながらフィールドが変わるたびに実装修正
            const obj = JSON.parse(checkPrivilegeDtoJsonText);
            if ("isRaiseExcception" in obj) {
                sessionPrivilegeDto.isRaiseExcception = obj.isRaiseExcception;
            }
            if ("isResult" in obj) {
                sessionPrivilegeDto.isResult = obj.isResult;
            }
            /* ここからは実利用する変数 */
            if ("loginUserId" in obj) {
                sessionPrivilegeDto.loginUserId = obj.loginUserId;
            }
            if ("loginUserCode" in obj) {
                sessionPrivilegeDto.loginUserCode = obj.loginUserCode;
            }
            if ("loginUserName" in obj) {
                sessionPrivilegeDto.loginUserName = obj.loginUserName;
            }
        }
        return sessionPrivilegeDto;
    }

    static getSecurity(): CheckSecurityDto {
        const sessionStrage = window["sessionStorage"];
        const checkSecurityDtoJsonText: string | null = sessionStrage.getItem("checkSecurityDto");

        const sessionSecurityDto: CheckSecurityDto = new CheckSecurityDto();
        if (checkSecurityDtoJsonText !== null) {
            const obj = JSON.parse(checkSecurityDtoJsonText);
            // フィールドの存在を逐一確認しながら値を設定していく
            // NOTE:当然ながらフィールドが変わるたびに実装修正
            if ("isRaiseExcception" in obj) {
                sessionSecurityDto.isRaiseExcception = obj.isRaiseExcception;
            }
            if ("isResult" in obj) {
                sessionSecurityDto.isResult = obj.isResult;
            }
            /* ここからは実利用する変数 */
            if ("loginUserId" in obj) {
                sessionSecurityDto.loginUserId = obj.loginUserId;
            }
            if ("loginUserCode" in obj) {
                sessionSecurityDto.loginUserCode = obj.loginUserCode;
            }
        }
        return sessionSecurityDto;
    }

}