import keinenUsageSurface02103SummaryByYearInterface from "../../../dto/keinen_henka/keinenUsageSurface02103SummaryByYearDto";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";

export default class CreateUsageSummaryChartData {

    /** 未選択  */
    static readonly NO_SELECT: string = "未選択";

    // 収入
    // (1)政党交付金(支部政党交付金)の総額 
    static readonly SHUNYU_PARTY_ALL: string = "(1)政党交付金(支部政党交付金)の総額";
    //  (2)前年末政党基金(支部基金)残高 
    static readonly SHUNYU_LAST_YEAR: string = "(2)前年末政党基金(支部基金)残高";
    // (3)政党交付金(支部政党交付金)による支出総額(4)+(5) 
    static readonly SHUNYU_KOUFUKIN_ALL: string = "(3)政党交付金(支部政党交付金)による支出総額";
    // (4)政党交付金(支部政党交付金)支出充当総額
    static readonly SHUNYU_KOUFUKIN: string = "(4)政党交付金(支部政党交付金)支出充当総額";
    //  (5)政党基金(支部基金)支出充当総額 
    static readonly SHUNYU_SHIBUKIKIN: string = "(5)政党基金(支部基金)支出充当総額";
    //  (6)政党基金(支部基金)の積み立てに充てるために取り崩した政党基金(支部基金)の額
    static readonly SHUNYU_WITHDORAL_FUNDS: string = "(6)政党基金(支部基金)の積み立てに充てるために取り崩した政党基金(支部基金)の額";
    // (7)政党基金(支部基金)の積み立て総額 
    static readonly SHUNYU_ACCUMLATE_FUNDS: string = "(7)政党基金(支部基金)の積み立て総額";
    // 政党基金(支部基金)の運用により収受した果実の総額
    static readonly SHUNYU_FUNDS_GAIN: string = "政党基金(支部基金)の運用により収受した果実の総額";
    // (8)本年末政党支部基金(支部基金)残高(2)-(5)-(6)+(7)
    static readonly SHUNYU_THIS_YEAR: string = "(8)本年末政党支部基金(支部基金)残高";
    // 備考
    static readonly SHUNYU_BIKO_DIFFER: string = "備考";


    // 支出
    // 全政治活動全合計
    static readonly TOTAL_ACTION: string = "全政治活動費項目";
    // 全総計全合計
    static readonly ALL_AMOUNT: string = "全総計項目";
    // 全事業総計全合計
    static readonly ALL_JIGYOU: string = "全事業項目";
    // 備品消耗品費全合計
    static readonly BIHINHI: string = "備品消耗品費項目";
    // 調査研究費全合計
    static readonly CHOUSA_KENKYU: string = "調査研究費項目";
    // 事務所費全合計
    static readonly JIMUSHOHI: string = "事務所費項目";
    // 人件費全合計
    static readonly JINKENHI: string = "人件費項目";
    // 経費全合計
    static readonly KEIHI: string = "経費項目";
    // 寄附全合計
    static readonly KIFU: string = "寄附項目";
    // 機関誌発行全合計
    static readonly KIKANSHI_HAKKOU: string = "機関誌発行項目";
    // 光熱費全合計
    static readonly KOUNETSU_HI: string = "光熱費項目";
    // パーティ費全合計
    static readonly PARTY_HI: string = "パーティ費項目";
    // 宣伝費全合計
    static readonly SENDEN_HI: string = "宣伝費項目";
    // 選挙費全合計
    static readonly ELECTION: string = "選挙費項目";
    // 支部交付金全合計
    static readonly SHIBU_KOFUKIN: string = "支部交付金項目";
    // その他事業費全合計
    static readonly SONOTA_JIGYOU: string = "その他事業費項目";
    // その他経費全合計
    static readonly SONOTA_KEIHI: string = "その他経費項目";
    // 組織費全合計
    static readonly SOSHIKI: string = "組織費項目";

