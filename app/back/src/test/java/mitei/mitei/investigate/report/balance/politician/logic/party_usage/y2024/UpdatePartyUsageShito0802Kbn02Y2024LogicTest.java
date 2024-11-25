package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0802Kbn02Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0802Kbn02Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePartyUsageShito0802Kbn02Y2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsageShito0802Kbn02Y2024LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePartyUsageShito0802Kbn02Y2024Logic updatePartyUsageShito0802Kbn02Y2024Logic;

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2024Repository offeringPartyUsage0802Kbn02Report2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("offering_party_usage_0802_kbn_02_report_2024.sql")
    void testPractice() {

        Long oldDcoumentCode = 3232L;

        List<OfferingPartyUsage0802Kbn02Report2024Entity> listPre = offeringPartyUsage0802Kbn02Report2024Repository
                .findByDocumentCodeOrderByPartyUsage0802Kbn02ReportId(oldDcoumentCode);

        OfferingPartyUsage0802Kbn02Report2024Entity preEntity = listPre.get(0);

        assertThat(preEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        // 変更前の値ではUpdate項目に値は入っていません
        assertThat(preEntity.getUpdateUserId()).isEqualTo(0L);
        assertThat(preEntity.getUpdateUserCode()).isEqualTo(0);
        assertThat(preEntity.getUpdateUserName()).isEqualTo("");
        assertThat(preEntity.getUpdateTimestamp()).isEqualTo(LocalDateTime.of(1948, 7, 29, 0, 0, 0));

        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = updatePartyUsageShito0802Kbn02Y2024Logic.practice(oldDcoumentCode, checkPrivilegeDto);

        List<OfferingPartyUsage0802Kbn02Report2024Entity> listPro = offeringPartyUsage0802Kbn02Report2024Repository
                .findByDocumentCodeOrderByPartyUsage0802Kbn02ReportId(oldDcoumentCode);

        // 前後で件数は替わっていません
        assertThat(size).isEqualTo(listPre.size());
        assertThat(listPro.size()).isEqualTo(listPre.size());

        OfferingPartyUsage0802Kbn02Report2024Entity proEntity;
        for (int index = 0; index < size; index++) {
            proEntity = listPro.get(index);

            // id順に取得しているのでidは一緒です
            assertThat(proEntity.getPartyUsage0802Kbn02ReportId())
                    .isEqualTo(preEntity.getPartyUsage0802Kbn02ReportId());

            // 最新区分は履歴に変更
            assertThat(proEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

            // 変更後にはUpdate項目に値が入ります
            assertThat(proEntity.getUpdateUserId()).isEqualTo(checkPrivilegeDto.getLoginUserId());
            assertThat(proEntity.getUpdateUserCode()).isEqualTo(checkPrivilegeDto.getLoginUserCode());
            assertThat(proEntity.getUpdateUserName()).isEqualTo(checkPrivilegeDto.getLoginUserName());
            assertTrue(proEntity.getUpdateTimestamp().isAfter(LocalDateTime.now().minusMinutes(1L)),
                    "1分前より後に生成");
        }

    }

}
