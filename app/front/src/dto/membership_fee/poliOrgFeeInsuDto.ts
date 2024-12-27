export default class PoliOrgFeeInsuDto {

    /** 政治団体Id */
    politicalOrgnaizationId: number;
    /** 政治団体同一識別コード */
    politicalOrgnaizationCode: number;
    /** 政治団体名称 */
    politicalOrgnaizationName: string;

    /** 政治団体代表者Id */
    poliOrgDaihyoushaId: number;
    /** 政治団体代表者同一識別コード */
    poliOrgDaihyoushaCode: number;
    /** 政治団体代表者名称 */
    poliOrgDaihyoushaName: string;

    /** 評価値 */
    rating: string;

    /** 党費 */
    fee: number;
    /** 員数 */
    insu: number;
    /** 頭割り */
    average: number;




    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.politicalOrgnaizationId = INIT_NUMBER;
        this.politicalOrgnaizationCode = INIT_NUMBER;
        this.politicalOrgnaizationName = INIT_STRING;

        this.poliOrgDaihyoushaId = INIT_NUMBER;
        this.poliOrgDaihyoushaCode = INIT_NUMBER;
        this.poliOrgDaihyoushaName = INIT_STRING;

        this.rating = INIT_STRING;
        this.fee = INIT_NUMBER;
        this.insu = INIT_NUMBER;
        this.average = INIT_NUMBER;
    }

}