    public createOptionIncome(): SelectOptionInterface[] {
        const options: SelectOptionInterface[] = [];
        options.splice(0);

        // (1)政党交付金(支部政党交付金)の総額
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_PARTY_ALL));
        // (2)前年末政党基金(支部基金)残高
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_LAST_YEAR));
        // (3)政党交付金(支部政党交付金)による支出総額(4)+(5)
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_KOUFUKIN_ALL));
        //  (4)政党交付金(支部政党交付金)支出充当総額
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_KOUFUKIN));
        //  (5)政党基金(支部基金)支出充当総額
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_SHIBUKIKIN));
        //  (6)政党基金(支部基金)の積み立てに充てるために取り崩した政党基金(支部基金)の額 
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_WITHDORAL_FUNDS));
        //  (7)政党基金(支部基金)の積み立て総額
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_ACCUMLATE_FUNDS));
        // 政党基金(支部基金)の運用により収受した果実の総額
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_FUNDS_GAIN));
        // (8)本年末政党支部基金(支部基金)残高(2)-(5)-(6)+(7)
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_THIS_YEAR));
        // 備考
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHUNYU_BIKO_DIFFER));

        return options;
    }
    public createOptionOutcome(): SelectOptionInterface[] {
        const options: SelectOptionInterface[] = [];
        options.splice(0);

        // 全政治活動全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.TOTAL_ACTION));
        // 全総計全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.ALL_AMOUNT));
        // 全事業総計全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.ALL_JIGYOU));
        // 備品消耗品費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.BIHINHI));
        // 調査研究費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.CHOUSA_KENKYU));
        // 事務所費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.JIMUSHOHI));
        // 人件費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.JINKENHI));
        // 経費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.KEIHI));
        // 寄附全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.KIFU));
        // 機関誌発行全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.KIKANSHI_HAKKOU));
        // 光熱費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.KOUNETSU_HI));
        // パーティ費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.PARTY_HI));
        // 宣伝費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SENDEN_HI));
        // 選挙費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.ELECTION));
        // 支部交付金全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SHIBU_KOFUKIN));
        // その他事業費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SONOTA_JIGYOU));
        // その他経費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SONOTA_KEIHI));
        // 組織費全合計
        options.push(this.createOpotionDto(CreateUsageSummaryChartData.SOSHIKI));

        return options;
    }

    private createOpotionDto(data: string) {
        const dto: SelectOptionInterface = new SelectOptionDto();
        dto.value = data;
        dto.text = data;
        return dto;
    }

    /**
     * 必要なデータをまとめる
     * @param viewKey 表示項目名
     * @param listResult 集計Entityリスト
     * @returns チャート表示データ
     */
    public allData(viewKey: string, listResult: keinenUsageSurface02103SummaryByYearInterface[]): (string | number)[][] {

        const chartData: (string | number)[][] = [[]];
        chartData.splice(0);
        chartData.push(this.createTitle(viewKey));
        for (const dto of listResult) {
            chartData.push(this.createData(viewKey, dto));
        }
        return chartData;
    }

    /**
     * タイトルを作成する
     * @returns タイトル名配列
     */
    private createTitle(viewKey: string): string[] {
        const title: string[] = [];
        title.splice(0);

        title.push("報告年");

        const sumAllText: string = "全合計";
        const koufukinText: string = "交付金充当";
        const myFundsText: string = "党基金充当";

        // 収入
        switch (viewKey) {

            //  (1)政党交付金(支部政党交付金)の総額
            case CreateUsageSummaryChartData.SHUNYU_PARTY_ALL:
                title.push(CreateUsageSummaryChartData.SHUNYU_PARTY_ALL);
                return title;

            // (2)前年末政党基金(支部基金)残高 
            case CreateUsageSummaryChartData.SHUNYU_LAST_YEAR:
                title.push(CreateUsageSummaryChartData.SHUNYU_LAST_YEAR);
                return title;

            // (3)政党交付金(支部政党交付金)による支出総額(4)+(5)
            case CreateUsageSummaryChartData.SHUNYU_KOUFUKIN_ALL:
                title.push(CreateUsageSummaryChartData.SHUNYU_KOUFUKIN_ALL);
                return title;

            // (4)政党交付金(支部政党交付金)支出充当総額
            case CreateUsageSummaryChartData.SHUNYU_KOUFUKIN:
                title.push(CreateUsageSummaryChartData.SHUNYU_KOUFUKIN);
                return title;

            // (5)政党基金(支部基金)支出充当総額 
            case CreateUsageSummaryChartData.SHUNYU_SHIBUKIKIN:
                title.push(CreateUsageSummaryChartData.SHUNYU_SHIBUKIKIN);
                return title;

            // (6)政党基金(支部基金)の積み立てに充てるために取り崩した政党基金(支部基金)の額
            case CreateUsageSummaryChartData.SHUNYU_WITHDORAL_FUNDS:
                title.push(CreateUsageSummaryChartData.SHUNYU_WITHDORAL_FUNDS);
                return title;

            // (7)政党基金(支部基金)の積み立て総額
            case CreateUsageSummaryChartData.SHUNYU_ACCUMLATE_FUNDS:
                title.push(CreateUsageSummaryChartData.SHUNYU_ACCUMLATE_FUNDS);
                return title;

            // 政党基金(支部基金)の運用により収受した果実の総額
            case CreateUsageSummaryChartData.SHUNYU_FUNDS_GAIN:
                title.push(CreateUsageSummaryChartData.SHUNYU_FUNDS_GAIN);
                return title;

            // (8)本年末政党支部基金(支部基金)残高(2)-(5)-(6)+(7)
            case CreateUsageSummaryChartData.SHUNYU_THIS_YEAR:
                title.push(CreateUsageSummaryChartData.SHUNYU_THIS_YEAR);
                return title;

            // 備考
            case CreateUsageSummaryChartData.SHUNYU_BIKO_DIFFER:
                title.push(CreateUsageSummaryChartData.SHUNYU_BIKO_DIFFER);
                return title;

            // 支出

            // 全政治活動全合計
            case CreateUsageSummaryChartData.TOTAL_ACTION:
                title.push(CreateUsageSummaryChartData.TOTAL_ACTION + sumAllText);
                title.push(CreateUsageSummaryChartData.TOTAL_ACTION + koufukinText);
                title.push(CreateUsageSummaryChartData.TOTAL_ACTION + myFundsText);
                return title;

            // 全総計全合計
            case CreateUsageSummaryChartData.ALL_AMOUNT:
                title.push(CreateUsageSummaryChartData.ALL_AMOUNT + sumAllText);
                title.push(CreateUsageSummaryChartData.ALL_AMOUNT + koufukinText);
                title.push(CreateUsageSummaryChartData.ALL_AMOUNT + myFundsText);
                return title;

            // 全事業総計全合計
            case CreateUsageSummaryChartData.ALL_JIGYOU:
                title.push(CreateUsageSummaryChartData.ALL_JIGYOU + sumAllText);
                title.push(CreateUsageSummaryChartData.ALL_JIGYOU + koufukinText);
                title.push(CreateUsageSummaryChartData.ALL_JIGYOU + myFundsText);
                return title;

            // 備品消耗品費全合計
            case CreateUsageSummaryChartData.BIHINHI:
                title.push(CreateUsageSummaryChartData.BIHINHI + sumAllText);
                title.push(CreateUsageSummaryChartData.BIHINHI + koufukinText);
                title.push(CreateUsageSummaryChartData.BIHINHI + myFundsText);
                return title;

            // 調査研究費全合計
            case CreateUsageSummaryChartData.CHOUSA_KENKYU:
                title.push(CreateUsageSummaryChartData.CHOUSA_KENKYU + sumAllText);
                title.push(CreateUsageSummaryChartData.CHOUSA_KENKYU + koufukinText);
                title.push(CreateUsageSummaryChartData.CHOUSA_KENKYU + myFundsText);
                return title;

            // 事務所費全合計
            case CreateUsageSummaryChartData.JIMUSHOHI:
                title.push(CreateUsageSummaryChartData.JIMUSHOHI + sumAllText);
                title.push(CreateUsageSummaryChartData.JIMUSHOHI + koufukinText);
                title.push(CreateUsageSummaryChartData.JIMUSHOHI + myFundsText);
                return title;

            // 人件費全合計
            case CreateUsageSummaryChartData.JINKENHI:
                title.push(CreateUsageSummaryChartData.JINKENHI + sumAllText);
                title.push(CreateUsageSummaryChartData.JINKENHI + koufukinText);
                title.push(CreateUsageSummaryChartData.JINKENHI + myFundsText);
                return title;

            // 経費全合計
            case CreateUsageSummaryChartData.KEIHI:
                title.push(CreateUsageSummaryChartData.KEIHI + sumAllText);
                title.push(CreateUsageSummaryChartData.KEIHI + koufukinText);
                title.push(CreateUsageSummaryChartData.KEIHI + myFundsText);
                return title;

            // 寄附全合計
            case CreateUsageSummaryChartData.KIFU:
                title.push(CreateUsageSummaryChartData.KIFU + sumAllText);
                title.push(CreateUsageSummaryChartData.KIFU + koufukinText);
                title.push(CreateUsageSummaryChartData.KIFU + myFundsText);
                return title;

            // 機関誌発行全合計
            case CreateUsageSummaryChartData.KIKANSHI_HAKKOU:
                title.push(CreateUsageSummaryChartData.KIKANSHI_HAKKOU + sumAllText);
                title.push(CreateUsageSummaryChartData.KIKANSHI_HAKKOU + koufukinText);
                title.push(CreateUsageSummaryChartData.KIKANSHI_HAKKOU + myFundsText);
                return title;

            // 光熱費全合計
            case CreateUsageSummaryChartData.KOUNETSU_HI:
                title.push(CreateUsageSummaryChartData.KOUNETSU_HI + sumAllText);
                title.push(CreateUsageSummaryChartData.KOUNETSU_HI + koufukinText);
                title.push(CreateUsageSummaryChartData.KOUNETSU_HI + myFundsText);
                return title;

            // パーティ費全合計
            case CreateUsageSummaryChartData.PARTY_HI:
                title.push(CreateUsageSummaryChartData.PARTY_HI + sumAllText);
                title.push(CreateUsageSummaryChartData.PARTY_HI + koufukinText);
                title.push(CreateUsageSummaryChartData.PARTY_HI + myFundsText);
                return title;

            // 宣伝費全合計
            case CreateUsageSummaryChartData.SENDEN_HI:
                title.push(CreateUsageSummaryChartData.SENDEN_HI + sumAllText);
                title.push(CreateUsageSummaryChartData.SENDEN_HI + koufukinText);
                title.push(CreateUsageSummaryChartData.SENDEN_HI + myFundsText);
                return title;

            // 選挙費全合計
            case CreateUsageSummaryChartData.ELECTION:
                title.push(CreateUsageSummaryChartData.ELECTION + sumAllText);
                title.push(CreateUsageSummaryChartData.ELECTION + koufukinText);
                title.push(CreateUsageSummaryChartData.ELECTION + myFundsText);
                return title;

            // 支部交付金全合計
            case CreateUsageSummaryChartData.SHIBU_KOFUKIN:
                title.push(CreateUsageSummaryChartData.SHIBU_KOFUKIN + sumAllText);
                title.push(CreateUsageSummaryChartData.SHIBU_KOFUKIN + koufukinText);
                title.push(CreateUsageSummaryChartData.SHIBU_KOFUKIN + myFundsText);
                return title;

            // その他事業費全合計
            case CreateUsageSummaryChartData.SONOTA_JIGYOU:
                title.push(CreateUsageSummaryChartData.SONOTA_JIGYOU + sumAllText);
                title.push(CreateUsageSummaryChartData.SONOTA_JIGYOU + koufukinText);
                title.push(CreateUsageSummaryChartData.SONOTA_JIGYOU + myFundsText);
                return title;

            // その他経費全合計
            case CreateUsageSummaryChartData.SONOTA_KEIHI:
                title.push(CreateUsageSummaryChartData.SONOTA_KEIHI + sumAllText);
                title.push(CreateUsageSummaryChartData.SONOTA_KEIHI + koufukinText);
                title.push(CreateUsageSummaryChartData.SONOTA_KEIHI + myFundsText);
                return title;

            // 組織費全合計
            case CreateUsageSummaryChartData.SOSHIKI:
                title.push(CreateUsageSummaryChartData.SOSHIKI + sumAllText);
                title.push(CreateUsageSummaryChartData.SOSHIKI + koufukinText);
                title.push(CreateUsageSummaryChartData.SOSHIKI + myFundsText);
                return title;

            default:
                return title;
        }
    }

    /**
     * データ行を作成する
     * @param dto 集計データ(年単位)
     * @returns google chart用のデータ 
     */
    private createData(viewKey: string, dto: keinenUsageSurface02103SummaryByYearInterface): (string | number)[] {

        const chartData: (string | number)[] = [];
        chartData.splice(0);
        chartData.push(dto.houkokuNen + "");

        switch (viewKey) {

            // (1)政党交付金(支部政党交付金)の総額
            case CreateUsageSummaryChartData.SHUNYU_PARTY_ALL:
                chartData.push(dto.summaryEntity.totalPartyAll);
                return chartData;

            // (2)前年末政党基金(支部基金)残高
            case CreateUsageSummaryChartData.SHUNYU_LAST_YEAR:
                chartData.push(dto.summaryEntity.lastYearRest);
                return chartData;

            // (3)政党交付金(支部政党交付金)による支出総額(4)+(5) 
            case CreateUsageSummaryChartData.SHUNYU_KOUFUKIN_ALL:
                chartData.push(dto.summaryEntity.outcomeKoufukinAll);
                return chartData;

            // (4)政党交付金(支部政党交付金)支出充当総額
            case CreateUsageSummaryChartData.SHUNYU_KOUFUKIN:
                chartData.push(dto.summaryEntity.outcomeKoufukin);
                return chartData;

            //  (5)政党基金(支部基金)支出充当総額
            case CreateUsageSummaryChartData.SHUNYU_SHIBUKIKIN:
                chartData.push(dto.summaryEntity.outcomeShibuKikin);
                return chartData;

            // (6)政党基金(支部基金)の積み立てに充てるために取り崩した政党基金(支部基金)の額 
            case CreateUsageSummaryChartData.SHUNYU_WITHDORAL_FUNDS:
                chartData.push(dto.summaryEntity.withdrawalFunds);
                return chartData;

            //  (7)政党基金(支部基金)の積み立て総額 
            case CreateUsageSummaryChartData.SHUNYU_ACCUMLATE_FUNDS:
                chartData.push(dto.summaryEntity.accumulateFunds);
                return chartData;

            // 政党基金(支部基金)の運用により収受した果実の総額
            case CreateUsageSummaryChartData.SHUNYU_FUNDS_GAIN:
                chartData.push(dto.summaryEntity.fundsSumGain);
                return chartData;

            // (8)本年末政党支部基金(支部基金)残高(2)-(5)-(6)+(7)
            case CreateUsageSummaryChartData.SHUNYU_THIS_YEAR:
                chartData.push(dto.summaryEntity.thisYearRest);
                return chartData;

            // 備考
            case CreateUsageSummaryChartData.SHUNYU_BIKO_DIFFER:
                chartData.push(dto.summaryEntity.bikouDiffer);
                return chartData;

            // 全政治活動全合計
            case CreateUsageSummaryChartData.TOTAL_ACTION:
                chartData.push(dto.summaryEntity.totalAllActionAll);
                chartData.push(dto.summaryEntity.totalAllActionJutoKoufukin);
                chartData.push(dto.summaryEntity.totalAllActionJutoMyFunds);
                return chartData;

            // 全総計全合計
            case CreateUsageSummaryChartData.ALL_AMOUNT:
                chartData.push(dto.summaryEntity.totalAllAmountAll);
                chartData.push(dto.summaryEntity.totalAllAmountJutoKoufukin);
                chartData.push(dto.summaryEntity.totalAllAmountJutoMyFunds);
                return chartData;

            // 全事業総計全合計
            case CreateUsageSummaryChartData.ALL_JIGYOU:
                chartData.push(dto.summaryEntity.totalAllJigyouAll);
                chartData.push(dto.summaryEntity.totalAllJigyouJutoKoufukin);
                chartData.push(dto.summaryEntity.totalAllJigyouJutoMyFunds);
                return chartData;

            // 備品消耗品費全合計
            case CreateUsageSummaryChartData.BIHINHI:
                chartData.push(dto.summaryEntity.totalBihinAll);
                chartData.push(dto.summaryEntity.totalBihinJutoKoufukin);
                chartData.push(dto.summaryEntity.totalBihinJutoMyFunds);
                return chartData;

            // 調査研究費全合計
            case CreateUsageSummaryChartData.CHOUSA_KENKYU:
                chartData.push(dto.summaryEntity.totalChousaAll);
                chartData.push(dto.summaryEntity.totalChousaJutoKoufukin);
                chartData.push(dto.summaryEntity.totalChousaJutoMyFunds);
                return chartData;

            // 事務所費全合計
            case CreateUsageSummaryChartData.JIMUSHOHI:
                chartData.push(dto.summaryEntity.totalJimushoAll);
                chartData.push(dto.summaryEntity.totalJimushoJutoKoufukin);
                chartData.push(dto.summaryEntity.totalJimushoJutoMyFunds);
                return chartData;

            // 人件費全合計
            case CreateUsageSummaryChartData.JINKENHI:
                chartData.push(dto.summaryEntity.totalJinkenhiAll);
                chartData.push(dto.summaryEntity.totalJinkenhiJutoKoufukin);
                chartData.push(dto.summaryEntity.totalJinkenhiJutoMyFunds);
                return chartData;

            // 経費全合計
            case CreateUsageSummaryChartData.KEIHI:
                chartData.push(dto.summaryEntity.totalKeihiAll);
                chartData.push(dto.summaryEntity.totalKeihiJutoKoufukin);
                chartData.push(dto.summaryEntity.totalKeihiJutoMyFunds);
                return chartData;

            // 寄附全合計
            case CreateUsageSummaryChartData.KIFU:
                chartData.push(dto.summaryEntity.totalKifuAll);
                chartData.push(dto.summaryEntity.totalKifuJutoKoufukin);
                chartData.push(dto.summaryEntity.totalShibuKoufuJutoMyFunds);
                return chartData;

            // 機関誌発行全合計
            case CreateUsageSummaryChartData.KIKANSHI_HAKKOU:
                chartData.push(dto.summaryEntity.totalKikanshiAll);
                chartData.push(dto.summaryEntity.totalKikanshiJutoKoufukin);
                chartData.push(dto.summaryEntity.totalKikanshiJutoMyFunds);
                return chartData;

            // 光熱費全合計
            case CreateUsageSummaryChartData.KOUNETSU_HI:
                chartData.push(dto.summaryEntity.totalKounetsuhiAll);
                chartData.push(dto.summaryEntity.totalKounetsuhiJutoKoufukin);
                chartData.push(dto.summaryEntity.totalKounetsuhiJutoMyFunds);
                return chartData;

            // パーティ費全合計
            case CreateUsageSummaryChartData.PARTY_HI:
                chartData.push(dto.summaryEntity.totalPartyAll);
                chartData.push(dto.summaryEntity.totalPartyJutoKoufukin);
                chartData.push(dto.summaryEntity.totalPartyJutoMyFunds);
                return chartData;

            // 宣伝費全合計
            case CreateUsageSummaryChartData.SENDEN_HI:
                chartData.push(dto.summaryEntity.totalSendenAll);
                chartData.push(dto.summaryEntity.totalSendenJutoKoufukin);
                chartData.push(dto.summaryEntity.totalSendenJutoMyFunds);
                return chartData;

            // 選挙費全合計
            case CreateUsageSummaryChartData.ELECTION:
                chartData.push(dto.summaryEntity.totalSenkyoAll);
                chartData.push(dto.summaryEntity.totalSenkyoJutoKoufukin);
                chartData.push(dto.summaryEntity.totalSenkyoJutoMyFunds);
                return chartData;

            // 支部交付金全合計
            case CreateUsageSummaryChartData.SHIBU_KOFUKIN:
                chartData.push(dto.summaryEntity.totalShibuKoufuAll);
                chartData.push(dto.summaryEntity.totalShibuKoufuJutoKoufukin);
                chartData.push(dto.summaryEntity.totalShibuKoufuJutoMyFunds);
                return chartData;

            // その他事業費全合計
            case CreateUsageSummaryChartData.SONOTA_JIGYOU:
                chartData.push(dto.summaryEntity.totalSonotaJigyouAll);
                chartData.push(dto.summaryEntity.totalSonotaJigyouJutoKoufukin);
                chartData.push(dto.summaryEntity.totalSonotaJigyouJutoMyFunds);
                return chartData;

            // その他経費全合計
            case CreateUsageSummaryChartData.SONOTA_KEIHI:
                chartData.push(dto.summaryEntity.totalSonotaKeihiAll);
                chartData.push(dto.summaryEntity.totalSonotaKeihiJutoKoufukin);
                chartData.push(dto.summaryEntity.totalSonotaKeihiJutoMyFunds);
                return chartData;

            // 組織費全合計
            case CreateUsageSummaryChartData.SOSHIKI:
                chartData.push(dto.summaryEntity.totalSoshikiAll);
                chartData.push(dto.summaryEntity.totalSoshikiJutoKoufukin);
                chartData.push(dto.summaryEntity.totalSoshikiJutoMyFunds);
                return chartData;

            // 未選択他
            default:
                chartData.push(0);
                chartData.push(0);
                chartData.push(0);
                return chartData;
        }
    }
}


