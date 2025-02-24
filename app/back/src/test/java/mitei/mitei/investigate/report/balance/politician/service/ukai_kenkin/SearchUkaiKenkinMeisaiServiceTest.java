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

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinDetaillResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinSearchCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * SearchUkaiKenkinMeisaiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchUkaiKenkinMeisaiServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchUkaiKenkinMeisaiService searchUkaiKenkinMeisaiService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("../../batch/balancesheet/ukai_kenkin/configuration_wktbl_meisai.sql")
    void test() throws Exception {
        UkaiKenkinSearchCapsuleDto capsuleDto = new UkaiKenkinSearchCapsuleDto();
        capsuleDto.setCheckPrivilegeDto(CreateTestPrivilegeDtoUtil.pracitce());
        capsuleDto.setLimit(5);
        capsuleDto.setCountAll(0);
        capsuleDto.setPageNumber(2);

        UkaiKenkinDetaillResultDto resultDto = searchUkaiKenkinMeisaiService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "上限は同じ数");
        assertEquals(capsuleDto.getPageNumber(), resultDto.getOffset(), "ページ位置(検索位置も同じ)");
        assertEquals(52, resultDto.getCountAll(), "52件存在");

        List<WkTblUkaiKenkinEntity> listEntity = resultDto.getListDetailEntity();
        assertEquals(capsuleDto.getLimit(), listEntity.size(), "取得リストサイズと上限は一致");

        WkTblUkaiKenkinEntity entity00 = listEntity.get(0);
        assertEquals(32, entity00.getTablleId(), "収入テーブルが一致0");
        WkTblUkaiKenkinEntity entity01 = listEntity.get(1);
        assertEquals(33, entity01.getTablleId(), "収入テーブルが一致1");
        WkTblUkaiKenkinEntity entity02 = listEntity.get(2);
        assertEquals(34, entity02.getTablleId(), "収入テーブルが一致2");
        WkTblUkaiKenkinEntity entity03 = listEntity.get(3);
        assertEquals(35, entity03.getTablleId(), "収入テーブルが一致3");
        WkTblUkaiKenkinEntity entity04 = listEntity.get(4);
        assertEquals(36, entity04.getTablleId(), "収入テーブルが一致4");

    }

}
