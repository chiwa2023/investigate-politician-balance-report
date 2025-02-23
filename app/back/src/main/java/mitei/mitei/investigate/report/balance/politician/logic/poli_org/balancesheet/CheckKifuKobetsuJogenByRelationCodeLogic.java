package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KobetsuKifuJougenResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDantaiKbnListLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuKobetsuJogenByRelationCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckKifuKobetsuJogenByRelationCodeY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckKifuKobetsuJogenByRelationCodeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckKifuKobetsuJogenByRelationCodeY2025Logic;

/**
 * 個別規制(初回限定)検索結果取得Logic
 */
@Component
public class CheckKifuKobetsuJogenByRelationCodeLogic {

    /** ページ取得行 */
    private static final int OFFSET = 100;

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
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByRelationCodeY2022Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByRelationCodeY2022Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2022

            // 2023年
            case YEAR_2023:
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByRelationCodeY2023Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByRelationCodeY2023Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2023

            // 2024年
            case YEAR_2024:
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByRelationCodeY2024Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByRelationCodeY2024Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2024

            // 2025年
            case YEAR_2025:
                resultDto.setResultOrgDto(checkKifuKobetsuJogenByRelationCodeY2025Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.SEIJI_DANTAI, pageable));
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByRelationCodeY2025Logic.practice(poliOrgCode,
                        searchKifuKbn, YoushikiEdaKbn.KOJIN, pageable));
                break; // 2025

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + houokokuNen);
        }

        return resultDto;
    }

}
