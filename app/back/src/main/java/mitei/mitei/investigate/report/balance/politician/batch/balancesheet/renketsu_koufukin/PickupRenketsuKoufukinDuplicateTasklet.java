package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.RenketsuKoufukinConstants;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;

/**
 * 交付金連結データから重複計上を抽出して区分を変更する
 */
@Component
public class PickupRenketsuKoufukinDuplicateTasklet implements Tasklet, StepExecutionListener {

    /** ユーザCode */
    private Integer userCode;

    /** 交付金連結ワークテーブル */
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

        // 収入
        List<Long> listIncomeDuplicate = wkTblRenketsuKoufukinRepository.findRenketsuDuplicate(userCode,
                RenketsuKoufukinConstants.INCOME);
        wkTblRenketsuKoufukinRepository.updateDataKbnByUserCode(userCode, listIncomeDuplicate,
                RenketsuKoufukinConstants.INCOME_DUPLICATE,RenketsuKoufukinConstants.INCOME);

        // 支出
        List<Long> listOutcomeDuplicate = wkTblRenketsuKoufukinRepository.findRenketsuDuplicate(userCode,
                RenketsuKoufukinConstants.OUTCOME);
        wkTblRenketsuKoufukinRepository.updateDataKbnByUserCode(userCode, listOutcomeDuplicate,
                RenketsuKoufukinConstants.OUTCOME_DUPLICATE,RenketsuKoufukinConstants.OUTCOME);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
