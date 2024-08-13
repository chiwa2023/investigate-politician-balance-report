export default interface PoliticalPartyLeastInterface {
    /** 政党Id */
    politicalPartyId: number;

    /** 政党同一識別コード */
    politicalPartyCode: number;

    /** 政党名称 */
    politicalPartyName: string;

    // eslint-disable-next-line semi
}

export default class PoliticalPartyLeastDto implements PoliticalPartyLeastInterface {

    /** 政党Id */
    politicalPartyId: number;

    /** 政党同一識別コード */
    politicalPartyCode: number;

    /** 政党名称 */
    politicalPartyName: string;

    /**
     * コンストラクタ
     * @param impl インターフェイス実装
     */
    constructor(impl?: PoliticalPartyInterface) {

        const initString: string = "";
        const initNumber: number = 0;

        if (impl !== undefined) {
            this.politicalPartyId = impl.politicalPartyId;
            this.politicalPartyCode = impl.politicalPartyCode;
            this.politicalPartyName = impl.politicalPartyName;
        }
        else {
            this.politicalPartyId = initNumber;
            this.politicalPartyCode = initNumber;
            this.politicalPartyName = initString;
        }
    }
}