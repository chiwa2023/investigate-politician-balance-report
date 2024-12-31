package mitei.mitei.investigate.report.balance.politician.batch.task_plan;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.logic.task_plan.UpdateFinishedByTaskNameUserLogic;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;

/**
 * 不記載検出ワークテーブル作業完了フラグを立てる
 */
@Component
public class FinishTaskPlanTasklet implements Tasklet, StepExecutionListener {

    /** 操作者同一識別コード */
    private Long userId;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 操作者同一識別コード */
    private String userName;

    /** タスク計画同一識別コード */
    private Integer taskPlanCode;

    /** タスク登録年 */
    private Integer year;

    /** タスク名 */
    private String taskName;

    /** タスク計画呼び出しLogic */
    @Autowired
    private UpdateFinishedByTaskNameUserLogic updateFinishedByTaskNameUserLogic;

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

        taskPlanCode = Math.toIntExact(stepExecution.getJobParameters().getLong("taskPlanCode"));
        taskName = stepExecution.getJobParameters().getString("taskName");
        year = Math.toIntExact(stepExecution.getJobParameters().getLong("year"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        updateFinishedByTaskNameUserLogic.practice(taskPlanCode,
                CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName), taskName, year);

        // 処理終了
        return RepeatStatus.FINISHED;

    }
}
