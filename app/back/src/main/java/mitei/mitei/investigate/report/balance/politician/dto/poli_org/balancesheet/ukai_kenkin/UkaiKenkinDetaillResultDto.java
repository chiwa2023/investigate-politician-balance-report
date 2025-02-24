package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;

/**
 * 迂回献金明細検索結果ResultDto
 */
public class UkaiKenkinDetaillResultDto extends AbstractResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 表示件数 */
    private int countAll = INIT_Integer;

    /** 件数上限 */
    private int limit = INIT_Integer;

    /** 検索位置 */
    private int offset = INIT_Integer;

    /** 検索明細リスト */
    private List<WkTblUkaiKenkinEntity> listDetailEntity = new ArrayList<>();

    /**
     * 表示件数を取得する
     *
     * @return 表示件数
     */
    public int getCountAll() {
        return countAll;
    }

    /**
     * 表示件数を設定する
     *
     * @param countAll 表示件数
     */
    public void setCountAll(final int countAll) {
        this.countAll = countAll;
    }

    /**
     * 件数上限を取得する
     *
     * @return 件数上限
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 件数上限を設定する
     *
     * @param limit 件数上限
     */
    public void setLimit(final int limit) {
        this.limit = limit;
    }

    /**
     * 検索位置を取得する
     *
     * @return 検索位置
     */
    public int getOffset() {
        return offset;
    }

    /**
     * 検索位置を設定する
     *
     * @param offset 検索位置
     */
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    /**
     * 検索明細リストを取得する
     *
     * @return 検索明細リスト
     */
    public List<WkTblUkaiKenkinEntity> getListDetailEntity() {
        return listDetailEntity;
    }

    /**
     * 検索明細リストを設定する
     *
     * @param listDetailEntity 検索明細リスト
     */
    public void setListDetailEntity(final List<WkTblUkaiKenkinEntity> listDetailEntity) {
        this.listDetailEntity = listDetailEntity;
    }

    /** 表示メッセージ */
    private String message = INIT_String;

    /**
     * 表示メッセージを取得する
     *
     * @return 表示メッセージ
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 表示メッセージを設定する
     *
     * @param message 表示メッセージ
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

}
