package mitei.mitei.investigate.report.balance.politician.dto.publish.logic.party_usage;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0902Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0902Dto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0902Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingPartyUsage0902Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * InsertPartyUsageShito0902Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0902LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /**　テスト対象 */
    @Autowired
    private InsertPartyUsageShito0902Logic insertPartyUsageShito0902Logic;

    /** 様式その9その2テーブルRepository */
    @Autowired
    private OfferingPartyUsage0902Report2025Repository offeringPartyUsage0902Report2025Repository;

    @Test
    @Transactional
    void testPractice() {

        Sheet0902Dto sheet1 = new Sheet0902Dto();
        sheet1.setItemName("事務所費1");
        sheet1.setDigest("適当な摘要1");

        Sheet0902Dto sheet2 = new Sheet0902Dto();
        sheet2.setItemName("事務所費2");
        sheet2.setDigest("適当な摘要2");

        Shito0902Dto shito0902Dto = new Shito0902Dto();
        shito0902Dto.getList().add(sheet1);
        shito0902Dto.getList().add(sheet2);

        // 政治団体基礎情報
        Long documentCode = 3434L;
        PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        partyUsageDocumentPoliticalPropertyDto.setNendo(2022); // 実際には表紙からコピー
        partyUsageDocumentPoliticalPropertyDto.setOfferingDate(LocalDate.of(2022, 12, 1)); // 実際には宣誓書からコピー
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationId(433L);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationCode(431);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        partyUsageDocumentPoliticalPropertyDto.setDantaiName("ちゃらん団体");
        partyUsageDocumentPoliticalPropertyDto.setDaihyouName("代表者 世間芸名");
        partyUsageDocumentPoliticalPropertyDto.setRelationKbn(223);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonIdDelegate(9898L);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonCodeDelegate(9867);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");


        int size = insertPartyUsageShito0902Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                shito0902Dto, CreateTestPrivilegeDtoUtil.pracitce());
        
        List<OfferingPartyUsage0902Report2025Entity> listSearch = offeringPartyUsage0902Report2025Repository.findByDocumentCodeOrderByPartyUsage0902ReportId(documentCode);
        
        //入力した分だけリストが取れています
        assertThat(size).isEqualTo(shito0902Dto.getList().size());
        //登録した分だけリストが取れています
        assertThat(listSearch.size()).isEqualTo(size);
        
        //2件入力したので2件取れます
        OfferingPartyUsage0902Report2025Entity entity1 = listSearch.get(0);
        OfferingPartyUsage0902Report2025Entity entity2 = listSearch.get(1);
               
        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getNendo()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getNendo());
        assertThat(entity1.getOfferingDate()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getOfferingDate());
        assertThat(entity1.getPoliticalOrganizationId())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationId());
        assertThat(entity1.getPoliticalOrganizationCode())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity1.getPoliticalOrganizationName())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationName());
        assertThat(entity1.getDantaiName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDantaiName());
        assertThat(entity1.getDaihyouName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDaihyouName());
        assertThat(entity1.getRelationKbn()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationKbn());
        assertThat(entity1.getRelationPersonIdDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity1.getRelationPersonCodeDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity1.getRelationPersonNameDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonNameDelegate());

        
        assertThat(entity1.getItemName()).isEqualTo(sheet1.getItemName());
        assertThat(entity1.getDigest()).isEqualTo(sheet1.getDigest());
        
        assertThat(entity2.getItemName()).isEqualTo(sheet2.getItemName());
        assertThat(entity2.getDigest()).isEqualTo(sheet2.getDigest());
        
    }

}
