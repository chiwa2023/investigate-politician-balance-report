package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0718Estate2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0719RealEstate2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0718Estate2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0719RealEstate2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書の資産明細を最新から履歴に変更する
 */
@Component
public class UpdatePoliticalOrganizationEstateAllY2025Logic {

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2025Repository offeringBalancesheet0718Estate2025Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2025Repository offeringBalancesheet0719RealEstate2025Repository;

    /**
     * 履歴変更処理を行う
     *
     * @param oldCode           変更すべき古い文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 変更件数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheet0718Estate2025Entity> listEstate = offeringBalancesheet0718Estate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingBalancesheet0718Estate2025Entity entity : listEstate) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        int sizeEstate = offeringBalancesheet0718Estate2025Repository.saveAll(listEstate).size();

        List<OfferingBalancesheet0719RealEstate2025Entity> listRealEstate = offeringBalancesheet0719RealEstate2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingBalancesheet0719RealEstate2025Entity entity : listRealEstate) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        int sizeRealEstate = offeringBalancesheet0719RealEstate2025Repository.saveAll(listRealEstate).size();

        return sizeEstate + sizeRealEstate;
    }

}
