package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income;

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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070500IncomeRelatedToGrantsDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * ConvertSheetDtoToEntity0705RelatedToGrantsLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertSheetDtoToEntity0705RelatedToGrantsY2024LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ConvertSheetDtoToEntity0705RelatedToGrantsY2024Logic convertSheetDtoToEntity0705RelatedToGrantsY2024Logic;

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

        Sheet070500IncomeRelatedToGrantsDto sheet1 = new Sheet070500IncomeRelatedToGrantsDto();
        sheet1.setPageTotal(501L);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070500IncomeRelatedToGrantsDto row0 = new Row070500IncomeRelatedToGrantsDto();

        row0.setIchirenNo(1);
        row0.setHonbuShibuName("本部支部名称");
        row0.setKingaku(502L);
        row0.setAccrualDate("R4/2/2");
        row0.setJimushoJuusho("事務所住所");
        row0.setBikou("備考");

        sheet1.getList().add(row0);

        List<OfferingBalancesheetIncome2024Entity> list = convertSheetDtoToEntity0705RelatedToGrantsY2024Logic
                .practice(documentCode, documentPropertyDto, sheet1, CreateTestPrivilegeDtoUtil.pracitce());

        assertThat(list.size()).isEqualTo(1); // 1件挿入

        // 1件取得
        OfferingBalancesheetIncome2024Entity entity = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity.getPoliticalOrganizationCode()).isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity.getPoliticalOrganizationName()).isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity.getRelationPersonIdDelegate()).isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        // 様式項目
        assertThat(entity.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_05);
        assertThat(entity.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity.getIchirenNo()).isEqualTo(row0.getIchirenNo());
        assertThat(entity.getKingaku()).isEqualTo(row0.getKingaku());
        assertThat(entity.getAccrualDate()).isEqualTo(row0.getAccrualDate());
        assertThat(entity.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity.getAccrualDate()));
        assertThat(entity.getBikou()).isEqualTo(row0.getBikou());
        assertThat(entity.getPartnerName()).isEqualTo(row0.getHonbuShibuName());
        assertThat(entity.getPartnerJuusho()).isEqualTo(row0.getJimushoJuusho());

        // TODO 関連者

        // 自由検索
        assertThat(entity.getSearchWords()).isEqualTo("本部/支部からの交付金本部支部名称事務所住所");
    }

}
