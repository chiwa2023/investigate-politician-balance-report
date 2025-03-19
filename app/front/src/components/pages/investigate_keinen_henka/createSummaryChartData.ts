import KeinenHenkaSurfaceAndSummaryByYearInterface from "../../../dto/keinen_henka/keinenHenkaSurfaceAndSummaryByYearDto";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";

export default class CreateSummaryChartData {

    /** 未選択  */
    static readonly NO_SELECT: string = "未選択";

    // 収入

    /** 収入総額 */
    static readonly SHUNYU_SOUGAKU: string = "収入総額";
    /** 前年からの繰越額 */
    static readonly ZENNEN_KURIKOSHI: string = "前年からの繰越額";
    /** 本年の収入額 */
    static readonly HONNEN_SHUUNYU: string = "本年の収入額";
    /** 支出総額 */
    static readonly SHISHUTSU_SOUGAKU: string = "支出総額";
    /** 翌年への繰越額 */
    static readonly YOKUNEN_KURIKOSHI: string = "翌年への繰越額";
    /** 個人の党費 */
    static readonly KOJIN_TOUHI: string = "個人の党費";
    /** 個人寄付の合計 */
    static readonly KOJIN_KIFU: string = "個人寄付の合計";
    /** 特定寄付合計 */
    static readonly TOKUTEI_KIFU: string = "特定寄付合計";
    /** 法人寄付合計 */
    static readonly HOUJIN_KIFU: string = "法人寄付合計";
    /** 政治団体寄付合計 */
    static readonly SEIJI_DANTAI_KIFU: string = "政治団体寄付合計";
    /** 寄付小計合計 */
    static readonly KIFU_SHOUKEI: string = "寄付小計合計";
    /** 寄付のうちあっせんによるもの合計 */
    static readonly KIFU_ASSEN: string = "寄付のうちあっせんによるもの合計";
    /** 政党匿名寄付 */
    static readonly SEITOU_TOKUSMEI_KIFU: string = "政党匿名寄付";
    /** 寄付総合計 */
    static readonly KIFU_SOUGOUKEI: string = "寄付総合計";

    // 支出

    /** 人件費項目 */
    static readonly JINKENHI: string = "人件費項目";
    /** 光熱費項目 */
    static readonly KOUNETSUHI: string = "光熱費項目";
    /** 備品項目 */
    static readonly BIHIN: string = "備品項目";
    /** 事務所費項目 */
    static readonly JIMUSHOHI: string = "事務所費項目";
    /** 経費項目 */
    static readonly KEIHI: string = "経費項目";
    /** 組織活動費項目 */
    static readonly SOSHIKI_KATSUDOUHI: string = "組織活動費項目";
    /** 選挙活動費項目 */
    static readonly SENKYYO_KATSUDOUHI: string = "選挙活動費項目";
    /** その他項目 */
    static readonly SONOTA_SHISHUTSU: string = "その他項目";
    /** 機関誌発行項目 */
    static readonly KIKANSHI_HAKKOUHI: string = "機関誌発行項目";
    /** 宣伝費項目 */
    static readonly SENDENHI: string = "宣伝費項目";
    /** 政治資金パーティ開催項目 */
    static readonly SEIJISHIKIN_PARTY: string = "政治資金パーティ開催項目";
    /** その他事業費項目 */
    static readonly SONOTA_JIGYOU: string = "その他事業費項目";
    /** 調査研究費項目 */
    static readonly CHOUSA_KENKYUUHI: string = "調査研究費項目";
    /** 寄付金項目 */
    static readonly KIFUKIN: string = "寄付金項目";
    /** その他の経費項目 */
    static readonly SONOTA_KEIHI: string = "その他の経費項目";
    /** 活動費小計 */
    static readonly KATSUDOUHI: string = "活動費小計";
    /** 現計合計 */
    static readonly SHISHUTSU_SOUKEI: string = "現計合計";

