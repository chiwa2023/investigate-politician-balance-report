import InputAddressDto from "../Input_address/inputAddressDto"
import InputPersonNameInterface from "../input_person_name/inputPersonNameDto"
import InputPersonNameDto from "../input_person_name/inputPersonNameDto"

export default interface PersonNoInterface {

}

export default class PersonNoDto implements PersonNoInterface {

    /** 関連者個人番号 */
    personNo: string

    /** 表示住所 */
    juushoAll: string

    /** 氏名 */
    nameAll: string

    /** 職業 */
    shokugyou: string

    /** 入力職業 */
    inputAddress: InputAddressDto;

    /** 入力姓名 */
    inputName:InputPersonNameInterface;

    /** 職業の業種 */
    gyoushu: string;
    /** 職業の役職 */
    yakushoku: string;
    /** ユーザ記述の職業 */
    shokugyouUserWrite: string;

    constructor() {
        const INIT_STRING: string = "";

        this.personNo = INIT_STRING;
        this.juushoAll = INIT_STRING;
        this.nameAll = INIT_STRING;
        this.shokugyou = INIT_STRING;

        this.inputAddress = new InputAddressDto();
        this.inputName = new InputPersonNameDto();        

        this.gyoushu = INIT_STRING;
        this.yakushoku = INIT_STRING;
        this.shokugyouUserWrite = INIT_STRING;

    }
}