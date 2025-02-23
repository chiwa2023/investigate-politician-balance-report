package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuSouryouKiseiByCorpCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckKifuSouryouKiseiByCorpCodeY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckKifuSouryouKiseiByCorpCodeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckKifuSouryouKiseiByCorpCodeY2025Logic;
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

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private CheckKifuSouryouKiseiByCorpCodeY2023Logic checkKifuSouryouKiseiByCorpCodeY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private CheckKifuSouryouKiseiByCorpCodeY2024Logic checkKifuSouryouKiseiByCorpCodeY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private CheckKifuSouryouKiseiByCorpCodeY2025Logic checkKifuSouryouKiseiByCorpCodeY2025Logic;

    /**
     * 処理を行う
     *
     * @param houokokuNen   報告年
     * @param poliOrgCode   政治団体同一識別コード
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

            // 2023年
            case YEAR_2023:
                return checkKifuSouryouKiseiByCorpCodeY2023Logic.practice(poliOrgCode, listDantaiKbn);

            // 2024年
            case YEAR_2024:
                return checkKifuSouryouKiseiByCorpCodeY2024Logic.practice(poliOrgCode, listDantaiKbn);

            // 2025年
            case YEAR_2025:
                return checkKifuSouryouKiseiByCorpCodeY2025Logic.practice(poliOrgCode, listDantaiKbn);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + houokokuNen);
        }
    }

}
