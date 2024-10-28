import SaveStorageResultDto from "../../storage/saveStorageResultDto";
import TemplateFrameworkResultInterface from "../../template/templateFrameworkResultDto";
import TemplateFrameworkResultDto from "../../template/templateFrameworkResultDto";
import Sheet0801Interface from "../../usage/sheet0801Dto";
import Sheet0801Dto from "../../usage/sheet0801Dto";
import PartyUsageDocumentPoliticalPropertyInterface from "./partyUsageDocumentPoliticalPropertyDto";
import PartyUsageDocumentPoliticalPropertyDto from "./partyUsageDocumentPoliticalPropertyDto";

export default interface  ReadXmlUsageReportResultInterface extends TemplateFrameworkResultInterface{

}

export default class ReadXmlUsageReportResultDto extends TemplateFrameworkResultDto implements  ReadXmlUsageReportResultInterface{ 

    /** 政治資金収支報告書用政治団体情報 */
    documentPropertyDto:PartyUsageDocumentPoliticalPropertyInterface ;

    /** 政党交付金使途報告書(様式8の1表紙) */
    coverDto:Sheet0801Interface;

    /** 書証保存Dto */
    saveStorageResultDto:SaveStorageResultDto;

    constructor(){
        super();
        this.documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto() ;
        this.coverDto = new Sheet0801Dto();
        this.saveStorageResultDto= new SaveStorageResultDto();

    }
}