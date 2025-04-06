package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.logic.political_party.CopyUsageWkTblIncomeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.political_party.CopyUsageWkTblOutcomeLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageIncomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageOutcomeRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;

/**
 * 使途報告書データをワークテーブルに複写する
 */
@Component
public class CopyUsageWkTblTasklet implements Tasklet, StepExecutionListener {

    /** ユーザId */
    private Long userId;
    /** ユーザCode */
    private Integer userCode;
    /** ユーザ名称 */
    private String userName;

    /** 報告年 */
    private Integer houkokuNen;

    /** 文書同一識別コード */
    private Long documentCode;

    /** 収支報告書収入ワークテーブル用抽出Logic */
    @Autowired
    private CopyUsageWkTblIncomeLogic copyUsageWkTblIncomeLogic;

    /** 収支報告書支出ワークテーブル用抽出Logic */
    @Autowired
    private CopyUsageWkTblOutcomeLogic copyUsageWkTblOutcomeLogic;

    /** 収支報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblUsageIncomeRepository wkTblUsageIncomeRepository;

    /** 収支報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblUsageOutcomeRepository wkTblUsageOutcomeRepository;

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        userId = stepExecution.getJobParameters().getLong("userId");
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
        userName = stepExecution.getJobParameters().getString("userName");
        houkokuNen = Math.toIntExact(stepExecution.getJobParameters().getLong("houkokuNen"));
        documentCode = stepExecution.getJobParameters().getLong("documentCodeUsage");
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        CheckPrivilegeDto privilegeDto = CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName);

        // 収入をワークテーブルに複写する
        int countIncome = copyUsageWkTblIncomeLogic.practiceSize(houkokuNen, documentCode);
        int chunkSizeIncome = copyUsageWkTblIncomeLogic.getChunkSize();
        int maxPageNumberIncome = countIncome / chunkSizeIncome + 1;
        for (int pageNumber = 0; pageNumber < maxPageNumberIncome; pageNumber++) {
            wkTblUsageIncomeRepository.saveAll(copyUsageWkTblIncomeLogic.practice(houkokuNen, documentCode, pageNumber,
                    chunkSizeIncome, privilegeDto));
        }

        // 支出をワークテーブルに複写する
        int countOutcome = copyUsageWkTblOutcomeLogic.practiceSize(houkokuNen, documentCode);
        int chunkSizeOutcome = copyUsageWkTblOutcomeLogic.getChunkSize();
        int maxPageNumberOutcome = countOutcome / chunkSizeOutcome + 1;
        for (int pageNumber = 0; pageNumber < maxPageNumberOutcome; pageNumber++) {
            wkTblUsageOutcomeRepository.saveAll(copyUsageWkTblOutcomeLogic.practice(houkokuNen, documentCode,
                    pageNumber, chunkSizeOutcome, privilegeDto));
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
