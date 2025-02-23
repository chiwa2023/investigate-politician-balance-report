package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

/**
 * CheckKifuSouryouKiseiByCorpCodeY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckKifuSouryouKiseiByCorpCodeY2024LogicTest {

    /** テスト対象 */
    @Autowired
    private CheckKifuSouryouKiseiByCorpCodeY2024Logic checkKifuSouryouKiseiByCorpCodeY2024Logic;

    /** 団体区分リスト取得Logic */
    @Autowired
    private GetDantaiKbnListLogic getDantaiKbnListLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "souryou_kisei_2024.sql", "../kifu_souryou_kisei/relation_corp.sql" })
    void test() throws Exception {
        // CHECKSTYLE:OFF

        // 政治団体区分が政党のリスト(通称A枠用)
        List<SouryouKiseiRelationCodeDto> list = checkKifuSouryouKiseiByCorpCodeY2024Logic.practice(100,
                getDantaiKbnListLogic.practiceParty());

        // CheckKifuSouryouKiseiByNameY2024Logicのテストと
        // あえて同じテストデータを使い、同じ回答を要求している
        // コード標準化が徹底されていれば上記のようになる
        assertEquals(4, list.size(), "取得サイズが想定通り");

        final String codeText = "同一識別が一致";
        final String idText = "idが一致";
        final String nameText = "名前が一致";
        final String addressText = "住所が一致";
        final String sumText = "寄付額合計が一致";
        // final String limitText = "上限額が一致";
        final String overText = "上限超え判定が一致";

        SouryouKiseiRelationCodeDto dto00 = list.get(0);
        assertEquals(2773, dto00.getRelationId(), idText);
        assertEquals(2770, dto00.getRelationCode(), codeText);
        assertEquals("株式会社いろはに", dto00.getPartnerName(), nameText);
        assertEquals("宮崎県実在市0", dto00.getPartnerJusho(), addressText);
        // assertEquals(20_000_000L, dto00.getLimitAmount(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);

        assertEquals(30_010L, dto00.getSum(), sumText);
        // MEMO 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto01 = list.get(1);
        assertEquals(3815, dto01.getRelationId(), idText);
        assertEquals(3810, dto01.getRelationCode(), codeText);
        assertEquals("株式会社ほへとち", dto01.getPartnerName(), nameText);
        assertEquals("宮崎県実在市1", dto01.getPartnerJusho(), addressText);
        // assertEquals(20_000_000L, dto01.getLimitAmount(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);
        assertEquals(60_120L, dto01.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto02 = list.get(2);
        assertEquals(4482, dto02.getRelationId(), idText);
        assertEquals(4480, dto02.getRelationCode(), codeText);
        assertEquals("株式会社りぬるを", dto02.getPartnerName(), nameText);
        assertEquals("宮崎県実在市2", dto02.getPartnerJusho(), addressText);
        // assertEquals(20_000_000L, dto02.getLimitAmount(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);
        assertEquals(60_470L, dto02.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto03 = list.get(3);
        assertEquals(5159, dto03.getRelationId(), idText);
        assertEquals(5150, dto03.getRelationCode(), codeText);
        assertEquals("株式会社りぬるを", dto03.getPartnerName(), nameText);
        assertEquals("山形県架空市0", dto03.getPartnerJusho(), addressText);
        // assertEquals(20_000_000L, dto03.getLimitAmount(), limitText);
        assertEquals(false, dto03.getIsLimitOver(), overText);
        assertEquals(80_250L, dto03.getSum(), sumText);

    }

}
