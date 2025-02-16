package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiKbn;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.PoliOrgUkaiKenkinDetailY2022Logic;

/**
 * 収支報告書収入データから迂回献金明細用データを取得するファクトリメソッドLogic
 */
@Component
public class PoliOrgUkaiKenkinDetailLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private PoliOrgUkaiKenkinDetailY2022Logic poliOrgUkaiKenkinDetailY2022Logic;

    /**
     * 件数を取得する
     *
     * @param userCode 操作者同一識別コード
     * @param houkokuNen 報告年
     * @param stage 取得階層
     * @param isSearchKoufukin 交付金検索有無フラグ
     * @return 総件数
     */
    public Integer practiceCount(final int userCode, final int houkokuNen, final int stage,
            final boolean isSearchKoufukin) {

        switch (houkokuNen) {
            // 2022年
            case YEAR_2022:
                return poliOrgUkaiKenkinDetailY2022Logic.practiceCount(userCode, stage,
                        this.getSearchKbn(isSearchKoufukin));

            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

    }

    /**
     * ひとつ前の階層迂回献金データから収支報告書収入データを取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @param houkokuNen 報告年
     * @param stage 取得階層
     * @param isSearchKoufukin 交付金検索有無
     * @param pageable ページング条件
     * @return 収支報告書輸入データ
     */
    public List<OfferingBalancesheetIncomeEntity> practice(final int userCode, final int houkokuNen, final int stage,
            final boolean isSearchKoufukin, final Pageable pageable) {

        switch (houkokuNen) {

            // 2022年
            case YEAR_2022:
                return poliOrgUkaiKenkinDetailY2022Logic.practice(userCode, stage, this.getSearchKbn(isSearchKoufukin),
                        pageable);

            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

    }

    // 交付金検索条件に合致した様式区分を返却する
    private Integer getSearchKbn(final boolean isSearchKoufukin) {

        if (isSearchKoufukin) {
            return YoushikiKbn.KOUFUKIN;
        } else {
            return 100; // SUPPRESS CHECKSTYLE MagicNumber // 検索条件を無効化する値
        }
    }

}
