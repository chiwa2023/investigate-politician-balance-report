package mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * offering_balancesheet_income_2024接続用Entity
 */
@Entity
@Table(name = "offering_balancesheet_income_2024")
public class OfferingBalancesheetIncome2024Entity implements Serializable, AllTabeDataHistoryInterface { // NOPMD

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Short INIT_Short = 0;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** 収支報告書収入(その3から12)Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offering_balancesheet_income_id")
    private Long offeringBalancesheetIncomeId = INIT_Long;

    /**
     * 収支報告書収入(その3から12)Idを取得する
     *
     * @return 収支報告書収入(その3から12)Id
     */
    public Long getOfferingBalancesheetIncomeId() {
        return offeringBalancesheetIncomeId;
    }

    /**
     * 収支報告書収入(その3から12)Idを設定する
     *
     * @param offeringBalancesheetIncomeId 収支報告書収入(その3から12)Id
     */
    public void setOfferingBalancesheetIncomeId(final Long offeringBalancesheetIncomeId) {
        this.offeringBalancesheetIncomeId = offeringBalancesheetIncomeId;
    }

    /** 収支報告書収入(その3から12)同一識別コード */
    @Column(name = "offering_balancesheet_income_code")
    private Long offeringBalancesheetIncomeCode = INIT_Long;

    /**
     * 収支報告書収入(その3から12)同一識別コードを取得する
     *
     * @return 収支報告書収入(その3から12)同一識別コード
     */
    public Long getOfferingBalancesheetIncomeCode() {
        return offeringBalancesheetIncomeCode;
    }

    /**
     * 収支報告書収入(その3から12)同一識別コードを設定する
     *
     * @param offeringBalancesheetIncomeCode 収支報告書収入(その3から12)同一識別コード
     */
    public void setOfferingBalancesheetIncomeCode(final Long offeringBalancesheetIncomeCode) {
        this.offeringBalancesheetIncomeCode = offeringBalancesheetIncomeCode;
    }

    /** 最新区分 */
    @Column(name = "saishin_kbn")
    private Integer saishinKbn = INIT_Integer;

    /**
     * 最新区分を取得する
     *
     * @return 最新区分
     */
    @Override
    public Integer getSaishinKbn() {
        return saishinKbn;
    }

    /**
     * 最新区分を設定する
     *
     * @param saishinKbn 最新区分
     */
    @Override
    public void setSaishinKbn(final Integer saishinKbn) {
        this.saishinKbn = saishinKbn;
    }

    /** 文書同一識別コード */
    @Column(name = "document_code")
    private Long documentCode = INIT_Long;

    /**
     * 文書同一識別コードを取得する
     *
     * @return 文書同一識別コード
     */
    public Long getDocumentCode() {
        return documentCode;
    }

    /**
     * 文書同一識別コードを設定する
     *
     * @param documentCode 文書同一識別コード
     */
    public void setDocumentCode(final Long documentCode) {
        this.documentCode = documentCode;
    }

    /** 報告年 */
    @Column(name = "houkoku_nen")
    private Integer houkokuNen = INIT_Integer;

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

    /** 提出日 */
    @Column(name = "offering_date")
    private LocalDate offeringDate = INIT_LocalDate;

    /**
     * 提出日を取得する
     *
     * @return 提出日
     */
    public LocalDate getOfferingDate() {
        return offeringDate;
    }

    /**
     * 提出日を設定する
     *
     * @param offeringDate 提出日
     */
    public void setOfferingDate(final LocalDate offeringDate) {
        this.offeringDate = offeringDate;
    }

    /** 政治団体Id */
    @Column(name = "political_organization_id")
    private Long politicalOrganizationId = INIT_Long;

    /**
     * 政治団体Idを取得する
     *
     * @return 政治団体Id
     */
    public Long getPoliticalOrganizationId() {
        return politicalOrganizationId;
    }

    /**
     * 政治団体Idを設定する
     *
     * @param politicalOrganizationId 政治団体Id
     */
    public void setPoliticalOrganizationId(final Long politicalOrganizationId) {
        this.politicalOrganizationId = politicalOrganizationId;
    }

