package mitei.mitei.investigate.report.balance.politician.util;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;

/**
 * テスト用の権限Dtoを作成する
 */
public final class CreateTestPrivilegeDtoUtil {

    private CreateTestPrivilegeDtoUtil() {

    }

    /**
     * テスト用の権限Dtoを生成する
     *
     * @return テスト用の権限Dto
     */
    public static CheckPrivilegeDto pracitce() {
        CheckPrivilegeDto checkPrivilegeDto = new CheckPrivilegeDto();
        final long ID = 123321L; // NOPMD
        final int CODE = 987;
        final String NAME = "ユーザ";

        checkPrivilegeDto.setLoginUserId(ID);
        checkPrivilegeDto.setLoginUserCode(CODE);
        checkPrivilegeDto.setLoginUserName(NAME);

        return checkPrivilegeDto;
    }
}
