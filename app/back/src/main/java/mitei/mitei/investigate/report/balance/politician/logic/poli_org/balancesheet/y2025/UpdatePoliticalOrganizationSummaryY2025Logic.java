package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書の集計表(収入・支出・資産)を最新から履歴に変更する
 */
@Component
public class UpdatePoliticalOrganizationSummaryY2025Logic {

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2025Repository offeringBalancesheet0702And0713And0717Summary2025Repository;

    /**
     * 履歴変更処理を行う
     *
     * @param oldCode           変更すべき古い文書同一識別コード
     * @param checkPrivilegeDto 権限確認Dto
     * @return 変更件数
     */
    public int practice(final Long oldCode, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheet0702And0713And0717Summary2025Entity> list = offeringBalancesheet0702And0713And0717Summary2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(oldCode);

        // 取得できた数を更新に値をセット
        for (OfferingBalancesheet0702And0713And0717Summary2025Entity entity : list) {
            SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.UPDATE);
        }

        return offeringBalancesheet0702And0713And0717Summary2025Repository.saveAll(list).size();

    }

}
