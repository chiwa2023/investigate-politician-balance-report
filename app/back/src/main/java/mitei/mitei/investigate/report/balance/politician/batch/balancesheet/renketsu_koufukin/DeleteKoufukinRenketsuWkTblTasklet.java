package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetIncomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetOutcomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageIncomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageOutcomeRepository;

/**
 * 交付金連結で使用するワークテーブルを削除する
 */
@Component
public class DeleteKoufukinRenketsuWkTblTasklet implements Tasklet, StepExecutionListener {

    /** ユーザCode */
    private Integer userCode;

    /** 収支報告書収入ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetIncomeRepository wkTblBalancesheetIncomeRepository;

    /** 収支報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetOutcomeRepository wkTblBalancesheetOutcomeRepository;

    /** 使途報告書収入ワークテーブルRepository */
    @Autowired
    private WkTblUsageIncomeRepository wkTblUsageIncomeRepository;

    /** 使途報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblUsageOutcomeRepository wkTblUsageOutcomeRepository;

    /** 交付金連結ワークテーブルRepository */
    @Autowired
    private WkTblRenketsuKoufukinRepository wkTblRenketsuKoufukinRepository;

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

        // 該当ユーザのデータを削除
        wkTblBalancesheetIncomeRepository.deleteByInsertUserCode(userCode);
        wkTblBalancesheetOutcomeRepository.deleteByInsertUserCode(userCode);
        wkTblUsageIncomeRepository.deleteByInsertUserCode(userCode);
        wkTblUsageOutcomeRepository.deleteByInsertUserCode(userCode);
        wkTblRenketsuKoufukinRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
