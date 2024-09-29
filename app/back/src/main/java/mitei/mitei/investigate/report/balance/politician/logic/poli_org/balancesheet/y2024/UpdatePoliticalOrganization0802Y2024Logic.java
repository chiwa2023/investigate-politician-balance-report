package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetWithdrawal0802Transfer2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetWithdrawal0802Transfer2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式08その2 支出項目別金額の内訳最新データを更新履歴に変更
 */
@Component
public class UpdatePoliticalOrganization0802Y2024Logic {
    
    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetWithdrawal0802Transfer2024Repository offeringBalancesheetWithdrawal0802Transfer2024Repository;


    /**
     * 履歴変更処理お行う
     *
     * @param oldCode 変更すべき古い文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 変更件数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {
        
        List<OfferingBalancesheetWithdrawal0802Transfer2024Entity> list = offeringBalancesheetWithdrawal0802Transfer2024Repository
                .findByDocumentCodeOrderByOfferingBalancesheetWithdrawal0802TransferId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingBalancesheetWithdrawal0802Transfer2024Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        return offeringBalancesheetWithdrawal0802Transfer2024Repository.saveAll(list).size();
    }

}
