package mitei.mitei.investigate.report.balance.politician.repository.mail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.mail.SendAlertMail2022Entity;

/**
 * send_alert_mail_2022接続用Repository
 */
public interface SendAlertMail2022Repository extends JpaRepository<SendAlertMail2022Entity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM send_alert_mail_2022 WHERE saishin_kbn= 1 AND MATCH(send_alert_mail_2022_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<SendAlertMail2022Entity> findFullText(String searchWords);

    /**
     * メール未送信かつ、送信時間が比較対象より前のデータを検索する
     *
     * @param saishinKbn   最新区分1
     * @param sendDatetime 現在時間
     * @return 未送信データリスト
     */
    List<SendAlertMail2022Entity> findBySaishinKbnAndSendDatetimeBeforeOrderBySendAlertMailId(Integer saishinKbn,
            LocalDateTime sendDatetime);

    /**
     * 最新区分と同一識別コードからリストを取得する
     *
     * @param saishinKbn        最新区分
     * @param sendAlertMailCode メール送信予定同一識別コード
     * @return メール送信予定Entityリスト
     */
    List<SendAlertMail2022Entity> findBySaishinKbnAndSendAlertMailCode(Integer saishinKbn, Integer sendAlertMailCode);

    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SendAlertMail2022Entity> findFirstByOrderBySendAlertMailCodeDesc();

}
