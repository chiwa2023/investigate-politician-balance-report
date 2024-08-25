package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071101Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071102Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071103Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071201Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071202Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071203Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070300JournalAndOtherDto;
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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071203ConsiderationMediationPartyPoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;

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
        
        // シート5を登録する
        Sheet070500IncomeRelatedToGrantsDto sheet070500 = allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().getSheet070500IncomeRelatedToGrantsDto();
        sheet070500.getPageTotal();
        
        // シート6を登録する
        Sheet070600OtherIncomeDto sheet070600 = allBookDto.getAllSheet0706OtherIncomeDto().getSheet070600OtherIncomeDto();
        sheet070600.getMimanTotal();
        sheet070600.getPageTotal();
        
        // シート7-1を登録する
        Sheet070701DonatePersonDto sheet070701 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto().getSheet070701DonatePersonDto();
        sheet070701.getPageTotal();
        sheet070701.getSonotaTotal();
        
        // シート7-2を登録する
        Sheet070702DonateGroupDto sheet070702 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto().getSheet070702DonateGroupDto();
        sheet070702.getPageTotal();
        sheet070702.getSonotaTotal();
        
        // シート7-3を登録する
        Sheet070703DonatePoliticOrgDto sheet070703 = allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto().getSheet070703DonatePoliticOrgDto();
        sheet070703.getPageTotal();
        sheet070703.getSonotaTotal();
        
        // シート8-1を登録する
        Sheet070801MediationPersonDto sheet070801 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto().getSheet070801MediationPersonDto();
        sheet070801.getPageTotal();
        sheet070801.getSonotaTotal();
        
        // シート8-2を登録する
        Sheet070802MediationGroupDto sheet070802 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto().getSheet070802MediationGroupDto();
        sheet070802.getPageTotal();
        sheet070802.getSonotaTotal();
        
        // シート8-3を登録する
        Sheet070803MediationPoliticOrgDto sheet070803 = allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto().getSheet070803MediationPoliticOrgDto();
        sheet070803.getPageTotal();
        sheet070803.getSonotaTotal();

        // シート9を登録する
        Sheet070900AnonymousInPoliticalPartyDto sheet070900 = allBookDto.getAllSheet0709AnonymousInPoliticalPartyDto().getSheet070900AnonymousInPoliticalPartyDto();
        sheet070900.getPageTotal();
        
        // シート10を登録する
        Sheet071000SpecificPartyDto sheet071000 = allBookDto.getAllSheet0710SpecificPartyDto().getSheet071000SpecificPartyDto();
        sheet071000.getPageTotal();
        
        // シート11-1を登録する
        AllSheetKbn071101Dto kbn071101Dto =      allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071101Dto();
        kbn071101Dto.getList();
        
        // シート11-2を登録する
        AllSheetKbn071102Dto kbn071102Dto =      allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071102Dto();
        kbn071102Dto.getList();
        
        // シート11-3を登録する
        AllSheetKbn071103Dto kbn071103Dto =      allBookDto.getAllSheet0711ConsiderationPartyDto().getAllSheetKbn071103Dto();
        kbn071103Dto.getList();
        
        // シート12-1を登録する
        AllSheetKbn071201Dto kbn071201Dto =   allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071201Dto();
        kbn071201Dto.getList();

        // シート12-2を登録する
        AllSheetKbn071202Dto kbn071202Dto =   allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071202Dto();
        kbn071202Dto.getList();
       
        // シート12-3を登録する
        AllSheetKbn071203Dto kbn071203Dto =   allBookDto.getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto();
        kbn071203Dto.getList();
        for(Sheet071203ConsiderationMediationPartyPoliticOrgDto sheetDto : kbn071203Dto.getList()) {
            sheetDto.getPageTotal();
            sheetDto.getPartyName();
            sheetDto.getSortNo();
            sheetDto.getList();
        }
        
        
        return 0;
    }
}
