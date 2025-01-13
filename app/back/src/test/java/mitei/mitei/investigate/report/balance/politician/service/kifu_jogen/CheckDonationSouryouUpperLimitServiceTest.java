package mitei.mitei.investigate.report.balance.politician.service.kifu_jogen;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.KifuSouryouSeigenAllResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;

/**
 * CheckDonationUpperLimitService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "souryou_kisei_2022.sql", "relation_person.sql", "relation_corp.sql" })
class CheckDonationSouryouUpperLimitServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CheckDonationSouryouUpperLimitService checkDonationSouryouUpperLimitService;

    @Test
    @Tag("TableTruncate")
    void testName() throws Exception { // NOPMD

        Integer houkokuNen = 2022;
        Integer poliOrgCode = 100;
        Boolean isNameSearch = true;

        KifuSouryouSeigenAllResultDto resultDto = checkDonationSouryouUpperLimitService.practice(houkokuNen,
                poliOrgCode, isNameSearch);

        // 企業団体A枠
        List<SouryouKiseiRelationCodeDto> listKigyouParty = resultDto.getListDtoKigyouParty();

        final String sizeText = "取得サイズが想定通り";
        final String addressText = "取引相手住所が一致";
        final String nameText = "取引相手名が一致";
        final String sumText = "寄付額合計が一致";
        final String limitText = "上限額が一致";
        final String overText = "上限超え判定が一致";
        final String noResultText = "該当データがありませんでした";

        assertEquals(4, listKigyouParty.size(), sizeText);

        SouryouKiseiRelationCodeDto dto00 = listKigyouParty.get(0);
        assertEquals(0L, dto00.getRelationId(), "idはない");
        assertEquals(0, dto00.getRelationCode(), "codeもない");
        assertEquals("株式会社いろはに", dto00.getPartnerName(), nameText);
        assertEquals("宮崎県実在市", dto00.getPartnerJusho(), addressText);
        assertEquals(-1L, dto00.getLimitAmount(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);

        assertEquals(30_010L, dto00.getSum(), sumText);
        // MEMO 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto01 = listKigyouParty.get(1);
        assertEquals("株式会社ほへとち", dto01.getPartnerName(), nameText);
        assertEquals("宮崎県実在市", dto01.getPartnerJusho(), addressText);
        assertEquals(-1L, dto01.getLimitAmount(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);
        assertEquals(60_120L, dto01.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto02 = listKigyouParty.get(2);
        assertEquals("株式会社りぬるを", dto02.getPartnerName(), nameText); // NOPMD
        assertEquals("宮崎県実在市", dto02.getPartnerJusho(), addressText);
        assertEquals(-1L, dto02.getLimitAmount(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);
        assertEquals(60_470L, dto02.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto03 = listKigyouParty.get(3);
        assertEquals("株式会社りぬるを", dto03.getPartnerName(), nameText);
        assertEquals("山形県架空市", dto03.getPartnerJusho(), addressText);
        assertEquals(-1L, dto03.getLimitAmount(), limitText);
        assertEquals(false, dto03.getIsLimitOver(), overText);
        assertEquals(80_250L, dto03.getSum(), sumText);

        // 企業団体B枠の違反はありませんでした。
        List<SouryouKiseiRelationCodeDto> listKigyouOther = resultDto.getListDtoKigyouOtherOrg();
        assertEquals(1, listKigyouOther.size(), sizeText);
        SouryouKiseiRelationCodeDto dto10 = listKigyouOther.get(0);
        assertEquals(noResultText, dto10.getPartnerName(), noResultText);

        // 個人A枠
        List<SouryouKiseiRelationCodeDto> listKojinParty = resultDto.getListDtoKojinParty();

        assertEquals(4, listKojinParty.size(), sizeText);

        SouryouKiseiRelationCodeDto dto20 = listKojinParty.get(0);
        assertEquals("個人寄付太郎", dto20.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto20.getPartnerJusho(), addressText); // NOPMD
        assertEquals(0L, dto20.getRelationId(), "Idは存在しない");
        assertEquals(0, dto20.getRelationCode(), "同一識別コードは存在しない");
        assertEquals(20_000_000L, dto20.getLimitAmount(), limitText);
        assertEquals(false, dto20.getIsLimitOver(), overText);
        assertEquals(3001L, dto20.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto21 = listKojinParty.get(1);
        assertEquals("個人寄付次郎", dto21.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto21.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto21.getLimitAmount(), limitText);
        assertEquals(false, dto21.getIsLimitOver(), overText);
        assertEquals(6006L, dto21.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto22 = listKojinParty.get(2);
        assertEquals("個人寄付花子", dto22.getPartnerName(), nameText); // NOPMD
        assertEquals("和歌山県実在市", dto22.getPartnerJusho(), addressText);
        assertEquals(20_000_000L, dto22.getLimitAmount(), limitText);
        assertEquals(false, dto22.getIsLimitOver(), overText);
        assertEquals(6003L, dto22.getSum(), sumText);

        // 同姓同名(住所が異なる)
        SouryouKiseiRelationCodeDto dto23 = listKojinParty.get(3);
        assertEquals("個人寄付花子", dto23.getPartnerName(), nameText);
        assertEquals("山梨県架空市", dto23.getPartnerJusho(), addressText); // NOPMD
        assertEquals(20_000_000L, dto23.getLimitAmount(), limitText);
        assertEquals(false, dto23.getIsLimitOver(), overText);
        assertEquals(8025L, dto23.getSum(), sumText);

        // 個人B枠
        List<SouryouKiseiRelationCodeDto> listKojinOther = resultDto.getListDtoKojinOtherOrg();

        assertEquals(4, listKojinOther.size(), sizeText);

        SouryouKiseiRelationCodeDto dto30 = listKojinOther.get(0);
        assertEquals("個人寄付太郎", dto30.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto30.getPartnerJusho(), addressText);
        assertEquals(0L, dto30.getRelationId(), "Idは存在しない");
        assertEquals(0, dto30.getRelationCode(), "同一識別コードは存在しない");
        assertEquals(10_000_000L, dto30.getLimitAmount(), limitText);
        assertEquals(false, dto30.getIsLimitOver(), overText);
        assertEquals(3001L, dto30.getSum(), sumText);
        // 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto31 = listKojinOther.get(1);
        assertEquals("個人寄付次郎", dto31.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto31.getPartnerJusho(), addressText);
        assertEquals(10_000_000L, dto31.getLimitAmount(), limitText);
        assertEquals(false, dto31.getIsLimitOver(), overText);
        assertEquals(6006L, dto31.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto32 = listKojinOther.get(2);
        assertEquals("個人寄付花子", dto32.getPartnerName(), nameText);
        assertEquals("和歌山県実在市", dto32.getPartnerJusho(), addressText);
        assertEquals(10_000_000L, dto32.getLimitAmount(), limitText);
        assertEquals(false, dto32.getIsLimitOver(), overText);
        assertEquals(6003L, dto32.getSum(), sumText);

        // 同姓同名(住所が異なる)
        SouryouKiseiRelationCodeDto dto33 = listKojinOther.get(3);
        assertEquals("個人寄付花子", dto33.getPartnerName(), nameText);
        assertEquals("山梨県架空市", dto33.getPartnerJusho(), addressText);
        assertEquals(10_000_000L, dto33.getLimitAmount(), limitText);
        assertEquals(true, dto33.getIsLimitOver(), overText);
        assertEquals(200_008_025L, dto33.getSum(), sumText);

        // 政治団体区分指定なしデータ
        List<SouryouKiseiRelationCodeDto> listNotSet = resultDto.getListDtoNoset();
        assertEquals(1, listNotSet.size(), sizeText);
        SouryouKiseiRelationCodeDto dto50 = listNotSet.get(0);
        assertEquals(noResultText, dto50.getPartnerName(), noResultText);

    }

    @Test
    @Tag("TableTruncate")
    void testCode() throws Exception { // NOPMD

        Integer houkokuNen = 2022;
        Integer poliOrgCode = 100;
        Boolean isNameSearch = false;

        KifuSouryouSeigenAllResultDto resultDto = checkDonationSouryouUpperLimitService.practice(houkokuNen,
                poliOrgCode, isNameSearch);

        // 企業団体A枠
        List<SouryouKiseiRelationCodeDto> listKigyouParty = resultDto.getListDtoKigyouParty();

        final String sizeText = "取得サイズが想定通り";
        final String codeText = "同一識別が一致";
        final String idText = "idが一致";
        final String nameText = "名前が一致";
        final String addressText = "住所が一致";
        final String sumText = "寄付額合計が一致";
        final String limitText = "上限額が一致";
        final String overText = "上限超え判定が一致";
        final String noResultText = "該当データがありませんでした";

        assertEquals(4, listKigyouParty.size(), sizeText);

        SouryouKiseiRelationCodeDto dto00 = listKigyouParty.get(0);
        assertEquals(2773, dto00.getRelationId(), idText);
        assertEquals(2770, dto00.getRelationCode(), codeText);
        assertEquals("株式会社いろはに", dto00.getPartnerName(), nameText);
        assertEquals("宮崎県実在市0", dto00.getPartnerJusho(), addressText);
        assertEquals(-1L, dto00.getLimitAmount(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);

        assertEquals(30_010L, dto00.getSum(), sumText);
        // MEMO 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto01 = listKigyouParty.get(1);
        assertEquals(3815, dto01.getRelationId(), idText);
        assertEquals(3810, dto01.getRelationCode(), codeText);
        assertEquals("株式会社ほへとち", dto01.getPartnerName(), nameText);
        assertEquals("宮崎県実在市1", dto01.getPartnerJusho(), addressText);
        assertEquals(-1L, dto01.getLimitAmount(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);
        assertEquals(60_120L, dto01.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto02 = listKigyouParty.get(2);
        assertEquals(4482, dto02.getRelationId(), idText);
        assertEquals(4480, dto02.getRelationCode(), codeText);
        assertEquals("株式会社りぬるを", dto02.getPartnerName(), nameText);
        assertEquals("宮崎県実在市2", dto02.getPartnerJusho(), addressText);
        assertEquals(-1L, dto02.getLimitAmount(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);
        assertEquals(60_470L, dto02.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto03 = listKigyouParty.get(3);
        assertEquals(5159, dto03.getRelationId(), idText);
        assertEquals(5150, dto03.getRelationCode(), codeText);
        assertEquals("株式会社りぬるを", dto03.getPartnerName(), nameText);
        assertEquals("山形県架空市0", dto03.getPartnerJusho(), addressText);
        assertEquals(-1L, dto03.getLimitAmount(), limitText);
        assertEquals(false, dto03.getIsLimitOver(), overText);
        assertEquals(80_250L, dto03.getSum(), sumText);

        // 企業団体B枠の違反はありませんでした。
        List<SouryouKiseiRelationCodeDto> listKigyouOther = resultDto.getListDtoKigyouOtherOrg();
        assertEquals(1, listKigyouOther.size(), sizeText);
        SouryouKiseiRelationCodeDto dto10 = listKigyouOther.get(0);
        assertEquals(noResultText, dto10.getPartnerName(), noResultText);

        // 個人A枠
        //List<SouryouKiseiRelationCodeDto> listKojinParty = resultDto.getListDtoKojinParty();

        //assertEquals(4, listKojinParty.size(), sizeText);

        //SouryouKiseiRelationCodeDto dto20 = listKojinParty.get(0);
        //assertEquals(365, dto20.getRelationId(), idText);
        //assertEquals(360, dto20.getRelationCode(), codeText);
        //assertEquals("個人寄付　太郎", dto20.getPartnerName(), nameText);
        //assertEquals("和歌山県実在市0", dto20.getPartnerJusho(), addressText);
        //assertEquals(20_000_000L, dto20.getLimitAmount(), limitText);
        //assertEquals(false, dto20.getIsLimitOver(), overText);
        //assertEquals(3001L, dto20.getSum(), sumText);
        // MEMO 最新でないデータは除外されている

        //SouryouKiseiRelationCodeDto dto21 = listKojinParty.get(1);
        //assertEquals(481, dto21.getRelationId(), idText);
        //assertEquals(480, dto21.getRelationCode(), codeText);
        //assertEquals("個人寄付　次郎", dto21.getPartnerName(), nameText);
        //assertEquals("和歌山県実在市1", dto21.getPartnerJusho(), addressText);
        //assertEquals(20_000_000L, dto21.getLimitAmount(), limitText);
        //assertEquals(false, dto21.getIsLimitOver(), overText);
        //assertEquals(6006L, dto21.getSum(), sumText);

        //SouryouKiseiRelationCodeDto dto22 = listKojinParty.get(2);
        //assertEquals(594, dto22.getRelationId(), idText);
        //assertEquals(590, dto22.getRelationCode(), codeText);
        //assertEquals("個人寄付　花子", dto22.getPartnerName(), nameText); // NOPMD
        //assertEquals("和歌山県実在市2", dto22.getPartnerJusho(), addressText);
        //assertEquals(20_000_000L, dto22.getLimitAmount(), limitText);
        //assertEquals(false, dto22.getIsLimitOver(), overText);
        //assertEquals(6003L, dto22.getSum(), sumText);

        //SouryouKiseiRelationCodeDto dto23 = listKojinParty.get(3);
        //assertEquals(622, dto23.getRelationId(), idText);
        //assertEquals(620, dto23.getRelationCode(), codeText);
        //assertEquals("個人寄付　花子", dto23.getPartnerName(), nameText);
        //assertEquals("山梨県架空市", dto23.getPartnerJusho(), addressText);
        //assertEquals(20_000_000L, dto23.getLimitAmount(), limitText);
        //assertEquals(false, dto23.getIsLimitOver(), overText);
        //assertEquals(8025L, dto23.getSum(), sumText);

        // 個人B枠
        List<SouryouKiseiRelationCodeDto> listKojinOther = resultDto.getListDtoKojinOtherOrg();

        assertEquals(4, listKojinOther.size(), sizeText);

        SouryouKiseiRelationCodeDto dto30 = listKojinOther.get(0);
        assertEquals("個人寄付　太郎", dto30.getPartnerName(), nameText);
        assertEquals("和歌山県実在市0", dto30.getPartnerJusho(), addressText);
        assertEquals(365L, dto30.getRelationId(), idText);
        assertEquals(360, dto30.getRelationCode(), codeText);
        assertEquals(10_000_000L, dto30.getLimitAmount(), limitText);
        assertEquals(false, dto30.getIsLimitOver(), overText);
        assertEquals(3001L, dto30.getSum(), sumText);
        // 最新でないデータは除外されている

        SouryouKiseiRelationCodeDto dto31 = listKojinOther.get(1);
        assertEquals("個人寄付　次郎", dto31.getPartnerName(), nameText);
        assertEquals("和歌山県実在市1", dto31.getPartnerJusho(), addressText);
        assertEquals(481L, dto31.getRelationId(), idText);
        assertEquals(480, dto31.getRelationCode(), codeText);
        assertEquals(10_000_000L, dto31.getLimitAmount(), limitText);
        assertEquals(false, dto31.getIsLimitOver(), overText);
        assertEquals(6006L, dto31.getSum(), sumText);

        SouryouKiseiRelationCodeDto dto32 = listKojinOther.get(2);
        assertEquals("個人寄付　花子", dto32.getPartnerName(), nameText);
        assertEquals("和歌山県実在市2", dto32.getPartnerJusho(), addressText);
        assertEquals(594L, dto32.getRelationId(), idText);
        assertEquals(590, dto32.getRelationCode(), codeText);
        assertEquals(10_000_000L, dto32.getLimitAmount(), limitText);
        assertEquals(false, dto32.getIsLimitOver(), overText);
        assertEquals(6003L, dto32.getSum(), sumText);

        // 同姓同名(住所が異なる)
        SouryouKiseiRelationCodeDto dto33 = listKojinOther.get(3);
        assertEquals("個人寄付　花子", dto33.getPartnerName(), nameText);
        assertEquals("山梨県架空市", dto33.getPartnerJusho(), addressText);
        assertEquals(622L, dto33.getRelationId(), idText);
        assertEquals(620, dto33.getRelationCode(), codeText);
        assertEquals(10_000_000L, dto33.getLimitAmount(), limitText);
        assertEquals(true, dto33.getIsLimitOver(), overText);
        assertEquals(200_008_025L, dto33.getSum(), sumText);

        // 政治団体区分指定なしデータ
        List<SouryouKiseiRelationCodeDto> listNotSet = resultDto.getListDtoNoset();
        assertEquals(1, listNotSet.size(), sizeText);
        SouryouKiseiRelationCodeDto dto50 = listNotSet.get(0);
        assertEquals(noResultText, dto50.getPartnerName(), noResultText);

    }

}
