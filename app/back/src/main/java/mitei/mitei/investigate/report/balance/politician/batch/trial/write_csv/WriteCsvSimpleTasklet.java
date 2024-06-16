package mitei.mitei.investigate.report.balance.politician.batch.trial.write_csv;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * 固定Csvを書き出すタスクレット
 */
@Component
public class WriteCsvSimpleTasklet implements Tasklet {

    /** 書き出しPath */
    public static final String PATH_WRTITE = "c:/temp/tasklet_test.csv";

    /**
     * 固定Csvを書き出すタスクレット
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        
        //StepBuilderを使っている限り(chunkを渡す実装をしない限り)基本的に空のはず

        //ジョブパラメータの受け取り
        //JobParameters jobParameters =  contribution.getStepExecution().getJobParameters();
        //String fileName =  jobParameters.getString("readFileName");
        
        // 実際のファイル書き込み
        Path path = Paths.get(PATH_WRTITE);

        List<String> listContent = new ArrayList<>();
        listContent.add("1,2,3");
        listContent.add("a,b,c");

        Files.write(path, listContent);

        return RepeatStatus.FINISHED;
    }

}
