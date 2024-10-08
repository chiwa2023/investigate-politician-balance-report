package mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0805Report2025Entity;

/**
 * offering_party_usage_0805_report_2025接続用Repository
 */
public interface OfferingPartyUsage0805Report2025Repository
        extends JpaRepository<OfferingPartyUsage0805Report2025Entity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_party_usage_0805_report_2025 WHERE saishin_kbn= 1 AND MATCH(offering_party_usage_0805_report_2025_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<OfferingPartyUsage0805Report2025Entity> findFullText(String searchWords);

    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingPartyUsage0805Report2025Entity> findFirstByOrderByPartyUsage0805ReportCodeDesc();

    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param documentCode 文書同一識別コード
     * @return データリスト(Id順)
     */
    List<OfferingPartyUsage0805Report2025Entity> findByDocumentCodeOrderByPartyUsage0805ReportId(Long documentCode);

}
