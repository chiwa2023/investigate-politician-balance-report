package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Repository;

/**
 * 収支報告書の迂回献金の政治団体データを取得する(2024)
 */
@Component
public class PoliOrgUkaiKenkinDetailY2024Logic {

    /** 収支報告書収入Repository(2024) */
    @Autowired
    private OfferingBalancesheetIncome2024Repository offeringBalancesheetIncome2024Repository;

    /**
     * ページ条件とともに迂回献金明細データ用の収入データの総件数を取得する
     *
     * @param userCode 操作者ユーザ同一識別コード
     * @param stage            階層(取得回数)
     * @param koufukinKbn 交付金区分(取得する=5,取得しない=100)
     * @return 総件数
     */
    public Integer practiceCount(final int userCode, final int stage, final Integer koufukinKbn) {

        return offeringBalancesheetIncome2024Repository.findUkaiKenkiMeisaiCount(userCode, stage, koufukinKbn);
    }

    /**
     * ページ条件とともに迂回献金明細データ用の収入データを取得する
     *
     * @param userCode 操作者ユーザ同一識別コード
     * @param stage            階層(取得回数)
     * @param koufukinKbn 交付金区分(取得する=5,取得しない=100)
     * @param pageable ページ条件
     * @return 収入データ
     */
    public List<OfferingBalancesheetIncomeEntity> practice(final int userCode, final int stage,
            final Integer koufukinKbn, final Pageable pageable) {

        return offeringBalancesheetIncome2024Repository.findUkaiKenkiMeisai(userCode, stage, koufukinKbn, pageable)
                .toList();
    }

}
