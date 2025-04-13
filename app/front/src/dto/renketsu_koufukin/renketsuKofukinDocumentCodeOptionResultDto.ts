import SelectOptionInterface from "../selectOptionDto";


export default interface RenketsuKofukinDocumentCodeOptionResultInterface {

}

export default class RenketsuKofukinDocumentCodeOptionResultDto implements RenketsuKofukinDocumentCodeOptionResultInterface {

    /** 使途報告書文書コード選択肢リスト */
    listUsage: SelectOptionInterface[];

    /** 使途報告書文書コード選択肢リスト */
    listBalance: SelectOptionInterface[];

    constructor() {

        this.listUsage = [];
        this.listBalance = [];

    }
}