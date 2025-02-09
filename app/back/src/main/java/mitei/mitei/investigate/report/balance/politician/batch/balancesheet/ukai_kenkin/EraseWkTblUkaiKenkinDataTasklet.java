package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * 迂回献金ワークテーブルの操作者のデータを削除する
 */
public class EraseWkTblUkaiKenkinDataTasklet implements Tasklet, StepExecutionListener{

    
    /** 迂回献金関係データ詳細Repojitory */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;
    
    /** 迂回献金抽出ルートRepojitory */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;
    
    
    /** 操作者同一識別コード */
    private Integer userCode;

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));

    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
    
        // ワークテーブルの自身のデータを消去
        wkTblUkaiKenkinRepository.deleteByInsertUserCode(userCode);
        wkTblUkaiKenkinPickupRouteRepository.deleteByInsertUserCode(userCode);
        
        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
