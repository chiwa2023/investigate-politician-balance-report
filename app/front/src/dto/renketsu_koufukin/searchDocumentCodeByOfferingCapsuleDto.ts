import AbstractCapsuleDto from "../abstractCapsuleDto";

export default interface SearchDocumentCodeByOfferingCapsuleInterface {

}

export default class SearchDocumentCodeByOfferingCapsuleDto extends AbstractCapsuleDto
    implements SearchDocumentCodeByOfferingCapsuleInterface {

    /** 政治団体Id */
    poliOrgId: number;

    /** 政治団体コード */
    poliOrgCode: number;

    /** 政治団体Id */
    poliOrgName: string;

    /** 報告年 */
    houkokuNen: number;

    constructor() {
        super();

        const INIT_INTEGER: number = 0;
        const INIT_STRING: string = "";

        this.poliOrgId = INIT_INTEGER;
        this.poliOrgCode = INIT_INTEGER;
        this.poliOrgName = INIT_STRING;
        this.houkokuNen = INIT_INTEGER;
    }
}