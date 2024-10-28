package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0902Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.InsertPartyUsageShito0902Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.InsertPartyUsageShito0902Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.InsertPartyUsageShito0902Y2025Logic;

/**
 * 使途報告書様式9その2を保存する
 */
@Component
public class InsertPartyUsageShito0902Logic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPartyUsageShito0902Y2022Logic insertPartyUsageShito0902Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPartyUsageShito0902Y2024Logic insertPartyUsageShito0902Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPartyUsageShito0902Y2025Logic insertPartyUsageShito0902Y2025Logic;

    // NOTE:コンポーネントとswitchラベル追加位置

    /**
     * 様式9その2を保存する
     *
     * @param shito0902Dto 様式9その2
     */
    public int practice(final Long documentCode, final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto,
            final Shito0902Dto shito0902Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getNendo()) {
            // 2022年
            case YEAR_2022:
                size = insertPartyUsageShito0902Y2022Logic.practice(documentCode, documentPropertyDto, shito0902Dto,
                        checkPrivilegeDto);
                break;

            // 2024年
            case YEAR_2024:
                size = insertPartyUsageShito0902Y2024Logic.practice(documentCode, documentPropertyDto, shito0902Dto,
                        checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPartyUsageShito0902Y2025Logic.practice(documentCode, documentPropertyDto, shito0902Dto,
                        checkPrivilegeDto);
                break;

            // NOTE:Logic実行追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + documentPropertyDto.getNendo());
        }

        return size;
    }

}
