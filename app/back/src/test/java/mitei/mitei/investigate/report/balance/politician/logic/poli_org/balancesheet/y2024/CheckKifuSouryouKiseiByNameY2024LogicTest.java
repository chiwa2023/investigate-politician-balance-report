package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDantaiKbnListLogic;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;

/**
 * CheckKifuSouryouKiseiByNameY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckKifuSouryouKiseiByNameY2024LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CheckKifuSouryouKiseiByNameY2024Logic checkKifuSouryouKiseiByNameY2024Logic;

    /** 団体区分リスト取得Logic */
    @Autowired
    private GetDantaiKbnListLogic getDantaiKbnListLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("souryou_kisei_2024.sql")
    void testPersonalSeitou() throws Exception {

        // 政治団体区分が政党のリスト(通称A枠用)
        List<SouryouKiseiRelationCodeDto> list = checkKifuSouryouKiseiByNameY2024Logic.practice(100,
                YoushikiEdaKbn.KOJIN, getDantaiKbnListLogic.practiceParty());

        assertEquals(4, list.size(), "取得サイズが想定通り");

        final String addressText = "取引相手住所が一致";
        final String nameText = "取引相手名が一致";
        final String sumText = "寄付額合計が一致";
        final String limitText = "上限額が一致";
        final String overText = "上限超え判定が一致";

        SouryouKiseiRelationCodeDto dto00 = list.get(0);
        assertEquals("個人寄付太郎", dto00.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto00.getPartnerJusho(), addressText); // NOPMD
        assertEquals(0L, dto00.getRelationId(), "Idは存在しない");
        assertEquals(0, dto00.getRelationCode(), "同一識別コードは存在しない");
        assertEquals(20_000_000L, dto00.getLimitAmount(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);
        assertEquals(3001L, dto00.getSum(), sumText);
        // 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto01 = list.get(1);
        assertEquals("個人寄付次郎", dto01.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto01.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto01.getLimitAmount(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);
        assertEquals(6006L, dto01.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto02 = list.get(2);
        assertEquals("個人寄付花子", dto02.getPartnerName(), nameText); // NOPMD
        assertEquals("和歌山県実在市", dto02.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto02.getLimitAmount(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);
        assertEquals(6003L, dto02.getSum(), sumText);

        // 同姓同名(住所が異なる)
        SouryouKiseiRelationCodeDto dto03 = list.get(3);
        assertEquals("個人寄付花子", dto03.getPartnerName(), nameText);
        assertEquals("山梨県架空市", dto03.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto03.getLimitAmount(), limitText);
        assertEquals(false, dto03.getIsLimitOver(), overText);
        assertEquals(8025L, dto03.getSum(), sumText);

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("souryou_kisei_2024.sql")
    void testPersonalOther() throws Exception {

        // 政治団体区分がその他のリスト(通称B枠用)

        List<SouryouKiseiRelationCodeDto> list = checkKifuSouryouKiseiByNameY2024Logic.practice(100,
                YoushikiEdaKbn.KOJIN, getDantaiKbnListLogic.practiceOtherOrg());

        assertEquals(4, list.size(), "取得サイズが想定通り");

        final String addressText = "取引相手住所が一致";
        final String nameText = "取引相手名が一致";
        final String sumText = "寄付額合計が一致";
        final String limitText = "上限額が一致";
        final String overText = "上限超え判定が一致";

        SouryouKiseiRelationCodeDto dto00 = list.get(0);
        assertEquals("個人寄付太郎", dto00.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto00.getPartnerJusho(), addressText);
        assertEquals(0L, dto00.getRelationId(), "Idは存在しない");
        assertEquals(0, dto00.getRelationCode(), "同一識別コードは存在しない");
        assertEquals(10_000_000L, dto00.getLimitAmount(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);
        assertEquals(3001L, dto00.getSum(), sumText);
        // 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto01 = list.get(1);
        assertEquals("個人寄付次郎", dto01.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto01.getPartnerJusho(), addressText);
        assertEquals(10_000_000L, dto01.getLimitAmount(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);
        assertEquals(6006L, dto01.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto02 = list.get(2);
        assertEquals("個人寄付花子", dto02.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto02.getPartnerJusho(), addressText);
        assertEquals(10_000_000L, dto02.getLimitAmount(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);
        assertEquals(6003L, dto02.getSum(), sumText);

        // 同姓同名(住所が異なる)
        SouryouKiseiRelationCodeDto dto03 = list.get(3);
        assertEquals("個人寄付花子", dto03.getPartnerName(), nameText);
        assertEquals("山梨県架空市", dto03.getPartnerJusho(), addressText);
        assertEquals(10_000_000L, dto03.getLimitAmount(), limitText);
        assertEquals(true, dto03.getIsLimitOver(), overText);
        assertEquals(200_008_025L, dto03.getSum(), sumText);
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("souryou_kisei_2024.sql")
    void testCorpParty() throws Exception {

        // 政治団体区分が政党のリスト(通称A枠用)
        List<SouryouKiseiRelationCodeDto> list = checkKifuSouryouKiseiByNameY2024Logic.practice(100,
                YoushikiEdaKbn.KIGYOU_DANTAI, getDantaiKbnListLogic.practiceParty());

        assertEquals(4, list.size(), "取得サイズが想定通り");

        final String addressText = "取引相手住所が一致";
        final String nameText = "取引相手名が一致";
        final String sumText = "寄付額合計が一致";
        final String limitText = "上限額が一致";
        final String overText = "上限超え判定が一致";

        SouryouKiseiRelationCodeDto dto00 = list.get(0);
        assertEquals(0L, dto00.getRelationId(), "idはない");
        assertEquals(0, dto00.getRelationCode(), "codeもない");
        assertEquals("株式会社いろはに", dto00.getPartnerName(), nameText);
        assertEquals("宮崎県実在市", dto00.getPartnerJusho(), addressText);
        assertEquals(-1L, dto00.getLimitAmount(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);

        assertEquals(30_010L, dto00.getSum(), sumText);
        // MEMO 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto01 = list.get(1);
        assertEquals("株式会社ほへとち", dto01.getPartnerName(), nameText);
        assertEquals("宮崎県実在市", dto01.getPartnerJusho(), addressText);
        assertEquals(-1L, dto01.getLimitAmount(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);
        assertEquals(60_120L, dto01.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto02 = list.get(2);
        assertEquals("株式会社りぬるを", dto02.getPartnerName(), nameText);
        assertEquals("宮崎県実在市", dto02.getPartnerJusho(), addressText);
        assertEquals(-1L, dto02.getLimitAmount(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);
        assertEquals(60_470L, dto02.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto03 = list.get(3);
        assertEquals("株式会社りぬるを", dto03.getPartnerName(), nameText);
        assertEquals("山形県架空市", dto03.getPartnerJusho(), addressText);
         assertEquals(-1L, dto03.getLimitAmount(), limitText);
        assertEquals(false, dto03.getIsLimitOver(), overText);
        assertEquals(80_250L, dto03.getSum(), sumText);

    }

}
