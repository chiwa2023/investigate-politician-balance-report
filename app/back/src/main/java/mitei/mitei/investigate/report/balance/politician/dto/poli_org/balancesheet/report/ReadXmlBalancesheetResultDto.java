package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report;

import java.io.Serializable;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;

/**
 * 政治資金収支報告書XML読み取り結果Dto
 */
public class ReadXmlBalancesheetResultDto extends AbstractResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

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

    /** 政治資金収支報告書用政治団体情報 */
    private BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto;

    /** 政治資金収支報告書様式1(表紙) */
    private Sheet070100CoverAndOrganizationDetailsDto coverDto;

    /** 書証保存Dto */
    private SaveStorageResultDto saveStorageResultDto;

    /**
     * 政治資金収支報告書用政治団体情報を取得する
     *
     * @return 政治資金収支報告書用政治団体情報
     */
    public BalancesheetReportDocumentPoliticalPropertyDto getDocumentPropertyDto() {
        return documentPropertyDto;
    }

    /**
     * 政治資金収支報告書用政治団体情報を設定する
     *
     * @param documentPropertyDto 政治資金収支報告書用政治団体情報
     */
    public void setDocumentPropertyDto(final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto) {
        this.documentPropertyDto = documentPropertyDto;
    }

    /**
     * 政治資金収支報告書様式1(表紙)を取得する
     *
     * @return 政治資金収支報告書様式1(表紙)
     */
    public Sheet070100CoverAndOrganizationDetailsDto getCoverDto() {
        return coverDto;
    }

    /**
     * 政治資金収支報告書様式1(表紙)を設定する
     *
     * @param coverDto 政治資金収支報告書様式1(表紙)
     */
    public void setCoverDto(final Sheet070100CoverAndOrganizationDetailsDto coverDto) {
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

    /** 政治資金収支報告書準備登録タスクId */
    private Long taskPlanBalancesheetDetailId = INIT_Long;

    /**
     * 政治資金収支報告書準備登録タスクIdを取得する
     *
     * @return 政治資金収支報告書準備登録タスクId
     */
    public Long getTaskPlanBalancesheetDetailId() {
        return taskPlanBalancesheetDetailId;
    }

    /**
     * 政治資金収支報告書準備登録タスクIdを設定する
     *
     * @param taskPlanBalancesheetDetailId 政治資金収支報告書準備登録タスクId
     */
    public void setTaskPlanBalancesheetDetailId(final Long taskPlanBalancesheetDetailId) {
        this.taskPlanBalancesheetDetailId = taskPlanBalancesheetDetailId;
    }

    /** 政治資金収支報告書準備登録タスク定同一識別コード */
    private Long taskPlanBalancesheetDetailCode = INIT_Long;

    /**
     * 政治資金収支報告書準備登録タスク定同一識別コードを取得する
     *
     * @return 政治資金収支報告書準備登録タスク定同一識別コード
     */
    public Long getTaskPlanBalancesheetDetailCode() {
        return taskPlanBalancesheetDetailCode;
    }

    /**
     * 政治資金収支報告書準備登録タスク定同一識別コードを設定する
     *
     * @param taskPlanBalancesheetDetailCode 政治資金収支報告書準備登録タスク定同一識別コード
     */
    public void setTaskPlanBalancesheetDetailCode(final Long taskPlanBalancesheetDetailCode) {
        this.taskPlanBalancesheetDetailCode = taskPlanBalancesheetDetailCode;
    }

    /** 権限確認Dto */
    private CheckPrivilegeDto checkPrivilegeDto = new CheckPrivilegeDto();

    /**
     * 権限確認Dtoを取得する
     *
     * @return 権限確認Dto
     */
    public CheckPrivilegeDto getCheckPrivilegeDto() {
        return checkPrivilegeDto;
    }

    /**
     * 権限確認Dtoを設定する
     *
     * @param checkPrivilegeDto 権限確認Dto
     */
    public void setCheckPrivilegeDto(final CheckPrivilegeDto checkPrivilegeDto) {
        this.checkPrivilegeDto = checkPrivilegeDto;
    }

}
