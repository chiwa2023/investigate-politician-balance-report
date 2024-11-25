package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0901Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0901Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式9(その1)最新データを履歴データにする
 */
@Component
public class UpdatePartyUsageShito0901Y2024Logic {

    /** 様式9(その1)Repository */
    @Autowired
    private OfferingPartyUsage0901Report2024Repository offeringPartyUsage0901Report2024Repository;

    /**
     * 変更処理を行う
     *
     * @param oldCode           変更する文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0901Report2024Entity> list = offeringPartyUsage0901Report2024Repository
                .findByDocumentCodeOrderByPartyUsage0901ReportId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingPartyUsage0901Report2024Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        return offeringPartyUsage0901Report2024Repository.saveAll(list).size();
    }

}