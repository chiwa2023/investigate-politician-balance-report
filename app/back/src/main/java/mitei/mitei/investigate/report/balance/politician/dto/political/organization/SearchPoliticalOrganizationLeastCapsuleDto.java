package mitei.mitei.investigate.report.balance.politician.dto.political.organization;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 政治団体検索検索条件Dto
 */
public class SearchPoliticalOrganizationLeastCapsuleDto extends AbstractCapsuleDto implements Serializable { // NOPMD

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 検索語 */
    private String searchWords = INIT_String;

    /**
     * 検索語を取得する
     *
     * @return 検索語
     */
    public String getSearchWords() {
        return searchWords;
    }

    /**
     * 検索語を設定する
     *
     * @param searchWords 検索語
     */
    public void setSearchWords(final String searchWords) {
        this.searchWords = searchWords;
    }

    /** 結果に履歴データも含める該否 */
    private Boolean isHisory = INIT_Boolean;

    /**
     * 結果に履歴データも含める該否を取得する
     *
     * @return 結果に履歴データも含める該否
     */
    public Boolean getIsHisory() {
        return isHisory;
    }

    /**
     * 結果に履歴データも含める該否を設定する
     *
     * @param isHisory 結果に履歴データも含める該否
     */
    public void setIsHisory(final Boolean isHisory) {
        this.isHisory = isHisory;
    }


}
