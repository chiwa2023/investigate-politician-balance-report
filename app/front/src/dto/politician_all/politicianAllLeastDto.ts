export default interface PoliticianAllLeastInterface {
    /** 政治家Id */
    politicianAllId: number;

    /** 政治家同一識別コード */
    politicianAllCode: number;

    /** 政治家名称 */
    politicianAllName: string;

    /** 政治家名称ふりがな */
    politicianAllNameKana: string;

    /** 議会Id */
    parliamentOrganizationId: number;

    /** 議会同一識別コード */
    parliamentOrganizationCode: number;

    /** 議会名称 */
    parliamentOrganizationName: string;

    /** 議会選挙区Id */
    electionDistrictId: number;

    /** 議会選挙区同一識別コード */
    electionDistrictCode: number;

    /** 議会選挙区名称 */
    electionDistrictName: string;

    // eslint-disable-next-line semi
}

export default class PoliticianAllDto implements PoliticianAllLeastInterface {
    /** 政治家Id */
    politicianAllId: number;

    /** 政治家同一識別コード */
    politicianAllCode: number;

    /** 政治家名称 */
    politicianAllName: string;

    /** 政治家名称ふりがな */
    politicianAllNameKana: string;

    /** 議会Id */
    parliamentOrganizationId: number;

    /** 議会同一識別コード */
    parliamentOrganizationCode: number;

    /** 議会名称 */
    parliamentOrganizationName: string;

    /** 議会選挙区Id */
    electionDistrictId: number;

    /** 議会選挙区同一識別コード */
    electionDistrictCode: number;

    /** 議会選挙区名称 */
    electionDistrictName: string;

    /**
     * コンストラクタ
     * @param impl インターフェイス実装 
     */
    constructor(impl?: PoliticianAllLeastInterface) {

        const initString: string = "";
        const initNumber: number = 0;

        if (impl !== undefined) {
            this.politicianAllId = impl.politicianAllId;
            this.politicianAllCode = impl.politicianAllCode;
            this.politicianAllName = impl.politicianAllName;
            this.politicianAllNameKana = impl.politicianAllNameKana;
            this.parliamentOrganizationId = impl.parliamentOrganizationId;
            this.parliamentOrganizationCode = impl.parliamentOrganizationCode;
            this.parliamentOrganizationName = impl.parliamentOrganizationName;
            this.electionDistrictId = impl.electionDistrictId;
            this.electionDistrictCode = impl.electionDistrictCode;
            this.electionDistrictName = impl.electionDistrictName;
        } else {
            this.politicianAllId = initNumber;
            this.politicianAllCode = initNumber;
            this.politicianAllName = initString;
            this.politicianAllNameKana = initString;
            this.parliamentOrganizationId = initNumber;
            this.parliamentOrganizationCode = initNumber;
            this.parliamentOrganizationName = initString;
            this.electionDistrictId = initNumber;
            this.electionDistrictCode = initNumber;
            this.electionDistrictName = initString;
        }
    }
}