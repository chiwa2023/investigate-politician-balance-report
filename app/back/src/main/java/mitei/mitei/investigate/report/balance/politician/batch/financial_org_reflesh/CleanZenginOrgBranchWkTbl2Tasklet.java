package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk2Repository;

/**
 * 全銀協記入機関・店舗ワークテーブル2を初期化する
 */
@Component
public class CleanZenginOrgBranchWkTbl2Tasklet  implements Tasklet{

    /** 全銀協記入機関・店舗ワークテーブル1Repository */
    @Autowired
    private ZenginOrgBranchWk2Repository zenginOrgBranchWk2Repository;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        
        // 全削除してidを初期化
        zenginOrgBranchWk2Repository.deleteAll();
        zenginOrgBranchWk2Repository.initialId();
        
        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
