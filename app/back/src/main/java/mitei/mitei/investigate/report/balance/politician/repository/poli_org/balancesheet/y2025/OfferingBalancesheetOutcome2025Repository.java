package mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetOutcome2025Entity;

/**
 * offering_balancesheet_outcome_2025接続用Repository
 */
public interface OfferingBalancesheetOutcome2025Repository  extends JpaRepository<OfferingBalancesheetOutcome2025Entity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_outcome_2025 WHERE saishin_kbn= 1 AND MATCH(search_words) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<OfferingBalancesheetOutcome2025Entity> findFullText(String searchWords);

    
    
    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingBalancesheetOutcome2025Entity> findFirstByOrderByOfferingBalancesheetOutcomeCodeDesc();
    
    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param documentCode 文書同一識別コード
     * @return データリスト()
     */
    List<OfferingBalancesheetOutcome2025Entity> findByDocumentCodeOrderByOfferingBalancesheetOutcomeId(Long documentCode);
    

}
