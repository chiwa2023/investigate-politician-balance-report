package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;

/**
 * 政治資金収支報告書一括処理ワークテーブル(政治団体未指定)を検索する
 */
@Service
public class SearchRegistPartyUsageWkTbErrorService {

    
    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    /**
     * 検索処理を行う
     *
     * @return 検索結果
     */
    public List<WkTblPoliPartyUsageReportEntity> practice() {
        return wkTblPoliPartyUsageReportRepository
                .findBySaishinKbnAndPoliticalOrganizationId(DataHistoryStatusConstants.INSERT.value(), 0L);
    }

}
