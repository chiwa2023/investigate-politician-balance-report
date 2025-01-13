package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuSouryouKiseiByCorpCodeY2022Logic;
// importを追加

/**
 * 企業団体総計取得ファクトリメソッドLogic
 */
@Component
public class CheckKifuSouryouKiseiByCorpCodeLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CheckKifuSouryouKiseiByCorpCodeY2022Logic checkKifuSouryouKiseiByCorpCodeY2022Logic;

    /**
     * 処理を行う
     *
     * @param houokokuNen 報告年
     * @param poliOrgCode 政治団体同一識別コード
     * @param listDantaiKbn 団体区分リスト
     * @return 総計リスト
     */
    public List<SouryouKiseiRelationCodeDto> practice(final Integer houokokuNen, final Integer poliOrgCode,
            final List<String> listDantaiKbn) {

        switch (houokokuNen) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                return checkKifuSouryouKiseiByCorpCodeY2022Logic.practice(poliOrgCode, listDantaiKbn);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + houokokuNen);
        }
    }

}
