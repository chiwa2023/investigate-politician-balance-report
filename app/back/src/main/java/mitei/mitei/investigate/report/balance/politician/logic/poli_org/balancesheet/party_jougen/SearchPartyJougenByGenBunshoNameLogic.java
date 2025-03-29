package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.party_jougen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.SearchPartyJougenByGenbunshoNameY2022Logic;

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
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }
    }

}
