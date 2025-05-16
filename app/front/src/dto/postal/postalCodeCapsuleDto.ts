export default interface PostalCodeCapsuleInterface {

}

export default class PostalCodeCapsuleDto implements PostalCodeCapsuleInterface {


    /** 郵便番号1 */
    postal1: string;

    /** 郵便番号2 */
    postal2: string;

    /** 行政区検索 */
    isGyouseikuData: boolean;

    /** 選択された住所郵便番号まで */
    selectedPostal: number;

    /** 選択された住所番地まで */
    selectedBlock: string;

    /** 地方自治体コード */
    lgCode:string;

    /** 郵便番号 */
    postalCode: string;

    constructor() {

        //  初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.postal1 = INIT_STRING;
        this.postal2 = INIT_STRING;
        this.isGyouseikuData = INIT_BOOLEAN;
        this.selectedPostal = INIT_NUMBER;
        this.selectedBlock = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.postalCode = INIT_STRING;

    }

}