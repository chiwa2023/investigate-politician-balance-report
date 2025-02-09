package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin;

import java.io.Serializable;

/**
 * 役割付き関連者一次保存Dto
 */
public class RelationPersonWithYakuwariDto implements Serializable { // NOPMD DataCalss

    /** SerializableId */
    private static final long serialVersionUID = 1L;

    /** 関連者Id */
    private Long Id; // NOPMD

    /** 関連者Code */
    private Integer code;

    /** 関連者姓名 */
    private String name;

    /** 関連者団体内役割 */
    private String yakuwari;

    /**
     * 関連者Id
     *
     * @return 関連者Id
     */
    public Long getId() {
        return Id;
    }

    /**
     * 関連者Id
     *
     * @param id 関連者Id
     */
    public void setId(final Long id) { // NOPMD
        Id = id;
    }

    /**
     * 関連者同一識別コード
     *
     * @return 関連者同一識別コード
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 関連者同一識別コード
     *
     * @param code 関連者同一識別コード
     */
    public void setCode(final Integer code) {
        this.code = code;
    }

    /**
     * 関連者氏名
     *
     * @return 関連者氏名
     */
    public String getName() {
        return name;
    }

    /**
     * 関連者氏名
     *
     * @param name 関連者氏名
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 関連者役割
     *
     * @return 関連者役割
     */
    public String getYakuwari() {
        return yakuwari;
    }

    /**
     * 関連者役割
     *
     * @param yakuwari 関連者役割
     */
    public void setYakuwari(final String yakuwari) {
        this.yakuwari = yakuwari;
    }

}
