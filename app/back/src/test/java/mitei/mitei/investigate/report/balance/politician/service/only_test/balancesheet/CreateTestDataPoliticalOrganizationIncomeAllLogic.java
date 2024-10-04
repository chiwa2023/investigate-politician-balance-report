package mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet; // NOPMD

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

/**
 * 政治資金収支報告書収入(その3から12)テストデータ生成
 */
public class CreateTestDataPoliticalOrganizationIncomeAllLogic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成
     * 
     * @param allBookDto 政治資金収支報告書Dto
     */
    public void practice(final AllBookDto allBookDto) { // NOPMD

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

    }

}
