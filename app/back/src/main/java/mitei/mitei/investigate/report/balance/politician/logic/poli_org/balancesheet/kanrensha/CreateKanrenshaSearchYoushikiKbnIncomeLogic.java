package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiKbn;
import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import reactor.util.function.Tuple6;
import reactor.util.function.Tuples;

/**
 * 様式区分検索条件から、検索の引数にできるリストを作成する(収入)
 */
@Component
public class CreateKanrenshaSearchYoushikiKbnIncomeLogic {


    /**
     * 収入テーブル用の引数作成を実行する
     *
     * @param capsuleDto 検索条件
     * @return リスト群
     */
    public Tuple6<List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>> practice(
            final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {

        // 寄付とパーティの以外の様式区分
        List<Integer> listYoushiki = this.convertIncomeList1(capsuleDto);

        // 寄付とパーティの枝項目
        List<Integer> listKifuEda = this.convertIncomeList4(capsuleDto);
        List<Integer> listPartyEda = this.convertIncomeList6(capsuleDto);

        return Tuples.of(listYoushiki, this.convertIncomeList2(listYoushiki), this.convertIncomeList3(listKifuEda),
                listKifuEda, this.convertIncomeList5(listPartyEda), listPartyEda);
    }
    private List<Integer> convertIncomeList1( // SUPPRESS CHECKSTYLE NPathComplexity NOPMD
            final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {

        List<Integer> listYoushiki = new ArrayList<>();

        // 収入機関誌発行該否
        if (capsuleDto.getIsSearchJournal()) {
            listYoushiki.add(YoushikiKbn.KIKANSHI);
        }

        // 収入借入金該否
        if (capsuleDto.getIsSearchBorrowed()) {
            listYoushiki.add(YoushikiKbn.SHAKUNYUKIN);
        }

        // 収入交付金該否
        if (capsuleDto.getIsSearchRelatedlgrants()) {
            listYoushiki.add(YoushikiKbn.KOUFUKIN);
        }

        // 収入その他該否
        if (capsuleDto.getIsSearchOtherIncome()) {
            listYoushiki.add(YoushikiKbn.SHUUNYU_SONOTA);
        }

        // 収入匿名政党寄付該否
        if (capsuleDto.getIsSearchAnonymus()) {
            listYoushiki.add(YoushikiKbn.DONATE_TOKUMEI);
        }

        // 収入特定パーティ該否
        if (capsuleDto.getIsSearchSpecificParty()) {
            listYoushiki.add(YoushikiKbn.TOKUTEI_PARTY);
        }

        // 収入寄付あっせん該否
        if (capsuleDto.getIsSearchDonateMediate()) {
            listYoushiki.add(YoushikiKbn.DONATE_ASSEN);
        }

        // 収入パーティあっせん該否
        if (capsuleDto.getIsSearchPartyMediate()) {
            listYoushiki.add(YoushikiKbn.PARTY_ASSEN);
        }

        return listYoushiki;
    }

    private List<Integer> convertIncomeList2(final List<Integer> listYoushiki) {
        List<Integer> listEdaKoumoku = new ArrayList<>();

        // 上記様式がONの場合は枝区分項目に0必須
        if (!listYoushiki.isEmpty()) {
            listEdaKoumoku.add(YoushikiEdaKbn.NO_SET);
        }

        // あっせんを検索する場合は枝区分はすべて必要
        if (listYoushiki.contains(YoushikiKbn.DONATE_ASSEN) || listYoushiki.contains(YoushikiKbn.PARTY_ASSEN)) {
            listEdaKoumoku.add(YoushikiEdaKbn.KOJIN);
            listEdaKoumoku.add(YoushikiEdaKbn.KIGYOU_DANTAI);
            listEdaKoumoku.add(YoushikiEdaKbn.SEIJI_DANTAI);
        }

        return listEdaKoumoku;
    }

    private List<Integer> convertIncomeList3(final List<Integer> listKifuEda) {

        List<Integer> listKifuKbn = new ArrayList<>();
        if (!listKifuEda.isEmpty()) {
            listKifuKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        }

        return listKifuKbn;
    }

    private List<Integer> convertIncomeList4(final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {
        List<Integer> listKifuEda = new ArrayList<>();

        // 収入寄付個人該否
        if (capsuleDto.getIsSearchDonatePerson()) {
            listKifuEda.add(YoushikiEdaKbn.KOJIN);
        }

        // 収入寄付企業団体該否
        if (capsuleDto.getIsSearchDonateCorp()) {
            listKifuEda.add(YoushikiEdaKbn.KIGYOU_DANTAI);
        }

        // 収入寄付政治団体該否
        if (capsuleDto.getIsSearchDonatePoliOrg()) {
            listKifuEda.add(YoushikiEdaKbn.SEIJI_DANTAI);
        }

        return listKifuEda;
    }

    private List<Integer> convertIncomeList5(final List<Integer> listPartyEda) {
        List<Integer> listPartyKbn = new ArrayList<>();
        if (!listPartyEda.isEmpty()) {
            listPartyKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        }

        return listPartyKbn;
    }

    private List<Integer> convertIncomeList6(final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {
        List<Integer> listPartyEda = new ArrayList<>();

        // 収入パーティ個人該否
        if (capsuleDto.getIsSearchPartyPerson()) {
            listPartyEda.add(YoushikiEdaKbn.KOJIN);
        }

        // 収入パーティ企業団体該否
        if (capsuleDto.getIsSearchPartyCorp()) {
            listPartyEda.add(YoushikiEdaKbn.KIGYOU_DANTAI);
        }

        // 収入パーティ政治団体該否
        if (capsuleDto.getIsSearchPartyPoliOrg()) {
            listPartyEda.add(YoushikiEdaKbn.SEIJI_DANTAI);
        }
        return listPartyEda;
    }



}
