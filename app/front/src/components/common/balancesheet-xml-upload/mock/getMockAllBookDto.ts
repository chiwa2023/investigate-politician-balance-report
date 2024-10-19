import  AllBookDto  from "../../../../dto/balancesheet/allBookDto";
import  AllSheet0701CoverAndOrganizationDetailsDto  from "../../../../dto/balancesheet/sheet01/allSheet0701CoverAndOrganizationDetailsDto";
import  Sheet070100CoverAndOrganizationDetailsDto  from "../../../../dto/balancesheet/sheet01/sheet070100CoverAndOrganizationDetailsDto";

export default async function getMockAllBookDto():Promise<AllBookDto>{

    const allBookDto:AllBookDto = new AllBookDto();
    const sheetDto:Sheet070100CoverAndOrganizationDetailsDto = new Sheet070100CoverAndOrganizationDetailsDto();
    sheetDto.houkokuNen = 2023;
    sheetDto.dantaiName = "サンプル政治団体";
    sheetDto.daihyoushaNameLast = "政治団体";
    sheetDto.daihyoushaNameFirst = "太郎";
    sheetDto.kaikeiSekinnshaNameLast = "政治団体";
    sheetDto.kaikeiSekinnshaNameFirst = "花子";
    sheetDto.jimushoJusho = "東京都千代田区霞が関999番地";
    sheetDto.jimushoJushoTatemono = "zzzabcビル3Fqqq室";
    sheetDto.dantaiKbn = "14";
    sheetDto.umuShikinKanrenDantai = 1;

    const allSheetDto:AllSheet0701CoverAndOrganizationDetailsDto = new AllSheet0701CoverAndOrganizationDetailsDto();
    allSheetDto.sheet070100CoverAndOrganizationDetailsDto = sheetDto;
    allBookDto.allSheet0701CoverAndOrganizationDetailsDto = allSheetDto;

    return allBookDto;
}
