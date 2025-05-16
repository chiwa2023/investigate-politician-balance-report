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

    /** アドレス・ベース・レジストリ住所郵便番号まで */
    rsdtAddressPostl: string;

    /** アドレス・ベース・レジストリ住所番地まで */
    rsdtAddressBlock: string;

    /** アドレス・ベース・レジストリ住所建物 */
    rsdtAddressBuilding: string;


    constructor() {
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.addressAll = INIT_STRING;
        this.orginAddressAll = INIT_STRING;

        this.postalcode1 = INIT_STRING;
        this.postalcode2 = INIT_STRING;
        this.addressPostal = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.addressBuilding = INIT_STRING;
        this.tel1 = INIT_STRING;
        this.tel2 = INIT_STRING;
        this.tel3 = INIT_STRING;

        this.lgCode = INIT_STRING;
        this.machiazaId = INIT_STRING;
        this.blkId = INIT_STRING;
        this.rsdtId = INIT_STRING;

        this.isEditAddressPostal = INIT_BOOLEAN;
        this.isEditAddressBlock = INIT_BOOLEAN;
        this.isEditAddressBuilding = INIT_BOOLEAN;

        this.rsdtAddressPostl = INIT_STRING;
        this.rsdtAddressBlock = INIT_STRING;
        this.rsdtAddressBuilding = INIT_STRING;

    }
}