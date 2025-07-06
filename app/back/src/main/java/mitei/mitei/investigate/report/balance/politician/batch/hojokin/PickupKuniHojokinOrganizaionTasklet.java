package mitei.mitei.investigate.report.balance.politician.batch.hojokin;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * 認定年基準で補助金取得企業・団体を抽出するTasklet
 * https://info.gbiz.go.jp/から補助金情報をダウンロードして、そのCSVから指定年の補助金受領団体を抽出する
 */
@Component
public class PickupKuniHojokinOrganizaionTasklet implements Tasklet, StepExecutionListener {

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(PickupKuniHojokinOrganizaionTasklet.class);

    /** 読み取りファイルパス */
    private Path pathRead;

    /** 書き出しファイルパス */
    private Path pathWrite;

    /** 処理年 */
    private int year;

    /** カンマ */
    private static final String COMMA = ",";

    /** 引用符 */
    private static final String QUOTE = "\"";

    /** 格納位置連名フラグ */
    private static final int POS_RENMEI_FLG = 10;

    /** 格納位置連名具体 */
    private static final int POS_RENMEI = 11;

    /** 格納位置認定日 */
    private static final int POS_NINTEI_BI = 5;
    /** 格納位置補助金名 */
    private static final int POS_HOJOKIN = 6;

    /** 格納位置法人番号 */
    private static final int POS_HOUJIN_NO = 0;
    /** 格納位置法人名 */
    private static final int POS_HOUJIN_MEI = 1;
    /** 格納位置住所 */
    private static final int POS_HOUJIN_JUUSHO = 2;

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        year = Integer.parseInt(stepExecution.getJobParameters().getString("year"));
        pathRead = Paths.get(stepExecution.getJobParameters().getString("readFilePath"));
        pathWrite = Paths.get(stepExecution.getJobParameters().getString("writeFilePath"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        String[] cell;
        String ninteiBiText;
        String yearPoint = String.valueOf(year - 1);
        String yearNext = String.valueOf(year);
        List<String> listHoujinNo = new ArrayList<>();
        String houjinNo;
        List<String> listWrite = new ArrayList<>();
        List<String> listSub = new ArrayList<>();

        final Charset charset = Charset.forName("cp932"); // UTFだとサクラエディタで読めない…
        try (Stream<String> stream = Files.lines(pathRead, charset)) {
            Iterator<String> iterator = stream.iterator();
            // 1行ずつ読み取り
            while (iterator.hasNext()) {
                String line = iterator.next();
                cell = line.split(",");
                ninteiBiText = this.removeQuote(cell[POS_NINTEI_BI]);
                // 指定年当年と前年が規制の対象の可能性
                if (ninteiBiText.startsWith(yearPoint) || ninteiBiText.startsWith(yearNext)) {
                    houjinNo = this.removeQuote(cell[0]);
                    // 基本的には1件でも受け取っていれば質的制限の候補
                    if (!listHoujinNo.contains(houjinNo)) {
                        listHoujinNo.add(houjinNo);
                        StringBuilder builderLine = new StringBuilder(); // NOPMD
                        builderLine //
                                .append(cell[POS_NINTEI_BI]).append(COMMA) // 補助金認定日
                                .append(QUOTE).append(this.removeQuote(cell[POS_HOJOKIN])).append("など").append(QUOTE)
                                .append(COMMA) // 補助金名
                                .append(cell[POS_HOUJIN_NO]).append(COMMA) // 法人番号
                                .append(cell[POS_HOUJIN_MEI]).append(COMMA) // 法人名
                                .append(cell[POS_HOUJIN_JUUSHO]) // 住所
                        ;
                        listWrite.add(builderLine.toString());
                    }

                    this.addRenmei(cell, listSub);
                }

            }
        }

        Files.write(pathWrite, listWrite, charset);
        Files.write(Paths.get("c:/temp/sublist.csv"), listSub, charset);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private String removeQuote(final String data) {

        if (data.startsWith(QUOTE)) {
            return data.substring(1, data.length() - 1);
        } else {
            return data;
        }
    }

    private void addRenmei(final String[] cell, final List<String> listSub) {
        log.info("連名発見");

        String[] cellRenmei;
        String renmeiFlg;
        String renmeiContents;

        // 連名受領の場合は展開(存在する年が限られる?)
        renmeiFlg = this.removeQuote(cell[POS_RENMEI_FLG]);
        if (renmeiFlg.indexOf("連名") != -1) {
            renmeiContents = cell[POS_RENMEI];
            if (renmeiContents.length() > 2) { // NOPMD
                cellRenmei = this.removeQuote(renmeiContents).split("、");
                for (String corp : cellRenmei) {
                    if (!listSub.contains(corp)) { // SUPPRESS CHECKSTYLE Nest
                        listSub.add(corp);
                    }
                }
            }
        }
    }

}
