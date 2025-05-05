export default interface InputOrgNameInterface {

}
export default class InputOrgNameDto implements InputOrgNameInterface {

    /** 団体名かな */
    orgNameKana: string;

    /** 団体名 */
    orgName: string;

    constructor() {
        const INIT_STRING: string = "";
        this.orgNameKana = INIT_STRING;
        this.orgName = INIT_STRING;

    }
}