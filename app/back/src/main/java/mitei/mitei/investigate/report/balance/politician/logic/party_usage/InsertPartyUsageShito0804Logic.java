package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0804Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.InsertPartyUsageShito0804Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.InsertPartyUsageShito0804Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.InsertPartyUsageShito0804Y2025Logic;

/**
 * 使途報告書様式8その4を保存する
 */
@Component
public class InsertPartyUsageShito0804Logic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPartyUsageShito0804Y2022Logic insertPartyUsageShito0804Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired
    private InsertPartyUsageShito0804Y2024Logic insertPartyUsageShito0804Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPartyUsageShito0804Y2025Logic insertPartyUsageShito0804Y2025Logic;

    // NOTE:コンポーネントとswitchラベル追加位置

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 使途報告書文書属性
     * @param shito0804Dto        様式8その4
     * @param checkPrivilegeDto   権限Dto
     * @return 登録行数
     */
    public int practice(final boolean isSearchRelation, final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto, final Shito0804Dto shito0804Dto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getNendo()) {
            // 2022年
            case YEAR_2022:
                size = insertPartyUsageShito0804Y2022Logic.practice(isSearchRelation, documentCode, documentPropertyDto,
                        shito0804Dto, checkPrivilegeDto);
                break;

            // 2024年
            case YEAR_2024:
                size = insertPartyUsageShito0804Y2024Logic.practice(isSearchRelation, documentCode, documentPropertyDto,
                        shito0804Dto, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPartyUsageShito0804Y2025Logic.practice(isSearchRelation, documentCode, documentPropertyDto,
                        shito0804Dto, checkPrivilegeDto);
                break;

            // NOTE:Logic実行追加位置

            default:
                break;
        }

        return size;
    }

}
