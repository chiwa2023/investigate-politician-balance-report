package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

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
import mitei.mitei.common.publish.party.usage.report.dto.v5.BookHeadDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.KaikeiKijunKingakuDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0807Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0801Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.SitoUmuFlgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0801And0807Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageShito0801And0807Y2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0801And0807Y2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0801And0807Y2025Logic insertPartyUsageShito0801And0807Y2025Logic;

    /** 政党交付金使途報告書Repository */
    @Autowired
    private OfferingPartyUsage0801And0807Report2025Repository offeringPartyUsage0801And0807Report2025Repository;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() { // NOPMD
        AllShitoBook allShitoBook = new AllShitoBook();

        CreateTestDataPartyUsageShito0801And0807Logic createTestDataPartyUsageShito0801And0807Logic = new CreateTestDataPartyUsageShito0801And0807Logic();
        createTestDataPartyUsageShito0801And0807Logic.practice(2025, allShitoBook);

        // 政治団体基礎情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto.setNendo(2025); // 実際には表紙からコピー
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R7/12/1")); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        Long code = insertPartyUsageShito0801And0807Y2025Logic.practice(documentPropertyDto, allShitoBook,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0801And0807Report2025Entity> list = offeringPartyUsage0801And0807Report2025Repository
                .findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(code);

        /* codeで呼び出すと同じデータが取得できる */

        // 1件だけ取得(1件しか登録していない)
        assertThat(list.size()).isEqualTo(1);
        OfferingPartyUsage0801And0807Report2025Entity entityRecord = list.get(0);

        // コードは一致している
        assertThat(entityRecord.getPartyUsage0801And0807ReportCode()).isEqualTo(code);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entityRecord.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entityRecord.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entityRecord.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entityRecord.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entityRecord.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entityRecord.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entityRecord.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entityRecord.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entityRecord.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entityRecord.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entityRecord.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entityRecord.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        BookHeadDto head = allShitoBook.getBookHeadDto();
        assertThat(entityRecord.getVersion()).isEqualTo(head.getVersion());
        assertThat(entityRecord.getApliName()).isEqualTo(head.getApliName());
        assertThat(entityRecord.getFlgApli()).isEqualTo(head.getFlgApli());
        assertThat(entityRecord.getFlgHonbu()).isEqualTo(head.getFlgHonbu());

        SitoUmuFlgDto sitoUmuFlgDto = allShitoBook.getSitoUmuFlgDto();
        assertThat(entityRecord.getJohoUmuText()).isEqualTo(sitoUmuFlgDto.getUmuStatusText());

        KaikeiKijunKingakuDto kijunKingakuDto = allShitoBook.getKaikeiShishutuKijunDto().getKaikeiKijunKingakuDto();
        assertThat(entityRecord.getKaikeiKijunKingaku()).isEqualTo(kijunKingakuDto.getAmount());

        // 様式8その7
        Sheet0807Dto sheet = allShitoBook.getShito0807Dto().getSheet0807Dto();
        assertThat(entityRecord.getCopyRecipt()).isEqualTo(sheet.getCopyRecipt());
        assertThat(entityRecord.getAuditOption()).isEqualTo(sheet.getAuditOption());
        assertThat(entityRecord.getAuditReport()).isEqualTo(sheet.getAuditReport());
        assertThat(entityRecord.getShibuDocument()).isEqualTo(sheet.getShibuDocument());
        assertThat(entityRecord.getGoverningDocument()).isEqualTo(sheet.getGoverningDocument());
        assertThat(entityRecord.getFlgConfirm()).isEqualTo(sheet.getFlgConfirm());
        assertThat(entityRecord.getAccrualDate()).isEqualTo(sheet.getAccrualDate());

        // 様式8その1
        Shito0801Dto shito0 = allShitoBook.getShito0801Dto();
        assertThat(entityRecord.getNendo()).isEqualTo(shito0.getSheet0801Dto().getNendo());
        assertThat(entityRecord.getPartyName()).isEqualTo(shito0.getSheet0801Dto().getPartyName());
        assertThat(entityRecord.getPartyNameKana()).isEqualTo(shito0.getSheet0801Dto().getPartyNameKana());
        assertThat(entityRecord.getOfficeAddress()).isEqualTo(shito0.getSheet0801Dto().getOfficeAddress());
        assertThat(entityRecord.getDelegateName()).isEqualTo(shito0.getSheet0801Dto().getDelegateName());
        assertThat(entityRecord.getAccountManagerName()).isEqualTo(shito0.getSheet0801Dto().getAccountManagerName());
        assertThat(entityRecord.getWorker1Name()).isEqualTo(shito0.getSheet0801Dto().getWorker1Name());
        assertThat(entityRecord.getWorker1Tel()).isEqualTo(shito0.getSheet0801Dto().getWorker1Tel());
        assertThat(entityRecord.getWorker2Name()).isEqualTo(shito0.getSheet0801Dto().getWorker2Name());
        assertThat(entityRecord.getWorker2Tel()).isEqualTo(shito0.getSheet0801Dto().getWorker2Tel());
        assertThat(entityRecord.getShibuKbn()).isEqualTo(shito0.getSheet0801Dto().getShibuKbn());
        assertThat(entityRecord.getKaisanKbn()).isEqualTo(shito0.getSheet0801Dto().getKaisanKbn());
        assertThat(entityRecord.getKaisanDate()).isEqualTo(shito0.getSheet0801Dto().getKaisanDate());
        assertThat(entityRecord.getSeiriNo()).isEqualTo(shito0.getSheet0801Dto().getSeiriNo());
        assertThat(entityRecord.getUketsukeNo()).isEqualTo(shito0.getSheet0801Dto().getUketsukeNo());

    }

}
