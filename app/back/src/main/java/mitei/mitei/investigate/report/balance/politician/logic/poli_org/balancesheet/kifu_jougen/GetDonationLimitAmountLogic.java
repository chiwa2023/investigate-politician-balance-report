package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen;

import java.util.List;

import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.PoliticalOrganizationKbnConstants;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;

/**
 * 寄付上限額を取得する
 * 制限上限金額だけがくるくる替わることが考えずらい
 * (登録すべきテーブル構成すべてが変更することを想定しているので)固定値で実装している
 * 実際に金額が変更されるケースが発生してからDB利用を考える
 */
@Component
public class GetDonationLimitAmountLogic {

    /**
     * 処理を行う
     *
     * @param youshikiEdaKbn 様式枝区分
     * @param listDantaiKbn 団体区分リスト
     * @return 上限金額
     */
    public Long practice(final Integer youshikiEdaKbn, final List<String> listDantaiKbn) {

        if (YoushikiEdaKbn.KOJIN == youshikiEdaKbn) {

            if (listDantaiKbn.contains(PoliticalOrganizationKbnConstants.PARTY)) {
                // 寄付者が個人かつ政党が含まれる政党リストの場合、総量は2000万円
                return 20_000_000L; // SUPPRESS CHECKSTYLE MagicNumber
            } else {
                // 寄付者が個人かつ政党が含まれず、そのた政治団体リストの場合、総量は1000万円
                return 10_000_000L; // SUPPRESS CHECKSTYLE MagicNumber
            }

        } else {
            if (listDantaiKbn.contains(PoliticalOrganizationKbnConstants.PARTY)) {
                // 寄付者が個人かつ政党が含まれる政党リストの場合、総量は資本金、人員などに依存するので、
                // 別リストへの依存を促す計算不能な値
                return -1L;

            } else {
                // 政党が含まれず、そのた政治団体リストの場合、総量は0円(全面禁止)
                return 0L;
            }
        }
    }
}
