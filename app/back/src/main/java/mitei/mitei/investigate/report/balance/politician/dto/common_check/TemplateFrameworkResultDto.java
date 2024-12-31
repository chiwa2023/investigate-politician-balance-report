package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * 最低限の結果を返却するDto
 */
public class TemplateFrameworkResultDto extends AbstractResultDto implements Serializable{

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

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
