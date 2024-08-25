package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071402Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071403Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071501Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071502Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071503Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071504Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071505Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071506Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071507Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071508Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071509Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071415OrdinaryExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071401UtilityCostsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071501OrganizationalActivityExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071502ElectionRelatedExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071503MagazinePublicationExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071504AdvertisingExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071505PartyHostingFeeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071506OtherBusinessExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071507ResearchExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071508DonationsGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071509OtherExpensesDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheetOutcome2025Entity;

/**
 * 政治資金収支報告書の支出を登録する
 */
@Component
public class InsertPoliticalOrganizationOutcomeAllLogic {

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

        List<OfferingBalancesheetOutcome2025Entity> list = new ArrayList<>();

        final String HIMOKU_BLANK = "";

        // シート14-1を登録する
        Sheet071401UtilityCostsDto sheet071401UtilityCostsDto = allBookDto.getAllSheet0714ConstsDto()
                .getAllSheetKbn071401Dto().getSheet071401UtilityCostsDto();
        list.addAll(this.createList(sheet071401UtilityCostsDto.getList(), sheet071401UtilityCostsDto.getPageTotal(),
                sheet071401UtilityCostsDto.getSonotaTotal(), HIMOKU_BLANK));

        // シート14-2を登録する
        AllSheetKbn071402Dto allSheetKbn071402Dto = allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto();
        list.addAll(this.createList(allSheetKbn071402Dto.getSheet071402EquipmentCostsDto().getList(),
                allSheetKbn071402Dto.getSheet071402EquipmentCostsDto().getPageTotal(),
                allSheetKbn071402Dto.getSheet071402EquipmentCostsDto().getSonotaTotal(), HIMOKU_BLANK));

        // シート14-3を登録する
        AllSheetKbn071403Dto allSheetKbn071403Dto = allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto();
        list.addAll(this.createList(allSheetKbn071403Dto.getSheet071403OfficeExpensesDto().getList(),
                allSheetKbn071403Dto.getSheet071403OfficeExpensesDto().getPageTotal(),
                allSheetKbn071403Dto.getSheet071403OfficeExpensesDto().getSonotaTotal(), HIMOKU_BLANK));

        
        
        
        // シート15-1を登録する
        AllSheetKbn071501Dto allSheetKbn071501Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto();
        for (Sheet071501OrganizationalActivityExpensesDto sheetDto : allSheetKbn071501Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-2を登録する
        AllSheetKbn071502Dto allSheetKbn071502Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto();
        for (Sheet071502ElectionRelatedExpensesDto sheetDto : allSheetKbn071502Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-3を登録する
        AllSheetKbn071503Dto allSheetKbn071503Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto();
        for (Sheet071503MagazinePublicationExpensesDto sheetDto : allSheetKbn071503Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-4を登録する
        AllSheetKbn071504Dto allSheetKbn071504Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto();
        for (Sheet071504AdvertisingExpensesDto sheetDto : allSheetKbn071504Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-5を登録する
        AllSheetKbn071505Dto allSheetKbn071505Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto();
        for (Sheet071505PartyHostingFeeDto sheetDto : allSheetKbn071505Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-6を登録する
        AllSheetKbn071506Dto allSheetKbn071506Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto();
        for (Sheet071506OtherBusinessExpensesDto sheetDto : allSheetKbn071506Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-7を登録する
        AllSheetKbn071507Dto allSheetKbn071507Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto();
        for (Sheet071507ResearchExpensesDto sheetDto : allSheetKbn071507Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-8を登録する
        AllSheetKbn071508Dto allSheetKbn071508Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto();
        for (Sheet071508DonationsGrantsDto sheetDto : allSheetKbn071508Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        // シート15-9を登録する
        AllSheetKbn071509Dto allSheetKbn071509Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto();
        for (Sheet071509OtherExpensesDto sheetDto : allSheetKbn071509Dto.getList()) {
            list.addAll(this.createList(sheetDto.getList(), sheetDto.getPageTotal(), sheetDto.getSonotaTotal(),
                    sheetDto.getHimoku()));
        }

        return 0;
    }

    private List<OfferingBalancesheetOutcome2025Entity> createList(final List<Row071415OrdinaryExpensesDto> listRow,
            final Long pageTotal, final String sonotaTotal, final String himoku) {

        List<OfferingBalancesheetOutcome2025Entity> list = new ArrayList<>();

        for (Row071415OrdinaryExpensesDto rowDto : listRow) {

            this.createEntity(rowDto, pageTotal, sonotaTotal, himoku);
        }

        return list;

    }

    private OfferingBalancesheetOutcome2025Entity createEntity(final Row071415OrdinaryExpensesDto rowDto,
            final Long pageTotal, final String sonotaTotal, final String himoku) {
        OfferingBalancesheetOutcome2025Entity entity = new OfferingBalancesheetOutcome2025Entity();

        // 
        // @JacksonXmlProperty(localName = "ICHIREN_NO")
        // private Integer ichirenNo;ichirenNo

        // 
        // @JacksonXmlProperty(localName = "MOKUTEKI")
        // private String mokuteki;mokuteki

        // 
        // @JacksonXmlProperty(localName = "KINGAKU")
        // private Long kingaku;kingaku

        // 
        // @JacksonXmlProperty(localName = "DT")
        // private String date;accrual_date

        // 
        // @JacksonXmlProperty(localName = "NM")
        // private String name;name

        // 
        // @JacksonXmlProperty(localName = "ADR")
        // private String jusho;jusho

        // 
        // @JacksonXmlProperty(localName = "BIKOU")
        // private String biko;biko

        // 
        // @JacksonXmlProperty(localName = "RYOUSYU")
        // private Integer flgRyoushuusho;flg_ryoushuusho

        // 
        // @JacksonXmlProperty(localName = "KOUFUKIN")
        // private Integer flgKouufukin;flg_kouufukin

        return entity;
    }
}
