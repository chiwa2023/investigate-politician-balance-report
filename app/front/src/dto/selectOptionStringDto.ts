export default interface SelectOptionStringInterface {

}
export default class SelectOptionStringDto implements SelectOptionStringInterface {

    /** 選択肢値 */
    value: string;

    /** 選択肢表示テキスト */
    text: string;

    constructor() {
        const INIT_STRING: string = "";
        this.value = INIT_STRING;
        this.text = INIT_STRING;
    }
}