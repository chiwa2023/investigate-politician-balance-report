package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuSouryouKiseiByPersonCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.CheckKifuSouryouKiseiByPersonCodeY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.CheckKifuSouryouKiseiByPersonCodeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.CheckKifuSouryouKiseiByPersonCodeY2025Logic;
// importを追加

/**
 * 寄付総額を関連者個人同一識別コードを元に取得するファクトリメソッドLogic
 */
@Component
public class CheckKifuSouryouKiseiByPersonCodeLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CheckKifuSouryouKiseiByPersonCodeY2022Logic checkKifuSouryouKiseiByPersonCodeY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private CheckKifuSouryouKiseiByPersonCodeY2023Logic checkKifuSouryouKiseiByPersonCodeY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private CheckKifuSouryouKiseiByPersonCodeY2024Logic checkKifuSouryouKiseiByPersonCodeY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private CheckKifuSouryouKiseiByPersonCodeY2025Logic checkKifuSouryouKiseiByPersonCodeY2025Logic;

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
                return checkKifuSouryouKiseiByPersonCodeY2022Logic.practice(poliOrgCode, listDantaiKbn);

            // 2023年
            case YEAR_2023:
                return checkKifuSouryouKiseiByPersonCodeY2023Logic.practice(poliOrgCode, listDantaiKbn);

            // 2024年
            case YEAR_2024:
                return checkKifuSouryouKiseiByPersonCodeY2024Logic.practice(poliOrgCode, listDantaiKbn);

            // 2025年
            case YEAR_2025:
                return checkKifuSouryouKiseiByPersonCodeY2025Logic.practice(poliOrgCode, listDantaiKbn);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + houokokuNen);
        }
    }

}
