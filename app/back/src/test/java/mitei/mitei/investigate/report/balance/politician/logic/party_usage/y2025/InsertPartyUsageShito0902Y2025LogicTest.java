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
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0902Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0902Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageShito0902Y2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0902Y2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0902Y2025Logic insertPartyUsageShito0902Y2025Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 様式その9その2テーブルRepository */
    @Autowired
    private OfferingPartyUsage0902Report2025Repository offeringPartyUsage0902Report2025Repository;

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

        /* 様式9その2 */
        CreateTestDataPartyUsageShito0902Logic createTestDataPartyUsageShito0902Logic = new CreateTestDataPartyUsageShito0902Logic();
        createTestDataPartyUsageShito0902Logic.practice(allShitoBook);
        Sheet0902Dto sheet090201 = allShitoBook.getShito0902Dto().getList().get(0);
        Sheet0902Dto sheet090202 = allShitoBook.getShito0902Dto().getList().get(1);

        int size = insertPartyUsageShito0902Y2025Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0902Dto(), CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0902Report2025Entity> listSearch = offeringPartyUsage0902Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0902ReportId(documentCode);

        // 登録した分だけリストが取れています
        assertThat(listSearch.size()).isEqualTo(size);

        // 2件入力したので2件取れます
        OfferingPartyUsage0902Report2025Entity entity090201 = listSearch.get(0);
        OfferingPartyUsage0902Report2025Entity entity090202 = listSearch.get(1);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity090201.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity090201.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity090201.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity090201.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity090201.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity090201.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity090201.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity090201.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity090201.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity090201.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity090201.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity090201.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity090201.getItemName()).isEqualTo(sheet090201.getItemName());
        assertThat(entity090201.getDigest()).isEqualTo(sheet090201.getDigest());

        assertThat(entity090202.getItemName()).isEqualTo(sheet090202.getItemName());
        assertThat(entity090202.getDigest()).isEqualTo(sheet090202.getDigest());
    }

}
