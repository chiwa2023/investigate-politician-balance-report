package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk2Repository;

/**
 * 追加金融機関店舗ItemReader
 */
@Component
public class AddFinancialTenpoWkTbl2ItemReader extends RepositoryItemReader<ZenginOrgBranchWk2Entity> {

    /**
     * コンストラクタ
     *
     * @param zenginOrgBranchWk2Repository 全銀ワークテーブル1Repository
     */
    public AddFinancialTenpoWkTbl2ItemReader(
            final @Autowired ZenginOrgBranchWk2Repository zenginOrgBranchWk2Repository) {

        super();
        super.setRepository(zenginOrgBranchWk2Repository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findAddBranch");
        super.setArguments(new ArrayList<Object>()); // NOPMD
    }

}
