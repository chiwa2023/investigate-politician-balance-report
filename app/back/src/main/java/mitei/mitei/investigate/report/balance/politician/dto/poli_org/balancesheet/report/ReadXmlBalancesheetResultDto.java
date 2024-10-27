package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;

/**
 * 
 */
public class ReadXmlBalancesheetResultDto extends AbstractResultDto { // NOPMD DataClass

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

}
