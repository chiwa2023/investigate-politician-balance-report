package mitei.mitei.investigate.report.balance.politician.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0801And0807Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0802And0803Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0802Kbn02Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0804Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0805Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0806Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0901Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0902Logic;

/**
 * 使途報告書Dtoを独自形式で保存する
 */
@Service
public class InsertPartyUsageReportService {

    /** 様式8その1とその7 */
    @Autowired
    private InsertPartyUsageShito0801And0807Logic insertPartyUsageShito0801And0807Logic;

    /** 様式8その2とその3 */
    @Autowired
    private InsertPartyUsageShito0802And0803Logic insertPartyUsageShito0802And0803Logic;

    /** 様式8その2区分2 */
    @Autowired
    private InsertPartyUsageShito0802Kbn02Logic insertPartyUsageShito0802Kbn02Logic;

    /** 様式8その4 */
    @Autowired
    private InsertPartyUsageShito0804Logic insertPartyUsageShito0804Logic;

    /** 様式8その5 */
    @Autowired
    private InsertPartyUsageShito0805Logic insertPartyUsageShito0805Logic;

    /** 様式8その6 */
    @Autowired
    private InsertPartyUsageShito0806Logic insertPartyUsageShito0806Logic;

    /** 様式9 */
    @Autowired
    private InsertPartyUsageShito0901Logic insertPartyUsageShito0901Logic;

    /** 様式9その2 */
    @Autowired
    private InsertPartyUsageShito0902Logic insertPartyUsageShito0902Logic;

    /**
     * 使途報告書XMLを独自形式で保存する
     *
     * @param allShitoBook 使途報告書XML
     */
    public RegistPoliticalPartyUsageReportResultDto practice(final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final CheckPrivilegeDto checkPrivilegeDto, final AllShitoBook allShitoBook,
            final boolean isSearchRelation) {

        // 8号様式表紙と宣誓書
        Long documentCode = insertPartyUsageShito0801And0807Logic.practice(partyUsageDocumentPoliticalPropertyDto,
                allShitoBook, checkPrivilegeDto);

        // 8号様式その2と3:収支の総括表
        int sizeSummary = insertPartyUsageShito0802And0803Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                allShitoBook.getShito0802Dto(), allShitoBook.getShito0803Dto(), checkPrivilegeDto);

        // 8号様式その2区分2
        int size2 = insertPartyUsageShito0802Kbn02Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080202Dto(), checkPrivilegeDto);

        // 8号様式その4
        int size4 = insertPartyUsageShito0804Logic.practice(isSearchRelation, documentCode, partyUsageDocumentPoliticalPropertyDto,
                allShitoBook.getShito0804Dto(), checkPrivilegeDto);

        // 8号様式その5:支部政党交付金の内訳
        int size5 = insertPartyUsageShito0805Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                allShitoBook.getShito0805Dto(), checkPrivilegeDto);

        // 8号様式その6:政党基金の内訳
        int size6 = insertPartyUsageShito0806Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                allShitoBook.getShito0806Dto(), checkPrivilegeDto);

        // 9号様式:領収書を徴しがたかった明細書
        int size9 = insertPartyUsageShito0901Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                allShitoBook.getShito0901Dto(), checkPrivilegeDto);

        // 9号様式その2:振込明細書にかかる支出目的書
        int size92 = insertPartyUsageShito0902Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                allShitoBook.getShito0902Dto(), checkPrivilegeDto);
        
        
        // 登録失敗はTransactionをロールバックしないといけないのですべて例外
        RegistPoliticalPartyUsageReportResultDto resultDto = new RegistPoliticalPartyUsageReportResultDto();

        resultDto.setDocumentCode(documentCode);
        resultDto.setSuccessCount(1 + sizeSummary + size2 + size4 + size5 + size6 + size9 + size92);
        resultDto.setIsOk(true);
        resultDto.setMessage(resultDto.getSuccessCount() + "件登録しました");

        return resultDto;
    }
}