import type PersonNoInterface from "./personNoDto"
import PersonNoDto from "./personNoDto"

export default interface PersonNoApprovalInterface extends PersonNoInterface {

}

export default class PersonNoApprovalDto extends PersonNoDto implements PersonNoApprovalInterface {

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