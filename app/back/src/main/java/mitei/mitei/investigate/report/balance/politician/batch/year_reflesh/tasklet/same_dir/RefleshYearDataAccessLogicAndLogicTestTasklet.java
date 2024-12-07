package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet.same_dir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * 政治資金収支報告書、政党交付金使途報告書の挿入Logicの複写Tasklet
 */
@StepScope
@Component
public class RefleshYearDataAccessLogicAndLogicTestTasklet implements Tasklet, StepExecutionListener {

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

        // 機能ごとのBaseフォルダ(ここではLogic)
        final String pathFunctionMail = "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_alert/mail/y";
        final String pathFunctionTask = "main/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y";
        final String pathFunctionStorage = "main/java/mitei/mitei/investigate/report/balance/politician/logic/storage/y";

        // Logic複写作業
        this.worksByOneFunction(pathFunctionMail + baseYear, pathFunctionMail + copyYear);
        this.worksByOneFunction(pathFunctionTask + baseYear, pathFunctionTask + copyYear);
        this.worksByOneFunction(pathFunctionStorage + baseYear, pathFunctionStorage + copyYear);

        // 機能ごとのBaseフォルダ(ここではLogicTest)
        final String pathFunctionMailTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_alert/mail/y";
        final String pathFunctionTaskTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y";
        final String pathFunctionStorageTest = "test/java/mitei/mitei/investigate/report/balance/politician/logic/storage/y";

        final String pathFunctionMailTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/task_alert/mail/y";
        final String pathFunctionTaskTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/task_plan/y";
        final String pathFunctionStorageTestSql = "test/resources/mitei/mitei/investigate/report/balance/politician/logic/storage/y";

        // LogicTest複写作業
        this.worksByOneFunction(pathFunctionMailTest + baseYear, pathFunctionMailTest + copyYear);
        this.worksByOneFunction(pathFunctionTaskTest + baseYear, pathFunctionTaskTest + copyYear);
        this.worksByOneFunction(pathFunctionStorageTest + baseYear, pathFunctionStorageTest + copyYear);
        this.worksByOneFunction(pathFunctionMailTestSql + baseYear, pathFunctionMailTestSql + copyYear);
        this.worksByOneFunction(pathFunctionTaskTestSql + baseYear, pathFunctionTaskTestSql + copyYear);
        this.worksByOneFunction(pathFunctionStorageTestSql + baseYear, pathFunctionStorageTestSql + copyYear);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private void worksByOneFunction(final String baseDir, final String copyDir) throws IOException {

        String baseAbsPath = GetCurrentResourcePath.getBackSrcPath(baseDir);
        String copyAbsPath = GetCurrentResourcePath.getBackSrcPath(copyDir);

        // コピー先のフォルダを作成する
        Files.createDirectories(Paths.get(copyAbsPath));

        // 取得できたファイルの分だけループする
        List<Path> list = Files.list(Paths.get(baseAbsPath)).toList();
        for (Path entry : list) {
            // ディレクトリでない=ファイルのみコピー対象
            if (!Files.isDirectory(entry)) {
                this.copyFile(entry, copyAbsPath);
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
