package mitei.mitei.external_api.report.balance.politician.dto.relation;

import java.io.Serializable;

/**
 * 関連者Dto
 */
public class RelationPersonDto implements Serializable { // NOPMD DataClass

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 問い合わせId */
    private String inquireId;

    /** 回答区分 */
    private String nationalityAnswerKbn;

    /** 回答区分表示用 */
    private String nationalityAnswerKbnText;

    /** アプリコード(問い合わせ元アプリを記録する場合) */
    private String applicationComonCode;

    /** アプリ名称(問い合わせ元アプリを記録する場合) */
    private String applicationName;

    /** 関連者(個人)Id(内部) */
    private Long relationPersonId;

    /** 関連者(個人)同一識別コード(内部) */
    private Integer relationPersonCode;

    /** 政治団体Id(内部) */
    private Long politicalOrganizationId;
    /** 政治団体同一識別コード(内部) */
    private Integer politicalOrganizationCode;
    /** 政治団体名称(内部) */
    private String politicalOrganizationName;

    /** 検索対象該否(内部) */
    private Boolean isSearch;

    /** 名前(全) */
    private String nameAll;

    /** 姓名の姓 */
    private String firstName;
    /** 姓名の姓ふりがな */
    private String firstNameKana;
    /** 姓名の名 */
    private String lastName;
    /** 姓名の名かな */
    private String lastNameKana;
    /** ミドルネーム */
    private String middleName;
    /** ミドルネームふりがな */
    private String middleNameKana;

    /** 電話番号市外局番 */
    private String tel1;
    /** 電話番号局番 */
    private String tel2;
    /** 電話番号番号 */
    private String tel3;

    /** 住所(全) */
    private String addressAll;

    /** 元住所(全) */
    private String orginAddressAll;

    /** 郵便番号1 */
    private String postalcode1;
    /** 郵便番号2 */
    private String postalcode2;
    /** 住所郵便番号まで1 */
    private String addressPostal;
    /** 住所番地 */
    private String addressBlock;
    /** 住所建物 */
    private String addressBuilding;

    /** 地方公共団体コード */
    private String lgCode;
    /** 町字Id */
    private String machiazaId;
    /** 街区Id */
    private String blkId;
    /** 住居Id */
    private String rsdtId;

    /** 政治資金分野関連者同一識別コード */
    private String kanrenshaCode;

    /**
     * 問い合わせIdを取得する
     *
     * @return 問い合わせId
     */
    public String getInquireId() {
        return inquireId;
    }

    /**
     * 問い合わせIdを設定する
     *
     * @param inquireId 問い合わせId
     */
    public void setInquireId(final String inquireId) {
        this.inquireId = inquireId;
    }

    /**
     * 回答区分を取得する
     *
     * @return 回答区分
     */
    public String getNationalityAnswerKbn() {
        return nationalityAnswerKbn;
    }

    /**
     * 回答区分を設定する
     *
     * @param nationalityAnswerKbn 回答区分
     */
    public void setNationalityAnswerKbn(final String nationalityAnswerKbn) {
        this.nationalityAnswerKbn = nationalityAnswerKbn;
    }

    /**
     * 回答区分表示用を取得する
     *
     * @return 回答区分表示用
     */
    public String getNationalityAnswerKbnText() {
        return nationalityAnswerKbnText;
    }

    /**
     * 回答区分表示用を設定する
     *
     * @param nationalityAnswerKbnText 回答区分表示用
     */
    public void setNationalityAnswerKbnText(final String nationalityAnswerKbnText) {
        this.nationalityAnswerKbnText = nationalityAnswerKbnText;
    }

    /**
     * アプリコード(問い合わせ元アプリを記録する場合) を取得する
     *
     * @return アプリコード(問い合わせ元アプリを記録する場合)
     */
    public String getApplicationComonCode() {
        return applicationComonCode;
    }

    /**
     * アプリコード(問い合わせ元アプリを記録する場合)を設定する
     *
     * @param applicationComonCode アプリコード(問い合わせ元アプリを記録する場合)
     */
    public void setApplicationComonCode(final String applicationComonCode) {
        this.applicationComonCode = applicationComonCode;
    }

