export default interface OfferingPartyUsage0802And0803ReportInterface {

}

export default class OfferingPartyUsage0802And0803ReportEntity implements OfferingPartyUsage0802And0803ReportInterface {



    /** 使途報告書様式8その2と3Id */
    partyUsage0802And0803ReportId: number;

    /** 使途報告書様式8その2と3同一識別コード */
    partyUsage0802And0803ReportCode: number;

    /** 最新区分 */
    saishinKbn: number;

    /** 文書同一識別コード */
    documentCode: number;

    /** 報告年度 */
    nendo: number;

    /** 提出日 */
    offeringDate: Date;

    /** 政治団体Id */
    politicalOrganizationId: number;

    /** 政治団体同一識別コード */
    politicalOrganizationCode: number;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    /** 原文書政治団体名称 */
    dantaiName: string;

    /** 原文書政治団体代表者名 */
    daihyouName: string;

    /** 関連者区分 */
    relationKbn: number;

    /** 代表者関連者Id */
    relationPersonIdDelegate: number;

    /** 代表者関連者同一識別コード */
    relationPersonCodeDelegate: number;

    /** 代表者関連者名称 */
    relationPersonNameDelegate: string;

    /** 全政治活動全合計 */
    totalAllActionAll: number;

    /** 全政治活動交付金充当 */
    totalAllActionJutoKoufukin: number;

    /** 全政治活動自党基金充当 */
    totalAllActionJutoMyFunds: number;

    /** 全政治活動備考 */
    totalAllActionBikou: string;

    /** 全総計全合計 */
    totalAllAmountAll: number;

    /** 全総計交付金充当 */
    totalAllAmountJutoKoufukin: number;

    /** 総計自党基金充当 */
    totalAllAmountJutoMyFunds: number;

    /** 全総計備考 */
    totalAllAmountBikou: string;

    /** 全事業総計全合計 */
    totalAllJigyouAll: number;

    /** 全事業総計交付金充当 */
    totalAllJigyouJutoKoufukin: number;

    /** 全事業自党基金充当 */
    totalAllJigyouJutoMyFunds: number;

    /** 全事業備考 */
    totalAllJigyouBikou: string;

    /** 備品消耗品費全合計 */
    totalBihinAll: number;

    /** 備品消耗品費交付金充当 */
    totalBihinJutoKoufukin: number;

    /** 備品消耗品費自党基金充当 */
    totalBihinJutoMyFunds: number;

    /** 備品消耗品費備考 */
    totalBihinBikou: string;

    /** 調査研究費全合計 */
    totalChousaAll: number;

    /** 調査研究費交付金充当 */
    totalChousaJutoKoufukin: number;

    /** 調査研究費自党基金充当 */
    totalChousaJutoMyFunds: number;

    /** 調査研究費備考 */
    totalChousaBikou: string;

    /** 事務所費全合計 */
    totalJimushoAll: number;

    /** 事務所費交付金充当 */
    totalJimushoJutoKoufukin: number;

    /** 事務所費自党基金充当 */
    totalJimushoJutoMyFunds: number;

    /** 事務所費備考 */
    totalJimushoBikou: string;

    /** 人件費全合計 */
    totalJinkenhiAll: number;

    /** 人件費交付金充当 */
    totalJinkenhiJutoKoufukin: number;

    /** 人件費自党基金充当 */
    totalJinkenhiJutoMyFunds: number;

    /** 人件費備考 */
    totalJinkenhiBikou: string;

    /** 経費全合計 */
    totalKeihiAll: number;

    /** 経費交付金充当 */
    totalKeihiJutoKoufukin: number;

    /** 経費自党基金充当 */
    totalKeihiJutoMyFunds: number;

    /** 経費備考 */
    totalKeihiBikou: string;

    /** 寄附全合計 */
    totalKifuAll: number;

    /** 寄附交付金充当 */
    totalKifuJutoKoufukin: number;

    /** 寄附自党基金充当 */
    totalKifuJutoMyFunds: number;

    /** 寄附備考 */
    totalKifuBikou: string;

    /** 機関誌発行全合計 */
    totalKikanshiAll: number;

    /** 機関誌発行交付金充当 */
    totalKikanshiJutoKoufukin: number;

    /** 機関誌発行自党基金充当 */
    totalKikanshiJutoMyFunds: number;

    /** 機関誌発行備考 */
    totalKikanshiBikou: string;

    /** 光熱費全合計 */
    totalKounetsuhiAll: number;

    /** 光熱費交付金充当 */
    totalKounetsuhiJutoKoufukin: number;

    /** 光熱費自党基金充当 */
    totalKounetsuhiJutoMyFunds: number;

    /** 光熱費備考 */
    totalKounetsuhiBikou: string;

    /** パーティ費全合計 */
    totalPartyAll: number;

    /** パーティ費交付金充当 */
    totalPartyJutoKoufukin: number;

    /** パーティ費自党基金充当 */
    totalPartyJutoMyFunds: number;

    /** パーティ費備考 */
    totalPartyBikou: string;

