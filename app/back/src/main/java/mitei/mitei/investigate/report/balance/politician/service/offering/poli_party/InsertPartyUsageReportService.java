package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party; // NOPMD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.CheckAllreadyRegistDataPartyUsageLogic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0801And0807Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0802And0803Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0802Kbn02Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0804Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0805Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0806Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0901Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0902Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatePartyUsageShito0801And0807Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatePartyUsageShito0802And0803Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatePartyUsageShito0802Kbn02Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatePartyUsageShito0804Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatePartyUsageShito0805Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatePartyUsageShito0806Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatePartyUsageShito0901Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.UpdatetPartyUsageShito0902Logic;
import mitei.mitei.investigate.report.balance.politician.logic.political_organization.InsertPoliticalOrganizationByPartyUsageLogic;

/**
 * 使途報告書Dtoを独自形式で保存する
 */
@Service
public class InsertPartyUsageReportService {

    /** 既存最新データ確認Logic */
    @Autowired
    private CheckAllreadyRegistDataPartyUsageLogic checkAllreadyRegistDataPartyUsageLogic;

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

    /** 様式8その1とその7 */
    @Autowired
    private UpdatePartyUsageShito0801And0807Logic updatePartyUsageShito0801And0807Logic;

    /** 様式8その2とその3 */
    @Autowired
    private UpdatePartyUsageShito0802And0803Logic updatePartyUsageShito0802And0803Logic;

    /** 様式8その2区分2 */
    @Autowired
    private UpdatePartyUsageShito0802Kbn02Logic updatePartyUsageShito0802Kbn02Logic;

    /** 様式8その4 */
    @Autowired
    private UpdatePartyUsageShito0804Logic updatePartyUsageShito0804Logic;

    /** 様式8その5 */
    @Autowired
    private UpdatePartyUsageShito0805Logic updatePartyUsageShito0805Logic;

    /** 様式8その6 */
    @Autowired
    private UpdatePartyUsageShito0806Logic updatePartyUsageShito0806Logic;

    /** 様式9 */
    @Autowired
    private UpdatePartyUsageShito0901Logic updatePartyUsageShito0901Logic;

    /** 様式9その2 */
    @Autowired
    private UpdatetPartyUsageShito0902Logic updatePartyUsageShito0902Logic;

    /** 政治団体を使途報告書から登録Logic */
    @Autowired
    private InsertPoliticalOrganizationByPartyUsageLogic insertPoliticalOrganizationByPartyUsageLogic;

    /**
     * 使途報告書XMLを独自形式で保存する
     *
     * @param allShitoBook 使途報告書XML
     */
    public RegistPoliticalPartyUsageReportResultDto practice(
            final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto, final CheckPrivilegeDto checkPrivilegeDto,
            final AllShitoBook allShitoBook, final boolean isSearchRelation) {

        boolean isAcceptUpdate = true;

        // 政治団体新規登録が選択された場合は、登録してIdなどを更新する
        if (documentPropertyDto.getPoliticalOrganizationId().equals(0L) && documentPropertyDto.getIsAddOrganization()) {
            PoliticalOrganizationEntity organizationEntity = insertPoliticalOrganizationByPartyUsageLogic
                    .practice(allShitoBook.getShito0801Dto().getSheet0801Dto(), checkPrivilegeDto);
            documentPropertyDto.setPoliticalOrganizationId(organizationEntity.getPoliticalOrganizationId());
            documentPropertyDto.setPoliticalOrganizationCode(organizationEntity.getPoliticalOrganizationCode());
            documentPropertyDto.setPoliticalOrganizationName(organizationEntity.getPoliticalOrganizationName());
        }

        List<Long> listOldCode = checkAllreadyRegistDataPartyUsageLogic.practice(documentPropertyDto);

        // すでに登録があり、かつ更新を許さない場合
        if (!isAcceptUpdate && !listOldCode.isEmpty()) {
            RegistPoliticalPartyUsageReportResultDto resultDto = new RegistPoliticalPartyUsageReportResultDto();
            resultDto.setIsOk(false);
            resultDto.setMessage("すでに登録があり、更新を許可していません。");
            return resultDto;
        }

        // 8号様式表紙と宣誓書
        Long documentCode = insertPartyUsageShito0801And0807Logic.practice(documentPropertyDto, allShitoBook,
                checkPrivilegeDto);

        // 8号様式その2と3:収支の総括表
        int sizeSummary = insertPartyUsageShito0802And0803Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0802Dto(), allShitoBook.getShito0803Dto(), checkPrivilegeDto);

        // 8号様式その2区分2
        int size2 = insertPartyUsageShito0802Kbn02Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080202Dto(), checkPrivilegeDto);

        // 8号様式その4
        int size4 = insertPartyUsageShito0804Logic.practice(isSearchRelation, documentCode, documentPropertyDto,
                allShitoBook.getShito0804Dto(), checkPrivilegeDto);

        // 8号様式その5:支部政党交付金の内訳
        int size5 = insertPartyUsageShito0805Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0805Dto(), checkPrivilegeDto);

        // 8号様式その6:政党基金の内訳
        int size6 = insertPartyUsageShito0806Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0806Dto(), checkPrivilegeDto);

        // 9号様式:領収書を徴しがたかった明細書
        int size9 = insertPartyUsageShito0901Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0901Dto(), checkPrivilegeDto);

        // 9号様式その2:振込明細書にかかる支出目的書
        int size92 = insertPartyUsageShito0902Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0902Dto(), checkPrivilegeDto);

        int nendo = documentPropertyDto.getNendo();
        /* 旧データを履歴に */
        // 確実に登録ができたのが判明してから最新データから履歴データに変える
        for (Long oldCode : listOldCode) {

            // 表紙と宣誓書
            updatePartyUsageShito0801And0807Logic.practice(nendo, oldCode, checkPrivilegeDto);

            // 様式8その2とその3 */
            updatePartyUsageShito0802And0803Logic.practice(nendo, oldCode, checkPrivilegeDto);

            // 様式8その2区分2 */
            updatePartyUsageShito0802Kbn02Logic.practice(nendo, oldCode, checkPrivilegeDto);

            // 様式8その4
            updatePartyUsageShito0804Logic.practice(nendo, oldCode, checkPrivilegeDto);

            // 様式8その5
            updatePartyUsageShito0805Logic.practice(nendo, oldCode, checkPrivilegeDto);

            // 様式8その6
            updatePartyUsageShito0806Logic.practice(nendo, oldCode, checkPrivilegeDto);

            // 様式9
            updatePartyUsageShito0901Logic.practice(nendo, oldCode, checkPrivilegeDto);

            // 様式9その2
            updatePartyUsageShito0902Logic.practice(nendo, oldCode, checkPrivilegeDto);
        }

        // 登録失敗はTransactionをロールバックしないといけないのですべて例外
        RegistPoliticalPartyUsageReportResultDto resultDto = new RegistPoliticalPartyUsageReportResultDto();

        resultDto.setDocumentCode(documentCode);
        resultDto.setMessage(
                "1件登" + (sizeSummary + sizeSummary + size2 + size4 + size5 + size6 + size9 + size92) + "行登録しました");
        resultDto.setSuccessCount(1);
        resultDto.setIsOk(true);

        return resultDto;
    }
}
