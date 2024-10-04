package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書の支出を最新データを履歴データに変更する
 */
@Component
public class UpdatePoliticalOrganizationOutcomeAllY2022Logic {

    /** 政治資金収支報告書支出提出分Repository */
    @Autowired
    private OfferingBalancesheetOutcome2022Repository offeringBalancesheetOutcome2022Repository;

    /**
     * 履歴変更処理を行う
     *
     * @param oldCode           変更すべき古い文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 変更件数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetOutcome2022Entity> list = offeringBalancesheetOutcome2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetOutcomeId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingBalancesheetOutcome2022Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        return offeringBalancesheetOutcome2022Repository.saveAll(list).size();
    }

}
