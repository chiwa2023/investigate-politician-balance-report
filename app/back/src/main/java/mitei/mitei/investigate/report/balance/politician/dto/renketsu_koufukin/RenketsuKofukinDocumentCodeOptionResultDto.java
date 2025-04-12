package mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin;

import java.io.Serializable;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;

/**
 * 収支報告書・使途報告書文書コードリスト検索結果格納Dto
 */
public class RenketsuKofukinDocumentCodeOptionResultDto extends AbstractResultDto // NOPMD DataClass
        implements Serializable {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 使途報告書文書コード選択肢リスト */
    private List<SelectOptionDto> listUsage;

    /** 使途報告書文書コード選択肢リスト */
    private List<SelectOptionDto> listBalance;

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
     * 使途報告書文書コード選択肢リストを取得する
     *
     * @return 使途報告書文書コード選択肢リスト
     */
    public List<SelectOptionDto> getListUsage() {
        return listUsage;
    }

    /**
     * 使途報告書文書コード選択肢リストを設定する
     *
     * @param listUsage 使途報告書文書コード選択肢リスト
     */
    public void setListUsage(final List<SelectOptionDto> listUsage) {
        this.listUsage = listUsage;
    }

    /**
     * 使途報告書文書コード選択肢リスト
     *
     * @return 使途報告書文書コード選択肢リストを取得する
     */
    public List<SelectOptionDto> getListBalance() {
        return listBalance;
    }

    /**
     * 使途報告書文書コード選択肢リストを設定する
     *
     * @param listBalance 使途報告書文書コード選択肢リスト
     */
    public void setListBalance(final List<SelectOptionDto> listBalance) {
        this.listBalance = listBalance;
    }

}
