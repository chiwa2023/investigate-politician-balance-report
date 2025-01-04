package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.exception.BadFactoryMethodFileStructureException;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * ファクトリメソッドLogic単体テストの年更新処理を追加する
 */
@Component
public class RefleshYearDataAccessFactoryMethodLogicTestTasklet implements Tasklet, StepExecutionListener {

    /** 複写先となる年数 */
    private String copyYear;

    /** 複写元となる年数(固定値) */
    private static final String BASE_YEAR = "2022";

    /** 元ファイル更新フラグ */
    private boolean isReadFileReflesh;

    /** 解析対象リスト(Java) */
    private List<String> listJavaPath = new ArrayList<>();

    /** 解析対象リスト(SQL) */
    private List<String> listSqlPath = new ArrayList<>();

    /** indexOfメソッドで存在しない場合の値 */
    private static final int INDEX_OFF_NOT_CONTAIN = -1;

    /** import文の追加位置 */
    private static final String IMPORT_POSITION = "// import追加指定位置";
    /** テンプレートドのスタート位置 */
    private static final String TEMPLATE_START = "    // テンプレート開始位置";
    /** テンプレートドのスタート位置 */
    private static final String TEMPLATE_END = "    // テンプレート終了位置";

    /** テンプレートの貼り付け位置 */
    private static final String PASTE_POS = "// 追加位置";

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        listJavaPath = new ArrayList<>();
        listSqlPath = new ArrayList<>();

        final String keyCopyYear = "copyYear";
        final String keyIsReflesh = "isReadFileReflesh";
        final String keyJava = "java";
        final String keySql = "sql";

        Map<String, JobParameter<?>> map = stepExecution.getJobParameters().getParameters();
        for (String key : map.keySet().stream().sorted().toList()) {

            // 複写先年
            if (keyCopyYear.equals(key)) {
                copyYear = map.get(key).getValue().toString(); // NOPMD
            }
            // 元ファイル更新フラグ
            if (keyIsReflesh.equals(key)) {
                isReadFileReflesh = Boolean.parseBoolean(map.get(key).getValue().toString()); // NOPMD
            }

            // Java更新対象リスト
            if (key.startsWith(keyJava)) {
                listJavaPath.add(map.get(key).getValue().toString()); // NOPMD
            }

            // SQL更新対象リスト
            if (key.startsWith(keySql)) {
                listSqlPath.add(map.get(key).getValue().toString()); // NOPMD
            }
        }
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        // ファイル解析部分をループする(Java)
        for (int index = 0; index < listJavaPath.size(); index++) {
            // 作成処理
            this.createTemplateJava(index);
        }

        // ファイル解析部分をループする(SQL)
        for (int index = 0; index < listSqlPath.size(); index++) {
            // 作成処理
            this.createTemplateSql(index);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private void createTemplateJava(final int fileIndex) throws IOException, BadFactoryMethodFileStructureException {
        List<String> listBody = Files
                .readAllLines(Paths.get(GetCurrentResourcePath.getBackSrcPath(listJavaPath.get(fileIndex))));

        List<String> listNewContets = new ArrayList<>();
        List<String> listAdd = new ArrayList<>();
        boolean isInAlready = false;
        boolean isInTemplate = false;
        boolean isAdd = false;
        String line;
        String baseImport = "";
        for (int index = 0; index < listBody.size(); index++) {

            line = listBody.get(index);

            // コピー先の年のデータが含まれていた場合は中断
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(copyYear)) {
                isInAlready = true;
                break;
            }

            // import貼り付け位置の場合はひとつ前の行を置換して追加
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(IMPORT_POSITION)) {
                baseImport = listBody.get(index - 1);
                listNewContets.add(baseImport.replaceAll(this.getPreLineYear(baseImport), copyYear));
            }

            // テンプレート位置がスタートしている場合はコピーリストに複写
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(TEMPLATE_END)) {
                isInTemplate = false;
            }

            // テンプレート内であれば追加リストに追加(テンプレート開始コメントと終了コメントをつけないためにはこの位置でないと問題)
            if (isInTemplate) {
                listAdd.add(line.replaceAll(BASE_YEAR, copyYear));
            }

