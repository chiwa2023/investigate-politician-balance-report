package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
 * メール通知SNSダイレクトメッセージ通知DDLを複写する
 */
@StepScope
@Component
public class RefleshYearDataAccessDdlTasklet implements Tasklet, StepExecutionListener {

    /** 複写元となる年数 */
    private String baseYear;
    /** 複写先となる年数 */
    private String copyYear;

    /** キーワード@Tableを探すための検索キー */
    private static final String KEY_TABLE = "@Table(name = \"";

    /** 複写先取得用相対ディレクトリ */
    private static final String copyPath = "main/resources/SQL/DDL";

    /** 複写先絶対ディレクトリ */
    private String copyAbsPath;

    /** Entityの存在するディレクトリ */
    private String dirEntity;

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        baseYear = stepExecution.getJobParameters().getString("baseYear");
        copyYear = stepExecution.getJobParameters().getString("copyYear");
        dirEntity = stepExecution.getJobParameters().getString("dirEntity");
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        this.copyAbsPath = GetCurrentResourcePath.getBackSrcPath(copyPath);

        // テーブル名リストを作成する
        List<String> listTablename = new ArrayList<>();
        listTablename.addAll(this.createTableList(dirEntity));

        for (String tablename : listTablename) {
            if (-1 != tablename.indexOf(baseYear)) {
                this.copyFile(tablename);
            }
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private List<String> createTableList(final String pathSearch) throws IOException {

        String searchAbsPath = GetCurrentResourcePath.getBackSrcPath(pathSearch);
        if (searchAbsPath.endsWith("\\y")) {
            searchAbsPath = searchAbsPath + baseYear;
        }

        List<String> listTablename = new ArrayList<>();

        // Entityフォルダをループ
        List<Path> listEntity = Files.list(Paths.get(searchAbsPath)).toList();
        for (Path entry : listEntity) {
            if (!Files.isDirectory(entry)) {
                listTablename.add(this.pickupTableInFile(entry));
            }
        }

        return listTablename;
    }

    private String pickupTableInFile(final Path entry) throws IOException {

        // Entityクラスソースを行単位で開く
        List<String> list = Files.readAllLines(entry);

        for (String line : list) {
            if (line.startsWith(KEY_TABLE)) {

                int posStart = KEY_TABLE.length();
                int posEnd = line.indexOf("\"", posStart + 1);
                return line.substring(posStart, posEnd);
            }
        }

        throw new IllegalArgumentException(KEY_TABLE + "で開始する行が見つからず、テーブルが特定できませんでした");
    }

    private void copyFile(final String tablename) throws IOException {

        Path entry = Paths.get(copyAbsPath, tablename.toLowerCase(Locale.JAPANESE) + ".sql");
        // 内容はbaseYearをcopyYearで全置換
        String baseContent = Files.readString(entry);
        String copyContent = baseContent.replaceAll(baseYear, copyYear);

        // ファイル名も同様
        String fileName = entry.getFileName().toString().replaceAll(baseYear, copyYear);
        Files.writeString(Paths.get(copyAbsPath, fileName), copyContent);
    }

}
