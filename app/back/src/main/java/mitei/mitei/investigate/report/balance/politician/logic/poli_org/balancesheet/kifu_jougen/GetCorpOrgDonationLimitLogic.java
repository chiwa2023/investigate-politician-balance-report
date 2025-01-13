package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.CorpDonationLimitDantaiKbnConstants;
import mitei.mitei.investigate.report.balance.politician.repository.DonationLimitRepository;

/**
 * 企業団体の寄付上限額を取得する
 */
@Component
public class GetCorpOrgDonationLimitLogic {

    /** 企業団体寄付上限Repository */
    @Autowired
    private DonationLimitRepository donationLimitRepository;

    /**
     * 処理を行う
     *
     * @param kifuDantaiKbn 寄付団体区分(企業・労働組合または職員組合・それ以外)
     * @param amount        根拠となる値
     * @return 寄付上限額
     */
    public Long practice(final Integer kifuDantaiKbn, final Long amount) {
        final long saiteigen = 1L;
        if (amount < saiteigen) {
            throw new IllegalArgumentException("1未満の値は資本金0円、員数0人など常識では存在しない値のため許容できません:" + amount);
        }

        switch (kifuDantaiKbn) {

            // 資本基準
            case CorpDonationLimitDantaiKbnConstants.CORP:
                return donationLimitRepository.getLimitByCapital(amount);

            // 員数基準
            case CorpDonationLimitDantaiKbnConstants.UNION:
                return donationLimitRepository.getLimitByInsuu(amount);

            // 経費基準
            case CorpDonationLimitDantaiKbnConstants.OTHER:
                return donationLimitRepository.getLimitByExpense(amount);

            default:
                throw new IllegalArgumentException("Unexpected value: " + kifuDantaiKbn);
        }
    }

}