    /** 政治団体同一識別コード */
    @Column(name = "political_organization_code")
    private Integer politicalOrganizationCode = INIT_Integer;

    /**
     * 政治団体同一識別コードを取得する
     *
     * @return 政治団体同一識別コード
     */
    public Integer getPoliticalOrganizationCode() {
        return politicalOrganizationCode;
    }

    /**
     * 政治団体同一識別コードを設定する
     *
     * @param politicalOrganizationCode 政治団体同一識別コード
     */
    public void setPoliticalOrganizationCode(final Integer politicalOrganizationCode) {
        this.politicalOrganizationCode = politicalOrganizationCode;
    }

    /** 政治団体名称 */
    @Column(name = "political_organization_name")
    private String politicalOrganizationName = INIT_String;

    /**
     * 政治団体名称を取得する
     *
     * @return 政治団体名称
     */
    public String getPoliticalOrganizationName() {
        return politicalOrganizationName;
    }

    /**
     * 政治団体名称を設定する
     *
     * @param politicalOrganizationName 政治団体名称
     */
    public void setPoliticalOrganizationName(final String politicalOrganizationName) {
        this.politicalOrganizationName = politicalOrganizationName;
    }

    /** 原文書政治団体代表者名 */
    @Column(name = "daihyou_name")
    private String daihyouName = INIT_String;

    /**
     * 原文書政治団体代表者名を取得する
     *
     * @return 原文書政治団体代表者名
     */
    public String getDaihyouName() {
        return daihyouName;
    }

    /**
     * 原文書政治団体代表者名を設定する
     *
     * @param daihyouName 原文書政治団体代表者名
     */
    public void setDaihyouName(final String daihyouName) {
        this.daihyouName = daihyouName;
    }

    /** 原文書政治団体名称 */
    @Column(name = "dantai_name")
    private String dantaiName = INIT_String;

