package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ConvertFukisaiIncomeLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblFukisaiBalancesheetRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;

/**
 * 支出テーブルを不記載ワークテーブルに記載するTasklet
 */
@Component
public class WriteIncomeDataFukisaiTasklet implements Tasklet, StepExecutionListener {

    /** 不記載検出ワークテーブル */
    @Autowired
    private WkTblFukisaiBalancesheetRepository wkTblFukisaiBalancesheetRepository;

    /** 不記載取得Logic */
    @Autowired
    private ConvertFukisaiIncomeLogic convertFukisaiIncomeLogic;

    /** 操作者同一識別コード */
    private Long userId;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 操作者同一識別コード */
    private String userName;

    /** コード検索該当 */
    private Boolean isSearchCode;

    /** 政治団体同一識別コード */
    private Integer poliOrgCode;

    /** 報告年 */
    private Integer houkokunen;

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

        poliOrgCode = Math.toIntExact(stepExecution.getJobParameters().getLong("poliOrgCode"));
        isSearchCode = Boolean.valueOf(stepExecution.getJobParameters().getString("isSearchCode"));
        houkokunen = Math.toIntExact(stepExecution.getJobParameters().getLong("houkokunen"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        FukisaiSearchConditionDto conditionDto = new FukisaiSearchConditionDto();
        conditionDto.setIsSearchCode(isSearchCode);
        conditionDto.setPoliOrgCode(poliOrgCode);
        conditionDto.setHoukokuNen(houkokunen);

        wkTblFukisaiBalancesheetRepository.saveAll(convertFukisaiIncomeLogic.practice(conditionDto,
                CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName)));

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
