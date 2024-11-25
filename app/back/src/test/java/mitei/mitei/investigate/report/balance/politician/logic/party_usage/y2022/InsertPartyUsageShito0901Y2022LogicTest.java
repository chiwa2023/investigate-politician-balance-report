package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Tag;
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
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0901Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0901Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0901Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0901Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageShito0901Y2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0901Y2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0901Y2022Logic insertPartyUsageShito0901Y2022Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 様式9(その1)Repository */
    @Autowired
    private OfferingPartyUsage0901Report2022Repository offeringPartyUsage0901Report2022Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() {

        int houkokuNen = 2022;
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

        /* 様式9 */
        CreateTestDataPartyUsageShito0901Logic createTestDataPartyUsageShito0901Logic = new CreateTestDataPartyUsageShito0901Logic();
        createTestDataPartyUsageShito0901Logic.practice(allShitoBook);
        RowShito0901Dto row090101 = allShitoBook.getShito0901Dto().getSheet0901Dto().getList().get(0);
        RowShito0901Dto row090102 = allShitoBook.getShito0901Dto().getSheet0901Dto().getList().get(1);

        int size = insertPartyUsageShito0901Y2022Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0901Dto(), CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0901Report2022Entity> list0901 = offeringPartyUsage0901Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0901ReportId(documentCode);

        assertThat(list0901.size()).isEqualTo(size);// 2行追加

        OfferingPartyUsage0901Report2022Entity entity090101 = list0901.get(0);
        OfferingPartyUsage0901Report2022Entity entity090102 = list0901.get(1);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity090101.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity090101.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity090101.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity090101.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity090101.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity090101.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity090101.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity090101.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity090101.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity090101.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity090101.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity090101.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity090101.getRowNo()).isEqualTo(row090101.getRowNo());
        assertThat(entity090101.getItemName()).isEqualTo(row090101.getItemName());
        assertThat(entity090101.getDigest()).isEqualTo(row090101.getDigest());
        assertThat(entity090101.getAmount()).isEqualTo(row090101.getAmount());
        assertThat(entity090101.getAccrualDate()).isEqualTo(row090101.getAccrualDate());
        assertThat(entity090101.getExplainText()).isEqualTo(row090101.getExplainText());

        assertThat(entity090102.getRowNo()).isEqualTo(row090102.getRowNo());
        assertThat(entity090102.getItemName()).isEqualTo(row090102.getItemName());
        assertThat(entity090102.getDigest()).isEqualTo(row090102.getDigest());
        assertThat(entity090102.getAmount()).isEqualTo(row090102.getAmount());
        assertThat(entity090102.getAccrualDate()).isEqualTo(row090102.getAccrualDate());
        assertThat(entity090102.getExplainText()).isEqualTo(row090102.getExplainText());

    }

}
