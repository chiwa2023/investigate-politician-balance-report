package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0901Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.InsertPartyUsageShito0901Y2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024.InsertPartyUsageShito0901Y2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025.InsertPartyUsageShito0901Y2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2023.InsertPartyUsageShito0901Y2023Logic;
// importを追加

/**
 * 使途報告書様式9その1を保存する
 */
@Component
public class InsertPartyUsageShito0901Logic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 様式9(その1)2022年登録Logic */
    @Autowired // 2022
    private InsertPartyUsageShito0901Y2022Logic insertPartyUsageShito0901Y2022Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 様式9(その1)2024年登録Logic */
    @Autowired
    private InsertPartyUsageShito0901Y2024Logic insertPartyUsageShito0901Y2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 様式9(その1)2025年登録Logic */
    @Autowired
    private InsertPartyUsageShito0901Y2025Logic insertPartyUsageShito0901Y2025Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 様式9(その1)2023年登録Logic */
    private InsertPartyUsageShito0901Y2023Logic insertPartyUsageShito0901Y2023Logic;

    // フィールドの追加位置

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param shito0901Dto        様式9(その1)Dto
     * @param checkPrivilegeDto   権限確認Dto
     * @return 想定登録結果
     */
    public int practice(final Long documentCode, final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto,
            final Shito0901Dto shito0901Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        int size = 0;
        switch (documentPropertyDto.getNendo()) {
            
            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                size = insertPartyUsageShito0901Y2022Logic.practice(documentCode, documentPropertyDto, shito0901Dto,
                        checkPrivilegeDto); // 2022
                break; // 2022

            // 2024年
            case YEAR_2024:
                size = insertPartyUsageShito0901Y2024Logic.practice(documentCode, documentPropertyDto, shito0901Dto,
                        checkPrivilegeDto);
                break;

            // 2025年
            case YEAR_2025:
                size = insertPartyUsageShito0901Y2025Logic.practice(documentCode, documentPropertyDto, shito0901Dto,
                        checkPrivilegeDto);
                break;

            // 2023年
            case YEAR_2023:
                size = insertPartyUsageShito0901Y2023Logic.practice(documentCode, documentPropertyDto, shito0901Dto,
                        checkPrivilegeDto);
                break;

            // caseの追加位置

            default:
                break;
        }

        return size;
    }
}
