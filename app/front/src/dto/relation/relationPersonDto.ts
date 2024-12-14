import { ThisApplication } from "../thisApplication";

export default interface RelationPersonInterface {

}

export default class RelationPersonDto implements RelationPersonInterface {

    /** 問い合わせId  */
    inquireId: string;

    /** 回答区分  */
    nationalityAnswerKbn: string;

    /** 回答区分表示用  */
    nationalityAnswerKbnText: string;

    /** アプリコード(問い合わせ元アプリを記録する場合)  */
    applicationComonCode: string;

    /** アプリ名称(問い合わせ元アプリを記録する場合)  */
    applicationName: string;

    /** 関連者(個人)Id(内部)  */
    relationPersonId: number;

    /** 関連者(個人)同一識別コード(内部)  */
    relationPersonCode: number;

    /** 政治団体Id(内部)  */
    politicalOrganizationId: number;
    /** 政治団体同一識別コード(内部)  */
    politicalOrganizationCode: number;
    /** 政治団体名称(内部)  */
    politicalOrganizationName: string;

    /** 検索対象該否(内部)  */
    isSearch: boolean;

    /** 名前(全) */
    nameAll: string;

    /** 姓名の姓 */
    firstName: string;
    /** 姓名の姓ふりがな */
    firstNameKana: string;
    /** 姓名の名 */
    lastName: string;
    /** 姓名の名かな */
    lastNameKana: string;
    /** ミドルネーム */
    middleName: string;
    /** ミドルネームふりがな */
    middleNameKana: string;

    /** 電話番号市外局番 */
    tel1: string;
    /** 電話番号局番 */
    tel2: string;
    /** 電話番号番号 */
    tel3: string;

    /** 住所(全) */
    addressAll: string;

        /** 元住所(全) */
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

    /** 地方公共団体コード */
    lgCode: string;
    /** 町字Id */
    machiazaId: string;
    /** 街区Id */
    blkId: string;
    /** 住居Id */
    rsdtId: string;

    /** 政治資金分野関連者同一識別コード */
    kanrenshaCode: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.inquireId = INIT_STRING;
        this.nationalityAnswerKbn = INIT_STRING;
        this.nationalityAnswerKbnText = INIT_STRING;

        this.applicationComonCode = ThisApplication.code;
        this.applicationName = ThisApplication.name;

        this.relationPersonId = INIT_NUMBER;
        this.relationPersonCode = INIT_NUMBER;
        this.politicalOrganizationId = INIT_NUMBER;
        this.politicalOrganizationCode = INIT_NUMBER;
        this.politicalOrganizationName = INIT_STRING;
        this.isSearch = false;

        this.nameAll = INIT_STRING;
        this.firstName = INIT_STRING;
        this.firstNameKana = INIT_STRING;
        this.lastName = INIT_STRING;
        this.lastNameKana = INIT_STRING;
        this.middleName = INIT_STRING;
        this.middleNameKana = INIT_STRING;
        this.tel1 = INIT_STRING;
        this.tel2 = INIT_STRING;
        this.tel3 = INIT_STRING;
        this.addressAll = INIT_STRING;
        this.postalcode1 = INIT_STRING;
        this.postalcode2 = INIT_STRING;
        this.addressPostal = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.addressBuilding = INIT_STRING;
        this.orginAddressAll = INIT_STRING;

        this.lgCode = INIT_STRING;
        this.machiazaId = INIT_STRING;
        this.blkId = INIT_STRING;
        this.rsdtId = INIT_STRING;
        this.kanrenshaCode = INIT_STRING;
    }

}