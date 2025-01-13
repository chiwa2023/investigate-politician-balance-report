package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import追加指定位置
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KobetsuKifuJougenResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.KobetsuKiseiMeisaiEntity;

/**
 * CheckKifuKobetsuJougenLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckKifuKobetsuJougenLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CheckKifuKobetsuJougenLogic checkKifuKobetsuJougenLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("y2022/kobetsu_kisei_2022.sql")
    void test() throws Exception { // NOPMD

        // テストテキスト
        final String partnerNameText = "取引相手名称";
        final String partnerAddressText = "取引相手住所";
        final String limitText = "寄付合計";
        final String overText = "上限突破";
        final String primaryText = "取得した明細のPrimaryKeyが一致";

        Integer poliOrgCode = 101;

        KobetsuKifuJougenResultDto resultAllDto = checkKifuKobetsuJougenLogic.practice(2022, poliOrgCode);

        SearchKifuJougenMeisaiBalancesheetResultDto orgDto = resultAllDto.getResultOrgDto();

        assertEquals(4, orgDto.getCountAll(), "合計4件取得");
        assertEquals(0, orgDto.getPageNumber(), "0ページで指定したのでそのまま");

        List<KifuJougenTradingInfoDto> listOrg = orgDto.getListTradingGroup();
        assertEquals(2, listOrg.size(), "2団体取得");

        KifuJougenTradingInfoDto dto00 = listOrg.get(0);
        assertEquals("いいかげん政治団体", dto00.getPartnerName(), partnerNameText);
        assertEquals("和歌山県実在市", dto00.getPartnerAddress(), partnerAddressText); // NOPMD
        assertEquals(300_000L, dto00.getSumKifu(), limitText);
        assertEquals(false, dto00.getIsLimitOver(), overText);

        List<KobetsuKiseiMeisaiEntity> listMeisai00 = dto00.getListTradingMeisai();
        assertEquals(1, listMeisai00.size(), "1明細取得");
        KobetsuKiseiMeisaiEntity entity00 = listMeisai00.get(0);
        assertEquals(3004L, entity00.getOfferingBalancesheetIncomeId(), primaryText);

        KifuJougenTradingInfoDto dto01 = listOrg.get(1);

        assertEquals("ちゃらんぽらん政治団体", dto01.getPartnerName(), partnerNameText);
        assertEquals("和歌山県実在市", dto01.getPartnerAddress(), partnerAddressText);
        assertEquals(60_050_000L, dto01.getSumKifu(), limitText);
        assertEquals(true, dto01.getIsLimitOver(), overText);
        List<KobetsuKiseiMeisaiEntity> listMeisai01 = dto01.getListTradingMeisai();
        assertEquals(3, listMeisai01.size(), "3明細取得");

        KobetsuKiseiMeisaiEntity entity10 = listMeisai01.get(0);
        assertEquals(3001L, entity10.getOfferingBalancesheetIncomeId(), primaryText);
        KobetsuKiseiMeisaiEntity entity11 = listMeisai01.get(1);
        assertEquals(3002L, entity11.getOfferingBalancesheetIncomeId(), primaryText);
        KobetsuKiseiMeisaiEntity entity12 = listMeisai01.get(2);
        assertEquals(3003L, entity12.getOfferingBalancesheetIncomeId(), primaryText);

        SearchKifuJougenMeisaiBalancesheetResultDto personDto = resultAllDto.getResultPersonalDto();

        assertEquals(5, personDto.getCountAll(), "合計4件取得");
        assertEquals(0, personDto.getPageNumber(), "0ページで指定したのでそのまま");

        List<KifuJougenTradingInfoDto> listPerson = personDto.getListTradingGroup();
        assertEquals(3, listPerson.size(), "3人取得");

        dto00 = listPerson.get(0);
        assertEquals("個人寄付太郎", dto00.getPartnerName(), partnerNameText);
        assertEquals("和歌山県実在市", dto00.getPartnerAddress(), partnerAddressText);
        assertEquals(2_499_999L, dto00.getSumKifu(), limitText);
        assertEquals(true, dto00.getIsLimitOver(), overText);

        listMeisai00 = dto00.getListTradingMeisai();
        assertEquals(3, listMeisai00.size(), "3明細取得");

        entity00 = listMeisai00.get(0);
        assertEquals(4001L, entity00.getOfferingBalancesheetIncomeId(), primaryText);
        KobetsuKiseiMeisaiEntity entity01 = listMeisai00.get(1);
        assertEquals(4002L, entity01.getOfferingBalancesheetIncomeId(), primaryText);
        KobetsuKiseiMeisaiEntity entity02 = listMeisai00.get(2);
        assertEquals(4003L, entity02.getOfferingBalancesheetIncomeId(), primaryText);

        dto01 = listPerson.get(1);

        assertEquals("個人寄付花子", dto01.getPartnerName(), partnerNameText);
        assertEquals("和歌山県実在市", dto01.getPartnerAddress(), partnerAddressText);
        assertEquals(30_020L, dto01.getSumKifu(), limitText);
        assertEquals(false, dto01.getIsLimitOver(), overText);

        listMeisai01 = dto01.getListTradingMeisai();
        assertEquals(1, listMeisai01.size(), "1明細取得");
        entity10 = listMeisai01.get(0);
        assertEquals(4004L, entity10.getOfferingBalancesheetIncomeId(), primaryText);

        KifuJougenTradingInfoDto dto02 = listPerson.get(2);

        assertEquals("個人寄付花子", dto02.getPartnerName(), partnerNameText);
        assertEquals("山梨県架空市", dto02.getPartnerAddress(), partnerAddressText);
        assertEquals(80_250L, dto02.getSumKifu(), limitText);
        assertEquals(false, dto02.getIsLimitOver(), overText);

        List<KobetsuKiseiMeisaiEntity> listMeisai02 = dto02.getListTradingMeisai();
        assertEquals(1, listMeisai02.size(), "1明細取得");
        KobetsuKiseiMeisaiEntity entity20 = listMeisai02.get(0);
        assertEquals(4005L, entity20.getOfferingBalancesheetIncomeId(), primaryText);

    }

}
