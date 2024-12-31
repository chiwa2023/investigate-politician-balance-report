package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.repository.WkTblFukisaiBalancesheetRepository;

/**
 * 不記載検出ワークテーブルから操作者データを削除する
 */
@Component
public class EraseWkTblMyDataTasklet implements Tasklet, StepExecutionListener {

    /** 不記載検出ワークテーブル */
    @Autowired
    private WkTblFukisaiBalancesheetRepository wkTblFukisaiBalancesheetRepository;

    
    /** 操作者同一識別コード */
    private Integer userCode;
    
    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        userCode =  Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        
        wkTblFukisaiBalancesheetRepository.deleteByInsertUserCode(userCode);
        
        if(0 == wkTblFukisaiBalancesheetRepository.count()) {
            wkTblFukisaiBalancesheetRepository.initialId();
            wkTblFukisaiBalancesheetRepository.flush();
        }
        
        // 処理終了
        return RepeatStatus.FINISHED;
        
    }
}