    /** 宣伝費全合計 */
    totalSendenAll: number;

    /** 宣伝費交付金充当 */
    totalSendenJutoKoufukin: number;

    /** 宣伝費自党基金充当 */
    totalSendenJutoMyFunds: number;

    /** 宣伝費備考 */
    totalSendenBikou: string;

    /** 選挙費全合計 */
    totalSenkyoAll: number;

    /** 選挙費交付金充当 */
    totalSenkyoJutoKoufukin: number;

    /** 選挙費自党基金充当 */
    totalSenkyoJutoMyFunds: number;

    /** 選挙費備考 */
    totalSenkyoBikou: string;

    /** 支部交付金全合計 */
    totalShibuKoufuAll: number;

    /** 支部交付金交付金充当 */
    totalShibuKoufuJutoKoufukin: number;

    /** 支部交付金自党基金充当 */
    totalShibuKoufuJutoMyFunds: number;

    /** 支部交付金備考 */
    totalShibuKoufuBikou: string;

    /** その他事業費全合計 */
    totalSonotaJigyouAll: number;

    /** その他事業費交付金充当 */
    totalSonotaJigyouJutoKoufukin: number;

    /** その他事業費自党基金充当 */
    totalSonotaJigyouJutoMyFunds: number;

    /** その他事業費備考 */
    totalSonotaJigyouBikou: string;

    /** その他経費全合計 */
    totalSonotaKeihiAll: number;

    /** その他経費交付金充当 */
    totalSonotaKeihiJutoKoufukin: number;

    /** その他経費自党基金充当 */
    totalSonotaKeihiJutoMyFunds: number;

    /** その他経費備考 */
    totalSonotaKeihiBikou: string;

    /** 組織費全合計 */
    totalSoshikiAll: number;

    /** 組織費交付金充当 */
    totalSoshikiJutoKoufukin: number;

    /** 組織費自党基金充当 */
    totalSoshikiJutoMyFunds: number;

    /** 組織費備考 */
    totalSoshikiBikou: string;

    /** 政党交付金（支部政党交付金）の総額 */
    partyAllKoufukin: number;

    /** 前年末政党基金（支部基金）残高 */
    lastYearRest: number;

    /** 政党交付金（支部政党交付金）による支出総額 */
    outcomeKoufukinAll: number;

    /** 政党交付金（支部政党交付金）支出充当額総額 */
    outcomeKoufukin: number;

    /** 政党基金（支部基金）支出充当額総額 */
    outcomeShibuKikin: number;

    /** 基金取り崩し */
    withdrawalFunds: number;

    /** 政党基金（支部基金）積立総額 */
    accumulateFunds: number;

    /** 政党基金（支部基金）の運用により収受した果実の総額 */
    fundsSumGain: number;

    /** 本年末等政党基金（支部基金）残高 */
    thisYearRest: number;

    /** 備 考 */
    bikouDiffer: number;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザ同一識別コード */
    insertUserCode: number;

    /** 挿入ユーザ姓名 */
    insertUserName: string;

    /** 挿入タイムスタンプ */
    insertTimestamp: Date;

    /** 更新ユーザId */
    updateUserId: number;

    /** 更新ユーザ同一識別コード */
    updateUserCode: number;

    /** 更新ユーザ姓名 */
    updateUserName: string;

    /** 更新タイムスタンプ */
    updateTimestamp: Date;

