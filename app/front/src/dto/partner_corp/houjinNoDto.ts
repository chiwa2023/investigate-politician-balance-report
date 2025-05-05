export default interface HoujinNoInterface {

}


export default class HoujinNoDto implements HoujinNoInterface {

    /** 法人番号 */
    houjinNo: string;

    /** 処理区分 */
    shoriKbn: string;

    /** 更新日 */
    updateDate: Date;

    /** 法人種別 */
    houjinSbts: string;

    /** 法人商号カナ */
    houjinNameKana: string;

    /** 法人商号 */
    houjinName: string;

    /**　郵便番号  */
    postalcode: string;

    /**　住所・都道府県  */
    addressPrefecture: string;

    /** 住所・市  */
    addressCity: string;

    /** 住所・街区建物  */
    addressBlock: string;

    constructor() {

        const INIT_STYRING: string = "";

        this.houjinNo = INIT_STYRING;
        this.shoriKbn = INIT_STYRING;
        this.updateDate = new Date(1948, 7, 29);
        this.houjinSbts = INIT_STYRING;
        this.houjinNameKana = INIT_STYRING;
        this.houjinName = INIT_STYRING;
        this.postalcode = INIT_STYRING;
        this.addressPrefecture = INIT_STYRING;
        this.addressCity = INIT_STYRING;
        this.addressBlock = INIT_STYRING;

    }
}