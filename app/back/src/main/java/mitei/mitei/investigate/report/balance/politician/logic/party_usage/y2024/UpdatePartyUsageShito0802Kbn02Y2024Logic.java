package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0802Kbn02Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0802Kbn02Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式8その2区分2の新規データを履歴データに変更する
 */
@Component
public class UpdatePartyUsageShito0802Kbn02Y2024Logic {

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2024Repository offeringPartyUsage0802Kbn02Report2024Repository;

    /**
     * 変更処理を行う
     *
     * @param oldCode           変更する文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0802Kbn02Report2024Entity> list = offeringPartyUsage0802Kbn02Report2024Repository
                .findByDocumentCodeOrderByPartyUsage0802Kbn02ReportId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingPartyUsage0802Kbn02Report2024Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        return offeringPartyUsage0802Kbn02Report2024Repository.saveAll(list).size();
    }

}
