import InputAddressDto from "../Input_address/inputAddressDto";

export default interface CorpNoInterface {

}

export default class CorpNoDto implements CorpNoInterface {

    /** 関連者企業・団体コード */
    corpNo: string;

    /** 法人番号 */
    houjinNo: string;

    /** 支店該当 */
    isShiten: boolean;

    /** 法人種別 */
    houjinSbts: string;

    /** 企業・団体名称 */
    corpName: string;

    /** 企業・団体名称かな */
    corpNameKana: string;

    /** 住所詳細Dto */
    inputAddress: InputAddressDto;

    /** 代表者コード */
    orgDelegateCode: string;

    /** 代表者名 */
    orgDelegate: string;

    constructor() {

        const INIT_STRING: string = "";

        this.corpNo = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.isShiten = false;
        this.houjinSbts = INIT_STRING;
        this.corpName = INIT_STRING;
        this.corpNameKana = INIT_STRING;
        this.inputAddress = new InputAddressDto();
        this.orgDelegateCode = INIT_STRING;
        this.orgDelegate = INIT_STRING;
    }
}