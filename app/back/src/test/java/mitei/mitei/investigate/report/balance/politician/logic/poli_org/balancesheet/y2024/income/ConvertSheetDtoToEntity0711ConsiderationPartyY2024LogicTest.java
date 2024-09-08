package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income;

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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0711ConsiderationPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071101Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071102Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071103Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071101ConsiderationPartyPerspnalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071102ConsiderationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071103ConsiderationPartyPoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * ConvertSheetDtoToEntity0711ConsiderationPartyLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertSheetDtoToEntity0711ConsiderationPartyLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ConvertSheetDtoToEntity0711ConsiderationPartyY2024Logic convertSheetDtoToEntity0711ConsiderationPartyY2024Logic;

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

        AllSheet0711ConsiderationPartyDto allSheet0711ConsiderationPartyDto = new AllSheet0711ConsiderationPartyDto();

        // その1
        AllSheetKbn071101Dto allSheetKbn071101Dto = new AllSheetKbn071101Dto();

        Sheet071101ConsiderationPartyPerspnalDto sheet1 = new Sheet071101ConsiderationPartyPerspnalDto();
        sheet1.setPageTotal(101L);
        sheet1.setPartyName("パーティ名称");
        sheet1.setSortNo("9");

        Row070711DonateDto row1 = new Row070711DonateDto();

        // 連番
        row1.setIchirenNo(1);
        // 寄付者の名前
        row1.setKifusha("寄付者名称個人");
        // 金額
        row1.setKingaku(201L);
        // 発生日
        row1.setAccrualDate("R4/2/1");
        // 住所
        row1.setJusho("住所個人");
        // 職業
        row1.setShokugyou("職業");
        // 備考
        row1.setBikou("備考");
        // 通し番号
        row1.setTohshibangou(1);
        // 税額控除フラグ
        row1.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row1.setGyoukubun(Short.valueOf("8"));

        sheet1.getList().add(row1);

        allSheetKbn071101Dto.getList().add(sheet1);

        // その2
        AllSheetKbn071102Dto allSheetKbn071102Dto = new AllSheetKbn071102Dto();

        Sheet071102ConsiderationPartyGroupDto sheet2 = new Sheet071102ConsiderationPartyGroupDto();
        sheet2.setPageTotal(102L);
        sheet2.setPartyName("パーティ名称");
        sheet2.setSortNo("9");

        Row070711DonateDto row2 = new Row070711DonateDto();

        // 連番
        row2.setIchirenNo(1);
        // 寄付者の名前
        row2.setKifusha("寄付者名称法人");
        // 金額
        row2.setKingaku(202L);
        // 発生日
        row2.setAccrualDate("R4/2/2");
        // 住所
        row2.setJusho("住所法人");
        // 職業
        row2.setShokugyou("職業");
        // 備考
        row2.setBikou("備考");
        // 通し番号
        row2.setTohshibangou(1);
        // 税額控除フラグ
        row2.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row2.setGyoukubun(Short.valueOf("8"));

        sheet2.getList().add(row2);

        allSheetKbn071102Dto.getList().add(sheet2);

        // その3
        AllSheetKbn071103Dto allSheetKbn071103Dto = new AllSheetKbn071103Dto();

        Sheet071103ConsiderationPartyPoliticOrgDto sheet3 = new Sheet071103ConsiderationPartyPoliticOrgDto();
        sheet3.setPageTotal(103L);
        sheet3.setPartyName("パーティ名称");
        sheet3.setSortNo("9");

        Row070711DonateDto row3 = new Row070711DonateDto();

        // 連番
        row3.setIchirenNo(1);
        // 寄付者の名前
        row3.setKifusha("寄付者名称政治団体");
        // 金額
        row3.setKingaku(203L);
        // 発生日
        row3.setAccrualDate("R4/2/3");
        // 住所
        row3.setJusho("住所政治団体");
        // 職業
        row3.setShokugyou("職業");
        // 備考
        row3.setBikou("備考");
        // 通し番号
        row3.setTohshibangou(1);
        // 税額控除フラグ
        row3.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row3.setGyoukubun(Short.valueOf("8"));

        sheet3.getList().add(row3);

        allSheetKbn071103Dto.getList().add(sheet3);

        allSheet0711ConsiderationPartyDto.setAllSheetKbn071101Dto(allSheetKbn071101Dto);
        allSheet0711ConsiderationPartyDto.setAllSheetKbn071102Dto(allSheetKbn071102Dto);
        allSheet0711ConsiderationPartyDto.setAllSheetKbn071103Dto(allSheetKbn071103Dto);

        List<OfferingBalancesheetIncome2024Entity> list = convertSheetDtoToEntity0711ConsiderationPartyY2024Logic.practice(
                documentCode, documentPropertyDto, allSheet0711ConsiderationPartyDto,
                CreateTestPrivilegeDtoUtil.pracitce());

        assertThat(list.size()).isEqualTo(3); // 3件挿入

        // 1件取得
        OfferingBalancesheetIncome2024Entity entity1 = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
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
        assertThat(entity1.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        assertThat(entity1.getPageTotal()).isEqualTo(sheet1.getPageTotal());
        assertThat(entity1.getPartyName()).isEqualTo(sheet1.getPartyName());
        assertThat(entity1.getSortNo()).isEqualTo(sheet1.getSortNo());

        assertThat(entity1.getIchirenNo()).isEqualTo(row1.getIchirenNo());
        assertThat(entity1.getPartnerName()).isEqualTo(row1.getKifusha());
        assertThat(entity1.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet1.getPartyName() + ")");
        assertThat(entity1.getKingaku()).isEqualTo(row1.getKingaku());
        assertThat(entity1.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        assertThat(entity1.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1.getAccrualDate()));
        assertThat(entity1.getPartnerJuusho()).isEqualTo(row1.getJusho());
        assertThat(entity1.getShokugyou()).isEqualTo(row1.getShokugyou());
        assertThat(entity1.getBikou()).isEqualTo(row1.getBikou());
        assertThat(entity1.getTohshibangou()).isEqualTo(row1.getTohshibangou());
        assertThat(entity1.getFlgZeigakuKohjo()).isEqualTo(row1.getFlgZeigakuKohjo());
        assertThat(entity1.getGyoukubun()).isEqualTo(row1.getGyoukubun());

        // 自由検索
        assertThat(entity1.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称)寄付者名称個人住所個人");

        /* 枝区分項目2 */
        OfferingBalancesheetIncome2024Entity entity2 = list.get(1);

        // 様式項目
        assertThat(entity2.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity2.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        assertThat(entity2.getPageTotal()).isEqualTo(sheet2.getPageTotal());
        assertThat(entity2.getPartyName()).isEqualTo(sheet2.getPartyName());
        assertThat(entity2.getSortNo()).isEqualTo(sheet2.getSortNo());

        assertThat(entity2.getIchirenNo()).isEqualTo(row2.getIchirenNo());
        assertThat(entity2.getPartnerName()).isEqualTo(row2.getKifusha());
        assertThat(entity2.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet2.getPartyName() + ")");
        assertThat(entity2.getKingaku()).isEqualTo(row2.getKingaku());
        assertThat(entity2.getAccrualDate()).isEqualTo(row2.getAccrualDate());
        assertThat(entity2.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity2.getAccrualDate()));
        assertThat(entity2.getPartnerJuusho()).isEqualTo(row2.getJusho());
        assertThat(entity2.getShokugyou()).isEqualTo(row2.getShokugyou());
        assertThat(entity2.getBikou()).isEqualTo(row2.getBikou());
        assertThat(entity2.getTohshibangou()).isEqualTo(row2.getTohshibangou());
        assertThat(entity2.getFlgZeigakuKohjo()).isEqualTo(row2.getFlgZeigakuKohjo());
        assertThat(entity2.getGyoukubun()).isEqualTo(row2.getGyoukubun());

        // 自由検索
        assertThat(entity2.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称)寄付者名称法人住所法人");

        /* 枝区分項目3 */
        OfferingBalancesheetIncome2024Entity entity3 = list.get(2);

        // 様式項目
        assertThat(entity3.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity3.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        assertThat(entity3.getPageTotal()).isEqualTo(sheet3.getPageTotal());
        assertThat(entity3.getPartyName()).isEqualTo(sheet3.getPartyName());
        assertThat(entity3.getSortNo()).isEqualTo(sheet3.getSortNo());

        assertThat(entity3.getIchirenNo()).isEqualTo(row3.getIchirenNo());
        assertThat(entity3.getPartnerName()).isEqualTo(row3.getKifusha());
        assertThat(entity3.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet3.getPartyName() + ")");
        assertThat(entity3.getKingaku()).isEqualTo(row3.getKingaku());
        assertThat(entity3.getAccrualDate()).isEqualTo(row3.getAccrualDate());
        assertThat(entity3.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity3.getAccrualDate()));
        assertThat(entity3.getPartnerJuusho()).isEqualTo(row3.getJusho());
        assertThat(entity3.getShokugyou()).isEqualTo(row3.getShokugyou());
        assertThat(entity3.getBikou()).isEqualTo(row3.getBikou());
        assertThat(entity3.getTohshibangou()).isEqualTo(row3.getTohshibangou());
        assertThat(entity3.getFlgZeigakuKohjo()).isEqualTo(row3.getFlgZeigakuKohjo());
        assertThat(entity3.getGyoukubun()).isEqualTo(row3.getGyoukubun());

        // 自由検索
        assertThat(entity3.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称)寄付者名称政治団体住所政治団体");

    }

}
