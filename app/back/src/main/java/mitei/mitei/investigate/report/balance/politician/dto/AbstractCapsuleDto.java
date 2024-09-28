package mitei.mitei.investigate.report.balance.politician.dto;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckSecurityDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckTransactionDto;

/**
 * front側からback側に遷移するときに共通のチェック処理情報を格納するDto
 */
public abstract class AbstractCapsuleDto { // NOPMD DataClass

    /** セキュリティチェック情報 */
    private CheckSecurityDto checkSecurityDto;

    /** 権限チェック情報 */
    private CheckPrivilegeDto checkPrivilegeDto;

    /** 排他制御情報 */
    private CheckTransactionDto checkTransactionDto;

    /**
     * セキュリティ情報を取得する
     *
     * @return セキュリティ情報
     */
    public CheckSecurityDto getCheckSecurityDto() {
        return checkSecurityDto;
    }

    /**
     * セキュリティ情報を設定する
     *
     * @param checkSecurityDto セキュリティ情報
     */
    public void setCheckSecurityDto(final CheckSecurityDto checkSecurityDto) {
        this.checkSecurityDto = checkSecurityDto;
    }

    /**
     * 権限情報を取得する
     *
     * @return 権限情報
     */
    public CheckPrivilegeDto getCheckPrivilegeDto() {
        return checkPrivilegeDto;
    }

    /**
     * 権限情報を設定する
     *
     * @param checkPrivilegeDto 権限情報
     */
    public void setCheckPrivilegeDto(final CheckPrivilegeDto checkPrivilegeDto) {
        this.checkPrivilegeDto = checkPrivilegeDto;
    }

    /**
     * 排他制御情報を取得する
     *
     * @return 排他制御情報
     */
    public CheckTransactionDto getCheckTransactionDto() {
        return checkTransactionDto;
    }

    /**
     * 排他制御情報を設定する
     *
     * @param checkTransactionDto 排他制御情報
     */
    public void setCheckTransactionDto(final CheckTransactionDto checkTransactionDto) {
        this.checkTransactionDto = checkTransactionDto;
    }

}
