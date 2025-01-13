package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.repository.DonationLimitRepository;
import reactor.util.function.Tuple2;

/**
 * 寄付上限リストを取得する
 */
@Component
public class GetDonationLimitListLogic {

    /** 企業団体寄付上限Repository */
    @Autowired
    private DonationLimitRepository donationLimitRepository;

    /**
     * 資本基準の上限リストを取得する
     *
     * @return 寄付上限リスト
     */
    public List<Tuple2<Long, Long>> practiceShihonkin() {
        return donationLimitRepository.findCapitalList();
    }

    /**
     * 経費基準の上限リストを取得する
     *
     * @return 寄付上限リスト
     */
    public List<Tuple2<Long, Long>> practiceKeihi() {
        return donationLimitRepository.findExpenseList();
    }

    /**
     * 員数基準の上限リストを取得する
     *
     * @return 寄付上限リスト
     */
    public List<Tuple2<Long, Long>> practiceShozokuInsuu() {
        return donationLimitRepository.findInsuList();
    }

}
