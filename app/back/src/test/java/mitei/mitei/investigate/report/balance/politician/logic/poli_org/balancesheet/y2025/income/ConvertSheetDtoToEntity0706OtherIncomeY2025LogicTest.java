package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.income;

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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070600OtherIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070600OtherIncomeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * ConvertSheetDtoToEntity0706OtherIncomeLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertSheetDtoToEntity0706OtherIncomeY2025LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ConvertSheetDtoToEntity0706OtherIncomeY2025Logic convertSheetDtoToEntity0706OtherIncomeY2025Logic;

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

        Sheet070600OtherIncomeDto sheet1 = new Sheet070600OtherIncomeDto();
        sheet1.setPageTotal(300L);
        sheet1.setMimanTotal("");

        Row070600OtherIncomeDto row0 = new Row070600OtherIncomeDto();
        // 連番
        row0.setIchirenNo(1);
        // 摘要
        row0.setTekiyou("摘要");
        // 金額
        row0.setKingaku(400L);
        // 備考
        row0.setBikou("備考");
        sheet1.getList().add(row0);

        List<OfferingBalancesheetIncome2025Entity> list = convertSheetDtoToEntity0706OtherIncomeY2025Logic
                .practice(documentCode, documentPropertyDto, sheet1, CreateTestPrivilegeDtoUtil.pracitce());

        assertThat(list.size()).isEqualTo(1); // 1件挿入

        // 1件取得
        OfferingBalancesheetIncome2025Entity entity = list.get(0);

        // 様式項目
        assertThat(entity.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_06);
        assertThat(entity.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート記載内容
        assertThat(entity.getPageTotal()).isEqualTo(sheet1.getPageTotal());
        assertThat(entity.getMimanTotal()).isEqualTo(sheet1.getMimanTotal());
        assertThat(entity.getIchirenNo()).isEqualTo(row0.getIchirenNo());
        assertThat(entity.getItemName()).isEqualTo(row0.getTekiyou());
        assertThat(entity.getKingaku()).isEqualTo(row0.getKingaku());
        assertThat(entity.getBikou()).isEqualTo(row0.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity.getSearchWords()).isEqualTo("摘要");
    }

}
