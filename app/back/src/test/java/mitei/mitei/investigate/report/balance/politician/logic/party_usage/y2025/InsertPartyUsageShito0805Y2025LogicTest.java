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

import jakarta.transaction.Transactional;
import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0805Dto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0805Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * InsertPartyUsageShito0805Y2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0805Y2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0805Y2025Logic insertPartyUsageShito0805Y2025Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 様式8その5Repository */
    @Autowired
    private OfferingPartyUsage0805Report2025Repository offeringPartyUsage0805Report2025Repository;

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

        /* 様式8その5 */
        CreateTestDataPartyUsageShito0805Logic createTestDataPartyUsageShito0805Logic = new CreateTestDataPartyUsageShito0805Logic();
        createTestDataPartyUsageShito0805Logic.practice(allShitoBook);
        Sheet0805Dto sheet0805 = allShitoBook.getShito0805Dto().getSheet0805Dto();
        RowShito0805Dto row0805 = sheet0805.getList().get(0);

        
        int size = insertPartyUsageShito0805Y2025Logic.practice(documentCode, documentPropertyDto, allShitoBook.getShito0805Dto(), CreateTestPrivilegeDtoUtil.pracitce());
        
        List<OfferingPartyUsage0805Report2025Entity> list0805 = offeringPartyUsage0805Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode);

        assertThat(list0805.size()).isEqualTo(size);// 取得と設定が同一

        OfferingPartyUsage0805Report2025Entity entity0805 = list0805.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity0805.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
        assertThat(entity0805.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity0805.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity0805.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity0805.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity0805.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity0805.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity0805.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity0805.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity0805.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity0805.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity0805.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity0805.getSumAmount()).isEqualTo(sheet0805.getSumAmount());
        assertThat(entity0805.getRowNo()).isEqualTo(row0805.getRowNo());
        assertThat(entity0805.getSibuName()).isEqualTo(row0805.getSibuName());
        assertThat(entity0805.getAmount()).isEqualTo(row0805.getAmount());
        assertThat(entity0805.getAccrualDate()).isEqualTo(row0805.getAccrualDate());
        assertThat(entity0805.getPurpose()).isEqualTo(row0805.getPurpose());
        assertThat(entity0805.getBikou()).isEqualTo(row0805.getBikou());
        assertThat(entity0805.getRowKbn()).isEqualTo(row0805.getRowKbn());


    }

}
