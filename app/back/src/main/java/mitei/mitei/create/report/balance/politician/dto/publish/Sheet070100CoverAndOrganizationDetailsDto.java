package mitei.mitei.create.report.balance.politician.dto.publish;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 様式17その1 政治資金収支報告書の表紙を表すワークシートDto
 */
@JacksonXmlRootElement(localName = "SHEET")
public class Sheet070100CoverAndOrganizationDetailsDto implements Serializable {

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 報告年 */
    @JacksonXmlProperty(localName = "HOUKOKU_NEN")
    private Integer houkokuNen = 0;

    /** 開催年月日 */
    @JacksonXmlProperty(localName = "KAISAI_DT")
    private String dateKaisai;

    /** 政治団体名称 */
    @JacksonXmlProperty(localName = "DANTAI_NM")
    private String dantaiName;

    /** 政治団体名称かな */
    @JacksonXmlProperty(localName = "DANTAI_KANA")
    private String dantaiNameKana;

    /** 事務所の住所 */
    @JacksonXmlProperty(localName = "JIM_ADR")
    private String jimushoJusho;

    /** 事務所の住所建物 */
    @JacksonXmlProperty(localName = "JIM_APA_ADR")
    private String jimushoJushoTatemono;

    /** 代表者の姓 */
    @JacksonXmlProperty(localName = "DAI_NM1")
    private String daihyoushaNameLast;

    /** 代表者の名 */
    @JacksonXmlProperty(localName = "DAI_NM2")
    private String daihyoushaNameFirst;

    /** 会計責任者の姓 */
    @JacksonXmlProperty(localName = "KAI_NM1")
    private String kaikeiSekinnshaNameLast;

    /** 会計責任者の名 */
    @JacksonXmlProperty(localName = "KAI_NM2")
    private String kaikeiSekinnshaNameFirst;

    /** 事務担当者1の姓 */
    @JacksonXmlProperty(localName = "TANTOU1_NM1")
    private String jimuTantousha1NameLast;

    /** 事務担当者1の名 */
    @JacksonXmlProperty(localName = "TANTOU1_NM2")
    private String jimuTantousha1NameFirst;

    /** 事務担当者1の電話番号 */
    @JacksonXmlProperty(localName = "TANTOU1_TEL")
    private String jimuTantousha1Tel;

    /** 事務担当者2の姓 */
    @JacksonXmlProperty(localName = "TANTOU2_NM1")
    private String jimuTantousha2NameLast;

    /** 事務担当者2の名 */
    @JacksonXmlProperty(localName = "TANTOU2_NM2")
    private String jimuTantousha2NameFirst;

    /** 事務担当者2の電話番号 */
    @JacksonXmlProperty(localName = "TANTOU2_TEL")
    private String jimuTantousha2Tel;

    /** 事務担当者3の姓 */
    @JacksonXmlProperty(localName = "TANTOU3_NM1")
    private String jimuTantousha3NameLast;

    /** 事務担当者3の名 */
    @JacksonXmlProperty(localName = "TANTOU3_NM2")
    private String jimuTantousha3NameFirst;

    /** 事務担当者3の電話番号 */
    @JacksonXmlProperty(localName = "TANTOU3_TEL")
    private String jimuTantousha3Tel;

    /** 団体区分 */
    @JacksonXmlProperty(localName = "DANTAI_KBN")
    private String dantaiKbn;

    /** 活動区域区分 */
    @JacksonXmlProperty(localName = "KATU_KUKI")
    private Integer katsudouKuikiKbn;

    /** 資金管理団体の有無 */
    @JacksonXmlProperty(localName = "SIKIN_UMU")
    private Integer umuShikinKanrenDantai;

    /** 公職の名称 */
    @JacksonXmlProperty(localName = "KOSYOKU_NM")
    private String koushokuName;

    /** 現職候補者の別 */
    @JacksonXmlProperty(localName = "KOSYOKU_KBN")
    private String koushokuGenKouho;

    /** 資金管理団体の設立者の姓 */
    @JacksonXmlProperty(localName = "SIKIN_TODOKE_NM1")
    private String shikinDaihyouName1;

    /** 資金管理団体の設立者の名 */
    @JacksonXmlProperty(localName = "SIKIN_TODOKE_NM2")
    private String shikinDaihyouName2;

