package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023.income;

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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0708MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070801Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070802Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070803Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070801MediationPersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070802MediationGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070803MediationPoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheetIncome2023Entity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * ConvertSheetDtoToEntity0708MediationLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertSheetDtoToEntity0708MediationLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ConvertSheetDtoToEntity0708MediationY2023Logic convertSheetDtoToEntity0708MediationY2023Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    @Test
    void testPractice() { // NOPMD

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

        // その1
        AllSheetKbn070801Dto allSheetKbn070801Dto = new AllSheetKbn070801Dto();

        Sheet070801MediationPersonDto sheet1 = new Sheet070801MediationPersonDto();
        sheet1.setPageTotal(101L);
        sheet1.setSonotaTotal("201");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row1 = new Row070812MediationDto();

        /// ** 連番 */
        row1.setIchirenNo(1);
        /// ** 名前 */
        row1.setName("名称個人");
        /// ** 金額 */
        row1.setKingaku(301L);
        /// ** 発生日 */
        row1.setAccrualDate("R4/2/1");
        /// ** 斡旋の期間 */
        row1.setPeriodMediate("1/1-1/31");
        /// ** 住所 */
        row1.setJuusho("住所個人");
        /// ** 職業 */
        row1.setShokugyou("職業");
        /// ** 備考 */
        row1.setBikou("備考");
        /// ** 通し番号 */
        row1.setTohshibangou(1);
        /// ** 行区分 */
        row1.setGyoukubun(Short.valueOf("7"));

        sheet1.getList().add(row1);

        allSheetKbn070801Dto.setSheet070801MediationPersonDto(sheet1);

        // その2
        AllSheetKbn070802Dto allSheetKbn070802Dto = new AllSheetKbn070802Dto();

        Sheet070802MediationGroupDto sheet2 = new Sheet070802MediationGroupDto();
        sheet2.setPageTotal(102L);
        sheet2.setSonotaTotal("202");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row2 = new Row070812MediationDto();

        /// ** 連番 */
        row2.setIchirenNo(1);
        /// ** 名前 */
        row2.setName("名称法人");
        /// ** 金額 */
        row2.setKingaku(303L);
        /// ** 発生日 */
        row2.setAccrualDate("R4/2/2");
        /// ** 斡旋の期間 */
        row2.setPeriodMediate("1/1-1/31");
        /// ** 住所 */
        row2.setJuusho("住所法人");
        /// ** 職業 */
        row2.setShokugyou("職業");
        /// ** 備考 */
        row2.setBikou("備考");
        /// ** 通し番号 */
        row2.setTohshibangou(1);
        /// ** 行区分 */
        row2.setGyoukubun(Short.valueOf("7"));

        sheet2.getList().add(row2);

        allSheetKbn070802Dto.setSheet070802MediationGroupDto(sheet2);

        // その3
        AllSheetKbn070803Dto allSheetKbn070803Dto = new AllSheetKbn070803Dto();

        Sheet070803MediationPoliticOrgDto sheet3 = new Sheet070803MediationPoliticOrgDto();
        sheet3.setPageTotal(103L);
        sheet3.setSonotaTotal("303");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row3 = new Row070812MediationDto();

        /// ** 連番 */
        row3.setIchirenNo(1);
        /// ** 名前 */
        row3.setName("名称政治団体");
        /// ** 金額 */
        row3.setKingaku(303L);
        /// ** 発生日 */
        row3.setAccrualDate("R4/2/3");
        /// ** 斡旋の期間 */
        row3.setPeriodMediate("1/1-1/31");
        /// ** 住所 */
        row3.setJuusho("住所政治団体");
        /// ** 職業 */
        row3.setShokugyou("職業");
        /// ** 備考 */
        row3.setBikou("備考");
        /// ** 通し番号 */
        row3.setTohshibangou(1);
        /// ** 行区分 */
        row3.setGyoukubun(Short.valueOf("7"));

        sheet3.getList().add(row3);

        allSheetKbn070803Dto.setSheet070803MediationPoliticOrgDto(sheet3);

        AllSheet0708MediationDto allSheet0708MediationDto = new AllSheet0708MediationDto();

        allSheet0708MediationDto.setAllSheetKbn070801Dto(allSheetKbn070801Dto);
        allSheet0708MediationDto.setAllSheetKbn070802Dto(allSheetKbn070802Dto);
        allSheet0708MediationDto.setAllSheetKbn070803Dto(allSheetKbn070803Dto);

        List<OfferingBalancesheetIncome2023Entity> list = convertSheetDtoToEntity0708MediationY2023Logic.practice(
                documentCode, documentPropertyDto, allSheet0708MediationDto, CreateTestPrivilegeDtoUtil.pracitce());

        assertThat(list.size()).isEqualTo(3); // 3件挿入

        // 1件取得
        OfferingBalancesheetIncome2023Entity entity1 = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity1.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity1.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity1.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity1.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity1.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity1.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity1.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity1.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity1.getRelationPersonIdDelegate()).isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity1.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity1.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* 枝区分項目1 */

        // 様式項目
        assertThat(entity1.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity1.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート項目
        assertThat(entity1.getPageTotal()).isEqualTo(sheet1.getPageTotal());
        assertThat(entity1.getSonotaTotal()).isEqualTo(sheet1.getSonotaTotal());
        assertThat(entity1.getIchirenNo()).isEqualTo(row1.getIchirenNo());
        assertThat(entity1.getPartnerName()).isEqualTo(row1.getName());
        assertThat(entity1.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity1.getKingaku()).isEqualTo(row1.getKingaku());
        assertThat(entity1.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity1.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row1.getAccrualDate()));
        assertThat(entity1.getPeriodMediate()).isEqualTo(row1.getPeriodMediate());
        assertThat(entity1.getPartnerJuusho()).isEqualTo(row1.getJuusho());
        assertThat(entity1.getShokugyou()).isEqualTo(row1.getShokugyou());
        assertThat(entity1.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity1.getTohshibangou()).isEqualTo(row1.getTohshibangou());
        assertThat(entity1.getGyoukubun()).isEqualTo(row1.getGyoukubun());

        // 自由検索
        assertThat(entity1.getSearchWords()).isEqualTo("寄付のうちあっせん名称個人住所個人");

        /* 枝区分項目2 */
        OfferingBalancesheetIncome2023Entity entity2 = list.get(1);

        // 様式項目
        assertThat(entity2.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity2.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート項目
        assertThat(entity2.getPageTotal()).isEqualTo(sheet2.getPageTotal());
        assertThat(entity2.getSonotaTotal()).isEqualTo(sheet2.getSonotaTotal());
        assertThat(entity2.getIchirenNo()).isEqualTo(row2.getIchirenNo());
        assertThat(entity2.getPartnerName()).isEqualTo(row2.getName());
        assertThat(entity2.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity2.getKingaku()).isEqualTo(row2.getKingaku());
        assertThat(entity2.getAccrualDate()).isEqualTo(row2.getAccrualDate());
        assertThat(entity2.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row2.getAccrualDate()));
        assertThat(entity2.getPeriodMediate()).isEqualTo(row2.getPeriodMediate());
        assertThat(entity2.getPartnerJuusho()).isEqualTo(row2.getJuusho());
        assertThat(entity2.getShokugyou()).isEqualTo(row2.getShokugyou());
        assertThat(entity2.getBikou()).isEqualTo(row2.getBikou());
        assertThat(entity2.getTohshibangou()).isEqualTo(row2.getTohshibangou());
        assertThat(entity2.getGyoukubun()).isEqualTo(row2.getGyoukubun());

        // 自由検索
        assertThat(entity2.getSearchWords()).isEqualTo("寄付のうちあっせん名称法人住所法人");

        /* 枝区分項目3 */
        OfferingBalancesheetIncome2023Entity entity3 = list.get(2);

        // 様式項目
        assertThat(entity3.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity3.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート項目
        assertThat(entity3.getPageTotal()).isEqualTo(sheet3.getPageTotal());
        assertThat(entity3.getSonotaTotal()).isEqualTo(sheet3.getSonotaTotal());
        assertThat(entity3.getIchirenNo()).isEqualTo(row3.getIchirenNo());
        assertThat(entity3.getPartnerName()).isEqualTo(row3.getName());
        assertThat(entity3.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity3.getKingaku()).isEqualTo(row3.getKingaku());
        assertThat(entity3.getAccrualDate()).isEqualTo(row3.getAccrualDate());
        assertThat(entity3.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row3.getAccrualDate()));
        assertThat(entity3.getPeriodMediate()).isEqualTo(row3.getPeriodMediate());
        assertThat(entity3.getPartnerJuusho()).isEqualTo(row3.getJuusho());
        assertThat(entity3.getShokugyou()).isEqualTo(row3.getShokugyou());
        assertThat(entity3.getBikou()).isEqualTo(row3.getBikou());
        assertThat(entity3.getTohshibangou()).isEqualTo(row3.getTohshibangou());
        assertThat(entity3.getGyoukubun()).isEqualTo(row3.getGyoukubun());

        // 自由検索
        assertThat(entity3.getSearchWords()).isEqualTo("寄付のうちあっせん名称政治団体住所政治団体");

        // TODO 関連者
    }

}
