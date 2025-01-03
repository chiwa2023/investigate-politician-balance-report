package mitei.mitei.investigate.report.balance.politician.service.zengin_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

import mitei.mitei.investigate.report.balance.politician.constants.ZenginOrgChangeKbnConstants;
import mitei.mitei.investigate.report.balance.politician.dto.zengin_org_change.SearchZenginChangeConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * SearchZenginOrgChangeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchZenginOrgChangeServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchZenginOrgChangeService searchZenginOrgChangeService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("zengin_org_change_branch.sql")
    void testMishori() {

        SearchZenginChangeConditionCapsuleDto capsuleDto = new SearchZenginChangeConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setMishoriOnly(true);

        List<ZenginOrgChangeBranchEntity> list = searchZenginOrgChangeService.practice(capsuleDto);
        assertEquals(6, list.size(), "6件取得");

        final String textId = "指定id取得";
        
        // id順にソード
        list.sort((e1, e2) -> e1.getZenginOrgChangeBranchId() - e2.getZenginOrgChangeBranchId());
        ZenginOrgChangeBranchEntity entity0 = list.get(0);
        assertEquals(94, entity0.getZenginOrgChangeBranchId(), textId);
        ZenginOrgChangeBranchEntity entity1 = list.get(1);
        assertEquals(96, entity1.getZenginOrgChangeBranchId(), textId);
        ZenginOrgChangeBranchEntity entity2 = list.get(2);
        assertEquals(97, entity2.getZenginOrgChangeBranchId(), textId);
        ZenginOrgChangeBranchEntity entity3 = list.get(3);
        assertEquals(98, entity3.getZenginOrgChangeBranchId(), textId);
        ZenginOrgChangeBranchEntity entity4 = list.get(4);
        assertEquals(99, entity4.getZenginOrgChangeBranchId(), textId);
        ZenginOrgChangeBranchEntity entity5 = list.get(5);
        assertEquals(100, entity5.getZenginOrgChangeBranchId(), textId);

    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("zengin_org_change_branch.sql")
    void testOrgCode() {

        SearchZenginChangeConditionCapsuleDto capsuleDto = new SearchZenginChangeConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setMishoriOnly(false);
        capsuleDto.setStartDate(LocalDate.of(2023, 6, 1));
        capsuleDto.setEndDate(LocalDate.of(2025, 1, 4));
        capsuleDto.setFinancialCode("3100");
        capsuleDto.getListChangeKbn().add(ZenginOrgChangeKbnConstants.MOVE);
        capsuleDto.getListChangeKbn().add(ZenginOrgChangeKbnConstants.ADD);

        List<ZenginOrgChangeBranchEntity> list = searchZenginOrgChangeService.practice(capsuleDto);
        assertEquals(1, list.size(), "1件取得");

        final String textId = "指定id取得";
        
        // id順にソード
        list.sort((e1, e2) -> e1.getZenginOrgChangeBranchId() - e2.getZenginOrgChangeBranchId());
        ZenginOrgChangeBranchEntity entity0 = list.get(0);
        assertEquals(94, entity0.getZenginOrgChangeBranchId(), textId);

    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("zengin_org_change_branch.sql")
    void testOrgPeriod() {

        SearchZenginChangeConditionCapsuleDto capsuleDto = new SearchZenginChangeConditionCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setMishoriOnly(false);
        capsuleDto.setStartDate(LocalDate.of(2023, 6, 1));
        capsuleDto.setEndDate(LocalDate.of(2025, 1, 4));
        capsuleDto.getListChangeKbn().add(ZenginOrgChangeKbnConstants.MOVE);
        capsuleDto.getListChangeKbn().add(ZenginOrgChangeKbnConstants.DELETE);

        List<ZenginOrgChangeBranchEntity> list = searchZenginOrgChangeService.practice(capsuleDto);
        assertEquals(3, list.size(), "3件取得");

        final String textId = "指定id取得";

        // id順にソード
        list.sort((e1, e2) -> e1.getZenginOrgChangeBranchId() - e2.getZenginOrgChangeBranchId());
        ZenginOrgChangeBranchEntity entity0 = list.get(0);
        assertEquals(96, entity0.getZenginOrgChangeBranchId(), textId);

        ZenginOrgChangeBranchEntity entity1 = list.get(1);
        assertEquals(97, entity1.getZenginOrgChangeBranchId(), textId);

        ZenginOrgChangeBranchEntity entity2 = list.get(2);
        assertEquals(100, entity2.getZenginOrgChangeBranchId(), textId);

    }

}
