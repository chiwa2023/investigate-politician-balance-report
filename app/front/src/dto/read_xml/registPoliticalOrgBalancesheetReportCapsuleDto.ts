import SaveStorageResultDto from "../storage/saveStorageResultDto";
import TemplateFrameworkCapsuleDto from "../template/templateFrameworkCapsuleDto";
import BalancesheetReportDocumentPoliticalPropertyDto from "./balancesheetReportDocumentPoliticalPropertyDto";

export default interface RegistPoliticalOrgBalancesheetReportCapsuleInterface{

}


export default class RegistPoliticalOrgBalancesheetReportCapsuleDto extends TemplateFrameworkCapsuleDto implements RegistPoliticalOrgBalancesheetReportCapsuleInterface{

        /** 政治資金収支報告書文書属性Dto */
        documentPropertyDto:BalancesheetReportDocumentPoliticalPropertyDto;

        /** 書証ファイル保存Dto */
        saveStorageResultDto:SaveStorageResultDto;


        constructor(){
            super();
            this.documentPropertyDto= new BalancesheetReportDocumentPoliticalPropertyDto();
            this.saveStorageResultDto= new SaveStorageResultDto();

        }
}