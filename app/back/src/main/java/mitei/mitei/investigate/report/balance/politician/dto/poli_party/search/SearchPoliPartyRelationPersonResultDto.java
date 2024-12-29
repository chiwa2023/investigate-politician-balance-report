package mitei.mitei.investigate.report.balance.politician.dto.poli_party.search;

import java.util.List;

/**
 * 政党と関連者紐づけテーブルからページ情報も含めた検索結果格納Dto
 */
public class SearchPoliPartyRelationPersonResultDto { // NOPMD DataClass

    /** 検索結果全件 */
    private long countAll;

    /** 検索結果を取得したページ数 */
    private long posPage;

    /** 検索結果 */
    private List<Integer> listPerson;

    /**
     * 検索結果全件を取得する
     *
     * @return 検索結果全件
     */
    public long getCountAll() {
        return countAll;
    }

    /**
     * 検索結果全件を設定する
     *
     * @param countAll 検索結果全件
     */
    public void setCountAll(final long countAll) {
        this.countAll = countAll;
    }

    /**
     * 検索結果を取得したページ数を取得する
     *
     * @return 検索結果を取得したページ数
     */
    public long getPosPage() {
        return posPage;
    }

    /**
     * 検索結果を取得したページ数を設定する
     *
     * @param posPage 検索結果を取得したページ数
     */
    public void setPosPage(final long posPage) {
        this.posPage = posPage;
    }

    /**
     * 検索結果を取得する
     *
     * @return 検索結果
     */
    public List<Integer> getListPerson() {
        return listPerson;
    }

    /**
     * 検索結果を設定する
     *
     * @param listPerson 検索結果
     */
    public void setListPerson(final List<Integer> listPerson) {
        this.listPerson = listPerson;
    }

}
