export default interface OfferingBalancesheet0702And0713SummaryInterface {

}
export default class OfferingBalancesheet0702And0713SummaryEntity implements OfferingBalancesheet0702And0713SummaryInterface {

   /** 収支報告書様式7その2と13と17Id */
   offeringBalancesheet0702And0713And0717SummaryId;

   /** 収支報告書様式7その2と13と17同一識別コード */
   offeringBalancesheet0702And0713And0717SummaryCode: number;

   /** 文書同一識別コード */
   documentCode: number;

   /** 最新区分 */
   saishinKbn: number;

   /** 報告年 */
   houkokuNen: number;

   /** 提出日 */
   offeringDate: Date;

   /** 政治団体Id */
   politicalOrganizationId: number;

   /** 政治団体同一識別コード */
   politicalOrganizationCode: number;

   /** 政治団体名称 */
   politicalOrganizationName: string;

   /** 原文書政治団体代表者名 */
   daihyouName: string;

   /** 原文書政治団体名称 */
   dantaiName: string;

   /** 関連者区分 */
   relationKbn: number;

   /** 代表者関連者Id */
   relationPersonIdDelegate: number;

   /** 代表者関連者同一識別コード */
   relationPersonCodeDelegate: number;

   /** 代表者関連者名称 */
   relationPersonNameDelegate: string;

   /** 収入総合計 */
   shunyuGokei: number;

   /** 前年繰り越し合計 */
   zennenKurikoshi: number;

   /** 本年収入合計 */
   honnenShunyu: number;

   /** 支出総合計 */
   shishutsuGoukei: number;

   /** 翌年繰り越し合計 */
   yokunenKurikoshi: number;

   /** 個人の党費または会費を納入金額 */
   kojiFutanGoukei: string;

   /** 党費または会費を納入した員数 */
   kojiFutanSuu: string;

   /** 個人寄付の合計 */
   kojinKifuGoukei: number;

   /** 個人寄付備考 */
   kojinKifuBikou: string;

   /** 特定寄付合計 */
   tokuteiKifuGoukei: number;

   /** 特定寄付備考 */
   tokuteiKifuBikou: string;

   /** 法人寄付合計 */
   houjinKifuGoukei: number;

   /** 法人寄付備考 */
   houjinKifuBiko: string;

   /** 政治団体寄付合計 */
   seijiDantaiKifuGoukei: number;

   /** 政治団体寄付備考 */
   seijiDantaiKifuBikou: string;

   /** 寄付小計合計 */
   kifuShoukeiGoukei: number;

   /** 寄付小計備考 */
   kifuShoukeiBikou: string;

   /** あっせん合計 */
   assenGoukei: number;

   /** あっせん備考 */
   assenBikou: string;

   /** 匿名寄附合計 */
   tokumeiKifuGoukei: number;

   /** 匿名寄付備考 */
   tokumeiKifuBikou: string;

   /** 寄付総合計 */
   kifuSoGoukei: number;

   /** 寄付総合計備考 */
   kifuSoGoukeiBikou: string;

   /** 人件費合計 */
   goukeiJinkenhi: string;

   /** 人件費のうち交付金に係る支出 */
   kohfuJinkenhi: string;

   /** 人件費備考 */
   bikouJinkenhi: string;

   /** 光熱費合計 */
   goukeiKohnetsuhi: string;

   /** 光熱費のうち交付金に係る支出 */
   kohfuKohnetsuhi: string;

   /** 光熱費備考 */
   bikouKohnetsuhi: string;

   /** 備品合計 */
   goukeiBihinhi: string;

   /** 備品のうち交付金に係る支出 */
   kohfuBihinhi: string;

   /** 備品備考 */
   bikouBihinhi: string;

   /** 事務所費合計 */
   goukeiJimushohi: string;

   /** 事務所費合計のうち交付金に係る支出 */
   kohfuJimushohi: string;

   /** 事務所費備考 */
   bikouJimushohi: string;

   /** 経費項目の合計 */
   goukeiKeihiShoukei: number;

   /** 経費の供与した交付金に係る支出 */
   kohfuKeihiShoukei: number;

   /** 経費の備考 */
   bikouKeihiShoukei: string;

   /** 経費項目の合計 */
   goukeiSonotaKeihi: number;

   /** 経費項目の合計のうち交付金に係る支出 */
   kohfuSonotaKeihi: string;

   /** 経費項目の合計備考 */
   bikouSonotaKeihi: string;

   /** 組織費合計 */
   goukeiSoshikiKatsudouhi: number;

   /** 組織費合計のうち交付金に係る支出 */
   kohfuSoshikiKatsudouhi: string;

   /** 組織費合計備考 */
   bikouSoshikiKatsudouhi: string;

   /** 選挙費合計 */
   goukeiSenkyoKatsudou: number;

   /** 選挙費合計のうち交付金に係る支出 */
   kohfuSenkyoKatsudou: string;

   /** 選挙費合計備考 */
   bikouSenkyoKatsudou: string;

   /** その他事業費合計 */
   goukeiSonotaJigyou: number;

