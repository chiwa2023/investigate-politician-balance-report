package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.income;

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

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071000SpecificPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071000SpecificPartyDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetIncome2022Entity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * ConvertSheetDtoToEntity0710SpecificPartyLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertSheetDtoToEntity0710SpecificPartyLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ConvertSheetDtoToEntity0710SpecificPartyY2022Logic convertSheetDtoToEntity0710SpecificPartyY2022Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    @Test
    void testPractice() {

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

        Sheet071000SpecificPartyDto sheet1 = new Sheet071000SpecificPartyDto();
        sheet1.setPageTotal(101L);

        Row071000SpecificPartyDto row0 = new Row071000SpecificPartyDto();

        // 連番
        row0.setIchirenNo(1);
        // パーティ名称
        row0.setPartyName("パーティ名称");
        // 金額
        row0.setKingaku(102L);
        // 支払い数
        row0.setShiharaisu(3);
        // 開催日
        row0.setAccrualDate("R4/2/2");
        // 開催場所
        row0.setKaisaiBasho("開催場所");
        // 備考
        row0.setBikou("備考");

        sheet1.getList().add(row0);

        List<OfferingBalancesheetIncome2022Entity> list = convertSheetDtoToEntity0710SpecificPartyY2022Logic
                .practice(documentCode, documentPropertyDto, sheet1, CreateTestPrivilegeDtoUtil.pracitce());

        assertThat(list.size()).isEqualTo(1); // 1件挿入

        // 1件取得
        OfferingBalancesheetIncome2022Entity entity = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
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
        assertThat(entity.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_10);
        assertThat(entity.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity.getPageTotal()).isEqualTo(sheet1.getPageTotal());
        assertThat(entity.getIchirenNo()).isEqualTo(row0.getIchirenNo());
        assertThat(entity.getItemName()).isEqualTo(row0.getPartyName() + "(" + row0.getKaisaiBasho() + ")");
        assertThat(entity.getKingaku()).isEqualTo(row0.getKingaku());
        assertThat(entity.getShiharaisu()).isEqualTo(row0.getShiharaisu());
        assertThat(entity.getAccrualDate()).isEqualTo(row0.getAccrualDate());
        assertThat(entity.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row0.getAccrualDate()));
        assertThat(entity.getKaisaiBasho()).isEqualTo(row0.getKaisaiBasho());
        assertThat(entity.getBikou()).isEqualTo(row0.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity.getSearchWords()).isEqualTo("パーティ名称(開催場所)");

    }

}
