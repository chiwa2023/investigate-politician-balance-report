package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.CheckAllreadyRegistDataPartyUsageY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.CheckAllreadyRegistDataPartyUsageY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.CheckAllreadyRegistDataPartyUsageY2025Logic;

/**
 * すでに登録済の場合は表紙、誓約書の文書同一識別コードのリストを返す
 */
@Component
public class CheckAllreadyRegistDataPartyUsageLogic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 政党交付金使途報告書Repository */
    @Autowired
    private CheckAllreadyRegistDataPartyUsageY2022Logic checkAllreadyRegistDataPartyUsageY2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 政党交付金使途報告書Repository */
    @Autowired
    private CheckAllreadyRegistDataPartyUsageY2024Logic checkAllreadyRegistDataPartyUsageY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 政党交付金使途報告書Repository */
    @Autowired
    private CheckAllreadyRegistDataPartyUsageY2025Logic checkAllreadyRegistDataPartyUsageY2025Logic;

    /**
     * 提出年と政治団体同一識別コードが同じかつ最新区分が最新であるコードを返却する
     *
     * @param documentPropertyDto 文書同一識別コード
     * @return 検索条件に一致する文書同一識別コード
     */
    public List<Long> practice(final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto) {

        List<Long> list = new ArrayList<>();

        switch (documentPropertyDto.getNendo()) {
            // 2022年
            case YEAR_2022:
                list.addAll(checkAllreadyRegistDataPartyUsageY2022Logic.practice(documentPropertyDto));
                break;

            // 2024年
            case YEAR_2024:
                list.addAll(checkAllreadyRegistDataPartyUsageY2024Logic.practice(documentPropertyDto));
                break;

            // 2025年
            case YEAR_2025:
                list.addAll(checkAllreadyRegistDataPartyUsageY2025Logic.practice(documentPropertyDto));
                break;

            // NOTE:Logic実行追加位置

            default:
                break;
        }

        return list;
    }

}
