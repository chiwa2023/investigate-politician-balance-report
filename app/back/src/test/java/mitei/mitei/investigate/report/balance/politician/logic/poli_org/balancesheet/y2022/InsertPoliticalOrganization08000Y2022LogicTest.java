package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0800DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row080000DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080000DifficultCollectReceiptDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetDifficalt0800Recipt2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetDifficalt0800Recipt2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalOrganization08000Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganization08000Y2022LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganization08000Y2022Logic insertPoliticalOrganization08000Y2022Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 様式8 領収書を徴しがたかった支出項目一覧表Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2022Repository offeringBalancesheetDifficalt0800Recipt2022Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
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

        AllSheet0800DifficultCollectReceiptDto allSheet0800DifficultCollectReceiptDto = new AllSheet0800DifficultCollectReceiptDto();

        Sheet080000DifficultCollectReceiptDto sheet1 = new Sheet080000DifficultCollectReceiptDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row080000DifficultCollectReceiptDto row1 = new Row080000DifficultCollectReceiptDto();
        row1.setIchirenNo(1);
        row1.setKoumoku("支出項目");
        row1.setTekiyou("摘要");
        row1.setKingaku(30000L); // NOPMD
        row1.setAccrualDate("R4/12/1");
        row1.setJijyou("徴し難たかった事情");

        sheet1.getList().add(row1);

        allSheet0800DifficultCollectReceiptDto.setSheet080000DifficultCollectReceiptDto(sheet1);

        int size = insertPoliticalOrganization08000Y2022Logic.practice(documentCode, documentPropertyDto,
                allSheet0800DifficultCollectReceiptDto, CreateTestPrivilegeDtoUtil.pracitce());
        List<OfferingBalancesheetDifficalt0800Recipt2022Entity> list = offeringBalancesheetDifficalt0800Recipt2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(documentCode);

        // 取得データは1件
        assertEquals(1, size, "");
        assertEquals(size, list.size(), "");

        OfferingBalancesheetDifficalt0800Recipt2022Entity entity = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertEquals(DataHistoryStatusConstants.INSERT.value(), entity.getSaishinKbn(), "");
        assertEquals(documentPropertyDto.getHoukokuNen(), entity.getHoukokuNen(), "");
        assertEquals(documentPropertyDto.getOfferingDate(), entity.getOfferingDate(), "");
        assertEquals(documentPropertyDto.getPoliticalOrganizationId(), entity.getPoliticalOrganizationId(), "");
        assertEquals(documentPropertyDto.getPoliticalOrganizationCode(), entity.getPoliticalOrganizationCode(), "");
        assertEquals(documentPropertyDto.getPoliticalOrganizationName(), entity.getPoliticalOrganizationName(), "");
        assertEquals(documentPropertyDto.getDantaiName(), entity.getDantaiName(), "");
        assertEquals(documentPropertyDto.getDaihyouName(), entity.getDaihyouName(), "");
        assertEquals(documentPropertyDto.getRelationKbn(), entity.getRelationKbn(), "");
        assertEquals(documentPropertyDto.getRelationPersonIdDelegate(), entity.getRelationPersonIdDelegate(), "");
        assertEquals(documentPropertyDto.getRelationPersonCodeDelegate(), entity.getRelationPersonCodeDelegate(), "");
        assertEquals(documentPropertyDto.getRelationPersonNameDelegate(), entity.getRelationPersonNameDelegate(), "");

        assertEquals(row1.getIchirenNo(), entity.getIchirenNo(), "");
        assertEquals(row1.getKoumoku(), entity.getKoumoku(), "");
        assertEquals(row1.getTekiyou(), entity.getTekiyou(), "");
        assertEquals(row1.getKingaku(), entity.getKingaku(), "");
        assertEquals(row1.getAccrualDate(), entity.getAccrualDate(), "");
        assertEquals(row1.getJijyou(), entity.getJijyou(), "");
    }

}
