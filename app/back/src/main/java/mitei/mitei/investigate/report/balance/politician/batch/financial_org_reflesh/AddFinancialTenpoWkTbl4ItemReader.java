package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk4Entity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk4Repository;

/**
 * 追加金融機関店舗ItemReader
 */
@Component
public class AddFinancialTenpoWkTbl4ItemReader extends RepositoryItemReader<ZenginOrgBranchWk4Entity> {

    /**
     * コンストラクタ
     *
     * @param zenginOrgBranchWk1Repository 全銀ワークテーブル1Repository
     */
    public AddFinancialTenpoWkTbl4ItemReader(
            final @Autowired ZenginOrgBranchWk4Repository zenginOrgBranchWk1Repository) {

        super();
        super.setRepository(zenginOrgBranchWk1Repository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findAddBranch");
        super.setArguments(new ArrayList<Object>()); // NOPMD
    }

}
