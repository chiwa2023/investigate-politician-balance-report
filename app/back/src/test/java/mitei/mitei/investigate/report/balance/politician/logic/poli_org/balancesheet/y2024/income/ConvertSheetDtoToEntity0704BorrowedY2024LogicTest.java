package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070400BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070400BorrowedMoneyDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * ConvertSheetDtoToEntity0704BorrowedLogic単体テスト
 */
class ConvertSheetDtoToEntity0704BorrowedY2024LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
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

        Sheet070400BorrowedMoneyDto sheet1 = new Sheet070400BorrowedMoneyDto();
        sheet1.setPageTotal(300L);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070400BorrowedMoneyDto row0 = new Row070400BorrowedMoneyDto();

        // 連番
        row0.setIchirenNo(1);
        // 借り先/
        row0.setKarisaki("親戚のおじさん");
        // 金額
        row0.setKingaku(400L);
        // 備考
        row0.setBikou("備考");

        sheet1.getList().add(row0);

        ConvertSheetDtoToEntity0704BorrowedY2024Logic convertSheetDtoToEntity0704BorrowedY2024Logic = new ConvertSheetDtoToEntity0704BorrowedY2024Logic();

        List<OfferingBalancesheetIncome2024Entity> list = convertSheetDtoToEntity0704BorrowedY2024Logic
                .practice(documentCode, documentPropertyDto, sheet1, CreateTestPrivilegeDtoUtil.pracitce());

        assertThat(list.size()).isEqualTo(1); // 1件挿入

        // 1件取得
        OfferingBalancesheetIncome2024Entity entity = list.get(0);

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
        
        //様式項目
        assertThat(entity.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04);
        assertThat(entity.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity.getPageTotal()).isEqualTo(sheet1.getPageTotal());
        assertThat(entity.getIchirenNo()).isEqualTo(row0.getIchirenNo());
        assertThat(entity.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04_TEXT);
        assertThat(entity.getKingaku()).isEqualTo(row0.getKingaku());
        assertThat(entity.getPartnerName()).isEqualTo(row0.getKarisaki());
        assertThat(entity.getBikou()).isEqualTo(row0.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity.getSearchWords()).isEqualTo("借入金親戚のおじさん");
    }

}
