package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

/**
 * 権限チェック条件Dto TODO 仕様が決定次第修正する
 */
public class CheckPrivilegeDto implements Serializable { // NOPMD DataClass

    /** SerializableId */
    private static final long serialVersionUID = 1L;

    /** 他機能確認用Mock強制例外発生フラグ */
    private Boolean isRaiseExcception;

    /** 他機能確認用Mock判定結果 */
    private Boolean isResult;

    /** ログインユーザId */
    private Long loginUserId;

    /** ログインユーザー同一識別コード */
    private Integer loginUserCode;

    /** ログインユーザー氏名 */
    private String loginUserName;

    /** 政治資金団体Id */
    private Long politicalOrganizationId;

    /** 政治資金団体同一識別コード */
    private Integer politicalOrganizationCode;

    /** 政治資金団体名称 */
    private String politicalOrganizationName;

    /**
     * ログインユーザIdを取得する
     *
     * @return ログインユーザId
     */
    public Long getLoginUserId() {
        return loginUserId;
    }

    /**
     * ログインユーザIdを設定する
     *
     * @param loginUserId ログインユーザId
     */
    public void setLoginUserId(final Long loginUserId) {
        this.loginUserId = loginUserId;
    }

    /**
     * ログイン同一識別コードを取得する
     *
     * @return ログイン同一識別コード
     */
    public Integer getLoginUserCode() {
        return loginUserCode;
    }

    /**
     * ログイン同一識別コードを設定する
     *
     * @param loginUserCode ログイン同一識別コード
     */
    public void setLoginUserCode(final Integer loginUserCode) {
        this.loginUserCode = loginUserCode;
    }

    /**
     * 政治資金団体Idを取得する
     *
     * @return 政治資金団体Id
     */
    public Long getPoliticalOrganizationId() {
        return politicalOrganizationId;
    }

    /**
     * 政治資金団体Idを設定する
     *
     * @param politicalOrganizationId 政治資金団体Id
     */
    public void setPoliticalOrganizationId(final Long politicalOrganizationId) {
        this.politicalOrganizationId = politicalOrganizationId;
    }

    /**
     * 政治資金団体同一識別コードを取得する
     *
     * @return 政治資金団体同一識別コード
     */
    public Integer getPoliticalOrganizationCode() {
        return politicalOrganizationCode;
    }

    /**
     * 政治資金団体同一識別コードを設定する
     *
     * @param politicalOrganizationCode 政治資金団体同一識別コード
     */
    public void setPoliticalOrganizationCode(final Integer politicalOrganizationCode) {
        this.politicalOrganizationCode = politicalOrganizationCode;
    }

    /**
     * 政治資金団体名称を取得する
     *
     * @return 政治資金団体名称
     */
    public String getPoliticalOrganizationName() {
        return politicalOrganizationName;
    }

    /**
     * 政治資金団体名称を設定する
     *
     * @param politicalOrganizationName 政治資金団体名称
     */
    public void setPoliticalOrganizationName(final String politicalOrganizationName) {
        this.politicalOrganizationName = politicalOrganizationName;
    }

    /**
     * 他機能確認用Mock強制例外発生フラグを取得する
     *
     * @return 他機能確認用Mock強制例外発生フラグ
     */
    public Boolean getIsRaiseExcception() {
        return isRaiseExcception;
    }

    /**
     * 他機能確認用Mock強制例外発生フラグを設定する
     *
     * @param isRaiseExcception 他機能確認用Mock強制例外発生フラグ
     */
    public void setIsRaiseExcception(final Boolean isRaiseExcception) {
        this.isRaiseExcception = isRaiseExcception;
    }

    /**
     * 他機能確認用Mock判定結果を取得する
     *
     * @return 他機能確認用Mock判定結果
     */
    public Boolean getIsResult() {
        return isResult;
    }

    /**
     * 他機能確認用Mock判定結果を設定する
     *
     * @param isResult 他機能確認用Mock判定結果
     */
    public void setIsResult(final Boolean isResult) {
        this.isResult = isResult;
    }

    /**
     * ログインユーザ氏名を取得する
     *
     * @return ログインユーザ氏名
     */
    public String getLoginUserName() {
        return loginUserName;
    }

    /**
     * ログインユーザ氏名を設定する
     *
     * @param loginUserName ログインユーザ氏名
     */
    public void setLoginUserName(final String loginUserName) {
        this.loginUserName = loginUserName;
    }

}
