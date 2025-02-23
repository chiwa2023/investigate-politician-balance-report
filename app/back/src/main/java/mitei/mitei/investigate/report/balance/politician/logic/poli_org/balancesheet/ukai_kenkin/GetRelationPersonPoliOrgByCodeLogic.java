package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.GetRelationPersonPoliOrgByCodeY2022Logic;

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

    /**
     * 処理を行う
     *
     * @param houkokuNen 報告年
     * @param poliOrgCode 政治団体同一識別コード
     * @return 政治団体属性Entity
     */
    public PoliticalOrganizationPropertyEntity practice(final Integer houkokuNen,final Integer poliOrgCode) {
        
        switch (houkokuNen) {
            // 2022年
            case YEAR_2022:
                return getRelationPersonPoliOrgByCodeY2022Logic.practice(poliOrgCode);

            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

    }
}
