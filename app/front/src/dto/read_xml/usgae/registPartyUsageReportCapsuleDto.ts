import SaveStorageResultDto from "../../storage/saveStorageResultDto";
import TemplateFrameworkCapsuleDto from "../../template/templateFrameworkCapsuleDto";
import PartyUsageDocumentPoliticalPropertyInterface from "./partyUsageDocumentPoliticalPropertyDto";
import PartyUsageDocumentPoliticalPropertyDto from "./partyUsageDocumentPoliticalPropertyDto";

export default interface RegistPoliticalOrgBalancesheetReportCapsuleInterface {

}

export default class RegistPoliticalOrgBalancesheetReportCapsuleDto extends TemplateFrameworkCapsuleDto implements RegistPoliticalOrgBalancesheetReportCapsuleInterface {

    /** 政党交付金使途報告書属性Dto */
    documentPropertyDto: PartyUsageDocumentPoliticalPropertyInterface;

    /** 書証ファイル保存Dto */
    saveStorageResultDto: SaveStorageResultDto;

    constructor() {
        super();
        this.documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        this.saveStorageResultDto = new SaveStorageResultDto();

    }
}