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

import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0805Dto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0805Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0805Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * InsertPartyUsageShito0805Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0805LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0805Logic insertPartyUsageShito0805Logic;

    /** 様式8その5Repository */
    @Autowired
    private OfferingPartyUsage0805Report2025Repository offeringPartyUsage0805Report2025Repository;


    /** 様式8その5Repository */
    @Autowired
    private OfferingPartyUsage0805Report2022Repository offeringPartyUsage0805Report2022Repository;

    @Test
    @Transactional
    void testPractice2025() {

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

        Sheet0805Dto sheet1 = new Sheet0805Dto();
        sheet1.setSumAmount(242424L); // NOPMD

        RowShito0805Dto row = new RowShito0805Dto();
        row.setRowNo(1);
        row.setSibuName("支部名称");
        row.setAmount(23456L); // NOPMD
        row.setAccrualDate("R3/11/13");
        row.setPurpose("事務所費");
        row.setBikou("備考");
        row.setRowKbn(100);

        sheet1.getList().add(row);

        Shito0805Dto shito1 = new Shito0805Dto();
        shito1.setSheet0805Dto(sheet1);

        int size = insertPartyUsageShito0805Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto, shito1,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0805Report2025Entity> list = offeringPartyUsage0805Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode);

        assertThat(size).isEqualTo(1);// リストではあるが１行しか登録していない
        assertThat(list.size()).isEqualTo(size);// 取得と設定が同一

        OfferingPartyUsage0805Report2025Entity entity1 = list.get(0);

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

        assertThat(entity1.getSumAmount()).isEqualTo(sheet1.getSumAmount());
        assertThat(entity1.getRowNo()).isEqualTo(row.getRowNo());
        assertThat(entity1.getSibuName()).isEqualTo(row.getSibuName());
        assertThat(entity1.getAmount()).isEqualTo(row.getAmount());
        assertThat(entity1.getAccrualDate()).isEqualTo(row.getAccrualDate());
        assertThat(entity1.getPurpose()).isEqualTo(row.getPurpose());
        assertThat(entity1.getBikou()).isEqualTo(row.getBikou());
        assertThat(entity1.getRowKbn()).isEqualTo(row.getRowKbn());

    }

    @Test
    @Transactional
    void testPractice2022() {

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

        Sheet0805Dto sheet1 = new Sheet0805Dto();
        sheet1.setSumAmount(242424L); // NOPMD

        RowShito0805Dto row = new RowShito0805Dto();
        row.setRowNo(1);
        row.setSibuName("支部名称");
        row.setAmount(23456L); // NOPMD
        row.setAccrualDate("R3/11/13");
        row.setPurpose("事務所費");
        row.setBikou("備考");
        row.setRowKbn(100);

        sheet1.getList().add(row);

        Shito0805Dto shito1 = new Shito0805Dto();
        shito1.setSheet0805Dto(sheet1);

        int size = insertPartyUsageShito0805Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto, shito1,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0805Report2022Entity> list = offeringPartyUsage0805Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0805ReportId(documentCode);

        assertThat(size).isEqualTo(1);// リストではあるが１行しか登録していない
        assertThat(list.size()).isEqualTo(size);// 取得と設定が同一

        OfferingPartyUsage0805Report2022Entity entity1 = list.get(0);

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

        assertThat(entity1.getSumAmount()).isEqualTo(sheet1.getSumAmount());
        assertThat(entity1.getRowNo()).isEqualTo(row.getRowNo());
        assertThat(entity1.getSibuName()).isEqualTo(row.getSibuName());
        assertThat(entity1.getAmount()).isEqualTo(row.getAmount());
        assertThat(entity1.getAccrualDate()).isEqualTo(row.getAccrualDate());
        assertThat(entity1.getPurpose()).isEqualTo(row.getPurpose());
        assertThat(entity1.getBikou()).isEqualTo(row.getBikou());
        assertThat(entity1.getRowKbn()).isEqualTo(row.getRowKbn());

    }

}
