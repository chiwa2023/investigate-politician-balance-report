package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuKobetsuJogenByNameY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckKifuKobetsuJogenByNameY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckKifuKobetsuJogenByNameY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckKifuKobetsuJogenByNameY2025Logic;

/**
 * 寄付明細原文書名称取得ファクトリメソッドLogic
 */
@Component
public class GetKifuKobetsuJogenByNameMeisaiLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CheckKifuKobetsuJogenByNameY2022Logic checkKifuKobetsuJogenByNameY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private CheckKifuKobetsuJogenByNameY2023Logic checkKifuKobetsuJogenByNameY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private CheckKifuKobetsuJogenByNameY2024Logic checkKifuKobetsuJogenByNameY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private CheckKifuKobetsuJogenByNameY2025Logic checkKifuKobetsuJogenByNameY2025Logic;

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
                return checkKifuKobetsuJogenByNameY2022Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);

            // 2023年
            case YEAR_2023:
                return checkKifuKobetsuJogenByNameY2023Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);

            // 2024年
            case YEAR_2024:
                return checkKifuKobetsuJogenByNameY2024Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);

            // 2025年
            case YEAR_2025:
                return checkKifuKobetsuJogenByNameY2025Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
    }

}
