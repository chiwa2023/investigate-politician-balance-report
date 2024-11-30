package mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;

/**
 * 政治資金収支報告書登録Dto
 */
public class RegistPoliticalPartyUsageReportCapsuleDto extends AbstractCapsuleDto implements Serializable { // NOPMD

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 政治資金収支報告書文書属性Dto */
    private PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();

    /** 書証ファイル保存Dto */
    private SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();

    /**
     * 政治資金収支報告書文書属性Dtoを取得する
     *
     * @return 政治資金収支報告書文書属性Dto
     */
    public PartyUsageDocumentPoliticalPropertyDto getDocumentPropertyDto() {
        return documentPropertyDto;
    }

    /**
     * 政治資金収支報告書文書属性Dtoを設定する
     *
     * @param documentPropertyDto 政治資金収支報告書文書属性Dto
     */
    public void setDocumentPropertyDto(final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto) {
        this.documentPropertyDto = documentPropertyDto;
    }

    /**
     * 書証ファイル保存Dtoを取得する
     *
     * @return 書証ファイル保存Dto
     */
    public SaveStorageResultDto getSaveStorageResultDto() {
        return saveStorageResultDto;
    }

    /**
     * 書証ファイル保存Dtoを設定する
     *
     * @param saveStorageResultDto 書証ファイル保存Dto
     */
    public void setSaveStorageResultDto(final SaveStorageResultDto saveStorageResultDto) {
        this.saveStorageResultDto = saveStorageResultDto;
    }

}
