export default interface wkTblFukisaiBalancesheetInterface {

}

export default class WkTblFukisaiBalancesheetEntity implements wkTblFukisaiBalancesheetInterface {

        /** 収支報告書不記載ワークテーブルId */
        wkTblFukisaiBalancesheetId: number;

        /** 最新区分 */
        saishinKbn: number;

        /** 検索順キー */
        searchOrderKey: string;

        /** 報告年 */
        houkokuNen: number;

        /** 提出日 */
        offeringDate: Date;

        /** 文書同一識別コード収入 */
        documentCodeInput: number;

        /** 文書同一識別コード支出 */
        documentCodeOutput: number;

        /** 原文書政治団体代表者名 */
        daihyouName: string;

        /** 原文書政治団体名称 */
        dantaiName: string;

        /** 政治団体Id */
        politicalOrganizationId: number;

        /** 政治団体同一識別コード */
        politicalOrganizationCode: number;

        /** 政治団体名称 */
        politicalOrganizationName: string;

        /** 代表者関連者Id */
        relationPersonIdDelegate: number;

        /** 代表者関連者同一識別コード */
        relationPersonCodeDelegate: number;

        /** 代表者関連者名称 */
        relationPersonNameDelegate: string;

        /** 様式区分 */
        youshikiKbn: number;

        /** 様式枝区分項目 */
        youshikiEdaKbn: number;

        /** 項目名称 */
        itemName: string;

        /** 金額収入 */
        kingakuInput: number;

        /** 金額支出 */
        kingakuOutput: number;

        /** 発生日 */
        accrualDate: string;

        /** 発生日実値 */
        accrualDateValue: Date;

        /** 支出した相手先名 */
        partnerName: string;

        /** 支出した相手先住所 */
        partnerJuusho: string;

        /** 職業 */
        shokugyou: string;

        /** 取引相手関連者同一識別コード(政治団体) */
        relationPoliticalOrgId: number;

        /** 取引相手関連者名称(政治団体) */
        relationPoliticalOrgCode: number;

        /** 取引相手関連者名称(政治団体) */
        relationPoliticalOrgName: string;

        constructor() {
                // 初期データ
                const INIT_STRING = "";
                const INIT_NUMBER = 0;
                const INIT_DATE: Date = new Date(1947, 7, 28);


                this.wkTblFukisaiBalancesheetId = INIT_NUMBER;
                this.saishinKbn = INIT_NUMBER;
                this.searchOrderKey = INIT_STRING;
                this.houkokuNen = INIT_NUMBER;
                this.offeringDate = INIT_DATE;
                this.documentCodeInput = INIT_NUMBER;
                this.documentCodeOutput = INIT_NUMBER;
                this.daihyouName = INIT_STRING;
                this.dantaiName = INIT_STRING;
                this.politicalOrganizationId = INIT_NUMBER;
                this.politicalOrganizationCode = INIT_NUMBER;
                this.politicalOrganizationName = INIT_STRING;
                this.relationPersonIdDelegate = INIT_NUMBER;
                this.relationPersonCodeDelegate = INIT_NUMBER;
                this.relationPersonNameDelegate = INIT_STRING;
                this.youshikiKbn = INIT_NUMBER;
                this.youshikiEdaKbn = INIT_NUMBER;
                this.itemName = INIT_STRING;
                this.kingakuInput = INIT_NUMBER;
                this.kingakuOutput = INIT_NUMBER;
                this.accrualDate = INIT_STRING;
                this.accrualDateValue = INIT_DATE;
                this.partnerName = INIT_STRING;
                this.partnerJuusho = INIT_STRING;
                this.shokugyou = INIT_STRING;
                this.relationPoliticalOrgId = INIT_NUMBER;
                this.relationPoliticalOrgCode = INIT_NUMBER;
                this.relationPoliticalOrgName = INIT_STRING;








        }

}