    /** 資金管理団体の指定期間(開始) */
    @JacksonXmlProperty(localName = "SIKIN_KIKAN1")
    private String kanriDantaiPeriodStart;

    /** 資金管理団体の指定期間(終了) */
    @JacksonXmlProperty(localName = "SIKIN_KIKAN2")
    private String kanriDantaiPeriodEnd;

    /** 資金管理団体の複数指定期間 */
    @JacksonXmlProperty(localName = "SIKIN_KIKAN_FUKUSU")
    private String kanriDantaiPeriodRest;

    /** 国会議員関連団体区分 */
    @JacksonXmlProperty(localName = "GIIN_DANTAI_KBN")
    private Integer kokkaiGiinDantaiKbn;

    /** 国家議員1の姓 */
    @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_NM_1")
    private String kokkaiGiin1NameLast;

    /** 国家議員1の名 */
    @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_NM_2")
    private String kokkaiGiin1NameFirst;

    /** 国家議員1の公職(衆参) */
    @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_NM")
    private String kokkaiGiin1ShuuSan;

    /** 国家議員1現職と候補者の別 */
    @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_KBN")
    private String kokkaiGiin1GenKouho;

    /** 国会議員関係団体の特例適用期間(開始) */
    @JacksonXmlProperty(localName = "GIIN_KIKAN1")
    private String giinDantantaiTokureiPeriodStart;

    /** 国会議員関係団体の特例適用期間(終了) */
    @JacksonXmlProperty(localName = "GIIN_KIKAN2")
    private String giinDantantaiTokureiPeriodEnd;

    /** 国会議員関係団体の複数特例適用期間 */
    @JacksonXmlProperty(localName = "GIIN_KIKAN_FUKUSU")
    private String giinDantantaiTokureiPeriodRest;

    /** 国家議員2の姓 */
    @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_NM_1")
    private String kokkaiGiin2NameLast;

    /** 国家議員2の名 */
    @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_NM_2")
    private String kokkaiGiin2NameFirst;

    /** 国家議員2の公職(衆参) */
    @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_NM")
    private String kokkaiGiin2ShuuSan;

    /** 国家議員2現職と候補者の別 */
    @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_KBN")
    private String kokkaiGiin2GenKouho;

    /** 国家議員3の姓 */
    @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_NM_1")
    private String kokkaiGiin3NameLast;

    /** 国家議員3の姓 */
    @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_NM_2")
    private String kokkaiGiin3NameFirst;

    /** 国家議員3の公職(衆参) */
    @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_NM")
    private String kokkaiGiin3ShuuSan;

    /** 国家議員3現職と候補者の別 */
    @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_KBN")
    private String kokkaiGiin3GenKouho;

    /**
     * 報告年を取得する
     *
     * @return 報告年
     */
    public Integer getHoukokuNen() {
        return houkokuNen;
    }

    /**
     * 報告年を設定する
     *
     * @param houkokuNen 報告年
     */
    public void setHoukokuNen(final Integer houkokuNen) {
        this.houkokuNen = houkokuNen;
    }

    /**
     * 開催日付を取得する
     *
     * @return 開催日付
     */
    public String getDateKaisai() {
        return dateKaisai;
    }

    /**
     * 開催日付を設定する
     *
     * @param dateKaisai 開催日付
     */
    public void setDateKaisai(final String dateKaisai) {
        this.dateKaisai = dateKaisai;
    }

