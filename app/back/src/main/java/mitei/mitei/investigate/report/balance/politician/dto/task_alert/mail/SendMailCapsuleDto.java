package mitei.mitei.investigate.report.balance.politician.dto.task_alert.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * メール送信に必要な情報を格納するDto
 */
public class SendMailCapsuleDto extends AbstractCapsuleDto implements Serializable { //NOPMD DataClass

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 送信メール情報リスト */
    private List<MailDataDto> listMailData = new ArrayList<>();

    /** テスト用パラメータ。テストを失敗させる位置(人目) TODO テストが終了したら外す */
    private List<Integer> posFailure = new ArrayList<>();

    /**
     * テスト失敗位置を取得する
     *
     * @return テスト失敗位置
     */
    public List<Integer> getPosFailure() {
        return posFailure;
    }

    /**
     * テスト失敗位置を設定する
     *
     * @param posFailure テスト失敗位置
     */
    public void setPosFailure(final List<Integer> posFailure) {
        this.posFailure = posFailure;
    }

    /**
     * 送信メール情報リストを取得する
     *
     * @return 送信メール情報リスト
     */
    public List<MailDataDto> getListMailData() {
        return listMailData;
    }

    /**
     * 送信メール情報リストを設定する
     *
     * @param listMailData 送信メール情報リスト
     */
    public void setListMailData(final List<MailDataDto> listMailData) {
        this.listMailData = listMailData;
    }

}
