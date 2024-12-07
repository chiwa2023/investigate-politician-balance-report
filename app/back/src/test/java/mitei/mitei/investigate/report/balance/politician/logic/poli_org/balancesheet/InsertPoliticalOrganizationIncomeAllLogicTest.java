package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

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

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0703JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0704BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0705IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0706OtherIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0707DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0708MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0709AnonymousInPoliticalPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0710SpecificPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0711ConsiderationPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0712PartyMediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070701Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070702Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070703Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070801Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070802Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn070803Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071101Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071102Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071103Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071201Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071202Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071203Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070300JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070400BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070600OtherIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070900AnonymousInPoliticalPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071000SpecificPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070300JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070400BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070600OtherIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070701DonatePersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070702DonateGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070703DonatePoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070801MediationPersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070802MediationGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070803MediationPoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070900AnonymousInPoliticalPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071000SpecificPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071101ConsiderationPartyPerspnalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071102ConsiderationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071103ConsiderationPartyPoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071201ConsiderationMediationPartyPersonalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071202ConsiderationMediationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071203ConsiderationMediationPartyPoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalOrganizationIncomeLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganizationIncomeAllLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganizationIncomeAllLogic insertPoliticalOrganizationIncomeAllLogic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 政治資金収支報告書収入提出分 */
    @Autowired
    private OfferingBalancesheetIncome2025Repository offeringBalancesheetIncome2025Repository;

    /** 政治資金収支報告書収入提出分 */
    @Autowired
    private OfferingBalancesheetIncome2024Repository offeringBalancesheetIncome2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice2025() { // NOPMD

        // 文書同一識別コード
        Long documentCode = 3434L;

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(2025); // 実際には表紙からコピー
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

        AllBookDto allBookDto = new AllBookDto();

        allBookDto.setAllSheet0703JournalAndOtherDto(new AllSheet0703JournalAndOtherDto());
        allBookDto.setAllSheet0704BorrowedMoneyDto(new AllSheet0704BorrowedMoneyDto());
        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0706OtherIncomeDto(new AllSheet0706OtherIncomeDto());
        allBookDto.setAllSheet0709AnonymousInPoliticalPartyDto(new AllSheet0709AnonymousInPoliticalPartyDto());
        allBookDto.setAllSheet0710SpecificPartyDto(new AllSheet0710SpecificPartyDto());

        /* その3 */
        Sheet070300JournalAndOtherDto sheet03 = new Sheet070300JournalAndOtherDto();

        Row070300JournalAndOtherDto row03 = new Row070300JournalAndOtherDto();
        row03.setIchirenNo(1);
        row03.setJigyoNoShurui("機関誌発行1");
        row03.setKingaku(301L);
        row03.setBikou("備考");

        sheet03.getList().add(row03);

        allBookDto.getAllSheet0703JournalAndOtherDto().setSheet070300JournalAndOtherDto(sheet03);

        /* その4 */

        Sheet070400BorrowedMoneyDto sheet04 = new Sheet070400BorrowedMoneyDto();
        sheet04.setPageTotal(300L);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070400BorrowedMoneyDto row04 = new Row070400BorrowedMoneyDto();

        // 連番
        row04.setIchirenNo(1);
        // 借り先/
        row04.setKarisaki("親戚のおじさん");
        // 金額
        row04.setKingaku(400L);
        // 備考
        row04.setBikou("備考");

        sheet04.getList().add(row04);

        allBookDto.getAllSheet0704BorrowedMoneyDto().setSheet070400BorrowedMoneyDto(sheet04);

        /* その5 */

        Sheet070500IncomeRelatedToGrantsDto sheet05 = new Sheet070500IncomeRelatedToGrantsDto();
        sheet05.setPageTotal(508L);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070500IncomeRelatedToGrantsDto row05 = new Row070500IncomeRelatedToGrantsDto();

        row05.setIchirenNo(1);
        row05.setHonbuShibuName("本部支部名称");
        row05.setKingaku(506L);
        row05.setAccrualDate("R4/2/9");
        row05.setJimushoJuusho("事務所住所");
        row05.setBikou("備考");

        sheet05.getList().add(row05);

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().setSheet070500IncomeRelatedToGrantsDto(sheet05);

        /* その6 */

        Sheet070600OtherIncomeDto sheet06 = new Sheet070600OtherIncomeDto();
        sheet06.setPageTotal(300L);
        sheet06.setMimanTotal("");

        Row070600OtherIncomeDto row06 = new Row070600OtherIncomeDto();
        // 連番
        row06.setIchirenNo(1);
        // 摘要
        row06.setTekiyou("摘要");
        // 金額
        row06.setKingaku(400L);
        // 備考
        row06.setBikou("備考");
        sheet06.getList().add(row06);

        allBookDto.getAllSheet0706OtherIncomeDto().setSheet070600OtherIncomeDto(sheet06);

        /* その7 */

        // その1
        AllSheetKbn070701Dto allSheetKbn070701Dto = new AllSheetKbn070701Dto();

        Sheet070701DonatePersonDto sheet071 = new Sheet070701DonatePersonDto();
        sheet071.setPageTotal(101L);
        sheet071.setSonotaTotal("2012");

        Row070711DonateDto row071 = new Row070711DonateDto();

        // 連番 */
        row071.setIchirenNo(1);
        // 寄付者の名前 */
        row071.setKifusha("寄付者名称個人a");
        // 金額 */
        row071.setKingaku(301L);
        // 発生日 */
        row071.setAccrualDate("R4/2/11");
        // 住所 */
        row071.setJusho("住所個人71");
        // 職業 */
        row071.setShokugyou("職業");
        // 備考 */
        row071.setBikou("備考");
        // 通し番号 */
        row071.setTohshibangou(1);
        // 税額控除フラグ */
        row071.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分 */
        row071.setGyoukubun(Short.valueOf("8"));

        sheet071.getList().add(row071);

        allSheetKbn070701Dto.setSheet070701DonatePersonDto(sheet071);

        // その2
        AllSheetKbn070702Dto allSheetKbn070702Dto = new AllSheetKbn070702Dto();

        Sheet070702DonateGroupDto sheet072 = new Sheet070702DonateGroupDto();
        sheet072.setPageTotal(102L);
        sheet072.setSonotaTotal("2022");

        Row070711DonateDto row072 = new Row070711DonateDto();

        // 連番
        row072.setIchirenNo(1);
        // 寄付者の名前 */
        row072.setKifusha("寄付者名称法人2");
        // 金額
        row072.setKingaku(516L);
        // 発生日
        row072.setAccrualDate("R4/2/13");
        // 住所
        row072.setJusho("住所法人72");
        // 職業
        row072.setShokugyou("職業");
        // 備考
        row072.setBikou("備考");
        // 通し番号
        row072.setTohshibangou(1);
        // 税額控除フラグ
        row072.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row072.setGyoukubun(Short.valueOf("8"));

        sheet072.getList().add(row072);

        allSheetKbn070702Dto.setSheet070702DonateGroupDto(sheet072);

        // その3
        AllSheetKbn070703Dto allSheetKbn070703Dto = new AllSheetKbn070703Dto();

        Sheet070703DonatePoliticOrgDto sheet073 = new Sheet070703DonatePoliticOrgDto();
        sheet073.setPageTotal(103L);
        sheet073.setSonotaTotal("203");

        Row070711DonateDto row073 = new Row070711DonateDto();

        // 連番 */
        row073.setIchirenNo(1);
        // 寄付者の名前 */
        row073.setKifusha("寄付者名称政治団体4");
        // 金額 */
        row073.setKingaku(529L);
        // 発生日 */
        row073.setAccrualDate("R4/2/4");
        // 住所 */
        row073.setJusho("住所政治団体73");
        // 職業 */
        row073.setShokugyou("職業");
        // 備考 */
        row073.setBikou("備考");
        // 通し番号 */
        row073.setTohshibangou(1);
        // 税額控除フラグ */
        row073.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分 */
        row073.setGyoukubun(Short.valueOf("8"));

        sheet073.getList().add(row073);

        allSheetKbn070703Dto.setSheet070703DonatePoliticOrgDto(sheet073);

        AllSheet0707DonateDto allSheet0707DonateDto = new AllSheet0707DonateDto();
        allSheet0707DonateDto.setAllSheetKbn070701Dto(allSheetKbn070701Dto);
        allSheet0707DonateDto.setAllSheetKbn070702Dto(allSheetKbn070702Dto);
        allSheet0707DonateDto.setAllSheetKbn070703Dto(allSheetKbn070703Dto);

        allBookDto.setAllSheet0707DonateDto(allSheet0707DonateDto);

        /* その8 */

        // その1
        AllSheetKbn070801Dto allSheetKbn070801Dto = new AllSheetKbn070801Dto();

        Sheet070801MediationPersonDto sheet081 = new Sheet070801MediationPersonDto();
        sheet081.setPageTotal(101L);
        sheet081.setSonotaTotal("201");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row081 = new Row070812MediationDto();

        /// ** 連番 */
        row081.setIchirenNo(1);
        /// ** 名前 */
        row081.setName("名称個人5");
        /// ** 金額 */
        row081.setKingaku(301L);
        /// ** 発生日 */
        row081.setAccrualDate("R4/2/5");
        /// ** 斡旋の期間 */
        row081.setPeriodMediate("1/1-1/21");
        /// ** 住所 */
        row081.setJuusho("住所個人5");
        /// ** 職業 */
        row081.setShokugyou("職業");
        /// ** 備考 */
        row081.setBikou("備考");
        /// ** 通し番号 */
        row081.setTohshibangou(1);
        /// ** 行区分 */
        row081.setGyoukubun(Short.valueOf("7"));

        sheet081.getList().add(row081);

        allSheetKbn070801Dto.setSheet070801MediationPersonDto(sheet081);

        // その2
        AllSheetKbn070802Dto allSheetKbn070802Dto = new AllSheetKbn070802Dto();

        Sheet070802MediationGroupDto sheet082 = new Sheet070802MediationGroupDto();
        sheet082.setPageTotal(102L);
        sheet082.setSonotaTotal("202");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row082 = new Row070812MediationDto();

        /// ** 連番 */
        row082.setIchirenNo(1);
        /// ** 名前 */
        row082.setName("名称法人6");
        /// ** 金額 */
        row082.setKingaku(303L);
        /// ** 発生日 */
        row082.setAccrualDate("R4/2/21");
        /// ** 斡旋の期間 */
        row082.setPeriodMediate("1/1-1/22");
        /// ** 住所 */
        row082.setJuusho("住所法人6");
        /// ** 職業 */
        row082.setShokugyou("職業");
        /// ** 備考 */
        row082.setBikou("備考");
        /// ** 通し番号 */
        row082.setTohshibangou(1);
        /// ** 行区分 */
        row082.setGyoukubun(Short.valueOf("7"));

        sheet082.getList().add(row082);

        allSheetKbn070802Dto.setSheet070802MediationGroupDto(sheet082);

        // その3
        AllSheetKbn070803Dto allSheetKbn070803Dto = new AllSheetKbn070803Dto();

        Sheet070803MediationPoliticOrgDto sheet083 = new Sheet070803MediationPoliticOrgDto();
        sheet083.setPageTotal(103L);
        sheet083.setSonotaTotal("303");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row083 = new Row070812MediationDto();

        /// ** 連番 */
        row083.setIchirenNo(1);
        /// ** 名前 */
        row083.setName("名称政治団体7");
        /// ** 金額 */
        row083.setKingaku(303L);
        /// ** 発生日 */
        row083.setAccrualDate("R4/2/7");
        /// ** 斡旋の期間 */
        row083.setPeriodMediate("1/1-1/23");
        /// ** 住所 */
        row083.setJuusho("住所政治団体7");
        /// ** 職業 */
        row083.setShokugyou("職業");
        /// ** 備考 */
        row083.setBikou("備考");
        /// ** 通し番号 */
        row083.setTohshibangou(1);
        /// ** 行区分 */
        row083.setGyoukubun(Short.valueOf("7"));

        sheet083.getList().add(row083);

        allSheetKbn070803Dto.setSheet070803MediationPoliticOrgDto(sheet083);

        AllSheet0708MediationDto allSheet0708MediationDto = new AllSheet0708MediationDto();

        allSheet0708MediationDto.setAllSheetKbn070801Dto(allSheetKbn070801Dto);
        allSheet0708MediationDto.setAllSheetKbn070802Dto(allSheetKbn070802Dto);
        allSheet0708MediationDto.setAllSheetKbn070803Dto(allSheetKbn070803Dto);

        allBookDto.setAllSheet0708MediationDto(allSheet0708MediationDto);

        /* その9 */

        Sheet070900AnonymousInPoliticalPartyDto sheet09 = new Sheet070900AnonymousInPoliticalPartyDto();
        sheet09.setPageTotal(101L);

        Row070900AnonymousInPoliticalPartyDto row09 = new Row070900AnonymousInPoliticalPartyDto();

        // 連番
        row09.setIchirenNo(1);
        // 場所
        row09.setBasho("開催場所8");
        // 金額
        row09.setKingaku(518L);
        // 発生日
        row09.setAccrualDate("R4/3/1");
        // 備考
        row09.setBikou("備考");

        sheet09.getList().add(row09);

        allBookDto.getAllSheet0709AnonymousInPoliticalPartyDto().setSheet070900AnonymousInPoliticalPartyDto(sheet09);

        /* その10 */
        Sheet071000SpecificPartyDto sheet10 = new Sheet071000SpecificPartyDto();
        sheet10.setPageTotal(101L);

        Row071000SpecificPartyDto row10 = new Row071000SpecificPartyDto();

        // 連番
        row10.setIchirenNo(1);
        // パーティ名称
        row10.setPartyName("パーティ名称10");
        // 金額
        row10.setKingaku(102L);
        // 支払い数
        row10.setShiharaisu(3);
        // 開催日
        row10.setAccrualDate("R4/2/16");
        // 開催場所
        row10.setKaisaiBasho("開催場所");
        // 備考
        row10.setBikou("備考");

        sheet10.getList().add(row10);

        allBookDto.getAllSheet0710SpecificPartyDto().setSheet071000SpecificPartyDto(sheet10);

        /* その11 */

        AllSheet0711ConsiderationPartyDto allSheet0711ConsiderationPartyDto = new AllSheet0711ConsiderationPartyDto();

        // その1
        AllSheetKbn071101Dto allSheetKbn071101Dto = new AllSheetKbn071101Dto();

        Sheet071101ConsiderationPartyPerspnalDto sheet111 = new Sheet071101ConsiderationPartyPerspnalDto();
        sheet111.setPageTotal(101L);
        sheet111.setPartyName("パーティ名称111");
        sheet111.setSortNo("9");

        Row070711DonateDto row111 = new Row070711DonateDto();

        // 連番
        row111.setIchirenNo(1);
        // 寄付者の名前
        row111.setKifusha("寄付者名称個人");
        // 金額
        row111.setKingaku(201L);
        // 発生日
        row111.setAccrualDate("R4/2/8");
        // 住所
        row111.setJusho("住所個人8");
        // 職業
        row111.setShokugyou("職業");
        // 備考
        row111.setBikou("備考");
        // 通し番号
        row111.setTohshibangou(1);
        // 税額控除フラグ
        row111.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row111.setGyoukubun(Short.valueOf("8"));

        sheet111.getList().add(row111);

        allSheetKbn071101Dto.getList().add(sheet111);

        // その2
        AllSheetKbn071102Dto allSheetKbn071102Dto = new AllSheetKbn071102Dto();

        Sheet071102ConsiderationPartyGroupDto sheet112 = new Sheet071102ConsiderationPartyGroupDto();
        sheet112.setPageTotal(102L);
        sheet112.setPartyName("パーティ名称112");
        sheet112.setSortNo("9");

        Row070711DonateDto row112 = new Row070711DonateDto();

        // 連番
        row112.setIchirenNo(1);
        // 寄付者の名前
        row112.setKifusha("寄付者名称法人");
        // 金額
        row112.setKingaku(202L);
        // 発生日
        row112.setAccrualDate("R4/2/9");
        // 住所
        row112.setJusho("住所法人9");
        // 職業
        row112.setShokugyou("職業");
        // 備考
        row112.setBikou("備考");
        // 通し番号
        row112.setTohshibangou(1);
        // 税額控除フラグ
        row112.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row112.setGyoukubun(Short.valueOf("8"));

        sheet112.getList().add(row112);

        allSheetKbn071102Dto.getList().add(sheet112);

        // その3
        AllSheetKbn071103Dto allSheetKbn071103Dto = new AllSheetKbn071103Dto();

        Sheet071103ConsiderationPartyPoliticOrgDto sheet113 = new Sheet071103ConsiderationPartyPoliticOrgDto();
        sheet113.setPageTotal(103L);
        sheet113.setPartyName("パーティ名称113");
        sheet113.setSortNo("9");

        Row070711DonateDto row113 = new Row070711DonateDto();

        // 連番
        row113.setIchirenNo(1);
        // 寄付者の名前
        row113.setKifusha("寄付者名称政治団体");
        // 金額
        row113.setKingaku(203L);
        // 発生日
        row113.setAccrualDate("R4/2/10");
        // 住所
        row113.setJusho("住所政治団体10");
        // 職業
        row113.setShokugyou("職業");
        // 備考
        row113.setBikou("備考");
        // 通し番号
        row113.setTohshibangou(1);
        // 税額控除フラグ
        row113.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row113.setGyoukubun(Short.valueOf("8"));

        sheet113.getList().add(row113);

        allSheetKbn071103Dto.getList().add(sheet113);

        allSheet0711ConsiderationPartyDto.setAllSheetKbn071101Dto(allSheetKbn071101Dto);
        allSheet0711ConsiderationPartyDto.setAllSheetKbn071102Dto(allSheetKbn071102Dto);
        allSheet0711ConsiderationPartyDto.setAllSheetKbn071103Dto(allSheetKbn071103Dto);

        allBookDto.setAllSheet0711ConsiderationPartyDto(allSheet0711ConsiderationPartyDto);

        /* その12 */

        AllSheet0712PartyMediationDto allSheet0712PartyMediationDto = new AllSheet0712PartyMediationDto();

        // その1
        AllSheetKbn071201Dto allSheetKbn071201Dto = new AllSheetKbn071201Dto();

        Sheet071201ConsiderationMediationPartyPersonalDto sheet121 = new Sheet071201ConsiderationMediationPartyPersonalDto();
        sheet121.setPageTotal(101L);
        sheet121.setPartyName("パーティ名称11");
        sheet121.setSortNo("9");

        Row070812MediationDto row121 = new Row070812MediationDto();

        // 連番
        row121.setIchirenNo(1);
        // 名前
        row121.setName("名称個人");
        // 金額
        row121.setKingaku(201L);
        // 発生日
        row121.setAccrualDate("R4/2/11");
        // 斡旋の期間
        row121.setPeriodMediate("1/1-1/28");
        // 住所
        row121.setJuusho("住所個人11");
        // 職業
        row121.setShokugyou("職業");
        // 備考
        row121.setBikou("備考");
        // 通し番号
        row121.setTohshibangou(1);
        // 行区分
        row121.setGyoukubun(Short.valueOf("7"));

        sheet121.getList().add(row121);

        allSheetKbn071201Dto.getList().add(sheet121);

        // その2
        AllSheetKbn071202Dto allSheetKbn071202Dto = new AllSheetKbn071202Dto();

        Sheet071202ConsiderationMediationPartyGroupDto sheet122 = new Sheet071202ConsiderationMediationPartyGroupDto();
        sheet122.setPageTotal(102L);
        sheet122.setPartyName("パーティ名称12");
        sheet122.setSortNo("9");

        Row070812MediationDto row122 = new Row070812MediationDto();

        // 連番
        row122.setIchirenNo(1);
        // 名前
        row122.setName("名称法人");
        // 金額
        row122.setKingaku(202L);
        // 発生日
        row122.setAccrualDate("R4/2/12");
        // 斡旋の期間
        row122.setPeriodMediate("1/1-1/27");
        // 住所
        row122.setJuusho("住所法人12");
        // 職業
        row122.setShokugyou("職業");
        // 備考
        row122.setBikou("備考");
        // 通し番号
        row122.setTohshibangou(1);
        // 行区分
        row122.setGyoukubun(Short.valueOf("7"));

        sheet122.getList().add(row122);

        allSheetKbn071202Dto.getList().add(sheet122);

        // その3
        AllSheetKbn071203Dto allSheetKbn071203Dto = new AllSheetKbn071203Dto();

        Sheet071203ConsiderationMediationPartyPoliticOrgDto sheet123 = new Sheet071203ConsiderationMediationPartyPoliticOrgDto();
        sheet123.setPageTotal(103L);
        sheet123.setPartyName("パーティ名称14");
        sheet123.setSortNo("9");

        Row070812MediationDto row123 = new Row070812MediationDto();

        // 連番
        row123.setIchirenNo(1);
        // 名前
        row123.setName("名称政治団体");
        // 金額
        row123.setKingaku(519L);
        // 発生日
        row123.setAccrualDate("R4/2/13");
        // 斡旋の期間
        row123.setPeriodMediate("1/1-1/26");
        // 住所
        row123.setJuusho("住所政治団体13");
        // 職業
        row123.setShokugyou("職業");
        // 備考
        row123.setBikou("備考");
        // 通し番号
        row123.setTohshibangou(1);
        // 行区分
        row123.setGyoukubun(Short.valueOf("7"));

        sheet123.getList().add(row123);

        allSheetKbn071203Dto.getList().add(sheet123);

        allSheet0712PartyMediationDto.setAllSheetKbn071201Dto(allSheetKbn071201Dto);
        allSheet0712PartyMediationDto.setAllSheetKbn071202Dto(allSheetKbn071202Dto);
        allSheet0712PartyMediationDto.setAllSheetKbn071203Dto(allSheetKbn071203Dto);

        allBookDto.setAllSheet0712PartyMediationDto(allSheet0712PartyMediationDto);

        int size = insertPoliticalOrganizationIncomeAllLogic.practice(documentCode, documentPropertyDto, allBookDto,
                CreateTestPrivilegeDtoUtil.pracitce());
        List<OfferingBalancesheetIncome2025Entity> list = offeringBalancesheetIncome2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetIncomeId(documentCode);

        assertThat(size).isEqualTo(18); // 3,4,5,6,7-1,7-2,7-3,8-1,8-2,8-3,9,10,11-1,11-2,11-3,12-1,12-2,12-3の計18
        assertThat(list.size()).isEqualTo(size); // 想定リスト数と取得リスト数が同じ

        OfferingBalancesheetIncome2025Entity entity0300 = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity0300.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity0300.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity0300.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity0300.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity0300.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity0300.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity0300.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity0300.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity0300.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity0300.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity0300.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity0300.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* その3 */
        // 様式項目
        assertThat(entity0300.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_03);
        assertThat(entity0300.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        assertThat(entity0300.getPageTotal()).isEqualTo(sheet03.getPageTotal());
        assertThat(entity0300.getIchirenNo()).isEqualTo(row03.getIchirenNo());
        assertThat(entity0300.getItemName()).isEqualTo(row03.getJigyoNoShurui());
        assertThat(entity0300.getKingaku()).isEqualTo(row03.getKingaku());
        assertThat(entity0300.getBikou()).isEqualTo(row03.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0300.getSearchWords()).isEqualTo("機関誌発行1");

        /* その4 */
        OfferingBalancesheetIncome2025Entity entity0400 = list.get(1);

        // 様式項目
        assertThat(entity0400.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04);
        assertThat(entity0400.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0400.getPageTotal()).isEqualTo(sheet04.getPageTotal());
        assertThat(entity0400.getIchirenNo()).isEqualTo(row04.getIchirenNo());
        assertThat(entity0400.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04_TEXT);
        assertThat(entity0400.getKingaku()).isEqualTo(row04.getKingaku());
        assertThat(entity0400.getPartnerName()).isEqualTo(row04.getKarisaki());
        assertThat(entity0400.getBikou()).isEqualTo(row04.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0400.getSearchWords()).isEqualTo("借入金親戚のおじさん");

        /* その5 */
        OfferingBalancesheetIncome2025Entity entity0500 = list.get(2);

        // 様式項目
        assertThat(entity0500.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_05);
        assertThat(entity0500.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0500.getIchirenNo()).isEqualTo(row05.getIchirenNo());
        assertThat(entity0500.getKingaku()).isEqualTo(row05.getKingaku());
        assertThat(entity0500.getAccrualDate()).isEqualTo(row05.getAccrualDate());
        assertThat(entity0500.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity0500.getAccrualDate()));
        assertThat(entity0500.getBikou()).isEqualTo(row05.getBikou());
        assertThat(entity0500.getPartnerName()).isEqualTo(row05.getHonbuShibuName());
        assertThat(entity0500.getPartnerJuusho()).isEqualTo(row05.getJimushoJuusho());

        // TODO 関連者

        // 自由検索
        assertThat(entity0500.getSearchWords()).isEqualTo("本部/支部からの交付金本部支部名称事務所住所");

        /* その6 */
        OfferingBalancesheetIncome2025Entity entity0600 = list.get(3);

        // 様式項目
        assertThat(entity0600.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_06);
        assertThat(entity0600.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート記載内容
        assertThat(entity0600.getPageTotal()).isEqualTo(sheet06.getPageTotal());
        assertThat(entity0600.getMimanTotal()).isEqualTo(sheet06.getMimanTotal());
        assertThat(entity0600.getIchirenNo()).isEqualTo(row06.getIchirenNo());
        assertThat(entity0600.getItemName()).isEqualTo(row06.getTekiyou());
        assertThat(entity0600.getKingaku()).isEqualTo(row06.getKingaku());
        assertThat(entity0600.getBikou()).isEqualTo(row06.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0600.getSearchWords()).isEqualTo("摘要");

        /* その7-1 */
        OfferingBalancesheetIncome2025Entity entity0701 = list.get(4);

        // 様式項目
        assertThat(entity0701.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0701.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート項目
        assertThat(entity0701.getPageTotal()).isEqualTo(sheet071.getPageTotal());
        assertThat(entity0701.getSonotaTotal()).isEqualTo(sheet071.getSonotaTotal());

        assertThat(entity0701.getIchirenNo()).isEqualTo(row071.getIchirenNo());
        assertThat(entity0701.getPartnerName()).isEqualTo(row071.getKifusha());
        assertThat(entity0701.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0701.getKingaku()).isEqualTo(row071.getKingaku());
        assertThat(entity0701.getAccrualDate()).isEqualTo(row071.getAccrualDate());
        assertThat(entity0701.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row071.getAccrualDate()));
        assertThat(entity0701.getPartnerJuusho()).isEqualTo(row071.getJusho());
        assertThat(entity0701.getShokugyou()).isEqualTo(row071.getShokugyou());
        assertThat(entity0701.getBikou()).isEqualTo(row071.getBikou());
        assertThat(entity0701.getTohshibangou()).isEqualTo(row071.getTohshibangou());
        assertThat(entity0701.getFlgZeigakuKohjo()).isEqualTo(row071.getFlgZeigakuKohjo());
        assertThat(entity0701.getGyoukubun()).isEqualTo(row071.getGyoukubun());

        // 自由検索
        assertThat(entity0701.getSearchWords()).isEqualTo("寄付寄付者名称個人a住所個人71");

        /* その7-2 */
        OfferingBalancesheetIncome2025Entity entity0702 = list.get(5);

        // 様式項目
        assertThat(entity0702.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0702.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート項目
        assertThat(entity0702.getPageTotal()).isEqualTo(sheet072.getPageTotal());
        assertThat(entity0702.getSonotaTotal()).isEqualTo(sheet072.getSonotaTotal());

        assertThat(entity0702.getIchirenNo()).isEqualTo(row072.getIchirenNo());
        assertThat(entity0702.getPartnerName()).isEqualTo(row072.getKifusha());
        assertThat(entity0702.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0702.getKingaku()).isEqualTo(row072.getKingaku());
        assertThat(entity0702.getAccrualDate()).isEqualTo(row072.getAccrualDate());
        assertThat(entity0702.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row072.getAccrualDate()));
        assertThat(entity0702.getPartnerJuusho()).isEqualTo(row072.getJusho());
        assertThat(entity0702.getShokugyou()).isEqualTo(row072.getShokugyou());
        assertThat(entity0702.getBikou()).isEqualTo(row072.getBikou());
        assertThat(entity0702.getTohshibangou()).isEqualTo(row072.getTohshibangou());
        assertThat(entity0702.getFlgZeigakuKohjo()).isEqualTo(row072.getFlgZeigakuKohjo());
        assertThat(entity0702.getGyoukubun()).isEqualTo(row072.getGyoukubun());

        // 自由検索
        assertThat(entity0702.getSearchWords()).isEqualTo("寄付寄付者名称法人2住所法人72");

        /* その7-3 */
        OfferingBalancesheetIncome2025Entity entity0703 = list.get(6);

        // 様式項目
        assertThat(entity0703.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0703.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート項目
        assertThat(entity0703.getPageTotal()).isEqualTo(sheet073.getPageTotal());
        assertThat(entity0703.getSonotaTotal()).isEqualTo(sheet073.getSonotaTotal());

        assertThat(entity0703.getIchirenNo()).isEqualTo(row073.getIchirenNo());
        assertThat(entity0703.getPartnerName()).isEqualTo(row073.getKifusha());
        assertThat(entity0703.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0703.getKingaku()).isEqualTo(row073.getKingaku());
        assertThat(entity0703.getAccrualDate()).isEqualTo(row073.getAccrualDate());
        assertThat(entity0703.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row073.getAccrualDate()));
        assertThat(entity0703.getPartnerJuusho()).isEqualTo(row073.getJusho());
        assertThat(entity0703.getShokugyou()).isEqualTo(row073.getShokugyou());
        assertThat(entity0703.getBikou()).isEqualTo(row073.getBikou());
        assertThat(entity0703.getTohshibangou()).isEqualTo(row073.getTohshibangou());
        assertThat(entity0703.getFlgZeigakuKohjo()).isEqualTo(row073.getFlgZeigakuKohjo());
        assertThat(entity0703.getGyoukubun()).isEqualTo(row073.getGyoukubun());

        // 自由検索
        assertThat(entity0703.getSearchWords()).isEqualTo("寄付寄付者名称政治団体4住所政治団体73");

        /* その8-1 */
        OfferingBalancesheetIncome2025Entity entity0801 = list.get(7);

        // 様式項目
        assertThat(entity0801.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0801.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート項目
        assertThat(entity0801.getPageTotal()).isEqualTo(sheet081.getPageTotal());
        assertThat(entity0801.getSonotaTotal()).isEqualTo(sheet081.getSonotaTotal());
        assertThat(entity0801.getIchirenNo()).isEqualTo(row081.getIchirenNo());
        assertThat(entity0801.getPartnerName()).isEqualTo(row081.getName());
        assertThat(entity0801.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0801.getKingaku()).isEqualTo(row081.getKingaku());
        assertThat(entity0801.getAccrualDate()).isEqualTo(row081.getAccrualDate());
        assertThat(entity0801.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row081.getAccrualDate()));
        assertThat(entity0801.getPeriodMediate()).isEqualTo(row081.getPeriodMediate());
        assertThat(entity0801.getPartnerJuusho()).isEqualTo(row081.getJuusho());
        assertThat(entity0801.getShokugyou()).isEqualTo(row081.getShokugyou());
        assertThat(entity0801.getBikou()).isEqualTo(row081.getBikou());
        assertThat(entity0801.getTohshibangou()).isEqualTo(row081.getTohshibangou());
        assertThat(entity0801.getGyoukubun()).isEqualTo(row081.getGyoukubun());

        // 自由検索
        assertThat(entity0801.getSearchWords()).isEqualTo("寄付のうちあっせん名称個人5住所個人5");

        /* その8-2 */
        OfferingBalancesheetIncome2025Entity entity0802 = list.get(8);

        // 様式項目
        assertThat(entity0802.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0802.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート項目
        assertThat(entity0802.getPageTotal()).isEqualTo(sheet082.getPageTotal());
        assertThat(entity0802.getSonotaTotal()).isEqualTo(sheet082.getSonotaTotal());
        assertThat(entity0802.getIchirenNo()).isEqualTo(row082.getIchirenNo());
        assertThat(entity0802.getPartnerName()).isEqualTo(row082.getName());
        assertThat(entity0802.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0802.getKingaku()).isEqualTo(row082.getKingaku());
        assertThat(entity0802.getAccrualDate()).isEqualTo(row082.getAccrualDate());
        assertThat(entity0802.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row082.getAccrualDate()));
        assertThat(entity0802.getPeriodMediate()).isEqualTo(row082.getPeriodMediate());
        assertThat(entity0802.getPartnerJuusho()).isEqualTo(row082.getJuusho());
        assertThat(entity0802.getShokugyou()).isEqualTo(row082.getShokugyou());
        assertThat(entity0802.getBikou()).isEqualTo(row082.getBikou());
        assertThat(entity0802.getTohshibangou()).isEqualTo(row082.getTohshibangou());
        assertThat(entity0802.getGyoukubun()).isEqualTo(row082.getGyoukubun());

        // 自由検索
        assertThat(entity0802.getSearchWords()).isEqualTo("寄付のうちあっせん名称法人6住所法人6");

        /* その8-3 */
        OfferingBalancesheetIncome2025Entity entity0803 = list.get(9);

        // 様式項目
        assertThat(entity0803.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0803.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート項目
        assertThat(entity0803.getPageTotal()).isEqualTo(sheet083.getPageTotal());
        assertThat(entity0803.getSonotaTotal()).isEqualTo(sheet083.getSonotaTotal());
        assertThat(entity0803.getIchirenNo()).isEqualTo(row083.getIchirenNo());
        assertThat(entity0803.getPartnerName()).isEqualTo(row083.getName());
        assertThat(entity0803.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0803.getKingaku()).isEqualTo(row083.getKingaku());
        assertThat(entity0803.getAccrualDate()).isEqualTo(row083.getAccrualDate());
        assertThat(entity0803.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row083.getAccrualDate()));
        assertThat(entity0803.getPeriodMediate()).isEqualTo(row083.getPeriodMediate());
        assertThat(entity0803.getPartnerJuusho()).isEqualTo(row083.getJuusho());
        assertThat(entity0803.getShokugyou()).isEqualTo(row083.getShokugyou());
        assertThat(entity0803.getBikou()).isEqualTo(row083.getBikou());
        assertThat(entity0803.getTohshibangou()).isEqualTo(row083.getTohshibangou());
        assertThat(entity0803.getGyoukubun()).isEqualTo(row083.getGyoukubun());

        // 自由検索
        assertThat(entity0803.getSearchWords()).isEqualTo("寄付のうちあっせん名称政治団体7住所政治団体7");

        /* その9 */
        OfferingBalancesheetIncome2025Entity entity0900 = list.get(10);

        // 様式項目
        assertThat(entity0900.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09);
        assertThat(entity0900.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0900.getPageTotal()).isEqualTo(sheet09.getPageTotal());
        assertThat(entity0900.getIchirenNo()).isEqualTo(row09.getIchirenNo());
        assertThat(entity0900.getKaisaiBasho()).isEqualTo(row09.getBasho());
        assertThat(entity0900.getItemName())
                .isEqualTo(row09.getBasho() + "での" + IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09_TEXT);
        assertThat(entity0900.getKingaku()).isEqualTo(row09.getKingaku());
        assertThat(entity0900.getIchirenNo()).isEqualTo(row09.getIchirenNo());
        assertThat(entity0900.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity0900.getAccrualDate()));
        assertThat(entity0900.getBikou()).isEqualTo(row09.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0900.getSearchWords()).isEqualTo("開催場所8での政党匿名寄付");

        /* その10 */
        OfferingBalancesheetIncome2025Entity entity1000 = list.get(11);

        // 様式項目
        assertThat(entity1000.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_10);
        assertThat(entity1000.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity1000.getPageTotal()).isEqualTo(sheet10.getPageTotal());
        assertThat(entity1000.getIchirenNo()).isEqualTo(row10.getIchirenNo());
        assertThat(entity1000.getItemName()).isEqualTo(row10.getPartyName() + "(" + row10.getKaisaiBasho() + ")");
        assertThat(entity1000.getKingaku()).isEqualTo(row10.getKingaku());
        assertThat(entity1000.getShiharaisu()).isEqualTo(row10.getShiharaisu());
        assertThat(entity1000.getAccrualDate()).isEqualTo(row10.getAccrualDate());
        assertThat(entity1000.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row10.getAccrualDate()));
        assertThat(entity1000.getKaisaiBasho()).isEqualTo(row10.getKaisaiBasho());
        assertThat(entity1000.getBikou()).isEqualTo(row10.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity1000.getSearchWords()).isEqualTo("パーティ名称10(開催場所)");

        /* その11-1 */
        OfferingBalancesheetIncome2025Entity entity1101 = list.get(12);

        // 様式項目
        assertThat(entity1101.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1101.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        assertThat(entity1101.getPageTotal()).isEqualTo(sheet111.getPageTotal());
        assertThat(entity1101.getPartyName()).isEqualTo(sheet111.getPartyName());
        assertThat(entity1101.getSortNo()).isEqualTo(sheet111.getSortNo());

        assertThat(entity1101.getIchirenNo()).isEqualTo(row111.getIchirenNo());
        assertThat(entity1101.getPartnerName()).isEqualTo(row111.getKifusha());
        assertThat(entity1101.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet111.getPartyName() + ")");
        assertThat(entity1101.getKingaku()).isEqualTo(row111.getKingaku());
        assertThat(entity1101.getAccrualDate()).isEqualTo(row111.getAccrualDate());
        assertThat(entity1101.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1101.getAccrualDate()));
        assertThat(entity1101.getPartnerJuusho()).isEqualTo(row111.getJusho());
        assertThat(entity1101.getShokugyou()).isEqualTo(row111.getShokugyou());
        assertThat(entity1101.getBikou()).isEqualTo(row111.getBikou());
        assertThat(entity1101.getTohshibangou()).isEqualTo(row111.getTohshibangou());
        assertThat(entity1101.getFlgZeigakuKohjo()).isEqualTo(row111.getFlgZeigakuKohjo());
        assertThat(entity1101.getGyoukubun()).isEqualTo(row111.getGyoukubun());

        // 自由検索
        assertThat(entity1101.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称111)寄付者名称個人住所個人8");

        /* その11-2 */
        OfferingBalancesheetIncome2025Entity entity1102 = list.get(13);

        // 様式項目
        assertThat(entity1102.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1102.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        assertThat(entity1102.getPageTotal()).isEqualTo(sheet112.getPageTotal());
        assertThat(entity1102.getPartyName()).isEqualTo(sheet112.getPartyName());
        assertThat(entity1102.getSortNo()).isEqualTo(sheet112.getSortNo());

        assertThat(entity1102.getIchirenNo()).isEqualTo(row112.getIchirenNo());
        assertThat(entity1102.getPartnerName()).isEqualTo(row112.getKifusha());
        assertThat(entity1102.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet112.getPartyName() + ")");
        assertThat(entity1102.getKingaku()).isEqualTo(row112.getKingaku());
        assertThat(entity1102.getAccrualDate()).isEqualTo(row112.getAccrualDate());
        assertThat(entity1102.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1102.getAccrualDate()));
        assertThat(entity1102.getPartnerJuusho()).isEqualTo(row112.getJusho());
        assertThat(entity1102.getShokugyou()).isEqualTo(row112.getShokugyou());
        assertThat(entity1102.getBikou()).isEqualTo(row112.getBikou());
        assertThat(entity1102.getTohshibangou()).isEqualTo(row112.getTohshibangou());
        assertThat(entity1102.getFlgZeigakuKohjo()).isEqualTo(row112.getFlgZeigakuKohjo());
        assertThat(entity1102.getGyoukubun()).isEqualTo(row112.getGyoukubun());

        // 自由検索
        assertThat(entity1102.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称112)寄付者名称法人住所法人9");

        /* その11-3 */
        OfferingBalancesheetIncome2025Entity entity1103 = list.get(14);

        // 様式項目
        assertThat(entity1103.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1103.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        assertThat(entity1103.getPageTotal()).isEqualTo(sheet113.getPageTotal());
        assertThat(entity1103.getPartyName()).isEqualTo(sheet113.getPartyName());
        assertThat(entity1103.getSortNo()).isEqualTo(sheet113.getSortNo());

        assertThat(entity1103.getIchirenNo()).isEqualTo(row113.getIchirenNo());
        assertThat(entity1103.getPartnerName()).isEqualTo(row113.getKifusha());
        assertThat(entity1103.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet113.getPartyName() + ")");
        assertThat(entity1103.getKingaku()).isEqualTo(row113.getKingaku());
        assertThat(entity1103.getAccrualDate()).isEqualTo(row113.getAccrualDate());
        assertThat(entity1103.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1103.getAccrualDate()));
        assertThat(entity1103.getPartnerJuusho()).isEqualTo(row113.getJusho());
        assertThat(entity1103.getShokugyou()).isEqualTo(row113.getShokugyou());
        assertThat(entity1103.getBikou()).isEqualTo(row113.getBikou());
        assertThat(entity1103.getTohshibangou()).isEqualTo(row113.getTohshibangou());
        assertThat(entity1103.getFlgZeigakuKohjo()).isEqualTo(row113.getFlgZeigakuKohjo());
        assertThat(entity1103.getGyoukubun()).isEqualTo(row113.getGyoukubun());

        // 自由検索
        assertThat(entity1103.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称113)寄付者名称政治団体住所政治団体10");

        /* その12-1 */
        OfferingBalancesheetIncome2025Entity entity1201 = list.get(15);

        // 様式項目
        assertThat(entity1201.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1201.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート記載内容
        assertThat(entity1201.getPageTotal()).isEqualTo(sheet121.getPageTotal());
        assertThat(entity1201.getPartyName()).isEqualTo(sheet121.getPartyName());
        assertThat(entity1201.getSortNo()).isEqualTo(sheet121.getSortNo());

        assertThat(entity1201.getIchirenNo()).isEqualTo(row121.getIchirenNo());
        assertThat(entity1201.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet121.getPartyName() + ")");
        assertThat(entity1201.getPartnerName()).isEqualTo(row121.getName());
        assertThat(entity1201.getKingaku()).isEqualTo(row121.getKingaku());
        assertThat(entity1201.getAccrualDate()).isEqualTo(row121.getAccrualDate());
        assertThat(entity1201.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1201.getAccrualDate()));
        assertThat(entity1201.getPeriodMediate()).isEqualTo(row121.getPeriodMediate());
        assertThat(entity1201.getPartnerJuusho()).isEqualTo(row121.getJuusho());
        assertThat(entity1201.getShokugyou()).isEqualTo(row121.getShokugyou());
        assertThat(entity1201.getBikou()).isEqualTo(row121.getBikou());
        assertThat(entity1201.getTohshibangou()).isEqualTo(row121.getTohshibangou());
        assertThat(entity1201.getGyoukubun()).isEqualTo(row121.getGyoukubun());

        // 自由検索
        assertThat(entity1201.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称11)名称個人住所個人11");

        /* その12-2 */
        OfferingBalancesheetIncome2025Entity entity1202 = list.get(16);

        // 様式項目
        assertThat(entity1202.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1202.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート記載内容
        assertThat(entity1202.getPageTotal()).isEqualTo(sheet122.getPageTotal());
        assertThat(entity1202.getPartyName()).isEqualTo(sheet122.getPartyName());
        assertThat(entity1202.getSortNo()).isEqualTo(sheet122.getSortNo());

        assertThat(entity1202.getIchirenNo()).isEqualTo(row122.getIchirenNo());
        assertThat(entity1202.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet122.getPartyName() + ")");
        assertThat(entity1202.getPartnerName()).isEqualTo(row122.getName());
        assertThat(entity1202.getKingaku()).isEqualTo(row122.getKingaku());
        assertThat(entity1202.getAccrualDate()).isEqualTo(row122.getAccrualDate());
        assertThat(entity1202.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1202.getAccrualDate()));
        assertThat(entity1202.getPeriodMediate()).isEqualTo(row122.getPeriodMediate());
        assertThat(entity1202.getPartnerJuusho()).isEqualTo(row122.getJuusho());
        assertThat(entity1202.getShokugyou()).isEqualTo(row122.getShokugyou());
        assertThat(entity1202.getBikou()).isEqualTo(row122.getBikou());
        assertThat(entity1202.getTohshibangou()).isEqualTo(row122.getTohshibangou());
        assertThat(entity1202.getGyoukubun()).isEqualTo(row122.getGyoukubun());

        // 自由検索
        assertThat(entity1202.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称12)名称法人住所法人12");

        /* その12-3 */
        OfferingBalancesheetIncome2025Entity entity1203 = list.get(17);

        // 様式項目
        assertThat(entity1203.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1203.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート記載内容
        assertThat(entity1203.getPageTotal()).isEqualTo(sheet123.getPageTotal());
        assertThat(entity1203.getPartyName()).isEqualTo(sheet123.getPartyName());
        assertThat(entity1203.getSortNo()).isEqualTo(sheet123.getSortNo());

        assertThat(entity1203.getIchirenNo()).isEqualTo(row123.getIchirenNo());
        assertThat(entity1203.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet123.getPartyName() + ")");
        assertThat(entity1203.getPartnerName()).isEqualTo(row123.getName());
        assertThat(entity1203.getKingaku()).isEqualTo(row123.getKingaku());
        assertThat(entity1203.getAccrualDate()).isEqualTo(row123.getAccrualDate());
        assertThat(entity1203.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1203.getAccrualDate()));
        assertThat(entity1203.getPeriodMediate()).isEqualTo(row123.getPeriodMediate());
        assertThat(entity1203.getPartnerJuusho()).isEqualTo(row123.getJuusho());
        assertThat(entity1203.getShokugyou()).isEqualTo(row123.getShokugyou());
        assertThat(entity1203.getBikou()).isEqualTo(row123.getBikou());
        assertThat(entity1203.getTohshibangou()).isEqualTo(row123.getTohshibangou());
        assertThat(entity1203.getGyoukubun()).isEqualTo(row123.getGyoukubun());

        // 自由検索
        assertThat(entity1203.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称14)名称政治団体住所政治団体13");

    }

    
    
    
    
    
    
    @Test
    @Transactional
    void testPractice2024() { // NOPMD

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

        AllBookDto allBookDto = new AllBookDto();

        allBookDto.setAllSheet0703JournalAndOtherDto(new AllSheet0703JournalAndOtherDto());
        allBookDto.setAllSheet0704BorrowedMoneyDto(new AllSheet0704BorrowedMoneyDto());
        allBookDto.setAllSheet0705IncomeRelatedToGrantsDto(new AllSheet0705IncomeRelatedToGrantsDto());
        allBookDto.setAllSheet0706OtherIncomeDto(new AllSheet0706OtherIncomeDto());
        allBookDto.setAllSheet0709AnonymousInPoliticalPartyDto(new AllSheet0709AnonymousInPoliticalPartyDto());
        allBookDto.setAllSheet0710SpecificPartyDto(new AllSheet0710SpecificPartyDto());

        /* その3 */
        Sheet070300JournalAndOtherDto sheet03 = new Sheet070300JournalAndOtherDto();

        Row070300JournalAndOtherDto row03 = new Row070300JournalAndOtherDto();
        row03.setIchirenNo(1);
        row03.setJigyoNoShurui("機関誌発行");
        row03.setKingaku(301L);
        row03.setBikou("備考");

        sheet03.getList().add(row03);

        allBookDto.getAllSheet0703JournalAndOtherDto().setSheet070300JournalAndOtherDto(sheet03);

        /* その4 */

        Sheet070400BorrowedMoneyDto sheet04 = new Sheet070400BorrowedMoneyDto();
        sheet04.setPageTotal(300L);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070400BorrowedMoneyDto row04 = new Row070400BorrowedMoneyDto();

        // 連番
        row04.setIchirenNo(1);
        // 借り先/
        row04.setKarisaki("親戚のおじさん");
        // 金額
        row04.setKingaku(400L);
        // 備考
        row04.setBikou("備考");

        sheet04.getList().add(row04);

        allBookDto.getAllSheet0704BorrowedMoneyDto().setSheet070400BorrowedMoneyDto(sheet04);

        /* その5 */

        Sheet070500IncomeRelatedToGrantsDto sheet05 = new Sheet070500IncomeRelatedToGrantsDto();
        sheet05.setPageTotal(508L);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070500IncomeRelatedToGrantsDto row05 = new Row070500IncomeRelatedToGrantsDto();

        row05.setIchirenNo(1);
        row05.setHonbuShibuName("本部支部名称");
        row05.setKingaku(506L);
        row05.setAccrualDate("R4/2/9");
        row05.setJimushoJuusho("事務所住所");
        row05.setBikou("備考");

        sheet05.getList().add(row05);

        allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().setSheet070500IncomeRelatedToGrantsDto(sheet05);

        /* その6 */

        Sheet070600OtherIncomeDto sheet06 = new Sheet070600OtherIncomeDto();
        sheet06.setPageTotal(300L);
        sheet06.setMimanTotal("");

        Row070600OtherIncomeDto row06 = new Row070600OtherIncomeDto();
        // 連番
        row06.setIchirenNo(1);
        // 摘要
        row06.setTekiyou("摘要");
        // 金額
        row06.setKingaku(400L);
        // 備考
        row06.setBikou("備考");
        sheet06.getList().add(row06);

        allBookDto.getAllSheet0706OtherIncomeDto().setSheet070600OtherIncomeDto(sheet06);

        /* その7 */

        // その1
        AllSheetKbn070701Dto allSheetKbn070701Dto = new AllSheetKbn070701Dto();

        Sheet070701DonatePersonDto sheet071 = new Sheet070701DonatePersonDto();
        sheet071.setPageTotal(101L);
        sheet071.setSonotaTotal("201");

        Row070711DonateDto row071 = new Row070711DonateDto();

        // 連番 */
        row071.setIchirenNo(1);
        // 寄付者の名前 */
        row071.setKifusha("寄付者名称個人");
        // 金額 */
        row071.setKingaku(301L);
        // 発生日 */
        row071.setAccrualDate("R4/2/11");
        // 住所 */
        row071.setJusho("住所個人71");
        // 職業 */
        row071.setShokugyou("職業");
        // 備考 */
        row071.setBikou("備考");
        // 通し番号 */
        row071.setTohshibangou(1);
        // 税額控除フラグ */
        row071.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分 */
        row071.setGyoukubun(Short.valueOf("8"));

        sheet071.getList().add(row071);

        allSheetKbn070701Dto.setSheet070701DonatePersonDto(sheet071);

        // その2
        AllSheetKbn070702Dto allSheetKbn070702Dto = new AllSheetKbn070702Dto();

        Sheet070702DonateGroupDto sheet072 = new Sheet070702DonateGroupDto();
        sheet072.setPageTotal(102L);
        sheet072.setSonotaTotal("202");

        Row070711DonateDto row072 = new Row070711DonateDto();

        // 連番
        row072.setIchirenNo(1);
        // 寄付者の名前 */
        row072.setKifusha("寄付者名称法人");
        // 金額
        row072.setKingaku(516L);
        // 発生日
        row072.setAccrualDate("R4/2/13");
        // 住所
        row072.setJusho("住所法人72");
        // 職業
        row072.setShokugyou("職業");
        // 備考
        row072.setBikou("備考");
        // 通し番号
        row072.setTohshibangou(1);
        // 税額控除フラグ
        row072.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row072.setGyoukubun(Short.valueOf("8"));

        sheet072.getList().add(row072);

        allSheetKbn070702Dto.setSheet070702DonateGroupDto(sheet072);

        // その3
        AllSheetKbn070703Dto allSheetKbn070703Dto = new AllSheetKbn070703Dto();

        Sheet070703DonatePoliticOrgDto sheet073 = new Sheet070703DonatePoliticOrgDto();
        sheet073.setPageTotal(103L);
        sheet073.setSonotaTotal("203");

        Row070711DonateDto row073 = new Row070711DonateDto();

        // 連番 */
        row073.setIchirenNo(1);
        // 寄付者の名前 */
        row073.setKifusha("寄付者名称政治団体");
        // 金額 */
        row073.setKingaku(529L);
        // 発生日 */
        row073.setAccrualDate("R4/2/3");
        // 住所 */
        row073.setJusho("住所政治団体73");
        // 職業 */
        row073.setShokugyou("職業");
        // 備考 */
        row073.setBikou("備考");
        // 通し番号 */
        row073.setTohshibangou(1);
        // 税額控除フラグ */
        row073.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分 */
        row073.setGyoukubun(Short.valueOf("8"));

        sheet073.getList().add(row073);

        allSheetKbn070703Dto.setSheet070703DonatePoliticOrgDto(sheet073);

        AllSheet0707DonateDto allSheet0707DonateDto = new AllSheet0707DonateDto();
        allSheet0707DonateDto.setAllSheetKbn070701Dto(allSheetKbn070701Dto);
        allSheet0707DonateDto.setAllSheetKbn070702Dto(allSheetKbn070702Dto);
        allSheet0707DonateDto.setAllSheetKbn070703Dto(allSheetKbn070703Dto);

        allBookDto.setAllSheet0707DonateDto(allSheet0707DonateDto);

        /* その8 */

        // その1
        AllSheetKbn070801Dto allSheetKbn070801Dto = new AllSheetKbn070801Dto();

        Sheet070801MediationPersonDto sheet081 = new Sheet070801MediationPersonDto();
        sheet081.setPageTotal(101L);
        sheet081.setSonotaTotal("201");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row081 = new Row070812MediationDto();

        /// ** 連番 */
        row081.setIchirenNo(1);
        /// ** 名前 */
        row081.setName("名称個人");
        /// ** 金額 */
        row081.setKingaku(301L);
        /// ** 発生日 */
        row081.setAccrualDate("R4/2/1");
        /// ** 斡旋の期間 */
        row081.setPeriodMediate("1/1-1/21");
        /// ** 住所 */
        row081.setJuusho("住所個人");
        /// ** 職業 */
        row081.setShokugyou("職業");
        /// ** 備考 */
        row081.setBikou("備考");
        /// ** 通し番号 */
        row081.setTohshibangou(1);
        /// ** 行区分 */
        row081.setGyoukubun(Short.valueOf("7"));

        sheet081.getList().add(row081);

        allSheetKbn070801Dto.setSheet070801MediationPersonDto(sheet081);

        // その2
        AllSheetKbn070802Dto allSheetKbn070802Dto = new AllSheetKbn070802Dto();

        Sheet070802MediationGroupDto sheet082 = new Sheet070802MediationGroupDto();
        sheet082.setPageTotal(102L);
        sheet082.setSonotaTotal("202");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row082 = new Row070812MediationDto();

        /// ** 連番 */
        row082.setIchirenNo(1);
        /// ** 名前 */
        row082.setName("名称法人");
        /// ** 金額 */
        row082.setKingaku(303L);
        /// ** 発生日 */
        row082.setAccrualDate("R4/2/21");
        /// ** 斡旋の期間 */
        row082.setPeriodMediate("1/1-1/22");
        /// ** 住所 */
        row082.setJuusho("住所法人");
        /// ** 職業 */
        row082.setShokugyou("職業");
        /// ** 備考 */
        row082.setBikou("備考");
        /// ** 通し番号 */
        row082.setTohshibangou(1);
        /// ** 行区分 */
        row082.setGyoukubun(Short.valueOf("7"));

        sheet082.getList().add(row082);

        allSheetKbn070802Dto.setSheet070802MediationGroupDto(sheet082);

        // その3
        AllSheetKbn070803Dto allSheetKbn070803Dto = new AllSheetKbn070803Dto();

        Sheet070803MediationPoliticOrgDto sheet083 = new Sheet070803MediationPoliticOrgDto();
        sheet083.setPageTotal(103L);
        sheet083.setSonotaTotal("303");

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row070812MediationDto row083 = new Row070812MediationDto();

        /// ** 連番 */
        row083.setIchirenNo(1);
        /// ** 名前 */
        row083.setName("名称政治団体");
        /// ** 金額 */
        row083.setKingaku(303L);
        /// ** 発生日 */
        row083.setAccrualDate("R4/2/3");
        /// ** 斡旋の期間 */
        row083.setPeriodMediate("1/1-1/23");
        /// ** 住所 */
        row083.setJuusho("住所政治団体");
        /// ** 職業 */
        row083.setShokugyou("職業");
        /// ** 備考 */
        row083.setBikou("備考");
        /// ** 通し番号 */
        row083.setTohshibangou(1);
        /// ** 行区分 */
        row083.setGyoukubun(Short.valueOf("7"));

        sheet083.getList().add(row083);

        allSheetKbn070803Dto.setSheet070803MediationPoliticOrgDto(sheet083);

        AllSheet0708MediationDto allSheet0708MediationDto = new AllSheet0708MediationDto();

        allSheet0708MediationDto.setAllSheetKbn070801Dto(allSheetKbn070801Dto);
        allSheet0708MediationDto.setAllSheetKbn070802Dto(allSheetKbn070802Dto);
        allSheet0708MediationDto.setAllSheetKbn070803Dto(allSheetKbn070803Dto);

        allBookDto.setAllSheet0708MediationDto(allSheet0708MediationDto);

        /* その9 */

        Sheet070900AnonymousInPoliticalPartyDto sheet09 = new Sheet070900AnonymousInPoliticalPartyDto();
        sheet09.setPageTotal(101L);

        Row070900AnonymousInPoliticalPartyDto row09 = new Row070900AnonymousInPoliticalPartyDto();

        // 連番
        row09.setIchirenNo(1);
        // 場所
        row09.setBasho("開催場所");
        // 金額
        row09.setKingaku(518L);
        // 発生日
        row09.setAccrualDate("R4/3/1");
        // 備考
        row09.setBikou("備考");

        sheet09.getList().add(row09);

        allBookDto.getAllSheet0709AnonymousInPoliticalPartyDto().setSheet070900AnonymousInPoliticalPartyDto(sheet09);

        /* その10 */
        Sheet071000SpecificPartyDto sheet10 = new Sheet071000SpecificPartyDto();
        sheet10.setPageTotal(101L);

        Row071000SpecificPartyDto row10 = new Row071000SpecificPartyDto();

        // 連番
        row10.setIchirenNo(1);
        // パーティ名称
        row10.setPartyName("パーティ名称10");
        // 金額
        row10.setKingaku(102L);
        // 支払い数
        row10.setShiharaisu(3);
        // 開催日
        row10.setAccrualDate("R4/2/16");
        // 開催場所
        row10.setKaisaiBasho("開催場所");
        // 備考
        row10.setBikou("備考");

        sheet10.getList().add(row10);

        allBookDto.getAllSheet0710SpecificPartyDto().setSheet071000SpecificPartyDto(sheet10);

        /* その11 */

        AllSheet0711ConsiderationPartyDto allSheet0711ConsiderationPartyDto = new AllSheet0711ConsiderationPartyDto();

        // その1
        AllSheetKbn071101Dto allSheetKbn071101Dto = new AllSheetKbn071101Dto();

        Sheet071101ConsiderationPartyPerspnalDto sheet111 = new Sheet071101ConsiderationPartyPerspnalDto();
        sheet111.setPageTotal(101L);
        sheet111.setPartyName("パーティ名称111");
        sheet111.setSortNo("9");

        Row070711DonateDto row111 = new Row070711DonateDto();

        // 連番
        row111.setIchirenNo(1);
        // 寄付者の名前
        row111.setKifusha("寄付者名称個人");
        // 金額
        row111.setKingaku(201L);
        // 発生日
        row111.setAccrualDate("R4/2/1");
        // 住所
        row111.setJusho("住所個人");
        // 職業
        row111.setShokugyou("職業");
        // 備考
        row111.setBikou("備考");
        // 通し番号
        row111.setTohshibangou(1);
        // 税額控除フラグ
        row111.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row111.setGyoukubun(Short.valueOf("8"));

        sheet111.getList().add(row111);

        allSheetKbn071101Dto.getList().add(sheet111);

        // その2
        AllSheetKbn071102Dto allSheetKbn071102Dto = new AllSheetKbn071102Dto();

        Sheet071102ConsiderationPartyGroupDto sheet112 = new Sheet071102ConsiderationPartyGroupDto();
        sheet112.setPageTotal(102L);
        sheet112.setPartyName("パーティ名称112");
        sheet112.setSortNo("9");

        Row070711DonateDto row112 = new Row070711DonateDto();

        // 連番
        row112.setIchirenNo(1);
        // 寄付者の名前
        row112.setKifusha("寄付者名称法人");
        // 金額
        row112.setKingaku(202L);
        // 発生日
        row112.setAccrualDate("R4/2/2");
        // 住所
        row112.setJusho("住所法人");
        // 職業
        row112.setShokugyou("職業");
        // 備考
        row112.setBikou("備考");
        // 通し番号
        row112.setTohshibangou(1);
        // 税額控除フラグ
        row112.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row112.setGyoukubun(Short.valueOf("8"));

        sheet112.getList().add(row112);

        allSheetKbn071102Dto.getList().add(sheet112);

        // その3
        AllSheetKbn071103Dto allSheetKbn071103Dto = new AllSheetKbn071103Dto();

        Sheet071103ConsiderationPartyPoliticOrgDto sheet113 = new Sheet071103ConsiderationPartyPoliticOrgDto();
        sheet113.setPageTotal(103L);
        sheet113.setPartyName("パーティ名称113");
        sheet113.setSortNo("9");

        Row070711DonateDto row113 = new Row070711DonateDto();

        // 連番
        row113.setIchirenNo(1);
        // 寄付者の名前
        row113.setKifusha("寄付者名称政治団体");
        // 金額
        row113.setKingaku(203L);
        // 発生日
        row113.setAccrualDate("R4/2/3");
        // 住所
        row113.setJusho("住所政治団体");
        // 職業
        row113.setShokugyou("職業");
        // 備考
        row113.setBikou("備考");
        // 通し番号
        row113.setTohshibangou(1);
        // 税額控除フラグ
        row113.setFlgZeigakuKohjo(Short.valueOf("0"));
        // 行区分
        row113.setGyoukubun(Short.valueOf("8"));

        sheet113.getList().add(row113);

        allSheetKbn071103Dto.getList().add(sheet113);

        allSheet0711ConsiderationPartyDto.setAllSheetKbn071101Dto(allSheetKbn071101Dto);
        allSheet0711ConsiderationPartyDto.setAllSheetKbn071102Dto(allSheetKbn071102Dto);
        allSheet0711ConsiderationPartyDto.setAllSheetKbn071103Dto(allSheetKbn071103Dto);

        allBookDto.setAllSheet0711ConsiderationPartyDto(allSheet0711ConsiderationPartyDto);

        /* その12 */

        AllSheet0712PartyMediationDto allSheet0712PartyMediationDto = new AllSheet0712PartyMediationDto();

        // その1
        AllSheetKbn071201Dto allSheetKbn071201Dto = new AllSheetKbn071201Dto();

        Sheet071201ConsiderationMediationPartyPersonalDto sheet121 = new Sheet071201ConsiderationMediationPartyPersonalDto();
        sheet121.setPageTotal(101L);
        sheet121.setPartyName("パーティ名称");
        sheet121.setSortNo("9");

        Row070812MediationDto row121 = new Row070812MediationDto();

        // 連番
        row121.setIchirenNo(1);
        // 名前
        row121.setName("名称個人");
        // 金額
        row121.setKingaku(201L);
        // 発生日
        row121.setAccrualDate("R4/2/1");
        // 斡旋の期間
        row121.setPeriodMediate("1/1-1/31");
        // 住所
        row121.setJuusho("住所個人");
        // 職業
        row121.setShokugyou("職業");
        // 備考
        row121.setBikou("備考");
        // 通し番号
        row121.setTohshibangou(1);
        // 行区分
        row121.setGyoukubun(Short.valueOf("7"));

        sheet121.getList().add(row121);

        allSheetKbn071201Dto.getList().add(sheet121);

        // その2
        AllSheetKbn071202Dto allSheetKbn071202Dto = new AllSheetKbn071202Dto();

        Sheet071202ConsiderationMediationPartyGroupDto sheet122 = new Sheet071202ConsiderationMediationPartyGroupDto();
        sheet122.setPageTotal(102L);
        sheet122.setPartyName("パーティ名称");
        sheet122.setSortNo("9");

        Row070812MediationDto row122 = new Row070812MediationDto();

        // 連番
        row122.setIchirenNo(1);
        // 名前
        row122.setName("名称法人");
        // 金額
        row122.setKingaku(202L);
        // 発生日
        row122.setAccrualDate("R4/2/2");
        // 斡旋の期間
        row122.setPeriodMediate("1/1-1/31");
        // 住所
        row122.setJuusho("住所法人");
        // 職業
        row122.setShokugyou("職業");
        // 備考
        row122.setBikou("備考");
        // 通し番号
        row122.setTohshibangou(1);
        // 行区分
        row122.setGyoukubun(Short.valueOf("7"));

        sheet122.getList().add(row122);

        allSheetKbn071202Dto.getList().add(sheet122);

        // その3
        AllSheetKbn071203Dto allSheetKbn071203Dto = new AllSheetKbn071203Dto();

        Sheet071203ConsiderationMediationPartyPoliticOrgDto sheet123 = new Sheet071203ConsiderationMediationPartyPoliticOrgDto();
        sheet123.setPageTotal(103L);
        sheet123.setPartyName("パーティ名称");
        sheet123.setSortNo("9");

        Row070812MediationDto row123 = new Row070812MediationDto();

        // 連番
        row123.setIchirenNo(1);
        // 名前
        row123.setName("名称政治団体");
        // 金額
        row123.setKingaku(519L);
        // 発生日
        row123.setAccrualDate("R4/2/2");
        // 斡旋の期間
        row123.setPeriodMediate("1/1-1/31");
        // 住所
        row123.setJuusho("住所政治団体");
        // 職業
        row123.setShokugyou("職業");
        // 備考
        row123.setBikou("備考");
        // 通し番号
        row123.setTohshibangou(1);
        // 行区分
        row123.setGyoukubun(Short.valueOf("7"));

        sheet123.getList().add(row123);

        allSheetKbn071203Dto.getList().add(sheet123);

        allSheet0712PartyMediationDto.setAllSheetKbn071201Dto(allSheetKbn071201Dto);
        allSheet0712PartyMediationDto.setAllSheetKbn071202Dto(allSheetKbn071202Dto);
        allSheet0712PartyMediationDto.setAllSheetKbn071203Dto(allSheetKbn071203Dto);

        allBookDto.setAllSheet0712PartyMediationDto(allSheet0712PartyMediationDto);

        int size = insertPoliticalOrganizationIncomeAllLogic.practice(documentCode, documentPropertyDto, allBookDto,
                CreateTestPrivilegeDtoUtil.pracitce());
        List<OfferingBalancesheetIncome2024Entity> list = offeringBalancesheetIncome2024Repository
                .findByDocumentCodeOrderByOfferingBalancesheetIncomeId(documentCode);

        assertThat(size).isEqualTo(18); // 3,4,5,6,7-1,7-2,7-3,8-1,8-2,8-3,9,10,11-1,11-2,11-3,12-1,12-2,12-3の計18
        assertThat(list.size()).isEqualTo(size); // 想定リスト数と取得リスト数が同じ

        OfferingBalancesheetIncome2024Entity entity0300 = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity0300.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity0300.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entity0300.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity0300.getPoliticalOrganizationId()).isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity0300.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity0300.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity0300.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity0300.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity0300.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity0300.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity0300.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity0300.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* その3 */
        // 様式項目
        assertThat(entity0300.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_03);
        assertThat(entity0300.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        assertThat(entity0300.getPageTotal()).isEqualTo(sheet03.getPageTotal());
        assertThat(entity0300.getIchirenNo()).isEqualTo(row03.getIchirenNo());
        assertThat(entity0300.getItemName()).isEqualTo(row03.getJigyoNoShurui());
        assertThat(entity0300.getKingaku()).isEqualTo(row03.getKingaku());
        assertThat(entity0300.getBikou()).isEqualTo(row03.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0300.getSearchWords()).isEqualTo("機関誌発行");

        /* その4 */
        OfferingBalancesheetIncome2024Entity entity0400 = list.get(1);

        // 様式項目
        assertThat(entity0400.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04);
        assertThat(entity0400.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0400.getPageTotal()).isEqualTo(sheet04.getPageTotal());
        assertThat(entity0400.getIchirenNo()).isEqualTo(row04.getIchirenNo());
        assertThat(entity0400.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04_TEXT);
        assertThat(entity0400.getKingaku()).isEqualTo(row04.getKingaku());
        assertThat(entity0400.getPartnerName()).isEqualTo(row04.getKarisaki());
        assertThat(entity0400.getBikou()).isEqualTo(row04.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0400.getSearchWords()).isEqualTo("借入金親戚のおじさん");

        /* その5 */
        OfferingBalancesheetIncome2024Entity entity0500 = list.get(2);

        // 様式項目
        assertThat(entity0500.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_05);
        assertThat(entity0500.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0500.getIchirenNo()).isEqualTo(row05.getIchirenNo());
        assertThat(entity0500.getKingaku()).isEqualTo(row05.getKingaku());
        assertThat(entity0500.getAccrualDate()).isEqualTo(row05.getAccrualDate());
        assertThat(entity0500.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity0500.getAccrualDate()));
        assertThat(entity0500.getBikou()).isEqualTo(row05.getBikou());
        assertThat(entity0500.getPartnerName()).isEqualTo(row05.getHonbuShibuName());
        assertThat(entity0500.getPartnerJuusho()).isEqualTo(row05.getJimushoJuusho());

        // TODO 関連者

        // 自由検索
        assertThat(entity0500.getSearchWords()).isEqualTo("本部/支部からの交付金本部支部名称事務所住所");

        /* その6 */
        OfferingBalancesheetIncome2024Entity entity0600 = list.get(3);

        // 様式項目
        assertThat(entity0600.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_06);
        assertThat(entity0600.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート記載内容
        assertThat(entity0600.getPageTotal()).isEqualTo(sheet06.getPageTotal());
        assertThat(entity0600.getMimanTotal()).isEqualTo(sheet06.getMimanTotal());
        assertThat(entity0600.getIchirenNo()).isEqualTo(row06.getIchirenNo());
        assertThat(entity0600.getItemName()).isEqualTo(row06.getTekiyou());
        assertThat(entity0600.getKingaku()).isEqualTo(row06.getKingaku());
        assertThat(entity0600.getBikou()).isEqualTo(row06.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0600.getSearchWords()).isEqualTo("摘要");

        /* その7-1 */
        OfferingBalancesheetIncome2024Entity entity0701 = list.get(4);

        // 様式項目
        assertThat(entity0701.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0701.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート項目
        assertThat(entity0701.getPageTotal()).isEqualTo(sheet071.getPageTotal());
        assertThat(entity0701.getSonotaTotal()).isEqualTo(sheet071.getSonotaTotal());

        assertThat(entity0701.getIchirenNo()).isEqualTo(row071.getIchirenNo());
        assertThat(entity0701.getPartnerName()).isEqualTo(row071.getKifusha());
        assertThat(entity0701.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0701.getKingaku()).isEqualTo(row071.getKingaku());
        assertThat(entity0701.getAccrualDate()).isEqualTo(row071.getAccrualDate());
        assertThat(entity0701.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row071.getAccrualDate()));
        assertThat(entity0701.getPartnerJuusho()).isEqualTo(row071.getJusho());
        assertThat(entity0701.getShokugyou()).isEqualTo(row071.getShokugyou());
        assertThat(entity0701.getBikou()).isEqualTo(row071.getBikou());
        assertThat(entity0701.getTohshibangou()).isEqualTo(row071.getTohshibangou());
        assertThat(entity0701.getFlgZeigakuKohjo()).isEqualTo(row071.getFlgZeigakuKohjo());
        assertThat(entity0701.getGyoukubun()).isEqualTo(row071.getGyoukubun());

        // 自由検索
        assertThat(entity0701.getSearchWords()).isEqualTo("寄付寄付者名称個人住所個人71");

        /* その7-2 */
        OfferingBalancesheetIncome2024Entity entity0702 = list.get(5);

        // 様式項目
        assertThat(entity0702.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0702.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート項目
        assertThat(entity0702.getPageTotal()).isEqualTo(sheet072.getPageTotal());
        assertThat(entity0702.getSonotaTotal()).isEqualTo(sheet072.getSonotaTotal());

        assertThat(entity0702.getIchirenNo()).isEqualTo(row072.getIchirenNo());
        assertThat(entity0702.getPartnerName()).isEqualTo(row072.getKifusha());
        assertThat(entity0702.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0702.getKingaku()).isEqualTo(row072.getKingaku());
        assertThat(entity0702.getAccrualDate()).isEqualTo(row072.getAccrualDate());
        assertThat(entity0702.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row072.getAccrualDate()));
        assertThat(entity0702.getPartnerJuusho()).isEqualTo(row072.getJusho());
        assertThat(entity0702.getShokugyou()).isEqualTo(row072.getShokugyou());
        assertThat(entity0702.getBikou()).isEqualTo(row072.getBikou());
        assertThat(entity0702.getTohshibangou()).isEqualTo(row072.getTohshibangou());
        assertThat(entity0702.getFlgZeigakuKohjo()).isEqualTo(row072.getFlgZeigakuKohjo());
        assertThat(entity0702.getGyoukubun()).isEqualTo(row072.getGyoukubun());

        // 自由検索
        assertThat(entity0702.getSearchWords()).isEqualTo("寄付寄付者名称法人住所法人72");

        /* その7-3 */
        OfferingBalancesheetIncome2024Entity entity0703 = list.get(6);

        // 様式項目
        assertThat(entity0703.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        assertThat(entity0703.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート項目
        assertThat(entity0703.getPageTotal()).isEqualTo(sheet073.getPageTotal());
        assertThat(entity0703.getSonotaTotal()).isEqualTo(sheet073.getSonotaTotal());

        assertThat(entity0703.getIchirenNo()).isEqualTo(row073.getIchirenNo());
        assertThat(entity0703.getPartnerName()).isEqualTo(row073.getKifusha());
        assertThat(entity0703.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT);
        assertThat(entity0703.getKingaku()).isEqualTo(row073.getKingaku());
        assertThat(entity0703.getAccrualDate()).isEqualTo(row073.getAccrualDate());
        assertThat(entity0703.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row073.getAccrualDate()));
        assertThat(entity0703.getPartnerJuusho()).isEqualTo(row073.getJusho());
        assertThat(entity0703.getShokugyou()).isEqualTo(row073.getShokugyou());
        assertThat(entity0703.getBikou()).isEqualTo(row073.getBikou());
        assertThat(entity0703.getTohshibangou()).isEqualTo(row073.getTohshibangou());
        assertThat(entity0703.getFlgZeigakuKohjo()).isEqualTo(row073.getFlgZeigakuKohjo());
        assertThat(entity0703.getGyoukubun()).isEqualTo(row073.getGyoukubun());

        // 自由検索
        assertThat(entity0703.getSearchWords()).isEqualTo("寄付寄付者名称政治団体住所政治団体73");

        /* その8-1 */
        OfferingBalancesheetIncome2024Entity entity0801 = list.get(7);

        // 様式項目
        assertThat(entity0801.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0801.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート項目
        assertThat(entity0801.getPageTotal()).isEqualTo(sheet081.getPageTotal());
        assertThat(entity0801.getSonotaTotal()).isEqualTo(sheet081.getSonotaTotal());
        assertThat(entity0801.getIchirenNo()).isEqualTo(row081.getIchirenNo());
        assertThat(entity0801.getPartnerName()).isEqualTo(row081.getName());
        assertThat(entity0801.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0801.getKingaku()).isEqualTo(row081.getKingaku());
        assertThat(entity0801.getAccrualDate()).isEqualTo(row081.getAccrualDate());
        assertThat(entity0801.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row081.getAccrualDate()));
        assertThat(entity0801.getPeriodMediate()).isEqualTo(row081.getPeriodMediate());
        assertThat(entity0801.getPartnerJuusho()).isEqualTo(row081.getJuusho());
        assertThat(entity0801.getShokugyou()).isEqualTo(row081.getShokugyou());
        assertThat(entity0801.getBikou()).isEqualTo(row081.getBikou());
        assertThat(entity0801.getTohshibangou()).isEqualTo(row081.getTohshibangou());
        assertThat(entity0801.getGyoukubun()).isEqualTo(row081.getGyoukubun());

        // 自由検索
        assertThat(entity0801.getSearchWords()).isEqualTo("寄付のうちあっせん名称個人住所個人");

        /* その8-2 */
        OfferingBalancesheetIncome2024Entity entity0802 = list.get(8);

        // 様式項目
        assertThat(entity0802.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0802.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート項目
        assertThat(entity0802.getPageTotal()).isEqualTo(sheet082.getPageTotal());
        assertThat(entity0802.getSonotaTotal()).isEqualTo(sheet082.getSonotaTotal());
        assertThat(entity0802.getIchirenNo()).isEqualTo(row082.getIchirenNo());
        assertThat(entity0802.getPartnerName()).isEqualTo(row082.getName());
        assertThat(entity0802.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0802.getKingaku()).isEqualTo(row082.getKingaku());
        assertThat(entity0802.getAccrualDate()).isEqualTo(row082.getAccrualDate());
        assertThat(entity0802.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row082.getAccrualDate()));
        assertThat(entity0802.getPeriodMediate()).isEqualTo(row082.getPeriodMediate());
        assertThat(entity0802.getPartnerJuusho()).isEqualTo(row082.getJuusho());
        assertThat(entity0802.getShokugyou()).isEqualTo(row082.getShokugyou());
        assertThat(entity0802.getBikou()).isEqualTo(row082.getBikou());
        assertThat(entity0802.getTohshibangou()).isEqualTo(row082.getTohshibangou());
        assertThat(entity0802.getGyoukubun()).isEqualTo(row082.getGyoukubun());

        // 自由検索
        assertThat(entity0802.getSearchWords()).isEqualTo("寄付のうちあっせん名称法人住所法人");

        /* その8-3 */
        OfferingBalancesheetIncome2024Entity entity0803 = list.get(9);

        // 様式項目
        assertThat(entity0803.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        assertThat(entity0803.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート項目
        assertThat(entity0803.getPageTotal()).isEqualTo(sheet083.getPageTotal());
        assertThat(entity0803.getSonotaTotal()).isEqualTo(sheet083.getSonotaTotal());
        assertThat(entity0803.getIchirenNo()).isEqualTo(row083.getIchirenNo());
        assertThat(entity0803.getPartnerName()).isEqualTo(row083.getName());
        assertThat(entity0803.getItemName()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);
        assertThat(entity0803.getKingaku()).isEqualTo(row083.getKingaku());
        assertThat(entity0803.getAccrualDate()).isEqualTo(row083.getAccrualDate());
        assertThat(entity0803.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row083.getAccrualDate()));
        assertThat(entity0803.getPeriodMediate()).isEqualTo(row083.getPeriodMediate());
        assertThat(entity0803.getPartnerJuusho()).isEqualTo(row083.getJuusho());
        assertThat(entity0803.getShokugyou()).isEqualTo(row083.getShokugyou());
        assertThat(entity0803.getBikou()).isEqualTo(row083.getBikou());
        assertThat(entity0803.getTohshibangou()).isEqualTo(row083.getTohshibangou());
        assertThat(entity0803.getGyoukubun()).isEqualTo(row083.getGyoukubun());

        // 自由検索
        assertThat(entity0803.getSearchWords()).isEqualTo("寄付のうちあっせん名称政治団体住所政治団体");

        /* その9 */
        OfferingBalancesheetIncome2024Entity entity0900 = list.get(10);

        // 様式項目
        assertThat(entity0900.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09);
        assertThat(entity0900.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity0900.getPageTotal()).isEqualTo(sheet09.getPageTotal());
        assertThat(entity0900.getIchirenNo()).isEqualTo(row09.getIchirenNo());
        assertThat(entity0900.getKaisaiBasho()).isEqualTo(row09.getBasho());
        assertThat(entity0900.getItemName())
                .isEqualTo(row09.getBasho() + "での" + IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09_TEXT);
        assertThat(entity0900.getKingaku()).isEqualTo(row09.getKingaku());
        assertThat(entity0900.getIchirenNo()).isEqualTo(row09.getIchirenNo());
        assertThat(entity0900.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity0900.getAccrualDate()));
        assertThat(entity0900.getBikou()).isEqualTo(row09.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity0900.getSearchWords()).isEqualTo("開催場所での政党匿名寄付");

        /* その10 */
        OfferingBalancesheetIncome2024Entity entity1000 = list.get(11);

        // 様式項目
        assertThat(entity1000.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_10);
        assertThat(entity1000.getYoushikiEdaKbn()).isEqualTo(0); // 様式枝区分項目はなし

        // シート・記載内容
        assertThat(entity1000.getPageTotal()).isEqualTo(sheet10.getPageTotal());
        assertThat(entity1000.getIchirenNo()).isEqualTo(row10.getIchirenNo());
        assertThat(entity1000.getItemName()).isEqualTo(row10.getPartyName() + "(" + row10.getKaisaiBasho() + ")");
        assertThat(entity1000.getKingaku()).isEqualTo(row10.getKingaku());
        assertThat(entity1000.getShiharaisu()).isEqualTo(row10.getShiharaisu());
        assertThat(entity1000.getAccrualDate()).isEqualTo(row10.getAccrualDate());
        assertThat(entity1000.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(row10.getAccrualDate()));
        assertThat(entity1000.getKaisaiBasho()).isEqualTo(row10.getKaisaiBasho());
        assertThat(entity1000.getBikou()).isEqualTo(row10.getBikou());

        // TODO 関連者

        // 自由検索
        assertThat(entity1000.getSearchWords()).isEqualTo("パーティ名称10(開催場所)");

        /* その11-1 */
        OfferingBalancesheetIncome2024Entity entity1101 = list.get(12);

        // 様式項目
        assertThat(entity1101.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1101.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        assertThat(entity1101.getPageTotal()).isEqualTo(sheet111.getPageTotal());
        assertThat(entity1101.getPartyName()).isEqualTo(sheet111.getPartyName());
        assertThat(entity1101.getSortNo()).isEqualTo(sheet111.getSortNo());

        assertThat(entity1101.getIchirenNo()).isEqualTo(row111.getIchirenNo());
        assertThat(entity1101.getPartnerName()).isEqualTo(row111.getKifusha());
        assertThat(entity1101.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet111.getPartyName() + ")");
        assertThat(entity1101.getKingaku()).isEqualTo(row111.getKingaku());
        assertThat(entity1101.getAccrualDate()).isEqualTo(row111.getAccrualDate());
        assertThat(entity1101.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1101.getAccrualDate()));
        assertThat(entity1101.getPartnerJuusho()).isEqualTo(row111.getJusho());
        assertThat(entity1101.getShokugyou()).isEqualTo(row111.getShokugyou());
        assertThat(entity1101.getBikou()).isEqualTo(row111.getBikou());
        assertThat(entity1101.getTohshibangou()).isEqualTo(row111.getTohshibangou());
        assertThat(entity1101.getFlgZeigakuKohjo()).isEqualTo(row111.getFlgZeigakuKohjo());
        assertThat(entity1101.getGyoukubun()).isEqualTo(row111.getGyoukubun());

        // 自由検索
        assertThat(entity1101.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称111)寄付者名称個人住所個人");

        /* その11-2 */
        OfferingBalancesheetIncome2024Entity entity1102 = list.get(13);

        // 様式項目
        assertThat(entity1102.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1102.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        assertThat(entity1102.getPageTotal()).isEqualTo(sheet112.getPageTotal());
        assertThat(entity1102.getPartyName()).isEqualTo(sheet112.getPartyName());
        assertThat(entity1102.getSortNo()).isEqualTo(sheet112.getSortNo());

        assertThat(entity1102.getIchirenNo()).isEqualTo(row112.getIchirenNo());
        assertThat(entity1102.getPartnerName()).isEqualTo(row112.getKifusha());
        assertThat(entity1102.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet112.getPartyName() + ")");
        assertThat(entity1102.getKingaku()).isEqualTo(row112.getKingaku());
        assertThat(entity1102.getAccrualDate()).isEqualTo(row112.getAccrualDate());
        assertThat(entity1102.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1102.getAccrualDate()));
        assertThat(entity1102.getPartnerJuusho()).isEqualTo(row112.getJusho());
        assertThat(entity1102.getShokugyou()).isEqualTo(row112.getShokugyou());
        assertThat(entity1102.getBikou()).isEqualTo(row112.getBikou());
        assertThat(entity1102.getTohshibangou()).isEqualTo(row112.getTohshibangou());
        assertThat(entity1102.getFlgZeigakuKohjo()).isEqualTo(row112.getFlgZeigakuKohjo());
        assertThat(entity1102.getGyoukubun()).isEqualTo(row112.getGyoukubun());

        // 自由検索
        assertThat(entity1102.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称112)寄付者名称法人住所法人");

        /* その11-3 */
        OfferingBalancesheetIncome2024Entity entity1103 = list.get(14);

        // 様式項目
        assertThat(entity1103.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        assertThat(entity1103.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        assertThat(entity1103.getPageTotal()).isEqualTo(sheet113.getPageTotal());
        assertThat(entity1103.getPartyName()).isEqualTo(sheet113.getPartyName());
        assertThat(entity1103.getSortNo()).isEqualTo(sheet113.getSortNo());

        assertThat(entity1103.getIchirenNo()).isEqualTo(row113.getIchirenNo());
        assertThat(entity1103.getPartnerName()).isEqualTo(row113.getKifusha());
        assertThat(entity1103.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT + "(" + sheet113.getPartyName() + ")");
        assertThat(entity1103.getKingaku()).isEqualTo(row113.getKingaku());
        assertThat(entity1103.getAccrualDate()).isEqualTo(row113.getAccrualDate());
        assertThat(entity1103.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1103.getAccrualDate()));
        assertThat(entity1103.getPartnerJuusho()).isEqualTo(row113.getJusho());
        assertThat(entity1103.getShokugyou()).isEqualTo(row113.getShokugyou());
        assertThat(entity1103.getBikou()).isEqualTo(row113.getBikou());
        assertThat(entity1103.getTohshibangou()).isEqualTo(row113.getTohshibangou());
        assertThat(entity1103.getFlgZeigakuKohjo()).isEqualTo(row113.getFlgZeigakuKohjo());
        assertThat(entity1103.getGyoukubun()).isEqualTo(row113.getGyoukubun());

        // 自由検索
        assertThat(entity1103.getSearchWords()).isEqualTo("政治資金パーティ(パーティ名称113)寄付者名称政治団体住所政治団体");

        /* その12-1 */
        OfferingBalancesheetIncome2024Entity entity1201 = list.get(15);

        // 様式項目
        assertThat(entity1201.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1201.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);

        // シート記載内容
        assertThat(entity1201.getPageTotal()).isEqualTo(sheet121.getPageTotal());
        assertThat(entity1201.getPartyName()).isEqualTo(sheet121.getPartyName());
        assertThat(entity1201.getSortNo()).isEqualTo(sheet121.getSortNo());

        assertThat(entity1201.getIchirenNo()).isEqualTo(row121.getIchirenNo());
        assertThat(entity1201.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet121.getPartyName() + ")");
        assertThat(entity1201.getPartnerName()).isEqualTo(row121.getName());
        assertThat(entity1201.getKingaku()).isEqualTo(row121.getKingaku());
        assertThat(entity1201.getAccrualDate()).isEqualTo(row121.getAccrualDate());
        assertThat(entity1201.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1201.getAccrualDate()));
        assertThat(entity1201.getPeriodMediate()).isEqualTo(row121.getPeriodMediate());
        assertThat(entity1201.getPartnerJuusho()).isEqualTo(row121.getJuusho());
        assertThat(entity1201.getShokugyou()).isEqualTo(row121.getShokugyou());
        assertThat(entity1201.getBikou()).isEqualTo(row121.getBikou());
        assertThat(entity1201.getTohshibangou()).isEqualTo(row121.getTohshibangou());
        assertThat(entity1201.getGyoukubun()).isEqualTo(row121.getGyoukubun());

        // 自由検索
        assertThat(entity1201.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称)名称個人住所個人");

        /* その12-2 */
        OfferingBalancesheetIncome2024Entity entity1202 = list.get(16);

        // 様式項目
        assertThat(entity1202.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1202.getYoushikiEdaKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);

        // シート記載内容
        assertThat(entity1202.getPageTotal()).isEqualTo(sheet122.getPageTotal());
        assertThat(entity1202.getPartyName()).isEqualTo(sheet122.getPartyName());
        assertThat(entity1202.getSortNo()).isEqualTo(sheet122.getSortNo());

        assertThat(entity1202.getIchirenNo()).isEqualTo(row122.getIchirenNo());
        assertThat(entity1202.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet122.getPartyName() + ")");
        assertThat(entity1202.getPartnerName()).isEqualTo(row122.getName());
        assertThat(entity1202.getKingaku()).isEqualTo(row122.getKingaku());
        assertThat(entity1202.getAccrualDate()).isEqualTo(row122.getAccrualDate());
        assertThat(entity1202.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1202.getAccrualDate()));
        assertThat(entity1202.getPeriodMediate()).isEqualTo(row122.getPeriodMediate());
        assertThat(entity1202.getPartnerJuusho()).isEqualTo(row122.getJuusho());
        assertThat(entity1202.getShokugyou()).isEqualTo(row122.getShokugyou());
        assertThat(entity1202.getBikou()).isEqualTo(row122.getBikou());
        assertThat(entity1202.getTohshibangou()).isEqualTo(row122.getTohshibangou());
        assertThat(entity1202.getGyoukubun()).isEqualTo(row122.getGyoukubun());

        // 自由検索
        assertThat(entity1202.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称)名称法人住所法人");

        /* その12-3 */
        OfferingBalancesheetIncome2024Entity entity1203 = list.get(17);

        // 様式項目
        assertThat(entity1203.getYoushikiKbn()).isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        assertThat(entity1203.getYoushikiEdaKbn())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);

        // シート記載内容
        assertThat(entity1203.getPageTotal()).isEqualTo(sheet123.getPageTotal());
        assertThat(entity1203.getPartyName()).isEqualTo(sheet123.getPartyName());
        assertThat(entity1203.getSortNo()).isEqualTo(sheet123.getSortNo());

        assertThat(entity1203.getIchirenNo()).isEqualTo(row123.getIchirenNo());
        assertThat(entity1203.getItemName())
                .isEqualTo(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + sheet123.getPartyName() + ")");
        assertThat(entity1203.getPartnerName()).isEqualTo(row123.getName());
        assertThat(entity1203.getKingaku()).isEqualTo(row123.getKingaku());
        assertThat(entity1203.getAccrualDate()).isEqualTo(row123.getAccrualDate());
        assertThat(entity1203.getAccrualDateValue())
                .isEqualTo(dateConvertUtil.practiceWarekiToLocalDate(entity1203.getAccrualDate()));
        assertThat(entity1203.getPeriodMediate()).isEqualTo(row123.getPeriodMediate());
        assertThat(entity1203.getPartnerJuusho()).isEqualTo(row123.getJuusho());
        assertThat(entity1203.getShokugyou()).isEqualTo(row123.getShokugyou());
        assertThat(entity1203.getBikou()).isEqualTo(row123.getBikou());
        assertThat(entity1203.getTohshibangou()).isEqualTo(row123.getTohshibangou());
        assertThat(entity1203.getGyoukubun()).isEqualTo(row123.getGyoukubun());

        // 自由検索
        assertThat(entity1203.getSearchWords()).isEqualTo("政治資金パーティのうちあっせん(パーティ名称)名称政治団体住所政治団体");

    }

}
