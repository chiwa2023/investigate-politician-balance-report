import FrameworkResultInterface from "../frameworkResultDto";
import FrameworkResultDto from "../frameworkResultDto";
import type CorpInterface from "./corpNoDto";

export default interface CheckRegistCorpResultInterface extends FrameworkResultInterface {

}

export default class CheckRegistCorpResultDto extends FrameworkResultDto implements CheckRegistCorpResultInterface {

    /** 法人番号既存 */
    savedCorpNo:string;

    listCorptDto:CorpInterface[];

    constructor() {
        super();
        this.savedCorpNo = "";
        this.listCorptDto = [];
    }
}