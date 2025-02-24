package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;


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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinPickupRouteResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinSearchCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * SearchUkaiKenkinRouteByPickupStageService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchUkaiKenkinRouteByPickupStageServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchUkaiKenkinRouteByPickupStageService searchUkaiKenkinRouteByPickupStageService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wktbl_route_full.sql")
    void test() throws Exception {

        UkaiKenkinSearchCapsuleDto capsuleDto = new UkaiKenkinSearchCapsuleDto();
        capsuleDto.setCheckPrivilegeDto(CreateTestPrivilegeDtoUtil.pracitce());
        capsuleDto.setCountAll(0); // 初回のため件数未取得
        capsuleDto.setLimit(4);
        capsuleDto.setPageNumber(2);

        UkaiKenkinPickupRouteResultDto resultDto = searchUkaiKenkinRouteByPickupStageService.practice(capsuleDto);

        assertEquals(19, resultDto.getCountAll(), "総件数は19件");
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "件数上限を引き継ぐ");
        assertEquals(capsuleDto.getPageNumber(), resultDto.getPageNumber(), "件数上限を引き継ぐ");

        // 階層0
        List<WkTblUkaiKenkinPickupRouteEntity> listFirst = resultDto.getListFirstRouteEntity();
        assertEquals(4, listFirst.size(), "階層0は4件");

        WkTblUkaiKenkinPickupRouteEntity entity00 = listFirst.get(0);
        assertEquals(21L, entity00.getTablleId(), "元テーブルが一致10");
        WkTblUkaiKenkinPickupRouteEntity entity01 = listFirst.get(1);
        assertEquals(22L, entity01.getTablleId(), "元テーブルが一致11");
        WkTblUkaiKenkinPickupRouteEntity entity02 = listFirst.get(2);
        assertEquals(30L, entity02.getTablleId(), "元テーブルが一致12");
        WkTblUkaiKenkinPickupRouteEntity entity03 = listFirst.get(3);
        assertEquals(31L, entity03.getTablleId(), "元テーブルが一致13");

        // 階層最終
        List<WkTblUkaiKenkinPickupRouteEntity> listLast = resultDto.getListLastRouteEntity();
        assertEquals(2, listLast.size(), "階層最終は2件");
        WkTblUkaiKenkinPickupRouteEntity entity10 = listLast.get(0);
        assertEquals(120L, entity10.getTablleId(), "元テーブルが一致10");
        WkTblUkaiKenkinPickupRouteEntity entity11 = listLast.get(1);
        assertEquals(220L, entity11.getTablleId(), "元テーブルが一致11");

        // 中間経路
        List<WkTblUkaiKenkinPickupRouteEntity> listPick = resultDto.getListPickupRouteEntity();
        assertEquals(1, listPick.size(), "中間経路は1件");
        WkTblUkaiKenkinPickupRouteEntity entity20 = listPick.get(0);
        assertEquals(121L, entity20.getTablleId(), "元テーブルが一致20");

    }

}
