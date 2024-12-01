package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgPartyUsageReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePartyUsageWkTbService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsageWkTbServiceTest {
    // CHECKSTYLE:OFF

    /** 単体テスト */
    @Autowired
    private UpdatePartyUsageWkTbService updatePartyUsageWkTbService;

    /** 政党交付金使途報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliOrgPartyUsageReportRepository wkTblPoliOrgPartyUsageReportRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("wk_tbl_poli_org_party_usage_report.sql")
    void testPractice() {

        Long callId = 2239L;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        WkTblPoliOrgPartyUsageReportEntity entitySrc = wkTblPoliOrgPartyUsageReportRepository.findById(callId).get();

        Long poliOrgId = 326L;
        Integer poliOrgCode = 320;
        String poliOrgName = "ちゃらんぽらん政治団体";

        entitySrc.setPoliticalOrganizationId(poliOrgId);
        entitySrc.setPoliticalOrganizationCode(poliOrgCode);
        entitySrc.setPoliticalOrganizationName(poliOrgName);

        // 編集
        Long newId = updatePartyUsageWkTbService.practice(entitySrc, privilegeDto);

        // 元のデータを呼びなおし
        WkTblPoliOrgPartyUsageReportEntity entitySrc2 = wkTblPoliOrgPartyUsageReportRepository.findById(callId).get();

        // 編集後データを呼ぶ
        WkTblPoliOrgPartyUsageReportEntity entityCopy = wkTblPoliOrgPartyUsageReportRepository.findById(newId).get();

        // 最新区分が変更されている
        assertEquals(DataHistoryStatusConstants.UPDATE.value(), entitySrc2.getSaishinKbn(), "");
        assertEquals(DataHistoryStatusConstants.INSERT.value(), entityCopy.getSaishinKbn(), "");

        // 政治団体が変更されている
        assertEquals(0L, entitySrc2.getPoliticalOrganizationId(), "");
        assertEquals(poliOrgId, entityCopy.getPoliticalOrganizationId(), "");
        assertEquals(poliOrgCode, entityCopy.getPoliticalOrganizationCode(), "");
        assertEquals(poliOrgName, entityCopy.getPoliticalOrganizationName(), "");

        // 事故で呼び出せないデータを編集対象にした場合は例外で処理中断
        WkTblPoliOrgPartyUsageReportEntity entityNoInsert = new WkTblPoliOrgPartyUsageReportEntity();
        entityNoInsert.setWkTblPoliOrgPartyUsageReportId(33L);

        assertThrows(EmptyResultDataAccessException.class,
                () -> updatePartyUsageWkTbService.practice(entityNoInsert, privilegeDto));

    }

}
