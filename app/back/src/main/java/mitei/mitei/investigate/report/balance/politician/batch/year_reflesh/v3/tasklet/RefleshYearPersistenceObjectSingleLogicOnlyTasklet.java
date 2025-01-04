package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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
 * 一般的な単Logicでの年更新の複写Tasklet
 */
@StepScope
@Component
public class RefleshYearPersistenceObjectSingleLogicOnlyTasklet implements Tasklet, StepExecutionListener {

    /** 複写元となる年数 */
    private String baseYear;
    /** 複写先となる年数 */
    private String copyYear;

    /** Logicのベースディレクトリ */
    private String dirLogic;
    /** Logictestのベースディレクトリ */
    private String dirLogicTest;
    /** LogicTestSQLのベースディレクトリ */
    private String dirLogicTestSql;

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        baseYear = stepExecution.getJobParameters().getString("baseYear");
        copyYear = stepExecution.getJobParameters().getString("copyYear");
        dirLogic = stepExecution.getJobParameters().getString("dirLogic");
        dirLogicTest = stepExecution.getJobParameters().getString("dirLogicTest");
        dirLogicTestSql = stepExecution.getJobParameters().getString("dirLogicTestSql");
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        
        // Logic
        this.worksByOneFunction(dirLogic + baseYear, dirLogic + copyYear);
        
        // LogicTest
        if(!Objects.isNull(dirLogicTest)) {
            this.worksByOneFunction(dirLogicTest + baseYear, dirLogicTest + copyYear);
        }

        // LogicTestのSQL
        if(!Objects.isNull(dirLogicTestSql)) {
            this.worksByOneFunction(dirLogicTestSql + baseYear, dirLogicTestSql + copyYear);
        }

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
