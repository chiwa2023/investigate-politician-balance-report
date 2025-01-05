package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0803Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.InsertPartyUsageShito0802And0803Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.InsertPartyUsageShito0802And0803Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.InsertPartyUsageShito0802And0803Y2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2023.InsertPartyUsageShito0802And0803Y2023Logic;
// importを追加

/**
 * 使途報告書様式8その2(区分1)、区分2を保存する
 */
@Component
public class InsertPartyUsageShito0802And0803Logic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private InsertPartyUsageShito0802And0803Y2022Logic insertPartyUsageShito0802And0803Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2022
    private InsertPartyUsageShito0802And0803Y2024Logic insertPartyUsageShito0802And0803Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired
    private InsertPartyUsageShito0802And0803Y2025Logic insertPartyUsageShito0802And0803Y2025Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    private InsertPartyUsageShito0802And0803Y2023Logic insertPartyUsageShito0802And0803Y2023Logic;

    // フィールドの追加位置

    /**
     * 登録作業を行う
     *
     * @param shito0802Dto 様式8その2(区分1だけ使用)
     * @param shito0803Dto 様式8その3
     */
    public int practice(final Long documentCode, final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto,
            final Shito0802Dto shito0802Dto, final Shito0803Dto shito0803Dto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getNendo()) {
            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                size = insertPartyUsageShito0802And0803Y2022Logic.practice(documentCode, documentPropertyDto,
                        shito0802Dto, shito0803Dto, checkPrivilegeDto); // 2022
                break; // 2022

            // 2024年
            case YEAR_2024:
                size = insertPartyUsageShito0802And0803Y2024Logic.practice(documentCode, documentPropertyDto,
                        shito0802Dto, shito0803Dto, checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPartyUsageShito0802And0803Y2025Logic.practice(documentCode, documentPropertyDto,
                        shito0802Dto, shito0803Dto, checkPrivilegeDto);
                break;

            // 2023年
            case YEAR_2023:
                size = insertPartyUsageShito0802And0803Y2023Logic.practice(documentCode, documentPropertyDto,
                        shito0802Dto, shito0803Dto, checkPrivilegeDto);
                break;

            // caseの追加位置

            default:
                break;
        }

        return size;
    }

}