    public createOptionIncome(): SelectOptionInterface[] {
        const options: SelectOptionInterface[] = [];
        options.splice(0);
        // 収入総額 
        options.push(this.createOpotionDto(CreateSummaryChartData.SHUNYU_SOUGAKU));
        // 前年からの繰越額 
        options.push(this.createOpotionDto(CreateSummaryChartData.ZENNEN_KURIKOSHI));
        // 本年の収入額 
        options.push(this.createOpotionDto(CreateSummaryChartData.HONNEN_SHUUNYU));
        // 支出総額
        options.push(this.createOpotionDto(CreateSummaryChartData.SHISHUTSU_SOUGAKU));
        // 翌年への繰越額 
        options.push(this.createOpotionDto(CreateSummaryChartData.YOKUNEN_KURIKOSHI));
        // 個人の党費 
        options.push(this.createOpotionDto(CreateSummaryChartData.KOJIN_TOUHI));
        // 個人寄付の合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.KOJIN_KIFU));
        // 特定寄付合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.TOKUTEI_KIFU));
        // 法人寄付合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.HOUJIN_KIFU));
        // 政治団体寄付合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.SEIJI_DANTAI_KIFU));
        // 寄付小計合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.KIFU_SHOUKEI));
        // 寄付のうちあっせんによるもの合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.KIFU_ASSEN));
        // 政党匿名寄付 
        options.push(this.createOpotionDto(CreateSummaryChartData.SEITOU_TOKUSMEI_KIFU));
        // 寄付総合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.KIFU_SOUGOUKEI));

        return options;
    }
    public createOptionOutcome(): SelectOptionInterface[] {
        const options: SelectOptionInterface[] = [];
        options.splice(0);
        // 人件費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.JINKENHI));
        // 光熱費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.KOUNETSUHI));
        // 備品項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.BIHIN));
        // 事務所費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.JIMUSHOHI));
        // 経費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.KEIHI));
        // 組織活動費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.SOSHIKI_KATSUDOUHI));
        // 選挙活動費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.SENKYYO_KATSUDOUHI));
        // その他項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.SONOTA_SHISHUTSU));
        // 機関誌発行項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.KIKANSHI_HAKKOUHI));
        // 宣伝費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.SENDENHI));
        // 政治資金パーティ開催項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.SEIJISHIKIN_PARTY));
        // その他事業費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.SONOTA_JIGYOU));
        // 調査研究費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.CHOUSA_KENKYUUHI));
        // 寄付金項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.KIFUKIN));
        // その他の経費項目 
        options.push(this.createOpotionDto(CreateSummaryChartData.SONOTA_KEIHI));
        // 活動費小計 
        options.push(this.createOpotionDto(CreateSummaryChartData.KATSUDOUHI));
        // 現計合計 
        options.push(this.createOpotionDto(CreateSummaryChartData.SHISHUTSU_SOUKEI));
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
    public allData(viewKey: string, listResult: KeinenHenkaSurfaceAndSummaryByYearInterface[]): (string | number)[][] {

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

        const koufukinKoumoku: string = "内交付金";

        // 収入
        switch (viewKey) {

            // 収入総額 
            case CreateSummaryChartData.SHUNYU_SOUGAKU:
                title.push(CreateSummaryChartData.SHUNYU_SOUGAKU);
                return title;
            // 前年からの繰越額 
            case CreateSummaryChartData.ZENNEN_KURIKOSHI:
                title.push(CreateSummaryChartData.ZENNEN_KURIKOSHI);
                return title;
            // 本年の収入額 
            case CreateSummaryChartData.HONNEN_SHUUNYU:
                title.push(CreateSummaryChartData.HONNEN_SHUUNYU);
                return title;
            // 支出総額
            case CreateSummaryChartData.SHISHUTSU_SOUGAKU:
                title.push(CreateSummaryChartData.SHISHUTSU_SOUGAKU);
                return title;
            // 翌年への繰越額 
            case CreateSummaryChartData.YOKUNEN_KURIKOSHI:
                title.push(CreateSummaryChartData.YOKUNEN_KURIKOSHI);
                return title;
            // 個人の党費 
            case CreateSummaryChartData.KOJIN_TOUHI:
                title.push(CreateSummaryChartData.KOJIN_TOUHI);
                title.push("党費支払した員数");
                return title;
            // 個人寄付の合計 
            case CreateSummaryChartData.KOJIN_KIFU:
                title.push(CreateSummaryChartData.KOJIN_KIFU);
                return title;
            // 特定寄付合計 
            case CreateSummaryChartData.TOKUTEI_KIFU:
                title.push(CreateSummaryChartData.TOKUTEI_KIFU);
                return title;
            // 法人寄付合計 
            case CreateSummaryChartData.HOUJIN_KIFU:
                title.push(CreateSummaryChartData.HOUJIN_KIFU);
                return title;
            // 政治団体寄付合計 
            case CreateSummaryChartData.SEIJI_DANTAI_KIFU:
                title.push(CreateSummaryChartData.SEIJI_DANTAI_KIFU);
                return title;
            // 寄付小計合計 
            case CreateSummaryChartData.KIFU_SHOUKEI:
                title.push(CreateSummaryChartData.KIFU_SHOUKEI);
                return title;
            // 寄付のうちあっせんによるもの合計 
            case CreateSummaryChartData.KIFU_ASSEN:
                title.push(CreateSummaryChartData.KIFU_ASSEN);
                return title;
            // 政党匿名寄付 
            case CreateSummaryChartData.SEITOU_TOKUSMEI_KIFU:
                title.push(CreateSummaryChartData.SEITOU_TOKUSMEI_KIFU);
                return title;
            // 寄付総合計 
            case CreateSummaryChartData.KIFU_SOUGOUKEI:
                title.push(CreateSummaryChartData.KIFU_SOUGOUKEI);
                return title;

            // 支出

            // 人件費項目 
            case CreateSummaryChartData.JINKENHI:
                title.push(CreateSummaryChartData.JINKENHI);
                title.push(CreateSummaryChartData.JINKENHI + koufukinKoumoku);
                return title;
            // 光熱費項目 
            case CreateSummaryChartData.KOUNETSUHI:
                title.push(CreateSummaryChartData.KOUNETSUHI);
                title.push(CreateSummaryChartData.KOUNETSUHI + koufukinKoumoku);
                return title;
            // 備品項目 
            case CreateSummaryChartData.BIHIN:
                title.push(CreateSummaryChartData.BIHIN);
                title.push(CreateSummaryChartData.BIHIN + koufukinKoumoku);
                return title;
            // 事務所費項目 
            case CreateSummaryChartData.JIMUSHOHI:
                title.push(CreateSummaryChartData.JIMUSHOHI);
                title.push(CreateSummaryChartData.JIMUSHOHI + koufukinKoumoku);
                return title;
            // 経費項目 
            case CreateSummaryChartData.KEIHI:
                title.push(CreateSummaryChartData.KEIHI);
                title.push(CreateSummaryChartData.KEIHI + koufukinKoumoku);
                return title;
            // 組織活動費項目 
            case CreateSummaryChartData.SOSHIKI_KATSUDOUHI:
                title.push(CreateSummaryChartData.SOSHIKI_KATSUDOUHI);
                title.push(CreateSummaryChartData.SOSHIKI_KATSUDOUHI + koufukinKoumoku);
                return title;
            // 選挙活動費項目 
            case CreateSummaryChartData.SENKYYO_KATSUDOUHI:
                title.push(CreateSummaryChartData.SENKYYO_KATSUDOUHI);
                title.push(CreateSummaryChartData.SENKYYO_KATSUDOUHI + koufukinKoumoku);
                return title;
            // その他項目 
            case CreateSummaryChartData.SONOTA_SHISHUTSU:
                title.push(CreateSummaryChartData.SONOTA_SHISHUTSU);
                title.push(CreateSummaryChartData.SONOTA_SHISHUTSU + koufukinKoumoku);
                return title;
            // 機関誌発行項目 
            case CreateSummaryChartData.KIKANSHI_HAKKOUHI:
                title.push(CreateSummaryChartData.KIKANSHI_HAKKOUHI);
                title.push(CreateSummaryChartData.KIKANSHI_HAKKOUHI + koufukinKoumoku);
                return title;
            // 宣伝費項目 
            case CreateSummaryChartData.SENDENHI:
                title.push(CreateSummaryChartData.SENDENHI);
                title.push(CreateSummaryChartData.SENDENHI + koufukinKoumoku);
                return title;
            // 政治資金パーティ開催項目 
            case CreateSummaryChartData.SEIJISHIKIN_PARTY:
                title.push(CreateSummaryChartData.SEIJISHIKIN_PARTY);
                title.push(CreateSummaryChartData.SEIJISHIKIN_PARTY + koufukinKoumoku);
                return title;
            // その他事業費項目 
            case CreateSummaryChartData.SONOTA_JIGYOU:
                title.push(CreateSummaryChartData.SONOTA_JIGYOU);
                title.push(CreateSummaryChartData.SONOTA_JIGYOU + koufukinKoumoku);
                return title;
            // 調査研究費項目 
            case CreateSummaryChartData.CHOUSA_KENKYUUHI:
                title.push(CreateSummaryChartData.CHOUSA_KENKYUUHI);
                title.push(CreateSummaryChartData.CHOUSA_KENKYUUHI + koufukinKoumoku);
                return title;
            // 寄付金項目 
            case CreateSummaryChartData.KIFUKIN:
                title.push(CreateSummaryChartData.KIFUKIN);
                title.push(CreateSummaryChartData.KIFUKIN + koufukinKoumoku);
                return title;
            // その他の経費項目 
            case CreateSummaryChartData.SONOTA_KEIHI:
                title.push(CreateSummaryChartData.SONOTA_KEIHI);
                title.push(CreateSummaryChartData.SONOTA_KEIHI + koufukinKoumoku);
                return title;
            // 活動費小計 
            case CreateSummaryChartData.KATSUDOUHI:
                title.push(CreateSummaryChartData.KATSUDOUHI);
                title.push(CreateSummaryChartData.KATSUDOUHI + koufukinKoumoku);
                return title;
            // 現計合計 
            case CreateSummaryChartData.SHISHUTSU_SOUKEI:
                title.push(CreateSummaryChartData.SHISHUTSU_SOUKEI);
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
    private createData(viewKey: string, dto: KeinenHenkaSurfaceAndSummaryByYearInterface): (string | number)[] {

        const chartData: (string | number)[] = [];
        chartData.splice(0);
        chartData.push(dto.houkokuNen + "");

        switch (viewKey) {
            // 収入総額 
            case CreateSummaryChartData.SHUNYU_SOUGAKU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.shunyuGokei);
                return chartData;
            // 前年からの繰越額 
            case CreateSummaryChartData.ZENNEN_KURIKOSHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.zennenKurikoshi);
                return chartData;
            // 本年の収入額 
            case CreateSummaryChartData.HONNEN_SHUUNYU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.honnenShunyu);
                return chartData;
            // 支出総額
            case CreateSummaryChartData.SHISHUTSU_SOUGAKU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.shishutsuGoukei);
                return chartData;
            // 翌年への繰越額 
            case CreateSummaryChartData.YOKUNEN_KURIKOSHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.yokunenKurikoshi);
                return chartData;
            // 個人の党費 
            case CreateSummaryChartData.KOJIN_TOUHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kojiFutanGoukei);
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kojiFutanSuu);
                return chartData;
            // 個人寄付の合計 
            case CreateSummaryChartData.KOJIN_KIFU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kojinKifuGoukei);
                return chartData;
            // 特定寄付合計 
            case CreateSummaryChartData.TOKUTEI_KIFU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.tokuteiKifuGoukei);
                return chartData;
            // 法人寄付合計 
            case CreateSummaryChartData.HOUJIN_KIFU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.houjinKifuGoukei);
                return chartData;
            // 政治団体寄付合計 
            case CreateSummaryChartData.SEIJI_DANTAI_KIFU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.seijiDantaiKifuGoukei);
                return chartData;
            // 寄付小計合計 
            case CreateSummaryChartData.KIFU_SHOUKEI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kifuShoukeiGoukei);
                return chartData;
            // 寄付のうちあっせんによるもの合計 
            case CreateSummaryChartData.KIFU_ASSEN:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.assenGoukei);
                return chartData;
            // 政党匿名寄付 
            case CreateSummaryChartData.SEITOU_TOKUSMEI_KIFU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.tokumeiKifuGoukei);
                return chartData;
            // 寄付総合計 
            case CreateSummaryChartData.KIFU_SOUGOUKEI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kifuSoGoukei);
                return chartData;

            // 支出

            // 人件費項目 
            case CreateSummaryChartData.JINKENHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kifuSoGoukei);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuJinkenhi));
                return chartData;
            // 光熱費項目 
            case CreateSummaryChartData.KOUNETSUHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiKohnetsuhi);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuKohnetsuhi));
                return chartData;
            // 備品項目 
            case CreateSummaryChartData.BIHIN:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiBihinhi);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuBihinhi));
                return chartData;
            // 事務所費項目 
            case CreateSummaryChartData.JIMUSHOHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiJimushohi);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuJimushohi));
                return chartData;
            // 経費項目 
            case CreateSummaryChartData.KEIHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiKeihiShoukei);
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuKeihiShoukei);
                return chartData;
            // 組織活動費項目 
            case CreateSummaryChartData.SOSHIKI_KATSUDOUHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiSoshikiKatsudouhi);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuSoshikiKatsudouhi));
                return chartData;
            // 選挙活動費項目 
            case CreateSummaryChartData.SENKYYO_KATSUDOUHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiSenkyoKatsudou);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuSenkyoKatsudou));
                return chartData;
            // その他項目 
            case CreateSummaryChartData.SONOTA_SHISHUTSU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiSonota);
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuSonota);
                return chartData;
            // 機関誌発行項目 
            case CreateSummaryChartData.KIKANSHI_HAKKOUHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiHakkou);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuHakkou));
                return chartData;
            // 宣伝費項目 
            case CreateSummaryChartData.SENDENHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiSenden);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuSenden));
                return chartData;
            // 政治資金パーティ開催項目 
            case CreateSummaryChartData.SEIJISHIKIN_PARTY:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiKaisaiPty);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuKaisaiPty));
                return chartData;
            // その他事業費項目 
            case CreateSummaryChartData.SONOTA_JIGYOU:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiSonotaJigyou);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuSonotaJigyou));
                return chartData;
            // 調査研究費項目 
            case CreateSummaryChartData.CHOUSA_KENKYUUHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiChousaKenkyu);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuChousaKenkyu));
                return chartData;
            // 寄付金項目 
            case CreateSummaryChartData.KIFUKIN:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiKifukin);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuKifukin));
                return chartData;
            // その他の経費項目 
            case CreateSummaryChartData.SONOTA_KEIHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiSonotaKeihi);
                chartData.push(parseInt(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuSonotaKeihi));
                return chartData;
            // 活動費小計 
            case CreateSummaryChartData.KATSUDOUHI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiKatsudouhi);
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.kohfuKatsudouhi);
                return chartData;
            // 現計合計 
            case CreateSummaryChartData.SHISHUTSU_SOUKEI:
                chartData.push(dto.offeringBalancesheet0702And0713SummaryEntity.goukeiZenGohkei);
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