    /**
     * 団体名称を取得する
     *
     * @return 団体名称
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 団体名称を設定する
     *
     * @param dantaiName 団体名称
     */
    public void setDantaiName(final String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /**
     * 団体名称かなを取得する
     *
     * @return 団体名称かな
     */
    public String getDantaiNameKana() {
        return dantaiNameKana;
    }

    /**
     * 団体名称かなを設定する
     *
     * @param dantaiNameKana 団体名称かな
     */
    public void setDantaiNameKana(final String dantaiNameKana) {
        this.dantaiNameKana = dantaiNameKana;
    }

    /**
     * 事務所住所を取得する
     *
     * @return 事務所住所
     */
    public String getJimushoJusho() {
        return jimushoJusho;
    }

    /**
     * 事務所住所を設定する
     *
     * @param jimushoJusho 事務所住所
     */
    public void setJimushoJusho(final String jimushoJusho) {
        this.jimushoJusho = jimushoJusho;
    }

    /**
     * 事務所住所建物を取得する
     *
     * @return 事務所住所建物
     */
    public String getJimushoJushoTatemono() {
        return jimushoJushoTatemono;
    }

    /**
     * 事務所住所建物を設定する
     *
     * @param jimushoJushoTatemono 事務所住所建物
     */
    public void setJimushoJushoTatemono(final String jimushoJushoTatemono) {
        this.jimushoJushoTatemono = jimushoJushoTatemono;
    }

    /**
     * 代表者名の姓を取得する
     *
     * @return 代表者名の姓
     */
    public String getDaihyoushaNameLast() {
        return daihyoushaNameLast;
    }

    /**
     * 代表者名の姓を設定する
     *
     * @param daihyoushaNameLast 代表者名の姓
     */
    public void setDaihyoushaNameLast(final String daihyoushaNameLast) {
        this.daihyoushaNameLast = daihyoushaNameLast;
    }

    /**
     * 代表者名の名を取得する
     *
     * @return 代表者名の名
     */
    public String getDaihyoushaNameFirst() {
        return daihyoushaNameFirst;
    }

    /**
     * 代表者名の名を設定する
     *
     * @param daihyoushaNameFirst 代表者名の名
     */
    public void setDaihyoushaNameFirst(final String daihyoushaNameFirst) {
        this.daihyoushaNameFirst = daihyoushaNameFirst;
    }

    /**
     * 会計責任者の姓を取得する
     *
     * @return 会計責任者の姓
     */
    public String getKaikeiSekinnshaNameLast() {
        return kaikeiSekinnshaNameLast;
    }

    /**
     * 会計責任者の姓を設定する
     *
     * @param kaikeiSekinnshaNameLast 会計責任者の姓
     */
    public void setKaikeiSekinnshaNameLast(final String kaikeiSekinnshaNameLast) {
        this.kaikeiSekinnshaNameLast = kaikeiSekinnshaNameLast;
    }

    /**
     * 会計責任者の名を取得する
     *
     * @return 会計責任者の名
     */
    public String getKaikeiSekinnshaNameFirst() {
        return kaikeiSekinnshaNameFirst;
    }

    /**
     * 会計責任者の名を設定する
     *
     * @param kaikeiSekinnshaNameFirst 会計責任者の名
     */
    public void setKaikeiSekinnshaNameFirst(final String kaikeiSekinnshaNameFirst) {
        this.kaikeiSekinnshaNameFirst = kaikeiSekinnshaNameFirst;
    }

    /**
     * 事務担当者1の姓を取得する
     *
     * @return 事務担当者1の姓
     */
    public String getJimuTantousha1NameLast() {
        return jimuTantousha1NameLast;
    }

    /**
     * 事務担当者1の姓を設定する
     *
     * @param jimuTantousha1NameLast 事務担当者1の姓
     */
    public void setJimuTantousha1NameLast(final String jimuTantousha1NameLast) {
        this.jimuTantousha1NameLast = jimuTantousha1NameLast;
    }

    /**
     * 事務担当者1の名を取得する
     *
     * @return 事務担当者1の名
     */
    public String getJimuTantousha1NameFirst() {
        return jimuTantousha1NameFirst;
    }

    /**
     * 事務担当者1の名を設定する
     *
     * @param jimuTantousha1NameFirst 事務担当者1の名
     */
    public void setJimuTantousha1NameFirst(final String jimuTantousha1NameFirst) {
        this.jimuTantousha1NameFirst = jimuTantousha1NameFirst;
    }

    /**
     * 事務担当者1の電話番号を取得する
     *
     * @return 事務担当者1の電話番号
     */
    public String getJimuTantousha1Tel() {
        return jimuTantousha1Tel;
    }

    /**
     * 事務担当者1の電話番号を設定する
     *
     * @param jimuTantousha1Tel 事務担当者1の電話番号
     */
    public void setJimuTantousha1Tel(final String jimuTantousha1Tel) {
        this.jimuTantousha1Tel = jimuTantousha1Tel;
    }

    /**
     * 事務担当者2の姓を取得する
     *
     * @return 事務担当者2の姓
     */
    public String getJimuTantousha2NameLast() {
        return jimuTantousha2NameLast;
    }

    /**
     * 事務担当者2の姓を設定する
     *
     * @param jimuTantousha2NameLast 事務担当者2の姓
     */
    public void setJimuTantousha2NameLast(final String jimuTantousha2NameLast) {
        this.jimuTantousha2NameLast = jimuTantousha2NameLast;
    }

    /**
     * 事務担当者2の名を取得する
     *
     * @return 事務担当者2の名
     */
    public String getJimuTantousha2NameFirst() {
        return jimuTantousha2NameFirst;
    }

    /**
     * 事務担当者2の名を設定する
     *
     * @param jimuTantousha2NameFirst 事務担当者2の名
     */
    public void setJimuTantousha2NameFirst(final String jimuTantousha2NameFirst) {
        this.jimuTantousha2NameFirst = jimuTantousha2NameFirst;
    }

    /**
     * 事務担当者2の電話番号を取得する
     *
     * @return 事務担当者2の電話番号
     */
    public String getJimuTantousha2Tel() {
        return jimuTantousha2Tel;
    }

    /**
     * 事務担当者2の電話番号を設定する
     *
     * @param jimuTantousha2Tel 事務担当者2の電話番号
     */
    public void setJimuTantousha2Tel(final String jimuTantousha2Tel) {
        this.jimuTantousha2Tel = jimuTantousha2Tel;
    }

    /**
     * 事務担当者3の姓を取得する
     *
     * @return 事務担当者3の姓
     */
    public String getJimuTantousha3NameLast() {
        return jimuTantousha3NameLast;
    }

    /**
     * 事務担当者3の姓を設定する
     *
     * @param jimuTantousha3NameLast 事務担当者3の姓
     */
    public void setJimuTantousha3NameLast(final String jimuTantousha3NameLast) {
        this.jimuTantousha3NameLast = jimuTantousha3NameLast;
    }

    /**
     * 事務担当者3の名を取得する
     *
     * @return 事務担当者3の名
     */
    public String getJimuTantousha3NameFirst() {
        return jimuTantousha3NameFirst;
    }

    /**
     * 事務担当者3の名を設定する
     *
     * @param jimuTantousha3NameFirst 事務担当者3の名
     */
    public void setJimuTantousha3NameFirst(final String jimuTantousha3NameFirst) {
        this.jimuTantousha3NameFirst = jimuTantousha3NameFirst;
    }

    /**
     * 事務担当者3の電話番号を取得する
     *
     * @return 事務担当者3の電話番号
     */
    public String getJimuTantousha3Tel() {
        return jimuTantousha3Tel;
    }

    /**
     * 事務担当者3の電話番号を設定する
     *
     * @param jimuTantousha3Tel 事務担当者3の電話番号
     */
    public void setJimuTantousha3Tel(final String jimuTantousha3Tel) {
        this.jimuTantousha3Tel = jimuTantousha3Tel;
    }

    /**
     * 団体区分を取得する
     *
     * @return 団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 団体区分を設定する
     *
     * @param dantaiKbn 団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }

    /**
     * 活動区域区分を取得する
     *
     * @return 活動区域区分
     */
    public Integer getKatsudouKuikiKbn() {
        return katsudouKuikiKbn;
    }

    /**
     * 活動区域区分を設定する
     *
     * @param katsudouKuikiKbn 活動区域区分
     */
    public void setKatsudouKuikiKbn(final Integer katsudouKuikiKbn) {
        this.katsudouKuikiKbn = katsudouKuikiKbn;
    }

    /**
     * 資金管理団体の有無を取得する
     *
     * @return 資金管理団体の有無
     */
    public Integer getUmuShikinKanrenDantai() {
        return umuShikinKanrenDantai;
    }

    /**
     * 資金管理団体の有無を設定する
     *
     * @param umuShikinKanrenDantai 資金管理団体の有無
     */
    public void setUmuShikinKanrenDantai(final Integer umuShikinKanrenDantai) {
        this.umuShikinKanrenDantai = umuShikinKanrenDantai;
    }

    /**
     * 公職名称を取得する
     *
     * @return 公職名称
     */
    public String getKoushokuName() {
        return koushokuName;
    }

    /**
     * 公職名称を設定する
     *
     * @param koushokuName 公職名称
     */
    public void setKoushokuName(final String koushokuName) {
        this.koushokuName = koushokuName;
    }

    /**
     * 公職者の現職候補者を取得する
     *
     * @return 公職者の現職候補者
     */
    public String getKoushokuGenKouho() {
        return koushokuGenKouho;
    }

    /**
     * 公職者の現職候補者を設定する
     *
     * @param koushokuGenKouho 公職者の現職候補者
     */
    public void setKoushokuGenKouho(final String koushokuGenKouho) {
        this.koushokuGenKouho = koushokuGenKouho;
    }

    /**
     * 資金管理団体代表者名の姓を取得する
     *
     * @return 資金管理団体代表者名の姓
     */
    public String getShikinDaihyouName1() {
        return shikinDaihyouName1;
    }

    /**
     * 資金管理団体代表者名の姓を設定する
     *
     * @param shikinDaihyouName1 資金管理団体代表者名の姓
     */
    public void setShikinDaihyouName1(final String shikinDaihyouName1) {
        this.shikinDaihyouName1 = shikinDaihyouName1;
    }

    /**
     * 資金管理団体代表者名の名を取得する
     *
     * @return 資金管理団体代表者名の名
     */
    public String getShikinDaihyouName2() {
        return shikinDaihyouName2;
    }

    /**
     * 資金管理団体代表者名の名を設定する
     *
     * @param shikinDaihyouName2 資金管理団体代表者名の名
     */
    public void setShikinDaihyouName2(final String shikinDaihyouName2) {
        this.shikinDaihyouName2 = shikinDaihyouName2;
    }

    /**
     * 資金管理団体の指定期間の開始のを取得する
     *
     * @return 資金管理団体の指定期間の開始
     */
    public String getKanriDantaiPeriodStart() {
        return kanriDantaiPeriodStart;
    }

    /**
     * 資金管理団体の指定期間の開始のを設定する
     *
     * @param kanriDantaiPeriodStart 資金管理団体の指定期間の開始
     */
    public void setKanriDantaiPeriodStart(final String kanriDantaiPeriodStart) {
        this.kanriDantaiPeriodStart = kanriDantaiPeriodStart;
    }

    /**
     * 資金管理団体の指定期間の終了を取得する
     *
     * @return 資金管理団体の指定期間の終了
     */
    public String getKanriDantaiPeriodEnd() {
        return kanriDantaiPeriodEnd;
    }

    /**
     * 資金管理団体の指定期間の終了を設定する
     *
     * @param kanriDantaiPeriodEnd 資金管理団体の指定期間の終了
     */
    public void setKanriDantaiPeriodEnd(final String kanriDantaiPeriodEnd) {
        this.kanriDantaiPeriodEnd = kanriDantaiPeriodEnd;
    }

    /**
     * 資金管理団体の指定期間の複数期間を取得する
     *
     * @return 資金管理団体の指定期間の複数期間
     */
    public String getKanriDantaiPeriodRest() {
        return kanriDantaiPeriodRest;
    }

    /**
     * 資金管理団体の指定期間の複数期間を設定する
     *
     * @param kanriDantaiPeriodRest 資金管理団体の指定期間の複数期間
     */
    public void setKanriDantaiPeriodRest(final String kanriDantaiPeriodRest) {
        this.kanriDantaiPeriodRest = kanriDantaiPeriodRest;
    }

    /**
     * 国会議員団体区分を取得する
     *
     * @return 国会議員団体区分
     */
    public Integer getKokkaiGiinDantaiKbn() {
        return kokkaiGiinDantaiKbn;
    }

    /**
     * 国会議員団体区分を設定する
     *
     * @param kokkaiGiinDantaiKbn 国会議員団体区分
     */
    public void setKokkaiGiinDantaiKbn(final Integer kokkaiGiinDantaiKbn) {
        this.kokkaiGiinDantaiKbn = kokkaiGiinDantaiKbn;
    }

    /**
     * 国会議員1の姓を取得する
     *
     * @return 国会議員1の姓
     */
    public String getKokkaiGiin1NameLast() {
        return kokkaiGiin1NameLast;
    }

    /**
     * 国会議員1の姓を設定する
     *
     * @param kokkaiGiin1NameLast 国会議員1の姓
     */
    public void setKokkaiGiin1NameLast(final String kokkaiGiin1NameLast) {
        this.kokkaiGiin1NameLast = kokkaiGiin1NameLast;
    }

    /**
     * 国会議員1の名を取得する
     *
     * @return 国会議員1の名
     */
    public String getKokkaiGiin1NameFirst() {
        return kokkaiGiin1NameFirst;
    }

    /**
     * 国会議員1の名を設定する
     *
     * @param kokkaiGiin1NameFirst 国会議員1の名
     */
    public void setKokkaiGiin1NameFirst(final String kokkaiGiin1NameFirst) {
        this.kokkaiGiin1NameFirst = kokkaiGiin1NameFirst;
    }

    /**
     * 国会議員1の衆参を取得する
     *
     * @return 国会議員1の衆参
     */
    public String getKokkaiGiin1ShuuSan() {
        return kokkaiGiin1ShuuSan;
    }

    /**
     * 国会議員1の衆参を設定する
     *
     * @param kokkaiGiin1ShuuSan 国会議員1の衆参
     */
    public void setKokkaiGiin1ShuuSan(final String kokkaiGiin1ShuuSan) {
        this.kokkaiGiin1ShuuSan = kokkaiGiin1ShuuSan;
    }

    /**
     * 国会議員1の現職候補者を取得する
     *
     * @return 国会議員1の現職候補者
     */
    public String getKokkaiGiin1GenKouho() {
        return kokkaiGiin1GenKouho;
    }

    /**
     * 国会議員1の現職候補者を設定する
     *
     * @param kokkaiGiin1GenKouho 国会議員1の現職候補者
     */
    public void setKokkaiGiin1GenKouho(final String kokkaiGiin1GenKouho) {
        this.kokkaiGiin1GenKouho = kokkaiGiin1GenKouho;
    }

    /**
     * 国会議員団体の特例適用期間開始を取得する
     *
     * @return 国会議員団体の特例適用期間開始
     */
    public String getGiinDantantaiTokureiPeriodStart() {
        return giinDantantaiTokureiPeriodStart;
    }

    /**
     * 国会議員団体の特例適用期間開始を設定する
     *
     * @param giinDantantaiTokureiPeriodStart 国会議員団体の特例適用期間開始
     */
    public void setGiinDantantaiTokureiPeriodStart(final String giinDantantaiTokureiPeriodStart) {
        this.giinDantantaiTokureiPeriodStart = giinDantantaiTokureiPeriodStart;
    }

    /**
     * 国会議員団体の特例適用期間終了を取得する
     *
     * @return 国会議員団体の特例適用期間終了
     */
    public String getGiinDantantaiTokureiPeriodEnd() {
        return giinDantantaiTokureiPeriodEnd;
    }

    /**
     * 国会議員団体の特例適用期間終了を設定する
     *
     * @param giinDantantaiTokureiPeriodEnd 国会議員団体の特例適用期間終了
     */
    public void setGiinDantantaiTokureiPeriodEnd(final String giinDantantaiTokureiPeriodEnd) {
        this.giinDantantaiTokureiPeriodEnd = giinDantantaiTokureiPeriodEnd;
    }

    /**
     * 国会議員団体の特例適用期間の複数期間を取得する
     *
     * @return 国会議員団体の特例適用期間の複数期間
     */
    public String getGiinDantantaiTokureiPeriodRest() {
        return giinDantantaiTokureiPeriodRest;
    }

    /**
     * 国会議員団体の特例適用期間の複数期間を設定する
     *
     * @param giinDantantaiTokureiPeriodRest 国会議員団体の特例適用期間の複数期間
     */
    public void setGiinDantantaiTokureiPeriodRest(final String giinDantantaiTokureiPeriodRest) {
        this.giinDantantaiTokureiPeriodRest = giinDantantaiTokureiPeriodRest;
    }

    /**
     * 国会議員2の姓を取得する
     *
     * @return 国会議員2の姓
     */
    public String getKokkaiGiin2NameLast() {
        return kokkaiGiin2NameLast;
    }

    /**
     * 国会議員2の姓を設定する
     *
     * @param kokkaiGiin2NameLast 国会議員2の姓
     */
    public void setKokkaiGiin2NameLast(final String kokkaiGiin2NameLast) {
        this.kokkaiGiin2NameLast = kokkaiGiin2NameLast;
    }

    /**
     * 国会議員2の名を取得する
     *
     * @return 国会議員2の名
     */
    public String getKokkaiGiin2NameFirst() {
        return kokkaiGiin2NameFirst;
    }

    /**
     * 国会議員2の名を設定する
     *
     * @param kokkaiGiin2NameFirst 国会議員2の名
     */
    public void setKokkaiGiin2NameFirst(final String kokkaiGiin2NameFirst) {
        this.kokkaiGiin2NameFirst = kokkaiGiin2NameFirst;
    }

    /**
     * 国会議員2の衆参を取得する
     *
     * @return 国会議員2の衆参
     */
    public String getKokkaiGiin2ShuuSan() {
        return kokkaiGiin2ShuuSan;
    }

    /**
     * 国会議員2の衆参を設定する
     *
     * @param kokkaiGiin2ShuuSan 国会議員2の衆参
     */
    public void setKokkaiGiin2ShuuSan(final String kokkaiGiin2ShuuSan) {
        this.kokkaiGiin2ShuuSan = kokkaiGiin2ShuuSan;
    }

    /**
     * 国会議員2の現職候補者を取得する
     *
     * @return 国会議員2の現職候補者
     */
    public String getKokkaiGiin2GenKouho() {
        return kokkaiGiin2GenKouho;
    }

    /**
     * 国会議員2の現職候補者を設定する
     *
     * @param kokkaiGiin2GenKouho 国会議員2の現職候補者
     */
    public void setKokkaiGiin2GenKouho(final String kokkaiGiin2GenKouho) {
        this.kokkaiGiin2GenKouho = kokkaiGiin2GenKouho;
    }

    /**
     * 国会議員3の姓を取得する
     *
     * @return 国会議員3の姓
     */
    public String getKokkaiGiin3NameLast() {
        return kokkaiGiin3NameLast;
    }

    /**
     * 国会議員3の姓を設定する
     *
     * @param kokkaiGiin3NameLast 国会議員3の姓
     */
    public void setKokkaiGiin3NameLast(final String kokkaiGiin3NameLast) {
        this.kokkaiGiin3NameLast = kokkaiGiin3NameLast;
    }

    /**
     * 国会議員3の名を取得する
     *
     * @return 国会議員3の名
     */
    public String getKokkaiGiin3NameFirst() {
        return kokkaiGiin3NameFirst;
    }

    /**
     * 国会議員3の名を設定する
     *
     * @param kokkaiGiin3NameFirst 国会議員3の名
     */
    public void setKokkaiGiin3NameFirst(final String kokkaiGiin3NameFirst) {
        this.kokkaiGiin3NameFirst = kokkaiGiin3NameFirst;
    }

    /**
     * 国会議員3の衆参を取得する
     *
     * @return 国会議員3の衆参
     */
    public String getKokkaiGiin3ShuuSan() {
        return kokkaiGiin3ShuuSan;
    }

    /**
     * 国会議員3の衆参を設定する
     *
     * @param kokkaiGiin3ShuuSan 国会議員3の衆参
     */
    public void setKokkaiGiin3ShuuSan(final String kokkaiGiin3ShuuSan) {
        this.kokkaiGiin3ShuuSan = kokkaiGiin3ShuuSan;
    }

    /**
     * 国会議員3の現職候補者を取得する
     *
     * @return 国会議員3の現職候補者
     */
    public String getKokkaiGiin3GenKouho() {
        return kokkaiGiin3GenKouho;
    }

    /**
     * 国会議員3の現職候補者を設定する
     *
     * @param kokkaiGiin3GenKouho 国会議員3の現職候補者
     */
    public void setKokkaiGiin3GenKouho(final String kokkaiGiin3GenKouho) {
        this.kokkaiGiin3GenKouho = kokkaiGiin3GenKouho;
    }

}
