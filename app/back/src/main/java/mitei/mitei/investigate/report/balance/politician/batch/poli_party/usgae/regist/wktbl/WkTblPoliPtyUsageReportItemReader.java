package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.wktbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;

/**
 * 政党交付金使途報告書ワークテーブル読み取りItemReader
 */
@Component
public class WkTblPoliPtyUsageReportItemReader
        extends RepositoryItemReader<WkTblPoliPartyUsageReportEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblPoliPartyUsageReportRepository 政党交付金使途報告書Repository
     */
    public WkTblPoliPtyUsageReportItemReader(
            final @Autowired WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository) {

        super();
        super.setRepository(wkTblPoliPartyUsageReportRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findBySaishinKbnAndPoliticalOrganizationIdNot");
        List<Object> listArgs = new ArrayList<>();
        listArgs.add(DataHistoryStatusConstants.INSERT.value()); // saishinKbn = 1:最新
        listArgs.add(0L); // politicalOrganizationId = 0 ;id=0は未指定で、未指定以外を抽出
        super.setArguments(listArgs);

    }
}
