package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.ZenginOrgChangeKbnConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;

/**
 * 全銀金融機関支店異動のうち追加を抽出するItemReader
 */
@Component
public class AddFinancialTenpoChangeItemReader extends RepositoryItemReader<ZenginOrgChangeBranchEntity> {

    /**
     * コンストラクタ
     *
     * @param zenginOrgChangeBranchRepository 全銀金融機関支店異動Repository
     */
    public AddFinancialTenpoChangeItemReader(
            final @Autowired ZenginOrgChangeBranchRepository zenginOrgChangeBranchRepository) {

        super();
        super.setRepository(zenginOrgChangeBranchRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByChangeKbnAndIsFinishedAndSaishinKbn");

        List<Object> list = new ArrayList<>();
        list.add(ZenginOrgChangeKbnConstants.ADD);
        list.add(false);
        list.add(DataHistoryStatusConstants.INSERT.value());
        super.setArguments(list); // NOPMD
    }

}