    /**
     * アプリ名称(問い合わせ元アプリを記録する場合)を取得する
     *
     * @return アプリ名称(問い合わせ元アプリを記録する場合)
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * アプリ名称(問い合わせ元アプリを記録する場合)を設定する
     *
     * @param applicationName アプリ名称(問い合わせ元アプリを記録する場合)
     */
    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * 関連者(個人)Id(内部)を取得する
     *
     * @return 関連者(個人)Id(内部)
     */
    public Long getRelationPersonId() {
        return relationPersonId;
    }

    /**
     * 関連者(個人)Id(内部)を設定する
     *
     * @param relationPersonId 関連者(個人)Id(内部)
     */
    public void setRelationPersonId(final Long relationPersonId) {
        this.relationPersonId = relationPersonId;
    }

    /**
     * 関連者(個人)同一識別コード(内部)を取得する
     *
     * @return 関連者(個人)同一識別コード(内部)
     */
    public Integer getRelationPersonCode() {
        return relationPersonCode;
    }

    /**
     * 関連者(個人)同一識別コード(内部)を設定する
     *
     * @param relationPersonCode 関連者(個人)同一識別コード(内部)
     */
    public void setRelationPersonCode(final Integer relationPersonCode) {
        this.relationPersonCode = relationPersonCode;
    }

    /**
     * 政治団体Id(内部)を取得する
     *
     * @return 政治団体Id(内部)
     */
    public Long getPoliticalOrganizationId() {
        return politicalOrganizationId;
    }

    /**
     * 政治団体Id(内部)を設定する
     *
     * @param politicalOrganizationId 政治団体Id(内部)
     */
    public void setPoliticalOrganizationId(final Long politicalOrganizationId) {
        this.politicalOrganizationId = politicalOrganizationId;
    }

    /**
     * 政治団体同一識別コード(内部)を取得する
     *
     * @return 政治団体同一識別コード(内部)
     */
    public Integer getPoliticalOrganizationCode() {
        return politicalOrganizationCode;
    }

    /**
     * 政治団体同一識別コード(内部)を設定する
     *
     * @param politicalOrganizationCode 政治団体同一識別コード(内部)
     */
    public void setPoliticalOrganizationCode(final Integer politicalOrganizationCode) {
        this.politicalOrganizationCode = politicalOrganizationCode;
    }

    /**
     * 政治団体名称(内部)を取得する
     *
     * @return 政治団体名称(内部)
     */
    public String getPoliticalOrganizationName() {
        return politicalOrganizationName;
    }

    /**
     * 政治団体名称(内部)を設定する
     *
     * @param politicalOrganizationName 政治団体名称(内部)
     */
    public void setPoliticalOrganizationName(final String politicalOrganizationName) {
        this.politicalOrganizationName = politicalOrganizationName;
    }

    /**
     * 検索対象該否(内部)を取得する
     *
     * @return 検索対象該否(内部)
     */
    public Boolean getIsSearch() {
        return isSearch;
    }

    /**
     * 検索対象該否(内部)を設定する
     *
     * @param isSearch 検索対象該否(内部)
     */
    public void setIsSearch(final Boolean isSearch) {
        this.isSearch = isSearch;
    }

    /**
     * 名前(全)を取得する
     *
     * @return 名前(全)
     */
    public String getNameAll() {
        return nameAll;
    }

    /**
     * 名前(全)を設定する
     *
     * @param nameAll 名前(全)
     */
    public void setNameAll(final String nameAll) {
        this.nameAll = nameAll;
    }

    /**
     * 姓名の姓を取得する
     *
     * @return 姓名の姓
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 姓名の姓を設定する
     *
     * @param firstName 姓名の姓
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * 姓名の姓ふりがなを取得する
     *
     * @return 姓名の姓ふりがな
     */
    public String getFirstNameKana() {
        return firstNameKana;
    }

