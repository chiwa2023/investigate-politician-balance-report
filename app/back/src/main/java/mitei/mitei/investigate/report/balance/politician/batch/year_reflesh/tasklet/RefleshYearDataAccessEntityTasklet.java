package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.tasklet;

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
 * 政治資金収支報告書、政党交付金使途報告書Entityを複写する
 */
@Component
public class RefleshYearDataAccessEntityTasklet implements Tasklet, StepExecutionListener {

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

        // 機能ごとのBaseフォルダ(ここではEntity)

        /* 提出文書部門 */
        final String pathFunctionUsage = "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_party/usage/y";
        final String pathFunctionBalancesheet = "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_org/balancesheet/y";
        this.worksByOneFunction(pathFunctionUsage);
        this.worksByOneFunction(pathFunctionBalancesheet);

        /* タスク通知部門 */
        final String pathFunctionMailEntity = "main/java/mitei/mitei/create/report/balance/politician/entity/mail/";
        final String pathFunctionTaskEntity = "main/java/mitei/mitei/create/report/balance/politician/entity/task_plan/";

        this.worksByOneFunction(pathFunctionMailEntity);
        this.worksByOneFunction(pathFunctionTaskEntity);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private void worksByOneFunction(final String pathFunction) throws IOException {

        final String baseDir = pathFunction + baseYear;
        final String copyDir = pathFunction + copyYear;

        String baseAbsPath = GetCurrentResourcePath.getBackSrcPath(baseDir);
        String copyAbsPath = GetCurrentResourcePath.getBackSrcPath(copyDir);

        // コピー先のフォルダを作成する
        Files.createDirectories(Paths.get(copyAbsPath));

        // 取得できたファイルの分だけループする
        List<Path> list = Files.list(Paths.get(baseAbsPath)).toList();
        for (Path entry : list) {
            this.copyFile(entry, copyAbsPath);
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
