package mitei.mitei.investigate.report.balance.politician.dto.storage;

import java.io.Serializable;

/**
 * 1件の保存済書証Dto
 */
public class SaveStorageResultDto implements Serializable{ // NOPMD DataClass

    /** Serial Id */
    private static final long serialVersionUID = 1L;

    /** 書証Id */
    private String shoshouId;

    /** 書証同一識別コード */
    private Long shoshouCode;

    /** 書証区分 */
    private Integer shoshouKbn;

    /**
     * 書証Idを取得する
     *
     * @return 書証Id
     */
    public String getShoshouId() {
        return shoshouId;
    }

    /**
     * 書証Idを設定する
     *
     * @param shoshouId 書証Id
     */
    public void setShoshouId(final String shoshouId) {
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
   
}
