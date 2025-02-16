package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.PoliOrgUkaiKenkinDetailLogic;
/**
 * 前回迂回献金明細テーブルに記載された、政治団体が受け取リ手になる迂回献金収入データを取得する、取得階層1回目のItemReader
 */
@Component
public class UkaiKenkinIncomeStage07TimesItemReader extends AbstractPagingItemReader<OfferingBalancesheetIncomeEntity> {

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 2;

    /** 収支報告書迂回献金データ取得ロジック */
    private final PoliOrgUkaiKenkinDetailLogic poliOrgUkaiKenkinDetailLogic;

    /** 操作ユーザ同一識別コード */
    private int userCode;

    /** 報告年 */
    private int houkokuNen;

    /** 検索階層 */
    private static final int STAGE = 7;

    /** 交付金検索フラグ */
    private boolean isSearchKoufukin;

    /**
     * コンストラクタ
     * フィールドからロジックを@Autowiredで取得できないので、コンストラクタで取得する
     *
     * @param poliOrgUkaiKenkinDetailLogic 前回取得階層に紐づく収支報告書収入データ
     */
    public UkaiKenkinIncomeStage07TimesItemReader(
            final @Autowired PoliOrgUkaiKenkinDetailLogic poliOrgUkaiKenkinDetailLogic) {
        super();
        this.poliOrgUkaiKenkinDetailLogic = poliOrgUkaiKenkinDetailLogic;
    }

    /**
     * read実行前に起動条件を取得する
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        // 起動条件を取得
        houkokuNen = Math.toIntExact(stepExecution.getJobParameters().getLong("houkokuNen"));
        isSearchKoufukin = Boolean.parseBoolean(stepExecution.getJobParameters().getString("isSearchKoufukin"));
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
        
        // 件数を取得
        this.setPageSize(CHUNK_SIZE);
        this.setMaxItemCount(poliOrgUkaiKenkinDetailLogic.practiceCount(userCode, houkokuNen, STAGE, isSearchKoufukin));
    }

    /**
     *　chunkサイズ分指定データを取得する
     */
    @Override
    protected void doReadPage() {
        Pageable pageable = Pageable.ofSize(CHUNK_SIZE).withPage(this.getPage());
        List<OfferingBalancesheetIncomeEntity> list = poliOrgUkaiKenkinDetailLogic.practice(userCode, houkokuNen, STAGE,
                isSearchKoufukin, pageable);
        super.results = list;
    }
}
