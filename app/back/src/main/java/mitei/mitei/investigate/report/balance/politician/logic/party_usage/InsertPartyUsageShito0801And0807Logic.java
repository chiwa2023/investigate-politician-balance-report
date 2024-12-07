package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.InsertPartyUsageShito0801And0807Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.InsertPartyUsageShito0801And0807Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.InsertPartyUsageShito0801And0807Y2025Logic;

/**
 * 使途報告書ヘッダ、表紙を保存する
 */
@Component
public class InsertPartyUsageShito0801And0807Logic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPartyUsageShito0801And0807Y2022Logic insertPartyUsageShito0801And0807Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPartyUsageShito0801And0807Y2024Logic insertPartyUsageShito0801And0807Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPartyUsageShito0801And0807Y2025Logic insertPartyUsageShito0801And0807Y2025Logic;

    // NOTE:コンポーネントとswitchラベル追加位置

    /**
     * 表紙と宣誓書、ヘッダ部分を保存する
     *
     * @param allShitoBook 使途報告書XML
     */
    public Long practice(final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto,
            final AllShitoBook allShitoBook, final CheckPrivilegeDto checkPrivilegeDto) {

        Long code = 0L;
        switch (documentPropertyDto.getNendo()) {

            // 2022年
            case YEAR_2022:
                code = insertPartyUsageShito0801And0807Y2022Logic.practice(documentPropertyDto, allShitoBook,
                        checkPrivilegeDto);
                break;

            // 2024年
            case YEAR_2024:
                code = insertPartyUsageShito0801And0807Y2024Logic.practice(documentPropertyDto, allShitoBook,
                        checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                code = insertPartyUsageShito0801And0807Y2025Logic.practice(documentPropertyDto, allShitoBook,
                        checkPrivilegeDto);
                break;

            // NOTE:Logic実行追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + documentPropertyDto.getNendo());
        }

        return code;
    }

}
