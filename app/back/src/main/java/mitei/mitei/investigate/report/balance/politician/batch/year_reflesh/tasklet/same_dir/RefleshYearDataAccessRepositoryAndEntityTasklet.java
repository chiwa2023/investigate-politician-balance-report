package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.same_dir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;



/**
 * 政治資金収支報告書、政党交付金使途報告書Repositoryを複写する
 */
@Component
public class RefleshYearDataAccessRepositoryAndEntityTasklet implements Tasklet, StepExecutionListener {

    /** 複写元となる年数 */
    private String baseYear;
    /** 複写先となる年数 */
    private String copyYear;

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        baseYear = stepExecution.getJobParameters().getString("baseYear");
        copyYear = stepExecution.getJobParameters().getString("copyYear");
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // 機能ごとのBaseフォルダ(ここではRepository)
        final String pathFunctionMailRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/mail/";
        final String pathFunctionTaskRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/task_plan/";
        final String pathFunctionStorageRepository = "main/java/mitei/mitei/investigate/report/balance/politician/repository/storage/";

        this.worksByOneFunction(pathFunctionMailRepository);
        this.worksByOneFunction(pathFunctionTaskRepository);
        this.worksByOneFunction(pathFunctionStorageRepository);

        // 機能ごとのBaseフォルダ(ここではEntity)
        final String pathFunctionMailEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/mail/";
        final String pathFunctionTaskEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/task_plan/";
        final String pathFunctionStorageEntity = "main/java/mitei/mitei/investigate/report/balance/politician/entity/storage/";

        this.worksByOneFunction(pathFunctionMailEntity);
        this.worksByOneFunction(pathFunctionTaskEntity);
        this.worksByOneFunction(pathFunctionStorageEntity);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private void worksByOneFunction(final String pathFunction) throws IOException {

        String baseAbsPath = GetCurrentResourcePath.getBackSrcPath(pathFunction);

        // 取得できたファイルの分だけループする
        List<Path> list = Files.list(Paths.get(baseAbsPath)).toList();
        for (Path entry : list) {
            
            if(entry.toString().indexOf(baseYear) != -1) {
                this.copyFile(entry, baseAbsPath);
            }
        }

    }

    private void copyFile(final Path entry, final String copyAbsPath) throws IOException {

        // 内容はbaseYearをcopyYearで全置換
        String baseContent = Files.readString(entry);
        String copyContent = baseContent.replaceAll(baseYear, copyYear);

        // ファイル名も同様に置換
        String fileName = entry.getFileName().toString().replaceAll(baseYear, copyYear);

        // 書き込み
        Files.writeString(Paths.get(copyAbsPath, fileName), copyContent);
    }

}