    /**
     * 姓名の姓ふりがなを設定する
     *
     * @param firstNameKana 姓名の姓ふりがな
     */
    public void setFirstNameKana(final String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

    /**
     * 姓名の名を取得する
     *
     * @return 姓名の名
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 姓名の名を設定する
     *
     * @param lastName 姓名の名
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * 姓名の名かなを取得する
     *
     * @return 姓名の名かな
     */
    public String getLastNameKana() {
        return lastNameKana;
    }

    /**
     * 姓名の名かなを設定する
     *
     * @param lastNameKana 姓名の名かな
     */
    public void setLastNameKana(final String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    /**
     * ミドルネームを取得する
     *
     * @return ミドルネーム
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * ミドルネームを設定する
     *
     * @param middleName ミドルネーム
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * ミドルネームふりがなを取得する
     *
     * @return ミドルネームふりがな
     */
    public String getMiddleNameKana() {
        return middleNameKana;
    }

    /**
     * ミドルネームふりがなを設定する
     *
     * @param middleNameKana ミドルネームふりがな
     */
    public void setMiddleNameKana(final String middleNameKana) {
        this.middleNameKana = middleNameKana;
    }

    /**
     * 電話番号市外局番を取得する
     *
     * @return 電話番号市外局番
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * 電話番号市外局番を設定する
     *
     * @param tel1 電話番号市外局番
     */
    public void setTel1(final String tel1) {
        this.tel1 = tel1;
    }

    /**
     * 電話番号局番を取得する
     *
     * @return 電話番号局番
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * 電話番号局番を設定する
     *
     * @param tel2 電話番号局番
     */
    public void setTel2(final String tel2) {
        this.tel2 = tel2;
    }

    /**
     * 電話番号番号を取得する
     *
     * @return 電話番号番号
     */
    public String getTel3() {
        return tel3;
    }

    /**
     * 電話番号番号を設定する
     *
     * @param tel3 電話番号番号
     */
    public void setTel3(final String tel3) {
        this.tel3 = tel3;
    }

    /**
     * 住所(全)を取得する
     *
     * @return 住所(全)
     */
    public String getAddressAll() {
        return addressAll;
    }

    /**
     * 住所(全)を設定する
     *
     * @param addressAll 住所(全)
     */
    public void setAddressAll(final String addressAll) {
        this.addressAll = addressAll;
    }

    /**
     * 元住所(全)を取得する
     *
     * @return 元住所(全)
     */
    public String getOrginAddressAll() {
        return orginAddressAll;
    }

    /**
     * 元住所(全)を設定する
     *
     * @param orginAddressAll 元住所(全)
     */
    public void setOrginAddressAll(final String orginAddressAll) {
        this.orginAddressAll = orginAddressAll;
    }

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostalcode1() {
        return postalcode1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postalcode1 郵便番号1
     */
    public void setPostalcode1(final String postalcode1) {
        this.postalcode1 = postalcode1;
    }

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostalcode2() {
        return postalcode2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postalcode2 郵便番号2
     */
    public void setPostalcode2(final String postalcode2) {
        this.postalcode2 = postalcode2;
    }

    /**
     * 住所郵便番号までを取得する
     *
     * @return 住所郵便番号まで
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所郵便番号までを設定する
     *
     * @param addressPostal 住所郵便番号まで
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /**
     * 住所番地を取得する
     *
     * @return 住所番地
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地を設定する
     *
     * @param addressBlock 住所番地
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /**
     * 住所建物を取得する
     *
     * @return 住所建物
     */
    public String getAddressBuilding() {
        return addressBuilding;
    }

    /**
     * 住所建物を設定する
     *
     * @param addressBuilding 住所建物
     */
    public void setAddressBuilding(final String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    /**
     * 地方公共団体コードを取得する
     *
     * @return 地方公共団体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方公共団体コードを設定する
     *
     * @param lgCode 地方公共団体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /**
     * 町字Idを取得する
     *
     * @return 町字Id
     */
    public String getMachiazaId() {
        return machiazaId;
    }

    /**
     * 町字Idを設定する
     *
     * @param machiazaId 町字Id
     */
    public void setMachiazaId(final String machiazaId) {
        this.machiazaId = machiazaId;
    }

    /**
     * 街区Idを取得する
     *
     * @return 街区Id
     */
    public String getBlkId() {
        return blkId;
    }

    /**
     * 街区Idを設定する
     *
     * @param blkId 街区Id
     */
    public void setBlkId(final String blkId) {
        this.blkId = blkId;
    }

    /**
     * 住居Idを取得する
     *
     * @return 住居Id
     */
    public String getRsdtId() {
        return rsdtId;
    }

    /**
     * 住居Idを設定する
     *
     * @param rsdtId 住居Id
     */
    public void setRsdtId(final String rsdtId) {
        this.rsdtId = rsdtId;
    }

    /**
     * 政治資金分野関連者同一識別コードを取得する
     *
     * @return 政治資金分野関連者同一識別コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 政治資金分野関連者同一識別コードを設定する
     *
     * @param kanrenshaCode 政治資金分野関連者同一識別コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

}
