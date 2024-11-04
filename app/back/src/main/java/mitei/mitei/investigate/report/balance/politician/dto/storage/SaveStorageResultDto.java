package mitei.mitei.investigate.report.balance.politician.dto.storage;

import java.io.Serializable;

/**
 * 1件の保存済書証Dto
 */
public class SaveStorageResultDto implements Serializable { // NOPMD DataClass

    /** Serial Id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 書証Id */
    private Long shoshouId;

    /** 書証同一識別コード */
    private Long shoshouCode;

    /** 書証区分 */
    private Integer shoshouKbn;

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

    /** fillPath */
    private String fullPath;

    /**
     * 格納フルパスを取得する
     *
     * @return 格納フルパス
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * 格納フルパスを設定する
     *
     * @param fullPath 格納フルパス
     */
    public void setFullPath(final String fullPath) {
        this.fullPath = fullPath;
    }

    /** 保存子フォルダ */
    private String childDir = INIT_String;

    /** ファイル名 */
    private String fileName = INIT_String;

    /** 登録時間 */
    private String registTimeText = INIT_String;

    /**
     * 保存子フォルダ取得する
     *
     * @return 保存子フォルダ
     */
    public String getChildDir() {
        return childDir;
    }

    /**
     * 保存子フォルダ設定する
     *
     * @param childDir 保存子フォルダ
     */
    public void setChildDir(final String childDir) {
        this.childDir = childDir;
    }

    /**
     * ファイル名取得する
     *
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ファイル名設定する
     *
     * @param fileName ファイル名
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * 登録時間取得する
     *
     * @return 登録時間
     */
    public String getRegistTimeText() {
        return registTimeText;
    }

    /**
     * 登録時間設定する
     *
     * @param registTimeText 登録時間
     */
    public void setRegistTimeText(final String registTimeText) {
        this.registTimeText = registTimeText;
    }

    /** 読み取り文字コード */
    private String charset = INIT_String;

    /**
     * 読み取り文字コードを取得
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
}
