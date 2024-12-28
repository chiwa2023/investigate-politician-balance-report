package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

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

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0802WithdrawalItemsByTransferDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080200WithdrawalItemsByTransferDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetWithdrawal0802Transfer2024Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetWithdrawal0802Transfer2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetWithdrawal0802Transfer2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetWithdrawal0802Transfer2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalOrganization0802Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganization0802LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganization0802Logic insertPoliticalOrganization0802Logic;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetWithdrawal0802Transfer2025Repository offeringBalancesheetWithdrawal0802Transfer2025Repository;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetWithdrawal0802Transfer2024Repository offeringBalancesheetWithdrawal0802Transfer2024Repository;


    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    @Test
    @Transactional
    @Tag("TableTruncate")
    void testPractice2024() {

        Long documentCode = 3434L;

        AllSheet0802WithdrawalItemsByTransferDto allSheet0802WithdrawalItemsByTransferDto = new AllSheet0802WithdrawalItemsByTransferDto();

        Sheet080200WithdrawalItemsByTransferDto sheet1 = new Sheet080200WithdrawalItemsByTransferDto();

        sheet1.setShishutsuKoumoku("支出項目");
        sheet1.setTekiyou("摘要");
        sheet1.setDantaiName0820("団体名称");

        allSheet0802WithdrawalItemsByTransferDto.getListSheet0802().add(sheet1);

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(2024); // 実際には表紙からコピー
        documentPropertyDto
                .setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R4/12/1")); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        int size = insertPoliticalOrganization0802Logic.practice(documentCode,
                documentPropertyDto, allSheet0802WithdrawalItemsByTransferDto,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingBalancesheetWithdrawal0802Transfer2024Entity> list = offeringBalancesheetWithdrawal0802Transfer2024Repository
                .findByDocumentCodeOrderByOfferingBalancesheetWithdrawal0802TransferId(documentCode);

        // 取得データは1件
        assertThat(size).isEqualTo(1);
        assertThat(list.size()).isEqualTo(size);

        OfferingBalancesheetWithdrawal0802Transfer2024Entity entity = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity.getOfferingDate())
                .isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity.getShishutsuKoumoku()).isEqualTo(sheet1.getShishutsuKoumoku());
        assertThat(entity.getTekiyou()).isEqualTo(sheet1.getTekiyou());
        assertThat(entity.getDantaiName0820()).isEqualTo(sheet1.getDantaiName0820());
    }

    
    
    
    @Test
    @Transactional
    void testPractice2025() {

        Long documentCode = 3434L;

        AllSheet0802WithdrawalItemsByTransferDto allSheet0802WithdrawalItemsByTransferDto = new AllSheet0802WithdrawalItemsByTransferDto();

        Sheet080200WithdrawalItemsByTransferDto sheet1 = new Sheet080200WithdrawalItemsByTransferDto();

        sheet1.setShishutsuKoumoku("支出項目");
        sheet1.setTekiyou("摘要");
        sheet1.setDantaiName0820("団体名称");

        allSheet0802WithdrawalItemsByTransferDto.getListSheet0802().add(sheet1);

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(2025); // 実際には表紙からコピー
        documentPropertyDto
                .setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R4/12/1")); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        int size = insertPoliticalOrganization0802Logic.practice(documentCode,
                documentPropertyDto, allSheet0802WithdrawalItemsByTransferDto,
                CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingBalancesheetWithdrawal0802Transfer2025Entity> list = offeringBalancesheetWithdrawal0802Transfer2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetWithdrawal0802TransferId(documentCode);

        // 取得データは1件
        assertThat(size).isEqualTo(1);
        assertThat(list.size()).isEqualTo(size);

        OfferingBalancesheetWithdrawal0802Transfer2025Entity entity = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity.getOfferingDate())
                .isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity.getShishutsuKoumoku()).isEqualTo(sheet1.getShishutsuKoumoku());
        assertThat(entity.getTekiyou()).isEqualTo(sheet1.getTekiyou());
        assertThat(entity.getDantaiName0820()).isEqualTo(sheet1.getDantaiName0820());
    }

}
