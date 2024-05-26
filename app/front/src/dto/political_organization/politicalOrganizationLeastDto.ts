export default interface PoliticalOrganizationLeastInterface {
    /** 政治団体Id */
    politicalOrganizationId: number;

    /** 政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    // eslint-disable-next-line semi
}

export default class PoliticalOrganizationLeastDto implements PoliticalOrganizationLeastInterface {
    /** 政治団体Id */
    politicalOrganizationId: number;

    /** 政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    constructor() {
        this.politicalOrganizationId = 0;
        this.politicalOrganizationCode = 0;
        this.politicalOrganizationName = "";
    }
}