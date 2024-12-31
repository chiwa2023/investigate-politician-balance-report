package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai;

import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ConvertFukisaiOutcomeLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblFukisaiBalancesheetRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;

/**
 * 支出不記載ワークテーブル書き込みTasklet
 */
@Component
public class WriteOutcomeDataFukisaiTasklet implements Tasklet, StepExecutionListener {

    /** 不記載検出ワークテーブル */
    @Autowired
    private WkTblFukisaiBalancesheetRepository wkTblFukisaiBalancesheetRepository;

    /** 支出不記載ワークテーブル変換Logic */
    @Autowired
    private ConvertFukisaiOutcomeLogic convertFukisaiOutcomeLogic;

    /** 操作者Id */
    private Long userId;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 操作者名前 */
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

        if (!isSearchCode) {
            // 名称検索するときは団体名を取っておく
            Optional<WkTblFukisaiBalancesheetEntity> optional = wkTblFukisaiBalancesheetRepository
                    .findFirstByInsertUserCode(userCode);
            if (optional.isEmpty()) {
                // 名称検索をしようとして、データが取得できない場合は処理正常終了
                return RepeatStatus.FINISHED;
            } else {
                conditionDto.setDantaiName(optional.get().getDantaiName());
            }
        }

        wkTblFukisaiBalancesheetRepository.saveAll(convertFukisaiOutcomeLogic.practice(conditionDto,
                CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName)));
        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
