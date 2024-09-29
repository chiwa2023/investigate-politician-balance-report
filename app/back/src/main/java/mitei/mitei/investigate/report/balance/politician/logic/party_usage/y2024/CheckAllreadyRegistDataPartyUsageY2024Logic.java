package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0801And0807Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0801And0807Report2024Repository;

/**
 * すでに登録済の場合は表紙、誓約書の文書同一識別コードのリストを返す
 */
@Component
public class CheckAllreadyRegistDataPartyUsageY2024Logic {

    /** 政党交付金使途報告書Repository */
    @Autowired
    private OfferingPartyUsage0801And0807Report2024Repository offeringPartyUsage0801And0807Report2024Repository;

    /**
     * 提出年と政治団体同一識別コードが同じかつ最新区分が最新であるコードを返却する
     *
     * @param documentPropertyDto 文書同一識別コード
     * @return 検索条件に一致する文書同一識別コード
     */
    public List<Long> practice(final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto) {
        
        List<OfferingPartyUsage0801And0807Report2024Entity> listData = offeringPartyUsage0801And0807Report2024Repository
                .findBySaishinKbnAndPoliticalOrganizationCodeAndOfferingDate(DataHistoryStatusConstants.INSERT.value(),
                        documentPropertyDto.getPoliticalOrganizationCode(), documentPropertyDto.getOfferingDate());

        List<Long> list = new ArrayList<>();
        for (OfferingPartyUsage0801And0807Report2024Entity entity : listData) {
            list.add(entity.getPartyUsage0801And0807ReportCode());
        }

        return list;
    }

}
