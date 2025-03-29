package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;


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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.KobetsuKiseiMeisaiEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * SearchPartyJougenY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPartyJougenByCodeY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchPartyJougenByCodeY2022Logic searchPartyJougenByCodeY2022Logic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() {

        KifuJougenConditionCapsuleDto capsuleDto = new KifuJougenConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setPoliOrgCode(100);
        final int pageNum = 1;
        final int offset = 2;
        capsuleDto.setOffset(offset);
        capsuleDto.setPageNum(pageNum);

        SearchKifuJougenMeisaiBalancesheetResultDto resultDto00 = searchPartyJougenByCodeY2022Logic.practice(capsuleDto);

        assertEquals(9, resultDto00.getCountAll(), "全件数が一致");
        assertEquals(pageNum, resultDto00.getPageNumber(), "ページ番号は初期値が戻る");
        List<KifuJougenTradingInfoDto> list = resultDto00.getListTradingGroup();
        assertEquals(offset, list.size(), "取得件数は取得上限");

        KifuJougenTradingInfoDto dto0 = list.get(0);
        assertEquals(15_002L, dto0.getSumKifu(), "合計額が一致0");
        assertEquals(873_873L, dto0.getRelationId(), "取引相手委Idが一致0");
        assertEquals(873_870, dto0.getRelationCode(), "取引相手Codeが一致0");
        assertEquals("親族会社1", dto0.getPartnerName(), "取引相手が一致0");
        assertEquals("宮崎県実在市", dto0.getPartnerAddress(), "取引相手住所が一致0");
        List<KobetsuKiseiMeisaiEntity> listMeisai0 = dto0.getListTradingMeisai();
        KobetsuKiseiMeisaiEntity entity00 = listMeisai0.get(0);
        assertEquals(873_873L, entity00.getRelationId(), "Id手入力がしっかり入っている00");
        assertEquals(873_870, entity00.getRelationCode(), "code手入力がしっかり入っている00");

        KifuJougenTradingInfoDto dto1 = list.get(1);

        assertEquals(15_003L, dto1.getSumKifu(), "合計額が一致0");
        assertEquals("指摘業種組合2", dto1.getPartnerName(), "取引相手が一致0");
        assertEquals("宮崎県実在市", dto1.getPartnerAddress(), "取引相手住所が一致0");
        assertEquals(763_873L, dto1.getRelationId(), "取引相手委Idが一致1");
        assertEquals(763_870, dto1.getRelationCode(), "取引相手Codeが一致1");
        List<KobetsuKiseiMeisaiEntity> listMeisai1 = dto1.getListTradingMeisai();
        KobetsuKiseiMeisaiEntity entity01 = listMeisai1.get(0);
        assertEquals(763_873L, entity01.getRelationId(), "Id手入力がしっかり入っている01");
        assertEquals(763_870, entity01.getRelationCode(), "code手入力がしっかり入っている01");

        KifuJougenConditionCapsuleDto capsuleDto1 = new KifuJougenConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto1);
        capsuleDto1.setPoliOrgCode(100);
        final int pageNum1 = 0;
        final int offset1 = 2;
        capsuleDto1.setOffset(offset1);
        capsuleDto1.setPageNum(pageNum1);
        
        SearchKifuJougenMeisaiBalancesheetResultDto resultDto01 = searchPartyJougenByCodeY2022Logic.practice(capsuleDto1);

        assertEquals(9, resultDto01.getCountAll(), "全件数が一致");
        assertEquals(pageNum1, resultDto01.getPageNumber(), "ページ番号は初期値が戻る");
        List<KifuJougenTradingInfoDto> list11 = resultDto01.getListTradingGroup();
        assertEquals(offset1, list11.size(), "取得件数は取得上限");

        KifuJougenTradingInfoDto dto10 = list11.get(0);
        assertEquals(365L, dto10.getRelationId(), "取引相委Idが一致10");
        assertEquals(360, dto10.getRelationCode(), "取引相手Codeが一致10");
        assertEquals("迂回　献金太郎", dto10.getPartnerName(), "取引相手が一致10");
        assertEquals("和歌山県実在市", dto10.getPartnerAddress(), "取引相手住所が一致10");
        assertEquals(60_006L, dto10.getSumKifu(), "合計額が一致10");

        KifuJougenTradingInfoDto dto11 = list11.get(1);
        assertEquals(465L, dto11.getRelationId(), "取引相手委Idが一致11");
        assertEquals(460, dto11.getRelationCode(), "取引相手Codeが一致11");
        assertEquals("パーティ　花子", dto11.getPartnerName(), "取引相手が一致11");
        assertEquals("宮崎県実在市", dto11.getPartnerAddress(), "取引相手住所が一致11");
        assertEquals(15_002L, dto11.getSumKifu(), "合計額が一致11");
    }

}
