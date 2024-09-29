package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080202Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0802Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0802Kbn02Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageShito0802Kbn02Y2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0802Kbn02Y2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0802Kbn02Y2025Logic insertPartyUsageShito0802Kbn02Y2025Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2025Repository offeringPartyUsage0802Kbn02Report2025Repository;

    @Test
    @Transactional
    void testPractice() {

        int houkokuNen = 2025;
        String oathDateDtring = "R6/12/01";

        // 政治団体基礎情報
        Long documentCode = 3434L;
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto.setNendo(houkokuNen);
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(oathDateDtring));
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        AllShitoBook allShitoBook = new AllShitoBook();

        /* 様式8その2区分2 */
        CreateTestDataPartyUsageShito0802Kbn02Logic createTestDataPartyUsageShito0802Kbn02Logic = new CreateTestDataPartyUsageShito0802Kbn02Logic();
        createTestDataPartyUsageShito0802Kbn02Logic.practice(allShitoBook);
        Kbn080202Dto kbn1 = allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080202Dto();
        RowShito0802Dto row080202 = kbn1.getList().get(0);

        int size = insertPartyUsageShito0802Kbn02Y2025Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080202Dto(),
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0802Kbn02Report2025Entity> list080202 = offeringPartyUsage0802Kbn02Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0802Kbn02ReportId(documentCode);

        assertThat(list080202.size()).isEqualTo(size);// 1行しか登録していません

        OfferingPartyUsage0802Kbn02Report2025Entity entity080202 = list080202.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity080202.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity080202.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity080202.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity080202.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity080202.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity080202.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity080202.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity080202.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity080202.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity080202.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity080202.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity080202.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity080202.getTotalAmount()).isEqualTo(kbn1.getTotalAmount());
        assertThat(entity080202.getRowNo()).isEqualTo(row080202.getRowNo());
        assertThat(entity080202.getItemName()).isEqualTo(row080202.getItemName());
        assertThat(entity080202.getAccrualDate()).isEqualTo(row080202.getAccrualDate());
        assertThat(entity080202.getAmount()).isEqualTo(row080202.getAmount());

    }

}
