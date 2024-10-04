export default interface ElectionCommissionLeastInterface {
    /** 政治団体Id */
    electionCommissionId: number;

    /** 政治団体同一識別コード */
    electionCommissionCode: number;

    /** 政治団体名称 */
    electionCommissionName: string;

    // eslint-disable-next-line semi
}

export default class ElectionCommissionLeastDto implements ElectionCommissionLeastInterface {
    /** 政治団体Id */
    electionCommissionId: number;

    /** 政治団体同一識別コード */
    electionCommissionCode: number;

    /** 政治団体名称 */
    electionCommissionName: string;

    constructor() {
        this.electionCommissionId = 0;
        this.electionCommissionCode = 0;
        this.electionCommissionName = "";
    }
}