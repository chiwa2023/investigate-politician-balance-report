package mitei.mitei.investigate.report.balance.politician.trial;

import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0701CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;


/**
 * 政治資金収支報告書XMLをbackから正常に取得できるかテストしていくMock
 */
@Component
public class MockGetAllBookDtoLogic {

    /**
     * ダミーのAllBikkDtoを返却する
     *
     * @return 政治資金収支報告書Dto
     */
    public AllBookDto practice() {
        AllBookDto allBookDto = new AllBookDto();
        
        allBookDto.setAllSheet0701CoverAndOrganizationDetailsDto(new AllSheet0701CoverAndOrganizationDetailsDto());
        allBookDto.getAllSheet0701CoverAndOrganizationDetailsDto().setSheet070100CoverAndOrganizationDetailsDto(new Sheet070100CoverAndOrganizationDetailsDto());

        Sheet070100CoverAndOrganizationDetailsDto sheet0701 = allBookDto.getAllSheet0701CoverAndOrganizationDetailsDto().getSheet070100CoverAndOrganizationDetailsDto();
        
        sheet0701.setHoukokuNen(2022); //  SUPPRESS CHECKSTYLE MagicNumber
        sheet0701.setDantaiName01("backサンプル政治団体");
        sheet0701.setDaihyoushaNameLast("back政治団体");
        sheet0701.setDaihyoushaNameFirst("back太郎");
        sheet0701.setKaikeiSekinnshaNameLast("back政治団体");
        sheet0701.setKaikeiSekinnshaNameFirst("back花子");
        sheet0701.setJimushoJusho("back東京都千代田区霞が関");
        sheet0701.setJimushoJushoTatemono("back zzzabcビル");
        sheet0701.setDantaiKbn("back団体区分");
        sheet0701.setUmuShikinKanrenDantai(0);
        
        return allBookDto;
    }
}
