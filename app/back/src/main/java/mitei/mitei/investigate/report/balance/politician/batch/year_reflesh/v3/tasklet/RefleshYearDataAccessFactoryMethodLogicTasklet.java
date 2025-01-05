package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.v3.tasklet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.batch.year_reflesh.exception.BadFactoryMethodFileStructureException;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * ファクトリロジックに新たな年を追加するTasklet
 */
@StepScope
@Component
public class RefleshYearDataAccessFactoryMethodLogicTasklet implements Tasklet, StepExecutionListener {

    /** 複写先となる年数 */
    private String copyYear;

    /** 元ファイル更新フラグ */
    private boolean isReadFileReflesh;

    /** 解析対象リスト */
    private List<String> listFilePath = new ArrayList<>();

    /** 複写先となる年数 */
    private String baseYear;

    /** import文の追加位置 */
    private static final String IMPORT_POSITION = "// importを追加";
    /** フィールドのスタート位置 */
    private static final String FIELD_START = "    // フィールドテンプレート始まり";
    /** フィールドの終端位置 */
    private static final String FIELD_END = "    // フィールドの追加位置";
    /** switch-caseのスタート位置 */
    private static final String CASE_START = "            // caseテンプレート始まり";
    /** switch-caseの終端位置 */
    private static final String CASE_END = "            // caseの追加位置";

    /** indexOfメソッドで存在しない場合の値 */
    private static final int INDEX_OFF_NOT_CONTAIN = -1;

    /** Case部分新規追加行リスト */
    private List<String> listCase = new ArrayList<>();
    /** Field部分新規追加行リスト */
    private List<String> listFields = new ArrayList<>();

    /** Case部分新規追加行リスト */
    private static final String NOT_WRITE_COMMENT = ":がソースに記載されていないため、自動処理の対象外です";

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        listFilePath = new ArrayList<>();

        Map<String, JobParameter<?>> map = stepExecution.getJobParameters().getParameters();

        final String keyCopyYear = "copyYear";
        final String keyIsReflesh = "isReadFileReflesh";
        final String keyFile = "file";

        for (String key : map.keySet().stream().sorted().toList()) {

            // 複写先年
            if (keyCopyYear.equals(key)) {
                copyYear = map.get(key).getValue().toString(); // NOPMD
            }
            // 元ファイル更新フラグ
            if (keyIsReflesh.equals(key)) {
                isReadFileReflesh = Boolean.parseBoolean(map.get(key).getValue().toString()); // NOPMD
            }
            // 解析対象に追加
            if (key.startsWith(keyFile)) {
                listFilePath.add(map.get(key).getValue().toString()); // NOPMD
            }
        }
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        int size = listFilePath.size();
        // ファイル解析部分をループする
        for (int index = 0; index < size; index++) {

            // 作成処理
            this.createTemplate(index);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private void createTemplate(final int fileIndex) throws IOException, BadFactoryMethodFileStructureException {

        listCase = new ArrayList<>();
        listFields = new ArrayList<>();

        List<String> listBody = Files
                .readAllLines(Paths.get(GetCurrentResourcePath.getBackSrcPath(listFilePath.get(fileIndex))));
        
        baseYear = this.getBaseYear(listBody);

        List<String> listNewContets = new ArrayList<>();

        String line;
        String tempLine;
        boolean isInField = false;
        boolean isInCase = false;
        boolean isInAlready = false;

        for (int index = 0; index < listBody.size(); index++) {
            line = listBody.get(index);

            // コピー先の年のデータが含まれていた場合は中断(理由は下記)
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(copyYear)) {
                isInAlready = true;
                break;
            }

            // テンプレとなるBase年が含まれている場合は、追加部分となることを検討する
            this.plusList(line, isInField, isInCase);

            // fields開始
            isInField = this.getIsInField(isInField, line);

            // case開始
            isInCase = this.getIsInCase(isInCase, line);

            // import終端
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(IMPORT_POSITION)) {
                tempLine = listBody.get(index - 1);
                listNewContets.add(tempLine.replaceAll(baseYear, copyYear));
            }

            // fields終端
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(FIELD_END)) {
                isInField = false;
                listNewContets.addAll(listFields);
                listNewContets.add(""); // 空行
            }

