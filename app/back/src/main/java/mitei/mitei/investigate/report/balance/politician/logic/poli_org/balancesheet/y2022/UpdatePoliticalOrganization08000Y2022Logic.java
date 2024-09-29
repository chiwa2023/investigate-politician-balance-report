package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetDifficalt0800Recipt2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetDifficalt0800Recipt2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 2022年様式8の既存最新データを履歴にする
 */
@Component
public class UpdatePoliticalOrganization08000Y2022Logic {

    /** 様式8 領収書を徴しがたかった支出項目一覧表Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2022Repository offeringBalancesheetDifficalt0800Recipt2022Repository;

    /**
     * 更新作業を行う
     *
     * @param oldCode 履歴とする文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新件数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetDifficalt0800Recipt2022Entity> list = offeringBalancesheetDifficalt0800Recipt2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingBalancesheetDifficalt0800Recipt2022Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }
        
        return offeringBalancesheetDifficalt0800Recipt2022Repository.saveAll(list).size();
    }
}
