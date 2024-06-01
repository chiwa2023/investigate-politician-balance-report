import PublicationInformalDataInterface from "./publicationInformalDataDto";

export default interface PublicationFormalItemInterface {


    // eslint-disable-next-line semi
}

export default class PublicationFormalItemDto {

    /** 公式政治資金収支報告書リンクId */
    publicationFormalItemId: number;

    /** 公式政治資金収支報告書同一識別コード */
    publicationFormalItemCode: number;

    /** データセット該否 */
    isDataSet: boolean;

    /** 政治団体Id */
    politicalOrgnaizationId: number;

    /** 政治団体同一識別コード */
    politicalOrgnaizationCode: number;

    /** 政治団体名称 */
    politicalOrgnaizationName: string;

    /** 提出選挙管理委員会Id */
    electionCommitionId: number;

    /** 提出選挙管理委員会同一識別コード */
    electionCommitionCode: number;

    /** 提出選挙管理委員会名称 */
    electionCommitionName: string;

    /** 提出年 */
    offeringYear: number;

    /** 提出回数 */
    offeringTimes: number;

    /** 提出日 */
    offeringDate: string;

    /** 公開日 */
    publishingDate: string;

    /** 公式サイト公開該否 */
    isClosed: boolean;

    /** 公式(選挙管理委員会)掲載リンク */
    publicationLinkUrl: string;

    /** システムへの保存該否 */
    isImported: boolean;

    /** 公開済収支報告書Id */
    publicatedBalancesheetId: number;

    /** 収支報告書同一識別コード */
    publicatedBalancesheetCode: number;

    /** 非公式データリスト */
    listInformalData: PublicationInformalDataInterface[];

    constructor() {
        //初期値
        const initInt: number = 0;
        const initString: string = "";
        const initDate: string = "1980-01-01";
        const initBoolean: boolean = false;

        this.publicationFormalItemId = initInt;
        this.publicationFormalItemCode = initInt;
        this.politicalOrgnaizationId = 0;
        /*データ該否は普段は生成された時点でtrue、ただし欠損検出で
        データが存在しないことを明示するときだけは後でfalseに上書き */
        this.isDataSet = true;
        this.politicalOrgnaizationCode = initInt;
        this.politicalOrgnaizationName = initString;
        this.electionCommitionId = initInt;
        this.electionCommitionCode = initInt;
        this.electionCommitionName = initString;
        this.offeringYear = initInt;
        this.offeringTimes = initInt;
        this.offeringDate = initDate;
        this.publishingDate = initDate;
        this.publicationLinkUrl = initString;
        this.isImported = initBoolean;
        this.publicatedBalancesheetId = initInt;
        this.publicatedBalancesheetCode = initInt;
        this.listInformalData = [];
        this.isClosed = initBoolean;
    }
}