    constructor() {

        // 初期データ
        const INIT_STRING = "";
        const INIT_NUMBER = 0;
        const INIT_DATE: Date = new Date(1947, 7, 28);

        this.partyUsage0802And0803ReportId = INIT_NUMBER;
        this.partyUsage0802And0803ReportCode = INIT_NUMBER;
        this.saishinKbn = INIT_NUMBER;
        this.documentCode = INIT_NUMBER;
        this.nendo = INIT_NUMBER;
        this.offeringDate = INIT_DATE;
        this.politicalOrganizationId = INIT_NUMBER;
        this.politicalOrganizationCode = INIT_NUMBER;
        this.politicalOrganizationName = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.daihyouName = INIT_STRING;
        this.relationKbn = INIT_NUMBER;
        this.relationPersonIdDelegate = INIT_NUMBER;
        this.relationPersonCodeDelegate = INIT_NUMBER;
        this.relationPersonNameDelegate = INIT_STRING;
        this.totalAllActionAll = INIT_NUMBER;
        this.totalAllActionJutoKoufukin = INIT_NUMBER;
        this.totalAllActionJutoMyFunds = INIT_NUMBER;
        this.totalAllActionBikou = INIT_STRING;
        this.totalAllAmountAll = INIT_NUMBER;
        this.totalAllAmountJutoKoufukin = INIT_NUMBER;
        this.totalAllAmountJutoMyFunds = INIT_NUMBER;
        this.totalAllAmountBikou = INIT_STRING;
        this.totalAllJigyouAll = INIT_NUMBER;
        this.totalAllJigyouJutoKoufukin = INIT_NUMBER;
        this.totalAllJigyouJutoMyFunds = INIT_NUMBER;
        this.totalAllJigyouBikou = INIT_STRING;
        this.totalBihinAll = INIT_NUMBER;
        this.totalBihinJutoKoufukin = INIT_NUMBER;
        this.totalBihinJutoMyFunds = INIT_NUMBER;
        this.totalBihinBikou = INIT_STRING;
        this.totalChousaAll = INIT_NUMBER;
        this.totalChousaJutoKoufukin = INIT_NUMBER;
        this.totalChousaJutoMyFunds = INIT_NUMBER;
        this.totalChousaBikou = INIT_STRING;
        this.totalJimushoAll = INIT_NUMBER;
        this.totalJimushoJutoKoufukin = INIT_NUMBER;
        this.totalJimushoJutoMyFunds = INIT_NUMBER;
        this.totalJimushoBikou = INIT_STRING;
        this.totalJinkenhiAll = INIT_NUMBER;
        this.totalJinkenhiJutoKoufukin = INIT_NUMBER;
        this.totalJinkenhiJutoMyFunds = INIT_NUMBER;
        this.totalJinkenhiBikou = INIT_STRING;
        this.totalKeihiAll = INIT_NUMBER;
        this.totalKeihiJutoKoufukin = INIT_NUMBER;
        this.totalKeihiJutoMyFunds = INIT_NUMBER;
        this.totalKeihiBikou = INIT_STRING;
        this.totalKifuAll = INIT_NUMBER;
        this.totalKifuJutoKoufukin = INIT_NUMBER;
        this.totalKifuJutoMyFunds = INIT_NUMBER;
        this.totalKifuBikou = INIT_STRING;
        this.totalKikanshiAll = INIT_NUMBER;
        this.totalKikanshiJutoKoufukin = INIT_NUMBER;
        this.totalKikanshiJutoMyFunds = INIT_NUMBER;
        this.totalKikanshiBikou = INIT_STRING;
        this.totalKounetsuhiAll = INIT_NUMBER;
        this.totalKounetsuhiJutoKoufukin = INIT_NUMBER;
        this.totalKounetsuhiJutoMyFunds = INIT_NUMBER;
        this.totalKounetsuhiBikou = INIT_STRING;
        this.totalPartyAll = INIT_NUMBER;
        this.totalPartyJutoKoufukin = INIT_NUMBER;
        this.totalPartyJutoMyFunds = INIT_NUMBER;
        this.totalPartyBikou = INIT_STRING;
        this.totalSendenAll = INIT_NUMBER;
        this.totalSendenJutoKoufukin = INIT_NUMBER;
        this.totalSendenJutoMyFunds = INIT_NUMBER;
        this.totalSendenBikou = INIT_STRING;
        this.totalSenkyoAll = INIT_NUMBER;
        this.totalSenkyoJutoKoufukin = INIT_NUMBER;
        this.totalSenkyoJutoMyFunds = INIT_NUMBER;
        this.totalSenkyoBikou = INIT_STRING;
        this.totalShibuKoufuAll = INIT_NUMBER;
        this.totalShibuKoufuJutoKoufukin = INIT_NUMBER;
        this.totalShibuKoufuJutoMyFunds = INIT_NUMBER;
        this.totalShibuKoufuBikou = INIT_STRING;
        this.totalSonotaJigyouAll = INIT_NUMBER;
        this.totalSonotaJigyouJutoKoufukin = INIT_NUMBER;
        this.totalSonotaJigyouJutoMyFunds = INIT_NUMBER;
        this.totalSonotaJigyouBikou = INIT_STRING;
        this.totalSonotaKeihiAll = INIT_NUMBER;
        this.totalSonotaKeihiJutoKoufukin = INIT_NUMBER;
        this.totalSonotaKeihiJutoMyFunds = INIT_NUMBER;
        this.totalSonotaKeihiBikou = INIT_STRING;
        this.totalSoshikiAll = INIT_NUMBER;
        this.totalSoshikiJutoKoufukin = INIT_NUMBER;
        this.totalSoshikiJutoMyFunds = INIT_NUMBER;
        this.totalSoshikiBikou = INIT_STRING;
        this.partyAllKoufukin = INIT_NUMBER;
        this.lastYearRest = INIT_NUMBER;
        this.outcomeKoufukinAll = INIT_NUMBER;
        this.outcomeKoufukin = INIT_NUMBER;
        this.outcomeShibuKikin = INIT_NUMBER;
        this.withdrawalFunds = INIT_NUMBER;
        this.accumulateFunds = INIT_NUMBER;
        this.fundsSumGain = INIT_NUMBER;
        this.thisYearRest = INIT_NUMBER;
        this.bikouDiffer = INIT_NUMBER;
        this.insertUserId = INIT_NUMBER;
        this.insertUserCode = INIT_NUMBER;
        this.insertUserName = INIT_STRING;
        this.insertTimestamp = INIT_DATE;
        this.updateUserId = INIT_NUMBER;
        this.updateUserCode = INIT_NUMBER;
        this.updateUserName = INIT_STRING;
        this.updateTimestamp = INIT_DATE;

    }

}
