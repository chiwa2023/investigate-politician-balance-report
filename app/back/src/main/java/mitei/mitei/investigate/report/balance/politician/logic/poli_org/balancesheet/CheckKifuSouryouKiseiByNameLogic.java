package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuSouryouKiseiByNameY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckKifuSouryouKiseiByNameY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckKifuSouryouKiseiByNameY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckKifuSouryouKiseiByNameY2025Logic;
// importを追加

/**
 * 寄付総額原文書基準取得ファクトリメソッドLogic
 */
@Component
public class CheckKifuSouryouKiseiByNameLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CheckKifuSouryouKiseiByNameY2022Logic checkKifuSouryouKiseiByNameY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private CheckKifuSouryouKiseiByNameY2023Logic checkKifuSouryouKiseiByNameY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private CheckKifuSouryouKiseiByNameY2024Logic checkKifuSouryouKiseiByNameY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private CheckKifuSouryouKiseiByNameY2025Logic checkKifuSouryouKiseiByNameY2025Logic;

    /**
     * 処理を行う
     *
     * @param houokokuNen    報告年
     * @param poliOrgCode    政治団体同一識別コード
     * @param youshikiEdaKbn 様式枝区分
     * @param listDantaiKbn  団体区分リスト
     * @return 総計リスト
     */
    public List<SouryouKiseiRelationCodeDto> practice(final Integer houokokuNen, final Integer poliOrgCode,
            final Integer youshikiEdaKbn, final List<String> listDantaiKbn) {

        switch (houokokuNen) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                return checkKifuSouryouKiseiByNameY2022Logic.practice(poliOrgCode, youshikiEdaKbn, listDantaiKbn);

            // 2023年
            case YEAR_2023:
                return checkKifuSouryouKiseiByNameY2023Logic.practice(poliOrgCode, youshikiEdaKbn, listDantaiKbn);

            // 2024年
            case YEAR_2024:
                return checkKifuSouryouKiseiByNameY2024Logic.practice(poliOrgCode, youshikiEdaKbn, listDantaiKbn);

            // 2025年
            case YEAR_2025:
                return checkKifuSouryouKiseiByNameY2025Logic.practice(poliOrgCode, youshikiEdaKbn, listDantaiKbn);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + houokokuNen);
        }
    }

}
