package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 迂回献金の明細からルート(政治団体遷移)を抽出する
 */
@Entity
public class RouteUkaiKenkinStage06Dto implements Serializable {

    /** SerializableId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 階層0政治団体コード */
    @Id
    @Column(name = "code00")
    private Integer code00 = INIT_Integer;

    /** 階層1政治団体コード */
    @Column(name = "code01")
    private Integer code01 = INIT_Integer;

    /** 階層2政治団体コード */
    @Column(name = "code02")
    private Integer code02 = INIT_Integer;

    /** 階層3政治団体コード */
    @Column(name = "code03")
    private Integer code03 = INIT_Integer;

    /** 階層4政治団体コード */
    @Column(name = "code04")
    private Integer code04 = INIT_Integer;

    /** 階層5政治団体コード */
    @Column(name = "code05")
    private Integer code05 = INIT_Integer;

    /** 階層6政治団体コード */
    @Column(name = "code06")
    private Integer code06 = INIT_Integer;

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

    /**
     * 階層2政治団体コードを取得する
     *
     * @return 階層2政治団体コード
     */
    public Integer getCode02() {
        return code02;
    }

    /**
     * 階層3政治団体コードを設定する
     *
     * @param code02 階層3政治団体コード
     */
    public void setCode02(final Integer code02) {
        this.code02 = code02;
    }

    /**
     * 階層3政治団体コードを取得する
     *
     * @return 階層3政治団体コード
     */
    public Integer getCode03() {
        return code03;
    }

    /**
     * 階層3政治団体コードを設定する
     *
     * @param code03 階層3政治団体コード
     */
    public void setCode03(final Integer code03) {
        this.code03 = code03;
    }

    /**
     * 階層4政治団体コードを取得する
     *
     * @return 階層4政治団体コード
     */
    public Integer getCode04() {
        return code04;
    }

    /**
     * 階層5政治団体コードを設定する
     *
     * @param code04 階層5政治団体コード
     */
    public void setCode04(final Integer code04) {
        this.code04 = code04;
    }

    /**
     * 階層5政治団体コードを取得する
     *
     * @return 階層5政治団体コード
     */
    public Integer getCode05() {
        return code05;
    }

    /**
     * 階層5政治団体コードを設定する
     *
     * @param code05 階層5政治団体コード
     */
    public void setCode05(final Integer code05) {
        this.code05 = code05;
    }

    /**
     * 階層6政治団体コードを取得する
     *
     * @return 階層6政治団体コード
     */
    public Integer getCode06() {
        return code06;
    }

    /**
     * 階層6政治団体コードを設定する
     *
     * @param code06 階層6政治団体コード
     */
    public void setCode06(final Integer code06) {
        this.code06 = code06;
    }
}
