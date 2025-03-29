package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.OutcomeYoushikiKbnConstants;
import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuples;

/**
 * 様式区分検索条件から、検索の引数にできるリストを作成する(支出)
 */
@Component
public class CreateKanrenshaSearchYoushikiKbnOutcomeLogic {


    /**
     * 支出テーブル用の引数作成を実行する
     *
     * @param capsuleDto 検索条件
     * @return リスト群
     */
    public Tuple4<List<Integer>, List<Integer>, List<Integer>, List<Integer>> practice(
            final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {

        List<Integer> listEda14 = this.convertOutcomeList1(capsuleDto);
        List<Integer> listEda15 = this.convertOutcomeList3(capsuleDto);

        return Tuples.of(this.convertOutcomeList2(listEda14), listEda14, this.convertOutcomeList4(listEda15),
                listEda15);
    }

    private List<Integer> convertOutcomeList1(final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {
        List<Integer> listEda14 = new ArrayList<>();

        // 支出光熱費該否
        if (capsuleDto.getIsSearchKounetsuhi()) {
            listEda14.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_KOUNETSUHI);
        }

        // 支出消耗品該否
        if (capsuleDto.getIsSearchShoumouhin()) {
            listEda14.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_SHOUMOUHINHI);
        }

        // 支出事務所費該否
        if (capsuleDto.getIsSearchJimusho()) {
            listEda14.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_JIMUSHOHI);
        }

        return listEda14;
    }

    private List<Integer> convertOutcomeList2(final List<Integer> listEda14) {
        // 何か1件でも有効になっているならば区分そのものも検索対象にする
        List<Integer> listYoushiki14 = new ArrayList<>();
        if (!listEda14.isEmpty()) {
            listYoushiki14.add(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14);
        }

        return listYoushiki14;
    }

    private List<Integer> convertOutcomeList3( // SUPPRESS CHECKSTYLE NPathComplexity NOPMD
            final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {
        List<Integer> listEda15 = new ArrayList<>();

        // 支出活動費該否
        if (capsuleDto.getIsSearchActivation()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SOSHIKIKATSUDOUHI);
        }

        // 支出選挙運動費該否
        if (capsuleDto.getIsSearchElection()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENKYOKATSUDOUHI);
        }

        // 支出機関誌発行費該否
        if (capsuleDto.getIsSearchPaper()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIKANSHIHAKKOUHI);
        }

        // 支出宣伝広告費該否
        if (capsuleDto.getIsSearchComercial()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENDENKOUKOKUHI);
        }

        // 支出パーティ費該否
        if (capsuleDto.getIsSearchPartyOutcome()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_PARTYKAISAIHI);
        }

        // 支出その他事業費該否
        if (capsuleDto.getIsSearchBuissiness()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAJIGYOU);
        }

        // 支出調査研究費該否
        if (capsuleDto.getIsSearchResearch()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_CHOUSAKENKYUHI);
        }

        // 支出寄付費該否
        if (capsuleDto.getIsSearchDonation()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIFUKOUFUKIN);
        }

        // 支出その他費用該否
        if (capsuleDto.getIsSearchOtherOutcome()) {
            listEda15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAKEIHI);
        }

        return listEda15;
    }

    private List<Integer> convertOutcomeList4(final List<Integer> listEda15) {

        List<Integer> listYoushiki15 = new ArrayList<>();

        // 何か1件でも有効になっているならば区分そのものも検索対象にする
        if (!listEda15.isEmpty()) {
            listYoushiki15.add(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15);
        }
        return listYoushiki15;
    }


}
