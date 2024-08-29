package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheetOutcome2025Entity;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書収入の部を登録する
 */
@Component
public class InsertPoliticalOrganizationIncomeLogic {

    /**
     * 登録作業を行う
     *
     * @param documentCode 文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allBookDto 全文書Dto(使用するのは様式7その14と15のみ)
     * @param checkPrivilegeDto 権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllBookDto allBookDto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2025Entity> list = new ArrayList<>();

        
        
        
        // シート3を登録する
        Sheet070300JournalAndOtherDto sheet070300 = allBookDto.getAllSheet0703JournalAndOtherDto().getSheet070300JournalAndOtherDto();
        sheet070300.getPageTotal();
        for(Row070300JournalAndOtherDto rowDto : sheet070300.getList()) {
            
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 事業の種類 */
            //@JacksonXmlProperty(localName = "GIGYOU_SYURUI")
            //private String jigyoNoShurui;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;
            
        }

        
        
        // シート4を登録する
        Sheet070400BorrowedMoneyDto sheet070400 = allBookDto.getAllSheet0704BorrowedMoneyDto().getSheet070400BorrowedMoneyDto();
        sheet070400.getPageTotal();
        for(Row070400BorrowedMoneyDto rowDto : sheet070400.getList()) {
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 借り先 */
            //@JacksonXmlProperty(localName = "KARIIRESAKI")
            //private String karisaki;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;
        }

        
        
        
        
        // シート5を登録する
        Sheet070500IncomeRelatedToGrantsDto sheet070500 = allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().getSheet070500IncomeRelatedToGrantsDto();
        sheet070500.getPageTotal();
        for(Row070500IncomeRelatedToGrantsDto rowDto : sheet070500.getList()) {

            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 本部支部の名称 */
            //@JacksonXmlProperty(localName = "HONSIBU_NM")
            //private String honbuShibuName;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 事務所の住所 */
            //@JacksonXmlProperty(localName = "JIMU_ADR")
            //private String jimushoJuusho;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;


        }
        

        // シート6を登録する
        Sheet070600OtherIncomeDto sheet070600 = allBookDto.getAllSheet0706OtherIncomeDto().getSheet070600OtherIncomeDto();
        sheet070600.getMimanTotal();
        sheet070600.getPageTotal();
        for(Row070600OtherIncomeDto rowDto : sheet070600.getList()) {
            
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 摘要 */
            //@JacksonXmlProperty(localName = "TEKIYOU")
            //private String tekiyou;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

        }
        
        // シート7-1を登録する
        Sheet070701DonatePersonDto sheet070701 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto().getSheet070701DonatePersonDto();
        sheet070701.getPageTotal();
        sheet070701.getSonotaTotal();
        for(Row070711DonateDto rowDto : sheet070701.getList()) {
            
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 寄付者の名前 */
            //@JacksonXmlProperty(localName = "KIFUSYA_NM")
            //private String kifusha;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 住所 */
            //@JacksonXmlProperty(localName = "ADR")
            //private String jusho;

            ///** 職業 */
            //@JacksonXmlProperty(localName = "SYOKUGYO")
            //private String shokugyou;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

            ///** 通し番号 */
            //@JacksonXmlProperty(localName = "SEQ_NO")
            //private Integer tohshibangou;

            ///** 税額控除フラグ */
            //@JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
            //private Short flgZeigakuKohjo;

            ///** 行区分 */
            //@JacksonXmlProperty(localName = "ROWKBN")
            //private Short gyoukubun;

        }
        
        // シート7-2を登録する
        Sheet070702DonateGroupDto sheet070702 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto().getSheet070702DonateGroupDto();
        sheet070702.getPageTotal();
        sheet070702.getSonotaTotal();
        for(Row070711DonateDto rowDto : sheet070702.getList()) {

            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 寄付者の名前 */
            //@JacksonXmlProperty(localName = "KIFUSYA_NM")
            //private String kifusha;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 住所 */
            //@JacksonXmlProperty(localName = "ADR")
            //private String jusho;

            ///** 職業 */
            //@JacksonXmlProperty(localName = "SYOKUGYO")
            //private String shokugyou;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

            ///** 通し番号 */
            //@JacksonXmlProperty(localName = "SEQ_NO")
            //private Integer tohshibangou;

            ///** 税額控除フラグ */
            //@JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
            //private Short flgZeigakuKohjo;

            ///** 行区分 */
            //@JacksonXmlProperty(localName = "ROWKBN")
            //private Short gyoukubun;

        }
        
        // シート7-3を登録する
        Sheet070703DonatePoliticOrgDto sheet070703 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto().getSheet070703DonatePoliticOrgDto();
        sheet070703.getPageTotal();
        sheet070703.getSonotaTotal();
        for(Row070711DonateDto rowDto : sheet070703.getList()) {

            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 寄付者の名前 */
            //@JacksonXmlProperty(localName = "KIFUSYA_NM")
            //private String kifusha;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 住所 */
            //@JacksonXmlProperty(localName = "ADR")
            //private String jusho;

            ///** 職業 */
            //@JacksonXmlProperty(localName = "SYOKUGYO")
            //private String shokugyou;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

            ///** 通し番号 */
            //@JacksonXmlProperty(localName = "SEQ_NO")
            //private Integer tohshibangou;

            ///** 税額控除フラグ */
            //@JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
            //private Short flgZeigakuKohjo;

            ///** 行区分 */
            //@JacksonXmlProperty(localName = "ROWKBN")
            //private Short gyoukubun;

        }
        
        // シート8-1を登録する
        Sheet070801MediationPersonDto sheet070801 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto().getSheet070801MediationPersonDto();
        sheet070801.getPageTotal();
        sheet070801.getSonotaTotal();
        for(Row070812MediationDto rowDto : sheet070801.getList()) {
            
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 名前 */
            //@JacksonXmlProperty(localName = "NM")
            //private String name;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 斡旋の期間 */
            //@JacksonXmlProperty(localName = "KIKAN")
            //private String periodMediate;

            ///** 住所 */
            //@JacksonXmlProperty(localName = "ADR")
            //private String juusho;

            ///** 職業 */
            //@JacksonXmlProperty(localName = "SYOKUGYO")
            //private String shokugyou;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

            ///** 通し番号 */
            //@JacksonXmlProperty(localName = "SEQ_NO")
            //private Integer tohshibangou;

            ///** 行区分 */
            //@JacksonXmlProperty(localName = "ROWKBN")
            //private Short gyoukubun;


        }
        
        // シート8-2を登録する
        Sheet070802MediationGroupDto sheet070802 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto().getSheet070802MediationGroupDto();
        sheet070802.getPageTotal();
        sheet070802.getSonotaTotal();
        for(Row070812MediationDto rowDto : sheet070802.getList()) {

            
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 名前 */
            //@JacksonXmlProperty(localName = "NM")
            //private String name;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 斡旋の期間 */
            //@JacksonXmlProperty(localName = "KIKAN")
            //private String periodMediate;

            ///** 住所 */
            //@JacksonXmlProperty(localName = "ADR")
            //private String juusho;

            ///** 職業 */
            //@JacksonXmlProperty(localName = "SYOKUGYO")
            //private String shokugyou;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

            ///** 通し番号 */
            //@JacksonXmlProperty(localName = "SEQ_NO")
            //private Integer tohshibangou;

            ///** 行区分 */
            //@JacksonXmlProperty(localName = "ROWKBN")
            //private Short gyoukubun;

        }
        
        // シート8-3を登録する
        Sheet070803MediationPoliticOrgDto sheet070803 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto().getSheet070803MediationPoliticOrgDto();
        sheet070803.getPageTotal();
        sheet070803.getSonotaTotal();
        for(Row070812MediationDto rowDto : sheet070803.getList()) {

            
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 名前 */
            //@JacksonXmlProperty(localName = "NM")
            //private String name;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 斡旋の期間 */
            //@JacksonXmlProperty(localName = "KIKAN")
            //private String periodMediate;

            ///** 住所 */
            //@JacksonXmlProperty(localName = "ADR")
            //private String juusho;

            ///** 職業 */
            //@JacksonXmlProperty(localName = "SYOKUGYO")
            //private String shokugyou;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

            ///** 通し番号 */
            //@JacksonXmlProperty(localName = "SEQ_NO")
            //private Integer tohshibangou;

            ///** 行区分 */
            //@JacksonXmlProperty(localName = "ROWKBN")
            //private Short gyoukubun;

        }

        // シート9を登録する
        Sheet070900AnonymousInPoliticalPartyDto sheet070900 = allBookDto.getAllSheet0709AnonymousInPoliticalPartyDto().getSheet070900AnonymousInPoliticalPartyDto();
        sheet070900.getPageTotal();
        for(Row070900AnonymousInPoliticalPartyDto rowDto : sheet070900.getList()) {
         
            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** 場所 */
            //@JacksonXmlProperty(localName = "BASYO")
            //private String basho;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 発生日 */
            //@JacksonXmlProperty(localName = "DT")
            //private String accrualDate;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;

        }
        
        // シート10を登録する
        Sheet071000SpecificPartyDto sheet071000 = allBookDto.getAllSheet0710SpecificPartyDto().getSheet071000SpecificPartyDto();
        sheet071000.getPageTotal();
        for(Row071000SpecificPartyDto rowDto : sheet071000.getList()) {

            ///** 連番 */
            //@JacksonXmlProperty(localName = "ICHIREN_NO")
            //private Integer ichirenNo;

            ///** パーティ名称 */
            //@JacksonXmlProperty(localName = "PATYI_NM")
            //private String partyName;

            ///** 金額 */
            //@JacksonXmlProperty(localName = "KINGAKU")
            //private Long kingaku;

            ///** 支払い数 */
            //@JacksonXmlProperty(localName = "SHIHARAI_SU")
            //private Long shiharaisu;

            ///** 開催日 */
            //@JacksonXmlProperty(localName = "KAISAI_DT")
            //private String accrualDate;

            ///** 開催場所 */
            //@JacksonXmlProperty(localName = "KAISAI_BASYO")
            //private String kaisaiBasho;

            ///** 備考 */
            //@JacksonXmlProperty(localName = "BIKOU")
            //private String biko;


        }
        
        // シート11-1を登録する
        AllSheetKbn071101Dto kbn071101Dto =      allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto();
        kbn071101Dto.getList();
        for(Sheet071101ConsiderationPartyPerspnalDto sheetDto : kbn071101Dto.getList()) {

            sheetDto.getPageTotal();
            sheetDto.getPartyName();
            sheetDto.getSortNo();
            
            for(Row070711DonateDto rowDto : sheetDto.getList()) {
                ///** 連番 */
                //@JacksonXmlProperty(localName = "ICHIREN_NO")
                //private Integer ichirenNo;

                ///** 寄付者の名前 */
                //@JacksonXmlProperty(localName = "KIFUSYA_NM")
                //private String kifusha;

                ///** 金額 */
                //@JacksonXmlProperty(localName = "KINGAKU")
                //private Long kingaku;

                ///** 発生日 */
                //@JacksonXmlProperty(localName = "DT")
                //private String accrualDate;

                ///** 住所 */
                //@JacksonXmlProperty(localName = "ADR")
                //private String jusho;

                ///** 職業 */
                //@JacksonXmlProperty(localName = "SYOKUGYO")
                //private String shokugyou;

                ///** 備考 */
                //@JacksonXmlProperty(localName = "BIKOU")
                //private String biko;

                ///** 通し番号 */
                //@JacksonXmlProperty(localName = "SEQ_NO")
                //private Integer tohshibangou;

                ///** 税額控除フラグ */
                //@JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
                //private Short flgZeigakuKohjo;

                ///** 行区分 */
                //@JacksonXmlProperty(localName = "ROWKBN")
                //private Short gyoukubun;
                
            }            
            
            


            
        }
        
        // シート11-2を登録する
        AllSheetKbn071102Dto kbn071102Dto =      allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto();
        kbn071102Dto.getList();
        for(Sheet071102ConsiderationPartyGroupDto sheetDto : kbn071102Dto.getList()) {
            sheetDto.getPageTotal();
            sheetDto.getPartyName();
            sheetDto.getSortNo();
            
            for(Row070711DonateDto rowDto : sheetDto.getList()) {
                ///** 連番 */
                //@JacksonXmlProperty(localName = "ICHIREN_NO")
                //private Integer ichirenNo;

                ///** 寄付者の名前 */
                //@JacksonXmlProperty(localName = "KIFUSYA_NM")
                //private String kifusha;

                ///** 金額 */
                //@JacksonXmlProperty(localName = "KINGAKU")
                //private Long kingaku;

                ///** 発生日 */
                //@JacksonXmlProperty(localName = "DT")
                //private String accrualDate;

                ///** 住所 */
                //@JacksonXmlProperty(localName = "ADR")
                //private String jusho;

                ///** 職業 */
                //@JacksonXmlProperty(localName = "SYOKUGYO")
                //private String shokugyou;

                ///** 備考 */
                //@JacksonXmlProperty(localName = "BIKOU")
                //private String biko;

                ///** 通し番号 */
                //@JacksonXmlProperty(localName = "SEQ_NO")
                //private Integer tohshibangou;

                ///** 税額控除フラグ */
                //@JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
                //private Short flgZeigakuKohjo;

                ///** 行区分 */
                //@JacksonXmlProperty(localName = "ROWKBN")
                //private Short gyoukubun;
                
            }            
            
        }
        
        // シート11-3を登録する
        AllSheetKbn071103Dto kbn071103Dto =      allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto();
        kbn071103Dto.getList();
        for(Sheet071103ConsiderationPartyPoliticOrgDto sheetDto : kbn071103Dto.getList()) {
            sheetDto.getPageTotal();
            sheetDto.getPartyName();
            sheetDto.getSortNo();
            
            for(Row070711DonateDto rowDto : sheetDto.getList()) {
                ///** 連番 */
                //@JacksonXmlProperty(localName = "ICHIREN_NO")
                //private Integer ichirenNo;

                ///** 寄付者の名前 */
                //@JacksonXmlProperty(localName = "KIFUSYA_NM")
                //private String kifusha;

                ///** 金額 */
                //@JacksonXmlProperty(localName = "KINGAKU")
                //private Long kingaku;

                ///** 発生日 */
                //@JacksonXmlProperty(localName = "DT")
                //private String accrualDate;

                ///** 住所 */
                //@JacksonXmlProperty(localName = "ADR")
                //private String jusho;

                ///** 職業 */
                //@JacksonXmlProperty(localName = "SYOKUGYO")
                //private String shokugyou;

                ///** 備考 */
                //@JacksonXmlProperty(localName = "BIKOU")
                //private String biko;

                ///** 通し番号 */
                //@JacksonXmlProperty(localName = "SEQ_NO")
                //private Integer tohshibangou;

                ///** 税額控除フラグ */
                //@JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
                //private Short flgZeigakuKohjo;

                ///** 行区分 */
                //@JacksonXmlProperty(localName = "ROWKBN")
                //private Short gyoukubun;
                
            }            
        }
        
        // シート12-1を登録する
        AllSheetKbn071201Dto kbn071201Dto =   allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto();
        kbn071201Dto.getList();
        for(Sheet071201ConsiderationMediationPartyPersonalDto sheetDto : kbn071201Dto.getList()) {
         

            sheetDto.getPageTotal();
            sheetDto.getPartyName();
            sheetDto.getSortNo();

            for(Row070812MediationDto rowDto : sheetDto.getList()) {
                
                
                ///** 連番 */
                //@JacksonXmlProperty(localName = "ICHIREN_NO")
                //private Integer ichirenNo;

                ///** 名前 */
                //@JacksonXmlProperty(localName = "NM")
                //private String name;

                ///** 金額 */
                //@JacksonXmlProperty(localName = "KINGAKU")
                //private Long kingaku;

                ///** 発生日 */
                //@JacksonXmlProperty(localName = "DT")
                //private String accrualDate;

                ///** 斡旋の期間 */
                //@JacksonXmlProperty(localName = "KIKAN")
                //private String periodMediate;

                ///** 住所 */
                //@JacksonXmlProperty(localName = "ADR")
                //private String juusho;

                ///** 職業 */
                //@JacksonXmlProperty(localName = "SYOKUGYO")
                //private String shokugyou;

                ///** 備考 */
                //@JacksonXmlProperty(localName = "BIKOU")
                //private String biko;

                ///** 通し番号 */
                //@JacksonXmlProperty(localName = "SEQ_NO")
                //private Integer tohshibangou;

                ///** 行区分 */
                //@JacksonXmlProperty(localName = "ROWKBN")
                //private Short gyoukubun;
            }


        }

        // シート12-2を登録する
        AllSheetKbn071202Dto kbn071202Dto =   allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto();
        kbn071202Dto.getList();
        for(Sheet071202ConsiderationMediationPartyGroupDto sheetDto : kbn071202Dto.getList()) {

            sheetDto.getPageTotal();
            sheetDto.getPartyName();
            sheetDto.getSortNo();

            for(Row070812MediationDto rowDto : sheetDto.getList()) {
                
                
                ///** 連番 */
                //@JacksonXmlProperty(localName = "ICHIREN_NO")
                //private Integer ichirenNo;

                ///** 名前 */
                //@JacksonXmlProperty(localName = "NM")
                //private String name;

                ///** 金額 */
                //@JacksonXmlProperty(localName = "KINGAKU")
                //private Long kingaku;

                ///** 発生日 */
                //@JacksonXmlProperty(localName = "DT")
                //private String accrualDate;

                ///** 斡旋の期間 */
                //@JacksonXmlProperty(localName = "KIKAN")
                //private String periodMediate;

                ///** 住所 */
                //@JacksonXmlProperty(localName = "ADR")
                //private String juusho;

                ///** 職業 */
                //@JacksonXmlProperty(localName = "SYOKUGYO")
                //private String shokugyou;

                ///** 備考 */
                //@JacksonXmlProperty(localName = "BIKOU")
                //private String biko;

                ///** 通し番号 */
                //@JacksonXmlProperty(localName = "SEQ_NO")
                //private Integer tohshibangou;

                ///** 行区分 */
                //@JacksonXmlProperty(localName = "ROWKBN")
                //private Short gyoukubun;
            }

        }
       
        // シート12-3を登録する
        AllSheetKbn071203Dto kbn071203Dto =   allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto();
        kbn071203Dto.getList();
        for(Sheet071203ConsiderationMediationPartyPoliticOrgDto sheetDto : kbn071203Dto.getList()) {
            sheetDto.getPageTotal();
            sheetDto.getPartyName();
            sheetDto.getSortNo();

            for(Row070812MediationDto rowDto : sheetDto.getList()) {
                
                
                ///** 連番 */
                //@JacksonXmlProperty(localName = "ICHIREN_NO")
                //private Integer ichirenNo;

                ///** 名前 */
                //@JacksonXmlProperty(localName = "NM")
                //private String name;

                ///** 金額 */
                //@JacksonXmlProperty(localName = "KINGAKU")
                //private Long kingaku;

                ///** 発生日 */
                //@JacksonXmlProperty(localName = "DT")
                //private String accrualDate;

                ///** 斡旋の期間 */
                //@JacksonXmlProperty(localName = "KIKAN")
                //private String periodMediate;

                ///** 住所 */
                //@JacksonXmlProperty(localName = "ADR")
                //private String juusho;

                ///** 職業 */
                //@JacksonXmlProperty(localName = "SYOKUGYO")
                //private String shokugyou;

                ///** 備考 */
                //@JacksonXmlProperty(localName = "BIKOU")
                //private String biko;

                ///** 通し番号 */
                //@JacksonXmlProperty(localName = "SEQ_NO")
                //private Integer tohshibangou;

                ///** 行区分 */
                //@JacksonXmlProperty(localName = "ROWKBN")
                //private Short gyoukubun;
            }
        }
        
        
        return 0;
    }


    
    
    private OfferingBalancesheetIncome2025Entity createEntity(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final CheckPrivilegeDto checkPrivilegeDto) {
        
        
        OfferingBalancesheetIncome2025Entity incomeEntity = new OfferingBalancesheetIncome2025Entity();
        
        incomeEntity.setDocumentCode(documentCode);
        
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);
        
        

        
        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
