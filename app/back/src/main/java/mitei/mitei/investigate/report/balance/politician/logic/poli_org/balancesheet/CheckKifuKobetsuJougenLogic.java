package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KobetsuKifuJougenResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDantaiKbnListLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuKobetsuJogenByNameY2022Logic;

/**
 * 個別規制(初回限定)検索結果取得Logic
 */
@Component
public class CheckKifuKobetsuJougenLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CheckKifuKobetsuJogenByNameY2022Logic checkKifuKobetsuJogenByNameY2022Logic;

    /** ページ取得行 */
    private static final int OFFSET = 100;

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
                resultDto.setResultPersonalDto(checkKifuKobetsuJogenByNameY2022Logic.practice(poliOrgCode, searchKifuKbn,
                        YoushikiEdaKbn.KOJIN, pageable));
                break; // 2022

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + houokokuNen);
        }

        return resultDto;
    }

}
