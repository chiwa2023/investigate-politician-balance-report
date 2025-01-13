package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

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
 * CheckKifuSouryouKiseiByPersonCodeY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckKifuSouryouKiseiByPersonCodeY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CheckKifuSouryouKiseiByPersonCodeY2022Logic checkKifuSouryouKiseiByPersonCodeY2022Logic;

    /** 団体区分リスト取得Logic */
    @Autowired
    private GetDantaiKbnListLogic getDantaiKbnListLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "souryou_kisei_2022.sql", "../kifu_souryou_kisei/relation_person.sql" })
    void test() throws Exception {

        // 政治団体区分が政党のリスト(通称A枠用)
        List<SouryouKiseiRelationCodeDto> list = checkKifuSouryouKiseiByPersonCodeY2022Logic.practice(100,
                getDantaiKbnListLogic.practiceParty());

        // CheckKifuSouryouKiseiByNameY2022Logicのテストと
        // あえて同じテストデータを使い、同じ回答を要求している
        // コード標準化が徹底されていれば上記のようになる
        assertEquals(4, list.size(), "取得サイズが想定通り");

        final String codeText = "同一識別が一致";
        final String idText = "idが一致";
        final String nameText = "名前が一致";
        final String addressText = "住所が一致";
        final String sumText = "寄付額合計が一致";
        final String limitText = "上限額が一致";
        final String overText = "上限超え判定が一致";

        SouryouKiseiRelationCodeDto dto00 = list.get(0);
        assertEquals(365, dto00.getRelationId(), idText);
        assertEquals(360, dto00.getRelationCode(), codeText);
        assertEquals("個人寄付　太郎", dto00.getPartnerName(), nameText);
        assertEquals("和歌山県実在市0", dto00.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto00.getLimitAmount(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);
        assertEquals(3001L, dto00.getSum(), sumText);
        // MEMO 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto01 = list.get(1);
        assertEquals(481, dto01.getRelationId(), idText);
        assertEquals(480, dto01.getRelationCode(), codeText);
        assertEquals("個人寄付　次郎", dto01.getPartnerName(), nameText);
        assertEquals("和歌山県実在市1", dto01.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto01.getLimitAmount(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);
        assertEquals(6006L, dto01.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto02 = list.get(2);
        assertEquals(594, dto02.getRelationId(), idText);
        assertEquals(590, dto02.getRelationCode(), codeText);
        assertEquals("個人寄付　花子", dto02.getPartnerName(), nameText);
        assertEquals("和歌山県実在市2", dto02.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto02.getLimitAmount(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);
        assertEquals(6003L, dto02.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto03 = list.get(3);
        assertEquals(622, dto03.getRelationId(), idText);
        assertEquals(620, dto03.getRelationCode(), codeText);
        assertEquals("個人寄付　花子", dto03.getPartnerName(), nameText);
        assertEquals("山梨県架空市", dto03.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto03.getLimitAmount(), limitText);
        assertEquals(false, dto03.getIsLimitOver(), overText);
        assertEquals(8025L, dto03.getSum(), sumText);
    }
}
