package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.party_jougen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.SearchPartyJougenByCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.SearchPartyJougenByCodeY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.SearchPartyJougenByCodeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.SearchPartyJougenByCodeY2025Logic;

/**
 * パーティ上限集計を返す単年ファクトリメソッド(コード基準)
 */
@Component
public class SearchPartyJougenByCodeLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private SearchPartyJougenByCodeY2022Logic searchPartyJougenByCodeY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private SearchPartyJougenByCodeY2023Logic searchPartyJougenByCodeY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private SearchPartyJougenByCodeY2024Logic searchPartyJougenByCodeY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private SearchPartyJougenByCodeY2025Logic searchPartyJougenByCodeY2025Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件格納Dto
     * @return 検索結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final KifuJougenConditionCapsuleDto capsuleDto) {

        int houkokuNen = capsuleDto.getHoukokuNen();
        switch (houkokuNen) {

            case YEAR_2022:
                return searchPartyJougenByCodeY2022Logic.practice(capsuleDto);

            case YEAR_2023:
                return searchPartyJougenByCodeY2023Logic.practice(capsuleDto);

            case YEAR_2024:
                return searchPartyJougenByCodeY2024Logic.practice(capsuleDto);

            case YEAR_2025:
                return searchPartyJougenByCodeY2025Logic.practice(capsuleDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }
    }
}