   /** その他事業費合計のうち交付金に係る支出 */
   kohfuSonotaJigyou: string;


   /** その他事業費合計備考 */
   bikouSonotaJigyou: string;

   /** 機関誌発行合計 */
   goukeiHakkou: number;

   /** 機関誌発行合計のうち交付金に係る支出 */
   kohfuHakkou: string;

   /** 機関誌発行合計備考 */
   bikouHakkou: string;

   /** 宣伝広告費合計 */
   goukeiSenden: number;

   /** 宣伝広告費合計のうち交付金に係る支出 */
   kohfuSenden: string;

   /** 宣伝広告費合計備考 */
   bikouSenden: string;

   /** パーティ開催費合計 */
   goukeiKaisaiPty: number;

   /** パーティ開催費合計のうち交付金に係る支出 */
   kohfuKaisaiPty: string;

   /** パーティ開催費合計備考 */
   bikouKaisaiPty: string;

   /** その他合計 */
   goukeiSonota: number;

   /** その他合計のうち交付金に係る支出 */
   kohfuSonota: number;

   /** その他合計備考 */
   bikouSonota: string;

   /** 調査研究費合計 */
   goukeiChousaKenkyu: number;

   /** 調査研究費合計のうち交付金に係る支出 */
   kohfuChousaKenkyu: string;

   /** 調査研究費合計備考 */
   bikouChousaKenkyu: string;

   /** 寄附合計 */
   goukeiKifukin: number;

   /** 寄附合計のうち交付金に係る支出 */
   kohfuKifukin: string;

   /** 寄附合計備考 */
   bikouKifukin: string;

   /** 活動費小計 */
   goukeiKatsudouhi: number;

   /** 活動費小計のうち交付金に係る支出 */
   kohfuKatsudouhi: number;

   /** 活動費小計備考 */
   bikouKatsudouhi: string;

   /** 総合計 */
   goukeiZenGohkei: number;

   /** 土地有無フラグ */
   flgTochi: number;

   /** 土地備考 */
   bikouTochi: string;

   /** 建物有無フラグ */
   flgTatemono: number;

   /** 建物備考 */
   bikouTatemono: string;

   /** 借地権有無フラグ */
   flgShakuchiken: number;

   /** 借地権備考 */
   bikouShakuchiken: string;

   /** 動産有無フラグ */
   flgDohsan: number;

   /** 動産備考 */
   bikouDohsan: string;

   /** 預金有無フラグ */
   flgYokin: number;

   /** 預金備考 */
   bikouYokin: string;

   /** 信託有無フラグ */
   flgShintaku: number;

   /** 信託備考 */
   bikouShintaku: string;

   /** 証券有無フラグ */
   flgShouken: number;

   /** 証券備考 */
   bikouShouken: string;

   /** 出資有無フラグ */
   flgShusshi: number;

   /** 出資備考 */
   bikouShusshi: string;

   /** 貸付有無フラグ */
   flgKashitsuke: number;

   /** 貸付備考 */
   bikouKashitsuke: string;

   /** 敷金有無フラグ */
   flgShikikin: number;

   /** 敷金備考 */
   bikouShikikin: string;

   /** 施設利用権有無フラグ */
   flgShisetsuRiyou: number;

   /** 施設利用権備考 */
   bikouShisetsuRiyou: string;

   /** 借入有無フラグ */
   flgKairiire: number;

   /** 借入備考 */
   bikouKariire: string;

