package mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetOutcome2024Entity;

/**
 * offering_balancesheet_outcome_2024接続用Repository
 */
public interface OfferingBalancesheetOutcome2024Repository  extends JpaRepository<OfferingBalancesheetOutcome2024Entity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_outcome_2024 WHERE saishin_kbn= 1 AND accrual_date_value BETWEEN ?3 AND ?4  AND MATCH(search_words) AGAINST (?1 IN BOOLEAN MODE) LIMIT 100 OFFSET ?2", nativeQuery = true)
    List<OfferingBalancesheetOutcome2024Entity> findFullText(String searchWords, int offset, LocalDate startDate, LocalDate endDate);

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_outcome_2024 WHERE saishin_kbn= 1 AND accrual_date_value BETWEEN ?2 AND ?3  AND MATCH(search_words) AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
    Integer findFullTextCount(String searchWords, LocalDate startDate, LocalDate endDate);
    
    
    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingBalancesheetOutcome2024Entity> findFirstByOrderByOfferingBalancesheetOutcomeCodeDesc();
    
    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param documentCode 文書同一識別コード
     * @return データリスト
     */
    List<OfferingBalancesheetOutcome2024Entity> findByDocumentCodeOrderByOfferingBalancesheetOutcomeId(Long documentCode);
    

}
