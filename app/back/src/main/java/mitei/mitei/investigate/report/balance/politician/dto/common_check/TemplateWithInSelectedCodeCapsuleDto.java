package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * コードだけを伝達するCapsuleDto
 */
public class TemplateWithInSelectedCodeCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 選択コード */
    private String codeSelected = INIT_String;

    /**
     * 選択コードを取得する
     *
     * @return 選択コード
     */
    public String getCodeSelected() {
        return codeSelected;
    }

    /**
     * 選択コードを設定する
     *
     * @param codeSelected 選択コード
     */
    public void setCodeSelected(final String codeSelected) {
        this.codeSelected = codeSelected;
    }

}