            // テンプレート位置がスタートしている場合はコピーリストに複写
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(TEMPLATE_START)) {
                isInTemplate = true;
            }

            // 貼り付け位置に来ている場合は貼り付け
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(PASTE_POS)) {
                listNewContets.addAll(listAdd);
                listNewContets.add("");
                isAdd = true;
            }

            listNewContets.add(line);
        }

        // コピー報告年がすでに入っている場合は更新しない
        if (!isInAlready) {

            // 正しく処理が行われているか、最低限のチェックをする
            this.checkFileStructure(baseImport, listAdd.size(), isInTemplate, isAdd);

            Path pathCopy = Paths.get(GetCurrentResourcePath.getBackSrcPath(this.getCopyJavaPath(fileIndex)));
            Files.createDirectories(pathCopy.getParent());
            Files.write(pathCopy, listNewContets);
        }

    }

    private void createTemplateSql(final int fileIndex) throws IOException, BadFactoryMethodFileStructureException {
        List<String> listBody = Files
                .readAllLines(Paths.get(GetCurrentResourcePath.getBackSrcPath(listSqlPath.get(fileIndex))));

        List<String> listNewContets = new ArrayList<>();
        for (String line : listBody) {
            listNewContets.add(line.replaceAll(BASE_YEAR, copyYear));
        }

        // コピー報告年がすでに入っている場合は更新しない
        Path pathCopy = Paths.get(GetCurrentResourcePath.getBackSrcPath(this.getCopySqlPath(fileIndex)));
        Files.createDirectories(pathCopy.getParent());
        Files.write(pathCopy, listNewContets);
    }

    private void checkFileStructure(final String importText, final int addLineSize, final boolean isInTemplate,
            final boolean isAdd) throws BadFactoryMethodFileStructureException {

        final String blank = "";

        if (blank.equals(importText)) {
            throw new BadFactoryMethodFileStructureException("import貼り付け位置コメントが存在しません");
        }

        if (0 == addLineSize) {
            throw new BadFactoryMethodFileStructureException("テンプレート開始位置コメントが存在しません");
        }

        if (isInTemplate) {
            throw new BadFactoryMethodFileStructureException("テンプレート終了位置コメントが存在しません");
        }

        if (!isAdd) {
            throw new BadFactoryMethodFileStructureException("追加位置指定コメントが存在しません");
        }

    }

    /**
     * 複写先ファイルパスを取得する(SQL)
     *
     * @param filePath 解析対象パス
     * @return 複写先ファイスパス
     */
    protected String getCopySqlPath(final int index) {

        return this.convertPath(listSqlPath.get(index)).replaceAll(BASE_YEAR, copyYear);
    }

    /**
     * 複写先ファイルパスを取得する(Java)
     *
     * @param filePath 解析対象パス
     * @return 複写先ファイスパス
     */
    protected String getCopyJavaPath(final int index) {

        return this.convertPath(listJavaPath.get(index));
    }

    private String convertPath(final String filePath) {
        // 解析対象ファイルを更新する
        if (isReadFileReflesh) {
            return filePath;
        }

        int posEnd = filePath.lastIndexOf(".");

        String copyPath = filePath.substring(0, posEnd) + "NewDiff" + filePath.substring(posEnd, filePath.length());

        // 解析ファイルがテスト環境でない場合は無理やりテスト環境に持ち込む

        if (filePath.startsWith("test/resources")) {
            copyPath = copyPath.replaceAll("mitei/mitei/create/report/balance/politician/", "batch/");
        } else {

            copyPath = copyPath.replaceAll("test/java/mitei/mitei/create/report/balance/politician/",
                    "test/resources/batch/");
        }

        return copyPath;
    }

    private String getPreLineYear(final String baseImport) throws BadFactoryMethodFileStructureException {
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(baseImport);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new BadFactoryMethodFileStructureException(IMPORT_POSITION + "直上のテンプレートとなる行に年数表記が存在しません");
        }
    }

}
