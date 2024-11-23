package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import static org.assertj.core.api.Assertions.assertThat;
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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdateBalancesheetWkTbService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateBalancesheetWkTbServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdateBalancesheetWkTbService updateBalancesheetWkTbService;

    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("wk_tbl_poli_org_balancesheet_report.sql")
    void testPractice() {
        
        Long callId = 101L;
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        WkTblPoliOrgBalancesheetReportEntity entitySrc = wkTblPoliOrgBalancesheetReportRepository.findById(callId)
                .get();

        Long poliOrgId = 326L;
        Integer poliOrgCode = 320;
        String poliOrgName = "ちゃらんぽらん政治団体";

        entitySrc.setPoliticalOrganizationId(poliOrgId);
        entitySrc.setPoliticalOrganizationCode(poliOrgCode);
        entitySrc.setPoliticalOrganizationName(poliOrgName);

        // 編集
        Long newId = updateBalancesheetWkTbService.practice(entitySrc, privilegeDto);

        // 元のデータを呼びなおし
        WkTblPoliOrgBalancesheetReportEntity entitySrc2 = wkTblPoliOrgBalancesheetReportRepository.findById(callId)
                .get();

        // 編集後データを呼ぶ
        WkTblPoliOrgBalancesheetReportEntity entityCopy = wkTblPoliOrgBalancesheetReportRepository.findById(newId)
                .get();

        // 最新区分が変更されている
        assertThat(entitySrc2.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());
        assertThat(entityCopy.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 政治団体が変更されている
        assertThat(entitySrc2.getPoliticalOrganizationId()).isEqualTo(0L);
        assertThat(entityCopy.getPoliticalOrganizationId()).isEqualTo(poliOrgId);
        assertThat(entityCopy.getPoliticalOrganizationCode()).isEqualTo(poliOrgCode);
        assertThat(entityCopy.getPoliticalOrganizationName()).isEqualTo(poliOrgName);

        // 事故で呼び出せないデータを編集対象にした場合は例外で処理中断
        WkTblPoliOrgBalancesheetReportEntity entityNoInsert = new WkTblPoliOrgBalancesheetReportEntity();
        entityNoInsert.setWkTblPoliOrgBalancesheetReportId(33L);

        assertThrows(EmptyResultDataAccessException.class,
                () -> updateBalancesheetWkTbService.practice(entityNoInsert, privilegeDto));

    }

}
