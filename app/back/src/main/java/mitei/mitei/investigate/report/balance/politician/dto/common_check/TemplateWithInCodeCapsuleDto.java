package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * コードだけを伝達するCapsuleDto
 */
public class TemplateWithInCodeCapsuleDto extends AbstractCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 取得コード */
    private Integer code = INIT_Integer;

    /**
     * 取得コードを取得する
     *
     * @return 取得コード
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 取得コードを設定する
     *
     * @param code 取得コード
     */
    public void setCode(final Integer code) {
        this.code = code;
    }

}
