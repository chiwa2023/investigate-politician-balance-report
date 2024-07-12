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

    /** 公式公開終了該否 */
    isClosed: boolean;

    /** 公式政治資金収支報告書リンクId */
    publicationFormalItemId: number;

    /** 公式政治資金収支報告書同一識別コード */
    publicationFormalItemCode: number;

    constructor() {
        //初期値
        const initInt: number = 0;
        const initString: string = "";
        const initDate: string = "1980-01-01";
        const initBoolean: boolean = false;

        this.linkBalancesheetVersionId = initInt;
        this.linkBalancesheetVersionCode = initInt;
        this.politicalOrgnaizationId = initInt;
        this.politicalOrgnaizationCode = initInt;
        this.politicalOrgnaizationName = initString;
        this.offeringYear = initInt;
        this.offeringTimes = initInt;
        this.offeringDate = initDate;
        this.publicationDate = initDate;
        this.isDataSetFormal = initBoolean;
        this.isDataSetInformal = initBoolean;
        this.electionCommitionId = initInt;
        this.electionCommitionCode = initInt;
        this.electionCommitionName = initString;
        this.publicationLinkUrl = initString;
        this.isClosed = initBoolean;
        this.publicationFormalItemId = initInt;
        this.publicationFormalItemCode = initInt;
    }
}