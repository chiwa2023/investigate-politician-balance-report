package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * 迂回献金の明細からルート(政治団体遷移)を抽出する
 */
public class RouteUkaiKenkinStage01Dto implements Serializable {

    /** SerializableId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 階層0政治団体コード */
    @Column(name = "code00")
    private Integer code00 = INIT_Integer;

    /** 階層1政治団体コード */
    @Column(name = "code01")
    private Integer code01 = INIT_Integer;

    /**
     * 階層0政治団体コードを取得する
     *
     * @return 階層0政治団体コード
     */
    public Integer getCode00() {
        return code00;
    }

    /**
     * 階層0政治団体コードを設定する
     *
     * @param code00 階層0政治団体コード
     */
    public void setCode00(final Integer code00) {
        this.code00 = code00;
    }

    /**
     * 階層1政治団体コードを取得する
     *
     * @return 階層1政治団体コード
     */
    public Integer getCode01() {
        return code01;
    }

    /**
     * 階層1政治団体コードを設定する
     *
     * @param code01 階層1政治団体コード
     */
    public void setCode01(final Integer code01) {
        this.code01 = code01;
    }

}
