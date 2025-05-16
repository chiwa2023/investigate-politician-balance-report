import InputAddressDto from "../Input_address/inputAddressDto"
import InputPersonNameInterface from "../input_person_name/inputPersonNameDto"
import InputPersonNameDto from "../input_person_name/inputPersonNameDto"
import type InputShokugyouInterface from "../input_shokugyou/inputShokugyouDto"
import InputShokugyouDto from "../input_shokugyou/inputShokugyouDto"

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
    inputName: InputPersonNameInterface;

    /** 入力職業 */
    inputShokugyou: InputShokugyouInterface;

    /** 職業の業種 */
    allShokugyou: string;

    /** 承認該否  */
    isApproval: boolean;

    constructor() {
        const INIT_STRING: string = "";

        this.personNo = INIT_STRING;
        this.juushoAll = INIT_STRING;
        this.nameAll = INIT_STRING;
        this.shokugyou = INIT_STRING;

        this.inputAddress = new InputAddressDto();
        this.inputName = new InputPersonNameDto();
        this.inputShokugyou = new InputShokugyouDto();

        this.allShokugyou = INIT_STRING;

        this.isApproval = false;

    }
}