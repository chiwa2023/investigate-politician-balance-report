package mitei.mitei.investigate.report.balance.politician.dto;

import java.io.Serializable;

/**
 * HTMLにおけるSelectボタンのOption項目を表すDto.
 */
public class SelectOptionDto implements Serializable { // NOPMD DataClass

    /** Serialize id. */
    private static final long serialVersionUID = 1L;

    /** option項目のvalue(値). */
    private String value;

    /** option項目の表示テキスト. */
    private String text;

    /**
     * オプション項目の値を取得する.
     *
     * @return オプション項目の値
     */
    public String getValue() {
        return value;
    }

    /**
     * オプション項目の値を設定する.
     *
     * @param value オプション項目の値
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * オプション項目のテキストを取得する.
     *
     * @return オプション項目のテキスト
     */
    public String getText() {
        return text;
    }

    /**
     * オプション項目のテキストを設定する.
     *
     * @param text オプション項目のテキスト
     */
    public void setText(final String text) {
        this.text = text;
    }

}
