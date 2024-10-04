export default interface SelectOptionInterface {
    value: string;
    text: string;
}

/**
 * HTMLにおける、Selectボタンの選択(option)項目データDto
 *
 * @class SelectOptionDto
 */
export default class SelectOptionDto implements SelectOptionInterface{

    /**
     * option項目の値
     * @type {string}
     * @memberof SelectOption
     */
    value: string;

    /**
     * option項目の表示内容
     * @type {string}
     * @memberof SelectOption
     */
    text: string;

    /**
     * Creates an instance of SelectOption.
     * @param {string} value option項目に設定したい値
     * @param {string} text option項目に設定したいテキスト
     * @memberof SelectOption
     */
    constructor() {
        this.value = "";
        this.text = "";
    }
}
