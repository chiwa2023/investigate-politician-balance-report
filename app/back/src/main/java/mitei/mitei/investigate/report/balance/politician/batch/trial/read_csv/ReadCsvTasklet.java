package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * サンプルのタスクレット
 */
@Component
public class ReadCsvTasklet implements Tasklet {

    /**
     * タスクレット実行内容
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        System.out.println("tasklet !!");

        return RepeatStatus.FINISHED;
    }

}
