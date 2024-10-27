import Sheet070100CoverAndOrganizationDetailsInterface from "../balancesheet/sheet01/sheet070100CoverAndOrganizationDetailsDto";
import Sheet070100CoverAndOrganizationDetailsDto from "../balancesheet/sheet01/sheet070100CoverAndOrganizationDetailsDto";
import SaveStorageResultDto from "../storage/saveStorageResultDto";
import TemplateFrameworkResultInterface from "../template/templateFrameworkResultDto";
import TemplateFrameworkResultDto from "../template/templateFrameworkResultDto";
import BalancesheetReportDocumentPoliticalPropertyDto from "../read_xml/balancesheetReportDocumentPoliticalPropertyDto";
import BalancesheetReportDocumentPoliticalPropertyInterface from "../read_xml/balancesheetReportDocumentPoliticalPropertyDto";

export default interface ReadXmlBalancesheetResultInterface extends TemplateFrameworkResultInterface{

}

export default class ReadXmlBalancesheetResultDto extends TemplateFrameworkResultDto implements ReadXmlBalancesheetResultInterface{ 

    /** 政治資金収支報告書用政治団体情報 */
    documentPropertyDto:BalancesheetReportDocumentPoliticalPropertyInterface ;

    /** 政治資金収支報告書様式1(表紙) */
    coverDto:Sheet070100CoverAndOrganizationDetailsInterface;

    /** 書証保存Dto */
    saveStorageResultDto:SaveStorageResultDto;

    constructor(){
        super();
        this.documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto() ;
        this.coverDto = new Sheet070100CoverAndOrganizationDetailsDto();
        this.saveStorageResultDto= new SaveStorageResultDto;

    }
}