   constructor() {
      // 初期データ
      const INIT_STRING: string = "";
      const INIT_NUMBER: number = 0;
      const INIT_DATE = new Date(1948, 7, 29);

      this.offeringBalancesheet0702And0713And0717SummaryId = INIT_NUMBER;
      this.offeringBalancesheet0702And0713And0717SummaryCode = INIT_NUMBER;
      this.documentCode = INIT_NUMBER
      this.saishinKbn = INIT_NUMBER;
      this.houkokuNen = INIT_NUMBER;
      this.offeringDate = INIT_DATE;
      this.politicalOrganizationId = INIT_NUMBER;
      this.politicalOrganizationCode = INIT_NUMBER;
      this.politicalOrganizationName = INIT_STRING;
      this.daihyouName = INIT_STRING;
      this.dantaiName = INIT_STRING;
      this.relationKbn = INIT_NUMBER;
      this.relationPersonIdDelegate = INIT_NUMBER;
      this.relationPersonCodeDelegate = INIT_NUMBER;
      this.relationPersonNameDelegate = INIT_STRING;
      this.shunyuGokei = INIT_NUMBER;
      this.zennenKurikoshi = INIT_NUMBER;
      this.honnenShunyu = INIT_NUMBER;
      this.shishutsuGoukei = INIT_NUMBER;
      this.yokunenKurikoshi = INIT_NUMBER;
      this.kojiFutanGoukei = INIT_STRING;
      this.kojiFutanSuu = INIT_STRING;
      this.kojinKifuGoukei = INIT_NUMBER;
      this.kojinKifuBikou = INIT_STRING;
      this.tokuteiKifuGoukei = INIT_NUMBER;
      this.tokuteiKifuBikou = INIT_STRING;
      this.houjinKifuGoukei = INIT_NUMBER;
      this.houjinKifuBiko = INIT_STRING;
      this.seijiDantaiKifuGoukei = INIT_NUMBER;
      this.seijiDantaiKifuBikou = INIT_STRING;
      this.kifuShoukeiGoukei = INIT_NUMBER;
      this.kifuShoukeiBikou = INIT_STRING;
      this.assenGoukei = INIT_NUMBER;
      this.assenBikou = INIT_STRING;
      this.tokumeiKifuGoukei = INIT_NUMBER;
      this.tokumeiKifuBikou = INIT_STRING;
      this.kifuSoGoukei = INIT_NUMBER;
      this.kifuSoGoukeiBikou = INIT_STRING;
      this.goukeiJinkenhi = INIT_STRING;
      this.kohfuJinkenhi = INIT_STRING;
      this.bikouJinkenhi = INIT_STRING;
      this.goukeiKohnetsuhi = INIT_STRING;
      this.kohfuKohnetsuhi = INIT_STRING;
      this.bikouKohnetsuhi = INIT_STRING;
      this.goukeiBihinhi = INIT_STRING;
      this.kohfuBihinhi = INIT_STRING;
      this.bikouBihinhi = INIT_STRING;
      this.goukeiJimushohi = INIT_STRING;
      this.kohfuJimushohi = INIT_STRING;
      this.bikouJimushohi = INIT_STRING;
      this.goukeiKeihiShoukei = INIT_NUMBER;
     this.kohfuKeihiShoukei = INIT_NUMBER;
      this.bikouKeihiShoukei = INIT_STRING;
      this.goukeiSonotaKeihi = INIT_NUMBER;
      this.kohfuSonotaKeihi = INIT_STRING;
      this.bikouSonotaKeihi = INIT_STRING;
      this.goukeiSoshikiKatsudouhi = INIT_NUMBER;
      this.kohfuSoshikiKatsudouhi = INIT_STRING;
      this.bikouSoshikiKatsudouhi = INIT_STRING;
      this.goukeiSenkyoKatsudou = INIT_NUMBER;
      this.kohfuSenkyoKatsudou = INIT_STRING;
      this.bikouSenkyoKatsudou = INIT_STRING;
      this.goukeiSonotaJigyou = INIT_NUMBER;
      this.kohfuSonotaJigyou = INIT_STRING;
      this.bikouSonotaJigyou = INIT_STRING;
      this.goukeiHakkou = INIT_NUMBER;
      this.kohfuHakkou = INIT_STRING;
      this.bikouHakkou = INIT_STRING;
      this.goukeiSenden = INIT_NUMBER;
      this.kohfuSenden = INIT_STRING;
      this.bikouSenden = INIT_STRING;
      this.goukeiKaisaiPty = INIT_NUMBER;
      this.kohfuKaisaiPty = INIT_STRING;
     this.bikouKaisaiPty = INIT_STRING;
      this.goukeiSonota = INIT_NUMBER;
      this.kohfuSonota = INIT_NUMBER;
      this.bikouSonota = INIT_STRING;
      this.goukeiChousaKenkyu = INIT_NUMBER;
      this.kohfuChousaKenkyu = INIT_STRING;
      this.bikouChousaKenkyu = INIT_STRING;
      this.goukeiKifukin = INIT_NUMBER;
      this.kohfuKifukin = INIT_STRING;
      this.bikouKifukin = INIT_STRING;
      this.goukeiKatsudouhi = INIT_NUMBER;
      this.kohfuKatsudouhi = INIT_NUMBER;
      this.bikouKatsudouhi = INIT_STRING;
      this.goukeiZenGohkei = INIT_NUMBER;
      this.flgTochi = INIT_NUMBER;
      this.bikouTochi = INIT_STRING;
      this.flgTatemono = INIT_NUMBER;
      this.bikouTatemono = INIT_STRING;
      this.flgShakuchiken = INIT_NUMBER;
      this.bikouShakuchiken = INIT_STRING;
      this.flgDohsan = INIT_NUMBER;
      this.bikouDohsan = INIT_STRING;
      this.flgYokin = INIT_NUMBER;
      this.bikouYokin = INIT_STRING;
      this.flgShintaku = INIT_NUMBER;
      this.bikouShintaku = INIT_STRING;
      this.flgShouken = INIT_NUMBER;
      this.bikouShouken = INIT_STRING;
      this.flgShusshi = INIT_NUMBER;
      this.bikouShusshi = INIT_STRING;
      this.flgKashitsuke = INIT_NUMBER;
      this.bikouKashitsuke = INIT_STRING;
      this.flgShikikin = INIT_NUMBER;
      this.bikouShikikin = INIT_STRING;
      this.flgShisetsuRiyou = INIT_NUMBER;
      this.bikouShisetsuRiyou = INIT_STRING;
      this.flgKairiire = INIT_NUMBER;
      this.bikouKariire = INIT_STRING;
   }
}