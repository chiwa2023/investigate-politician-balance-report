package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;

/**
 * 政治資金収支報告書公式ソフトウェアXMLを登録した際の結果Dto
 */
public class RegistPoliticalOrgBalancesheetReportResultDto extends AbstractResultDto implements Serializable { // NOPMD

    /** SerializableId */
    private static final long serialVersionUID = 1L;

    /** 登録時の文書同一識別コード */
    private Long documentCode;

    /** 表示メッセージ */
    private String message = "[実装必要]";

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

    /**
     * 登録文書同一識別コードを取得する
     *
     * @return 登録文書同一識別コード
     */
    public Long getDocumentCode() {
        return documentCode;
    }

    /**
     * 登録文書同一識別コードを設定する
     *
     * @param documentCode 登録文書同一識別コード
     */
    public void setDocumentCode(final Long documentCode) {
        this.documentCode = documentCode;
    }

}
