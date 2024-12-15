package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk4Repository;

/**
 * 全銀協記入機関・店舗ワークテーブル4を初期化する
 */
@Component
public class CleanZenginOrgBranchWkTbl4Tasklet  implements Tasklet{

    /** 全銀協記入機関・店舗ワークテーブル1Repository */
    @Autowired
    private ZenginOrgBranchWk4Repository zenginOrgBranchwk4Repository;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        
        // 全削除してidを初期化
        zenginOrgBranchwk4Repository.deleteAll();
        zenginOrgBranchwk4Repository.initialId();
        
        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
