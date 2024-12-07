import TemplateFrameworkResultDto from "../../template/templateFrameworkResultDto";

export default interface RegistPartyUsageReportResultInterface{

}


export default class RegistPartyUsageReportResultDto extends TemplateFrameworkResultDto implements RegistPartyUsageReportResultInterface{

    /** 登録時の文書同一識別コード */
    documentCode : number;

    constructor(){
        super();
        this.documentCode = 0       
    }
}