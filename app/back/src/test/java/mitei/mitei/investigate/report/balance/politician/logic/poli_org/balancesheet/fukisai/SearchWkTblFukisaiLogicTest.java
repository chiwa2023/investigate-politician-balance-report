package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.fukisai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.SearchFukisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;

/**
 * SearchWkTblFukisaiLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchWkTblFukisaiLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchWkTblFukisaiLogic searchWkTblFukisaiLogic;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_fukisai_balancesheet.sql")
    void test()throws Exception {
        Integer userCode = 970;
        Integer pageNumber = 0;
        
        SearchFukisaiBalancesheetResultDto resultDto = searchWkTblFukisaiLogic.practice(userCode,0,pageNumber);
        
        assertEquals(3, resultDto.getCountAll(),"取引相手は3件");
        assertEquals(0, resultDto.getPageNumber(),"指定したページがそのまま返る");
        
        List<FukisaiTradingInfoDto> listGroup = resultDto.getListTradingGroup();
        assertEquals(3, listGroup.size(),"3件取得");
        
        FukisaiTradingInfoDto dto00 = listGroup.get(0);
        assertEquals("ホリエモン新党(登録)", dto00.getPoliticalOrgName(),"団体名");
        assertEquals("のんびり党新宿", dto00.getPartnerName(),"取引相手名");
        assertEquals(true, dto00.getIsKingakuDiffer(),"収入・支出金額相違");

        List<WkTblFukisaiBalancesheetEntity> listMeisai1 = dto00.getListMeisai();
        assertEquals(2, listMeisai1.size(),"2件取得");
        WkTblFukisaiBalancesheetEntity entity1 = listMeisai1.get(0);       
        assertEquals(7, entity1.getYoushikiKbn(),"収入データ(様式区分<13)");
        WkTblFukisaiBalancesheetEntity entity2 = listMeisai1.get(1);       
        assertEquals(15, entity2.getYoushikiKbn(),"収入データ(様式区分>13)");
        
        FukisaiTradingInfoDto dto01 = listGroup.get(1);
        assertEquals("のんびり党池袋", dto01.getPartnerName(),"取引相手名");
        assertEquals(true, dto01.getIsKingakuDiffer(),"収入・支出金額相違");
        
        FukisaiTradingInfoDto dto02 = listGroup.get(2);
        assertEquals("のんびり党巣鴨", dto02.getPartnerName(),"取引相手名");
        assertEquals(true, dto02.getIsKingakuDiffer(),"収入・支出金額相違");
    }

}
