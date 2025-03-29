package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.common.constants.blancesheet_report.OutcomeYoushikiKbnConstants;
import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import reactor.util.function.Tuple4;

/**
 * CreateKanrenshaSearchYoushikiKbnOutcomeLogic単体テスト
 */
class CreateKanrenshaSearchYoushikiKbnOutcomeLogicTest {

    @Test
    @Tag("TableTruncate")
    void testOutcome() {

        CreateKanrenshaSearchYoushikiKbnOutcomeLogic createKanrenshaSearchYoushikiKbnLogic = new CreateKanrenshaSearchYoushikiKbnOutcomeLogic();

        KanrenshaBalancesheetConditionCapsuleDto capsuleDto00 = new KanrenshaBalancesheetConditionCapsuleDto();
        Tuple4<List<Integer>, List<Integer>, List<Integer>, List<Integer>> tuple00 = createKanrenshaSearchYoushikiKbnLogic
                .practice(capsuleDto00);

        assertTrue(tuple00.getT1().isEmpty(), "無選択は空リスト1");
        assertTrue(tuple00.getT2().isEmpty(), "無選択は空リスト2");
        assertTrue(tuple00.getT3().isEmpty(), "無選択は空リスト3");
        assertTrue(tuple00.getT4().isEmpty(), "無選択は空リスト4");

        KanrenshaBalancesheetConditionCapsuleDto capsuleDto01 = new KanrenshaBalancesheetConditionCapsuleDto();

        // 支出光熱費該否
        capsuleDto01.setIsSearchKounetsuhi(true);

        // 支出消耗品該否
        capsuleDto01.setIsSearchShoumouhin(true);

        // 支出事務所費該否
        capsuleDto01.setIsSearchJimusho(true);

        // 支出活動費該否
        capsuleDto01.setIsSearchActivation(true);

        // 支出選挙運動費該否
        capsuleDto01.setIsSearchElection(true);

        // 支出機関誌発行費該否
        capsuleDto01.setIsSearchPaper(true);

        // 支出宣伝広告費該否
        capsuleDto01.setIsSearchComercial(true);

        // 支出パーティ費該否
        capsuleDto01.setIsSearchPartyOutcome(true);

        // 支出その他事業費該否
        capsuleDto01.setIsSearchBuissiness(true);

        // 支出調査研究費該否
        capsuleDto01.setIsSearchResearch(true);

        // 支出寄付費該否
        capsuleDto01.setIsSearchDonation(true);

        // 支出その他費用該否
        capsuleDto01.setIsSearchOtherOutcome(true);

        Tuple4<List<Integer>, List<Integer>, List<Integer>, List<Integer>> tuple01 = createKanrenshaSearchYoushikiKbnLogic
                .practice(capsuleDto01);

        List<Integer> list1 = tuple01.getT1();
        assertTrue(list1.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14), "様式区分14が含まれる");

        List<Integer> list2 = tuple01.getT2();

        // 支出光熱費該否
        assertTrue(list2.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_KOUNETSUHI), "光熱費が含まれる");

        // 支出消耗品該否
        assertTrue(list2.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_SHOUMOUHINHI), "消耗品が含まれる");

        // 支出事務所費該否
        assertTrue(list2.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_JIMUSHOHI), "事務所費が含まれる");

        List<Integer> list3 = tuple01.getT3();
        assertTrue(list3.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15), "様式区分14が含まれる");

        List<Integer> list4 = tuple01.getT4();

        // 支出活動費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SOSHIKIKATSUDOUHI), "組織活動費が含まれる");

        // 支出選挙運動費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENKYOKATSUDOUHI), "選挙運動費が含まれる");

        // 支出機関誌発行費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIKANSHIHAKKOUHI), "機関誌発行費が含まれる");

        // 支出宣伝広告費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENDENKOUKOKUHI), "宣伝広告費が含まれる");

        // 支出パーティ費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_PARTYKAISAIHI), "パーティ開催費が含まれる");

        // 支出その他事業費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAJIGYOU), "その他事業費が含まれる");

        // 支出調査研究費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_CHOUSAKENKYUHI), "調査研究費が含まれる");

        // 支出寄付費該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIFUKOUFUKIN), "寄付交付金費が含まれる");

        // 支出その他費用該否
        assertTrue(list4.contains(OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAKEIHI), "その他支出が含まれる");
    }

}
