package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuKobetsuJogenByRelationCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckKifuKobetsuJogenByRelationCodeY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckKifuKobetsuJogenByRelationCodeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckKifuKobetsuJogenByRelationCodeY2025Logic;

/**
 * 寄付明細同一識別コード取得ファクトリメソッドLogic
 */
@Component
public class GetKifuKobetsuJogenByRelationCodeMeisaiLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CheckKifuKobetsuJogenByRelationCodeY2022Logic checkKifuKobetsuJogenByRelationCodeY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private CheckKifuKobetsuJogenByRelationCodeY2023Logic checkKifuKobetsuJogenByRelationCodeY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private CheckKifuKobetsuJogenByRelationCodeY2024Logic checkKifuKobetsuJogenByRelationCodeY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private CheckKifuKobetsuJogenByRelationCodeY2025Logic checkKifuKobetsuJogenByRelationCodeY2025Logic;

    /**
     * 処理を行う
     *
     * @param conditionDto 明細取得条件Dto
     * @return 検索結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final KifuJougenConditionCapsuleDto conditionDto) {

        int key = conditionDto.getHoukokuNen();
        Pageable pageable = Pageable.ofSize(conditionDto.getOffset()).withPage(conditionDto.getPageNum());

        switch (key) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                return checkKifuKobetsuJogenByRelationCodeY2022Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);
            // 2023年
            case YEAR_2023:
                return checkKifuKobetsuJogenByRelationCodeY2023Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);
            // 2024年
            case YEAR_2024:
                return checkKifuKobetsuJogenByRelationCodeY2024Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);
            // 2025年
            case YEAR_2025:
                return checkKifuKobetsuJogenByRelationCodeY2025Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
    }

}
