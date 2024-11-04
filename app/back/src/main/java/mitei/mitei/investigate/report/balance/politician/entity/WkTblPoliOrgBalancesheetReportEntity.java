package mitei.mitei.investigate.report.balance.politician.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;


/**
 * wk_tbl_poli_org_balancesheet_report接続用Entity
 */
@Entity
@Table(name = "wk_tbl_poli_org_balancesheet_report")
public class WkTblPoliOrgBalancesheetReportEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;
    
    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948,7,29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** 政治資金収支報告書登録準備ワークテーブルId */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_poli_org_balancesheet_report_id")
    private Long wkTblPoliOrgBalancesheetReportId = INIT_Long;

    /**
     * 政治資金収支報告書登録準備ワークテーブルIdを取得する
     *
     * @return 政治資金収支報告書登録準備ワークテーブルId
     */
    public Long getWkTblPoliOrgBalancesheetReportId() {
        return wkTblPoliOrgBalancesheetReportId;
    }

    /**
     * 政治資金収支報告書登録準備ワークテーブルIdを設定する
     *
     * @param wkTblPoliOrgBalancesheetReportId 政治資金収支報告書登録準備ワークテーブルId
     */
    public void setWkTblPoliOrgBalancesheetReportId(final Long wkTblPoliOrgBalancesheetReportId) {
        this.wkTblPoliOrgBalancesheetReportId = wkTblPoliOrgBalancesheetReportId;
    }

    /** 政治資金収支報告書登録準備ワークテーブル同一識別コード */
    @Column(name = "wk_tbl_poli_org_balancesheet_report_code")
    private Integer wkTblPoliOrgBalancesheetReportCode = INIT_Integer;

    /**
     * 政治資金収支報告書登録準備ワークテーブル同一識別コードを取得する
     *
     * @return 政治資金収支報告書登録準備ワークテーブル同一識別コード
     */
    public Integer getWkTblPoliOrgBalancesheetReportCode() {
        return wkTblPoliOrgBalancesheetReportCode;
    }

    /**
     * 政治資金収支報告書登録準備ワークテーブル同一識別コードを設定する
     *
     * @param wkTblPoliOrgBalancesheetReportCode 政治資金収支報告書登録準備ワークテーブル同一識別コード
     */
    public void setWkTblPoliOrgBalancesheetReportCode(final Integer wkTblPoliOrgBalancesheetReportCode) {
        this.wkTblPoliOrgBalancesheetReportCode = wkTblPoliOrgBalancesheetReportCode;
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

    /** 書証Id */
    @Column(name = "shoshou_id")
    private Long shoshouId = INIT_Long;

    /**
     * 書証Idを取得する
     *
     * @return 書証Id
     */
    public Long getShoshouId() {
        return shoshouId;
    }

    /**
     * 書証Idを設定する
     *
     * @param shoshouId 書証Id
     */
    public void setShoshouId(final Long shoshouId) {
        this.shoshouId = shoshouId;
    }

    /** 書証同一識別コード */
    @Column(name = "shoshou_code")
    private Long shoshouCode = INIT_Long;

    /**
     * 書証同一識別コードを取得する
     *
     * @return 書証同一識別コード
     */
    public Long getShoshouCode() {
        return shoshouCode;
    }

    /**
     * 書証同一識別コードを設定する
     *
     * @param shoshouCode 書証同一識別コード
     */
    public void setShoshouCode(final Long shoshouCode) {
        this.shoshouCode = shoshouCode;
    }

    /** 書証区分 */
    @Column(name = "shoshou_kbn")
    private Integer shoshouKbn = INIT_Integer;

    /**
     * 書証区分を取得する
     *
     * @return 書証区分
     */
    public Integer getShoshouKbn() {
        return shoshouKbn;
    }

    /**
     * 書証区分を設定する
     *
     * @param shoshouKbn 書証区分
     */
    public void setShoshouKbn(final Integer shoshouKbn) {
        this.shoshouKbn = shoshouKbn;
    }

    /** ファイルパス */
    @Column(name = "full_path")
    private String fullPath = INIT_String;

    /**
     * ファイルパスを取得する
     *
     * @return ファイルパス
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * ファイルパスを設定する
     *
     * @param fullPath ファイルパス
     */
    public void setFullPath(final String fullPath) {
        this.fullPath = fullPath;
    }

    /** 保存子フォルダ */
    @Column(name = "child_dir")
    private String childDir = INIT_String;

    /**
     * 保存子フォルダを取得する
     *
     * @return 保存子フォルダ
     */
    public String getChildDir() {
        return childDir;
    }

    /**
     * 保存子フォルダを設定する
     *
     * @param childDir 保存子フォルダ
     */
    public void setChildDir(final String childDir) {
        this.childDir = childDir;
    }

    /** ファイル名 */
    @Column(name = "file_name")
    private String fileName = INIT_String;

    /**
     * ファイル名を取得する
     *
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ファイル名を設定する
     *
     * @param fileName ファイル名
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /** 登録時間 */
    @Column(name = "regist_time_text")
    private String registTimeText = INIT_String;

    /**
     * 登録時間を取得する
     *
     * @return 登録時間
     */
    public String getRegistTimeText() {
        return registTimeText;
    }

    /**
     * 登録時間を設定する
     *
     * @param registTimeText 登録時間
     */
    public void setRegistTimeText(final String registTimeText) {
        this.registTimeText = registTimeText;
    }

    /** 読み取り文字コード */
    @Column(name = "charset")
    private String charset = INIT_String;

    /**
     * 読み取り文字コードを取得する
     *
     * @return 読み取り文字コード
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 読み取り文字コードを設定する
     *
     * @param charset 読み取り文字コード
     */
    public void setCharset(final String charset) {
        this.charset = charset;
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

    /** 原文書団体名称 */
    @Column(name = "dantai_name")
    private String dantaiName = INIT_String;

    /**
     * 原文書団体名称を取得する
     *
     * @return 原文書団体名称
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 原文書団体名称を設定する
     *
     * @param dantaiName 原文書団体名称
     */
    public void setDantaiName(final String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /** 原文書代表者名 */
    @Column(name = "daihyou_name")
    private String daihyouName = INIT_String;

    /**
     * 原文書代表者名を取得する
     *
     * @return 原文書代表者名
     */
    public String getDaihyouName() {
        return daihyouName;
    }

    /**
     * 原文書代表者名を設定する
     *
     * @param daihyouName 原文書代表者名
     */
    public void setDaihyouName(final String daihyouName) {
        this.daihyouName = daihyouName;
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

    /** 代表者関連者個人Id */
    @Column(name = "relation_person_id_delegate")
    private Long relationPersonIdDelegate = INIT_Long;

    /**
     * 代表者関連者個人Idを取得する
     *
     * @return 代表者関連者個人Id
     */
    public Long getRelationPersonIdDelegate() {
        return relationPersonIdDelegate;
    }

    /**
     * 代表者関連者個人Idを設定する
     *
     * @param relationPersonIdDelegate 代表者関連者個人Id
     */
    public void setRelationPersonIdDelegate(final Long relationPersonIdDelegate) {
        this.relationPersonIdDelegate = relationPersonIdDelegate;
    }

    /** 関連者同一識別コード */
    @Column(name = "relation_person_code_delegate")
    private Integer relationPersonCodeDelegate = INIT_Integer;

    /**
     * 関連者同一識別コードを取得する
     *
     * @return 関連者同一識別コード
     */
    public Integer getRelationPersonCodeDelegate() {
        return relationPersonCodeDelegate;
    }

    /**
     * 関連者同一識別コードを設定する
     *
     * @param relationPersonCodeDelegate 関連者同一識別コード
     */
    public void setRelationPersonCodeDelegate(final Integer relationPersonCodeDelegate) {
        this.relationPersonCodeDelegate = relationPersonCodeDelegate;
    }

    /** 関連者名称 */
    @Column(name = "relation_person_name_delegate")
    private String relationPersonNameDelegate = INIT_String;

    /**
     * 関連者名称を取得する
     *
     * @return 関連者名称
     */
    public String getRelationPersonNameDelegate() {
        return relationPersonNameDelegate;
    }

    /**
     * 関連者名称を設定する
     *
     * @param relationPersonNameDelegate 関連者名称
     */
    public void setRelationPersonNameDelegate(final String relationPersonNameDelegate) {
        this.relationPersonNameDelegate = relationPersonNameDelegate;
    }

    /** 報告年度 */
    @Column(name = "houkoku_nen")
    private Integer houkokuNen = INIT_Integer;

    /**
     * 報告年度を取得する
     *
     * @return 報告年度
     */
    public Integer getHoukokuNen() {
        return houkokuNen;
    }

    /**
     * 報告年度を設定する
     *
     * @param houkokuNen 報告年度
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

    /** 政治団体追加該否 */
    @Column(name = "is_add_organization")
    private Boolean isAddOrganization = INIT_Boolean;

    /**
     * 政治団体追加該否を取得する
     *
     * @return 政治団体追加該否
     */
    public Boolean getIsAddOrganization() {
        return isAddOrganization;
    }

    /**
     * 政治団体追加該否を設定する
     *
     * @param isAddOrganization 政治団体追加該否
     */
    public void setIsAddOrganization(final Boolean isAddOrganization) {
        this.isAddOrganization = isAddOrganization;
    }

    /** 開催年月日  */
    @Column(name = "date_kaisai")
    private String dateKaisai = INIT_String;

    /**
     * 開催年月日 を取得する
     *
     * @return 開催年月日 
     */
    public String getDateKaisai() {
        return dateKaisai;
    }

    /**
     * 開催年月日 を設定する
     *
     * @param dateKaisai 開催年月日 
     */
    public void setDateKaisai(final String dateKaisai) {
        this.dateKaisai = dateKaisai;
    }

    /** 政治団体名称 */
    @Column(name = "dantai_name01")
    private String dantaiName01 = INIT_String;

    /**
     * 政治団体名称を取得する
     *
     * @return 政治団体名称
     */
    public String getDantaiName01() {
        return dantaiName01;
    }

    /**
     * 政治団体名称を設定する
     *
     * @param dantaiName01 政治団体名称
     */
    public void setDantaiName01(final String dantaiName01) {
        this.dantaiName01 = dantaiName01;
    }

    /** 政治団体名称かな */
    @Column(name = "dantai_name_kana")
    private String dantaiNameKana = INIT_String;

    /**
     * 政治団体名称かなを取得する
     *
     * @return 政治団体名称かな
     */
    public String getDantaiNameKana() {
        return dantaiNameKana;
    }

    /**
     * 政治団体名称かなを設定する
     *
     * @param dantaiNameKana 政治団体名称かな
     */
    public void setDantaiNameKana(final String dantaiNameKana) {
        this.dantaiNameKana = dantaiNameKana;
    }

    /** 事務所の住所 */
    @Column(name = "jimusho_jusho")
    private String jimushoJusho = INIT_String;

    /**
     * 事務所の住所を取得する
     *
     * @return 事務所の住所
     */
    public String getJimushoJusho() {
        return jimushoJusho;
    }

    /**
     * 事務所の住所を設定する
     *
     * @param jimushoJusho 事務所の住所
     */
    public void setJimushoJusho(final String jimushoJusho) {
        this.jimushoJusho = jimushoJusho;
    }

    /** 事務所の住所建物 */
    @Column(name = "jimusho_jusho_tatemono")
    private String jimushoJushoTatemono = INIT_String;

    /**
     * 事務所の住所建物を取得する
     *
     * @return 事務所の住所建物
     */
    public String getJimushoJushoTatemono() {
        return jimushoJushoTatemono;
    }

    /**
     * 事務所の住所建物を設定する
     *
     * @param jimushoJushoTatemono 事務所の住所建物
     */
    public void setJimushoJushoTatemono(final String jimushoJushoTatemono) {
        this.jimushoJushoTatemono = jimushoJushoTatemono;
    }

    /** 代表者の姓 */
    @Column(name = "daihyousha_name_last")
    private String daihyoushaNameLast = INIT_String;

    /**
     * 代表者の姓を取得する
     *
     * @return 代表者の姓
     */
    public String getDaihyoushaNameLast() {
        return daihyoushaNameLast;
    }

    /**
     * 代表者の姓を設定する
     *
     * @param daihyoushaNameLast 代表者の姓
     */
    public void setDaihyoushaNameLast(final String daihyoushaNameLast) {
        this.daihyoushaNameLast = daihyoushaNameLast;
    }

    /** 代表者の名 */
    @Column(name = "daihyousha_name_first")
    private String daihyoushaNameFirst = INIT_String;

    /**
     * 代表者の名を取得する
     *
     * @return 代表者の名
     */
    public String getDaihyoushaNameFirst() {
        return daihyoushaNameFirst;
    }

    /**
     * 代表者の名を設定する
     *
     * @param daihyoushaNameFirst 代表者の名
     */
    public void setDaihyoushaNameFirst(final String daihyoushaNameFirst) {
        this.daihyoushaNameFirst = daihyoushaNameFirst;
    }

    /** 会計責任者の姓 */
    @Column(name = "kaikei_sekinnsha_name_last")
    private String kaikeiSekinnshaNameLast = INIT_String;

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

    /** 会計責任者の名 */
    @Column(name = "kaikei_sekinnsha_name_first")
    private String kaikeiSekinnshaNameFirst = INIT_String;

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

    /** 事務担当者1の姓 */
    @Column(name = "jimu_tantousha1_name_last")
    private String jimuTantousha1NameLast = INIT_String;

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

    /** 事務担当者1の名 */
    @Column(name = "jimu_tantousha1_name_first")
    private String jimuTantousha1NameFirst = INIT_String;

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

    /** 事務担当者1の電話番号 */
    @Column(name = "jimu_tantousha1_tel")
    private String jimuTantousha1Tel = INIT_String;

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

    /** 事務担当者2の姓 */
    @Column(name = "jimu_tantousha2_name_last")
    private String jimuTantousha2NameLast = INIT_String;

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

    /** 事務担当者2の名 */
    @Column(name = "jimu_tantousha2_name_first")
    private String jimuTantousha2NameFirst = INIT_String;

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

    /** 事務担当者2の電話番号 */
    @Column(name = "jimu_tantousha2_tel")
    private String jimuTantousha2Tel = INIT_String;

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

    /** 事務担当者3の姓 */
    @Column(name = "jimu_tantousha3_name_last")
    private String jimuTantousha3NameLast = INIT_String;

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

    /** 事務担当者3の名 */
    @Column(name = "jimu_tantousha3_name_first")
    private String jimuTantousha3NameFirst = INIT_String;

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

    /** 事務担当者3の電話番号 */
    @Column(name = "jimu_tantousha3_tel")
    private String jimuTantousha3Tel = INIT_String;

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

    /** 団体区分  */
    @Column(name = "dantai_kbn")
    private String dantaiKbn = INIT_String;

    /**
     * 団体区分 を取得する
     *
     * @return 団体区分 
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 団体区分 を設定する
     *
     * @param dantaiKbn 団体区分 
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }

    /** 活動区域区分 */
    @Column(name = "katsudou_kuiki_kbn")
    private Integer katsudouKuikiKbn = INIT_Integer;

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

    /** 資金管理団体の有無 */
    @Column(name = "umu_shikin_kanren_dantai")
    private Integer umuShikinKanrenDantai = INIT_Integer;

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

    /** 公職の名称 */
    @Column(name = "koushoku_name")
    private String koushokuName = INIT_String;

    /**
     * 公職の名称を取得する
     *
     * @return 公職の名称
     */
    public String getKoushokuName() {
        return koushokuName;
    }

    /**
     * 公職の名称を設定する
     *
     * @param koushokuName 公職の名称
     */
    public void setKoushokuName(final String koushokuName) {
        this.koushokuName = koushokuName;
    }

    /** 現職候補者の別 */
    @Column(name = "koushoku_gen_kouho")
    private String koushokuGenKouho = INIT_String;

    /**
     * 現職候補者の別を取得する
     *
     * @return 現職候補者の別
     */
    public String getKoushokuGenKouho() {
        return koushokuGenKouho;
    }

    /**
     * 現職候補者の別を設定する
     *
     * @param koushokuGenKouho 現職候補者の別
     */
    public void setKoushokuGenKouho(final String koushokuGenKouho) {
        this.koushokuGenKouho = koushokuGenKouho;
    }

    /** 資金管理団体の設立者の姓 */
    @Column(name = "shikin_daihyou_name1")
    private String shikinDaihyouName1 = INIT_String;

    /**
     * 資金管理団体の設立者の姓を取得する
     *
     * @return 資金管理団体の設立者の姓
     */
    public String getShikinDaihyouName1() {
        return shikinDaihyouName1;
    }

    /**
     * 資金管理団体の設立者の姓を設定する
     *
     * @param shikinDaihyouName1 資金管理団体の設立者の姓
     */
    public void setShikinDaihyouName1(final String shikinDaihyouName1) {
        this.shikinDaihyouName1 = shikinDaihyouName1;
    }

    /** 資金管理団体の設立者の名 */
    @Column(name = "shikin_daihyou_name2")
    private String shikinDaihyouName2 = INIT_String;

    /**
     * 資金管理団体の設立者の名を取得する
     *
     * @return 資金管理団体の設立者の名
     */
    public String getShikinDaihyouName2() {
        return shikinDaihyouName2;
    }

    /**
     * 資金管理団体の設立者の名を設定する
     *
     * @param shikinDaihyouName2 資金管理団体の設立者の名
     */
    public void setShikinDaihyouName2(final String shikinDaihyouName2) {
        this.shikinDaihyouName2 = shikinDaihyouName2;
    }

    /** 資金管理団体の指定期間(開始) */
    @Column(name = "kanri_dantai_period_start")
    private String kanriDantaiPeriodStart = INIT_String;

    /**
     * 資金管理団体の指定期間(開始)を取得する
     *
     * @return 資金管理団体の指定期間(開始)
     */
    public String getKanriDantaiPeriodStart() {
        return kanriDantaiPeriodStart;
    }

    /**
     * 資金管理団体の指定期間(開始)を設定する
     *
     * @param kanriDantaiPeriodStart 資金管理団体の指定期間(開始)
     */
    public void setKanriDantaiPeriodStart(final String kanriDantaiPeriodStart) {
        this.kanriDantaiPeriodStart = kanriDantaiPeriodStart;
    }

    /** 資金管理団体の指定期間(終了)  */
    @Column(name = "kanri_dantai_period_end")
    private String kanriDantaiPeriodEnd = INIT_String;

    /**
     * 資金管理団体の指定期間(終了) を取得する
     *
     * @return 資金管理団体の指定期間(終了) 
     */
    public String getKanriDantaiPeriodEnd() {
        return kanriDantaiPeriodEnd;
    }

    /**
     * 資金管理団体の指定期間(終了) を設定する
     *
     * @param kanriDantaiPeriodEnd 資金管理団体の指定期間(終了) 
     */
    public void setKanriDantaiPeriodEnd(final String kanriDantaiPeriodEnd) {
        this.kanriDantaiPeriodEnd = kanriDantaiPeriodEnd;
    }

    /** 資金管理団体の複数指定期間 */
    @Column(name = "kanri_dantai_period_rest")
    private String kanriDantaiPeriodRest = INIT_String;

    /**
     * 資金管理団体の複数指定期間を取得する
     *
     * @return 資金管理団体の複数指定期間
     */
    public String getKanriDantaiPeriodRest() {
        return kanriDantaiPeriodRest;
    }

    /**
     * 資金管理団体の複数指定期間を設定する
     *
     * @param kanriDantaiPeriodRest 資金管理団体の複数指定期間
     */
    public void setKanriDantaiPeriodRest(final String kanriDantaiPeriodRest) {
        this.kanriDantaiPeriodRest = kanriDantaiPeriodRest;
    }

    /** 国会議員関連団体区分 */
    @Column(name = "kokkai_giin_dantai_kbn")
    private Integer kokkaiGiinDantaiKbn = INIT_Integer;

    /**
     * 国会議員関連団体区分を取得する
     *
     * @return 国会議員関連団体区分
     */
    public Integer getKokkaiGiinDantaiKbn() {
        return kokkaiGiinDantaiKbn;
    }

    /**
     * 国会議員関連団体区分を設定する
     *
     * @param kokkaiGiinDantaiKbn 国会議員関連団体区分
     */
    public void setKokkaiGiinDantaiKbn(final Integer kokkaiGiinDantaiKbn) {
        this.kokkaiGiinDantaiKbn = kokkaiGiinDantaiKbn;
    }

    /** 国家議員1の姓 */
    @Column(name = "kokkai_giin1_name_last")
    private String kokkaiGiin1NameLast = INIT_String;

    /**
     * 国家議員1の姓を取得する
     *
     * @return 国家議員1の姓
     */
    public String getKokkaiGiin1NameLast() {
        return kokkaiGiin1NameLast;
    }

    /**
     * 国家議員1の姓を設定する
     *
     * @param kokkaiGiin1NameLast 国家議員1の姓
     */
    public void setKokkaiGiin1NameLast(final String kokkaiGiin1NameLast) {
        this.kokkaiGiin1NameLast = kokkaiGiin1NameLast;
    }

    /** 国家議員1の名 */
    @Column(name = "kokkai_giin1_name_first")
    private String kokkaiGiin1NameFirst = INIT_String;

    /**
     * 国家議員1の名を取得する
     *
     * @return 国家議員1の名
     */
    public String getKokkaiGiin1NameFirst() {
        return kokkaiGiin1NameFirst;
    }

    /**
     * 国家議員1の名を設定する
     *
     * @param kokkaiGiin1NameFirst 国家議員1の名
     */
    public void setKokkaiGiin1NameFirst(final String kokkaiGiin1NameFirst) {
        this.kokkaiGiin1NameFirst = kokkaiGiin1NameFirst;
    }

    /** 国家議員1の公職(衆参)  */
    @Column(name = "kokkai_giin1_shuu_san")
    private String kokkaiGiin1ShuuSan = INIT_String;

    /**
     * 国家議員1の公職(衆参) を取得する
     *
     * @return 国家議員1の公職(衆参) 
     */
    public String getKokkaiGiin1ShuuSan() {
        return kokkaiGiin1ShuuSan;
    }

    /**
     * 国家議員1の公職(衆参) を設定する
     *
     * @param kokkaiGiin1ShuuSan 国家議員1の公職(衆参) 
     */
    public void setKokkaiGiin1ShuuSan(final String kokkaiGiin1ShuuSan) {
        this.kokkaiGiin1ShuuSan = kokkaiGiin1ShuuSan;
    }

    /** 国家議員1現職と候補者の別 */
    @Column(name = "kokkai_giin1_gen_kouho")
    private String kokkaiGiin1GenKouho = INIT_String;

    /**
     * 国家議員1現職と候補者の別を取得する
     *
     * @return 国家議員1現職と候補者の別
     */
    public String getKokkaiGiin1GenKouho() {
        return kokkaiGiin1GenKouho;
    }

    /**
     * 国家議員1現職と候補者の別を設定する
     *
     * @param kokkaiGiin1GenKouho 国家議員1現職と候補者の別
     */
    public void setKokkaiGiin1GenKouho(final String kokkaiGiin1GenKouho) {
        this.kokkaiGiin1GenKouho = kokkaiGiin1GenKouho;
    }

    /** 国会議員関係団体の特例適用期間(開始) */
    @Column(name = "giin_dantantai_tokurei_period_start")
    private String giinDantantaiTokureiPeriodStart = INIT_String;

    /**
     * 国会議員関係団体の特例適用期間(開始)を取得する
     *
     * @return 国会議員関係団体の特例適用期間(開始)
     */
    public String getGiinDantantaiTokureiPeriodStart() {
        return giinDantantaiTokureiPeriodStart;
    }

    /**
     * 国会議員関係団体の特例適用期間(開始)を設定する
     *
     * @param giinDantantaiTokureiPeriodStart 国会議員関係団体の特例適用期間(開始)
     */
    public void setGiinDantantaiTokureiPeriodStart(final String giinDantantaiTokureiPeriodStart) {
        this.giinDantantaiTokureiPeriodStart = giinDantantaiTokureiPeriodStart;
    }

    /** 国会議員関係団体の特例適用期間(終了) */
    @Column(name = "giin_dantantai_tokurei_period_end")
    private String giinDantantaiTokureiPeriodEnd = INIT_String;

    /**
     * 国会議員関係団体の特例適用期間(終了)を取得する
     *
     * @return 国会議員関係団体の特例適用期間(終了)
     */
    public String getGiinDantantaiTokureiPeriodEnd() {
        return giinDantantaiTokureiPeriodEnd;
    }

    /**
     * 国会議員関係団体の特例適用期間(終了)を設定する
     *
     * @param giinDantantaiTokureiPeriodEnd 国会議員関係団体の特例適用期間(終了)
     */
    public void setGiinDantantaiTokureiPeriodEnd(final String giinDantantaiTokureiPeriodEnd) {
        this.giinDantantaiTokureiPeriodEnd = giinDantantaiTokureiPeriodEnd;
    }

    /** 国会議員関係団体の複数特例適用期間 */
    @Column(name = "giin_dantantai_tokurei_period_rest")
    private String giinDantantaiTokureiPeriodRest = INIT_String;

    /**
     * 国会議員関係団体の複数特例適用期間を取得する
     *
     * @return 国会議員関係団体の複数特例適用期間
     */
    public String getGiinDantantaiTokureiPeriodRest() {
        return giinDantantaiTokureiPeriodRest;
    }

    /**
     * 国会議員関係団体の複数特例適用期間を設定する
     *
     * @param giinDantantaiTokureiPeriodRest 国会議員関係団体の複数特例適用期間
     */
    public void setGiinDantantaiTokureiPeriodRest(final String giinDantantaiTokureiPeriodRest) {
        this.giinDantantaiTokureiPeriodRest = giinDantantaiTokureiPeriodRest;
    }

    /** 国家議員2の姓 */
    @Column(name = "kokkai_giin2_name_last")
    private String kokkaiGiin2NameLast = INIT_String;

    /**
     * 国家議員2の姓を取得する
     *
     * @return 国家議員2の姓
     */
    public String getKokkaiGiin2NameLast() {
        return kokkaiGiin2NameLast;
    }

    /**
     * 国家議員2の姓を設定する
     *
     * @param kokkaiGiin2NameLast 国家議員2の姓
     */
    public void setKokkaiGiin2NameLast(final String kokkaiGiin2NameLast) {
        this.kokkaiGiin2NameLast = kokkaiGiin2NameLast;
    }

    /** 国家議員2の名 */
    @Column(name = "kokkai_giin2_name_first")
    private String kokkaiGiin2NameFirst = INIT_String;

    /**
     * 国家議員2の名を取得する
     *
     * @return 国家議員2の名
     */
    public String getKokkaiGiin2NameFirst() {
        return kokkaiGiin2NameFirst;
    }

    /**
     * 国家議員2の名を設定する
     *
     * @param kokkaiGiin2NameFirst 国家議員2の名
     */
    public void setKokkaiGiin2NameFirst(final String kokkaiGiin2NameFirst) {
        this.kokkaiGiin2NameFirst = kokkaiGiin2NameFirst;
    }

    /** 国家議員2の公職(衆参) */
    @Column(name = "kokkai_giin2_shuu_san")
    private String kokkaiGiin2ShuuSan = INIT_String;

    /**
     * 国家議員2の公職(衆参)を取得する
     *
     * @return 国家議員2の公職(衆参)
     */
    public String getKokkaiGiin2ShuuSan() {
        return kokkaiGiin2ShuuSan;
    }

    /**
     * 国家議員2の公職(衆参)を設定する
     *
     * @param kokkaiGiin2ShuuSan 国家議員2の公職(衆参)
     */
    public void setKokkaiGiin2ShuuSan(final String kokkaiGiin2ShuuSan) {
        this.kokkaiGiin2ShuuSan = kokkaiGiin2ShuuSan;
    }

    /** 国家議員2現職と候補者の別 */
    @Column(name = "kokkai_giin2_gen_kouho")
    private String kokkaiGiin2GenKouho = INIT_String;

    /**
     * 国家議員2現職と候補者の別を取得する
     *
     * @return 国家議員2現職と候補者の別
     */
    public String getKokkaiGiin2GenKouho() {
        return kokkaiGiin2GenKouho;
    }

    /**
     * 国家議員2現職と候補者の別を設定する
     *
     * @param kokkaiGiin2GenKouho 国家議員2現職と候補者の別
     */
    public void setKokkaiGiin2GenKouho(final String kokkaiGiin2GenKouho) {
        this.kokkaiGiin2GenKouho = kokkaiGiin2GenKouho;
    }

    /** 国家議員3の姓 */
    @Column(name = "kokkai_giin3_name_last")
    private String kokkaiGiin3NameLast = INIT_String;

    /**
     * 国家議員3の姓を取得する
     *
     * @return 国家議員3の姓
     */
    public String getKokkaiGiin3NameLast() {
        return kokkaiGiin3NameLast;
    }

    /**
     * 国家議員3の姓を設定する
     *
     * @param kokkaiGiin3NameLast 国家議員3の姓
     */
    public void setKokkaiGiin3NameLast(final String kokkaiGiin3NameLast) {
        this.kokkaiGiin3NameLast = kokkaiGiin3NameLast;
    }

    /** 国家議員3の姓 */
    @Column(name = "kokkai_giin3_name_first")
    private String kokkaiGiin3NameFirst = INIT_String;

    /**
     * 国家議員3の姓を取得する
     *
     * @return 国家議員3の姓
     */
    public String getKokkaiGiin3NameFirst() {
        return kokkaiGiin3NameFirst;
    }

    /**
     * 国家議員3の姓を設定する
     *
     * @param kokkaiGiin3NameFirst 国家議員3の姓
     */
    public void setKokkaiGiin3NameFirst(final String kokkaiGiin3NameFirst) {
        this.kokkaiGiin3NameFirst = kokkaiGiin3NameFirst;
    }

    /** 国家議員3の公職(衆参) */
    @Column(name = "kokkai_giin3_shuu_san")
    private String kokkaiGiin3ShuuSan = INIT_String;

    /**
     * 国家議員3の公職(衆参)を取得する
     *
     * @return 国家議員3の公職(衆参)
     */
    public String getKokkaiGiin3ShuuSan() {
        return kokkaiGiin3ShuuSan;
    }

    /**
     * 国家議員3の公職(衆参)を設定する
     *
     * @param kokkaiGiin3ShuuSan 国家議員3の公職(衆参)
     */
    public void setKokkaiGiin3ShuuSan(final String kokkaiGiin3ShuuSan) {
        this.kokkaiGiin3ShuuSan = kokkaiGiin3ShuuSan;
    }

    /** 国家議員3現職と候補者の別 */
    @Column(name = "kokkai_giin3_gen_kouho")
    private String kokkaiGiin3GenKouho = INIT_String;

    /**
     * 国家議員3現職と候補者の別を取得する
     *
     * @return 国家議員3現職と候補者の別
     */
    public String getKokkaiGiin3GenKouho() {
        return kokkaiGiin3GenKouho;
    }

    /**
     * 国家議員3現職と候補者の別を設定する
     *
     * @param kokkaiGiin3GenKouho 国家議員3現職と候補者の別
     */
    public void setKokkaiGiin3GenKouho(final String kokkaiGiin3GenKouho) {
        this.kokkaiGiin3GenKouho = kokkaiGiin3GenKouho;
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

    /** 政治資金収支報告書準備登録タスクId */
    private Long taskPlanBalancesheetDetailId = INIT_Long;

    /**
     * 政治資金収支報告書準備登録タスクIdを取得する
     *
     * @return 政治資金収支報告書準備登録タスクId
     */
    public Long getTaskPlanBalancesheetDetailId() {
        return taskPlanBalancesheetDetailId;
    }

    /**
     * 政治資金収支報告書準備登録タスクIdを設定する
     *
     * @param taskPlanBalancesheetDetailId 政治資金収支報告書準備登録タスクId
     */
    public void setTaskPlanBalancesheetDetailId(final Long taskPlanBalancesheetDetailId) {
        this.taskPlanBalancesheetDetailId = taskPlanBalancesheetDetailId;
    }

    /** 政治資金収支報告書準備登録タスク定同一識別コード */
    private Long taskPlanBalancesheetDetailCode = INIT_Long;

    /**
     * 政治資金収支報告書準備登録タスク定同一識別コードを取得する
     *
     * @return 政治資金収支報告書準備登録タスク定同一識別コード
     */
    public Long getTaskPlanBalancesheetDetailCode() {
        return taskPlanBalancesheetDetailCode;
    }

    /**
     * 政治資金収支報告書準備登録タスク定同一識別コードを設定する
     *
     * @param taskPlanBalancesheetDetailCode 政治資金収支報告書準備登録タスク定同一識別コード
     */
    public void setTaskPlanBalancesheetDetailCode(final Long taskPlanBalancesheetDetailCode) {
        this.taskPlanBalancesheetDetailCode = taskPlanBalancesheetDetailCode;
    }
    
    
}
