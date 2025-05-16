export default interface InputShokugyouInterface {

}

export default class InputShokugyouDto implements InputShokugyouInterface {

    /** 職業全表記 */
    allShokugyou: string;

    /** 職業の業種 */
    gyoushu: string;

    /** 職業の役職 */
    yakushoku: string;

    /** ユーザ記述の職業 */
    shokugyouUserWrite: string;


    /** 企業番号 */
    corpNo: string;
    /** 企業所在地 */
    corpAddress: string;
    /** 企業名 */
    corpName: string;

    constructor() {
        const INIT_STRING: string = "";

        this.allShokugyou = INIT_STRING;
        this.gyoushu = INIT_STRING;
        this.yakushoku = INIT_STRING;
        this.shokugyouUserWrite = INIT_STRING;

        this.corpNo = INIT_STRING;
        this.corpAddress = INIT_STRING;
        this.corpName = INIT_STRING;
    }

}