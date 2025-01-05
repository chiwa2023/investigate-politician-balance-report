package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.ConvertFukisaiIncomeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.ConvertFukisaiIncomeY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.ConvertFukisaiIncomeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.ConvertFukisaiIncomeY2023Logic;
// importを追加

/**
 * 収支データを不記載ワークテーブルに変換するFactoryLogic
 */
@Component
public class ConvertFukisaiIncomeLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private ConvertFukisaiIncomeY2022Logic convertFukisaiIncomeY2022Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private ConvertFukisaiIncomeY2025Logic convertFukisaiIncomeY2025Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private ConvertFukisaiIncomeY2024Logic convertFukisaiIncomeY2024Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private ConvertFukisaiIncomeY2023Logic convertFukisaiIncomeY2023Logic;

    // フィールドの追加位置

    /**
     * 処理を行う
     *
     * @param conditionDto 検索条件Dto
     * @param privilegeDto 権限確認Dto
     * @return 検索結果
     */
    public List<WkTblFukisaiBalancesheetEntity> practice(final FukisaiSearchConditionDto conditionDto,
            final CheckPrivilegeDto privilegeDto) {

        int key = conditionDto.getHoukokuNen();

        switch (key) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                return convertFukisaiIncomeY2022Logic.practice(conditionDto, privilegeDto);

            // 2025年
            case YEAR_2025:
                return convertFukisaiIncomeY2025Logic.practice(conditionDto, privilegeDto);

            // 2024年
            case YEAR_2024:
                return convertFukisaiIncomeY2024Logic.practice(conditionDto, privilegeDto);

            // 2023年
            case YEAR_2023:
                return convertFukisaiIncomeY2023Logic.practice(conditionDto, privilegeDto);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
    }

}
