import WkTblRenketsuKoufukinSelectInterface from "../../entity/wkTblRenketsuKoufukinSelectDto";
import WkTblUsageIncomeInterface from "../../entity/wkTblUsageIncomeEntity";
import TemplateFrameworkResultDto from "../template/templateFrameworkResultDto";

export default interface RenketsuKoufukinIncomeInterface {

}

export default class RenketsuKoufukinIncomeDto extends TemplateFrameworkResultDto implements RenketsuKoufukinIncomeInterface {


    /** 紐づけ成功リスト */
    listSuccess: WkTblRenketsuKoufukinSelectInterface[];

    /** 紐づけ重複リスト */
    listDuplicate: WkTblRenketsuKoufukinSelectInterface[];

    /** 紐づけ重複リスト */
    listFailure: WkTblUsageIncomeInterface[];

    constructor() {
        super();

        this.listSuccess = [];
        this.listDuplicate = [];
        this.listFailure = [];
    }

}