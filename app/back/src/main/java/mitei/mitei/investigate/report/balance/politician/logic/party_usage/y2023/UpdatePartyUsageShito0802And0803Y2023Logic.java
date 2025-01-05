package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2023;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2023.OfferingPartyUsage0802And0803Report2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0802And0803Report2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式8その2区分1と3の新規データを履歴データに変更する
 */
@Component
public class UpdatePartyUsageShito0802And0803Y2023Logic {

    /** 様式8その2区分1と3Repository */
    @Autowired
    private OfferingPartyUsage0802And0803Report2023Repository offeringPartyUsage0802And0803Report2023Repository;

    /**
     * 変更処理を行う
     *
     * @param oldCode           変更する文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0802And0803Report2023Entity> list = offeringPartyUsage0802And0803Report2023Repository
                .findByDocumentCodeOrderByPartyUsage0802And0803ReportId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingPartyUsage0802And0803Report2023Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        return offeringPartyUsage0802And0803Report2023Repository.saveAll(list).size();
    }

}
