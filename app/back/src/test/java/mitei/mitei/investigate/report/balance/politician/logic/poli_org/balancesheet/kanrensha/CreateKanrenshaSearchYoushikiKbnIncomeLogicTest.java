package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiKbn;
import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import reactor.util.function.Tuple6;

/**
 * CreateKanrenshaSearchYoushikiKbnIncomeLogic単体テスト
 */
class CreateKanrenshaSearchYoushikiKbnIncomeLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() { // NOPMD

        CreateKanrenshaSearchYoushikiKbnIncomeLogic createKanrenshaSearchYoushikiKbnLogic = new CreateKanrenshaSearchYoushikiKbnIncomeLogic();

        KanrenshaBalancesheetConditionCapsuleDto capsuleDto00 = new KanrenshaBalancesheetConditionCapsuleDto();
        Tuple6<List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>> tuple00 = createKanrenshaSearchYoushikiKbnLogic
                .practice(capsuleDto00);

        assertTrue(tuple00.getT1().isEmpty(), "無選択は空リスト1");
        assertTrue(tuple00.getT2().isEmpty(), "無選択は空リスト2");
        assertTrue(tuple00.getT3().isEmpty(), "無選択は空リスト3");
        assertTrue(tuple00.getT4().isEmpty(), "無選択は空リスト4");
        assertTrue(tuple00.getT5().isEmpty(), "無選択は空リスト5");
        assertTrue(tuple00.getT6().isEmpty(), "無選択は空リスト6");

        KanrenshaBalancesheetConditionCapsuleDto capsuleDto01 = new KanrenshaBalancesheetConditionCapsuleDto();
        // 収入パーティあっせん該否
        capsuleDto01.setIsSearchPartyMediate(true);

        Tuple6<List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>> tuple01 = createKanrenshaSearchYoushikiKbnLogic
                .practice(capsuleDto01);

        assertTrue(tuple01.getT1().contains(YoushikiKbn.PARTY_ASSEN), "パーティあっせん");
        List<Integer> list012 = tuple01.getT2();
        assertTrue(list012.contains(YoushikiEdaKbn.KOJIN), "個人対象1a");
        assertTrue(list012.contains(YoushikiEdaKbn.KIGYOU_DANTAI), "個人対象1b");
        assertTrue(list012.contains(YoushikiEdaKbn.SEIJI_DANTAI), "個人対象1c");
        assertTrue(tuple01.getT3().isEmpty(), "無選択は空リスト3");
        assertTrue(tuple01.getT4().isEmpty(), "無選択は空リスト4");
        assertTrue(tuple01.getT5().isEmpty(), "無選択は空リスト5");
        assertTrue(tuple01.getT6().isEmpty(), "無選択は空リスト6");

        KanrenshaBalancesheetConditionCapsuleDto capsuleDto02 = new KanrenshaBalancesheetConditionCapsuleDto();
        // 収入機関誌発行該否
        capsuleDto02.setIsSearchJournal(true);
        // 収入借入金該否
        capsuleDto02.setIsSearchBorrowed(true);
        // 収入交付金該否
        capsuleDto02.setIsSearchRelatedlgrants(true);
        // 収入その他該否
        capsuleDto02.setIsSearchOtherIncome(true);
        // 収入寄付個人該否
        capsuleDto02.setIsSearchDonatePerson(true);
        // 収入寄付企業団体該否
        capsuleDto02.setIsSearchDonateCorp(true);
        // 収入寄付政治団体該否
        capsuleDto02.setIsSearchDonatePoliOrg(true);
        // 収入寄付あっせん該否
        capsuleDto02.setIsSearchDonateMediate(true);
        // 収入匿名政党寄付該否
        capsuleDto02.setIsSearchAnonymus(true);
        // 収入特定パーティ該否
        capsuleDto02.setIsSearchSpecificParty(true);
        // 収入パーティ個人該否
        capsuleDto02.setIsSearchPartyPerson(true);

        // 収入パーティ企業団体該否
        capsuleDto02.setIsSearchPartyCorp(true);

        // 収入パーティ政治団体該否
        capsuleDto02.setIsSearchPartyPoliOrg(true);

        // 収入パーティあっせん該否
        capsuleDto02.setIsSearchPartyMediate(true);

        Tuple6<List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>> tuple02 = createKanrenshaSearchYoushikiKbnLogic
                .practice(capsuleDto02);

        List<Integer> list021 = tuple02.getT1();

        // 収入機関誌発行該否
        assertTrue(list021.contains(YoushikiKbn.KIKANSHI), "機関誌2");
        // 収入借入金該否
        assertTrue(list021.contains(YoushikiKbn.SHAKUNYUKIN), "借入金2");
        // 収入交付金該否
        assertTrue(list021.contains(YoushikiKbn.KOUFUKIN), "交付金2");
        // 収入その他該否
        assertTrue(list021.contains(YoushikiKbn.SHUUNYU_SONOTA), "その他2");
        // 収入寄付あっせん該否
        assertTrue(list021.contains(YoushikiKbn.DONATE_ASSEN), "寄付あっせん2");
        // 収入匿名政党寄付該否
        assertTrue(list021.contains(YoushikiKbn.DONATE_TOKUMEI), "匿名政党寄付2");
        // 収入特定パーティ該否
        assertTrue(list021.contains(YoushikiKbn.TOKUTEI_PARTY), "特定パーティ2");
        List<Integer> list022 = tuple02.getT2();
        assertTrue(list022.contains(YoushikiEdaKbn.NO_SET), "個人対象2a");
        assertTrue(list022.contains(YoushikiEdaKbn.KOJIN), "個人対象2b");
        assertTrue(list022.contains(YoushikiEdaKbn.KIGYOU_DANTAI), "個人対象2c");
        assertTrue(list022.contains(YoushikiEdaKbn.SEIJI_DANTAI), "個人対象2d");

        List<Integer> list023 = tuple02.getT3();
        assertTrue(list023.contains(YoushikiKbn.DONATE), "寄付2");

        List<Integer> list024 = tuple02.getT4();
        assertTrue(list024.contains(YoushikiEdaKbn.KOJIN), "個人対象24a");
        assertTrue(list024.contains(YoushikiEdaKbn.KIGYOU_DANTAI), "個人対象24b");
        assertTrue(list024.contains(YoushikiEdaKbn.SEIJI_DANTAI), "個人対象24c");

        List<Integer> list025 = tuple02.getT5();
        assertTrue(list025.contains(YoushikiKbn.PARTY), "パーティ2");

        List<Integer> list026 = tuple02.getT6();
        assertTrue(list026.contains(YoushikiEdaKbn.KOJIN), "個人対象26a");
        assertTrue(list026.contains(YoushikiEdaKbn.KIGYOU_DANTAI), "個人対象26b");
        assertTrue(list026.contains(YoushikiEdaKbn.SEIJI_DANTAI), "個人対象26c");
    }


}
