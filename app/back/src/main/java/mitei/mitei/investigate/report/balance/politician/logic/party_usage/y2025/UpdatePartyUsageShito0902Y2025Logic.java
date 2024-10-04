package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式その9その2の新規データを履歴データに変更する
 */
@Component
public class UpdatePartyUsageShito0902Y2025Logic {

    /** 様式その9その2テーブルRepository */
    @Autowired
    private OfferingPartyUsage0902Report2025Repository offeringPartyUsage0902Report2025Repository;

    /**
     * 変更処理を行う
     *
     * @param oldCode           変更する文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0902Report2025Entity> list = offeringPartyUsage0902Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0902ReportId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingPartyUsage0902Report2025Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        return offeringPartyUsage0902Report2025Repository.saveAll(list).size();
    }

}
