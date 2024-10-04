export default interface PublicationInformalDataInterface {

        /** 非公式データId */
        publicationInformalId: number;

        /** 非公式データ同一識別コード */
        publicationInformalCode: number;
    
        /** 政治団体Id */
        orgnaizationId: number;
    
        /** 政治団体同一識別コード */
        orgnaizationCode: number;
    
        /** 政治団体名称 */
        orgnaizationName: string;
    
        /** 提出年 */
        offeringYear: number;
    
        /** 提出回数 */
        offeringTimes: number;
    
        /** 提出回数 */
        offeringDate: string;
    
        /** リンクラベル */
        linkLabel: string;
    
        /** リンクUrl */
        linkUrl: string;
    
        /** システムへの保存該否 */
        isImported: boolean;
    
        /** 公開済収支報告書Id */
        publishedBalancesheetId: number;
    
        /** 収支報告書同一識別コード */
        publishedBalancesheetCode: number;
    
    // eslint-disable-next-line semi
}

export default class PublicationInformalDataDto implements PublicationInformalDataInterface {

    /** 非公式データId */
    publicationInformalId: number;

    /** 非公式データ同一識別コード */
    publicationInformalCode: number;

    /** 政治団体Id */
    orgnaizationId: number;

    /** 政治団体同一識別コード */
    orgnaizationCode: number;

    /** 政治団体名称 */
    orgnaizationName: string;

    /** 提出年 */
    offeringYear: number;

    /** 提出回数 */
    offeringTimes: number;

    /** 提出回数 */
    offeringDate: string;

    /** リンクラベル */
    linkLabel: string;

    /** リンクUrl */
    linkUrl: string;

    /** システムへの保存該否 */
    isImported: boolean;

    /** 公開済収支報告書Id */
    publishedBalancesheetId: number;

    /** 収支報告書同一識別コード */
    publishedBalancesheetCode: number;


    constructor() {
        //初期値
        const initInt: number = 0;
        const initString: string = "";
        const initDate: string = "1980-01-01";
        const initBoolean: boolean = false;

        this.publicationInformalId = initInt;
        this.publicationInformalCode = initInt;
        this.orgnaizationId = initInt;
        this.orgnaizationCode = initInt;
        this.orgnaizationName = initString;
        this.offeringYear = initInt;
        this.offeringTimes = initInt;
        this.offeringDate = initDate;
        this.linkLabel = initString;
        this.linkUrl = initString;
        this.isImported = initBoolean;
        this.publishedBalancesheetId = initInt;
        this.publishedBalancesheetCode = initInt;
    }
}