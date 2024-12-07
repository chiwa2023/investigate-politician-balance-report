package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

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

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0800DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row080000DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080000DifficultCollectReceiptDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetDifficalt0800Recipt2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetDifficalt0800Recipt2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalOrganization08000Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganization08000Y2024LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganization08000Y2024Logic insertPoliticalOrganization08000Y2024Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 様式8 領収書を徴しがたかった支出項目一覧表Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2024Repository offeringBalancesheetDifficalt0800Recipt2024Repository;

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

        int size = insertPoliticalOrganization08000Y2024Logic.practice(documentCode, documentPropertyDto,
                allSheet0800DifficultCollectReceiptDto, CreateTestPrivilegeDtoUtil.pracitce());
        List<OfferingBalancesheetDifficalt0800Recipt2024Entity> list = offeringBalancesheetDifficalt0800Recipt2024Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(documentCode);

        // 取得データは1件
        assertThat(size).isEqualTo(1);
        assertThat(list.size()).isEqualTo(size);

        OfferingBalancesheetDifficalt0800Recipt2024Entity entity = list.get(0);

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

        assertThat(entity.getIchirenNo()).isEqualTo(row1.getIchirenNo());
        assertThat(entity.getKoumoku()).isEqualTo(row1.getKoumoku());
        assertThat(entity.getTekiyou()).isEqualTo(row1.getTekiyou());
        assertThat(entity.getKingaku()).isEqualTo(row1.getKingaku());
        assertThat(entity.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity.getJijyou()).isEqualTo(row1.getJijyou());
    }

}
