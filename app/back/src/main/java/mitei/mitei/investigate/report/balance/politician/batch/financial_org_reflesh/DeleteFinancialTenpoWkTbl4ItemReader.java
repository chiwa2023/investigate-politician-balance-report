package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchMasterRepository;

/**
 * 追加金融機関店舗ItemReader
 */
@Component
public class DeleteFinancialTenpoWkTbl4ItemReader extends RepositoryItemReader<ZenginOrgBranchMasterEntity> {

    /**
     * コンストラクタ
     *
     * @param zenginOrgBranchMasterRepository 全銀マスタテーブルRepository
     */
    public DeleteFinancialTenpoWkTbl4ItemReader(
            final @Autowired ZenginOrgBranchMasterRepository zenginOrgBranchMasterRepository) {

        super();
        super.setRepository(zenginOrgBranchMasterRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findDelteBranchWkTbl4");
        super.setArguments(new ArrayList<Object>()); // NOPMD
    }

}
