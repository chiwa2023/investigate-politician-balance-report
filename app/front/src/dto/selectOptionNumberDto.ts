export default interface SelectOptionNumberInterface {

}
export default class SelectOptionNumberDto implements SelectOptionNumberInterface {

    /** 選択肢値 */
    value: number;

    /** 選択肢表示テキスト */
    text: string;

    constructor() {
        this.value = 0;
        this.text = "";
    }
}