import CorpNoInterface from "./corpNoDto";
import CorpNoDto from "./corpNoDto";

export default interface CorpNoApprovalInterface extends CorpNoInterface {

}

export default class CorpNoApprovalDto extends CorpNoDto implements CorpNoApprovalInterface {

    /** アドレス・ベース・レジストリ住所郵便番号まで */
    rsdtAddressPostl: string;

    /** アドレス・ベース・レジストリ住所番地まで */
    rsdtAddressBlock: string;

    /** アドレス・ベース・レジストリ住所建物 */
    rsdtAddressBuilding: string;

    constructor() {
        super();

        const INIT_STRING: string = "";

        this.rsdtAddressPostl = INIT_STRING;
        this.rsdtAddressBlock = INIT_STRING;
        this.rsdtAddressBuilding = INIT_STRING;

    }
}