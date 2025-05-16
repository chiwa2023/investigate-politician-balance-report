import InputAddressDto from "../Input_address/inputAddressDto";
import InputOrgNameInterface from "../input_org_name/inputOrgNameDto";
import InputOrgNameDto from "../input_org_name/inputOrgNameDto";

export default interface PoliOrgNoInterface {

}

export default class PoliOrgNoDto implements PoliOrgNoInterface {

    /** 政治団体番号 */
    poliOrgNo: string;

    inputName:InputOrgNameInterface;

    /** 政治団体住所 */
    inputAddress: InputAddressDto;

    /** 代表者番号 */
    delegateNo: string;

    /** 代表者氏名 */
    delegateName: string;

    /** 会計責任者番号 */
    accountMgrNo: string;

    /** 会計責任者氏名 */
    accountMgrName: string;

        /** 承認該否  */
        isApproval: boolean;

    constructor() {

        const INIT_STRING: string = "";

        this.poliOrgNo = INIT_STRING;
        this.inputName = new InputOrgNameDto();
        this.inputAddress = new InputAddressDto();
        this.delegateNo = INIT_STRING;
        this.delegateName = INIT_STRING;
        this.accountMgrNo = INIT_STRING;
        this.accountMgrName = INIT_STRING;

        this.isApproval = false;

    }
}