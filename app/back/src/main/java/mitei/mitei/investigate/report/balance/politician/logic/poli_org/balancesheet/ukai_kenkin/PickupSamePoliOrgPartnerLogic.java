package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * 検索対象の構成員が一人でも一致する取引先を抽出し、迂回献金経路に抽出する
 */
@Component
public class PickupSamePoliOrgPartnerLogic {

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository ukaiKenkinRepository;

    /** 迂回献金明細経路変換(内部比較)Logic */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByInClassLogic convertUkaiKenkinDetailToRouteByInClassLogic;

    /**
     * 処理を行う
     *
     * @param userCode       操作者同一識別コード
     * @param youshikiKbn    交付金様式区分
     * @param propertyEntity 検索団体の政治団体属性Entity
     * @return 迂回献金経路リスト
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> practice(final Integer userCode, final Integer youshikiKbn,
            final PoliticalOrganizationPropertyEntity propertyEntity) {

        List<WkTblUkaiKenkinEntity> list = ukaiKenkinRepository.findTradingByRelationPoliOrg(userCode, youshikiKbn);

        return convertUkaiKenkinDetailToRouteByInClassLogic.practice(list, propertyEntity);
    }

}
