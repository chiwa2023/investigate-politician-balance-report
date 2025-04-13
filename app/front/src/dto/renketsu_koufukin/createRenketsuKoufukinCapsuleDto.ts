import AbstractCapsuleDto from "../abstractCapsuleDto";

export default interface CreateRenketsuKoufukinCapsuleInterface {

}

export default class CreateRenketsuKoufukinCapsuleDto extends AbstractCapsuleDto implements CreateRenketsuKoufukinCapsuleInterface {

    /** 報告年 */
    houkokuNen: number;

    /** 文書コード使途報告書 */
    documentCodeUsage: number;

    /** 文書コード収支報告書 */
    documentCodeBalance: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        this.houkokuNen = INIT_NUMBER;
        this.documentCodeUsage = INIT_NUMBER;
        this.documentCodeBalance = INIT_NUMBER;

    }
}