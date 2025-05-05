export default interface InputPersonNameInterface {

}
export default class InputPersonNameDto implements InputPersonNameInterface {

    /**　姓名の姓  */
    lastName: string;

    /**　姓名の名  */
    firstName: string;

    /**　姓名  */
    allName: string;

    /**　姓名のミドルネーム  */
    middleName: string;

    /**　姓名の姓かな  */
    lastNameKana: string;

    /**　姓名の名かな  */
    firstNameKana: string;

    /**　姓名のミドルネームかな  */
    middleNameKana: string;

    /**　姓名かな  */
    allNameKana: string;

    constructor() {
        const INIT_STRING = "";

        this.lastName = INIT_STRING;
        this.firstName = INIT_STRING;
        this.middleName = INIT_STRING;
        this.allName = INIT_STRING;

        this.lastNameKana = INIT_STRING;
        this.firstNameKana = INIT_STRING;
        this.middleNameKana = INIT_STRING;
        this.allNameKana = INIT_STRING;
    }

}