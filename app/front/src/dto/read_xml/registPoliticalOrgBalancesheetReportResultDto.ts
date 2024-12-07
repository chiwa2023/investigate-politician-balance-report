import TemplateFrameworkResultDto from "../template/templateFrameworkResultDto";

export default interface RegistPoliticalOrgBalancesheetReportResultInterface{

}


export default class RegistPoliticalOrgBalancesheetReportResultDto extends TemplateFrameworkResultDto implements RegistPoliticalOrgBalancesheetReportResultInterface{

    /** 登録時の文書同一識別コード */
    documentCode : number;

    constructor(){
        super();
        this.documentCode = 0       
    }
}