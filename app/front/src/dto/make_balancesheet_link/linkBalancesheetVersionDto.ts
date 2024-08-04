export default interface LinkBalancesheetVersionInte {


    // eslint-disable-next-line semi
}

export default class LinkBalancesheetVersionDto {

    /** 収支報告書公開版番号Id */
    linkBalancesheetVersionId: number;
    /** 収支報告書公開版番号同一識別コード */
    linkBalancesheetVersionCode: number;

    /** 政治団体Id */
    politicalOrgnaizationId: number;
    /** 政治団体同一識別コード */
    politicalOrgnaizationCode: number;
    /** 政治団体名称 */
    politicalOrgnaizationName: string;

    /** 提出年 */
    offeringYear: number;

    /** 提出回数 */
    offeringTimes: number;

    /** 提出日 */
    offeringDate: string;

    /** 公開日 */
    publicationDate: string;

    /** 公式データセット該否 */
    isDataSetFormal: boolean;

    /** 非公式データセット該否 */
    isDataSetInformal: boolean;

    /** 提出選挙管理委員会Id */
    electionCommitionId: number;
    /** 提出選挙管理委員会同一識別コード */
    electionCommitionCode: number;
    /** 提出選挙管理委員会名称 */
    electionCommitionName: string;

    /** 公式(選挙管理委員会)掲載リンク */
    publicationLinkUrl: string;

    /** 公式政治資金収支報告書リンクId */
    publicationFormalItemId: number;

    /** 公式政治資金収支報告書同一識別コード */
    publicationFormalItemCode: number;

    isClosed:boolean;

    constructor() {
        //初期値
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_DATE: string = "1980-01-01";
        const INIT_BOOLEAN: boolean = false;

        this.linkBalancesheetVersionId = INIT_NUMBER;
        this.linkBalancesheetVersionCode = INIT_NUMBER;
        this.politicalOrgnaizationId = INIT_NUMBER;
        this.politicalOrgnaizationCode = INIT_NUMBER;
        this.politicalOrgnaizationName = INIT_STRING;
        this.offeringYear = INIT_NUMBER;
        this.offeringTimes = INIT_NUMBER;
        this.offeringDate = INIT_DATE;
        this.publicationDate = INIT_DATE;
        this.isDataSetFormal = INIT_BOOLEAN;
        this.isDataSetInformal = INIT_BOOLEAN;
        this.electionCommitionId = INIT_NUMBER;
        this.electionCommitionCode = INIT_NUMBER;
        this.electionCommitionName = INIT_STRING;
        this.publicationLinkUrl = INIT_STRING;
        this.publicationFormalItemId = INIT_NUMBER;
        this.publicationFormalItemCode = INIT_NUMBER;
        this.isClosed = INIT_BOOLEAN;
    }
}