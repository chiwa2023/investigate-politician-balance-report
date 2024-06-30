/**
 * 1件の保存済書証Dto
 */
export default class SaveStorageResultDto {

    /** 書証Id */
    shoshouKbn: number;

    /** 書証Id */
    shoshouId: string;

    /** 書証同一識別コード */
    shoshouCode: number;

    constructor() {
        this.shoshouKbn = 0;
        this.shoshouId = "";
        this.shoshouCode = 0;
    }
}