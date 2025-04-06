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
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CopyBalancesheetWkTblIncomeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CopyBalancesheetWkTblOutcomeLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetIncomeRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblBalancesheetOutcomeRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;

/**
 * 収支報告書をワークテーブルに複写する
 */
@Component
public class CopyBalancesheetWkTblTasklet implements Tasklet, StepExecutionListener {

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
    private CopyBalancesheetWkTblIncomeLogic copyBalancesheetWkTblIncomeLogic;

    /** 収支報告書支出ワークテーブル用抽出Logic */
    @Autowired
    private CopyBalancesheetWkTblOutcomeLogic copyBalancesheetWkTblOutcomeLogic;

    /** 収支報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetIncomeRepository wkTblBalancesheetIncomeRepository;

    /** 収支報告書支出ワークテーブルRepository */
    @Autowired
    private WkTblBalancesheetOutcomeRepository wkTblBalancesheetOutcomeRepository;


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
        documentCode = stepExecution.getJobParameters().getLong("documentCodeBalance");
    }
    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        CheckPrivilegeDto privilegeDto = CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName);

        // 収入をワークテーブルに複写する
        int countIncome = copyBalancesheetWkTblIncomeLogic.practiceSize(houkokuNen, documentCode);
        int chunkSizeIncome = copyBalancesheetWkTblIncomeLogic.getChunkSize();
        int maxPageNumberIncome = countIncome / chunkSizeIncome + 1;
        for (int pageNumber = 0; pageNumber < maxPageNumberIncome; pageNumber++) {
            wkTblBalancesheetIncomeRepository.saveAll(copyBalancesheetWkTblIncomeLogic.practice(houkokuNen,
                    documentCode, pageNumber, chunkSizeIncome, privilegeDto));
        }

        // 支出をワークテーブルに複写する
        int countOutcome = copyBalancesheetWkTblOutcomeLogic.practiceSize(houkokuNen, documentCode);
        int chunkSizeOutcome = copyBalancesheetWkTblOutcomeLogic.getChunkSize();
        int maxPageNumberOutcome = countOutcome / chunkSizeOutcome + 1;
        for (int pageNumber = 0; pageNumber < maxPageNumberOutcome; pageNumber++) {
            wkTblBalancesheetOutcomeRepository.saveAll(copyBalancesheetWkTblOutcomeLogic.practice(houkokuNen,
                    documentCode, pageNumber, chunkSizeOutcome, privilegeDto));
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
