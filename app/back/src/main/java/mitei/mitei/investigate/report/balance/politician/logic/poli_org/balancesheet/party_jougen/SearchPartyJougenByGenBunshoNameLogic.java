package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.party_jougen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.SearchPartyJougenByGenbunshoNameY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.SearchPartyJougenByGenbunshoNameY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.SearchPartyJougenByGenbunshoNameY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.SearchPartyJougenByGenbunshoNameY2025Logic;

/**
 * パーティ上限集計を返す単年ファクトリメソッド(原文書名称基準)
 */
@Component
public class SearchPartyJougenByGenBunshoNameLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private SearchPartyJougenByGenbunshoNameY2022Logic searchPartyJougenByGenbunshoNameY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private SearchPartyJougenByGenbunshoNameY2023Logic searchPartyJougenByGenbunshoNameY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private SearchPartyJougenByGenbunshoNameY2024Logic searchPartyJougenByGenbunshoNameY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private SearchPartyJougenByGenbunshoNameY2025Logic searchPartyJougenByGenbunshoNameY2025Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final KifuJougenConditionCapsuleDto capsuleDto) {

        int houkokuNen = capsuleDto.getHoukokuNen();
        switch (houkokuNen) {

            case YEAR_2022:
                return searchPartyJougenByGenbunshoNameY2022Logic.practice(capsuleDto);

            case YEAR_2023:
                return searchPartyJougenByGenbunshoNameY2023Logic.practice(capsuleDto);

            case YEAR_2024:
                return searchPartyJougenByGenbunshoNameY2024Logic.practice(capsuleDto);

            case YEAR_2025:
                return searchPartyJougenByGenbunshoNameY2025Logic.practice(capsuleDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }
    }

}
