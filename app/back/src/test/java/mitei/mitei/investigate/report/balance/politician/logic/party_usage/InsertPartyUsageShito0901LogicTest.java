package mitei.mitei.investigate.report.balance.politician.logic.party_usage;


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

import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0901Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0901Dto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0901Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0901Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0901Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0901Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * InsertPartyUsageShito0901Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0901LogicTest {
    // CHECKSTYLE:OFF MagicNumber
    
    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0901Logic insertPartyUsageShito0901Logic;
    
    /** 様式9(その1)Repository */
    @Autowired
    private OfferingPartyUsage0901Report2025Repository offeringPartyUsage0901Report2025Repository;

    /** 様式9(その1)Repository */
    @Autowired
    private OfferingPartyUsage0901Report2022Repository offeringPartyUsage0901Report2022Repository;

    @Test
    @Transactional
    void testPractice2025() { // NOPMD
        
        // 政治団体基礎情報
        Long documentCode = 3434L;
        PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        partyUsageDocumentPoliticalPropertyDto.setNendo(2025); // 実際には表紙からコピー
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
        
        Shito0901Dto shito0901Dto = new Shito0901Dto();

        List<RowShito0901Dto> listRow = shito0901Dto.getSheet0901Dto().getList();
        
        RowShito0901Dto row1 = new RowShito0901Dto();

        row1.setRowNo(7);
        row1.setItemName("備品・消耗品費"); // NOPMD
        row1.setDigest("aaaaa");
        row1.setAmount(20000L); // NOPMD
        row1.setAccrualDate("R4/12/1");
        row1.setExplainText("相手が〇社だった");

        RowShito0901Dto row2 = new RowShito0901Dto();

        row2.setRowNo(9);
        row2.setItemName("備品・消耗品費"); // NOPMD
        row2.setDigest("bbbbb");
        row2.setAmount(30000L); // NOPMD
        row2.setAccrualDate("R5/12/1");
        row2.setExplainText("相手が反〇だった");
        
        listRow.add(row1);
        listRow.add(row2);
        
        int size = insertPartyUsageShito0901Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto, shito0901Dto, CreateTestPrivilegeDtoUtil.pracitce());
        
        List<OfferingPartyUsage0901Report2025Entity> list = offeringPartyUsage0901Report2025Repository.findByDocumentCodeOrderByPartyUsage0901ReportId(documentCode);

        assertThat(size).isEqualTo(2);//2行追加
        assertThat(list.size()).isEqualTo(size); //登録データ数と同じ数だけ取得 
        
        OfferingPartyUsage0901Report2025Entity entity1 = list.get(0);
        OfferingPartyUsage0901Report2025Entity entity2 = list.get(1);
        
        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
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

        assertThat(entity1.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity1.getItemName()).isEqualTo(row1.getItemName());
        assertThat(entity1.getDigest()).isEqualTo(row1.getDigest());
        assertThat(entity1.getAmount()).isEqualTo(row1.getAmount());
        assertThat(entity1.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity1.getExplainText()).isEqualTo(row1.getExplainText());
        
        assertThat(entity2.getRowNo()).isEqualTo(row2.getRowNo());
        assertThat(entity2.getItemName()).isEqualTo(row2.getItemName());
        assertThat(entity2.getDigest()).isEqualTo(row2.getDigest());
        assertThat(entity2.getAmount()).isEqualTo(row2.getAmount());
        assertThat(entity2.getAccrualDate()).isEqualTo(row2.getAccrualDate());
        assertThat(entity2.getExplainText()).isEqualTo(row2.getExplainText());
        
    }

    
    @Test
    @Transactional
    void testPractice2022() { // NOPMD
        
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
        
        Shito0901Dto shito0901Dto = new Shito0901Dto();

        List<RowShito0901Dto> listRow = shito0901Dto.getSheet0901Dto().getList();
        
        RowShito0901Dto row1 = new RowShito0901Dto();

        row1.setRowNo(7);
        row1.setItemName("備品・消耗品費");
        row1.setDigest("aaaaa");
        row1.setAmount(20000L); // NOPMD
        row1.setAccrualDate("R4/12/1");
        row1.setExplainText("相手が〇社だった");

        RowShito0901Dto row2 = new RowShito0901Dto();

        row2.setRowNo(9);
        row2.setItemName("備品・消耗品費");
        row2.setDigest("bbbbb");
        row2.setAmount(30000L); // NOPMD
        row2.setAccrualDate("R5/12/1");
        row2.setExplainText("相手が反〇だった");
        
        listRow.add(row1);
        listRow.add(row2);
        
        int size = insertPartyUsageShito0901Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto, shito0901Dto, CreateTestPrivilegeDtoUtil.pracitce());
        
        List<OfferingPartyUsage0901Report2022Entity> list = offeringPartyUsage0901Report2022Repository.findByDocumentCodeOrderByPartyUsage0901ReportId(documentCode);

        assertThat(size).isEqualTo(2);//2行追加
        assertThat(list.size()).isEqualTo(size); //登録データ数と同じ数だけ取得 
        
        OfferingPartyUsage0901Report2022Entity entity1 = list.get(0);
        OfferingPartyUsage0901Report2022Entity entity2 = list.get(1);
        
        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
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

        assertThat(entity1.getRowNo()).isEqualTo(row1.getRowNo());
        assertThat(entity1.getItemName()).isEqualTo(row1.getItemName());
        assertThat(entity1.getDigest()).isEqualTo(row1.getDigest());
        assertThat(entity1.getAmount()).isEqualTo(row1.getAmount());
        assertThat(entity1.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity1.getExplainText()).isEqualTo(row1.getExplainText());
        
        assertThat(entity2.getRowNo()).isEqualTo(row2.getRowNo());
        assertThat(entity2.getItemName()).isEqualTo(row2.getItemName());
        assertThat(entity2.getDigest()).isEqualTo(row2.getDigest());
        assertThat(entity2.getAmount()).isEqualTo(row2.getAmount());
        assertThat(entity2.getAccrualDate()).isEqualTo(row2.getAccrualDate());
        assertThat(entity2.getExplainText()).isEqualTo(row2.getExplainText());
        
    }

}