    /**
     * 原文書政治団体名称を取得する
     *
     * @return 原文書政治団体名称
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 原文書政治団体名称を設定する
     *
     * @param dantaiName 原文書政治団体名称
     */
    public void setDantaiName(final String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /** 関連者区分 */
    @Column(name = "relation_kbn")
    private Integer relationKbn = INIT_Integer;

    /**
     * 関連者区分を取得する
     *
     * @return 関連者区分
     */
    public Integer getRelationKbn() {
        return relationKbn;
    }

    /**
     * 関連者区分を設定する
     *
     * @param relationKbn 関連者区分
     */
    public void setRelationKbn(final Integer relationKbn) {
        this.relationKbn = relationKbn;
    }

    /** 代表者関連者Id */
    @Column(name = "relation_person_id_delegate")
    private Long relationPersonIdDelegate = INIT_Long;

    /**
     * 代表者関連者Idを取得する
     *
     * @return 代表者関連者Id
     */
    public Long getRelationPersonIdDelegate() {
        return relationPersonIdDelegate;
    }

    /**
     * 代表者関連者Idを設定する
     *
     * @param relationPersonIdDelegate 代表者関連者Id
     */
    public void setRelationPersonIdDelegate(final Long relationPersonIdDelegate) {
        this.relationPersonIdDelegate = relationPersonIdDelegate;
    }

    /** 代表者関連者同一識別コード */
    @Column(name = "relation_person_code_delegate")
    private Integer relationPersonCodeDelegate = INIT_Integer;

    /**
     * 代表者関連者同一識別コードを取得する
     *
     * @return 代表者関連者同一識別コード
     */
    public Integer getRelationPersonCodeDelegate() {
        return relationPersonCodeDelegate;
    }

    /**
     * 代表者関連者同一識別コードを設定する
     *
     * @param relationPersonCodeDelegate 代表者関連者同一識別コード
     */
    public void setRelationPersonCodeDelegate(final Integer relationPersonCodeDelegate) {
        this.relationPersonCodeDelegate = relationPersonCodeDelegate;
    }

    /** 代表者関連者名称 */
    @Column(name = "relation_person_name_delegate")
    private String relationPersonNameDelegate = INIT_String;

    /**
     * 代表者関連者名称を取得する
     *
     * @return 代表者関連者名称
     */
    public String getRelationPersonNameDelegate() {
        return relationPersonNameDelegate;
    }

    /**
     * 代表者関連者名称を設定する
     *
     * @param relationPersonNameDelegate 代表者関連者名称
     */
    public void setRelationPersonNameDelegate(final String relationPersonNameDelegate) {
        this.relationPersonNameDelegate = relationPersonNameDelegate;
    }

    /** 様式区分 */
    @Column(name = "youshiki_kbn")
    private Integer youshikiKbn = INIT_Integer;

    /**
     * 様式区分を取得する
     *
     * @return 様式区分
     */
    public Integer getYoushikiKbn() {
        return youshikiKbn;
    }

    /**
     * 様式区分を設定する
     *
     * @param youshikiKbn 様式区分
     */
    public void setYoushikiKbn(final Integer youshikiKbn) {
        this.youshikiKbn = youshikiKbn;
    }

    /** 様式枝区分項目 */
    @Column(name = "youshiki_eda_kbn")
    private Integer youshikiEdaKbn = INIT_Integer;

    /**
     * 様式枝区分項目を取得する
     *
     * @return 様式枝区分項目
     */
    public Integer getYoushikiEdaKbn() {
        return youshikiEdaKbn;
    }

    /**
     * 様式枝区分項目を設定する
     *
     * @param youshikiEdaKbn 様式枝区分項目
     */
    public void setYoushikiEdaKbn(final Integer youshikiEdaKbn) {
        this.youshikiEdaKbn = youshikiEdaKbn;
    }

    /** ページ計 */
    @Column(name = "page_total")
    private Long pageTotal = INIT_Long;

    /**
     * ページ計を取得する
     *
     * @return ページ計
     */
    public Long getPageTotal() {
        return pageTotal;
    }

    /**
     * ページ計を設定する
     *
     * @param pageTotal ページ計
     */
    public void setPageTotal(final Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    /** 未満計 */
    @Column(name = "miman_total")
    private String mimanTotal = INIT_String;

    /**
     * 未満計を取得する
     *
     * @return 未満計
     */
    public String getMimanTotal() {
        return mimanTotal;
    }

    /**
     * 未満計を設定する
     *
     * @param mimanTotal 未満計
     */
    public void setMimanTotal(final String mimanTotal) {
        this.mimanTotal = mimanTotal;
    }

    /** その他合計 */
    @Column(name = "sonota_total")
    private String sonotaTotal = INIT_String;

    /**
     * その他合計を取得する
     *
     * @return その他合計
     */
    public String getSonotaTotal() {
        return sonotaTotal;
    }

    /**
     * その他合計を設定する
     *
     * @param sonotaTotal その他合計
     */
    public void setSonotaTotal(final String sonotaTotal) {
        this.sonotaTotal = sonotaTotal;
    }

    /** パーティ名称 */
    @Column(name = "party_name")
    private String partyName = INIT_String;

    /**
     * パーティ名称を取得する
     *
     * @return パーティ名称
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * パーティ名称を設定する
     *
     * @param partyName パーティ名称
     */
    public void setPartyName(final String partyName) {
        this.partyName = partyName;
    }

    /** ソート番号 */
    @Column(name = "sort_no")
    private String sortNo = INIT_String;

    /**
     * ソート番号を取得する
     *
     * @return ソート番号
     */
    public String getSortNo() {
        return sortNo;
    }

    /**
     * ソート番号を設定する
     *
     * @param sortNo ソート番号
     */
    public void setSortNo(final String sortNo) {
        this.sortNo = sortNo;
    }

    /** 連番 */
    @Column(name = "ichiren_no")
    private Integer ichirenNo = INIT_Integer;

    /**
     * 連番を取得する
     *
     * @return 連番
     */
    public Integer getIchirenNo() {
        return ichirenNo;
    }

    /**
     * 連番を設定する
     *
     * @param ichirenNo 連番
     */
    public void setIchirenNo(final Integer ichirenNo) {
        this.ichirenNo = ichirenNo;
    }

    /** 項目名称 */
    @Column(name = "item_name")
    private String itemName = INIT_String;

    /**
     * 項目名称を取得する
     *
     * @return 項目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 項目名称を設定する
     *
     * @param itemName 項目名称
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /** 金額 */
    @Column(name = "kingaku")
    private Long kingaku = INIT_Long;

    /**
     * 金額を取得する
     *
     * @return 金額
     */
    public Long getKingaku() {
        return kingaku;
    }

    /**
     * 金額を設定する
     *
     * @param kingaku 金額
     */
    public void setKingaku(final Long kingaku) {
        this.kingaku = kingaku;
    }

    /** 発生日 */
    @Column(name = "accrual_date")
    private String accrualDate = INIT_String;

    /**
     * 発生日を取得する
     *
     * @return 発生日
     */
    public String getAccrualDate() {
        return accrualDate;
    }

    /**
     * 発生日を設定する
     *
     * @param accrualDate 発生日
     */
    public void setAccrualDate(final String accrualDate) {
        this.accrualDate = accrualDate;
    }

    /** 発生日実値 */
    @Column(name = "accrual_date_value")
    private LocalDate accrualDateValue = INIT_LocalDate;

    /**
     * 発生日実値を取得する
     *
     * @return 発生日実値
     */
    public LocalDate getAccrualDateValue() {
        return accrualDateValue;
    }

    /**
     * 発生日実値を設定する
     *
     * @param accrualDateValue 発生日実値
     */
    public void setAccrualDateValue(final LocalDate accrualDateValue) {
        this.accrualDateValue = accrualDateValue;
    }

    /** 備考 */
    @Column(name = "bikou")
    private String bikou = INIT_String;

    /**
     * 備考を取得する
     *
     * @return 備考
     */
    public String getBikou() {
        return bikou;
    }

    /**
     * 備考を設定する
     *
     * @param bikou 備考
     */
    public void setBikou(final String bikou) {
        this.bikou = bikou;
    }

    /** 支出した相手先名 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 支出した相手先名を取得する
     *
     * @return 支出した相手先名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 支出した相手先名を設定する
     *
     * @param partnerName 支出した相手先名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 支出した相手先住所 */
    @Column(name = "partner_juusho")
    private String partnerJuusho = INIT_String;

    /**
     * 支出した相手先住所を取得する
     *
     * @return 支出した相手先住所
     */
    public String getPartnerJuusho() {
        return partnerJuusho;
    }

    /**
     * 支出した相手先住所を設定する
     *
     * @param partnerJuusho 支出した相手先住所
     */
    public void setPartnerJuusho(final String partnerJuusho) {
        this.partnerJuusho = partnerJuusho;
    }

    /** パーティ支払人数 */
    @Column(name = "shiharaisu")
    private Integer shiharaisu = INIT_Integer;

    /**
     * パーティ支払人数を取得する
     *
     * @return パーティ支払人数
     */
    public Integer getShiharaisu() {
        return shiharaisu;
    }

    /**
     * パーティ支払人数を設定する
     *
     * @param shiharaisu パーティ支払人数
     */
    public void setShiharaisu(final Integer shiharaisu) {
        this.shiharaisu = shiharaisu;
    }

    /** パーティ開催場所 */
    @Column(name = "kaisai_basho")
    private String kaisaiBasho = INIT_String;

    /**
     * パーティ開催場所を取得する
     *
     * @return パーティ開催場所
     */
    public String getKaisaiBasho() {
        return kaisaiBasho;
    }

    /**
     * パーティ開催場所を設定する
     *
     * @param kaisaiBasho パーティ開催場所
     */
    public void setKaisaiBasho(final String kaisaiBasho) {
        this.kaisaiBasho = kaisaiBasho;
    }

    /** 税額控除フラグ */
    @Column(name = "flg_zeigaku_kohjo")
    private Short flgZeigakuKohjo = INIT_Short;

    /**
     * 税額控除フラグを取得する
     *
     * @return 税額控除フラグ
     */
    public Short getFlgZeigakuKohjo() {
        return flgZeigakuKohjo;
    }

    /**
     * 税額控除フラグを設定する
     *
     * @param flgZeigakuKohjo 税額控除フラグ
     */
    public void setFlgZeigakuKohjo(final Short flgZeigakuKohjo) {
        this.flgZeigakuKohjo = flgZeigakuKohjo;
    }

    /** 通し番号 */
    @Column(name = "tohshibangou")
    private Integer tohshibangou = INIT_Integer;

    /**
     * 通し番号を取得する
     *
     * @return 通し番号
     */
    public Integer getTohshibangou() {
        return tohshibangou;
    }

    /**
     * 通し番号を設定する
     *
     * @param tohshibangou 通し番号
     */
    public void setTohshibangou(final Integer tohshibangou) {
        this.tohshibangou = tohshibangou;
    }

    /** 行区分 */
    @Column(name = "gyoukubun")
    private Short gyoukubun = INIT_Short;

    /**
     * 行区分を取得する
     *
     * @return 行区分
     */
    public Short getGyoukubun() {
        return gyoukubun;
    }

    /**
     * 行区分を設定する
     *
     * @param gyoukubun 行区分
     */
    public void setGyoukubun(final Short gyoukubun) {
        this.gyoukubun = gyoukubun;
    }

    /** 職業 */
    @Column(name = "shokugyou")
    private String shokugyou = INIT_String;

    /**
     * 職業を取得する
     *
     * @return 職業
     */
    public String getShokugyou() {
        return shokugyou;
    }

    /**
     * 職業を設定する
     *
     * @param shokugyou 職業
     */
    public void setShokugyou(final String shokugyou) {
        this.shokugyou = shokugyou;
    }

    /** あっせんの期間 */
    @Column(name = "period_mediate")
    private String periodMediate = INIT_String;

    /**
     * あっせんの期間を取得する
     *
     * @return あっせんの期間
     */
    public String getPeriodMediate() {
        return periodMediate;
    }

    /**
     * あっせんの期間を設定する
     *
     * @param periodMediate あっせんの期間
     */
    public void setPeriodMediate(final String periodMediate) {
        this.periodMediate = periodMediate;
    }

    /** 支払者関連者Id(個人) */
    @Column(name = "relation_person_id_income")
    private Long relationPersonIdIncome = INIT_Long;

    /**
     * 支払者関連者Id(個人)を取得する
     *
     * @return 支払者関連者Id(個人)
     */
    public Long getRelationPersonIdIncome() {
        return relationPersonIdIncome;
    }

    /**
     * 支払者関連者Id(個人)を設定する
     *
     * @param relationPersonIdIncome 支払者関連者Id(個人)
     */
    public void setRelationPersonIdIncome(final Long relationPersonIdIncome) {
        this.relationPersonIdIncome = relationPersonIdIncome;
    }

    /** 支払者関連者同一識別コード(個人) */
    @Column(name = "relation_person_code_income")
    private Integer relationPersonCodeIncome = INIT_Integer;

    /**
     * 支払者関連者同一識別コード(個人)を取得する
     *
     * @return 支払者関連者同一識別コード(個人)
     */
    public Integer getRelationPersonCodeIncome() {
        return relationPersonCodeIncome;
    }

    /**
     * 支払者関連者同一識別コード(個人)を設定する
     *
     * @param relationPersonCodeIncome 支払者関連者同一識別コード(個人)
     */
    public void setRelationPersonCodeIncome(final Integer relationPersonCodeIncome) {
        this.relationPersonCodeIncome = relationPersonCodeIncome;
    }

    /** 支払者関連者名称(個人) */
    @Column(name = "relation_person_name_income")
    private String relationPersonNameIncome = INIT_String;

    /**
     * 支払者関連者名称(個人)を取得する
     *
     * @return 支払者関連者名称(個人)
     */
    public String getRelationPersonNameIncome() {
        return relationPersonNameIncome;
    }

    /**
     * 支払者関連者名称(個人)を設定する
     *
     * @param relationPersonNameIncome 支払者関連者名称(個人)
     */
    public void setRelationPersonNameIncome(final String relationPersonNameIncome) {
        this.relationPersonNameIncome = relationPersonNameIncome;
    }

    /** 支払者関連者Id(法人) */
    @Column(name = "relation_corp_id_income")
    private Long relationCorpIdIncome = INIT_Long;

    /**
     * 支払者関連者Id(法人)を取得する
     *
     * @return 支払者関連者Id(法人)
     */
    public Long getRelationCorpIdIncome() {
        return relationCorpIdIncome;
    }

    /**
     * 支払者関連者Id(法人)を設定する
     *
     * @param relationCorpIdIncome 支払者関連者Id(法人)
     */
    public void setRelationCorpIdIncome(final Long relationCorpIdIncome) {
        this.relationCorpIdIncome = relationCorpIdIncome;
    }

    /** 支払者関連者同一識別コード(法人) */
    @Column(name = "relation_corp_code_income")
    private Integer relationCorpCodeIncome = INIT_Integer;

    /**
     * 支払者関連者同一識別コード(法人)を取得する
     *
     * @return 支払者関連者同一識別コード(法人)
     */
    public Integer getRelationCorpCodeIncome() {
        return relationCorpCodeIncome;
    }

    /**
     * 支払者関連者同一識別コード(法人)を設定する
     *
     * @param relationCorpCodeIncome 支払者関連者同一識別コード(法人)
     */
    public void setRelationCorpCodeIncome(final Integer relationCorpCodeIncome) {
        this.relationCorpCodeIncome = relationCorpCodeIncome;
    }

    /** 支払者関連者Id(法人) */
    @Column(name = "relation_corp_name_inccome")
    private String relationCorpNameInccome = INIT_String;

    /**
     * 支払者関連者Id(法人)を取得する
     *
     * @return 支払者関連者Id(法人)
     */
    public String getRelationCorpNameInccome() {
        return relationCorpNameInccome;
    }

    /**
     * 支払者関連者Id(法人)を設定する
     *
     * @param relationCorpNameInccome 支払者関連者Id(法人)
     */
    public void setRelationCorpNameInccome(final String relationCorpNameInccome) {
        this.relationCorpNameInccome = relationCorpNameInccome;
    }

    /** 支払者関連者同一識別コード(政治団体) */
    @Column(name = "relation_political_org_id_income")
    private Long relationPoliticalOrgIdIncome = INIT_Long;

    /**
     * 支払者関連者同一識別コード(政治団体)を取得する
     *
     * @return 支払者関連者同一識別コード(政治団体)
     */
    public Long getRelationPoliticalOrgIdIncome() {
        return relationPoliticalOrgIdIncome;
    }

    /**
     * 支払者関連者同一識別コード(政治団体)を設定する
     *
     * @param relationPoliticalOrgIdIncome 支払者関連者同一識別コード(政治団体)
     */
    public void setRelationPoliticalOrgIdIncome(final Long relationPoliticalOrgIdIncome) {
        this.relationPoliticalOrgIdIncome = relationPoliticalOrgIdIncome;
    }

    /** 支払者関連者名称(政治団体) */
    @Column(name = "relation_political_org_code_income")
    private Integer relationPoliticalOrgCodeIncome = INIT_Integer;

    /**
     * 支払者関連者名称(政治団体)を取得する
     *
     * @return 支払者関連者名称(政治団体)
     */
    public Integer getRelationPoliticalOrgCodeIncome() {
        return relationPoliticalOrgCodeIncome;
    }

    /**
     * 支払者関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgCodeIncome 支払者関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgCodeIncome(final Integer relationPoliticalOrgCodeIncome) {
        this.relationPoliticalOrgCodeIncome = relationPoliticalOrgCodeIncome;
    }

    /** 支払者関連者名称(政治団体) */
    @Column(name = "relation_political_org_name_income")
    private String relationPoliticalOrgNameIncome = INIT_String;

    /**
     * 支払者関連者名称(政治団体)を取得する
     *
     * @return 支払者関連者名称(政治団体)
     */
    public String getRelationPoliticalOrgNameIncome() {
        return relationPoliticalOrgNameIncome;
    }

    /**
     * 支払者関連者名称(政治団体)を設定する
     *
     * @param relationPoliticalOrgNameIncome 支払者関連者名称(政治団体)
     */
    public void setRelationPoliticalOrgNameIncome(final String relationPoliticalOrgNameIncome) {
        this.relationPoliticalOrgNameIncome = relationPoliticalOrgNameIncome;
    }

    /** 自由検索用カラム */
    @Column(name = "search_words")
    private String searchWords = INIT_String;

    /**
     * 自由検索用カラムを取得する
     *
     * @return 自由検索用カラム
     */
    public String getSearchWords() {
        return searchWords;
    }

    /**
     * 自由検索用カラムを設定する
     *
     * @param searchWords 自由検索用カラム
     */
    public void setSearchWords(final String searchWords) {
        this.searchWords = searchWords;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Long insertUserId = INIT_Long;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    @Override
    public Long getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    @Override
    public void setInsertUserId(final Long insertUserId) {
        this.insertUserId = insertUserId;
    }

    /** 挿入ユーザ同一識別コード */
    @Column(name = "insert_user_code")
    private Integer insertUserCode = INIT_Integer;

    /**
     * 挿入ユーザ同一識別コードを取得する
     *
     * @return 挿入ユーザ同一識別コード
     */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザ同一識別コードを設定する
     *
     * @param insertUserCode 挿入ユーザ同一識別コード
     */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** 挿入ユーザ姓名 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_String;

    /**
     * 挿入ユーザ姓名を取得する
     *
     * @return 挿入ユーザ姓名
     */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ姓名を設定する
     *
     * @param insertUserName 挿入ユーザ姓名
     */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }

    /** 挿入タイムスタンプ */
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp = INIT_Timestamp;

    /**
     * 挿入タイムスタンプを取得する
     *
     * @return 挿入タイムスタンプ
     */
    @Override
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入タイムスタンプを設定する
     *
     * @param insertTimestamp 挿入タイムスタンプ
     */
    @Override
    public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** 更新ユーザId */
    @Column(name = "update_user_id")
    private Long updateUserId = INIT_Long;

    /**
     * 更新ユーザIdを取得する
     *
     * @return 更新ユーザId
     */
    @Override
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新ユーザIdを設定する
     *
     * @param updateUserId 更新ユーザId
     */
    @Override
    public void setUpdateUserId(final Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /** 更新ユーザ同一識別コード */
    @Column(name = "update_user_code")
    private Integer updateUserCode = INIT_Integer;

    /**
     * 更新ユーザ同一識別コードを取得する
     *
     * @return 更新ユーザ同一識別コード
     */
    @Override
    public Integer getUpdateUserCode() {
        return updateUserCode;
    }

    /**
     * 更新ユーザ同一識別コードを設定する
     *
     * @param updateUserCode 更新ユーザ同一識別コード
     */
    @Override
    public void setUpdateUserCode(final Integer updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    /** 更新ユーザ姓名 */
    @Column(name = "update_user_name")
    private String updateUserName = INIT_String;

    /**
     * 更新ユーザ姓名を取得する
     *
     * @return 更新ユーザ姓名
     */
    @Override
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 更新ユーザ姓名を設定する
     *
     * @param updateUserName 更新ユーザ姓名
     */
    @Override
    public void setUpdateUserName(final String updateUserName) {
        this.updateUserName = updateUserName;
    }

    /** 更新タイムスタンプ */
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp = INIT_Timestamp;

    /**
     * 更新タイムスタンプを取得する
     *
     * @return 更新タイムスタンプ
     */
    @Override
    public LocalDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 更新タイムスタンプを設定する
     *
     * @param updateTimestamp 更新タイムスタンプ
     */
    @Override
    public void setUpdateTimestamp(final LocalDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    /** 政治団体区分 */
    private String dantaiKbn = INIT_String;

    /**
     * 政治団体区分
     *
     * @return 政治団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 政治団体区分
     *
     * @param dantaiKbn 政治団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }

}
