package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk3Repository;

/**
 * 移転金融機関店舗ItemReader
 */
@Component
public class MoveFinancialTenpoWkTbl3ItemReader extends RepositoryItemReader<ZenginOrgBranchWk3Entity> {

    /**
     * コンストラクタ
     *
     * @param zenginOrgBranchWk3Repository 全銀ワークテーブル1Repository
     */
    public MoveFinancialTenpoWkTbl3ItemReader(
            final @Autowired ZenginOrgBranchWk3Repository zenginOrgBranchWk3Repository) {

        super();
        super.setRepository(zenginOrgBranchWk3Repository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findMoveBranch");
        super.setArguments(new ArrayList<Object>()); // NOPMD
    }
}
