package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.GetRelationPersonPoliOrgByCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.GetRelationPersonPoliOrgByCodeY2023Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.GetRelationPersonPoliOrgByCodeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.GetRelationPersonPoliOrgByCodeY2025Logic;

/**
 * 対象の政治団体に属する関連者すべてを抽出するファクトリメソッド
 */
@Component
public class GetRelationPersonPoliOrgByCodeLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private GetRelationPersonPoliOrgByCodeY2022Logic getRelationPersonPoliOrgByCodeY2022Logic;

    /** 登録対応年(2023) */
    private static final int YEAR_2023 = 2023;
    /** 登録対応年(2023)Logic */
    @Autowired // 2023
    private GetRelationPersonPoliOrgByCodeY2023Logic getRelationPersonPoliOrgByCodeY2023Logic;

    /** 登録対応年(2024) */
    private static final int YEAR_2024 = 2024;
    /** 登録対応年(2024)Logic */
    @Autowired // 2024
    private GetRelationPersonPoliOrgByCodeY2024Logic getRelationPersonPoliOrgByCodeY2024Logic;

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** 登録対応年(2025)Logic */
    @Autowired // 2025
    private GetRelationPersonPoliOrgByCodeY2025Logic getRelationPersonPoliOrgByCodeY2025Logic;

    /**
     * 処理を行う
     *
     * @param houkokuNen  報告年
     * @param poliOrgCode 政治団体同一識別コード
     * @return 政治団体属性Entity
     */
    public PoliticalOrganizationPropertyEntity practice(final Integer houkokuNen, final Integer poliOrgCode) {

        switch (houkokuNen) {
            // 2022年
            case YEAR_2022:
                return getRelationPersonPoliOrgByCodeY2022Logic.practice(poliOrgCode);

            // 2023年
            case YEAR_2023:
                return getRelationPersonPoliOrgByCodeY2023Logic.practice(poliOrgCode);

            // 2024年
            case YEAR_2024:
                return getRelationPersonPoliOrgByCodeY2024Logic.practice(poliOrgCode);

            // 2025年
            case YEAR_2025:
                return getRelationPersonPoliOrgByCodeY2025Logic.practice(poliOrgCode);

            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

    }
}
