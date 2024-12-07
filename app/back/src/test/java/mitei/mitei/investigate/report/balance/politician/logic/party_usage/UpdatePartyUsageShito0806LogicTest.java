package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import static org.assertj.core.api.Assertions.assertThat;

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
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0806Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0806Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0806Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0806Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePartyUsageShito0806Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsageShito0806LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePartyUsageShito0806Logic updatePartyUsageShito0806Logic;

    /** 様式8その6Repository */
    @Autowired
    private OfferingPartyUsage0806Report2022Repository offeringPartyUsage0806Report2022Repository;

    /** 様式8その6Repository */
    @Autowired
    private OfferingPartyUsage0806Report2025Repository offeringPartyUsage0806Report2025Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("offering_party_usage_0806_report.sql")
    void testPractice() {

        int nendo = 2022;
        long documentCode = 1234L;

        // 2022年、2025年とも最新状態です
        OfferingPartyUsage0806Report2022Entity entity2022Pre = offeringPartyUsage0806Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0806ReportId(documentCode).get(0);
        assertThat(entity2022Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        OfferingPartyUsage0806Report2025Entity entity2025Pre = offeringPartyUsage0806Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0806ReportId(documentCode).get(0);
        assertThat(entity2025Pre.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        updatePartyUsageShito0806Logic.practice(nendo, documentCode, checkPrivilegeDto);

        // 2022年のテーブルデータは変更されました
        OfferingPartyUsage0806Report2022Entity entity2022Pro = offeringPartyUsage0806Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0806ReportId(documentCode).get(0);

        assertThat(entity2022Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

        // 2025年のテーブルデータは変更されませんでした
        OfferingPartyUsage0806Report2025Entity entity2025Pro = offeringPartyUsage0806Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0806ReportId(documentCode).get(0);
        assertThat(entity2025Pro.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());

    }

}