            // case終端
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(CASE_END)) {
                isInCase = false;
                listNewContets.addAll(listCase);
                listNewContets.add(""); // 空行
            }

            // 最後に元の行を追加
            listNewContets.add(line);
        }

        // 元ファイル更新フラグが立っていない場合はこちらが勝手に決めたファイルに内容を排出する
        // どうしても差分で別ファイルに・・・という方向けの機能、というのが表向きでぶっちゃけテスト用

        // すでにコピー先の年がファイルに存在する場合は重複なので(switch文case重複エラー)ファイル更新を行わない
        if (!isInAlready) {

            // 正しく処理が行われているか、最低限のチェックをする
            this.checkFileStructure(isInField, isInCase);

            // ファイル書き出し
            String copyPath = this.getCopyFilePath(fileIndex);
            
            Files.write(Paths.get(GetCurrentResourcePath.getBackSrcPath(copyPath)), listNewContets,
                    StandardCharsets.UTF_8);
        }
    }

    /**
     * 置換の基準となる報告年を取得する
     *
     * @param listBody 解析対象ファイル内容
     * @return 報告年
     * @throws BadFactoryMethodFileStructureException ファイル構造不備(自動処理に必要なコメントが存在しない)
     */
    private String getBaseYear(final List<String> listBody) throws BadFactoryMethodFileStructureException {

        String line;
        String bodyImport;
        for (int index = 0; index < listBody.size(); index++) {
            line = listBody.get(index);

            // import部分は
            if (INDEX_OFF_NOT_CONTAIN != line.indexOf(IMPORT_POSITION)) {
                bodyImport = listBody.get(index - 1);

                Pattern pattern = Pattern.compile("[0-9]{4}");
                Matcher matcher = pattern.matcher(bodyImport);
                if (matcher.find()) {
                    return matcher.group();
                } else {
                    throw new BadFactoryMethodFileStructureException(IMPORT_POSITION + "直上のテンプレートとなる行に年数表記が存在しません");
                }
            }
        }

        throw new BadFactoryMethodFileStructureException(IMPORT_POSITION + NOT_WRITE_COMMENT);
    }

    /**
     * 追加リストへの追加処理を行う
     *
     * @param line      行
     * @param isInField 現在フィールド内にいるか該当非該当
     * @param isInCase  現在switch-case内にいるか該当非該当
     */
    private void plusList(final String line, final boolean isInField, final boolean isInCase) {
        if (INDEX_OFF_NOT_CONTAIN != line.indexOf(baseYear)) {
            if (isInField) {
                listFields.add(line.replaceAll(baseYear, copyYear));
            }
            if (isInCase) {
                listCase.add(line.replaceAll(baseYear, copyYear));
            }
        }

    }

    private boolean getIsInField(final boolean isInField, final String line) {

        if (isInField) {
            return isInField;
        }

        return INDEX_OFF_NOT_CONTAIN != line.indexOf(FIELD_START);
    }

    private boolean getIsInCase(final boolean isInCase, final String line) {

        if (isInCase) {
            return isInCase;
        }

        return INDEX_OFF_NOT_CONTAIN != line.indexOf(CASE_START);
    }

    /**
     * 自動処理に必要なコメントが不存在でないかどうか確認する
     *
     * @param isInField フィールド内該当非該当
     * @param isInCase  switch-case内該当非該当
     * @throws BadFactoryMethodFileStructureException 必要なコメント不在例外
     */
    private void checkFileStructure(final boolean isInField, final boolean isInCase)
            throws BadFactoryMethodFileStructureException {

        if (listFields.isEmpty()) {
            throw new BadFactoryMethodFileStructureException(FIELD_START + NOT_WRITE_COMMENT);
        }
        if (listCase.isEmpty()) {
            throw new BadFactoryMethodFileStructureException(CASE_START + NOT_WRITE_COMMENT);
        }

        // リスト追加フラグがONのまま・・・ リスト追加フラグがON移行のすべての行がコピーされ、かつ蓄積されたデータが新しいファイル内容に反映されていない
        if (isInField) {
            throw new BadFactoryMethodFileStructureException(FIELD_END + NOT_WRITE_COMMENT);
        }
        if (isInCase) {
            throw new BadFactoryMethodFileStructureException(CASE_END + NOT_WRITE_COMMENT);
        }
    }

    /**
     * 複写先ファイルパスを取得する
     *
     * @param filePath 解析対象パス
     * @return 複写先ファイスパス
     */
    protected String getCopyFilePath(final int index) {

        // 外部で誤ったindexを指定する、または実装ミスをすると例外が発生するが
        // 現状の実装では発生しえないのでそのまま放置する(のほうが筋が通っている気がする)

        final String filePath = listFilePath.get(index);
        // 解析対象ファイルを更新する
        if (isReadFileReflesh) {
            return filePath;
        }

        int posEnd = filePath.lastIndexOf(".");

        String copyPath = filePath.substring(0, posEnd) + "NewDiff" + filePath.substring(posEnd, filePath.length());

        // 解析ファイルがテスト環境でない場合は無理やりテスト環境に持ち込む
        if (!filePath.startsWith("test/resources")) {
            copyPath = copyPath.replaceAll("main/java/mitei/mitei/create/report/balance/politician/",
                    "test/resources/batch/");
        }

        return copyPath;
    }
}
