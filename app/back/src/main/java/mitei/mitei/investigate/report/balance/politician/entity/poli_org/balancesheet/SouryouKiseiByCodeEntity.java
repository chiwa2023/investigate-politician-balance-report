package mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 関連者idと関連者コードからをキーにして総額を取得する
 */
@Entity
public class SouryouKiseiByCodeEntity implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    
    /** 合計 */
    @Column(name = "sum")
    private Long sum = INIT_Long;

    /**
     * 合計を取得する
     *
     * @return 合計
     */
    public Long getSum() {
        return sum;
    }

    /**
     * 合計を設定する
     *
     * @param sum 合計
     */
    public void setSum(final Long sum) {
        this.sum = sum;
    }

    /** 関連者Id */
    @Id
    @Column(name = "relation_id")
    private Long relationId = INIT_Long;

    /** 関連者同一識別コード */
    @Id
    @Column(name = "relation_code")
    private Integer relationCode = INIT_Integer;

    /**
     * 関連者Idを取得する
     *
     * @return 関連者Id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 関連者Idを設定する
     *
     * @param relationId 関連者Id
     */
    public void setRelationId(final Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 関連者同一識別コードを取得する
     *
     * @return 関連者同一識別コード
     */
    public Integer getRelationCode() {
        return relationCode;
    }

    /**
     * 関連者同一識別コードを設定する
     *
     * @param relationCode 関連者同一識別コード
     */
    public void setRelationCode(final Integer relationCode) {
        this.relationCode = relationCode;
    }

}
