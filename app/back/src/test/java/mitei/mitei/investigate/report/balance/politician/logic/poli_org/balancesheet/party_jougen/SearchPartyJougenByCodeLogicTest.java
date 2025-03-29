package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.party_jougen;

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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * SearchPartyJougenByCodeLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPartyJougenByCodeLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchPartyJougenByCodeLogic searchPartyJougenByCodeLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("../y2022/party_jougen_2022.sql")
    void test() {

        KifuJougenConditionCapsuleDto capsuleDto0 = new KifuJougenConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto0);
        capsuleDto0.setIsNameSearch(false); // コード検索
        capsuleDto0.setPoliOrgCode(100);
        capsuleDto0.setHoukokuNen(2022);
        final int pageNum0 = 0;
        final int offset0 = 2;
        capsuleDto0.setOffset(offset0);
        capsuleDto0.setPageNum(pageNum0);

        SearchKifuJougenMeisaiBalancesheetResultDto resultDto0 = searchPartyJougenByCodeLogic.practice(capsuleDto0);

        assertEquals(9, resultDto0.getCountAll(), "全件数が一致");
        assertEquals(pageNum0, resultDto0.getPageNumber(), "ページ番号は初期値が戻る");
        List<KifuJougenTradingInfoDto> list0 = resultDto0.getListTradingGroup();
        assertEquals(offset0, list0.size(), "取得件数は取得上限");

        KifuJougenTradingInfoDto dto00 = list0.get(0);
        assertEquals(365L, dto00.getRelationId(), "取引相委Idが一致10");
        assertEquals(360, dto00.getRelationCode(), "取引相手Codeが一致10");
        assertEquals("迂回　献金太郎", dto00.getPartnerName(), "取引相手が一致10");
        assertEquals("和歌山県実在市", dto00.getPartnerAddress(), "取引相手住所が一致10");
        assertEquals(60_006L, dto00.getSumKifu(), "合計額が一致10");

        KifuJougenTradingInfoDto dto01 = list0.get(1);
        assertEquals(465L, dto01.getRelationId(), "取引相手委Idが一致11");
        assertEquals(460, dto01.getRelationCode(), "取引相手Codeが一致11");
        assertEquals("パーティ　花子", dto01.getPartnerName(), "取引相手が一致11");
        assertEquals("宮崎県実在市", dto01.getPartnerAddress(), "取引相手住所が一致11");
        assertEquals(15_002L, dto01.getSumKifu(), "合計額が一致11");
    }

}
