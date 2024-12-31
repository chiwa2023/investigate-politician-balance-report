package mitei.mitei.investigate.report.balance.politician.util;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;

/**
 * 各パラメータから権限確認Dtoを生成する
 */
public final class CreatePrivilegeDtoByParamUtil {

    /**
     * インスタンス生成除け
     */
    private CreatePrivilegeDtoByParamUtil() {
    }

    /**
     * 処理を行う
     *
     * @param userId   ユーザid
     * @param userCode ユーザ同一識別コード
     * @param userName ユーザ名
     * @return 権限確認Dto
     */
    public static CheckPrivilegeDto practice(final Long userId, final Integer userCode, final String userName) {
        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
        privilegeDto.setLoginUserId(userId);
        privilegeDto.setLoginUserCode(userCode);
        privilegeDto.setLoginUserName(userName);

        return privilegeDto;
    }

}
