package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * 迂回献金経路検索結果Dto(ページング)
 */
public class UkaiKenkinPickupRouteResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 表示件数 */
    private Integer countAll;

    /** 件数上限 */
    private Integer limit;

    /** 検索位置 */
    private Integer pageNumber;

    /** 抽出リスト(初階層) */
    private List<WkTblUkaiKenkinPickupRouteEntity> listFirstRouteEntity = new ArrayList<>();

    /** 抽出リスト(最終階層) */
    private List<WkTblUkaiKenkinPickupRouteEntity> listLastRouteEntity = new ArrayList<>();

    /** 抽出ルートリスト(中間ルート候補) */
    private List<WkTblUkaiKenkinPickupRouteEntity> listPickupRouteEntity = new ArrayList<>();

    /**
     * 表示件数
     *
     * @return 表示件数
     */
    public Integer getCountAll() {
        return countAll;
    }

    /**
     * 表示件数
     *
     * @param countAll 表示件数
     */
    public void setCountAll(final Integer countAll) {
        this.countAll = countAll;
    }

    /**
     * 件数上限
     *
     * @return 件数上限
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * 件数上限
     *
     * @param limit 件数上限
     */
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * 検索位置
     *
     * @return 検索位置
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * 検索位置
     *
     * @param pageNumber 検索位置
     */
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 抽出リスト(初階層)
     *
     * @return 抽出リスト(初階層)
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> getListFirstRouteEntity() {
        return listFirstRouteEntity;
    }

    /**
     * 抽出リスト(初階層)
     *
     * @param listFirstRouteEntity 抽出リスト(初階層)
     */
    public void setListFirstRouteEntity(final List<WkTblUkaiKenkinPickupRouteEntity> listFirstRouteEntity) {
        this.listFirstRouteEntity = listFirstRouteEntity;
    }

    /**
     * 抽出リスト(最終階層)
     *
     * @return 抽出リスト(最終階層)
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> getListLastRouteEntity() {
        return listLastRouteEntity;
    }

    /**
     * 抽出リスト(最終階層)
     *
     * @param listLastRouteEntity 抽出リスト(最終階層)
     */
    public void setListLastRouteEntity(final List<WkTblUkaiKenkinPickupRouteEntity> listLastRouteEntity) {
        this.listLastRouteEntity = listLastRouteEntity;
    }

    /**
     * 抽出ルートリスト(中間ルート候補)
     *
     * @return 抽出ルートリスト(中間ルート候補)
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> getListPickupRouteEntity() {
        return listPickupRouteEntity;
    }

    /**
     * 抽出ルートリスト(中間ルート候補)
     *
     * @param listPickupRouteEntity 抽出ルートリスト(中間ルート候補)
     */
    public void setListPickupRouteEntity(final List<WkTblUkaiKenkinPickupRouteEntity> listPickupRouteEntity) {
        this.listPickupRouteEntity = listPickupRouteEntity;
    }
}
