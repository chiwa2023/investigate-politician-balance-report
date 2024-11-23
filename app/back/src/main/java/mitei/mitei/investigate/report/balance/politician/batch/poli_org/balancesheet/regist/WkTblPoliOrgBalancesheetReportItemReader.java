package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;

/**
 * 政治資金収支報告書ワークテーブル読み取りItemReader
 */
@Component
public class WkTblPoliOrgBalancesheetReportItemReader
        extends RepositoryItemReader<WkTblPoliOrgBalancesheetReportEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblPoliOrgBalancesheetReportRepository 政治資金収支報告書ワークテーブルRepository
     */
    public WkTblPoliOrgBalancesheetReportItemReader(
            final @Autowired WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository) {

        super();
        super.setRepository(wkTblPoliOrgBalancesheetReportRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findBySaishinKbnAndPoliticalOrganizationIdNot");
        List<Object> listArgs = new ArrayList<>();
        listArgs.add(DataHistoryStatusConstants.INSERT.value()); // saishinKbn = 1:最新
        listArgs.add(0L); // politicalOrganizationId = 0 ;id=0は未指定で、未指定以外を抽出
        super.setArguments(listArgs);

    }

}
