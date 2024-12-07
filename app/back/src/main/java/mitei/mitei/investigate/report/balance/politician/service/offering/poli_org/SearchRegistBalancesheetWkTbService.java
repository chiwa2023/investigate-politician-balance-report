package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;

/**
 * 政治資金収支報告書一括処理ワークテーブル(政治団体推定／指定済)を検索する
 */
@Service
public class SearchRegistBalancesheetWkTbService {

    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    /**
     * 検索処理を行う
     *
     * @return 検索結果
     */
    public List<WkTblPoliOrgBalancesheetReportEntity> practice() {
        return wkTblPoliOrgBalancesheetReportRepository
                .findBySaishinKbnAndPoliticalOrganizationIdNot(DataHistoryStatusConstants.INSERT.value(), 0L);
    }

}
