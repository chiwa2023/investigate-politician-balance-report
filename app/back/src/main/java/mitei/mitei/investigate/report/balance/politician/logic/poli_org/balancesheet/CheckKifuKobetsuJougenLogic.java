package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KobetsuKifuJougenResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDantaiKbnListLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuKobetsuJogenByNameY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckKifuKobetsuJogenByNameY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckKifuKobetsuJogenByNameY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckKifuKobetsuJogenByNameY2025Logic;

/**
 * 個別規制(初回限定)検索結果取得Logic
 */
@Component
public class CheckKifuKobetsuJougenLogic {

    /** ページ取得行 */
    private static final int OFFSET = 100;

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
     * @param houokokuNen 報告年
     * @param poliOrgCode 政治団体同一識別コード
     * @return 総計リスト
     */
    public KobetsuKifuJougenResultDto practice(final Integer houokokuNen, final Integer poliOrgCode) {

        KobetsuKifuJougenResultDto resultDto = new KobetsuKifuJougenResultDto();
        Pageable pageable = Pageable.ofSize(OFFSET).withPage(0);

        final int searchKifuKbn = GetDantaiKbnListLogic.SEARCH_OTHER;

        switch (houokokuNen) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByNameY2022Logic.practice(poliOrgCode, searchKifuKbn,
                        YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByNameY2022Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2022

            // 2023年
            case YEAR_2023:
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByNameY2023Logic.practice(poliOrgCode, searchKifuKbn,
                        YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByNameY2023Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2023

            // 2024年
            case YEAR_2024:
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByNameY2024Logic.practice(poliOrgCode, searchKifuKbn,
                        YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByNameY2024Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2024

            // 2025年
            case YEAR_2025:
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByNameY2025Logic.practice(poliOrgCode, searchKifuKbn,
                        YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByNameY2025Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2022

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + houokokuNen);
        }

        return resultDto;
    }

}
