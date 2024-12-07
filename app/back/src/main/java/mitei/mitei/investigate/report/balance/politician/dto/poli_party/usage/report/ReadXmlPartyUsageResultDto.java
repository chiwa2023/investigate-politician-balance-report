package mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;

/**
 * 
 */
public class ReadXmlPartyUsageResultDto extends AbstractResultDto { // NOPMD DataClass

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 表示メッセージ */
    private String message = "";

    /**
     * 表示メッセージを取得する
     *
     * @return 表示メッセージ
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 表示メッセージを設定する
     *
     * @param message 表示メッセージ
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

    /** 政党交付金使途報告書用政治団体情報 */
    private PartyUsageDocumentPoliticalPropertyDto documentPropertyDto;

    /** 政党交付金使途報告書様式1(表紙) */
    private Sheet0801Dto coverDto;

    /** 書証保存Dto */
    private SaveStorageResultDto saveStorageResultDto;

    /**
     * 政治資金収支報告書用政治団体情報を取得する
     *
     * @return 政治資金収支報告書用政治団体情報
     */
    public PartyUsageDocumentPoliticalPropertyDto getDocumentPropertyDto() {
        return documentPropertyDto;
    }

    /**
     * 政治資金収支報告書用政治団体情報を設定する
     *
     * @param documentPropertyDto 政治資金収支報告書用政治団体情報
     */
    public void setDocumentPropertyDto(final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto) {
        this.documentPropertyDto = documentPropertyDto;
    }

    /**
     * 政治資金収支報告書様式1(表紙)を取得する
     *
     * @return 政治資金収支報告書様式1(表紙)
     */
    public Sheet0801Dto getCoverDto() {
        return coverDto;
    }

    /**
     * 政治資金収支報告書様式1(表紙)を設定する
     *
     * @param coverDto 政治資金収支報告書様式1(表紙)
     */
    public void setCoverDto(final Sheet0801Dto coverDto) {
        this.coverDto = coverDto;
    }

    /**
     * 書証保存Dtoを取得する
     *
     * @return 書証保存Dto
     */
    public SaveStorageResultDto getSaveStorageResultDto() {
        return saveStorageResultDto;
    }

    /**
     * 書証保存Dtoを設定する
     *
     * @param saveStorageResultDto 書証保存Dto
     */
    public void setSaveStorageResultDto(final SaveStorageResultDto saveStorageResultDto) {
        this.saveStorageResultDto = saveStorageResultDto;
    }

    /** 権限チェック情報 */
    private CheckPrivilegeDto checkPrivilegeDto;
    
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
    
    /** 政党交付金使途報告書準備登録タスクId */
    private Long taskPlanPartyUsageDetailId = INIT_Long;

    /**
     * 政党交付金使途報告書準備登録タスクIdを取得する
     *
     * @return 政党交付金使途報告書準備登録タスクId
     */
    public Long getTaskPlanPartyUsageDetailId() {
        return taskPlanPartyUsageDetailId;
    }

    /**
     * 政党交付金使途報告書準備登録タスクIdを設定する
     *
     * @param taskPlanPartyUsageDetailId 政党交付金使途報告書準備登録タスクId
     */
    public void setTaskPlanPartyUsageDetailId(final Long taskPlanPartyUsageDetailId) {
        this.taskPlanPartyUsageDetailId = taskPlanPartyUsageDetailId;
    }

    /** 政党交付金使途報告書準備登録タスク定同一識別コード */
    private Long taskPlanPartyUsageDetailCode = INIT_Long;

    /**
     * 政党交付金使途報告書準備登録タスク定同一識別コードを取得する
     *
     * @return 政党交付金使途報告書準備登録タスク定同一識別コード
     */
    public Long getTaskPlanPartyUsageDetailCode() {
        return taskPlanPartyUsageDetailCode;
    }

    /**
     * 政党交付金使途報告書準備登録タスク定同一識別コードを設定する
     *
     * @param taskPlanPartyUsageDetailCode 政党交付金使途報告書準備登録タスク定同一識別コード
     */
    public void setTaskPlanPartyUsageDetailCode(final Long taskPlanPartyUsageDetailCode) {
        this.taskPlanPartyUsageDetailCode = taskPlanPartyUsageDetailCode;
    }


}
