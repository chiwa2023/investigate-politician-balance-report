/**
 * 住所入力Dto
 */
export default class InputAddressDto {

    /** 住所全体 */
    addressAll: string;
    /** 元住所全体 */
    orginAddressAll: string;

    /** 郵便番号1 */
    postalcode1: string;
    /** 郵便番号2 */
    postalcode2: string;
    /** 住所郵便番号まで1 */
    addressPostal: string;
    /** 住所番地 */
    addressBlock: string;
    /** 住所建物 */
    addressBuilding: string;
    /** 電話番号市外局番 */
    tel1: string;
    /** 電話番号局番 */
    tel2: string;
    /** 電話番号番号 */
    tel3: string;

    /** 地方公共団体コード */
    lgCode: string;
    /** 町字Id */
    machiazaId: string;
    /** 街区Id */
    blkId: string;
    /** 住居Id */
    rsdtId: string;

    /** 住所郵便番号まで編集有無1 */
    isEditAddressPostal: boolean;
    /** 住所番地編集有無 */
    isEditAddressBlock: boolean;
    /** 住所建物編集有無 */
    isEditAddressBuilding: boolean;

    constructor() {
        const initString: string = "";
        const initBoolean: boolean = false;

        this.addressAll = initString;
        this.orginAddressAll = initString;

        this.postalcode1 = initString;
        this.postalcode2 = initString;
        this.addressPostal = initString;
        this.addressBlock = initString;
        this.addressBuilding = initString;
        this.tel1 = initString;
        this.tel2 = initString;
        this.tel3 = initString;

        this.lgCode = initString;
        this.machiazaId = initString;
        this.blkId = initString;
        this.rsdtId = initString;


        this.isEditAddressPostal = initBoolean;
        this.isEditAddressBlock = initBoolean;
        this.isEditAddressBuilding = initBoolean;

    }
}