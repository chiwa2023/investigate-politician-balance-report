package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.income;

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

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070300JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070300JournalAndOtherDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetIncome2022Entity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * ConvertSheetDtoToEntity0703JournalLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertSheetDtoToEntity0703JournalY2022LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ConvertSheetDtoToEntity0703JournalY2022Logic convertSheetDtoToEntity0703JournalY2022Logic;
    
    @Test
    @Tag("TableTruncate")
    void testPractice() {
        
        DateConvertUtil dateConvertUtil = new DateConvertUtil();
        
        // 文書同一識別コード
        Long documentCode = 3434L;

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(2024); // 実際には表紙からコピー
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R4/12/1")); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");
        
        // リスト存在
        Sheet070300JournalAndOtherDto sheet1 = new Sheet070300JournalAndOtherDto();

        Row070300JournalAndOtherDto row0 = new Row070300JournalAndOtherDto();
        row0.setIchirenNo(1);
        row0.setJigyoNoShurui("機関誌発行");
        row0.setKingaku(301L);
        row0.setBikou("備考");

        sheet1.getList().add(row0);


        List<OfferingBalancesheetIncome2022Entity> list = convertSheetDtoToEntity0703JournalY2022Logic
                .practice(documentCode, documentPropertyDto,  sheet1, CreateTestPrivilegeDtoUtil.pracitce());

        assertThat(list.size()).isEqualTo(1); // 1件挿入

        
        // 1件取得
        OfferingBalancesheetIncome2022Entity entity = list.get(0);
        
        // 様式項目
        assertThat(entity.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_03);
        assertThat(entity.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし


        assertThat(entity.getPageTotal()).isEqualTo(sheet1.getPageTotal());
        assertThat(entity.getIchirenNo()).isEqualTo(row0.getIchirenNo());
        assertThat(entity.getItemName()).isEqualTo(row0.getJigyoNoShurui());
        assertThat(entity.getKingaku()).isEqualTo(row0.getKingaku());
        assertThat(entity.getBikou()).isEqualTo(row0.getBikou());
        
        // TODO 関連者
        
        // 自由検索
        assertThat(entity.getSearchWords()).isEqualTo("機関誌発行");

    }

}
