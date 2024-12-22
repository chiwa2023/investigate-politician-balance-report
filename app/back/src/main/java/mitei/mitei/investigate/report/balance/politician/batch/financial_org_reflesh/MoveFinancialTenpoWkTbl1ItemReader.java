package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk1Repository;

/**
 * 移転金融機関店舗ItemReader
 */
@Component
public class MoveFinancialTenpoWkTbl1ItemReader extends RepositoryItemReader<ZenginOrgBranchWk1Entity> {

    /**
     * コンストラクタ
     *
     * @param zenginOrgBranchWk1Repository 全銀ワークテーブル1Repository
     */
    public MoveFinancialTenpoWkTbl1ItemReader(
            final @Autowired ZenginOrgBranchWk1Repository zenginOrgBranchWk1Repository) {

        super();
        super.setRepository(zenginOrgBranchWk1Repository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findMoveBranch");
        super.setArguments(new ArrayList<Object>()); // NOPMD
    }
}
