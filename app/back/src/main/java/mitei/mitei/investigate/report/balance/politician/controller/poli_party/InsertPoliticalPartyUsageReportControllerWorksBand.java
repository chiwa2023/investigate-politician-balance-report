package mitei.mitei.investigate.report.balance.politician.controller.poli_party;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.ReadAllShitoBookByXmlFileLogic;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_party.InsertPartyUsageReportService;

/**
 * ビジネスロジックをまとめるWorksBandController(Transaction用)
 */
@Component
public class InsertPoliticalPartyUsageReportControllerWorksBand {

    /** 政治資金収支報告書の公式XMLを登録Service */
    @Autowired
    private InsertPartyUsageReportService insertPartyUsageReportService;

    /** 政党交付金使途報告書読み取りLogic */
    @Autowired
    private ReadAllShitoBookByXmlFileLogic readAllShitoBookByXmlFileLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 政党交付金使途報告書登録Dto
     * @return 登録結果
     * @throws IOException ファイル読み取り例外
     */
    @Transactional
    public RegistPoliticalPartyUsageReportResultDto wakeBusiness(
            final RegistPoliticalPartyUsageReportCapsuleDto capsuleDto) throws IOException {

        // 保存したXMLからの全データ
        AllShitoBook allBookDto = readAllShitoBookByXmlFileLogic.practice(
                capsuleDto.getSaveStorageResultDto().getFullPath(), capsuleDto.getSaveStorageResultDto().getCharset());

        // 登録作業
        return insertPartyUsageReportService.practice(capsuleDto.getDocumentPropertyDto(),
                capsuleDto.getCheckPrivilegeDto(), allBookDto, false);

    }

}
