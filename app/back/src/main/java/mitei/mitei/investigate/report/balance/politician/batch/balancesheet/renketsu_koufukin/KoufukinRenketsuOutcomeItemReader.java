package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.RenketsuKoufukinConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinSelectDto;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageOutcomeRepository;

/**
 * 交付金連結支出ItemReader
 */
@Component
public class KoufukinRenketsuOutcomeItemReader extends RepositoryItemReader<WkTblRenketsuKoufukinSelectDto> {

    /**
     * コンストラクタ
     *
     * @param wkTblUsageOutcomeRepository 使途報告書支出Repository
     */
    public KoufukinRenketsuOutcomeItemReader(final @Autowired WkTblUsageOutcomeRepository wkTblUsageOutcomeRepository) {
        super();
        super.setRepository(wkTblUsageOutcomeRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findRenketsuEntity");
        super.setArguments(new ArrayList<Object>()); // NOPMD
    }

    /**
     * read実行前に起動条件を取得する
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        // 起動条件を設定
        List<Object> listArguments = new ArrayList<>();
        listArguments.add(Math.toIntExact(stepExecution.getJobParameters().getLong("userCode")));
        listArguments.add(stepExecution.getJobParameters().getLong("documentCodeUsage"));
        listArguments.add(stepExecution.getJobParameters().getLong("documentCodeBalance"));
        listArguments.add(RenketsuKoufukinConstants.OUTCOME);

        super.setArguments(listArguments); // NOPMD
    }

}
